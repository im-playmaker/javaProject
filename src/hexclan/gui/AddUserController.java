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
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author 21650
 */
public class AddUserController implements Initializable {

    @FXML
    private TextField txtNom;
    @FXML
    private TextField txtPrenom;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtPassword;
    @FXML
    private TextField txtCin;
    @FXML
    private TextField txtTel;
    @FXML
    private Button btnInscrit;
    @FXML
    private DatePicker datepicker;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void AddUser(ActionEvent event) {
        if(datepicker.getValue() != null){
         LocalDate date2 =datepicker.getValue();
         Date dateness = Date.valueOf(date2);
        
         User p = new User(txtNom.getText(), txtPrenom.getText(), txtEmail.getText(), txtPassword.getText(), txtCin.getText(), txtTel.getText(),dateness );
         UserService us = new UserService();
         if(us.verifValue(p)){
             if (us.verifMail(txtEmail.getText()).equals("")){
          if (!us.checkemail(txtEmail.getText())){
              if(!us.verifCin(txtCin.getText()).equals("") || us.checkcin(txtCin.getText())){
                  Alert alert = new Alert(Alert.AlertType.ERROR);
                  alert.setTitle("Impossible de créer un compte");
                  alert.setContentText("Cin Invalide ou deja existe!!" + us.verifCin(txtCin.getText()));
                  alert.show();
              }
              else if (!us.verifTel(txtTel.getText()).equals("")){
                   Alert alert = new Alert(Alert.AlertType.ERROR);
                  alert.setTitle("Numero du telephone ");
                  alert.setContentText(us.verifTel(txtTel.getText()));
                  alert.show();
              }else {
                  
                  
                  try
                  {
                      us.AddUser(p);
                      Alert alert = new Alert(Alert.AlertType.INFORMATION);
                      alert.setTitle("Compte Creé");
                      alert.setContentText("Votre compte à été creé");
                      alert.show();
                      
                      FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
                      Parent root = loader.load();
                      LoginController ad = loader.getController();
                      txtNom.getScene().setRoot(root);
                      
                      
                  }catch(IOException ex){
                      Alert alert = new Alert(Alert.AlertType.ERROR);
                      alert.setTitle("Impossible de créer un compte");
                      alert.setContentText(ex.getMessage());
                      alert.show();
                  }
              }
         }else {
             Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Impossible de créer un compte");
            alert.setContentText("Email deja existe dans la base!!");
            alert.show();
          }
         }else{
             Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Email invalide");
            alert.setContentText(us.verifMail(txtEmail.getText()));
            alert.show();
             }
         }else {
             Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Verifier les champs");
            alert.setContentText("champ vide !!");
            alert.show();
          }
         
        }else {
             Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Date is null");
            alert.setContentText("champ date est vide!!");
            alert.show();
          }
    }

    @FXML
    private void login(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
            Parent root = loader.load();
            LoginController ad = loader.getController();
            txtNom.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(AddUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
   
    
    
}
