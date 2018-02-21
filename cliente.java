import java.net.*;
import java.io.*;



public class cliente {




  public static void main(String[] args) {
    final String HOST = "localhost";
    final int PUERTO=5000;

    Socket conexion;

    try {
      conexion = new Socket(HOST,PUERTO);
    }catch(Exception e){
      System.out.println("cancer" +e.getMessage());
    }


  }
}
