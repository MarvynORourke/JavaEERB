/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSessionBindingListener;
import javax.sql.DataSource;
import newpackage.PurchaseOrder;
import newpackage.SimpleDataAccessObject;

/**
 * Cette classe est une servlet et est utilisée pour authentifier l'utilisateur.
 *
 * @author rroch
 */
@WebServlet(name = "authentificationController", urlPatterns = {"/authentificationController"})
public class authentificationController extends HttpServlet {

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
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SimpleDataAccessObject dao = null;
        try {
            // On récupère les paramètres de la requête
            String email = request.getParameter("email");
            int mdp = Integer.parseInt(request.getParameter("mdp"));
            String jspView; // La page à afficher

            // Créér le DAO avec sa source de données
            dao = new SimpleDataAccessObject(getDataSource());

            // En fonction des paramètres, on initialise les variables utilisées dans les JSP
            // Et on choisit la vue (page JSP) à afficher
            if (dao.identifiantExist(email, mdp)) {
                ArrayList<PurchaseOrder> listeCommandes = dao.listPurchaseOrder(mdp);
                request.setAttribute("commandes", listeCommandes);
                jspView = "bonsDeCommandes.jsp";
            } else {
                request.setAttribute("errorMessage", "Paramètre p1 incorrect: " + email);
                jspView = "jspErreurAuthentification.jsp";
            }   // On continue vers la page JSP sélectionnée
            HttpSessionBindingListener session = null;
            request.getSession().setAttribute("mdp", mdp);
            request.getRequestDispatcher(jspView).forward(request, response);
        } catch (SQLException exSQL) {
            Logger.getLogger(authentificationController.class.getName()).log(Level.SEVERE, null, exSQL);
        } catch (java.lang.NumberFormatException notInt) {
            String jspView;
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
        processRequest(request, response);
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
        processRequest(request, response);
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
