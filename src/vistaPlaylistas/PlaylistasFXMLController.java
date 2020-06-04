/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistaPlaylistas;

import Herramientas.Escritura_Lectura;
import Objetos.Cancion;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author martin
 */
public class PlaylistasFXMLController implements Initializable {

    private Escritura_Lectura escritura = new Escritura_Lectura();
    private static Cancion cancion;

    @FXML
    private JFXTextField playlist;
    @FXML
    private ListView<String> listViewPlaylist;
    @FXML
    private ImageView imageViewLupa;
    @FXML
    private JFXButton buttonAniadir;
    @FXML
    private JFXButton buttonAceptar;
    @FXML
    private Label label;

    @FXML
    private void aniadir() {
        try {
            if (!"".equals(playlist.getText())) {
                escritura.escribirPlaylist(playlist.getText());

            } else {
                JOptionPane.showMessageDialog(null, "Ingrese un nombre valido", "Error", JOptionPane.ERROR_MESSAGE);
            }
            llenarlista();
        } catch (IOException ex) {
            Logger.getLogger(PlaylistasFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void llenarlista() {
        try {
            ArrayList<String> listas = escritura.recuperarPlayList();
            if (listas != null) {
                ObservableList p = FXCollections.observableArrayList();
                for (String string : listas) {
                    p.add(string);
                }
                listViewPlaylist.setItems(p);
            }
        } catch (IOException ex) {
            Logger.getLogger(PlaylistasFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void agregarCancion() {
        try {
            String playlist = listViewPlaylist.getSelectionModel().getSelectedItem();
            escritura.escribirCancionPlayLIst(playlist, cancion);
            PlayList.cerrar();
        } catch (IOException ex) {
            Logger.getLogger(PlaylistasFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void cancion(Cancion cancion) {
        PlaylistasFXMLController.cancion = cancion;
    }

    public void mostrarElementos() {
        if (cancion != null) {
            buttonAceptar.setVisible(true);
            label.setVisible(true);
            imageViewLupa.setVisible(false);
            buttonAniadir.setVisible(false);
            playlist.setVisible(false);
        } else {
            imageViewLupa.setVisible(true);
            buttonAniadir.setVisible(true);
            playlist.setVisible(true);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        llenarlista();
        mostrarElementos();
    }

}
