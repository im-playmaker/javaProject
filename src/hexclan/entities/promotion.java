/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hexclan.entities;
import java.sql.Date;
/**
 *
 * @author user
 */
public class promotion {
    private int id;
    private String nom;
    private int Pourcentage;
    private Date dd;
    private Date df;

    public promotion(int id, String nom, int Pourcentage, Date dd, Date df) {
        this.id = id;
        this.nom = nom;
        this.Pourcentage = Pourcentage;
        this.dd = dd;
        this.df = df;
    }

    public promotion() {
    }

    public promotion(String nom, int Pourcentage, Date dd, Date df) {
        this.nom = nom;
        this.Pourcentage = Pourcentage;
        this.dd = dd;
        this.df = df;
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

    public int getPourcentage() {
        return Pourcentage;
    }

    public void setPourcentage(int Pourcentage) {
        this.Pourcentage = Pourcentage;
    }

    public Date getDd() {
        return dd;
    }

    public void setDd(Date dd) {
        this.dd = dd;
    }

    public Date getDf() {
        return df;
    }

    public void setDf(Date df) {
        this.df = df;
    }

    @Override
    public String toString() {
        return "promotion{" + "id=" + id + ", nom=" + nom + ", Pourcentage=" + Pourcentage + ", dd=" + dd + ", df=" + df + '}';
    }
    
    
    
}
