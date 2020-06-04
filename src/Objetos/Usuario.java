/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

import Herramientas.Encriptar;

/**
 *
 * @author manuel
 */
public final class Usuario {

    private String nombre, apellidos, correo, usuario;
    private Encriptar contrasenia;

    public Usuario(String nombre, String apellidos, String correo, String usuario, String contrasenia) throws ReproductorException {
        setNombre(nombre);
        setApellidos(apellidos);
        setCorreo(correo);
        setUsuario(usuario);
        setContrasenia(contrasenia);
    }

    public Usuario() throws ReproductorException {
        this("Nombre", "Apellidos", "Correo", "Usuario", "");
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) throws ReproductorException {
        if (!nombre.equals("")) {
            this.nombre = nombre;
        } else {
            throw new ReproductorException("Nombre Invalido");
        }
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) throws ReproductorException {
        if (!apellidos.equals("")) {

            this.apellidos = apellidos;
        } else {
            throw new ReproductorException("Apellidos Invalidos");
        }
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) throws ReproductorException {
        if (!correo.equals("")) {

            this.correo = correo;
        } else {
            throw new ReproductorException("Correo Invalido");
        }
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) throws ReproductorException {
        if (!usuario.equals("")) {

            this.usuario = usuario;
        } else {
            throw new ReproductorException("Usuario Invalido");
        }
    }

    public String getContrasenia() {
        return contrasenia.toString();
    }

    public void setContrasenia(String contrasenia) throws ReproductorException {
        if (!contrasenia.equals("")) {
            this.contrasenia = new Encriptar(contrasenia);
        } else {
            throw new ReproductorException("Contraseña Invalido");
        }
    }

    @Override
    public String toString() {
        return getNombre() + "," + getApellidos() + "," + getCorreo() + "," + getUsuario() + "," + getContrasenia();
    }
}
