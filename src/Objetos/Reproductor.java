/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

import java.io.File;
import javazoom.jlgui.basicplayer.BasicPlayer;
import javazoom.jlgui.basicplayer.BasicPlayerException;

/**
 *
 * @author nicolas
 */
public class Reproductor {

    private final BasicPlayer basicPlayer = new BasicPlayer();

    private void abrirArchivo(String cancion) throws BasicPlayerException {
        basicPlayer.open(new File("Canciones/" + cancion + ".mp3"));
    }

    public void Play(String cancion) throws Exception {
        abrirArchivo(cancion);
        basicPlayer.play();
    }

    public void Pausa() throws Exception {
        basicPlayer.pause();
    }

    public void Continuar() throws Exception {
        basicPlayer.resume();
    }

    public void Stop() throws Exception {
        basicPlayer.stop();
    }
    public int status() throws Exception {
        return basicPlayer.getStatus();
    }

}
