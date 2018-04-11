
import socket
import xmlrpclib
from threading import Thread
import time

menu = "1) LISTA_LIBROS \n2) SOLICITUD <libro> \n"
ip="0.0.0.0"
port=2004
listalibros=[]

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
        pass
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


    def solicitud(self,s):
        self.client.solicitud(s)

    def recfile(self,name):

        bind = self.client.sendummy("a.pdf")
        with open(name, "wb") as handle:
            handle.write(bind.data)






def selection(str):

    if "lista_libros" in str.lower():
        global listalibros
        if not listalibros:
            clienthread = ClientThread(ip,port,0)
            clienthread.start()
            listalibros = clienthread.listall()
            for i in listalibros:
                print i
            clienthread.join()
        else:
            for i in listalibros:
                print i


    elif "solicitud" in str.lower():
        global listalibros
        libro = str.split(' ',1)[1]
        if not listalibros:
            clienthread = ClientThread(ip,port,0)
            clienthread.start()
            listalibros = clienthread.listall()
            time.sleep(.1)
            clienthread.join()
        for i in listalibros:
            if libro.lower() == i.lower():
                clienthread = ClientThread(ip,port,0)
                clienthread.start()
                servers = clienthread.solicitud(libro)
                #time.sleep(.1)
                npieces=len(servers)
                rpclient=[]
                for i in servers:
                    rpclient.append(xmlrpclib.ServerProxy("http://localhost:"+str(5000+i)))

                rdata = []
                print "Empezando descarga"
                for i in rpclient:
                    rdata.append(i.download(libro,i,npieces))

                print "Reconstruyendo"
                with open(libro, "wb") as handle:
                    for i in rdata:
                        handle.write(i.data)

                print "Proceso finalizado"

                clienthread.join()
                return
        print "Libro invalido"




    elif "exit" in str.lower():
        clienthread = ClientThread(ip,port,0)
        clienthread.start()
        clienthread.recfile("funciona.pdf")


    else:
        print "Opcion invalida"

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
