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
public class ConnexionAction extends Action{
//    public static Adherent adherentConnecte;
    @Override
    public void execute(HttpServletRequest request) {
        
        try {
            Adherent adherentConnecte = ServiceMetier.identifierAdherentParEmailEtMotDePasse(request.getParameter("email"), request.getParameter("password"));
            request.setAttribute( "Adherents", adherentConnecte );
        } catch (ServiceException ex) {
            Logger.getLogger(ConnexionAction.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute( "Adherents", null );
        }
        
    }

//    public Adherent getAdherentConnecte() {
//        return adherentConnecte;
//    }
    
}
