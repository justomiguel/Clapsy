package com.frre.practica.isi.algoritmos.archivos.crear;

import com.frre.library.Generador;
import com.frre.practica.isi.algoritmos.archivos.Cliente;

import static com.frre.library.archivos.FuncionesDeArchivos.*;

import java.io.File;

/**
 * Created by jvargas on 8/20/15.
 */
public class Main {


    //ambiente
    //archivo
    private static File archivo;
    //registro
    private static Cliente cliente;

    //algoritmo
    public static void main(String[] args){
        //muestra de funciones con archivos
        //aqui creamos un archivo
        archivo = abrir("clientes", true);
        //agregamos 500 registros;
        for (int i = 0; i < 500; i++) {
            cliente = new Cliente();
            cliente.setNroCliente(i);
            cliente.setDni(Generador.generarDNIAleatorio());
            cliente.setDomicilio(Generador.generarLocalidadAleatorio());
            cliente.setNombreYApellido(Generador.generarNombreAleatorio()+","+Generador.generarApellidoAleatorio());
            cliente.setTelefono(Generador.generarEnteroAleatorio(23444324,314234342)+"");
            grabar(archivo, cliente);
        }

        cerrar(archivo);
    }
}
