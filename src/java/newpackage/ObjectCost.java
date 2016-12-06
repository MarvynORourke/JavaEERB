/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newpackage;

/**
 *
 * @author Marv
 */
public class ObjectCost {
    
    private String nom;
    private int total;
    
    
    public ObjectCost(String nom, int total){
        this.nom = nom;
        this.total=total;
    }
    
    public String getNom(){
        return this.nom;
    }
    public int getTotal(){
        return this.total;
    }
    
}
