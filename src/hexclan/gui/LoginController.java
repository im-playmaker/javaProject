/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hexclan.gui;

import hexclan.services.UserService;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import hexclan.entities.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author 21650
 */
public class LoginController implements Initializable {

    @FXML
    private TextField txtAdresse;
    @FXML
    private PasswordField txtPass;
    @FXML
    private Button btnConnect;
    @FXML
    private Button btnnewAccount;
    @FXML
    private Label passoubliée;
    public static User logged;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void login(ActionEvent event) {
        UserService us = new UserService();
        User u = new User();
        u = us.findUser(txtAdresse.getText(), txtPass.getText());
        if(u != null){
            try {
                Parent root;
                logged=u;
                if(u.getCompteType().equals("admin")){
                FXMLLoader loader = new FXMLLoader(getClass().getResource("dashboard.fxml"));
                root = loader.load();
                DashboardController ad = loader.getController();
                }else {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("Profile.fxml"));
                     root = loader.load();
                    ProfileController ad = loader.getController();
                }
                txtAdresse.getScene().setRoot(root);
                
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else {
             Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Impossible de connecter");
            alert.setContentText("Adresse ou mot de passe incorrect!!");
            alert.show();
        }
    }

    @FXML
    private void newAccount(ActionEvent event) {
        try {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("AddUser.fxml"));
            Parent root = loader.load();
            AddUserController ad = loader.getController();
            txtAdresse.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println("Problemme d'importation d'interface AddUser");
        }
    }

    @FXML
    private void forgetpass(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("passoubliée.fxml"));
            Parent root = loader.load();
            PassoubliéeController ad = loader.getController();
            txtAdresse.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
