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
    String linea;


    Socket conexion;

    try {
      conexion = new Socket(HOST,PUERTO);
        
        while (true){
          
        System.out.println("A ver");
        Scanner sc = new Scanner(System.in);
        comando = new PrintStream(conexion.getOutputStream());
        linea = sc.nextLine();
        System.out.println(linea);
        comando.println(linea);

        if (linea.equals("Solicitud libro")){
          try{
            System.out.println("Intentando conectar al socket");
            Thread.sleep(2000);
            Socket conexion2 = new Socket(HOST, 1234);
            System.out.println("Entrando en el nuevo socket");
            InputStream in = conexion2.getInputStream();
            FileOutputStream out = new FileOutputStream(new File("imagen_copia2.png"));

            byte buf[] = new byte[1024];
            int len;

            while((len = in.read(buf))!=-1){
              System.out.println("Empezando a descargar el archivo");
              out.write(buf,0,len);
            } 
            System.out.println("Descarga terminada");
            in.close();

          }catch(Exception e){
            System.out.println("Error en la segunda conexion del socket" + e.getMessage());
          }
        }
        


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
