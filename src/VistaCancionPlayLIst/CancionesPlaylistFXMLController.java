/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VistaCancionPlayLIst;

import EstructurasDeDatos.Cola;
import Herramientas.Escritura_Lectura;
import Objetos.Cancion;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

/**
 * FXML Controller class
 *
 * @author nicolas
 */
public class CancionesPlaylistFXMLController implements Initializable {
    
    private static String playlist;
    private Escritura_Lectura lectura=new Escritura_Lectura();
    
    @FXML
    private ListView listViewPlaylist;
    @FXML
    private Label label;
    
    public static void playList(String playlist) {
        CancionesPlaylistFXMLController.playlist = playlist;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (playlist != null) {
            try {
                Cola cola = lectura.recuperarCancionPlayLIst(playlist);
                ObservableList canciones = FXCollections.observableArrayList();
                int tamanio = cola.tamanio();
                for (int i = 0; i < tamanio; i++) {
                    Cancion c=cola.quitar();
                    System.out.println(c);
                    canciones.add(c);
                }
                listViewPlaylist.setItems(canciones);
            } catch (IOException ex) {
                Logger.getLogger(CancionesPlaylistFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }    
    
}
