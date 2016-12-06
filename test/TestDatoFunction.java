/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
        assertEquals(3, dao.listPurchaseOrder(1).size());
    }
    
     @Test
    public void testAddPurchaseOrder() throws SQLException, ParseException{
        Date d = new Date(1995, 5, 6);
        PurchaseOrder po = new PurchaseOrder(380008,10,7,980001,d,d,"coucou");
        dao.addPurchaseOrder(po,1);
        assertEquals(3,dao.listPurchaseOrder(1).size());
    }
    
    @Test
    public void testRemovePurchaseOrder() throws SQLException{
        dao.removePurchaseOrder(3);
        assertEquals(2,dao.listPurchaseOrder(1).size());
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