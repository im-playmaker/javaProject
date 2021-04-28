/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hexclan.gui;

import hexclan.services.JavaMailUtil;
import hexclan.services.UserService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.util.Duration;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
/**
 * FXML Controller class
 *
 * @author 21650
 */
public class PassoubliéeController implements Initializable {

    @FXML
    private Button btnrecuperer;
    @FXML
    private TextField txtemail;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void restaurer(ActionEvent event) {
        try {
            String email = txtemail.getText();
            UserService us = new UserService();
            String password =us.findUserPass(email);
            JavaMailUtil.sendMail(email,password);
             //test mail abassi.saif.ns@gmail.com

             
                    String title = "Email envoyé";
                 String message = "consultez votre email!!";
       
        
        TrayNotification tray = new TrayNotification();
        tray.setTitle(title);
        tray.setMessage(message);
        tray.setNotificationType(NotificationType.SUCCESS);
        tray.showAndDismiss(Duration.seconds(3));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
            Parent root = loader.load();
            LoginController ad = loader.getController();
            txtemail.getScene().setRoot(root);
        } catch (Exception ex) {
            System.out.println("restaurer problem !: "+ex.getMessage());
        }
       
    }
    
}
