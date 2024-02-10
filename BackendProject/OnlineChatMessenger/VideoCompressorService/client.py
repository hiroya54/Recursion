import socket
import os
import sys
import json

SERVER_ADDRESS = "0.0.0.0"
SERVER_PORT = 9002
MAX_FILE_SIZE = pow(2,32)
MAX_PACKET_SIZE = 1400
RESPONSE_SIZE = 16


def main():
    sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    
    try:
        sock.connect((SERVER_ADDRESS, SERVER_PORT))
    except socket.error as err:
        print(err)
        sys.exit(1)
    
    file_path = input("Please enter a file path: ")
    try:
        
        with open(file_path, "rb") as f:
            header = f.read(12)
            file_type = header[4:8].decode("utf-8")
            if not file_type == "ftyp":
                raise ValueError("This file is not mp4 file.")
            
            f.seek(0, os.SEEK_END)
            # 単位はバイト
            file_size = f.tell()
            f.seek(0,0)
            
            if file_size > MAX_FILE_SIZE :
                raise Exception("File size must be below 4 GB")
            
            request_header = file_size.to_bytes(32, "big")
            
            sock.sendall(request_header)
            
            data = f.read(MAX_PACKET_SIZE)
            while data:
                print("Sending...")
                sock.sendall(data)
                data = f.read(MAX_PACKET_SIZE)
                
            # response = sock.recv(RESPONSE_SIZE)
            
            # status_code = int.from_bytes(response, big)
            # print(status_code)
            
    except ValueError as err:
        print(err)
        
    except Exception as err:
        print(err)
        
    finally:
        print("connection close")
        sys.exit(0)
    

if __name__ == "__main__":
    main()