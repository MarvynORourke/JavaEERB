/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import javax.sql.DataSource;
import newpackage.PurchaseOrder;
import newpackage.SimpleDataAccessObject;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Marv
 */
public class TestDatoFunction {
	private SimpleDataAccessObject dao; // l'objet à tester
        private static DataSource ds;
        String mail = "jumboeagle@example.com";
    public TestDatoFunction() throws SQLException {
        ds =  getDataSource();
        dao = new SimpleDataAccessObject(ds);

    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {

    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void testMailExisting() throws SQLException{
        assertEquals(true, dao.mailCustomerExist(mail));
        assertEquals(false, dao.mailCustomerExist("coucou"));

    }
    
    @Test
    public void testIdentifiantExist() throws SQLException{
        assertEquals(true, dao.identifiantExist(mail,1));
        assertEquals(false, dao.identifiantExist(mail,5));

    }
    
    @Test
    public void testListPurchaseOrder() throws SQLException{
        assertEquals(2, dao.listPurchaseOrder(1).size());
    }
    
     @Test
    public void testAddPurchaseOrder() throws SQLException, ParseException{
        Date d = new Date(1995, 5, 6);
        PurchaseOrder po = new PurchaseOrder(380009,10,7,980001,d,d,"coucou");
        System.out.println(po.getProductID());
        dao.addPurchaseOrder(po,1);
        assertEquals(3,dao.listPurchaseOrder(1).size());
        dao.removePurchaseOrder(po.getOrderNum());
    }
    
    @Test
    public void testRemovePurchaseOrder() throws SQLException{
        dao.removePurchaseOrder(3);
        assertEquals(2,dao.listPurchaseOrder(1).size());
    }
    
    @Test
    public void testModifyPurchaseOrder() throws SQLException{
        Date d = new Date(1995, 5, 6);
        PurchaseOrder po = new PurchaseOrder(380010,36,7,980001,d,d,"TAMER");
        dao.modifiatePurchaseOrder(po,"10398006");
        assertEquals(36,dao.listPurchaseOrder(36).get(0).getQuantite());
        assertEquals(7,dao.listPurchaseOrder(36).get(0).getShippingCost());
    }
    @Test
    public void testGetAllPurchaseOrder() throws SQLException{
        ArrayList<Integer> a = dao.getAllProduct();
        assertEquals(30,a.size());
    }
    @Test
    public void testEnoughtQuantity() throws SQLException{
        assertEquals(false,dao.enoughtQuantity(1000000000, 980001));
        assertEquals(true,dao.enoughtQuantity(1, 980001));
    }
    @Test
    public void testNumPurchaseExist() throws SQLException{
        assertEquals(false,dao.numPurchaseExist(10398001));
        assertEquals(true,dao.numPurchaseExist(4));

    }
    
 
    

    // TODO add TestDatoFunction methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    public DataSource getDataSource() throws SQLException {
		org.apache.derby.jdbc.ClientDataSource ds = new org.apache.derby.jdbc.ClientDataSource();
		ds.setDatabaseName("sample");
		ds.setUser("app");
		ds.setPassword("app");
		// The host on which Network Server is running
		ds.setServerName("localhost");
		// port on which Network Server is listening
		ds.setPortNumber(1527);
		return ds;
	}
}
