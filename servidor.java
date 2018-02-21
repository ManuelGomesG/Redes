import java.net.*;
import java.io.*;
import java.util.concurrent.*;


public class servidor {








  public static void main(String[] args) {

    final int PUERTO = 5000;
    ServerSocket servidor;
    Socket conexion;

    DataOutputStream salida;
    BufferedOutputStream buffsalida;
    BufferedInputStream buffentrada;
    String recibido;

    try {

      servidor = new ServerSocket(PUERTO);
      while(true){

        conexion=new Socket();
        System.out.println("Esperando conexion");
        conexion=servidor.accept();
        System.out.println("Conexion establecida");

        
      }

    }catch(Exception e)
    {
      System.out.println("cancer servidor" + e.getMessage());
    }


  }




}
