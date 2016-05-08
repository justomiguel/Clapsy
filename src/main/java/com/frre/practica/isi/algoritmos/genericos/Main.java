package com.frre.practica.isi.algoritmos.genericos;

import com.frre.library.Utils;
import java.io.IOException;
import java.util.Arrays;

/**
 * Created by justo on 04/05/16.
 */
public class Main {

    public static void main(String[] args) throws IOException {
        if (args.length == 0){
            System.out.println("Debes especificar un archivo valido para analizar! Verifica que sea accesible desde donde lo estes invocando");
        } else {
            try{
                Utils.analizeAndExec(args[0]);
            } catch (Exception e){
                System.out.println("Ha sucedido un error utilizando la libreria. Verifica que el algoritmo este correcto.");
                System.out.println("Detalles del Error: "+e.getMessage());
                System.out.println(Arrays.toString(e.getStackTrace()));
            }
        }
    }

}


