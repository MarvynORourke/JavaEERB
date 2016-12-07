package newpackage;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Marv
 */
    import java.sql.Connection;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import javax.sql.DataSource;
    import java.sql.PreparedStatement;
    import java.util.ArrayList;
    import java.util.logging.Level;
    import java.util.logging.Logger;
    import javax.servlet.annotation.WebServlet;
    import com.google.gson.*;
import java.sql.Date;
    import java.sql.Statement;
    import java.util.HashMap;
    import java.util.Map;

@WebServlet(name = "SimpleDataAccessObject", urlPatterns = {"/SimpleDataAccessObject"})
    public class SimpleDataAccessObject {
    
        private final DataSource myDataSource;
    
        public SimpleDataAccessObject(DataSource dataSource) {
                    this.myDataSource = dataSource;
            }

        public boolean mailCustomerExist(String mail) throws SQLException{

            boolean resultat = false;
            String sql = "SELECT * FROM CUSTOMER WHERE EMAIL = ?";
            Connection connection = myDataSource.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, mail);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                resultat = true;
            }
            return resultat;
        }

        public boolean identifiantExist(String email, int id) throws SQLException{
            boolean resultat = false; 
            String sql = "SELECT CUSTOMER_ID FROM CUSTOMER WHERE EMAIL = ?";
            Connection connection = myDataSource.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                resultat = (id == rs.getInt(1));
            }
            return resultat;
        } 

        public ArrayList<PurchaseOrder> listPurchaseOrder(int id ) throws SQLException{
            ArrayList<PurchaseOrder> listeAchat = new ArrayList();
            PurchaseOrder po;
            String sql = "SELECT * From PURCHASE_ORDER WHERE CUSTOMER_ID = ?";
            Connection connection = myDataSource.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                po = new PurchaseOrder(rs.getInt("ORDER_NUM"),rs.getInt("QUANTITY"),rs.getInt("SHIPPING_COST"),rs.getInt("PRODUCT_ID"),rs.getDate("SALES_DATE"),rs.getDate("SHIPPING_DATE"),rs.getString("FREIGHT_COMPANY"));
                listeAchat.add(po);
            }
            
            Gson listeAchatJSON = new Gson();
            listeAchatJSON.toJson(listeAchat);
            return listeAchat;
        }

        public boolean addPurchaseOrder(PurchaseOrder po, int id) throws SQLException{
            System.out.println("YAYA CHOUCROUTE ON COMMENCE !!!!!!!");
            String sql = "INSERT INTO PURCHASE_ORDER VALUES(?,?,?,?,?,?,?,?)";
            boolean result = false;
            Connection c = null;
            try{
                c = myDataSource.getConnection();
                //c.setAutoCommit(false);
                PreparedStatement stmt = c.prepareStatement(sql);
                stmt.setInt(1,po.getOrderNum());
                stmt.setInt(2,id);
                stmt.setInt(3,po.getProductID());
                stmt.setInt(4,po.getQuantite());
                stmt.setInt(5,po.getShippingCost());
                stmt.setDate(6, po.getSaleDate());
                stmt.setDate(7,po.getShippingDate());
                stmt.setString(8, po.getFreightCompagny());
                stmt.executeUpdate();
                result = true;
                stmt.close();
                
            }catch(SQLException ex){
                Logger.getLogger(SimpleDataAccessObject.class.getName()).log(Level.SEVERE, null, ex);
                
            }finally{
                c.close();
            }
            return result;
        }

        public boolean removePurchaseOrder(int idPo) throws SQLException{
            String sql = "DELETE FROM PURCHASE_ORDER WHERE ORDER_NUM=?";
            boolean result = false;
            Connection c = null;
            try{
                c = myDataSource.getConnection();
                PreparedStatement stmt = c.prepareStatement(sql);
                stmt.setInt(1,idPo);
                stmt.executeUpdate();
                result = true;
                stmt.close();
            }catch(SQLException ex){
                c.rollback();
                Logger.getLogger(SimpleDataAccessObject.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
                c.close();
            }
        return result;
        }
        
        public boolean modifiatePurchaseOrder(PurchaseOrder po, String ancienOrderNum) throws SQLException{
            String sql = "UPDATE PURCHASE_ORDER " +
                         "SET ORDER_NUM = ?, PRODUCT_ID = ?, QUANTITY = ?, SHIPPING_COST = ?, SALES_DATE = ?, SHIPPING_DATE = ?, FREIGHT_COMPANY = ?" +
                         " WHERE ORDER_NUM = ?";
            
            boolean result = false;
            Connection c = null;
            try{
                c = myDataSource.getConnection();
                PreparedStatement stmt = c.prepareStatement(sql);
                java.sql.Date dateSale = new java.sql.Date(po.getSaleDate().getTime());
                java.sql.Date dateShip = new java.sql.Date(po.getShippingDate().getTime());
                stmt.setInt(1,po.getOrderNum());
                stmt.setInt(2,po.getProductID());
                stmt.setInt(3,po.getQuantite());
                stmt.setInt(4,po.getShippingCost());
                stmt.setDate(5,dateSale);
                stmt.setDate(6,dateShip);
                stmt.setString(7,po.getFreightCompagny());
                stmt.setInt(8,Integer.parseInt(ancienOrderNum));
                stmt.executeUpdate();
                result = true;
                stmt.close();
            }catch(SQLException ex){
                ex.printStackTrace();
            }finally{
                c.close();
            }
        return result;
        }
        
         public Map<String,Integer> getAllPurchaseObject(int idClient) throws SQLException{
            String sql = "SELECT p.DESCRIPTION,SUM(QUANTITY * PURCHASE_COST) as test" +
                    " FROM CUSTOMER c INNER JOIN PURCHASE_ORDER o ON (c.CUSTOMER_ID = o.CUSTOMER_ID)"+
                    " INNER JOIN PRODUCT p ON (o.PRODUCT_ID = p.PRODUCT_ID) WHERE o.CUSTOMER_ID=? GROUP BY p.DESCRIPTION";
            
            ObjectCost oc;
            Map<String,Integer> result = new HashMap<>();
            Connection c = null;
            try{
                c = myDataSource.getConnection();
                PreparedStatement stmt = c.prepareStatement(sql);
                stmt.setInt(1, idClient);
                ResultSet rs = stmt.executeQuery();
                while(rs.next()){
                    oc= new ObjectCost(rs.getString(1),rs.getInt(2));
                    result.put(oc.getNom(),oc.getTotal());
            }
                stmt.close();
            }catch(SQLException ex){
                ex.printStackTrace();
            }finally{
                c.close();
            }
        return result;
        }
         
         public Map<Date,Integer> getAllPurchaseObjectByDate(int idClient) throws SQLException{
            String sql = "SELECT o.SALES_DATE,SUM(QUANTITY) as test FROM CUSTOMER c "+
                    "INNER JOIN PURCHASE_ORDER o ON (c.CUSTOMER_ID = o.CUSTOMER_ID) WHERE o.CUSTOMER_ID=? GROUP BY o.SALES_DATE";
            
            Map<Date,Integer> result = new HashMap<>();
            Connection c = null;
            try{
                c = myDataSource.getConnection();
                PreparedStatement stmt = c.prepareStatement(sql);
                stmt.setInt(1, idClient);
                ResultSet rs = stmt.executeQuery();
                while(rs.next()){
                    result.put(rs.getDate(1),rs.getInt(2));
            }
                stmt.close();
            }catch(SQLException ex){
                ex.printStackTrace();
            }finally{
                c.close();
            }
        return result;
        }
        
         
         public ArrayList<Integer> getAllProduct() throws SQLException {
             String sql = "SELECT PRODUCT_ID FROM PRODUCT";
             ArrayList<Integer>result = new ArrayList<>();
             Connection c = null;
             try{
                 c = myDataSource.getConnection();
                 Statement stmt = c.createStatement();
                 ResultSet rs = stmt.executeQuery(sql);
                 while(rs.next()){
                     result.add(rs.getInt(1));
                 }
                 stmt.close();
             }catch(SQLException ex){
                 ex.printStackTrace();
             }finally{
                 c.close();
             }
             return result;
         }
         
         public boolean enoughtQuantity(int q, int prod_id) throws SQLException{
             String sql =" SELECT QUANTITY_ON_HAND FROM PRODUCT WHERE PRODUCT_ID=?";
             boolean result = false;
             Connection c = null;
             try{
                 c = myDataSource.getConnection();
                 PreparedStatement stmt = c.prepareStatement(sql);
                 stmt.setInt(1, prod_id);
                 ResultSet rs = stmt.executeQuery(sql);
                 while(rs.next()){
                     result = q <= rs.getInt(1);;
                 }
                 stmt.close();
             }catch(SQLException ex){
                 ex.printStackTrace();
             }finally{
                 c.close();
             }
             return result;
         }
                 
                 
}
