import socket
from threading import Thread
from SocketServer import ThreadingMixIn
import logging
import xmlrpclib
import SimpleXMLRPCServer
import time

listalibros = []
stats = []
dserverat = [0,0,0]
menu = "1) Estadisticas_libros \n2) Estadisticas_servidores"
wthread="guh"
# Multithreaded Python server : TCP Server Socket Thread Pool
class WorkerThread(Thread):
    def __init__(self,n):
        Thread.__init__(self)
        self.att=n

    def run(self):
        pass

    def buildstats(self):
        global stats
        global listalibros
        for i in listalibros:
            stats.append(0)

    def statact(self,str):
        global listalibros
        global stats
        stats[listalibros.index(str)] = stats[listalibros.index(str)]+1
        global listas
        for i in range(0,3):
            if str in listas[i]:
                dserverat[i]=dserverat[i]+1



class ClientThread(Thread):

    def __init__(self,ip,port):
        Thread.__init__(self)
        self.ip = ip
        self.port = port
        #print "[+] New server socket thread started for " + ip + ":" + str(port)

    def run(self):
        mServer=SimpleXMLRPCServer.SimpleXMLRPCServer(("0.0.0.0",2004))
        mServer.register_function(listallc)
        mServer.register_function(solicitud)
        mServer.register_function(sendummy)


        mServer.serve_forever()


        '''
        while True :
            data = conn.recv(2048)
            print "Server received data:", data
            #MESSAGE = raw_input("Multithreaded Python server : Enter Response from Server/Enter exit:")
            if not data:
                break
            if data == 'exit':
                break
            #conn.send(MESSAGE)  # echo'''


class DServerThread(Thread):
    def __init__(self,ip,port,conn):
        Thread.__init__(self)
        self.ip = ip
        self.port = port
        self.soc = conn
        self.url = "http://localhost:"+str(port)
        self.msclient = xmlrpclib.ServerProxy(self.url)

        #print "[+] New server socket thread started for " + ip + ":" + str(port)



    def run(self):
        pass
        #print "Creando hilo para comunicarse con un servidor de descarga"
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

    def sendmsg(self,str):
        self.soc.send(str)

    def alo(self):
        print self.msclient.alo()

    def list(self):
        return self.msclient.list()

def listall(l1,l2,l3):
    la = l1
    for i in l2:
        if i in la:
            pass
        else:
            la.append(i)
    for i in l3:
        if i in la:
            pass
        else:
            la.append(i)

    return la

def listallc():
    global listalibros
    return listalibros



def solicitud(s):

    global listalibros
    global wthread
    global listas
    servers = []
    for i in listalibros:
        if s.lower() in i.lower():
            wthread.statact(i)
            break
    for i in range(0,3):
        if s in listas[i]:
            servers.append(i+1)


    return servers

def sendummy(fname):
    with open(fname, "rb") as handle:
        binary_data = xmlrpclib.Binary(handle.read())

    return binary_data




def selection(str):

    if "estadisticas_libros" in str.lower():
        global listalibros
        global stats
        print "Cantidad -- Libro"
        for i in range(0,len(listalibros)):
            print stats[i], " -- ", listalibros[i]




    elif "estadisticas_servidores" in str.lower():
        global dserverat
        print "       Servidor        -- Cantidad"

        print "Servidor de descarga 1 -- ", dserverat[0]
        print "Servidor de descarga 2 -- ", dserverat[1]
        print "Servidor de descarga 3 -- ", dserverat[2]





    elif "exit" in str.lower():
        client.send(str)
        pass

    else:
        print "Opcion invalida"

        return;


# Multithreaded Python server : TCP Server Socket Program Stub
TCP_IP = '0.0.0.0'
TCP_PORT = 2004
sport = 5001
BUFFER_SIZE = 20  # Usually 1024, but we need quick response


##dServer=socket.socket(socket.AF_INET, socket.SOCK_STREAM)
##dServer.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
##dServer.bind((TCP_IP, sport))
tcpServer = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
tcpServer.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
tcpServer.bind((TCP_IP, TCP_PORT))
cthreads = [] #threads de clientes
sthreads = [] #threads de servidores de descarga
controlthreads = [] #Threads que manejan el flujo del programa
listas = []
wthread = WorkerThread(1)
wthread.start()


for i in range(0,3):
    dserverthread = DServerThread(TCP_IP,sport+i,0)
    dserverthread.start()
    sthreads.append(dserverthread)

time.sleep(.1)
for i in range(0,3):
    listas.append(sthreads[i].list())
#generando las listas para las estadisticas
listalibros = listall(listas[0],listas[1],listas[2])
wthread.buildstats()

print str(stats)

cserverthread = ClientThread(TCP_IP,TCP_PORT)
cserverthread.start()


'''
for i in range(1, 4):
    dServer.listen(3)
    (conn, (ip,port)) = dServer.accept()
    newthread = DServerThread(ip,port, conn)
    newthread.start()
    sthreads.append(newthread)
    print "Servidores de descarga conectados : %d" % (i)

print "Canidad de threads: %d" % len(sthreads)
for t in sthreads:
    print t.getName()
    t.sendmsg(t.getName())

'''
print "SOLICITUD"
solicitud("b")






while True:
    print menu
    MESSAGE = raw_input()
    selection(MESSAGE)

    #tcpServer.listen(4)
    #print "Multithreaded Python server : Waiting for connections from TCP clients..."
    #(conn, (ip,port)) = tcpServer.accept()
    #newthread = ClientThread(ip,port)
    #newthread.start()
    #cthreads.append(newthread)

for t in cthreads:
    t.join()
