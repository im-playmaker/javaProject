/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hexclan.services;

import java.util.Map;
import java.util.Properties;
import javax.mail.PasswordAuthentication;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
/**
 *
 * @author 21650
 */
public class JavaMailUtil {
    
    
    public static void sendMail(String recepient,String msg) throws Exception {
        System.out.println("Preparing to send email");
        Properties properties = new Properties();
        
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        
        String myAccountEmail="abassi.seifeddin@esprit.tn";
        String password="203JMT1815";
        
      Session session =Session.getInstance(properties,new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication(){
            return new PasswordAuthentication(myAccountEmail,password);
            }
            
});
        Message message= prepareMessage(session,myAccountEmail,recepient,msg);
        Transport.send(message);
        System.out.println("Message sent successfully");
    }

    private static Message prepareMessage(Session session, String myAccountEmail,String recepient,String msg) throws Exception {
        try {
             Message message=new MimeMessage(session);
             message.setFrom(new InternetAddress(myAccountEmail));
             message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
             
             message.setSubject("Restauration de mot de passe");
             message.setText("votre mot de passe est : "+msg);
                 return message;
            } catch (AddressException ex) {
            System.out.println("Probleme Email : "+ex.getMessage());
            }  
                       return null;//To change body of generated methods, choose Tools | Templates.
           }
    }
