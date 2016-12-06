package newpackage;


import java.sql.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Marv
 */
public class PurchaseOrder {
    
    private int orderNum ;
    private int quantite;
    private int shippingCost;
    private int productID;
    private Date saleDate;
    private Date shippingDate;
    private String freightCompagny;
    
    
    
    public PurchaseOrder(int orderNum, int quantite, int shippingCost,int productID, Date saleDate,Date shippingDate,String freightCompagny){
        this.orderNum = orderNum;
        this.quantite = quantite;
        this.productID = productID;
        this.shippingCost = shippingCost;
        this.saleDate = saleDate;
        this.shippingDate = shippingDate;
        this.freightCompagny = freightCompagny;
    }
    
    public int getOrderNum(){
        return this.orderNum;
    }
    public int getQuantite(){
        return this.quantite;
    }
    public int getShippingCost(){
        return this.shippingCost;
    }
    public int getProductID(){
        return this.productID;
    }
    public Date getSaleDate(){
        return this.saleDate;
    }
    public Date getShippingDate(){
        return this.shippingDate;
    }
    public String getFreightCompagny(){
        return this.freightCompagny;
    }
}
