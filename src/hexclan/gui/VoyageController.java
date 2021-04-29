/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hexclan.gui;

import hexclan.entities.promotion;
import hexclan.entities.voyage;
import hexclan.services.promotionService;
import hexclan.services.voyageService;
import java.io.File;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import org.controlsfx.control.Notifications;
/**
 * FXML Controller class
 *
 * @author user
 */
public class VoyageController implements Initializable {

    @FXML
    private Button ajoutBtn;
    @FXML
    private TextField destination;
    @FXML
    private TextArea desc;
    @FXML
    private Button browseBtn;
    @FXML
    private ImageView img;
    @FXML
    private TextField prix;
    @FXML
    private DatePicker date;
    @FXML
    private VBox vb;
    private voyage v=new voyage();
    private voyageService vs=new voyageService();
    private promotion p=new promotion();
    private promotionService ps=new promotionService();
    private int choice =-1;
    @FXML
    private ScrollPane listV;
    @FXML
    private ScrollPane listP;
    @FXML
    private VBox vb2;
    @FXML
    private Button associerBtn;
    @FXML
    private Button annulerBtn;
    @FXML
    private Button modifBtn;
    @FXML
    private Slider slider;
    @FXML
    private Label label;
    private int sliderV=100;
    @FXML
    private TextField rech;
    @FXML
    private ChoiceBox<String> trie;
    private String ch="";
    private String chT="-";
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        init();
        trie.setValue("-");
        trie.getItems().add("-");
        trie.getItems().add("Date (La plus recente)");
        trie.getItems().add("Date (La plus ancienne)");
        trie.setOnAction(this::change);
        loadList();
        initAssocier();
        slider.valueProperty().addListener(new ChangeListener<Number>(){
           @Override
           public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
              sliderV=(int) slider.getValue();
              label.setText("Pourcentage:"+String.valueOf(sliderV));
              chargerM();
           }
       });
    }   
     public void change(ActionEvent a){
       chT=trie.getValue();
       loadList();
   }
    public void init(){
        img.setImage(new Image("file:///C:\\Users\\user\\Desktop\\3eme\\projet\\voyage\\src\\voyage\\joindre.jpg"));
        date.setValue(LocalDate.now());
        prix.setText("");
        desc.setText("");
        destination.setText("");
        ajoutBtn.setVisible(true);
        modifBtn.setVisible(false);
    }
    @FXML
    private void ajouter(ActionEvent event) throws SQLException {
        v.setDate(Date.valueOf(date.getValue().toString()));
        v.setDestination(destination.getText());
        v.setPrix(Double.valueOf(prix.getText()));
        v.setDesc(desc.getText());
        v.setId_promotion(0);
        if(controleSaisie()==true){
        vs.ajouterVoyagePst(v);
        Image img=new Image(getClass().getResourceAsStream("tick.jpg"));
        Notifications notif=Notifications.create()
        .title("Notification")
        .text("Voyage ajoute")
        .graphic(new ImageView(img))
        .hideAfter(Duration.seconds(3))
        .position(Pos.BOTTOM_RIGHT);
        notif.darkStyle();
        notif.show();}
        loadList();
         }

    @FXML
    private void browse(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
                 FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("jpeg files (*.jpg)", "*.jpg","jpeg files (*.png)", "*.png","jpeg files (*.jpeg)", "*.jpeg");
              fileChooser.getExtensionFilters().add(extFilter);
             Window primaryStage = null;
            File file = fileChooser.showOpenDialog(primaryStage);
            if(file==null) return ;
            else{
                v.setImg(file.getPath());
                img.setImage(new Image("file:///"+file.getPath()));}
            
    }
    private void loadList() {
         
        List<voyage> list=vs.readAll(ch,chT);
        vb=new VBox();
        for (int i = 0; i < list.size(); i++){
        HBox h=new HBox();
        h.setPrefWidth(562);
        h.setPrefHeight(81);
        h.setStyle("-fx-background-color :#e6e6e6;");
        
        Label l1=new Label();
        l1.setText("A: "+list.get(i).getDestination());
        l1.setPrefWidth(71);
        l1.setPrefHeight(81);
        l1.setTextAlignment(TextAlignment.CENTER);
        l1.setStyle("-fx-margin-left :50px;");
        Label l3=new Label();  
        if(list.get(i).getId_promotion()!=0){
            int p=vs.getPourcentage(list.get(i).getId_promotion());
            double prix=(list.get(i).getPrix()*(100-p))/100;
            l3.setText(Double.toString(prix));}
        else
        l3.setText(String.valueOf(list.get(i).getPrix())+"DNT");
        l3.setPrefWidth(80);
        l3.setPrefHeight(81);
        l3.setTextAlignment(TextAlignment.CENTER);
        Label l4=new Label();
        l4.setText(String.valueOf(list.get(i).getId()));
        l4.setVisible(false);
        l4.setPrefWidth(7);
        l4.setPrefHeight(7);
        Label l2=new Label();
        Date date=list.get(i).getDate();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String text = df.format(date);
        l2.setText(text);
        l2.setPrefWidth(70);
        l2.setPrefHeight(81);
        l2.setTextAlignment(TextAlignment.CENTER);
        CheckBox c=new CheckBox();
        c.setPrefHeight(81);
        c.setPrefWidth(82);
        c.setDisable(true);
        c.setText("Promotion");
        c.setTextAlignment(TextAlignment.CENTER);
        if(list.get(i).getId_promotion()!=0)
            c.setSelected(true);
        else
            c.setSelected(false);
         Hyperlink hl=new Hyperlink();
          hl.setText("Supprimer");
          hl.setPrefHeight(81);
          hl.setPrefWidth(69);
          hl.setOnAction(e->{
              v.setId(Integer.valueOf(l4.getText()));
              supprimer();
                  }); 
        Hyperlink h2=new Hyperlink();
          h2.setPrefHeight(81);
          h2.setPrefWidth(110);
          if(Date.valueOf(LocalDate.now()).compareTo(list.get(i).getDate())>0)
            h2.setText("Voyage acheve");  
          else{
          h2.setText("Ajouter Promotion");
          h2.setOnAction(e->{
          v.setId(Integer.valueOf(l4.getText()));
        afficherAssocier();
                  }); }
        Hyperlink h3=new Hyperlink();
          h3.setText(">>>");
          h3.setPrefHeight(81);
          h3.setPrefWidth(49);
          h3.setOnAction(e->{
              modifForm(Integer.valueOf(l4.getText()));
                  }); 
        
        h.getChildren().addAll(l1,l2,l3,c,hl,h2,h3,l4);
        
        h.setOnMouseClicked(e->{
         v.setId(Integer.valueOf(l4.getText()));
         releaseChoice(vb);
        h.setStyle("-fx-background-color : #34669a;");
        afficherAssocier();
        });
        h.setId("ligneDemande");
        vb.getChildren().addAll(h);
        }
        listV.setContent(vb);
    }
    private void releaseChoice(VBox v) {
    for (int j = 0; j < v.getChildren().size(); j++)
            v.getChildren().get(j).setStyle("-fx-background-color :#e6e6e6;");
    }
    private void supprimer() {
        if(v.getId()!=-1){
           
           vs.supprimerVoyage(v.getId());
           loadList();
        }       
    }
    private void chargerM() {
         List<promotion> list=ps.readAll(sliderV);
        vb2=new VBox();
        for (int i = 0; i < list.size(); i++){
            System.out.println(list.get(i));
        HBox h=new HBox();
        h.setPrefWidth(567);
        h.setPrefHeight(76);
        h.setStyle("-fx-background-color :#e6e6e6;");
        
        Label l1=new Label();
        l1.setText("Nom: "+list.get(i).getNom());
        l1.setPrefWidth(128);
        l1.setPrefHeight(76);
        l1.setTextAlignment(TextAlignment.CENTER);
        Label l5=new Label();  
        l5.setText(String.valueOf(list.get(i).getPourcentage())+"%");
        l5.setPrefWidth(90);
        l5.setPrefHeight(81);
        l5.setTextAlignment(TextAlignment.CENTER);
        Label l4=new Label();
        l4.setText(String.valueOf(list.get(i).getId()));
        l4.setVisible(false);
        l4.setPrefWidth(7);
        l4.setPrefHeight(7);
        Label l2=new Label();
        Date date=list.get(i).getDd();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String text = df.format(date);
        l2.setText(text);
        l2.setPrefWidth(80);
        l2.setPrefHeight(81);
        l2.setTextAlignment(TextAlignment.CENTER);
        Label l3=new Label();
        Date date2=list.get(i).getDf();
        String text2 = df.format(date2);
        l3.setText(text2);
        l3.setPrefWidth(75);
        l3.setPrefHeight(81);
        l3.setTextAlignment(TextAlignment.CENTER);
        h.getChildren().addAll(l1,l2,l3,l5,l4);
        h.setOnMouseClicked(e->{
        choice=Integer.valueOf(l4.getText());
        releaseChoice(vb);
        h.setStyle("-fx-background-color : #34669a;");
        });
        h.setId("ligneDemande");
        vb2.getChildren().addAll(h);
        }
        listP.setContent(vb2);
    }

    @FXML
    private void associer(ActionEvent event) throws Exception {
        if(choice!=-1){
            vs.associer(choice, v.getId());
            loadList();
            initAssocier();
            List <String> list=vs.getMails();
            for (int i = 0; i < list.size(); i++){
                mailUtil.sendMail(list.get(i),"abassi.seifeddin@esprit.tn","203JMT1815", "Announcement",vs.getMessage( v.getId()), null);
            }
            
        }
            
    }
    
    private void initAssocier() {
        listP.setVisible(false);
        associerBtn.setVisible(false);
        annulerBtn.setVisible(false);
        label.setVisible(false);
        slider.setVisible(false);
    }
    private void afficherAssocier() {
        listP.setVisible(true);
        associerBtn.setVisible(true);
        annulerBtn.setVisible(true);
        label.setVisible(true);
        slider.setVisible(true);
        chargerM();
        
    }
    @FXML
    private void annuler(ActionEvent event) {
        choice=-1;
        v.setId(-1);
        initAssocier();
        releaseChoice(vb);
    }
    private void modifForm(int id) {
        v=vs.getById(id);
        destination.setText(v.getDestination());
        prix.setText(String.valueOf(v.getPrix()));
        date.setValue(v.getDate().toLocalDate());
        desc.setText(v.getDesc());
        img.setImage(new Image("file:///"+v.getImg()));
        ajoutBtn.setVisible(false);
        modifBtn.setVisible(true);
    }

    @FXML
    private void modifier(ActionEvent event) {
        
        if(controleSaisie()==true){
            v.setDate(Date.valueOf(date.getValue().toString()));
        v.setDestination(destination.getText());
        v.setPrix(Double.valueOf(prix.getText()));
        v.setDesc(desc.getText());
        v.setId_promotion(0);
        vs.modifierVoyage(v, v.getId());
        Image img=new Image(getClass().getResourceAsStream("tick.jpg"));
        Notifications notif=Notifications.create()
        .title("Notification")
        .text("Voyage modifie")
        .graphic(new ImageView(img))
        .hideAfter(Duration.seconds(3))
        .position(Pos.BOTTOM_RIGHT);
        notif.darkStyle();
        notif.show();
        init();
     }   
    }
    private boolean controleSaisie() {
        String dest=destination.getText();
        String p=prix.getText();
        Date d=Date.valueOf(date.getValue());
        String des=desc.getText();
        String text="";
        boolean test=true;
        if(dest.equals("")){
        if(text.equals(""))
        text="le champs destination est obligatoire";    
        test=false;
        }
        if(p.equals("")){
        if(text.equals(""))
        text="le champs prix est obligatoire";    
        test=false;
        }
        if(Date.valueOf(LocalDate.now()).compareTo(d)>=0){
        if(text.compareTo("")==0)
        text="date doit etre superieur a la date actuelle";
        date.setValue(LocalDate.now());
        test=false;
        }
        if(des.equals("")){
        if(text.equals(""))
        text="le champs description est obligatoire";    
        test=false;
        }
        if(test==false)
            JOptionPane.showMessageDialog(null,text);
        return test;
    }

    @FXML
    private void chercher(ActionEvent event) {
        ch=rech.getText();
        loadList();
    }
}
