/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.util.Date;

/**
 *
 * @author Asus
 */
public class Reservation {
    
    private int id;
    private int id_Client;
    private int id_Log;
    private Date date_debut;
    private Date date_fin;
    private int nbr_nuits;
    private int nbre_personnes;

    public Reservation(int id, int id_Client, int id_Log, Date date_debut, Date date_fin, int nbr_nuits, int nbre_personnes) {
        this.id = id;
        this.id_Client = id_Client;
        this.id_Log = id_Log;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.nbr_nuits = nbr_nuits;
        this.nbre_personnes = nbre_personnes;
    }

    public Reservation(int id_Client, int id_Log, Date date_debut, Date date_fin, int nbr_nuits, int nbre_personnes) {
        this.id_Client = id_Client;
        this.id_Log = id_Log;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.nbr_nuits = nbr_nuits;
        this.nbre_personnes = nbre_personnes;
    }

    public int getId() {
        return id;
    }

    

    public int getId_Client() {
        return id_Client;
    }

    public void setId_Client(int id_Client) {
        this.id_Client = id_Client;
    }

    public int getId_Log() {
        return id_Log;
    }

    public void setId_Log(int id_Log) {
        this.id_Log = id_Log;
    }

    public Date getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(Date date_debut) {
        this.date_debut = date_debut;
    }

    public Date getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(Date date_fin) {
        this.date_fin = date_fin;
    }

    public int getNbr_nuits() {
        return nbr_nuits;
    }

    public void setNbr_nuits(int nbr_nuits) {
        this.nbr_nuits = nbr_nuits;
    }

    public int getNbre_personnes() {
        return nbre_personnes;
    }

    public void setNbre_personnes(int nbre_personnes) {
        this.nbre_personnes = nbre_personnes;
    }
    
    
    
    
}
