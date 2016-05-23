package com.frre.practica.isi.algoritmos.genericos;

import com.frre.library.Utils;

import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.*;

/**
 * Created by justo on 04/05/16.
 */
public class Main {

    public static Callable<Object> analizeSintaxAndExecute(final String[] args){
        return new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                if (args.length == 0) {
                    System.out.println("Debes especificar un archivo valido para analizar! Verifica que sea accesible desde donde lo estes invocando");
                } else {
                    int search = Utils.findWord(args, "-DebugMode");
                    boolean DEBUG_MODE = search != -1 ? true : false;
                    String[] newArgs = Utils.removeWord(args, "-DebugMode");
                    try {
                        String dirSecuenciaArchivo = null;
                        if (newArgs.length > 1) {
                            dirSecuenciaArchivo = newArgs[1];
                        }
                        Utils.analizeAndExec(newArgs[0], dirSecuenciaArchivo, DEBUG_MODE);
                    } catch (Exception e) {
                        System.out.println("Ha sucedido un error utilizando la libreria. Verifica que el algoritmo este correcto.");
                        System.out.println("Detalles del Error: " + e.getMessage());
                        System.out.println(Arrays.toString(e.getStackTrace()));
                    }
                }

                return "Exito!";
            }

        };
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        final ExecutorService service = Executors.newCachedThreadPool();
        try {
            final Future<Object> f = service.submit(analizeSintaxAndExecute(args));
            System.out.println(f.get(2, TimeUnit.SECONDS));
        } catch (final TimeoutException e) {
            System.err.println("Revisa tu algoritmo, ya que probablemente entro en un bucle infinito debido a que ya paso mas de 2 minutos sin respuesta");
        } catch (final Exception e) {
            throw new RuntimeException(e);
        } finally {
            System.err.println("Cerrando programa por error de Timeout");
            service.shutdownNow();
        }
    }

}


