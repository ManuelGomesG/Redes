# Python TCP Client A
import socket

menu = "1) ESTADO_DESCARGAS \n2) LISTA_LIBROS \n3) SOLICITUD <libro> \n4) LIBROS_DESCARGADOSxSERVIDOR"
def selection(str):
    if "estado_descargas" in str.lower():
        print "EligiO ESTADO_DESCARGAS"

    elif "lista_libros" in str.lower():
        print "EligiO LISTA_LIBROS"

    elif "solicitud" in str.lower():
        print "EligiO SOLICITUD"

    elif "libros_descargadosxservidor" in str.lower():
        print "EligiO LIBROS_DESCARGADOSxSERVIDOR"

    elif "exit" in str.lower():
        break

    else:
        print "Invalid option"

        return;

host = socket.gethostname()
port = 2004
BUFFER_SIZE = 2000

tcpClientA = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
tcpClientA.connect((host, port))


while True:
    print menu
    selection(MESSAGE)
    MESSAGE = raw_input()
    tcpClientA.send(MESSAGE)
    #data = tcpClientA.recv(BUFFER_SIZE)
    #print " Client2 received data:", data

tcpClientA.close()
