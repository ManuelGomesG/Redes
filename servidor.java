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
        entrada=new BufferedReader(new InputStreamReader(conexion.getInputStream()));
        System.out.println(entrada.readLine());


      }

    }catch(Exception e)
    {
      System.out.println("cancer servidor" + e.getMessage());
    }


  }




}
