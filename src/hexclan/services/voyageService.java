/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hexclan.services;

import hexclan.entities.voyage;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.DataSource;

/**
 *
 * @author user
 */
public class voyageService {
       private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;

    private Connection conn;

    public voyageService() {
        conn = DataSource.getInstance().getCnx();
    }

   public void ajouterVoyagePst(voyage v) throws SQLException{
        String req = "insert into voyage (destination,image_destination,description,date_voyage,prix) values (?,?,?,?,?)";

        try {
            pst = conn.prepareStatement(req);
            pst.setString(1, v.getDestination());
            pst.setString(2, v.getImg());
            pst.setString(3, v.getDesc());
            pst.setDate(4, v.getDate());
            pst.setDouble(5, v.getPrix());
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(voyageService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    

public List <voyage> readAll(String ch,String t) {
        String req = "select * from voyage ";
        if(!ch.equals(""))
        req +=" where (destination like '"+ch+"%') ";
        if(!t.equals("-")){
            if(t.equals("Date (La plus recente)"))
         req+="order by date_voyage DESC";
            else
         req+="order by date_voyage ASC";}
        List <voyage> list=new ArrayList<>();
        try {
            ste = conn.createStatement();
           rs= ste.executeQuery(req);
           while(rs.next()){
               list.add(new voyage(rs.getInt(1), rs.getString(2),rs.getString(3), rs.getString(4),rs.getDouble(6),rs.getDate(5), rs.getInt(7)));
           }

        } catch (SQLException ex) {
            Logger.getLogger(voyageService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
public void supprimerVoyage(int id) {
        String req = "delete from voyage where id=? ";

        try {
            pst = conn.prepareStatement(req);
            pst.setInt(1, id);
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(voyageService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    public void modifierVoyage(voyage v,int id) {
        String req = "update voyage set destination=?, image_destination=?,description=?,date_voyage=?,prix=? where id=? ";

        try {
            pst = conn.prepareStatement(req);
            pst.setString(1, v.getDestination());
            pst.setString(2, v.getImg());
            pst.setString(3, v.getDesc());
            pst.setDate(4, v.getDate());
            pst.setDouble(5, v.getPrix());
            pst.setInt(6, id);
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(voyageService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    public void associer(int idp,int id) {
        String req = "update voyage set id_promotion=? where id=? ";

        try {
            pst = conn.prepareStatement(req);
            pst.setInt(1, idp);
            pst.setInt(2, id);
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(voyageService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public int getPourcentage(int id) {
        String req = "select pourcentage from promotion where id='"+id+"'";
        int p=0;
        try {
            ste = conn.createStatement();
           rs= ste.executeQuery(req);
           while(rs.next()){
           p=rs.getInt(1);
           }

        } catch (SQLException ex) {
            Logger.getLogger(voyageService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p;
    }
public List <String> getMails() {
        String req = "select email from user ";
        List <String> list=new ArrayList<>();
        try {
            ste = conn.createStatement();
           rs= ste.executeQuery(req);
           while(rs.next()){
           list.add(rs.getString(1));
           }

        } catch (SQLException ex) {
            Logger.getLogger(voyageService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
public String getMessage(int id) {
        String req = "select v.destination,v.date_voyage,v.prix,p.pourcentage,p.date_debut,p.datefin from voyage v inner join promotion p on v.id_promotion=p.id where v.id='"+id+"'";
        String msg="";
        try {
            ste = conn.createStatement();
           rs= ste.executeQuery(req);
           while(rs.next()){
           msg="annoce de promotion pour notre voyage programme le "+rs.getDate(2).toString()+" a "+rs.getString(1)+" qui a pour prix "+rs.getDouble(3)+" va avoir une promotion de "+rs.getInt(4)+"% de "+rs.getDate(5).toString()+" jusqu a "+rs.getDate(6).toString();
           }

        } catch (SQLException ex) {
            Logger.getLogger(voyageService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return msg;
    }
    public voyage getById(int id) {
        String req = "select * from voyage where id='"+id+"'";

        voyage v=new voyage();
        try {
            ste = conn.createStatement();
           rs= ste.executeQuery(req);
           while(rs.next()){
               v.setId(rs.getInt(1));
               v.setDestination(rs.getString(2));
               v.setImg(rs.getString(3));
               v.setDesc(rs.getString(4));
               v.setDate(rs.getDate(5));
               v.setPrix(rs.getDouble(6));
               v.setId_promotion(rs.getInt(7));
                    
           }

        } catch (SQLException ex) {
            Logger.getLogger(voyageService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return v;
    }
    public HashMap<String,Integer> stat1() {
       String req = "SELECT destination,count(*) from voyage group by(destination) limit 5";
        HashMap<String,Integer> list=new HashMap<>();
        try {
            ste = conn.createStatement();
           rs= ste.executeQuery(req);
           while(rs.next()){
              list.put(rs.getString(1),rs.getInt(2));
           }

        } catch (SQLException ex) {
            Logger.getLogger(voyageService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
public int stat2() {
        String req = "SELECT count(*) from voyage";
        int s=0;
        try {
            ste = conn.createStatement();
           rs= ste.executeQuery(req);
           while(rs.next()){
              s=rs.getInt(1);
           }

        } catch (SQLException ex) {
            Logger.getLogger(voyageService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s;
    }
}
