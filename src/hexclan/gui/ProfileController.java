/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hexclan.gui;

import hexclan.entities.User;
import hexclan.services.UserService;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import sun.security.jgss.LoginConfigImpl;

/**
 * FXML Controller class
 *
 * @author 21650
 */
public class ProfileController implements Initializable {

    @FXML
    private Button btnmodifier;
    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField email;
    @FXML
    private TextField motdepasse;
    @FXML
    private TextField numtel;
    @FXML
    private TextField cin;
    @FXML
    private DatePicker date;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       nom.setText(LoginController.logged.getNom());
       prenom.setText(LoginController.logged.getPrenom());
       email.setText(LoginController.logged.getEmail());
       motdepasse.setText(LoginController.logged.getPassword());
       numtel.setText(LoginController.logged.getTel());
       cin.setText(LoginController.logged.getCin());
       
      // date.setValue(LocalDate.MAX);
      LocalDate d1 = LoginController.logged.getDateNaiss().toLocalDate();
               date.setValue(d1);
       
    }    

    @FXML
    private void update(ActionEvent event) {
        LocalDate date2 =date.getValue();
         Date dateness = Date.valueOf(date2);
        UserService us = new UserService();
        User u = LoginController.logged;
        u.setNom(nom.getText());
        u.setPrenom(prenom.getText());
         u.setCin(cin.getText());
         u.setEmail(email.getText());
         u.setPassword(motdepasse.getText());
         u.setCompteType(LoginController.logged.getCompteType());
          u.setDateNaiss(dateness);
         u.setCreated_at(LoginController.logged.getCreated_at());
         u.setTel(numtel.getText());
         u.setStatus(LoginController.logged.getStatus());
        if (us.UpdateUser(u)){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Modifier avec succes");
                alert.setContentText("Votre profile à été modifié");
                alert.show();
                
        }
    }

    @FXML
    private void retour(ActionEvent event) {
        if(LoginController.logged.getCompteType().equals("admin")){
            try{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("ShowPersonnes.fxml"));
                Parent root = loader.load();
                ShowPersonnesController ad = loader.getController();
                motdepasse.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
