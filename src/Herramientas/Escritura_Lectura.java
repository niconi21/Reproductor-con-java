/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Herramientas;

import EstructurasDeDatos.Cola;
import EstructurasDeDatos.ListaDoble;
import Objetos.Usuario;
import Objetos.Cancion;
import Objetos.ReproductorException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 *
 * @author manuel
 */
public class Escritura_Lectura {

    private FileReader fileReader;
    private BufferedReader bufferedReader;
    private FileWriter fileWriter;
    private PrintWriter printWriter;
    private final String ruta = (new File("")).getAbsolutePath();

    private void conexion(String ruta, boolean reescrbir) throws IOException {
        fileWriter = new FileWriter(ruta, reescrbir);
        printWriter = new PrintWriter(fileWriter);
        fileReader = new FileReader(ruta);
        bufferedReader = new BufferedReader(fileReader);
    }

    private void desconectar() throws IOException {
        fileWriter.close();
        printWriter.close();
        fileReader.close();
        bufferedReader.close();
    }

    //Usuario - Escritura y lectura de archivo 
    // <editor-fold defaultstate="collapsed" desc="Generated Code">  
    public void escribirUsuario(Usuario usuario) throws IOException {
        conexion(ruta + "/Usuario.txt", false);
        printWriter.print(usuario.toString());
        desconectar();
    }

    public void vaciarUsuario() throws IOException {
        conexion(ruta + "/Usuario.txt", false);
        desconectar();
    }

    public Usuario recueprarUsuario() throws IOException, ReproductorException {
        conexion(ruta + "/Usuario.txt", true);
        String linea;
        if ((linea = bufferedReader.readLine()) != null) {
            String datos[] = linea.split(",");
            String nombre = datos[0];
            String apellidos = datos[1];
            String correo = datos[2];
            String usuario = datos[3];
            Desencriptar contrasenia = new Desencriptar(datos[4]);
            Usuario datosUsuario = new Usuario(nombre, apellidos, correo, usuario, contrasenia.toString());
            desconectar();
            return datosUsuario;
        } else {
            desconectar();
            return null;
        }

    }
    // </editor-fold>

    //Cancion - Escritura y lectura de archivo
    // <editor-fold defaultstate="collapsed" desc="Generated Code">  
    public void escribirCancion(Cancion canciones) throws IOException {
        conexion(ruta + "/Canciones.txt", true);
        printWriter.println(canciones.toString());
        desconectar();
    }

    public Cola recuperarCanciones() throws IOException {
        Cola canciones = new Cola();
        conexion(ruta + "/Canciones.txt", true);
        if (bufferedReader.ready()) {
            String linea;
            String titulo, autor, genero, duracion;
            while ((linea = bufferedReader.readLine()) != null) {
                titulo = linea.split(",")[0];
                autor = linea.split(",")[1];
                genero = linea.split(",")[2];
                duracion = linea.split(",")[3];
                canciones.agregar(new Cancion(titulo, autor, genero, duracion));
            }
            desconectar();

            return canciones;
        } else {
            return null;
        }
    }

    public void vaciarCanciones() throws IOException {
        conexion(ruta + "/Canciones.txt", false);
        desconectar();
    }
    // </editor-fold>

    //Favoritos - Escritura y lectura de archivo
    // <editor-fold defaultstate="collapsed" desc="Generated Code">  
    public void escribirFavoritos(Cancion canciones) throws IOException {
        conexion(ruta + "/Favoritos.txt", true);
        printWriter.println(canciones.toString());
        desconectar();
    }

    public Cola recuperarFavoritos() throws IOException {
        Cola canciones = new Cola();
        conexion(ruta + "/Favoritos.txt", true);
        if (bufferedReader.ready()) {
            String linea;
            String titulo, autor, genero, duracion;
            while ((linea = bufferedReader.readLine()) != null) {
                titulo = linea.split(",")[0];
                autor = linea.split(",")[1];
                genero = linea.split(",")[2];
                duracion = linea.split(",")[3];
                canciones.agregar(new Cancion(titulo, autor, genero, duracion));
            }
            desconectar();
            return canciones;
        } else {
            return null;
        }
    }

    public void vaciarFavoritos() throws IOException {
        conexion(ruta + "/Favoritos.txt", false);
        desconectar();
    }
    // </editor-fold>

    //PlayList - Escritura y lectura de archivo
    // <editor-fold defaultstate="collapsed" desc="Generated Code">  
    public void escribirPlaylist(String playlist) throws IOException {
        conexion(ruta + "/Playlists.txt", true);
        printWriter.println(playlist);
        desconectar();
    }

    public void eliminarPlaylist(String eliminar) throws IOException {
        ArrayList<String> listas = recuperarPlayList();
        conexion(ruta + "/Playlists.txt", false);
        for (String lista : listas) {
            if (!lista.equals(eliminar)) {
                printWriter.println(lista);
            }
        }
        desconectar();
    }

    public ArrayList<String> recuperarPlayList() throws IOException {
        ArrayList<String> playlist = new ArrayList<>();
        conexion(ruta + "/Playlists.txt", true);
        if (bufferedReader.ready()) {
            String linea;
            while ((linea = bufferedReader.readLine()) != null) {
                playlist.add(linea);
            }
            desconectar();
            return playlist;
        } else {
            return null;
        }
    }

    public void vaciarPlayList() throws IOException {
        conexion(ruta + "/Playlists.txt", false);
        desconectar();
    }

    public void escribirCancionPlayLIst(String playlist, Cancion cancion) throws IOException {
        conexion(ruta + "/" + playlist + ".txt", true);
        printWriter.println(cancion.toString());
        desconectar();
    }

    public Cola recuperarCancionPlayLIst(String playlist) throws IOException {
        Cola canciones = new Cola();
        System.out.println(playlist);
        conexion(ruta + "/" + playlist + ".txt", true);
        if (bufferedReader.ready()) {
            String linea;
            String titulo, artista, genero, duracion;
            while ((linea = bufferedReader.readLine()) != null) {
                titulo = linea.split(",")[0];
                artista = linea.split(",")[1];
                genero = linea.split(",")[2];
                duracion = linea.split(",")[3];
                canciones.agregar(new Cancion(titulo, artista, genero, duracion));
            }
            desconectar();
            return canciones;
        } else {
            return null;
        }
    }

    public void vaciarCancionPlayLIst() throws IOException {
        conexion(ruta + "/Playlists.txt", false);
        desconectar();
    }

// </editor-fold>
    //TopTec - lectura de archivo
    // <editor-fold defaultstate="collapsed" desc="Generated Code">  
    public Cola recuperarTopTec() throws IOException {
        Cola canciones = new Cola();
        conexion(ruta + "/TopTec.txt", true);
        if (bufferedReader.ready()) {
            String linea;
            String titulo, autor, genero, duracion;
            while ((linea = bufferedReader.readLine()) != null) {
                titulo = linea.split(",")[0];
                autor = linea.split(",")[1];
                genero = linea.split(",")[2];
                duracion = linea.split(",")[3];
                canciones.agregar(new Cancion(titulo, autor, genero, duracion));
            }
            desconectar();
            return canciones;
        } else {
            return null;
        }
    }
    // </editor-fold>

    //Lista de reproduccion actual - lectura y escritura de archivo
    // <editor-fold defaultstate="collapsed" desc="Generated Code">  
    public void escribirListaReproduccion(ListaDoble lista) throws IOException{
        conexion(ruta + "/ListaReproduccion.txt", false);
        int tamanio=lista.tamanio();
        for (int i = 0; i < tamanio; i++) {
            printWriter.println(lista.getActual().toString());
            lista.moverDerecha();
        }
        desconectar();
    
    }
    public ListaDoble recuperarListaReproduccion() throws IOException{
        ListaDoble canciones = null;
        conexion(ruta + "/ListaReproduccion.txt", true);
        if (bufferedReader.ready()) {
            canciones=new ListaDoble();
            String linea;
            String titulo, autor, genero, duracion;
            while ((linea = bufferedReader.readLine()) != null) {
                titulo = linea.split(",")[0];
                autor = linea.split(",")[1];
                genero = linea.split(",")[2];
                duracion = linea.split(",")[3];
                canciones.insertarFin(new Cancion(titulo, autor, genero, duracion));
            }
        }
        return canciones;
    }
    public void vaciarListaReproduccion(){
        
    }
    
    // </editor-fold>

}
