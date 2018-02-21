import java.net.*;
import java.io.*;
import java.util.*;



public class cliente {




  public static void main(String[] args) {
    final String HOST = "localhost";
    final int PUERTO=5000;
    DataOutputStream comando;

    Socket conexion;

    try {
      conexion = new Socket(HOST,PUERTO);
      Scanner sc = new Scanner(System.in);
      comando = new DataOutputStream(conexion.getOutputStream());
      comando.writeUTF(sc.nextLine());
    }catch(Exception e){
      System.out.println("cancer" +e.getMessage());

    }


  }
}
