/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login2.pkg0;

import Herramientas.Escritura_Lectura;
import Principal.BibliotecaController;
import Principal.Principal;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author martin
 */
public class Login20 extends Application {

    private static Stage stage;

    @Override
    public void start(Stage stage) throws Exception {
        Login20.stage = stage;
        Escritura_Lectura lectura = new Escritura_Lectura();
        if (lectura.recueprarUsuario() != null) {
            Principal p=new Principal();
            p.start(new Stage());
            cerrar();
        } else {
            Parent root = FXMLLoader.load(getClass().getResource("LoginFXML.fxml"));
            Scene scene = new Scene(root);
            Login20.stage.setScene(scene);
            Login20.stage.setResizable(false);
            Login20.stage.show();
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    public static void cerrar() {
        Login20.stage.close();
    }

}
