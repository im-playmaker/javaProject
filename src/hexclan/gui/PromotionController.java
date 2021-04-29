/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hexclan.gui;

import hexclan.entities.promotion;
import hexclan.services.promotionService;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author user
 */
public class PromotionController implements Initializable {

    @FXML
    private Button ajoutBtn;
    @FXML
    private TextField nom;
    @FXML
    private TextField pourcentage;
    @FXML
    private DatePicker dateD;
    @FXML
    private Button modifBtn;
    @FXML
    private TextField rech;
    @FXML
    private ChoiceBox<String> trie;
    @FXML
    private DatePicker dateF;
    @FXML
    private TableView<promotion> tabP;
    @FXML
    private Button suppBtn;
    @FXML
    private TableColumn<promotion,String> Nom;
    @FXML
    private TableColumn<promotion, Integer> Pourcentage;
    @FXML
    private TableColumn<promotion,Date> DateD;
    @FXML
    private TableColumn<promotion,Date> DateF;
    @FXML
    private TableColumn<promotion, Integer> ID;
    private String ch="";
    private String chT="-";
    private promotionService ps=new promotionService();
    private promotion p=new promotion();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadTable();
        trie.setValue("-");
        trie.getItems().add("-");
        trie.getItems().add("Date Debut(La plus recente)");
        trie.getItems().add("Date Fin(La plus ancienne)");
        trie.setOnAction(this::change);
        init();
    } 
    public void change(ActionEvent a){
       chT=trie.getValue();
       loadTable();
   }
public void loadTable(){
        ObservableList<promotion> tabPromo = FXCollections.observableArrayList(ps.getTablePromotion(ch,chT));
        ID.setCellValueFactory(new PropertyValueFactory<promotion,Integer>("id"));
        Nom.setCellValueFactory(new PropertyValueFactory<promotion,String>("nom"));
        Pourcentage.setCellValueFactory(new PropertyValueFactory<promotion,Integer>("pourcentage"));
        DateD.setCellValueFactory(new PropertyValueFactory<promotion,Date>("dd"));
        DateF.setCellValueFactory(new PropertyValueFactory<promotion,Date>("df"));
        tabP.setItems(tabPromo);
        tabP.setOnMouseClicked(e->loadFormUpdate(tabP.getSelectionModel().getSelectedItem()));
   }
    @FXML
    private void ajouter(ActionEvent event) throws SQLException {
        promotion pr=new promotion(nom.getText(),Integer.valueOf(pourcentage.getText()),Date.valueOf(dateD.getValue()),Date.valueOf(dateF.getValue()));
        if(controleSaisie()){
        ps.ajouterPromotionPst(pr);
        Image img=new Image(getClass().getResourceAsStream("tick.jpg"));
        Notifications notif=Notifications.create()
        .title("Notification")
        .text("promotion ajoute")
        .graphic(new ImageView(img))
        .hideAfter(Duration.seconds(3))
        .position(Pos.BOTTOM_RIGHT);
        notif.darkStyle();
        notif.show();
        loadTable();
        init();}
    }

    @FXML
    private void modifier(ActionEvent event) {
        
        if(controleSaisie()){
            p.setNom(nom.getText());
        p.setPourcentage(Integer.valueOf(pourcentage.getText()));
        p.setDd(Date.valueOf(dateD.getValue()));
        p.setDf(Date.valueOf(dateF.getValue()));
        ps.modifierPromotion(p,p.getId());
        Image img=new Image(getClass().getResourceAsStream("tick.jpg"));
        Notifications notif=Notifications.create()
        .title("Notification")
        .text("promotion modifie")
        .graphic(new ImageView(img))
        .hideAfter(Duration.seconds(3))
        .position(Pos.BOTTOM_RIGHT);
        notif.darkStyle();
        notif.show();
        loadTable();
        init();}
    }

    @FXML
    private void chercher(ActionEvent event) {
        ch=rech.getText();
        loadTable();
    }

    @FXML
    private void supprimer(ActionEvent event) {
        promotion pr=tabP.getSelectionModel().getSelectedItem();
    if (pr==null) 
        {
     JOptionPane.showMessageDialog(null,"Pas de selection");
        }
        else{ 
        int reponse=JOptionPane.showConfirmDialog(null,"Voulez vous vraiment supprimer ce promotion?","confirmer",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
        if(reponse==JOptionPane.YES_OPTION){
        ps.supprimerPromotion(pr.getId());
        loadTable();
        init();}
    } 
    }

    private void loadFormUpdate(promotion selectedItem) {
        p=tabP.getSelectionModel().getSelectedItem();
        dateD.setValue(p.getDd().toLocalDate());
        dateF.setValue(p.getDf().toLocalDate());
        pourcentage.setText(Integer.toString(p.getPourcentage()));
        nom.setText(p.getNom());
        ajoutBtn.setDisable(true);
        modifBtn.setDisable(false);
        suppBtn.setDisable(false);
    }
    private void init() {
        dateD.setValue(LocalDate.now());
        dateF.setValue(LocalDate.now());
        pourcentage.setText("");
        nom.setText("");
        ajoutBtn.setDisable(false);
        modifBtn.setDisable(true);
        suppBtn.setDisable(true);
    }
    private boolean controleSaisie(){
    String pourcent=pourcentage.getText();
    Date dd=Date.valueOf(dateD.getValue());
    Date df=Date.valueOf(dateF.getValue());
    String n=nom.getText();
    String text="";
    boolean test=true;
    if(n.equals("")){
      test=false;
      nom.setText("");
      if(text.equals(""))
          text="champs nom est obligatoire ";
    }   
    if(pourcent.equals("")||(chaineNum(pourcent)==false)){
      test=false;
      pourcentage.setText("");
      if(text.equals(""))
          text="champs pourcentage est un nombre obligatoire ";
    }  
    if(Integer.valueOf(pourcent)>100){
      test=false;
      pourcentage.setText("");
      if(text.equals(""))
          text="champs pourcentage doit etre inferieur a 100 ";
    } 
    if(dateD.getValue().compareTo(dateF.getValue())>=0){
        text="date debut doit etre inferieur a date fin";
        test=false;
        dateD.setValue(LocalDate.now());
        dateF.setValue(LocalDate.now());
        }
    if(!text.equals(""))
        JOptionPane.showMessageDialog(null,text);
   return test;
}
    private boolean chaineNum(String ch){
    return ch.matches("[+-]?\\d*(\\.\\d+)?");
}
}
