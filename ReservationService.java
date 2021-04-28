/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Models.Reservation;
import Utils.DB_CONNECTION;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Asus
 */
public class ReservationService {
    String query = null;
    Connection connection = null ;
    PreparedStatement preparedStatement = null ;
    ResultSet resultSet = null ;
    Reservation reservation = null;

    public ReservationService() {
    }
    
    public ObservableList<Reservation> Afficher(){
        ObservableList<Reservation> ReservationList=FXCollections.observableArrayList();
        try {
        connection = DB_CONNECTION.getConnect();
            query = "SELECT * FROM `reservation`";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()){
                ReservationList.add(new  Reservation(
                        resultSet.getInt(1),
                        resultSet.getInt(2),
                        resultSet.getInt(3),
                        resultSet.getDate(4),
                        resultSet.getDate(5),
                        resultSet.getInt(6),
                        resultSet.getInt(7)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ReservationList;
    }
    
    public ObservableList<Reservation> Recherche(String saisie){
        ObservableList<Reservation> ReservationList=FXCollections.observableArrayList();
        try {
        connection = DB_CONNECTION.getConnect();
            query = "SELECT * FROM `reservation` WHERE id LIKE '%"+saisie+"%' or id_client LIKE '%"+saisie+"%' or id_log LIKE '%"+saisie+"%'";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()){
                ReservationList.add(new  Reservation(
                        resultSet.getInt(1),
                        resultSet.getInt(2),
                        resultSet.getInt(3),
                        resultSet.getDate(4),
                        resultSet.getDate(5),
                        resultSet.getInt(6),
                        resultSet.getInt(7)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ReservationList;
    }
    
    public ObservableList<Reservation> Filtre(String date1, String date2){
        ObservableList<Reservation> ReservationList=FXCollections.observableArrayList();
        try {
        connection = DB_CONNECTION.getConnect();
            query = "SELECT * FROM `reservation` WHERE date_debut BETWEEN '"+date1+"' AND '"+date2+"'";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()){
                ReservationList.add(new  Reservation(
                        resultSet.getInt(1),
                        resultSet.getInt(2),
                        resultSet.getInt(3),
                        resultSet.getDate(4),
                        resultSet.getDate(5),
                        resultSet.getInt(6),
                        resultSet.getInt(7)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ReservationList;
    }
    
    public ObservableList<Reservation> TrieId(){
        ObservableList<Reservation> ReservationList=FXCollections.observableArrayList();
        try {
        connection = DB_CONNECTION.getConnect();
            query = "SELECT * FROM `reservation` ORDER BY id";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
         
            while (resultSet.next()){
                ReservationList.add(new  Reservation(
                        resultSet.getInt(1),
                        resultSet.getInt(2),
                        resultSet.getInt(3),
                        resultSet.getDate(4),
                        resultSet.getDate(5),
                        resultSet.getInt(6),
                        resultSet.getInt(7)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ReservationList;
    }
    
    public ObservableList<Reservation> TrieNbrNuits(){
        ObservableList<Reservation> ReservationList=FXCollections.observableArrayList();
        try {
        connection = DB_CONNECTION.getConnect();
            query = "SELECT * FROM `reservation` ORDER BY nbr_nuits";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()){
                ReservationList.add(new  Reservation(
                        resultSet.getInt(1),
                        resultSet.getInt(2),
                        resultSet.getInt(3),
                        resultSet.getDate(4),
                        resultSet.getDate(5),
                        resultSet.getInt(6),
                        resultSet.getInt(7)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ReservationList;
    }
    
    public ObservableList<Reservation> TrieIdLog(){
        ObservableList<Reservation> ReservationList=FXCollections.observableArrayList();
        try {
        connection = DB_CONNECTION.getConnect();
            query = "SELECT * FROM `reservation` ORDER BY id_log";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()){
                ReservationList.add(new  Reservation(
                        resultSet.getInt(1),
                        resultSet.getInt(2),
                        resultSet.getInt(3),
                        resultSet.getDate(4),
                        resultSet.getDate(5),
                        resultSet.getInt(6),
                        resultSet.getInt(7)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ReservationList;
    }
    public void Ajouter(int id_Client, int id_Log, String date_debut, String date_fin, int nbr_nuits, int nbre_personnes) {

        try {
        connection = DB_CONNECTION.getConnect();
            query = "INSERT INTO `Reservation`( `id_client`, `id_log`, `date_debut`, `date_fin`,  `nbr_nuits` ,`nbre_personnes` ) VALUES (?,?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id_Client);
            preparedStatement.setInt(2, id_Log);
            preparedStatement.setString(3, date_debut);
            preparedStatement.setString(4, date_fin);
            preparedStatement.setInt(5, nbr_nuits);
            preparedStatement.setInt(6,nbre_personnes );
            preparedStatement.execute();

        } catch (SQLException ex) {
            
        }

    }
    
    public void modifier(int id, int id_Client, int id_Log, String date_debut, String date_fin, int nbr_nuits, int nbre_personnes) {

        try {
        connection = DB_CONNECTION.getConnect();
            query = "UPDATE `packabonnement` SET "
                    + "`id_client`=?,"
                    + "`id_log`=?,"
                    + "`date_debut`=?,"
                    + "`date_fin`=?,"
                    + "`nbr_nuits`=?,"
                    + "`nbre_personnes`= ? WHERE id = '"+id+"'";
            preparedStatement = connection.prepareStatement(query);
             preparedStatement.setInt(1, id_Client);
            preparedStatement.setInt(2, id_Log);
            preparedStatement.setString(3, date_debut);
            preparedStatement.setString(4, date_fin);
            preparedStatement.setInt(5, nbr_nuits);
            preparedStatement.setInt(6,nbre_personnes );
            preparedStatement.execute();

        } catch (SQLException ex) {
            
        }

    }
    
    public void Supprimer(int id) throws SQLException{
        query = "DELETE FROM `Reservation` WHERE id  ="+id;
        connection = DB_CONNECTION.getConnect();
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.execute();
    }
}
