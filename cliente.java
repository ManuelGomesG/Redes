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
      while(true){
      Scanner sc = new Scanner(System.in);
      comando = new PrintStream(conexion.getOutputStream());
      comando.println(sc.nextLine());



      buffentrada = new InputStreamReader(conexion.getInputStream());
      entrada     = new BufferedReader(buffentrada);

      while((line = entrada.readLine()) != null){
        System.out.println(line);
      }
    }
    }catch(Exception e){
      System.out.println("cancer" +e.getMessage());

    }


  }
}
