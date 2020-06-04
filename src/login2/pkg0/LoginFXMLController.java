/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login2.pkg0;

import BaseDeDatos.MYSQL;
import Herramientas.Escritura_Lectura;
import Objetos.ReproductorException;
import Objetos.Usuario;
import Principal.BibliotecaController;
import Principal.Principal;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import recuperar_contraseña.recuperar;
import registro.pkg0.registro;

/**
 *
 * @author martin
 */
public class LoginFXMLController implements Initializable {

    private MYSQL mysql;

    @FXML
    private JFXTextField usuario;
    @FXML
    private JFXPasswordField contrasenia;
    @FXML
    private JFXCheckBox checkBox;

    @FXML
    private void login() {
        try {
            Usuario login = mysql.login(usuario.getText(), contrasenia.getText());
            if (checkBox.isSelected()) {
                Escritura_Lectura escritura = new Escritura_Lectura();
                escritura.escribirUsuario(login);
            } else {
                
            }
            Principal p = new Principal();
            p.iniciar(new Stage(), login);
            Login20.cerrar();
        } catch (ReproductorException ex) {
            JOptionPane.showMessageDialog(null, ex, "Error", JOptionPane.ERROR_MESSAGE);
        } catch (ClassNotFoundException | SQLException ex) {
        } catch (Exception ex) {
        }
    }

    @FXML
    private void recuperarContrasenia() {
        try {
            Login20.cerrar();
            recuperar r = new recuperar();
            r.start(new Stage());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @FXML
    private void crearCuenta() {
        try {
            Login20.cerrar();
            registro r = new registro();
            r.start(new Stage());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mysql = new MYSQL();
    }

}
