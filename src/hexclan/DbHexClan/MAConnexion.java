/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hexclan.DbHexClan;

import java.sql.*;


/**
 *
 * @author 21650
 */
public class MAConnexion {
    public String url="jdbc:mysql://localhost:3306/hexclan";
    public String user="root";
    public String pwd="";
    
    private Connection cnx;
    private static MAConnexion ct;
    
    private MAConnexion(){
        try {
            cnx = DriverManager.getConnection(url,user,pwd);
            System.out.println("Connexion etablie");
        } catch (SQLException ex) {
            System.out.println("Probleme de connexion : " + ex.getMessage());
        }
        
    }
    
    public static MAConnexion getInstance(){
        if (ct==null)
            ct = new MAConnexion();
        return ct;
    }

    public Connection getCnx() {
        return cnx;
    }
    
    
    
    
}
