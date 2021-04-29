/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hexclan.gui;

import hexclan.services.voyageService;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;

/**
 * FXML Controller class
 *
 * @author user
 */
public class StatController implements Initializable {

    @FXML
    private PieChart pie;
    voyageService vs=new voyageService();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        getPourcentageChart2();
        // TODO
    }    
    private void getPourcentageChart2(){
        ObservableList<PieChart.Data> list=FXCollections.observableArrayList();
        HashMap<String,Integer> map=vs.stat1();
        int s=vs.stat2();
        for (Map.Entry mapentry : map.entrySet()) {
            String ch=String.valueOf(mapentry.getValue());
          Double x=Double.valueOf(ch)*100/s;
           list.add(new PieChart.Data((String)mapentry.getKey(), x));
        }
        pie.setData(list);
        
    }
}
