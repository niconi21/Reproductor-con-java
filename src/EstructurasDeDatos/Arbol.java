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
public class Arbol {

    private Nodo raiz;
    private int index;

    public void insertar(Cancion valor) {
        if (raiz == null) {
            raiz = new Nodo();
            raiz.valor = valor;
            index++;
        } else {
            Nodo nodo = new Nodo(valor);
            insertar(raiz, nodo);
        }
    }

    private void insertar(Nodo raiz, Nodo nodo) {
        if (nodo.valor.getTitulo().charAt(0) < raiz.valor.getTitulo().charAt(0)) {
            if (raiz.izquierda == null) {
                raiz.izquierda = nodo;
            } else {
                insertar(raiz.izquierda, nodo);
            }
            index++;
        } else {
            if (raiz.derecha == null) {
                raiz.derecha = nodo;
            } else {
                insertar(raiz.derecha, nodo);
            }
            index++;
        }
    }

    public int tamanio(){
        return index;
    }
    
    public ListaDoble inOrden() {
        return inOrden(raiz, new ListaDoble());
    }

    private ListaDoble inOrden(Nodo raiz, ListaDoble lista) {
        if (raiz != null) {
            lista.insertarFin(raiz.valor);
            inOrden(raiz.izquierda, lista);
            inOrden(raiz.derecha, lista);
        }
        return lista;
    }

    public ListaDoble posOrden() {
        return posOrden(raiz, new ListaDoble());
    }

    private ListaDoble posOrden(Nodo raiz, ListaDoble lista) {
        if (raiz != null) {
            posOrden(raiz.izquierda, lista);
            posOrden(raiz.derecha, lista);
            lista.insertarFin(raiz.valor);
        }
        return lista;
    }

    public ListaDoble preOrden() {
        return preOrden(raiz, new ListaDoble());
    }

    private ListaDoble preOrden(Nodo raiz, ListaDoble lista) {
        if (raiz != null) {
            preOrden(raiz.izquierda, lista);
            lista.insertarFin(raiz.valor);
            preOrden(raiz.derecha, lista);
        }
        return lista;
    }
    

}
