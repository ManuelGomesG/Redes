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
        try {
            System.out.println("Creado el hilo:"+Thread.currentThread().getName());
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            out = new PrintWriter(client.getOutputStream());
            respuesta   = new PrintStream(client.getOutputStream());
            respuesta.println(menu);
        } catch (IOException e) {
            System.out.println("in or out failed");
            System.exit(-1);
        }

        while (true) {
            try {
                System.out.println("Recibiendo del hilo:"+Thread.currentThread().getName());
                line = in.readLine();

                if (line   != null) {
                  respuesta   = new PrintStream(client.getOutputStream());
                  respuesta.println(menu);
                        //fSystem.out.println("Read failed");

                        }
            } catch (IOException e) {
                System.out.println("Read failed");
                System.exit(-1);
            }
        }
    }
}
