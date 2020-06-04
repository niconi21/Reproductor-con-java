/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;

import Objetos.Usuario;
import java.beans.EventHandler;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author martin
 */
public class Principal extends Application {

    private static Stage stage;
    private static Usuario usuario;

    @Override
    public void start(Stage stage) throws Exception {
        Principal.stage = stage;
        Parent root = FXMLLoader.load(getClass().getResource("Biblioteca.fxml"));

        Scene scene = new Scene(root);

        Principal.stage.setScene(scene);
        Principal.stage.setResizable(false);
        stage.setOnCloseRequest((event) -> {
            try {
                BibliotecaController.respaldarListaReproduccion();
            } catch (IOException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        Principal.stage.show();

    }

    public void iniciar(Stage stage, Usuario usuario) throws Exception {
        BibliotecaController.usuario(usuario);
        start(stage);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    public static void cerrar() {
        Principal.stage.close();
    }

}
