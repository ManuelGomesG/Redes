import java.net.*;
import java.io.*;
import java.lang.*;
import java.util.concurrent.*;




public class ClientWorker implements Runnable {
    private final Socket client;

    public ClientWorker( Socket client ) {
        this.client = client;
    }

    @Override
    public void run() {
        String line;
        BufferedReader in = null;
        PrintWriter out = null;
        PrintStream respuesta;
        String menu = " 1) ESTADO_DESCARGAS ° 2) LISTA_LIBROS ° 3) SOLICITUD <libro> ° 4) LIBROS_DESCARGADOSxSERVIDOR°";


        while (true) {
            try {
              System.out.println("Creado el hilo:"+Thread.currentThread().getName());
              in = new BufferedReader(new InputStreamReader(client.getInputStream()));
              out = new PrintWriter(client.getOutputStream());
              respuesta   = new PrintStream(client.getOutputStream());
              respuesta.println(menu);

                System.out.println("Recibiendo del hilo:"+Thread.currentThread().getName());
                line = in.readLine();

                if (line   != null) {
                  if ((line = in.readLine()) != null) {
                    System.out.println(line);
                    String[] palabras = line.split(" ");
                    if (palabras[0].equalsIgnoreCase("Solicitud")){

                      try{
                        ServerSocket servidor2 = new ServerSocket(5020);
                        //while(true){
                          System.out.println("Esperando conexion en el nuevo socket");
                          Socket conexion2 = servidor2.accept();
                          System.out.println("Conectado en el nuevo socket");
                          String nombreArchivo = "";
                          nombreArchivo = line.substring(10);
                          final File archivoLocal = new File("./libros/" + nombreArchivo );
                          BufferedInputStream inArchivo = new BufferedInputStream(new FileInputStream(archivoLocal));
                          OutputStream pt;
                          byte[] byteArray = new byte[8192];
                          pt = conexion2.getOutputStream();
                          System.out.println("Iniciando transferencia de archivo");
                          respuesta.println("Iniciando transferencia de archivo");
                          int len;
                          while ((len = inArchivo.read(byteArray))!=-1){
                            pt.write(byteArray,0,len);
                          }
                          System.out.println("Termino transferencia");
                          pt.close();
                        //}
                      }catch(Exception e){
                          System.out.println("Hubo un error al conectarse al segundo socket "+e.getMessage());
                      }
                    }
                    else if (palabras[0].equalsIgnoreCase("Lista_libros")){
                      File archivo = new File ("listaLibros");
                      FileReader fr = new FileReader (archivo);
                      BufferedReader br = new BufferedReader(fr);
                      String linea;
                      String listaString = "";
                      while((linea=br.readLine())!=null){
                        listaString += "\n";
                        listaString += linea;
                      }
                      br.close();
                      respuesta.println(listaString.replace("\n","°")+"°"+"°"+menu);


                      /*
                      byte[] byteArray = new byte[1024];
                      final File archivoListaLibros = new File("listaLibros");
                      BufferedInputStream inArchivo = new BufferedInputStream(new FileInputStream(archivoListaLibros));
                      OutputStream pt = client.getOutputStream();
                      int len;
                      while((len = inArchivo.read(byteArray)) != -1) {
                        pt.write(byteArray,0,len);
                      }
                      */




                    }
                  }


                  respuesta.println(menu);

                        }
            } catch (IOException e) {
                System.out.println("Read failed");
                System.exit(-1);
            }
        }
    }
}
