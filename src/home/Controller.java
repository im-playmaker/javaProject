package home;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private VBox pnItems = null;
    @FXML
    private Button btnOverview;

    @FXML
    private Button btnOrders;

    @FXML
    private Button btnCustomers;

    @FXML
    private Button btnMenus;

    @FXML
    private Button btnPackages;

    @FXML
    private Button btnSettings;

    @FXML
    private Button btnSignout;

    @FXML
    private Pane pnlCustomer;

    @FXML
    private Pane pnlOrders;

    @FXML
    private Pane pnlOverview;

    @FXML
    private Pane pnlMenus;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Node[] nodes = new Node[10];
        
            try {
                
                nodes[1] = FXMLLoader.load(getClass().getResource("ShowPersonnes.fxml"));

                //give the items some effect

                nodes[1].setOnMouseEntered(event -> {
                    nodes[1].setStyle("-fx-background-color : #0A0E3F");
                });
                nodes[1].setOnMouseExited(event -> {
                    nodes[1].setStyle("-fx-background-color : #02030A");
                });
                pnItems.getChildren().add(nodes[1]);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    


    @FXML
    public void handleClicks(ActionEvent actionEvent) {
        if (actionEvent.getSource() == btnCustomers) {
            pnlCustomer.setStyle("-fx-background-color : #1620A1");
            pnlCustomer.toFront();
             Node[] nodes = new Node[10];
        
            try {
                
                nodes[1] = FXMLLoader.load(getClass().getResource("ShowPersonnes.fxml"));

                //give the items some effect

                nodes[1].setOnMouseEntered(event -> {
                    nodes[1].setStyle("-fx-background-color : #0A0E3F");
                });
                nodes[1].setOnMouseExited(event -> {
                    nodes[1].setStyle("-fx-background-color : #02030A");
                });
                pnlCustomer.getChildren().add(nodes[1]);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (actionEvent.getSource() == btnMenus) {
            pnlMenus.setStyle("-fx-background-color : #53639F");
            pnlMenus.toFront();
        }
        if (actionEvent.getSource() == btnOverview) {
            pnlOverview.setStyle("-fx-background-color : #02030A");
            pnlOverview.toFront();
        }
        if(actionEvent.getSource()==btnOrders)
        {
            pnlOrders.setStyle("-fx-background-color : #464F67");
            pnlOrders.toFront();
        }
    }
}
