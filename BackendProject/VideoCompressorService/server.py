import socket
import os
import sys
import json
import ffmpeg

SERVER_ADDRESS = "0.0.0.0"
SERVER_PORT = 9001
MAX_FILE_SIZE = pow(2,32)
MAX_PACKET_SIZE = 1400
RESPONSE_SIZE = 16
HEADER_SIZE_TOTAL = 64
HEADER_SIZE_JSON = 16
HEADER_SIZE_MEDIA = 1
HEADER_SIZE_PAYLOAD = 47
MAX_PAYLOAD_SIZE = pow(2,HEADER_SIZE_PAYLOAD)
FILE_PATH = "server_downloads/"
TMP_FILE_PATH = "server_tmp/"
def main():
    sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    
    sock.bind((SERVER_ADDRESS,SERVER_PORT))    
    sock.listen(1)
    
    print('Starting up on {} port {}'.format(SERVER_ADDRESS, SERVER_PORT))
    
    while True:
        print("Waiting connection...")
        try:
            connection, address = sock.accept()
            print("connection from", address)

            #step1: 受信処理
            request_header = connection.recv(HEADER_SIZE_TOTAL)
            request_json_length = int.from_bytes(request_header[:HEADER_SIZE_JSON], "big")
            request_media_length = int.from_bytes(request_header[HEADER_SIZE_JSON : HEADER_SIZE_JSON + HEADER_SIZE_MEDIA], "big")
            request_payload_length = int.from_bytes(request_header[HEADER_SIZE_JSON + HEADER_SIZE_MEDIA: HEADER_SIZE_TOTAL], "big")
            
            # ボディ部の受け取り
            request_encoded_json = connection.recv(request_json_length)
            request_json = json.loads(request_encoded_json.decode("utf-8"))
            
            request_encoded_media_type = connection.recv(request_media_length)
            request_media_type = request_encoded_media_type.decode("utf-8")     
            
            request_file_length = request_json["file_size"]
            print("file size: " + str(request_file_length))

            input_file_name = FILE_PATH + request_json["file_name"]
            with open(input_file_name, "wb+") as f:
                total = request_file_length
                while request_file_length > 0:
                    data = connection.recv(request_file_length if request_file_length <= MAX_PACKET_SIZE else MAX_PACKET_SIZE)
                    f.write(data)
                    request_file_length -= len(data)
                    print("Downloaded: " + str((total-request_file_length)*100 / total) + " %")
                    print("\033[1A\033[1A")
            
            print('Finished downloading the file from client.')
            
            # step2: 動画処理
            processing_method = request_json["processing_method"]
            output_file_name = TMP_FILE_PATH + request_json["file_name"]
            response_media_type = "mp4"
            
            print("Start processing...")
            if processing_method == 1:
                compress_video(input_file_name, output_file_name)

            elif processing_method == 2:
                resolution_width = request_json["resolution_width"]
                resolution_height = request_json["resolution_height"]
                change_resolution(input_file_name, output_file_name, resolution_width, resolution_height)

            elif processing_method == 3:
                aspect_width = request_json["aspect_width"]
                aspect_height = request_json["aspect_height"]
                change_aspect_ratio(input_file_name, output_file_name, aspect_width, aspect_height)

            elif processing_method == 4:
                response_media_type = "mp3"
                output_file_name = output_file_name.replace("mp4", "") + response_media_type
                convert_mp4_to_mp3(input_file_name, output_file_name)

            elif processing_method == 5:
                response_media_type = "gif"
                output_file_name = output_file_name.replace("mp4", "") + response_media_type
                start_time = request_json["start_time"]
                end_time = request_json["end_time"]
                get_video_duration(input_file_name)
                create_gif_or_webm(input_file_name, output_file_name, start_time, end_time)

            print("Complete processing")
            
            # step3: 送信処理
            with open(output_file_name, "rb") as f:

                f.seek(0, os.SEEK_END)
                response_file_length = f.tell()
                f.seek(0,0)

                status_code = 200
                response_encoded_json = create_encoded_json(response_file_length, status_code)
                response_encoded_media_type = response_media_type.encode()
                response_payload_length = len(response_encoded_json) + len(response_encoded_media_type) + response_file_length
                response_header = create_multiple_media_protocol_header(len(response_encoded_json), len(response_encoded_media_type), response_payload_length)
                
                connection.sendall(response_header)
                connection.sendall(response_encoded_json)
                connection.sendall(response_encoded_media_type)


                response_data = f.read(MAX_PACKET_SIZE)
                print("Sending...")
                while response_data:
                    connection.sendall(response_data)
                    response_data = f.read(MAX_PACKET_SIZE)
                print("Complete Sending file!")

                print("Delete file...")
                delete_file(input_file_name)
                delete_file(output_file_name)
                print("File was deleted")

        except FileNotFoundError as e:
            print(e)
    
        except Exception as e:
            print(e)

        except ffmpeg.Error as e:
            print(e.stderr)

        finally :
            connection.close()
            

def create_multiple_media_protocol_header(json_length, media_type_length, payload_length):
    return json_length.to_bytes(HEADER_SIZE_JSON, "big") + media_type_length.to_bytes(HEADER_SIZE_MEDIA, "big") + payload_length.to_bytes(HEADER_SIZE_PAYLOAD, "big")

def create_encoded_json(file_size, status_code):
    
    dict_json = {
        "file_size" : file_size,
        "status_code" : status_code
        }
        
    return json.dumps(dict_json).encode()

def compress_video(input_file_name, output_file_name):
    ffmpeg.input(input_file_name).output(output_file_name, vcodec="libx264", crf=23).run(capture_stderr=True)

def change_resolution(input_file_name, output_file_name, resolution_width, resolution_height):
    ffmpeg.input(input_file_name).filter("scale", resolution_width, resolution_height).output(output_file_name).run()

def change_aspect_ratio(input_file_name, output_file_name, aspect_width, aspect_height):
    aspect_ratio = str(aspect_width) + ":" + str(aspect_height)
    print(aspect_ratio)

    ffmpeg.input(input_file_name).filter("setdar", dar=aspect_ratio).output(output_file_name).run()

def convert_mp4_to_mp3(input_file_name, output_file_name):
    ffmpeg.input(input_file_name).output(output_file_name, format="mp3").run()

def create_gif_or_webm(input_file_name, output_file_name, start_time, end_time):
    ffmpeg.input(input_file_name, ss=start_time, to=end_time).output(output_file_name, format="gif").run()         

def get_video_duration(input_file_name):
    probe = ffmpeg.probe(input_file_name)
    duration = float(probe["streams"][0]["duration"])
    return duration

def delete_file(file_path):

    os.remove(file_path)

if __name__ == "__main__":
    main()