/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Util.JsonSender;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import metier.modele.Activite;
import metier.modele.Adherent;
import metier.modele.Evenement;

/**
 *
 * @author gspecq
 */
@WebServlet(name = "Controleur", urlPatterns = {"/Controleur"})
public class Controleur extends HttpServlet {
    

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        switch(action)
        {
            case "listeActivite":
                Action actionActivite = new ListeActiviteAction();
                actionActivite.execute(request);
                /*RequestDispatcher rd = request.getRequestDispatcher("/VueListeActivites");
                rd.forward(request, response);      */
                Util.JsonSender.printListeActivites(response.getWriter(), (List<Activite>) request.getAttribute("Activites"));
                break;
                
            case "Connexion":
                Action actionConnexion = new ConnexionAction();
                actionConnexion.execute(request);
                
                if(request.getSession().getAttribute("user") != null)
                {
                    Util.JsonSender.sendResult(response.getWriter(), true);
                    //RequestDispatcher dispatcher= request.getRequestDispatcher("portailClient.html");
                  //  dispatcher.forward(request, response);
                }
                else
                {
                    Util.JsonSender.sendResult(response.getWriter(), false);
                }
                
                break;
            case "soumettreEvenement":
                Action actionSoumettreEvenement = new ActionSoumettreEvenement();
                actionSoumettreEvenement.execute(request);
                if((boolean)request.getAttribute("success"))
                {
                    JsonSender.sendResult(response.getWriter(), true);
                }
                else
                {
                    Util.JsonSender.sendResult(response.getWriter(), false);
                }
                
                break;
            case "Inscription":
                Action actionInscription = new InscriptionAction();
                actionInscription.execute(request);
                Util.JsonSender.sendResult(response.getWriter(), true);
                break;
            case "evenementsClient":
                Action evtClient = new ListeEvenementsClientAction();
                evtClient.execute(request);
                Util.JsonSender.sendResult(response.getWriter(), (List<Evenement>) request.getAttribute("Evenements") );
                break;   
                      
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
