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
    import java.sql.Statement;
    import java.util.List;
    import javax.sql.DataSource;
    import java.sql.PreparedStatement;
    import java.util.ArrayList;
    import java.util.logging.Level;
    import java.util.logging.Logger;
    
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
            return listeAchat;
        }

        public boolean addPurchaseOrder(PurchaseOrder po, int id) throws SQLException{
            String sql = "INSERT INTO PURCHASE_ORDER(?,?,?,?,?,?,?,?)";
            boolean result = false;
            Connection c = null;
            try{
                c = myDataSource.getConnection();
                c.setAutoCommit(false);
                PreparedStatement stmt = c.prepareStatement(sql);
                stmt.setInt(1, po.getOrderNum());
                stmt.setInt(2,id);
                stmt.setInt(3,po.getProductID());
                stmt.setInt(4,po.getQuantite());
                stmt.setInt(5,po.getShippingCost());
                stmt.setDate(6, po.getSaleDate());
                stmt.setDate(7,po.getShippingDate());
                stmt.setString(8, po.getFreightCompagny());

                stmt.executeQuery();
                c.commit();
                result = true;

                stmt.close();
                c.close();
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
                c.setAutoCommit(false);
                PreparedStatement stmt = c.prepareStatement(sql);
                stmt.setInt(1,idPo);            
                stmt.executeQuery();
                c.commit();
                result = true;

                stmt.close();
                c.close();
            }catch(SQLException ex){
                Logger.getLogger(SimpleDataAccessObject.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
                c.close();
            }
        return result;
        }

}
