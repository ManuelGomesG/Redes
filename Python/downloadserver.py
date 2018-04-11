import SimpleXMLRPCServer
import socket
import sys
from threading import Thread
from os import listdir
import xmlrpclib



def alo():
    return "Funciona"

def list():
    print str(listdir(str(sys.argv[1])))
    return listdir(str(sys.argv[1]))

class dServerThread(Thread):

    def __init__(self):
        Thread.__init__(self)
        #self.ip = ip
        #self.port = port
        #print "[+] New server socket thread started for " + ip + ":" + str(port)

    def run(self):
        dServer=SimpleXMLRPCServer.SimpleXMLRPCServer(("0.0.0.0",port))
        dServer.register_function(alo)
        dServer.register_function(list)

        dServer.serve_forever()






menu = "1) Libros_descargados \n2) Progreso \n3) Clientes "
def selection(str):
    if "libros_descaragados" in str.lower():
        client = xmlrpclib.ServerProxy("http://localhost:2004")
        for i in client.preguntaserv():
    elif "lista_libros" in str.lower():
        dserver.send(str)
        data = dserver.recv(BUFFER_SIZE)
        print data


    elif "solicitud" in str.lower():
        dserver.send(str)

    elif "libros_descargadosxservidor" in str.lower():
        dserver.send(str)

    elif "exit" in str.lower():
        dserver.send(str)
        pass

    else:
        print "Invalid option"

        return;

host = socket.gethostname() #cambiar, poner ip
port = 5000+int(sys.argv[1])
BUFFER_SIZE = 2048

#print str(listdir(str(sys.argv[1])))
thread = dServerThread()
thread.start()


while True:
    print menu
    MESSAGE = raw_input()
    selection(MESSAGE)


#    #data = dserver.recv(BUFFER_SIZE)
    #print " Client2 received data:", data
