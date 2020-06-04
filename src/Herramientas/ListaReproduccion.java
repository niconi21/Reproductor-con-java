/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Herramientas;

import EstructurasDeDatos.Arbol;
import EstructurasDeDatos.ListaDoble;
import Objetos.Cancion;

/**
 *
 * @author nicolas
 */
public class ListaReproduccion {

    private Arbol arbol;

    public ListaReproduccion(Arbol arbol) {
        this.arbol = arbol;
    }

    public ListaReproduccion() {
    }

    public void setArbol(Arbol arbol) {
        this.arbol = arbol;
    }

    public ListaDoble ordenAlfabetico() {
        return arbol.preOrden();
    }

    public ListaDoble ordenIngresado() {
        return arbol.inOrden();
    }

    public ListaDoble ordenAleatorio() {
        return arbol.posOrden();
    }
    
    public void buscar(Cancion cancion, ListaDoble listaReproduccion) {
        if (!listaReproduccion.getActual().equals(cancion)) {
            listaReproduccion.moverDerecha();
            buscar(cancion, listaReproduccion);
        }
    }
    
    

}
