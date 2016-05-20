/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import dao.ActiviteDao;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import metier.modele.Activite;
import metier.modele.Adherent;
import metier.modele.DemandeDEvenement;
import metier.service.ServiceException;
import metier.service.ServiceMetier;

/**
 *
 * @author gab
 */
public class ActionSoumettreEvenement extends Action{

    @Override
    public void execute(HttpServletRequest request) {
        String donnees = request.getParameter("date");
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date;
        int id = Integer.parseInt(request.getParameter("idActivite"));
        try {
            date = formatter.parse(donnees);
            Activite activite=ServiceMetier.recupererActiviteParId(id);
            DemandeDEvenement demande = new DemandeDEvenement((Adherent)request.getSession().getAttribute("user"), activite, date);
            ServiceMetier.creerDemandeEvenement(demande);
            request.setAttribute("success", true);
        } catch (Exception ex) {
            Logger.getLogger(ActionSoumettreEvenement.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("success", false);
        }
        
    }
    
}
