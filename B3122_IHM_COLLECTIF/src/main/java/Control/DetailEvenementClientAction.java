/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import metier.modele.Activite;
import metier.modele.Adherent;
import metier.modele.Evenement;
import metier.service.ServiceException;
import metier.service.ServiceMetier;

/**
 *
 * @author gspecq
 */
public class DetailEvenementClientAction extends Action {

    @Override
    public void execute(HttpServletRequest request) {
        try {
            int id = (int) request.getAttribute("id");
            Evenement e = ServiceMetier.recupererEvenementParId( id  );
            request.setAttribute( "Evenement", e );
        } catch (ServiceException ex) {
            Logger.getLogger(DetailEvenementClientAction.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute( "Evenement", null );
        }
    }
}
