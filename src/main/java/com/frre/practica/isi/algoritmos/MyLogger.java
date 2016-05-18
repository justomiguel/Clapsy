package com.frre.practica.isi.algoritmos;

import com.frre.library.Utils;
import com.frre.practica.isi.algoritmos.genericos.Main;

/**
 * Created by justo on 13/05/16.
 */
public class MyLogger {

    public static void log(String info){
        if(Utils.DEBUG_MODE){
            System.err.println(info);
        }
    }
}
