import socket
import random
import threading
import json
import sys

# グローバル変数
SERVER_ADDRESS = "0.0.0.0"
SERVER_PORT_TCP = 9001
SERVER_PORT_UDP = 9002
MAX_MESSAGE_SIZE = 4096
CLIENT_ADDRESS = "localhost"


def main():
    
    sock_tcp = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    sock_udp = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)

    client_port = random.randint(9050, 9999)
    sock_tcp.bind((CLIENT_ADDRESS,client_port))

    # サーバに接続(TCP)
    try:
        connect_to_server(sock_tcp, SERVER_ADDRESS, SERVER_PORT_TCP)
    except RuntimeError as err:
        print(err)
        sys.exit(1)
    print("connecting to {} port {}".format(SERVER_ADDRESS,SERVER_PORT_TCP))
    
    # ユーザ名を入力
    username =  input("Please enter your username: ")

    # チャットルーム作成 or 参加を確認
    print("Please enter operation number")
    operation = None

    while True:
        print("1: Create new chatroom, 2: participated in a chatroom")
        user_input = input("Operation number: ")
        if user_input == "1" or user_input == "2":
            operation = int(user_input)
            break
        else :
            print("Invalid operation number. Pleare re-enter operation number")

    # ルーム名を入力
    input_room_name = input("Please enter a chat room name: ")

    # リクエストの作成
    payload = json.dumps(create_request_payload(username)).encode()
    request_header = create_request_header(len(input_room_name), operation, 0, len(payload))
    request_body = input_room_name.encode() + payload

    # 一連のサーバとのやり取りが正常に処理されたかを判定するフラグ
    is_request_successful = True

    try:
        # リクエストの送信
        sock_tcp.sendall(request_header)
        sock_tcp.sendall(request_body)
        print("Request was send")
        
        # 1回目のレスポンス処理
        # ステータスコードを受け取り、送信したリクエストが問題ないか確認する
        first_response_header  = sock_tcp.recv(32)
        
        response_room_name_length = int.from_bytes(first_response_header[:1], "big")
        operation = int.from_bytes(first_response_header[1:2], "big")
        state = int.from_bytes(first_response_header[2:3], "big")
        first_response_payload_length = int.from_bytes(first_response_header[3:32], "big")

        first_response_body = sock_tcp.recv(first_response_payload_length)
        
        status_code = json.loads(first_response_body.decode("utf-8"))["status_code"]
        print("statu code: {}".format(status_code))
        if not status_code == 200:
            raise Exception("Request Failure")
        
        # 2回目のレスポンス処理
        # ルーム名とトークンを受け取る
        second_response_header = sock_tcp.recv(32)
        
        second_response_payload_length = int.from_bytes(second_response_header[3:32], "big")
        
        second_response_body = sock_tcp.recv(response_room_name_length + second_response_payload_length)
        
        response_room_name = second_response_body[:response_room_name_length].decode("utf-8")
        
        token = json.loads(second_response_body[response_room_name_length : len(second_response_body)].decode("utf-8"))["token"]
        
    except Exception as err :
        is_request_successful = False
        print(str(err))

    finally :
        print("closing socket")
        sock_tcp.close()
    
    # 一連のサーバとのやり取りが正常終了しなかった場合はプログラム終了
    if not is_request_successful:
        sys.exit(1)
        
    print("You are participated in {} !\n".format(input_room_name)) 

    # udp通信用
    sock_udp.bind((CLIENT_ADDRESS, client_port))

    # スレッドの作成と開始
    threading.Thread(target=receive_data, args=(sock_udp,)).start()
    threading.Thread(target=wait_for_user_input, args=(sock_udp, token, response_room_name,)).start()
        

def receive_data(sock):
    while True:
        data, server = sock.recvfrom(4096)
        print(data.decode("utf-8"))

def wait_for_user_input(sock, token, room_name):
    try:
        while True:
            message_input = input("")
            print("\033[1A\033[1A")
            print("You: " + message_input)
            
            # ボディ部の作成
            room_name_byte = room_name.encode()
            token_byte = token.encode()
            message_byte = message_input.encode()
            
            # ヘッダー部の作成
            message_header = createmessage_header(len(room_name_byte), len(token_byte))
            
            message = message_header + room_name_byte + token_byte + message_byte

            if len(message) > MAX_MESSAGE_SIZE :
                raise Exception('message must be below 4MB.')
            sent = sock.sendto(message, (SERVER_ADDRESS, SERVER_PORT_UDP))

            if message_input == "exit":
                print("You has left {}.".format(room_name))
                break
    finally:
        print("socket close")
        sock.close


def connect_to_server(sock, SERVER_ADDRESS,SERVER_PORT_TCP):
    try:
        sock.connect((SERVER_ADDRESS, SERVER_PORT_TCP))
    except sock.error as err:
        raise RuntimeError("サーバの接続に失敗しました") from err

def create_request_header(room_name_length, operation, state, payload_length):
    return room_name_length.to_bytes(1, "big") + operation.to_bytes(1, "big") + state.to_bytes(1, "big") + payload_length.to_bytes(29, "big")

def create_request_payload(username):
    return {"username" : username}

def createmessage_header(room_name_length, token_length):
    return room_name_length.to_bytes(1, "big") + token_length.to_bytes(1, "big")

if __name__ == "__main__":
    main()