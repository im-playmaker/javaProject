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
public class voyage {
    private int id;
    private String destination;
    private String img;
    private String desc;
    private double prix;
    private Date date;
    private int id_promotion;

    public voyage() {
    }

    public voyage(int id, String destination, String img, String desc, double prix, Date date, int id_promotion) {
        this.id = id;
        this.destination = destination;
        this.img = img;
        this.desc = desc;
        this.prix = prix;
        this.date = date;
        this.id_promotion = id_promotion;
    }

    public voyage(String destination, String img, String desc, double prix, Date date, int id_promotion) {
        this.destination = destination;
        this.img = img;
        this.desc = desc;
        this.prix = prix;
        this.date = date;
        this.id_promotion = id_promotion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getId_promotion() {
        return id_promotion;
    }

    public void setId_promotion(int id_promotion) {
        this.id_promotion = id_promotion;
    }

    @Override
    public String toString() {
        return "voyage{" + "id=" + id + ", destination=" + destination + ", img=" + img + ", desc=" + desc + ", prix=" + prix + ", date=" + date + ", id_promotion=" + id_promotion + '}';
    }
    
    
    
}
