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
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Observable;
import java.util.ResourceBundle;
import static javafx.beans.binding.Bindings.select;
import static javafx.beans.binding.Bindings.select;
import static javafx.beans.binding.Bindings.select;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import javafx.util.Duration;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author 21650
 */
public class ShowPersonnesController implements Initializable {

    @FXML
    private TextField txtsearch;
    @FXML
    private TableView<User> listtable;
    @FXML
    private TableColumn<?, ?> nom;
    @FXML
    private TableColumn<?, ?> email;
    @FXML
    private TableColumn<?, ?> cin;
    @FXML
    private TableColumn<?, ?> compte;

   
    // Observable list to store data 
    private final ObservableList<User> dataList = FXCollections.observableArrayList();
    private UserService us = new UserService();
    @FXML
    private TextField txtcin;
    @FXML
    private Button btnActiver;
    @FXML
    private Button btnDesactiver;
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
       email.setCellValueFactory(new PropertyValueFactory<>("email"));
       cin.setCellValueFactory(new PropertyValueFactory<>("cin"));
       compte.setCellValueFactory(new PropertyValueFactory<>("compteType"));
       
        
        Path path = Paths.get("ShowPersonnes.fxml");
        
        System.out.println(path.toAbsolutePath());
           
       dataList.addAll(us.showUsers());
       User u = new User();
       
       //wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<User> filteredData = new FilteredList<>(dataList,b->true);
        //2 Set the filter predicate whenever the filter changes.
        txtsearch.textProperty().addListener((observable, oldValue, newValue)->{
            filteredData.setPredicate(User ->{
                //if filter text is empty, display all persons
                if (newValue== null || newValue.isEmpty()){
                    return true;
                }
                //compare  cin of every person with filter text.
                return User.getCin().contains(newValue);
                
            });
        });
        // 3 Wrap the filtered List in a storedList.
        SortedList<User> sortedData = new SortedList<>(filteredData);
        
        //4 bind the SortedList comarator to the TableView comparator.
        // Otherwise, sorting the Tableview would have no effect.
        sortedData.comparatorProperty().bind(listtable.comparatorProperty());
        //5 Add sorted (and filtered) data to the table
        listtable.setItems(sortedData);
        
    }    
    

    
    @FXML
    private void txtsearch(ActionEvent event) {
        
    }

    
    @FXML
    private void profile(ActionEvent event) {
         try {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("Profile.fxml"));
            Parent root = loader.load();
            ProfileController ad = loader.getController();
            txtsearch.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println("Problemme d'importation d'interface Profile");
        }
    }

    @FXML
    private void active(ActionEvent event) {
        dataList.clear();
        dataList.addAll(us.Compte("1"));
        
    }

    @FXML
    private void inactive(ActionEvent event) {
         dataList.clear();
        dataList.addAll(us.Compte("0"));
    }

    @FXML
    private void getfromtable(MouseEvent event) {
      User h= listtable.getSelectionModel().getSelectedItem();
     String cin=h.getCin();
     txtcin.setText(cin);
     if(h.getStatus()==0){
         btnActiver.setDisable(false);
         btnDesactiver.setDisable(true);
     }else {
         btnActiver.setDisable(true);
         btnDesactiver.setDisable(false);
     }
     
     
    }

    @FXML
    private void activer(ActionEvent event) {
        us.updateStatus(txtcin.getText(),1);
         TrayNotification tray = new TrayNotification();
        tray.setTitle("Update");
        tray.setMessage("Compte activé");
        tray.setNotificationType(NotificationType.SUCCESS);
        tray.showAndDismiss(Duration.seconds(3));
    }

    @FXML
    private void desactiver(ActionEvent event) {
         us.updateStatus(txtcin.getText(),0);
          TrayNotification tray = new TrayNotification();
        tray.setTitle("Update");
        tray.setMessage("Compte déactivé");
        tray.setNotificationType(NotificationType.SUCCESS);
        tray.showAndDismiss(Duration.seconds(3));
        
    }
    
    
    
}
