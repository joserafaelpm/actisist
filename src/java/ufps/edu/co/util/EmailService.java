/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufps.edu.co.util;

import java.util.List;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import ufps.edu.co.dto.SolicitudRegistro;

public class EmailService {
    private final String URL = "http://localhost:8084/actisist/ControlUsuario?q=access_sol";
    private final String ASUNTO = "Invitación al registro en la plataforma ACTISIST";
    private final String CUERPO = "Se invita a diligenciar por medio del siguiente link el formulario de registro para el acceso o solicitud de participación en la plataforma ACTISIST";
    private final Properties properties = new Properties();

    private Session session;

    private void init() {

        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.starttls.required", "true");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        properties.put("mail.smtp.ssl.protocols", "TLSv1.2");
        properties.put("mail.smtp.port", 587);
        properties.put("mail.smtp.mail.sender", "software.actisist@gmail.com");
        properties.put("mail.smtp.user", "software.actisist@gmail.com");

        session = Session.getDefaultInstance(properties);
    }

    public void sendEmail(SolicitudRegistro sr) {

        init();
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress((String) properties.get("mail.smtp.mail.sender")));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(sr.getEmail()));
            message.setSubject(this.ASUNTO);
            message.setText(this.CUERPO+"\n"+this.URL+"&token="+sr.getToken()+"&type="+sr.getTypeUs().getId());
            Transport t = session.getTransport("smtp");
            t.connect((String) properties.get("mail.smtp.user"), "sistema#115");
            t.sendMessage(message, message.getAllRecipients());
            t.close();
        } catch (MessagingException me) {
            System.out.println(me.getMessage());
        }
    }
}
