package metier.modele;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import metier.modele.TypeDeLieu;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-05-19T16:50:23")
@StaticMetamodel(Activite.class)
public class Activite_ { 

    public static volatile SingularAttribute<Activite, Integer> id;
    public static volatile SingularAttribute<Activite, Boolean> parEquipe;
    public static volatile SingularAttribute<Activite, Integer> nbParticipants;
    public static volatile SingularAttribute<Activite, String> denomination;
    public static volatile SingularAttribute<Activite, Integer> version;
    public static volatile SingularAttribute<Activite, TypeDeLieu> typeLieu;

}