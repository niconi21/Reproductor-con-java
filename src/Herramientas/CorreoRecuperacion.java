/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Herramientas;

/**
 *
 * @author nicolas
 */
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class CorreoRecuperacion {

    private final String emisor;
    private final String destinatario;
    private final String asunto;
    private final String cuerpo;
    static String username = "RaccoonsCompany2019";
    static String password = "Raccoon's2019";

    /**
     * 
     * @param contrasenia contraseña del usuario desencriptada
     * @param destinatario correo del usuario
     * @param nombre nombre del usuario
     */
    public CorreoRecuperacion(String contrasenia, String destinatario, String nombre) {
        emisor = "morenoduann";
        this.destinatario = destinatario;
        asunto = "Recuperacion de contraseña";
        cuerpo = "Hola " + nombre + "\nLa contraseña de su cuenta es: " + contrasenia;
    }
    
    /**
     * Enviará un correo con la información del usuario para poder proporcionarle su contraseña
     */
    public void enviarCorreo() {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(destinatario));
            message.setSubject(asunto);
            message.setText(cuerpo);

            Transport.send(message);
            
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

}
