/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Models.Logement;
import Utils.DB_CONNECTION;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Asus
 */
public class LogementService {
    String query = null;
    Connection connection = null ;
    PreparedStatement preparedStatement = null ;
    ResultSet resultSet = null ;
    Logement logement = null;

    public LogementService() {
    }
    
    public ObservableList<Logement> Afficher(){
        ObservableList<Logement> LogementList=FXCollections.observableArrayList();
        try {
        connection = DB_CONNECTION.getConnect();
            query = "SELECT * FROM `logement`";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()){
                LogementList.add(new  Logement(
                        resultSet.getInt(1),
                        resultSet.getInt(2),
                        resultSet.getInt(3),
                        resultSet.getString(4),
                        resultSet.getInt(5),
                        resultSet.getDouble(6)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return LogementList;
    }
    
    public void Ajouter(int id_Admin, int nbre_etoiles, String nom, int id_Categorie, double prix_nuit) {

        try {
        connection = DB_CONNECTION.getConnect();
            query = "INSERT INTO `Reservation`( `id_admin`, `nbre_etoiles`, `nom`, `id_categorie`,  `prix_nuit` ) VALUES (?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id_Admin);
            preparedStatement.setInt(2, nbre_etoiles);
            preparedStatement.setString(3, nom);
            preparedStatement.setInt(4, id_Categorie);
            preparedStatement.setDouble(5, prix_nuit);
            preparedStatement.execute();

        } catch (SQLException ex) {
            
        }

    }
    
    public void modifier(int id,int id_Admin, int nbre_etoiles, String nom, int id_Categorie, double prix_nuit) {

        try {
        connection = DB_CONNECTION.getConnect();
            query = "UPDATE `packabonnement` SET "
                    + "`id_admin`=?,"
                    + "`nbre_etoiles`=?,"
                    + "`nom`=?,"
                    + "`id_categorie`=?,"
                    + "`prix_nuit`=? WHERE id = '"+id+"'";
            preparedStatement = connection.prepareStatement(query);
              preparedStatement.setInt(1, id_Admin);
            preparedStatement.setInt(2, nbre_etoiles);
            preparedStatement.setString(3, nom);
            preparedStatement.setInt(4, id_Categorie);
            preparedStatement.setDouble(5, prix_nuit);
            preparedStatement.execute();

        } catch (SQLException ex) {
            
        }

    }
    
    public void Supprimer(int id) throws SQLException{
        query = "DELETE FROM `Logement` WHERE id  ="+id;
        connection = DB_CONNECTION.getConnect();
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.execute();
    }
}
