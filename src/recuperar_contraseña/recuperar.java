/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recuperar_contraseña;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author martin
 */
public class recuperar extends Application {
    private static Stage stage;
    
 @Override
    public void start(Stage stage) throws Exception {
        recuperar.stage=stage;
        Parent root = FXMLLoader.load(getClass().getResource("recuperar.fxml"));
        
        Scene scene = new Scene(root);
        
        recuperar.stage.setScene(scene);
        recuperar.stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    public static void cerrar(){
        stage.close();
    }
}
