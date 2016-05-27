package com.frre.practica.isi.algoritmos.archivos;

import com.frre.library.Clave;
import com.frre.library.Fecha;

/**
 * Created by jvargas on 8/28/15.
 */
public class Cliente {


    @Clave
    int nroCliente;
    String nombreYApellido;
    String domicilio;
    String telefono;
    int dni;

    public int getNroCliente() {
        return nroCliente;
    }

    public void setNroCliente(int nroCliente) {
        this.nroCliente = nroCliente;
    }

    public String getNombreYApellido() {
        return nombreYApellido;
    }

    public void setNombreYApellido(String nombreYApellido) {
        this.nombreYApellido = nombreYApellido;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getDni() {
        return dni;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "nroCliente=" + nroCliente +
                ", nombreYApellido='" + nombreYApellido + '\'' +
                ", domicilio='" + domicilio + '\'' +
                ", telefono='" + telefono + '\'' +
                ", dni=" + dni +
                '}';
    }

    public void setDni(int dni) {
        this.dni = dni;
    }
}


