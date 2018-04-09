
import socket
import xmlrpclib
from threading import Thread



class ClientThread(Thread):
    def __init__(self,ip,port,conn):
        Thread.__init__(self)
        self.ip = ip
        self.port = port
        self.soc = conn
        self.url = "http://localhost:2004"
        self.client = xmlrpclib.ServerProxy(self.url)

        #print "[+] New server socket thread started for " + ip + ":" + str(port)



    def run(self):
        print "Creando hilo para comunicarse con un servidor de descarga"
        #while True:

        #################################################################
        ##while True :
            ##data = conn.recv(2048)
            ##print "Server received data:", data
            #MESSAGE = raw_input("Multithreaded Python server : Enter Response from Server/Enter exit:")
            ##if not data:
                ##break
            ##if data == 'exit':
                ##break
            #conn.send(MESSAGE)  # echo





    def listall(self):
        return self.client.listallc()




menu = "1) LISTA_LIBROS \n2) SOLICITUD <libro> \n"
ip="0.0.0.0"
port=2004


def selection(str):

    if "lista_libros" in str.lower():
        clienthread = ClientThread(ip,port,0)
        clienthread.start()
        l = clienthread.listall()
        for i in l:
            print i
        clienthread.join()


    elif "solicitud" in str.lower():
        client.send(str)



    elif "exit" in str.lower():
        client.send(str)
        pass

    else:
        print "Invalid option"

        return;








'''
host = socket.gethostname()
print " HOST : " + host
port = 2004
BUFFER_SIZE = 2000

client = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
client.connect((host, port))
'''

while True:
    print menu
    MESSAGE = raw_input()
    selection(MESSAGE)
    #client.send(MESSAGE)
    #data = client.recv(BUFFER_SIZE)
    #print " Client2 received data:", data

client.close()
