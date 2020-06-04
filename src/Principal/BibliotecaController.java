/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;

import BaseDeDatos.MYSQL;
import EstructurasDeDatos.Arbol;
import EstructurasDeDatos.Cola;
import EstructurasDeDatos.ListaDoble;
import Herramientas.Escritura_Lectura;
import Herramientas.FTP;
import Herramientas.ListaReproduccion;
import Objetos.Cancion;
import Objetos.Reproductor;
import Objetos.ReproductorException;
import Objetos.Usuario;
import VistaCancionPlayLIst.CancionesPlayLIst;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
import javafx.scene.control.Tab;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import login2.pkg0.Login20;
import vistaPlaylistas.PlayList;

/**
 * FXML Controller class
 *
 * @author martin
 */
public class BibliotecaController implements Initializable {

    private final MYSQL mysql = new MYSQL();
    private static final Escritura_Lectura escritura_Lectura = new Escritura_Lectura();
    private final FTP ftp = new FTP();
    private static final Reproductor reproductor = new Reproductor();
    private static Usuario usuario;
    private static ListaDoble listaReproduccion;
    private Cancion cancionActual;
    private Cola colaBuscar = null;
    private int estadoListaReproduccion = 0;
    private int estadoAleatorio = 0;

//Elementos de JavaFX
// <editor-fold defaultstate="collapsed" desc="Generated Code">  
    @FXML
    private Label labelUsuario;
    @FXML
    private Label labelCorreo;
    @FXML
    private Label labelnombre;
    @FXML
    private Label labelInformacion;
    @FXML
    private Tab tabDescarga;
    @FXML
    private Tab tabMiMusica;
    @FXML
    private Tab tabFavoritos;
    @FXML
    private Tab tabTopTec;
    @FXML
    private Tab tabInformacion;
    @FXML
    private Tab tabPlaylist;
    @FXML
    private JFXButton buttonDescargar;
    @FXML
    private JFXButton buttonBuscar;
    @FXML
    private JFXTextField textFieldNombreCancion;
    @FXML
    private ImageView imageViewLupa;
    @FXML
    private ListView<Cancion> listViewDescargas;
    @FXML
    private ListView<Cancion> listViewTopTec;
    @FXML
    private ListView<Cancion> listViewMiMusica;
    @FXML
    private ListView<Cancion> listViewFavoritos;
    @FXML
    private ListView<String> listViewPlayList;
    @FXML
    private JFXButton buttonReproductir;
    @FXML
    private JFXButton buttonPausa;
    @FXML
    private JFXButton buttonSiguiente;
    @FXML
    private JFXButton buttonAtras;
    @FXML
    private JFXButton buttonTitulo;
    @FXML
    private JFXButton buttonArtista;
    @FXML
    private JFXButton buttonGenero;
    @FXML
    private JFXToggleButton buttonAleatorio;

    @FXML
    private Label labelCancionActual;
// </editor-fold>
//Pestañas    
// <editor-fold defaultstate="collapsed" desc="Generated Code">  

    @FXML
    private void descarga() {
        try {
            mostrarElementos(true, true, true, true, true, true, true, false);
            ListaDoble lista = (mysql.consultaCanciones()).preOrden();
            this.colaBuscar = new Cola();
            ObservableList<Cancion> canciones = FXCollections.observableArrayList();
            int tamanio = lista.tamanio();
            for (int i = 0; i < tamanio; i++) {
                canciones.add(lista.getActual());
                colaBuscar.agregar(lista.getActual());
                lista.moverDerecha();
            }
            listViewDescargas.setItems(canciones);

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(BibliotecaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void miMusica() {
        try {
            mostrarElementos(true, true, true, true, true, true, false, false);

            Cola cola = escritura_Lectura.recuperarCanciones();
            this.colaBuscar = cola;
            if (cola != null) {
                ObservableList<Cancion> canciones = FXCollections.observableArrayList();
                int tamanio = cola.tamanio();
                for (int i = 0; i < tamanio; i++) {
                    canciones.add(cola.quitar());
                }
                listViewMiMusica.setItems(canciones);
            }
        } catch (IOException ex) {
            Logger.getLogger(BibliotecaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void favoritos() {

        try {
            mostrarElementos(true, true, true, true, true, true, false, false);
            Cola cola = escritura_Lectura.recuperarFavoritos();
            this.colaBuscar = cola;
            if (cola != null) {

                ObservableList<Cancion> canciones = FXCollections.observableArrayList();
                int tamanio = cola.tamanio();
                for (int i = 0; i < tamanio; i++) {
                    canciones.add(cola.quitar());
                }
                listViewFavoritos.setItems(canciones);
            }
        } catch (IOException ex) {
            Logger.getLogger(BibliotecaController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void topTec() {

        try {
            mostrarElementos(true, true, true, true, true, true, true, false);
            Cola cola = escritura_Lectura.recuperarTopTec();
            this.colaBuscar = cola;
            if (cola != null) {
                ObservableList<Cancion> canciones = FXCollections.observableArrayList();
                int tamanio = cola.tamanio();
                for (int i = 0; i < tamanio; i++) {
                    canciones.add(cola.quitar());
                }
                listViewTopTec.setItems(canciones);
            }
        } catch (IOException ex) {
            Logger.getLogger(BibliotecaController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void playlist() {
        mostrarElementos(false, false, false, false, false, false, false, true);
        labelInformacion.setText("PlayLIsts");
        textFieldNombreCancion.setPromptText("Nombre de la PlayLIst");
        try {
            ArrayList<String> playlist = escritura_Lectura.recuperarPlayList();
            if (playlist != null) {
                ObservableList<String> lista = FXCollections.observableArrayList();
                playlist.forEach((string) -> {
                    lista.add(string);
                });
                listViewPlayList.setItems(lista);
            }
        } catch (IOException ex) {
            Logger.getLogger(BibliotecaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void cuenta() {
        mostrarElementos(false, false, false, false, false, false, false, true);
        labelInformacion.setText("Informacion de la cuenta");
    }
// </editor-fold>
//botones
// <editor-fold defaultstate="collapsed" desc="Generated Code">  

    @FXML
    private void aniadirPlaylist() {
        Cancion cancion = null;
        if (tabMiMusica.isSelected()) {
            cancion = listViewMiMusica.getSelectionModel().getSelectedItem();
        }
        if (tabFavoritos.isSelected()) {
            cancion = listViewMiMusica.getSelectionModel().getSelectedItem();
        }
        PlayList p = new PlayList();
        p.iniciar(cancion);
    }

    @FXML
    private void crearPlaylist() {
        try {
            PlayList p = new PlayList();
            p.start(new Stage());
        } catch (Exception ex) {

        }
    }

    @FXML
    private void eliminarPlaylist() {
        try {
            String eliminar = listViewPlayList.getSelectionModel().getSelectedItem();
            escritura_Lectura.eliminarPlaylist(eliminar);
            File archivo = new File(eliminar + ".txt");

            archivo.delete();
        } catch (IOException ex) {
            Logger.getLogger(BibliotecaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void verPlaylist() {
        String playLIst = listViewPlayList.getSelectionModel().getSelectedItem();
        CancionesPlayLIst cp = new CancionesPlayLIst();
        cp.iniciar(playLIst);
    }

    @FXML
    private void reproducirPlaylist() {
        try {
            String playlist = listViewPlayList.getSelectionModel().getSelectedItem();
            Cola cola = escritura_Lectura.recuperarCancionPlayLIst(playlist);
            listaReproduccion = new ListaDoble();
            int tamanio = cola.tamanio();
            for (int i = 0; i < tamanio; i++) {
                listaReproduccion.insertarFin(cola.quitar());
            }
            cancionActual = listaReproduccion.getActual();
            System.out.println(cancionActual);
            reproductor.Play(cancionActual.getTitulo());
            labelCancionActual();
        } catch (IOException ex) {
            Logger.getLogger(BibliotecaController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(BibliotecaController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void aniadirFavoritos() {

        try {
            Cancion cancion = listViewMiMusica.getSelectionModel().getSelectedItem();
            escritura_Lectura.escribirFavoritos(cancion);
        } catch (IOException ex) {

        }
    }

    @FXML
    private void descargar() {
        Cancion cancion;
        if (tabDescarga.isSelected()) {
            try {
                cancion = listViewDescargas.getSelectionModel().getSelectedItem();
                if (ftp.descargarCancion(cancion)) {
                    escritura_Lectura.escribirCancion(cancion);
                }
                JOptionPane.showMessageDialog(null, "Cancion Descargada");
            } catch (IOException | ReproductorException ex) {
                JOptionPane.showMessageDialog(null, ex, "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (tabTopTec.isSelected()) {
            try {
                cancion = listViewTopTec.getSelectionModel().getSelectedItem();
                if (ftp.descargarCancion(cancion)) {
                    escritura_Lectura.escribirCancion(cancion);
                }
                JOptionPane.showMessageDialog(null, "Cancion Descargada");
            } catch (IOException | ReproductorException ex) {
                JOptionPane.showMessageDialog(null, ex, "Error", JOptionPane.ERROR_MESSAGE);
            }

        }
    }

    @FXML
    private void ordenarTitulo() {
        Cancion CancionesOrdenadaTitulo[] = colaBuscar.ordenarTitulo();
        ObservableList<Cancion> canciones = FXCollections.observableArrayList();
        for (Cancion cancion : CancionesOrdenadaTitulo) {
            canciones.add(cancion);
        }
        listViewDescargas.setItems(canciones);
        listViewMiMusica.setItems(canciones);
    }

    @FXML
    private void ordenarArtista() {
        Cancion CancionesOrdenadaTitulo[] = colaBuscar.ordenarArtista();
        ObservableList<Cancion> canciones = FXCollections.observableArrayList();
        for (Cancion cancion : CancionesOrdenadaTitulo) {
            canciones.add(cancion);
        }
        listViewDescargas.setItems(canciones);
        listViewMiMusica.setItems(canciones);
    }

    @FXML
    private void ordenarGenero() {
        Cancion CancionesOrdenadaTitulo[] = colaBuscar.ordenarGenero();
        ObservableList<Cancion> canciones = FXCollections.observableArrayList();
        for (Cancion cancion : CancionesOrdenadaTitulo) {
            canciones.add(cancion);
        }
        listViewDescargas.setItems(canciones);
        listViewMiMusica.setItems(canciones);
    }

    @FXML
    private void buscar() {
        String buscar = textFieldNombreCancion.getText();
        Cancion cancion = colaBuscar.buscarCancion(buscar);
        ObservableList<Cancion> lista = FXCollections.observableArrayList();
        lista.add(cancion);
        if (cancion != null) {
            listViewDescargas.setItems(lista);
            listViewMiMusica.setItems(lista);
            listViewFavoritos.setItems(lista);
            listViewTopTec.setItems(lista);
        } else {
            JOptionPane.showMessageDialog(null, "La cancion no existe", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    @FXML
    private void cerrarSesion() {
        try {
            Principal.cerrar();
            escritura_Lectura.vaciarUsuario();
            escritura_Lectura.vaciarCanciones();
            escritura_Lectura.vaciarFavoritos();
            escritura_Lectura.vaciarPlayList();
            escritura_Lectura.vaciarListaReproduccion();
            escritura_Lectura.vaciarCancionPlayLIst();
            (new Login20()).start(new Stage());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex, "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

// </editor-fold>
//Reproductor
// <editor-fold defaultstate="collapsed" desc="Generated Code">  
    @FXML
    private void reproducir() {
        try {
            if (estadoListaReproduccion == 0) {

                Arbol arbol = new Arbol();
                Cola cola;
                int tamanio;
                if (tabFavoritos.isSelected()) {
                    cancionActual = listViewFavoritos.getSelectionModel().getSelectedItem();
                    cola = escritura_Lectura.recuperarFavoritos();
                    tamanio = cola.tamanio();
                    for (int i = 0; i < cola.tamanio(); i++) {
                        arbol.insertar(cola.quitar());
                    }
                }
                if (tabMiMusica.isSelected()) {
                    cancionActual = listViewMiMusica.getSelectionModel().getSelectedItem();
                    cola = escritura_Lectura.recuperarCanciones();
                    tamanio = cola.tamanio();
                    for (int i = 0; i < tamanio; i++) {
                        Cancion c = cola.quitar();
                        arbol.insertar(c);
                    }
                }
                if (tabTopTec.isSelected()) {
                    cancionActual = listViewTopTec.getSelectionModel().getSelectedItem();
                    ftp.descargarCancion(cancionActual);
                    escritura_Lectura.escribirCancion(cancionActual);
                    cola = escritura_Lectura.recuperarTopTec();
                    tamanio = cola.tamanio();
                    for (int i = 0; i < tamanio; i++) {
                        arbol.insertar(cola.quitar());
                    }
                }

                crearLIstaReproduccion(arbol);
                if (reproductor.status() == 1) {
                    reproductor.Stop();
                }
                reproductor.Play(cancionActual.getTitulo());
                labelCancionActual();
            } else {
                reproductor.Play(cancionActual.getTitulo());
                estadoListaReproduccion = 0;
            }
        } catch (Exception ex) {
            Logger.getLogger(BibliotecaController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void pausa() {
        try {
            reproductor.Pausa();
        } catch (Exception ex) {
            Logger.getLogger(BibliotecaController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void siguiente() {
        try {
            listaReproduccion.moverDerecha();
            if (!cancionActual.equals(listaReproduccion.getActual())) {
                cancionActual = listaReproduccion.getActual();
                if (reproductor.status() == 1) {
                    reproductor.Stop();
                }
                reproductor.Play(cancionActual.getTitulo());
                labelCancionActual();
            }
        } catch (Exception ex) {
            Logger.getLogger(BibliotecaController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void atras() {
        try {
            listaReproduccion.moverIzquierda();
            if (!cancionActual.equals(listaReproduccion.getActual())) {
                cancionActual = listaReproduccion.getActual();
                if (reproductor.status() == 1) {
                    reproductor.Stop();
                }
                reproductor.Play(cancionActual.getTitulo());
                labelCancionActual();
            }
        } catch (Exception ex) {
            Logger.getLogger(BibliotecaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void aleatorio() {
        if (estadoAleatorio == 0) {
            estadoAleatorio = 1;
        } else {
            estadoAleatorio = 0;
        }

    }

// </editor-fold>
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {

            usuario = escritura_Lectura.recueprarUsuario();
            ftp.descargarTopTec();
            
            llenarDatosUsuario();
            
            listaReproduccion = escritura_Lectura.recuperarListaReproduccion();
            if (listaReproduccion != null) {
                cancionActual = listaReproduccion.getActual();
                labelCancionActual();
                estadoListaReproduccion = 1;
            }
        } catch (IOException ex) {
            Logger.getLogger(BibliotecaController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ReproductorException ex) {
            JOptionPane.showMessageDialog(null, ex, "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    private void mostrarElementos(boolean lupa, boolean textField, boolean buscar, boolean titulo, boolean genero, boolean artista, boolean descargar, boolean label) {
        textFieldNombreCancion.setPromptText("Nombre de la cancion");
        imageViewLupa.setVisible(lupa);
        textFieldNombreCancion.setVisible(textField);
        buttonBuscar.setVisible(buscar);
        buttonTitulo.setVisible(titulo);
        buttonArtista.setVisible(artista);
        buttonGenero.setVisible(genero);
        buttonDescargar.setVisible(descargar);
        labelInformacion.setVisible(label);
    }

    private void crearLIstaReproduccion(Arbol arbol) {
        ListaReproduccion listaR = new ListaReproduccion(arbol);
        if (buttonAleatorio.isPressed()) {
            BibliotecaController.listaReproduccion = listaR.ordenIngresado();
        } else {
            BibliotecaController.listaReproduccion = listaR.ordenAleatorio();
        }
        listaReproduccion.buscarActual(cancionActual, BibliotecaController.listaReproduccion);
    }

    private void labelCancionActual() {
        labelCancionActual.setText(cancionActual.getTitulo() + " - " + cancionActual.getAutor());
    }

    public static void respaldarListaReproduccion() throws IOException {
        try {
            reproductor.Stop();
            listaReproduccion.regresarInicio();
            escritura_Lectura.escribirListaReproduccion(listaReproduccion);
        } catch (Exception ex) {
            Logger.getLogger(BibliotecaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void usuario(Usuario usuario) {
        BibliotecaController.usuario = usuario;
    }

    
    public void llenarDatosUsuario(){
        if (usuario != null) {
                labelUsuario.setText(usuario.getUsuario());
                labelCorreo.setText(usuario.getCorreo());
                labelnombre.setText(usuario.getNombre() + " " + usuario.getApellidos());
            }
    }
}
