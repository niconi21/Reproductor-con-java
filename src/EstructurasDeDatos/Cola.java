/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EstructurasDeDatos;

import Objetos.Cancion;
import java.util.ArrayList;

/**
 *
 * @author manuel
 */
public class Cola {
    ///NOTA "PISTA" HACE REFERENCIA A LA CANCION

    private int atras;
    private int frente;
    private final ArrayList<Cancion> cola;
    private Cancion canciones[];

    public Cola() {
        this.cola = new ArrayList<>();
        this.atras = 0;
        this.frente = 0;
    }

    public void agregar(Cancion pista) {
        this.cola.add(pista);
        this.atras++;
    }

    public Cancion quitar() {
        Cancion aux = this.cola.get(this.frente);
        this.frente++;
        return aux;
    }

    public boolean vacio() {
        return this.atras == this.frente;
    }

    public int tamanio() {
        return atras - frente;
    }

    public Cancion buscarCancion(String buscar) {
        Cancion canciones[] = convertir();
        Cancion cancion = new Cancion();
        return buscarCancion(buscar, canciones, cancion, 0);
    }

    private Cancion buscarCancion(String buscar, Cancion[] canciones, Cancion cancion, int i) {
        if (i < canciones.length) {
            Cancion aux = canciones[i++];
            if (buscar.equals(aux.getTitulo())) {
                cancion = aux;
            } else {
                cancion = buscarCancion(buscar, canciones, new Cancion(), i);
            }
            return cancion;
        }
        return null;
    }

    public Cancion[] ordenarTitulo() {
        return ordenarTitulo(convertir(), 0, cola.size() - 1);
    }

    private Cancion[] ordenarTitulo(Cancion[] canciones, int primero, int ultimo) {
        int i = primero, j = ultimo;

        int central = (primero + ultimo) / 2;
        Cancion pivote = canciones[central];
        do {
            while (canciones[i].getTitulo().charAt(0) < pivote.getTitulo().charAt(0)) {
                i++;
            }
            while (canciones[j].getTitulo().charAt(0) > pivote.getTitulo().charAt(0)) {
                j--;
            }
            if (i <= j) {
                Cancion cancion = canciones[i];
                canciones[i] = canciones[j];
                canciones[j] = cancion;
                i++;
                j--;
            }
        } while (i <= j);
        if (primero < j) {
            ordenarTitulo(canciones, primero, j);
        }
        if (i < ultimo) {
            ordenarTitulo(canciones, i, ultimo);
        }
        return canciones;
    }

    public Cancion[] ordenarArtista() {
        return ordenarArtista(convertir(), 0, cola.size() - 1);
    }

    private Cancion[] ordenarArtista(Cancion[] canciones, int primero, int ultimo) {
        int i = primero, j = ultimo;

        int central = (primero + ultimo) / 2;
        Cancion pivote = canciones[central];
        do {
            while (canciones[i].getAutor().charAt(0) < pivote.getAutor().charAt(0)) {
                i++;
            }
            while (canciones[j].getAutor().charAt(0) > pivote.getAutor().charAt(0)) {
                j--;
            }
            if (i <= j) {
                Cancion cancion = canciones[i];
                canciones[i] = canciones[j];
                canciones[j] = cancion;
                i++;
                j--;
            }
        } while (i <= j);
        if (primero < j) {
            ordenarArtista(canciones, primero, j);
        }
        if (i < ultimo) {
            ordenarArtista(canciones, i, ultimo);
        }
        return canciones;
    }

    public Cancion[] ordenarGenero() {
        return ordenarGenero(convertir(), 0, cola.size() - 1);
    }

    private Cancion[] ordenarGenero(Cancion[] canciones, int primero, int ultimo) {
        int i = primero, j = ultimo;

        int central = (primero + ultimo) / 2;
        Cancion pivote = canciones[central];
        do {
            while (canciones[i].getGenero().charAt(0) < pivote.getGenero().charAt(0)) {
                i++;
            }
            while (canciones[j].getGenero().charAt(0) > pivote.getGenero().charAt(0)) {
                j--;
            }
            if (i <= j) {
                Cancion cancion = canciones[i];
                canciones[i] = canciones[j];
                canciones[j] = cancion;
                i++;
                j--;
            }
        } while (i <= j);
        if (primero < j) {
            ordenarGenero(canciones, primero, j);
        }
        if (i < ultimo) {
            ordenarGenero(canciones, i, ultimo);
        }
        return canciones;
    }

    private Cancion[] convertir() {
        this.canciones = new Cancion[cola.size()];
        int i = 0;
        for (Cancion cancion : cola) {
            canciones[i] = cancion;
            i++;
        }
        return canciones;
    }

}
