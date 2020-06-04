/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package registro.pkg0;

import BaseDeDatos.MYSQL;
import Objetos.ReproductorException;
import Objetos.Usuario;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import login2.pkg0.Login20;

/**
 * FXML Controller class
 *
 * @author martin
 */
public class RegistroController implements Initializable {

    private MYSQL mysql;

    @FXML
    private JFXTextField nombre;
    @FXML
    private JFXTextField apellidos;
    @FXML
    private JFXTextField usuario;
    @FXML
    private JFXPasswordField contrasenia;
    @FXML
    private JFXTextField correo;

    @FXML
    private void registro() {
        try {
            boolean crearUsuario = mysql.crearUsuario(new Usuario(nombre.getText(), apellidos.getText(), correo.getText(), usuario.getText(), contrasenia.getText()));
            JOptionPane.showMessageDialog(null, "Usuario Creado");
            salir();
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex, "error", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex, "error", JOptionPane.ERROR_MESSAGE);
        } catch (ReproductorException ex) {
            Logger.getLogger(RegistroController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    @FXML
    private void salir(){
        try {
            registro.cerrar();
            Login20 l=new Login20();
            l.start(new Stage());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex,"error",JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mysql = new MYSQL();
    }

}
