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

        Scanner sc = new Scanner(System.in);
        comando = new PrintStream(conexion.getOutputStream());
        linea = sc.nextLine();
        comando.println(linea);
        String[] palabras = linea.split(" ");
        buffentrada = new InputStreamReader(conexion.getInputStream());
        entrada     = new BufferedReader(buffentrada);
        System.out.println(palabras[0]);
        if (palabras[0].equalsIgnoreCase("Solicitud")){
          try{
            System.out.println("Intentando conectar al socket");
            Thread.sleep(1500);
            Socket conexion2 = new Socket(HOST, 5020);
            System.out.println("Entrando en el nuevo socket");
            InputStream in = conexion2.getInputStream();
            String nombreArchivo = linea.substring(10);
            FileOutputStream out = new FileOutputStream(new File(nombreArchivo));

            byte buf[] = new byte[8192];
            int len;

            while((len = in.read(buf))!=-1){
              System.out.println("Empezando a descargar el archivo");
              System.out.println("Marca len: "+ len);
              out.write(buf,0,len);
            }
            System.out.println("Descarga terminada");
            in.close();

          }catch(Exception e){
            System.out.println("Error en la segunda conexion del socket" + e.getMessage());
          }
        }





        else if (linea.equalsIgnoreCase("Lista_libros")){



          line = entrada.readLine();
          System.out.println(line.replace("°","\n"));
        }

        else{
          line = entrada.readLine();
          System.out.println(line.replace("°","\n"));
        }


        }


    }catch(Exception e){
      System.out.println("Cliente:" +e.getMessage());

    }


  }
}
