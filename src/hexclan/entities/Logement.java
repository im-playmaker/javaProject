/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hexclan.entities;

/**
 *
 * @author Asus
 */
public class Logement {
    private int id;
    private int id_admin;
    private int nbre_etoiles;
    private String nom;
    private int id_categorie;
    private double prix_nuit;

    public Logement(int id, int id_admin, int nbre_etoiles, String nom, int id_categorie, double prix_nuit) {
        this.id = id;
        this.id_admin = id_admin;
        this.nbre_etoiles = nbre_etoiles;
        this.nom = nom;
        this.id_categorie = id_categorie;
        this.prix_nuit = prix_nuit;
    }

    public Logement(int id_admin, int nbre_etoiles, String nom, int id_categorie, double prix_nuit) {
        this.id_admin = id_admin;
        this.nbre_etoiles = nbre_etoiles;
        this.nom = nom;
        this.id_categorie = id_categorie;
        this.prix_nuit = prix_nuit;
    }
    

    public int getId() {
        return id;
    }

   

    public int getId_admin() {
        return id_admin;
    }

    public void setId_admin(int id_admin) {
        this.id_admin = id_admin;
    }

    public int getNbre_etoiles() {
        return nbre_etoiles;
    }

    public void setNbre_etoiles(int nbre_etoiles) {
        this.nbre_etoiles = nbre_etoiles;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getId_categorie() {
        return id_categorie;
    }

    public void setId_categorie(int id_categorie) {
        this.id_categorie = id_categorie;
    }

    public double getPrix_nuit() {
        return prix_nuit;
    }

    public void setPrix_nuit(double prix_nuit) {
        this.prix_nuit = prix_nuit;
    }
    
}
