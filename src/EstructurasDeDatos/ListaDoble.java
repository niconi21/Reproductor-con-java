/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EstructurasDeDatos;

import Objetos.Cancion;

/**
 *
 * @author manuel
 */
public class ListaDoble {

    private Nodo raiz;
    private int index = 0;
    private Nodo actual;

    public void insertarInicio(Cancion valor) {
        if (raiz == null) {
            raiz = new Nodo();
            raiz.valor = valor;
            actual = raiz;
            index++;
        } else {
            Nodo aux = new Nodo(valor);
            aux.derecha = raiz;
            raiz.izquierda = aux;
            raiz = aux;
            actual = raiz;
            index++;
        }
    }

    public void insertarFin(Cancion valor) {
        if (raiz == null) {
            raiz = new Nodo();
            raiz.valor = valor;
            actual = raiz;
            index++;
        } else {
            Nodo nodo = new Nodo(valor);
            Nodo aux = raiz;
            while (aux.derecha != null) {
                aux = aux.derecha;
            }
            aux.derecha = nodo;
            nodo.izquierda = aux;
            actual = raiz;
            index++;

        }
    }

    public Cancion getActual() {
        return actual.valor;
    }

    public Nodo getRaiz() {
        return raiz;
    }

    public void moverIzquierda() {
        if (actual.izquierda != null) {
            actual = actual.izquierda;
        }
    }

    public void moverDerecha() {
        if (actual.derecha != null) {
            actual = actual.derecha;
        }
    }

    public void regresarInicio(){
        actual=raiz;
    }
    
    public int tamanio() {
        return index;
    }

    public void buscarActual(Cancion buscar, ListaDoble lista) {
        Cancion cancion=lista.getActual();
        if (!buscar.equals(cancion)) {
            lista.moverDerecha();
            buscarActual(cancion, lista);
        }
        
    }

}
