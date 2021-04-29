/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hexclan.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author 21650
 */
public class DashboardController implements Initializable {

    @FXML
    private Pane container;
    @FXML
    private AnchorPane users;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        try {
            
            Pane newLoadedPane= FXMLLoader.load(getClass().getResource("ShowPersonnes.fxml"));
            container.getChildren().add(newLoadedPane);
        } catch (IOException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void users(Event event) {
        try {
            container.getChildren().clear();
            Pane newLoadedPane= FXMLLoader.load(getClass().getResource("ShowPersonnes.fxml"));
            container.getChildren().add(newLoadedPane);
        } catch (IOException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void logement(Event event) {
         try {
            container.getChildren().clear();
            Pane newLoadedPane= FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
            container.getChildren().add(newLoadedPane);
        } catch (IOException ex) {
            System.out.println("problem container");
        }
    }

    @FXML
    private void Profil(Event event) {
        try {
            container.getChildren().clear();
            Pane newLoadedPane= FXMLLoader.load(getClass().getResource("Profile.fxml"));
            container.getChildren().add(newLoadedPane);
        } catch (IOException ex) {
            System.out.println("problem container");
        }
    }

    @FXML
    private void aide(Event event) {
         try {
            container.getChildren().clear();
            Pane newLoadedPane= FXMLLoader.load(getClass().getResource("sample.fxml"));
            container.getChildren().add(newLoadedPane);
        } catch (IOException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void voyage(Event event) {
         try {
            container.getChildren().clear();
            Pane newLoadedPane= FXMLLoader.load(getClass().getResource("voyage.fxml"));
            container.getChildren().add(newLoadedPane);
        } catch (IOException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void promotion(Event event) {
         try {
            container.getChildren().clear();
            Pane newLoadedPane= FXMLLoader.load(getClass().getResource("promotion.fxml"));
            container.getChildren().add(newLoadedPane);
        } catch (IOException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void deconnexion(Event event) {
        
        
    }

    @FXML
    private void stat(Event event) {
          try {
            container.getChildren().clear();
            Pane newLoadedPane= FXMLLoader.load(getClass().getResource("stat.fxml"));
            container.getChildren().add(newLoadedPane);
        } catch (IOException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
