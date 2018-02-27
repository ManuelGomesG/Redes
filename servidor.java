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

    byte[] byteArray;

    int in;

    String menu = " 1) ESTADO_DESCARGAS \n 2) LISTA_LIBROS \n 3) SOLICITUD <libro> \n 4) LIBROS_DESCARGADOSxSERVIDOR  \n";

    try {

      servidor = new ServerSocket(PUERTO);
      System.out.println("Esperando conexion");
      while(true){
        conexion = servidor.accept();
        System.out.println("Conexion establecida");
        
        while(true){

          respuesta   = new PrintStream(conexion.getOutputStream());
          buffentrada = new InputStreamReader(conexion.getInputStream());
          entrada     = new BufferedReader(buffentrada);

          
          if ((recibido = entrada.readLine()) != null) {
            System.out.println(recibido);
            System.out.println(recibido);
            if (recibido.equals("Solicitud libro")){

              try{
                ServerSocket servidor2 = new ServerSocket(1234);
                //while(true){
                  System.out.println("Esperando conexion en el nuevo socket\n");
                  Socket conexion2 = servidor2.accept();
                  System.out.println("Conectado en el nuevo socket\n");
                  final File archivoLocal = new File("imagen_share.png");
                  BufferedInputStream entradaArchivo = new BufferedInputStream(new FileInputStream(archivoLocal));
                  OutputStream pt;
                  byteArray = new byte[8192];
                  pt = conexion2.getOutputStream();
                  while ((in = entradaArchivo.read(byteArray))!=-1){
                    System.out.printf("Iniciando transferencia de archivo");
                    respuesta.printf("Iniciando transferencia de archivo");
                    pt.write(byteArray,0,in);    
                  }
                  System.out.println("Termino transferencia");
                  pt.close();
                //}
              }catch(Exception e){
                  System.out.println("Hubo un error al conectarse al segundo socket "+e.getMessage());
              }
            }
                 
          }

          
          respuesta.printf("Se ha establecido correctamente la conexion con el servidor\n");
          respuesta.printf(menu);
          
        
        }
      }

    }catch(Exception e)
    {
      System.out.println("cancer servidor" + e.getMessage());
    }


  }



 
}
