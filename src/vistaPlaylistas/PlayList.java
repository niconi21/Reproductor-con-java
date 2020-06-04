/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistaPlaylistas;

import Objetos.Cancion;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author nicolas
 */
public class PlayList extends Application {
    
    private static Stage stage;
    
    @Override
    public void start(Stage stage) throws Exception {
        PlayList.stage=stage;
        Parent root = FXMLLoader.load(getClass().getResource("PlaylistasFXML.fxml"));
        
        Scene scene = new Scene(root);
        
        PlayList.stage.setScene(scene);
        PlayList.stage.setResizable(false);
        PlayList.stage.show();
    }
    
    public void iniciar(Cancion cancion){
        try {
            PlaylistasFXMLController.cancion(cancion);
            start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(PlayList.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void cerrar(){
        stage.close();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
