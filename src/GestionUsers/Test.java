/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionUsers;

import hexclan.DbHexClan.MAConnexion;
import hexclan.entities.User;
import hexclan.services.JavaMailUtil;
import hexclan.services.UserService;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.scene.control.Alert;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 *
 * @author 21650
 */
public class Test {
    public static void main(String[] args) throws Exception {
        MAConnexion mc = MAConnexion.getInstance();
        
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        
        UserService us = new UserService();
        System.out.println(us.findUser("abassi.saif.ns@gmail.com", "playmaker2021").toString());
        
    
 
        //Invalid emails
        String email=("15545652");
 
        String regex = "^[0-9]{8}$";
 
        Pattern pattern = Pattern.compile(regex);
 
        
            Matcher matcher = pattern.matcher(email);
            System.out.println(email +" : "+ matcher.matches());
       
         
    }
    
}
