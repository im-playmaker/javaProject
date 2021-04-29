/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hexclan.services;

import hexclan.entities.promotion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.DataSource;

/**
 *
 * @author user
 */
public class promotionService {
      private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;

    private Connection conn;

    public promotionService() {
        conn = DataSource.getInstance().getCnx();
    }

   public void ajouterPromotionPst(promotion p) throws SQLException{
        String req = "insert into promotion (nom,pourcentage,date_debut,datefin) values (?,?,?,?)";

        try {
            pst = conn.prepareStatement(req);
            pst.setString(1,p.getNom() );
            pst.setInt(2,p.getPourcentage());
            pst.setDate(3,p.getDd() );
            pst.setDate(4,p.getDf());
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(voyageService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    

public List <promotion> readAll(int p) {
        String req = "SELECT * FROM `promotion` WHERE SYSDATE() < datefin ";
if(p!=0)
    req+="and pourcentage < '"+p+"'";
        List <promotion> list=new ArrayList<>();
         System.out.println("tessssssssst1");
        try {
            ste = conn.createStatement();
           rs= ste.executeQuery(req);
           while(rs.next()){
               list.add(new promotion(rs.getInt(1),rs.getString(2),rs.getInt(3) ,rs.getDate(4),rs.getDate(5)));
               System.out.println("tessssssssst");
           }

        } catch (SQLException ex) {
            Logger.getLogger(voyageService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
public void supprimerPromotion(int id) {
        String req2 = "update voyage set id_promotion=null WHERE id_promotion=? ";
        String req = "delete from promotion where id=? ";

        try {
            pst = conn.prepareStatement(req2);
            pst.setInt(1, id);
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(voyageService.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            pst = conn.prepareStatement(req);
            pst.setInt(1, id);
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(voyageService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    public void modifierPromotion(promotion p,int id) {
        String req = "update promotion set nom=?, pourcentage=?,date_debut=?,datefin=? where id=? ";

        try {
            pst = conn.prepareStatement(req);
            pst.setString(1, p.getNom());
            pst.setInt(2, p.getPourcentage());
            pst.setDate(3, p.getDd());
            pst.setDate(4, p.getDf());
            pst.setInt(5, id);
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(voyageService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    public ObservableList<promotion> getTablePromotion(String ch,String t) {
        String req = "select id,nom,pourcentage,date_debut,datefin from promotion";
        if(!ch.equals(""))
        req +=" where nom like '"+ch+"%'"; 
        if(!t.equals("-")){
            if(t.equals("Date Debut(La plus recente)"))
         req+=" order by date_debut DESC";
            else
         req+=" order by datefin ASC";       
        }
        ObservableList<promotion> list=FXCollections.observableArrayList();
        try {
            ste = conn.createStatement();
           rs= ste.executeQuery(req);
           while(rs.next()){
               promotion p=new promotion(rs.getInt(1),rs.getString(2), rs.getInt(3),rs.getDate(4),rs.getDate(5));
               list.add(p);
           }

        } catch (SQLException ex) {
            Logger.getLogger(promotionService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
}
