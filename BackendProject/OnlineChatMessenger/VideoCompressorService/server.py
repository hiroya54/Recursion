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
    
    sock.bind((SERVER_ADDRESS,SERVER_PORT))
    
    sock.listen(1)
    
    print('Starting up on {} port {}'.format(SERVER_ADDRESS, SERVER_PORT))
    
    while True:
        
        try:
            connection, address = sock.accept()
            print("connection from", address)
            
            request_header = connection.recv(32)
            
            file_size = int.from_bytes(request_header, "big")
            file_path = "downloads/vc.mp4"
            print("file size: " + str(file_size))
            with open(file_path, "wb+") as f:
                while file_size > 0:
                    data = connection.recv(file_size if file_size <= MAX_PACKET_SIZE else MAX_PACKET_SIZE)
                    f.write(data)
                    file_size -= len(data)
                    print("file size: " + str(file_size))
            
            print('Finished downloading the file from client.')
        finally :
            connection.close()
            
if __name__ == "__main__":
    main()