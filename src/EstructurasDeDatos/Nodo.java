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
public class Nodo {
    Cancion valor;
    Nodo izquierda=null;
    Nodo derecha=null;

    public Nodo(Cancion valor) {
        this.valor=valor;
        izquierda=derecha=null;
    }

    public Nodo() {
        izquierda=derecha=null;
        
    }
    
    
    @Override
    public String toString() {
        return "Izquierda="+(izquierda!=null?izquierda.valor:null)+" Valor= "+valor+" Derecha="+(derecha!=null?derecha.valor:null);
    }
    
}
