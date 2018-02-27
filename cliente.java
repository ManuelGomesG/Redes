import java.net.*;
import java.io.*;
import java.util.*;



public class cliente {




  public static void main(String[] args) {
    final String HOST = "localhost";
    final int PUERTO=5000;
    PrintStream comando;
    InputStreamReader buffentrada;
    BufferedReader entrada;
    String respuesta;
    String line;


    Socket conexion;

    try {
      conexion = new Socket(HOST,PUERTO);
      buffentrada = new InputStreamReader(conexion.getInputStream());
      entrada     = new BufferedReader(buffentrada);
      while(true){
        buffentrada = new InputStreamReader(conexion.getInputStream());
        entrada     = new BufferedReader(buffentrada);
        respuesta   = entrada.readLine().replace("°","\n");
        System.out.println(respuesta);
        Scanner sc  = new Scanner(System.in);
        comando     = new PrintStream(conexion.getOutputStream());
        comando.println(sc.nextLine());
      }
    }catch(Exception e){
      System.out.println("Cliente:" +e.getMessage());

    }


  }
}
