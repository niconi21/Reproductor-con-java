/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Herramientas;

import Objetos.Cancion;
import Objetos.ReproductorException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.commons.net.ftp.FTPClient;

/**
 *
 * @author nicolas
 */
public class FTP {

    FTPClient fTPClient;
    String ruta = (new File("")).getAbsolutePath() + "/Canciones/";


    private void conectar() throws IOException, ReproductorException {
        fTPClient = new FTPClient();
        fTPClient.connect("localhost", 21);
        if (fTPClient.login("ftpclient", "niconi21")) {
            fTPClient.enterLocalPassiveMode();
            fTPClient.changeWorkingDirectory("ftp/files");
        } else {
            throw new ReproductorException("Error en la conexion");
        }

    }
//    private void conectar() throws IOException, ReproductorException {
//        fTPClient = new FTPClient();
//        fTPClient.connect("files.000webhost.com", 21);
//        if (fTPClient.login("raccoons2019", "niconi21")) {
//            fTPClient.enterLocalPassiveMode();
//            fTPClient.changeWorkingDirectory("ftp/files");
//        } else {
//            throw new ReproductorException("Error en la conexion");
//        }
//
//    }

    public void desconectar() throws IOException {
        fTPClient.logout();
        fTPClient.disconnect();
    }

    public boolean descargarCancion(Cancion informacionMusica) throws FileNotFoundException, IOException, ReproductorException {
        conectar();
        FileOutputStream cancion = new FileOutputStream(ruta + informacionMusica.getTitulo() + ".mp3");
        boolean descargado = fTPClient.retrieveFile(informacionMusica.getTitulo() + ".mp3", cancion);
        desconectar();
        return descargado;
    }

    public boolean descargarTopTec() throws FileNotFoundException, IOException, ReproductorException {
        conectar();
        FileOutputStream topTec = new FileOutputStream(ruta + "../TopTec.txt");
        boolean descargado = fTPClient.retrieveFile("TopTec.txt", topTec);
        desconectar();
        return descargado;
    }

}
