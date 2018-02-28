import java.net.*;
import java.io.*;
import java.util.concurrent.*;


public class servidor {



  public static void main(String[] args) {

    final int PUERTO = 5000;
    ServerSocket servidor;
    Socket conexion;

    DataOutputStream salida;
    BufferedReader entrada;
    InputStreamReader buffentrada;
    PrintStream respuesta;
    String recibido;

    String menu = " 1) ESTADO_DESCARGAS \n 2) LISTA_LIBROS \n 3) SOLICITUD <libro> \n 4) LIBROS_DESCARGADOSxSERVIDOR  \n";

    try {

      servidor = new ServerSocket(PUERTO);
      System.out.println("Esperando conexion");
      while(true){
      conexion = servidor.accept();
      System.out.println("Conexion establecida");
      while(true){

      buffentrada = new InputStreamReader(conexion.getInputStream());
      entrada     = new BufferedReader(buffentrada);
      recibido    = entrada.readLine();

      System.out.println(recibido);

      if (recibido != null) {
        respuesta   = new PrintStream(conexion.getOutputStream());
        respuesta.println(menu);
        }
      }
      }

    }catch(Exception e)
    {
      System.out.println("cancer servidor" + e.getMessage());
    }


  }




}
