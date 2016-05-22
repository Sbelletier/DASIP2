
import Control.Action;
import Control.DetailEvenementClientAction;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import metier.modele.Evenement;
import metier.modele.Lieu;
import metier.service.ServiceException;
import metier.service.ServiceMetier;

public class SetLieuEvenementAction extends Action {

    @Override
    public void execute(HttpServletRequest request) {
        try {
            int idEvt = (int) request.getAttribute("idEvenement");
            int idLieu = (int) request.getAttribute("idLieu");
            Evenement e = ServiceMetier.recupererEvenementParId( idEvt  );
            Lieu l = ServiceMetier.recupererLieuParId(idLieu);
            ServiceMetier.definirLieuPourEvenement(e, l);
            request.setAttribute( "success", true );
        } catch (ServiceException ex) {
            Logger.getLogger(DetailEvenementClientAction.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute( "success", false );
        }
    }
}