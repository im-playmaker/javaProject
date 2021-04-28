/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hexclan.entities;

import java.sql.Date;

/**
 *
 * @author 21650
 */
public class User {
    private int id;
    private String nom,prenom,email,password,cin,compteType,tel;
    private int status;
    private Date dateNaiss,created_at; 

    public User(String nom, String prenom, String email, String password, String cin, String tel, Date dateNaiss) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        this.cin = cin;
        this.tel = tel;
        this.dateNaiss = dateNaiss;
    }

    public User() {
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getCompteType() {
        return compteType;
    }

    public void setCompteType(String compteType) {
        this.compteType = compteType;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getDateNaiss() {
        return dateNaiss;
    }

    public void setDateNaiss(Date dateNaiss) {
        this.dateNaiss = dateNaiss;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", password=" + password + ", cin=" + cin + ", compteType=" + compteType + ", tel=" + tel + ", status=" + status + ", dateNaiss=" + dateNaiss + ", created_at=" + created_at + '}';
    }
    
    
    
}
