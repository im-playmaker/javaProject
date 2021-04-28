/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hexclan.gui;

import hexclan.entities.Categorie;
import hexclan.entities.Logement;
import hexclan.entities.Reservation;
import hexclan.services.CategorieService;
import hexclan.services.LogementService;
import hexclan.services.ReservationService;
import hexclan.services.DB_CONNECTION;
import hexclan.services.JavaMialUtil;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
 
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javax.mail.MessagingException;
import javax.swing.JFileChooser;

/**
 *
 * @author Asus
 */
public class FXMLDocumentController implements Initializable {
    private Desktop desktop = Desktop.getDesktop();
    @FXML
    private Label label;
    @FXML
    private TableView<Reservation> tableReservation;
    @FXML
    private TableColumn<Reservation, String> idReservation;
    @FXML
    private TableColumn<Reservation, String> IdClientReservation;
    @FXML
    private TableColumn<Reservation, String> IdLogReservation;
    @FXML
    private TableColumn<Reservation, String> dateDebutReservation;
    @FXML
    private TableColumn<Reservation, String> dateFinReservation;
    @FXML
    private TableColumn<Reservation, String> NombreNuitsReservation;
    @FXML
    private TableColumn<Reservation, String> NombrePersonnesReservation;
    @FXML
    private DatePicker DateDebutTexteReservation;
    @FXML
    private DatePicker DateFinTexteReservation;
    @FXML
    private TableView<Logement> tableLogement;
    @FXML
    private TableColumn<Logement, String> IdLogement;
    @FXML
    private TableColumn<Logement, String> IdAdminLogement;
    @FXML
    private TableColumn<Logement, String> NombreEtoileLogement;
    @FXML
    private TableColumn<Logement, String> NomLogement;
    @FXML
    private TableColumn<Logement, String> IdCategorieLogement;
    @FXML
    private TableColumn<Logement, String> PrixparNuitLogement;
    @FXML
    private TableView<Categorie> tableCategorie;
    @FXML
    private TableColumn<Categorie, String> typeCategorie;
    Connection connection = null ;
    String query = null;
    PreparedStatement preparedStatement = null ;
    ResultSet resultSet = null ;
    Reservation reservation = null;
    Logement logement = null;
    Categorie categorie = null;
    ReservationService RS = new ReservationService();
    LogementService LS = new LogementService();
    CategorieService CS = new CategorieService();
    @FXML
    private TextField IdClientTexteReservation;
    @FXML
    private TextField IdLogTexteReservation;
    @FXML
    private TextField NombreNuitsTexteReservation;
    @FXML
    private TextField NombrePersonnesTexteReservation;
    @FXML
    private TextField IdAdminTexteLogement;
    @FXML
    private TextField NombreNuitsTexteLogement;
    @FXML
    private TextField NomTexteLogement;
    @FXML
    private TextField IdCatégorieTexteLogement;
    @FXML
    private TextField PrixParNuitsTexteLogement;
    @FXML
    private TextField typeTexteCatégorie;
    @FXML
    private TextField rechercheTextReservation;
    @FXML
    private JFXDatePicker date1;
    @FXML
    private JFXDatePicker date2;
    
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        AfficherReservation();
        AfficherLogement();
    }    
    
    public void AfficherReservation(){
        tableReservation.setItems(RS.Afficher());
        idReservation.setCellValueFactory(new PropertyValueFactory<>("id"));
        IdClientReservation.setCellValueFactory(new PropertyValueFactory<>("id_Client"));
        IdLogReservation.setCellValueFactory(new PropertyValueFactory<>("id_Log"));
        dateDebutReservation.setCellValueFactory(new PropertyValueFactory<>("date_debut"));
        dateFinReservation.setCellValueFactory(new PropertyValueFactory<>("date_fin"));
        NombreNuitsReservation.setCellValueFactory(new PropertyValueFactory<>("nbr_nuits"));
        NombrePersonnesReservation.setCellValueFactory(new PropertyValueFactory<>("nbre_personnes"));
        
    }

    @FXML
    private void AjouterReservation(ActionEvent event) {
        connection = DB_CONNECTION.getConnect();
        int id_client= Integer.parseInt(IdClientTexteReservation.getText());
        int id_log = Integer.parseInt(IdLogTexteReservation.getText());
        String date_debut = String.valueOf(DateDebutTexteReservation.getValue());
        String date_fin = String.valueOf(DateFinTexteReservation.getValue());
        int nbr_nuits= Integer.parseInt(NombreNuitsTexteReservation.getText());
        int nbre_personnes = Integer.parseInt(NombrePersonnesTexteReservation.getText());

        if (date_debut.isEmpty() || date_fin.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Fill All DATA");
            alert.showAndWait();

        } else {
            RS.Ajouter(id_client,id_log,date_debut,date_fin,nbr_nuits,nbre_personnes);
            try {
            JavaMialUtil.sendMail("ghadasellamy@gmail.com");
        } catch (MessagingException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
               AfficherReservation();
               clean();
        }
    }

    @FXML
    private void ModifierReservation(ActionEvent event) {
        reservation = tableReservation.getSelectionModel().getSelectedItem();
        int id_client= Integer.parseInt(IdClientTexteReservation.getText());
        int id_log = Integer.parseInt(IdLogTexteReservation.getText());
        String date_debut = String.valueOf(DateDebutTexteReservation.getValue());
        String date_fin = String.valueOf(DateFinTexteReservation.getValue());
        int nbr_nuits= Integer.parseInt(NombreNuitsTexteReservation.getText());
        int nbre_personnes = Integer.parseInt(NombrePersonnesTexteReservation.getText());
        RS.modifier(reservation.getId(),id_client,id_log,date_debut,date_fin,nbr_nuits,nbre_personnes);
        AfficherReservation();
        clean();
    }

    private void clean() {
        IdClientTexteReservation.setText(null);
        IdLogTexteReservation.setText(null);
        DateDebutTexteReservation.setValue(null);
        DateFinTexteReservation.setValue(null);
        NombreNuitsTexteReservation.setText(null);
        NombrePersonnesTexteReservation.setText(null);
        
    }
    
    @FXML
    private void SupprimerReservation(ActionEvent event) {
        try {
                                reservation = tableReservation.getSelectionModel().getSelectedItem();
                                RS.Supprimer(reservation.getId());
                                AfficherReservation();
                                
                            } catch (SQLException ex) {
                            }
    }

     public void AfficherLogement(){
        tableLogement.setItems(LS.Afficher());
        IdLogement.setCellValueFactory(new PropertyValueFactory<>("id"));
        IdAdminLogement.setCellValueFactory(new PropertyValueFactory<>("id_admin"));
        NombreEtoileLogement.setCellValueFactory(new PropertyValueFactory<>("nbre_etoiles"));
        NomLogement.setCellValueFactory(new PropertyValueFactory<>("nom"));
        IdCategorieLogement.setCellValueFactory(new PropertyValueFactory<>("id_categorie"));
        PrixparNuitLogement.setCellValueFactory(new PropertyValueFactory<>("prix_nuit"));
        
    }
    @FXML
    private void AjouterLogement(ActionEvent event) {
         connection = DB_CONNECTION.getConnect();
        int id_admin= Integer.parseInt(IdAdminTexteLogement.getText());
        int nbre_etoiles = Integer.parseInt(NombreNuitsTexteLogement.getText());
        String nom = String.valueOf(NomTexteLogement.getText());
        int id_categorie = Integer.parseInt(IdCatégorieTexteLogement.getText());
        double prix_nuit= Double.parseDouble(PrixParNuitsTexteLogement.getText());
       

        if ( nom.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Fill All DATA");
            alert.showAndWait();

        } else {
            LS.Ajouter(id_admin,nbre_etoiles,nom,id_categorie,prix_nuit);
               AfficherLogement();
               cleanLogement();
        }
    }

    @FXML
    private void ModifierLogement(ActionEvent event) {
        logement = tableLogement.getSelectionModel().getSelectedItem();
        int id_admin= Integer.parseInt(IdAdminTexteLogement.getText());
        int nbre_etoiles = Integer.parseInt(NombreNuitsTexteLogement.getText());
        String nom = String.valueOf(NomTexteLogement.getText());
        int id_categorie = Integer.parseInt(IdCatégorieTexteLogement.getText());
        double prix_nuit= Double.parseDouble(PrixParNuitsTexteLogement.getText());
        LS.modifier(logement.getId(),id_admin,nbre_etoiles,nom,id_categorie,prix_nuit);
        AfficherLogement();
        cleanLogement();
    }

    @FXML
    private void SupprimerLogement(ActionEvent event) {
        try {
                                logement = tableLogement.getSelectionModel().getSelectedItem();
                                LS.Supprimer(logement.getId());
                                AfficherLogement();
                                cleanLogement();
                            } catch (SQLException ex) {
                            }
        
    }

    @FXML
    private void AjouterCatégorie(ActionEvent event) {
    }

    @FXML
    private void ModifierCatègorie(ActionEvent event) {
    }

    @FXML
    private void SupprimerCatégorie(ActionEvent event) {
    }

    @FXML
    private void getSelected(MouseEvent event) {
        
        reservation=tableReservation.getSelectionModel().getSelectedItem();
        System.out.println(reservation.toString());
        IdClientTexteReservation.setText(String.valueOf(reservation.getId_Client()));
        IdLogTexteReservation.setText(String.valueOf(reservation.getId_Log()));
        NombreNuitsTexteReservation.setText(String.valueOf(reservation.getNbr_nuits()));
        NombrePersonnesTexteReservation.setText(String.valueOf(reservation.getNbre_personnes()));
    }

    @FXML
    private void getSelectedLogement(MouseEvent event) {
        logement=tableLogement.getSelectionModel().getSelectedItem();
        System.out.println(logement.toString());
        IdAdminTexteLogement.setText(String.valueOf(logement.getId_admin()));
        NombreNuitsTexteLogement.setText(String.valueOf(logement.getNbre_etoiles()));
        NomTexteLogement.setText(String.valueOf(logement.getNom()));
        IdCatégorieTexteLogement.setText(String.valueOf(logement.getId_categorie()));
         PrixParNuitsTexteLogement.setText(String.valueOf(logement.getPrix_nuit()));
    }
    
    private void cleanLogement(){
        IdAdminTexteLogement.setText(null);
        NombreNuitsTexteLogement.setText(null);
        NomTexteLogement.setText(null);
        IdCatégorieTexteLogement.setText(null);
        PrixParNuitsTexteLogement.setText(null);
    }

    

    @FXML
    private void RechercheReservation(KeyEvent event) {
        System.out.println(rechercheTextReservation.getText());
        tableReservation.setItems(RS.Recherche(rechercheTextReservation.getText()));
        idReservation.setCellValueFactory(new PropertyValueFactory<>("id"));
        IdClientReservation.setCellValueFactory(new PropertyValueFactory<>("id_Client"));
        IdLogReservation.setCellValueFactory(new PropertyValueFactory<>("id_Log"));
        dateDebutReservation.setCellValueFactory(new PropertyValueFactory<>("date_debut"));
        dateFinReservation.setCellValueFactory(new PropertyValueFactory<>("date_fin"));
        NombreNuitsReservation.setCellValueFactory(new PropertyValueFactory<>("nbr_nuits"));
        NombrePersonnesReservation.setCellValueFactory(new PropertyValueFactory<>("nbre_personnes"));
    }

    @FXML
    private void FiltreRecherche(KeyEvent event) {
        
        String date11 = String.valueOf(date1.getValue());
        String date22 = String.valueOf(date2.getValue());
        if(!date11.isEmpty() || !date22.isEmpty()){
        tableReservation.setItems(RS.Filtre(date11, date22));
        idReservation.setCellValueFactory(new PropertyValueFactory<>("id"));
        IdClientReservation.setCellValueFactory(new PropertyValueFactory<>("id_Client"));
        IdLogReservation.setCellValueFactory(new PropertyValueFactory<>("id_Log"));
        dateDebutReservation.setCellValueFactory(new PropertyValueFactory<>("date_debut"));
        dateFinReservation.setCellValueFactory(new PropertyValueFactory<>("date_fin"));
        NombreNuitsReservation.setCellValueFactory(new PropertyValueFactory<>("nbr_nuits"));
        NombrePersonnesReservation.setCellValueFactory(new PropertyValueFactory<>("nbre_personnes"));}
    }

    @FXML
    private void TrieParId(ActionEvent event) {
        tableReservation.setItems(RS.TrieId());
        idReservation.setCellValueFactory(new PropertyValueFactory<>("id"));
        IdClientReservation.setCellValueFactory(new PropertyValueFactory<>("id_Client"));
        IdLogReservation.setCellValueFactory(new PropertyValueFactory<>("id_Log"));
        dateDebutReservation.setCellValueFactory(new PropertyValueFactory<>("date_debut"));
        dateFinReservation.setCellValueFactory(new PropertyValueFactory<>("date_fin"));
        NombreNuitsReservation.setCellValueFactory(new PropertyValueFactory<>("nbr_nuits"));
        NombrePersonnesReservation.setCellValueFactory(new PropertyValueFactory<>("nbre_personnes"));
    }

    @FXML
    private void TrieParNbrNuits(ActionEvent event) {
        tableReservation.setItems(RS.TrieNbrNuits());
        idReservation.setCellValueFactory(new PropertyValueFactory<>("id"));
        IdClientReservation.setCellValueFactory(new PropertyValueFactory<>("id_Client"));
        IdLogReservation.setCellValueFactory(new PropertyValueFactory<>("id_Log"));
        dateDebutReservation.setCellValueFactory(new PropertyValueFactory<>("date_debut"));
        dateFinReservation.setCellValueFactory(new PropertyValueFactory<>("date_fin"));
        NombreNuitsReservation.setCellValueFactory(new PropertyValueFactory<>("nbr_nuits"));
        NombrePersonnesReservation.setCellValueFactory(new PropertyValueFactory<>("nbre_personnes"));
    }

    @FXML
    private void TrieParIdLog(ActionEvent event) {
        tableReservation.setItems(RS.TrieIdLog());
        idReservation.setCellValueFactory(new PropertyValueFactory<>("id"));
        IdClientReservation.setCellValueFactory(new PropertyValueFactory<>("id_Client"));
        IdLogReservation.setCellValueFactory(new PropertyValueFactory<>("id_Log"));
        dateDebutReservation.setCellValueFactory(new PropertyValueFactory<>("date_debut"));
        dateFinReservation.setCellValueFactory(new PropertyValueFactory<>("date_fin"));
        NombreNuitsReservation.setCellValueFactory(new PropertyValueFactory<>("nbr_nuits"));
        NombrePersonnesReservation.setCellValueFactory(new PropertyValueFactory<>("nbre_personnes"));
    }

    @FXML
    private void Imprimer(ActionEvent event) throws IOException {
        String path="";
        
        Document document = new Document();
      try
      {
         PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(path+"HelloWorld.pdf"));
         document.open();
          PdfPTable tb1 = new PdfPTable(7);
          tb1.addCell("Identifiant");
          tb1.addCell("IdClient");
          tb1.addCell("IdLog");
          tb1.addCell("Date debut");
          tb1.addCell("Date Fin");
          tb1.addCell("Nombre de nuits");
          tb1.addCell("Nombre de personne");
          ObservableList<Reservation> res = RS.Afficher();
          for (int i = 0; i < res.size(); i++) {
              String id= String.valueOf(res.get(i).getId());
              String idClient= String.valueOf(res.get(i).getId_Client());
              String idLog= String.valueOf(res.get(i).getId_Log());
              String debut= String.valueOf(res.get(i).getDate_debut());
              String fin= String.valueOf(res.get(i).getDate_fin());
              String nbrnuit= String.valueOf(res.get(i).getNbr_nuits());
              String nbrpers= String.valueOf(res.get(i).getNbre_personnes());
              
          tb1.addCell(id);
          tb1.addCell(idClient);
          tb1.addCell(idLog);
          tb1.addCell(debut);
          tb1.addCell(fin);
          tb1.addCell(nbrnuit);
          tb1.addCell(nbrpers);
          }
         document.add(new Paragraph("Liste des Réservations"));
         document.add(tb1);
         document.close();
         writer.close();
        File file=new File("HelloWorld.pdf");
         desktop.open(file);
          System.out.println("javafxapplication1.FXMLDocumentController.Imprimer()");
      } catch (DocumentException e)
      {
         e.printStackTrace();
      } catch (FileNotFoundException e)
      {
         e.printStackTrace();
      }
    }
    
}
