package metier.modele;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import metier.modele.Adherent;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-05-19T16:50:23")
@StaticMetamodel(Equipe.class)
public class Equipe_ { 

    public static volatile SingularAttribute<Equipe, Long> id;
    public static volatile ListAttribute<Equipe, Adherent> adherents;
    public static volatile SingularAttribute<Equipe, Integer> version;

}