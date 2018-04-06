import java.net.*;
import java.io.*;
import java.util.concurrent.*;
//import hilosservidor.*;


public class servidor {



  public static void main(String[] args) {

    final int PUERTO = 5000;
    int i            = 1;
    ServerSocket servidor;
    Socket conexion;

    try {

      servidor = new ServerSocket(PUERTO);
      System.out.println("Esperando conexion");
      while(true){
        conexion = servidor.accept();
        System.out.println("Creando el hilo " + i);
        Thread hilo = new Thread(new ClientWorker(conexion));
        hilo.start();
        i++;
      }

    }catch(Exception e){
      System.out.println("Servidor:" + e.getMessage());
    }


  }




}
