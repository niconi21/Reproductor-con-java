package Objetos;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author nicolas
 */
public class ReproductorException extends Exception {

    private final String mensaje;
    
    public ReproductorException(String mensaje) {
        this.mensaje=mensaje;
    }

    @Override
    public String toString() {
        return "Error: "+mensaje;
    }
    
    
}
