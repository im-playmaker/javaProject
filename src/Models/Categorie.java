/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author Asus
 */
public class Categorie {
    private int id;
    private String type;

    public Categorie(int id, String type) {
        this.id = id;
        this.type = type;
    }

    public Categorie(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

   

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    
}
