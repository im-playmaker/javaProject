/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.net.Authenticator;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Asus
 */
public class JavaMialUtil {
    public static void sendMail(String recipient) throws MessagingException{
        System.out.println("préparation du mail");
        Properties properties = new Properties();
        
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        
        String myAccountEmail = "ghada.sellami@esprit.tn";
        String password = "181JFT1100";
        
        Session session = Session.getInstance(properties,new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail,password); //To change body of generated methods, choose Tools | Templates.
            }
            
        });
        
        Message message = prepareMessage(session, myAccountEmail, recipient);
        Transport.send(message);
        System.out.println("Message envoyé");
    }
    
    private static Message prepareMessage(Session session, String myAccountEmail, String recipent) throws AddressException, MessagingException{
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(myAccountEmail));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipent));
        message.setSubject("Reservation de la maison");
        message.setText("reservation effectuer avec succès");
        
        return message;
    }
}
