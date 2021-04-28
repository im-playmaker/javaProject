
package hexclan.services;

import hexclan.DbHexClan.MAConnexion;
import hexclan.entities.User;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserService {
    Connection cnx;
    PreparedStatement ste;
    MAConnexion mc = MAConnexion.getInstance();
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());

    public UserService() {
        cnx = MAConnexion.getInstance().getCnx();
    }
    
    public void AddUser(User p){
        try {
            String sql="insert into user(nom,prenom,email,password,num_tel,cin,status,compte_type,date_naiss,created_at)"+"Value(?,?,?,?,?,?,?,?,?,?)";
            ste =cnx.prepareStatement(sql);
            ste.setString(1, p.getNom());
            ste.setString(2, p.getPrenom());
            ste.setString(3, p.getEmail());
            ste.setString(4, p.getTel());
            ste.setString(5, p.getPassword());
            ste.setString(6, p.getCin());
            ste.setString(7, "0");
            ste.setString(8, "user");
            ste.setString(9, date.toString());
            ste.setString(10, date.toString());
            ste.executeUpdate();
            System.out.println("User ajoutéé");
        } catch (SQLException ex) {
            System.out.println("AddUser Problem : "+ex.getMessage());
        }
    }
    
    public List<User> showUsers(){
        List<User> listePersonne = new ArrayList<>();
        try {
            String sql ="Select * from user";
            ste = cnx.prepareStatement(sql);
            ResultSet rs= ste.executeQuery();
            while (rs.next()){
                User u = new User();
                u.setId(rs.getInt("id"));
                u.setCin(rs.getString("cin"));
                u.setNom(rs.getString("nom"));
                u.setPrenom(rs.getString("prenom"));
                u.setEmail(rs.getString("email"));
                u.setPassword(rs.getString("password"));
                u.setCompteType(rs.getString("compte_type"));
                u.setDateNaiss(rs.getDate("date_naiss"));
                u.setCreated_at(rs.getDate("created_at"));
                u.setTel(rs.getString("num_tel"));
                u.setStatus(rs.getInt("status"));
                
                listePersonne.add(u);
            }
           
        } catch (SQLException ex) {
            System.out.println("showUser problem : "+ex.getMessage());
        }
        return listePersonne ;
    }
     
    public List<User> Compte(String active){
     
            List<User> listePersonne = new ArrayList<>();
            
            String sql ="Select * from user where status='"+active+"'";
        try {
            ste = cnx.prepareStatement(sql);
            ResultSet rs;
       
            rs = ste.executeQuery();
       
            while (rs.next()){
                User u = new User();
                u.setId(rs.getInt("id"));
                u.setCin(rs.getString("cin"));
                u.setNom(rs.getString("nom"));
                u.setPrenom(rs.getString("prenom"));
                u.setEmail(rs.getString("email"));
                u.setPassword(rs.getString("password"));
                u.setCompteType(rs.getString("compte_type"));
                u.setDateNaiss(rs.getDate("date_naiss"));
                u.setCreated_at(rs.getDate("created_at"));
                u.setTel(rs.getString("num_tel"));
                u.setStatus(rs.getInt("status"));
                
                listePersonne.add(u);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
            return listePersonne;
            }
            
            
       
  
    
    public User findUser(String email,String password){
         User u = new User();
         String sql ="Select * from user where email='"+email+"' and password='"+password+"'";
            
        try {
        Statement  ste=cnx.createStatement();
            ResultSet rs=ste.executeQuery(sql);
            if(rs.first()){
           
                u.setId(rs.getInt("id"));
                u.setCin(rs.getString("cin"));
                u.setNom(rs.getString("nom"));
                u.setPrenom(rs.getString("prenom"));
                u.setEmail(rs.getString("email"));
                u.setPassword(rs.getString("password"));
                u.setCompteType(rs.getString("compte_type"));
                u.setDateNaiss(rs.getDate("date_naiss"));
                u.setCreated_at(rs.getDate("created_at"));
                u.setTel(rs.getString("num_tel"));
                u.setStatus(rs.getInt("status"));
                System.out.println(u.toString());
                return u;
        }
        } catch (SQLException ex) {
            System.out.println("findUser Problem "+ex.getMessage());
        }
        return null;
    }
    
    
    public String findUserPass(String Email){
       String sql = "Select password from user where email='"+Email+"'";
       Statement ste;
       try {
            ste=cnx.createStatement();
            ResultSet resultat=ste.executeQuery(sql);
            if(resultat.first())
                return resultat.getString(1);
            
            
         }catch (SQLException ex) {
            System.out.println("probleme find User by EMail"+ex.getMessage());
        }
       return "u have no account";
    }
    
     public boolean UpdateUser(User u){
           System.out.println(u.toString());
         String sql ="Update user set nom ='"+u.getNom()+"',prenom='"+u.getPrenom()+"',email='"+u.getEmail()+"',password='"+u.getPassword()+"',num_tel='"+u.getTel()+"',date_naiss='"+u.getDateNaiss()+"',cin='"+u.getCin()+"' where Id='"+u.getId()+"'";
    
        try {
            PreparedStatement ste=cnx.prepareStatement(sql);
            ste.executeUpdate();
           
            
        } catch (SQLException ex) {
            System.out.println("probleme de modification : "+ex.getMessage());
            return false;
        } return true;
    }
     
     public boolean checkemail(String email){
         String sql="select * from user where email='"+email+"'";
         Statement ste;
           try {
            ste=cnx.createStatement();
            ResultSet resultat=ste.executeQuery(sql);
            if(resultat.first())
                return true;
                
            
            
         }catch (SQLException ex) {
            System.out.println("checkemail problem : "+ex.getMessage());
        }
           return false;
     }
     
     public boolean checkcin(String cin){
         
         String sql="select * from user where cin='"+cin+"'";
         Statement ste;
           try {
            ste=cnx.createStatement();
            ResultSet resultat=ste.executeQuery(sql);
            if(resultat.first())
                return true;
                            
         }catch (SQLException ex) {
            System.out.println("checkcin problem : "+ex.getMessage());
        }
           return false;
     }
     
     public boolean updateStatus(String cin,int i){
       String sql ="Update user set status ='"+i+"' where cin ='"+cin+"'";
         try {
            PreparedStatement ste=cnx.prepareStatement(sql);
            ste.executeUpdate();
           
            
        } catch (SQLException ex) {
            System.out.println("probleme de modification : "+ex.getMessage());
            return false;
        } return true;
    }
     
     
     
     
      public boolean verifValue(User c){
            return !(c.getCin().length()==0 | c.getNom().length()==0 | c.getPrenom().length()==0 | c.getEmail().length()==0 |c.getTel().length()==0 |c.getPassword().length()==0);
                
            
             
    }
      
      public String verifMail(String email){
            String msg="";
             String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
 
            Pattern pattern = Pattern.compile(regex);
 
        
            Matcher matcher = pattern.matcher(email);
            if(!matcher.matches()){
                msg=(" e-mail : exemple a@b.com");
            }
        return msg;
             
      }
      
        public String verifCin(String cin){
        String msg="";
 
        String regex = "^[0-9]{8}$";
 
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(cin);
       if(!matcher.matches()){
                msg=(" Cin : 8 nombres");
        }
     return msg;
       }
        
        public String verifTel(String num){
         String msg="";
 
        String regex = "^[0-9]{8}$";
 
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(num);
       if(!matcher.matches()){
                msg=(" numero Télephone exemple  : 52557460");
        }
         return msg;
       }
}
