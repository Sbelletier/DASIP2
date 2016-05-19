/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Util.JsonSender;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.servlet.http.HttpServletRequest;
import metier.modele.Adherent;
import static metier.modele.Adherent_.mdp;
import metier.service.ServiceException;
import metier.service.ServiceMetier;

/**
 *
 * @author gab
 */
public class InscriptionAction extends Action{
//    public static Adherent adherentConnecte;
    @Override
    public void execute(HttpServletRequest request) {
        
        try {
            //Adherent(String nom, String prenom, String adresse, String mail, String motDePasse)
            Adherent a = new Adherent(request.getParameter("nom"), request.getParameter("prenom"), 
                    request.getParameter("adress"), request.getParameter("email"), request.getParameter("password"));
            ServiceMetier.ajouterAdherent(a);
            request.setAttribute( "Adherent", a );
        } catch (ServiceException ex) {
            Logger.getLogger(ConnexionAction.class.getName()).log(Level.SEVERE, null, ex.toString());
            request.setAttribute( "Adherent", null );
            
        }
        
    }

//    public Adherent getAdherentConnecte() {
//        return adherentConnecte;
//    }
    
}
