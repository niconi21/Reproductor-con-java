/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recuperar_contraseña;

import BaseDeDatos.MYSQL;
import Objetos.ReproductorException;
import com.jfoenix.controls.JFXTextField;
import java.awt.HeadlessException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
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
public class RecuperarController implements Initializable {

    private MYSQL mysql;
    @FXML
    private JFXTextField usuario;

    @FXML
    private void recuperarCuenta() {
        try {
            mysql.recuperarContrasenia(usuario.getText());
            JOptionPane.showMessageDialog(null, "Correo Enviado");
            atras();
        } catch (ReproductorException | HeadlessException | ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(null, ex, "error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @FXML
    private void atras() {
        try {
            recuperar.cerrar();
            Login20 l = new Login20();
            l.start(new Stage());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex, "error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mysql = new MYSQL();
    }

}
