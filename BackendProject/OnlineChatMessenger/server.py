import socket
import os
import sys
import json
import random
import string
import threading
from datetime import datetime

SERVER_ADDRESS = "0.0.0.0"
SERVER_PORT_TCP = 9001
SERVER_PORT_UDP = 9002
ONLINE_LIMIT = 300

def main():
    sock_tcp = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    sock_udp = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)

    sock_tcp.bind((SERVER_ADDRESS, SERVER_PORT_TCP))
    sock_udp.bind((SERVER_ADDRESS, SERVER_PORT_UDP))
    print('Starting up on {} tcp port {} and udp port {}'.format(SERVER_ADDRESS, SERVER_PORT_TCP, SERVER_PORT_UDP))
    
    sock_tcp.listen(10)
    
    # chatroom名とそれに紐づく情報を管理する
    # chat_rooms = { "ルーム名" : {"host_token" : host_token ,"member_token" : [member_token]}}
    chat_rooms = {}
    # トークンをキーとして、ユーザ名とアドレス、アクティブ時間を管理する。
    # users = {"トークン" : {"username":username, "address":address, "last_active_time" : datetime.now()}} 
    users = {} 

    # スレッドの作成と開始
    # TCP接続（チャットルーム作成/招待）とUDC接続(メッセージ処理)でマルチスレッド処理
    threading.Thread(target = create_or_invite_chat_room, args = (sock_tcp, chat_rooms, users,)).start()
    threading.Thread(target = process_message, args = (sock_udp, chat_rooms, users,)).start()


def create_or_invite_chat_room(sock, chat_rooms, users):
    while True:
        connection, client_address = sock.accept()
        
        try:
            print("connection from", client_address)
            
            #リクエストをヘッダーとボディに分けて受け取る
            #ヘッダー部処理
            header = connection.recv(32)
                      
            room_name_length = int.from_bytes(header[:1], "big")
            operation = int.from_bytes(header[1:2], "big")
            state = int.from_bytes(header[2:3], "big")
            payload_length = int.from_bytes(header[3:32], "big")
            body_length = room_name_length + payload_length
            
            # ボディ部処理
            body = connection.recv(body_length)
            
            room_name = body[:room_name_length].decode("utf-8")
            request_payload = json.loads(body[room_name_length:body_length].decode("utf-8"))
            username = request_payload["username"]
            
            # ヘッダーとボディの情報からステータスコードを設定
            # 200以外はリクエスト失敗となる
            status_code = set_status_code(state, operation, room_name, chat_rooms)

            # レスポンスは2回に分けて行う
            # 1回目のレスポンスはステータスコードを返す
            # 1回目のレスポンスの作成と送信
            first_response_body = json.dumps(create_first_response_payload(status_code)).encode()  
            first_response_header = create_response_header(room_name_length, operation, state+1, len(first_response_body))             
            connection.sendall(first_response_header)
            connection.sendall(first_response_body)

            # ステータスコードが200の場合のみ、2回目のレスポンス処理に進む
            if not status_code == 200:
                raise Exception("Invalid Client Request") 

            # トークンを作成し、ユーザーリストに追加
            token = create_token()
            users[token] = {
                "username" : username,
                "address" : client_address,
                "last_active_time" : datetime.now()
            }
            
            # operationからチャットルームの作成 or 参加を判定
            # チャットルームの新規作成
            if operation == 1:
                #トークンの発行及びチャットルームの作成
                chat_rooms[room_name]={
                    "host_token" : token,
                    "member_token" : [token]
                }
                print("Created new chat room {}".format(room_name))
                
            # チャットルームに参加
            elif operation == 2:
                # チャットルームのトークン取得およびチャットルームにメンバー追加
                chat_rooms[room_name]["member_token"].append(token)
                print("Invite to {}".format(chat_rooms))
            
            # 2回目のレスポンスはルーム名とトークンを返す
            # 2回目のレスポンスの作成と送信
            second_response_body = room_name.encode() + json.dumps(create_second_response_payload(token)).encode()
            second_response_header = create_response_header(room_name_length, operation, state+2, len(second_response_body))
            connection.sendall(second_response_header)
            connection.sendall(second_response_body)
            
            print("Complete create or participate in the chat room {}".format(room_name))
            
        except Exception as e:
            print("Error" + str(e))
            
        finally:
            print("Closing current connection")
            print("Waiting message and request...")
            connection.close()
            

def set_status_code(state, operation, room_name, chat_rooms):
    status_code = None
    if not state == 0 :
        status_code = 401 #state不正
        
    elif not (operation == 1 or operation == 2):
        status_code = 402 #operaion不正
        
    elif (room_name in chat_rooms) and (operation == 1):
        status_code = 403 #作成したいチャットルームがすでに存在する
        
    elif (not room_name in chat_rooms) and (operation == 2):
        status_code = 404 #参加したいチャットルームが存在しない 
        
    else :
        status_code = 200
    
    return status_code

def create_first_response_payload(status_code):
    return {"status_code" : status_code}

def create_second_response_payload(token):
    return {"token" : token}
    
def create_response_header(room_name_length, operation, state, response_payload_length):
    return room_name_length.to_bytes(1,"big") + operation.to_bytes(1, "big") + state.to_bytes(1, "big") + response_payload_length.to_bytes(29, "big")

def create_token():
    chars = string.ascii_letters + string.digits
    return "".join(random.choice(chars) for _ in range(10))

def process_message(sock, chat_rooms, users):
    while True:
        print("Waiting message and request...")
        data, address = sock.recvfrom(4096)
        room_name_length = int.from_bytes(data[:1], "big")
        token_length = int.from_bytes(data[1:2], "big")
        
        room_name = data[2 : 2 + room_name_length].decode("utf-8")
        client_token = data[2 + room_name_length : 2 + room_name_length + token_length].decode("utf-8")

        full_length = int.from_bytes(data, "big")
        message = data[2 + room_name_length + token_length : full_length].decode("utf-8")
        
        # チャットルームの存在確認
        if not room_name in chat_rooms:
            if message == "exit":
                # ホストユーザが退出しチャットルームが閉じた通知を受けたユーザがexitをした時のための処理
                del users[client_token]
            else:
                response = "From Server : Chat room do not exist..."
                sock.sendto(response.encode(), address)           
            continue
        
        host_token = chat_rooms[room_name]["host_token"]
        hostname = users[host_token]["username"]
        chat_memberes_token = chat_rooms[room_name]["member_token"]
        username = users[client_token]["username"]
        
        # メッセージがexitの場合、クライアントをチャットメンバーから除外する
        # クライアントがチャットルームのホストだった場合はチャットルームを削除する
        if message == "exit" :
            exit_message = ""
            if hostname == username:
                exit_message = "From Server: Host user has left. Close chat room {}".format(room_name)
                [sock.sendto(exit_message.encode(), users[x]["address"]) for x in chat_memberes_token]
                del chat_rooms[room_name]
                del users[client_token]
                print("Close chat room {} ...".format(room_name))
            else :
                exit_message = "From Server: {} has left {}".format(username, room_name)
                [sock.sendto(exit_message.encode(), users[x]["address"]) for x in chat_memberes_token]
                chat_memberes_token.remove(client_token)
                del users[client_token]
                print("{} has left {}.".format(username, room_name))
            continue
            
        print("received {} message from {}".format(message, username))
        
        # 各クライアントが最後にメッセージを送った時間を確認し、5分以上前だったら送信対象外
        del_user_tokens = [] # 削除対象ユーザのアドレスが入る
        for key in  chat_memberes_token:
            if not key == client_token :
                now = datetime.now()
                time_difference = now - users[key]["last_active_time"]
                if int(time_difference.total_seconds()) > ONLINE_LIMIT :
                    # オフラインになったユーザをチャットルームから退出させる場合は下記のコメントアウトを外す
                    # del_user_tokens.append(key)
                    continue
                else :
                    sending_message = "From {}: {}".format(username, message) 
                    sock.sendto(sending_message.encode(), users[key]["address"]) 
        
        #  オフラインになったユーザをチャットルームから退出させる場合は下記のコメントアウトを外す
        
        # for i in range(len(del_user_tokens)):
        #     chat_memberes_token.remove(del_user_tokens[i])
        # del_user_tokens.clear()
        print("Time: {}".format(datetime.now()))
        print("User: {}".format(username))
        print("Chat room : {}".format(room_name))
        print("Message: {}".format(message))
        print("send to all active user")
        
        users[client_token]["last_active_time"] = datetime.now()
        
if __name__ == "__main__":
    main()