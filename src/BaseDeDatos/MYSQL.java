/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseDeDatos;

import EstructurasDeDatos.Arbol;
import Herramientas.CorreoRecuperacion;
import Herramientas.Desencriptar;
import Herramientas.Encriptar;
import Objetos.Usuario;
import Objetos.Cancion;
import Objetos.ReproductorException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author nicolas
 */
public class MYSQL {

    private final String hostname = "localhost";
    private final String username = "root";
    private final String password = "root";
    private final String dataBase = "Reproductor";
//    private final String hostname = "remotemysql.com";
//    private final String username = "LyfLNqRuxS";
//    private final String password = "ecnESkzBL3";
//    private final String dataBase = "LyfLNqRuxS";
    private final String port = "3306";
    private final String driver = "com.mysql.jdbc.Driver";
    private final String url = "jdbc:mysql://" + hostname + ":" + port + "/" + dataBase + "?useSSL=false";

    /**
     * Crea una conexión con la base de datos
     *
     * @return la conexión ya preparada para trabajar
     */
    private Connection conexion() throws ClassNotFoundException, SQLException {
        Connection connection;
        Class.forName(driver);
        connection = DriverManager.getConnection(url, username, password);
        return connection;
    }

    /**
     *
     * @param usuario nombre del usuario a recuperar la contraseña
     * @return returna true si el usuario existe y la contraseña fue recuperada.
     * false si el usuario no existe
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     * @throws Objetos.ReproductorException
     */
    public boolean recuperarContrasenia(String usuario) throws ClassNotFoundException, SQLException, ReproductorException {
        if (!usuario.equals("")) {
            Connection connection = conexion();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Usuario WHERE usuario='" + usuario + "'");
            if (resultSet.first()) {
                String contrasenia = (new Desencriptar(resultSet.getString("contrasenia"))).toString();
                String destinatario = resultSet.getString("correo");
                String nombre = resultSet.getString("nombre") + " " + resultSet.getString("apellidos");
                CorreoRecuperacion correoRecuperacion = new CorreoRecuperacion(contrasenia, destinatario, nombre);
                correoRecuperacion.enviarCorreo();
                return true;
            } else {
                throw new ReproductorException("Usuario no existe");
            }

        } else {
            throw new ReproductorException("Ingresa un usuario");
        }
    }

    /**
     * Crea un nuevo usuario
     *
     * @return true si se crea el usuario, false si no lo crea
     * @param usuario Objeto de tipo DatosUsuario con la información del usuario
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     * @throws Objetos.ReproductorException
     */
    public boolean crearUsuario(Usuario usuario) throws ClassNotFoundException, SQLException, ReproductorException {
        Connection connection = conexion();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM Usuario WHERE usuario='" + usuario.getUsuario() + "'");
        if (!resultSet.first()) {
            boolean crearUsuario = statement.execute("INSERT INTO Usuario(usuario,"
                    + "contrasenia,"
                    + "nombre,"
                    + "apellidos,"
                    + "correo) "
                    + "VALUES "
                    + "('" + usuario.getUsuario() + "',"
                    + "'" + usuario.getContrasenia() + "',"
                    + "'" + usuario.getNombre() + "',"
                    + "'" + usuario.getApellidos() + "',"
                    + "'" + usuario.getCorreo() + "')");
            return !crearUsuario;
        } else {
            throw new ReproductorException("El usuario ya existe");
        }

    }

    /**
     *
     * @param usuario Cadena con el usuario para el login
     * @param contrasenia cadena con la contraseña sin encriptar para el login
     * @return retornará los datos del usuario en caso de ser logueado, de lo
     * contrario retornará null
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     * @throws ReproductorException Indicará que el usuario no existe
     */
    public Usuario login(String usuario, String contrasenia) throws ClassNotFoundException, SQLException, ReproductorException {
        Connection connection = conexion();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM Usuario WHERE usuario='" + usuario + "'");
        if (resultSet.first()) {
            Encriptar contraseniaEncriptada = new Encriptar(contrasenia);
            if (resultSet.getString("contrasenia").equals(contraseniaEncriptada.toString())) {
                return new Usuario(resultSet.getString("nombre"),
                        resultSet.getString("apellidos"),
                        resultSet.getString("correo"),
                        resultSet.getString("usuario"),
                        (new Desencriptar(resultSet.getString("contrasenia"))).toString());
            } else {
                throw new ReproductorException("Usuario y/o contraseña incorrectos");
            }

        } else {
            throw new ReproductorException("Usuario y/o contreseña incorrectos");
        }
    }

    //mejorar el método con una pila de canciones y hacerlo recursivo
    public Arbol consultaCanciones() throws ClassNotFoundException, SQLException {
        
        Arbol canciones = new Arbol();
        Connection connection = conexion();
        Statement statement = connection.createStatement();
        //ResultSet resultSet = statement.executeQuery("SELECT * FROM Cancion ORDER BY titulo asc");
        ResultSet resultSet = statement.executeQuery("SELECT * FROM Cancion");
        if (resultSet != null) {

            while (resultSet.next()) {
                canciones.insertar(new Cancion(resultSet.getString("titulo"), resultSet.getString("artista"), resultSet.getString("genero"),resultSet.getString("duracion")));

            }

        }
        return canciones;
    }

}
