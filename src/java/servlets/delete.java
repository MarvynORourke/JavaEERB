/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import newpackage.PurchaseOrder;
import newpackage.SimpleDataAccessObject;

/**
 *
 * @author Romain
 */
@WebServlet(name = "delete", urlPatterns = {"/delete"})
public class delete extends HttpServlet {

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

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {
        SimpleDataAccessObject dao = null;
        try {
            // On récupère les paramètres de la requête
            int ancienOrderNum = Integer.parseInt((String) request.getParameter("orderNum"));
            dao = new SimpleDataAccessObject(getDataSource());
            dao.removePurchaseOrder(ancienOrderNum);
            String jspView; // La page à afficher

            // En fonction des paramètres, on initialise les variables utilisées dans les JSP
            // Et on choisit la vue (page JSP) à afficher
            if (request.getSession(true).getAttribute("mdp") != null) {
                ArrayList<PurchaseOrder> listeCommandes = dao.listPurchaseOrder((Integer) request.getSession(true).getAttribute("mdp"));
                ArrayList<Integer> listeProduits = dao.getAllProduct();
                request.setAttribute("produits", listeProduits);
                request.setAttribute("commandes", listeCommandes);
                jspView = "bonsDeCommandes.jsp";
            } else {
                request.setAttribute("reAuthentificationMessage", "Vous n'êtes pas connecté. Veuillez vous connecter s'il vous plaît.");
                jspView = "reAcceuil.jsp";
            }   // On continue vers la page JSP sélectionnée
            request.getRequestDispatcher(jspView).forward(request, response);
            System.out.println("FIN AFFICHAGE !!!!!!!!");
        } catch (java.lang.NumberFormatException notInt) {
            String jspView;
            request.setAttribute("errorMessage", "Boarf.");
            jspView = "jspErreur.jsp";
            request.getRequestDispatcher(jspView).forward(request, response);
        } finally {
            dao = null;
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(modification.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(add.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(modification.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(add.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
