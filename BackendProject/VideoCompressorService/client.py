import socket
import os
import sys
import json

SERVER_ADDRESS = "0.0.0.0"
SERVER_PORT = 9001
MAX_FILE_SIZE = pow(2,32)
MAX_PACKET_SIZE = 1400
RESPONSE_SIZE = 16
INPUT_MEDIA_TYPE = "mp4"
CHECK_STR_INPUT_MEDIA_TYPE = "ftyp"
HEADER_SIZE_TOTAL = 64
HEADER_SIZE_JSON = 16
HEADER_SIZE_MEDIA = 1
HEADER_SIZE_PAYLOAD = 47
DOWNLOAD_PAHT = "client_downloads"
MAX_PAYLOAD_SIZE = pow(2,HEADER_SIZE_PAYLOAD)
DEFAULT_PROCESS_CHECK_TIME = 60


def main():
    sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    
    try:
        sock.connect((SERVER_ADDRESS, SERVER_PORT))
    except socket.error as err:
        print(err)
        sys.exit(1)
    
    input_file_path = input("Please enter a file path: ")
    
    print("You can select processing method.")
    print("1: compress the video, 2:change resolution, 3:change aspect ratio, 4:convet mp4 to mp3, 5:create gif or webm")
    while True:
        processing_method  = int(input("Please select a processing method form above and enter a number(1 ~ 5): "))
        
        if processing_method in (1, 2, 3, 4, 5):
            break
        else :
            print("Invalid input. Please re-enter a number(1 ~ 5): ")

    resolution_width = None   
    resolution_height = None
    if processing_method == 2 :
        resolution_width = int(input("Please enter width resolution: "))
        resolution_height = int(input("Please enter height resolution: "))
    
    aspect_width = None
    aspect_height = None
    if processing_method == 3:
        aspect_width = int(input("Please enter aspect width: "))
        aspect_height = int(input("Please enter aspect height: "))
        
    start_time = None
    end_time = None
    if processing_method == 5 :
        start_time = int(input("Please enter start time to create GIF and WEBM: "))
        end_time = int(input("Please enter end time to create GIF and WEBM: "))

    try:
        # step1: 送信処理
        with open(input_file_path, "rb") as f:
            header = f.read(12)
            file_type = header[4:8].decode("utf-8")
            if not file_type == CHECK_STR_INPUT_MEDIA_TYPE:
                raise ValueError("This file is not mp4 file.")

            f.seek(0,0)
            f.seek(0, os.SEEK_END)
            # 単位はバイト
            file_size = f.tell()
            f.seek(0,0)
            
            if file_size > MAX_FILE_SIZE :
                raise Exception("File size must be below 4 GB")
            
            # MMPに合わせたヘッダー及びファイル作成           
            media_type = INPUT_MEDIA_TYPE
            encoded_media_type = media_type.encode()
            input_file_name = os.path.basename(input_file_path)
            encoded_json = create_encoded_json(file_size, input_file_name, resolution_height, resolution_width, start_time, end_time, aspect_width, aspect_height, processing_method)
    
            payload_length = len(encoded_json) + len(encoded_media_type) +file_size
            
            request_header = create_multiple_media_protocol_header(len(encoded_json), len(encoded_media_type), payload_length)
            
            # ヘッダーの送信
            sock.sendall(request_header)
            
            # jsonファイル→メディアタイプ→ファイル本体の順にペイロードを送信
            sock.sendall(encoded_json)
            sock.sendall(encoded_media_type)
            data = f.read(MAX_PACKET_SIZE)
            print("Sending...")
            size = 0
            while data:
                sock.sendall(data)
                data = f.read(MAX_PACKET_SIZE)
            print("Complete sending file!")
            
        # step2: 受信処理
        print("Wait response from a server")
        # レスポンスヘッダーを受け取る
        response_header = sock.recv(HEADER_SIZE_TOTAL)
        response_json_length = int.from_bytes(response_header[:HEADER_SIZE_JSON], "big")
        response_media_length = int.from_bytes(response_header[HEADER_SIZE_JSON : HEADER_SIZE_JSON + HEADER_SIZE_MEDIA], "big")
        response_payload_length = int.from_bytes(response_header[HEADER_SIZE_JSON + HEADER_SIZE_MEDIA: HEADER_SIZE_TOTAL], "big")
        
        # jsonを受け取り、ステータスコードを確認する。
        response_encoded_json = sock.recv(response_json_length)
        response_json = json.loads(response_encoded_json.decode("utf-8"))
        response_code = response_json["status_code"]
        print("Status code: {}".format(response_code))
        
        if not response_code == 200:
            raise ValueError(response_json["error_message"])
        
        # 後続のペイロード受け取り
        response_encoded_media_type = sock.recv(response_media_length)
        response_media_type = response_encoded_media_type.decode("utf-8")  

        download_file_name = create_downloads_file_name(input_file_name, processing_method, response_media_type)
        response_file_length = response_json["file_size"]

        with open(os.path.join(DOWNLOAD_PAHT, download_file_name) ,"wb+") as f:
            total = response_file_length
            while response_file_length > 0:
                data = sock.recv(response_file_length if response_file_length <= MAX_PACKET_SIZE else MAX_PACKET_SIZE)
                f.write(data)
                response_file_length -= len(data)
                print("Downloaded: " + str((total-response_file_length)*100 / total) + " %")
                print("\033[1A\033[1A")
            
    except ValueError as err:
        print(err)
        
    except Exception as err:
        print(err)
        
    finally:
        print("connection close")
        sock.close()

def create_encoded_json(file_size, input_file_name, resolution_height, resolution_width, start_time, end_time, aspect_width, aspect_height, processing_method):
    
    dict_json = {
        "file_size" : file_size,
        "file_name" : input_file_name,
        "processing_method" : processing_method
        }
    
    if processing_method == 2:
        dict_json["resolution_width"] = resolution_width
        dict_json["resolution_height"] = resolution_height

    if processing_method == 3:
        dict_json["aspect_width"] = aspect_width
        dict_json["aspect_height"] = aspect_height    

    if processing_method == 5:
        dict_json["start_time"] = start_time
        dict_json["end_time"] = end_time
        
    return json.dumps(dict_json).encode()

def create_multiple_media_protocol_header(json_length, media_type_length, payload_length):
    return json_length.to_bytes(HEADER_SIZE_JSON, "big") + media_type_length.to_bytes(HEADER_SIZE_MEDIA, "big") + payload_length.to_bytes(HEADER_SIZE_PAYLOAD, "big")

def create_downloads_file_name(input_file_name, processing_method, response_media_type):
    return input_file_name.replace(("." + INPUT_MEDIA_TYPE), "") + "_" + str(processing_method) + "."+ response_media_type
    

if __name__ == "__main__":
    main()