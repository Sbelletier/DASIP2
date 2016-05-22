/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import metier.modele.Activite;
import metier.modele.Adherent;
import metier.modele.Equipe;
import metier.modele.Evenement;
import metier.modele.Lieu;
import metier.service.ServiceException;
import metier.service.ServiceMetier;

/**
 *
 * @author gspecq
 */
public class JsonSender {
    public static void printListeActivites(PrintWriter out, List<Activite> l)
    {
        JsonArray jlist = new JsonArray();
        for(Activite a : l){
            JsonObject jact = new JsonObject();
            jact.addProperty("nom", a.getDenomination() );
            jact.addProperty("nbParticipants", a.getNbParticipants() );
            jact.addProperty("id", a.getId());
            jact.addProperty("parEquipe",a.isParEquipe());
            jlist.add(jact);
        }
        JsonObject container = new JsonObject();
        container.add("activites",jlist);
        
        //ecriture
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String result = gson.toJson(container);
        out.println(result);
    }
    
    public static void sendResult(PrintWriter out, boolean a)
    {
     
        JsonObject jact = new JsonObject();
        
            jact.addProperty("resultat", a);
          
            //        jact.addProperty("nom", a.getNom());
//        jact.addProperty("prenom", a.getPrenom() );
//        jact.addProperty("id", a.getId());
//        jact.addProperty("latitude",a.getLatitude());
//        jact.addProperty("longitude",a.getLongitude());
        

        JsonObject container = new JsonObject();
        container.add("resultat",jact);
        
        //ecriture
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String result = gson.toJson(container);
        out.println(result);
    }
    
    public static void sendResult(PrintWriter out, List<Evenement> l)
    {
        
        
        JsonArray jlist = new JsonArray();
        for(Evenement e:l)
        {
            JsonObject jact = new JsonObject();
            jact.addProperty("nomActivite", e.getActivite().getDenomination() );
            Date d = e.getDateEvenement();
            if(d == null){
                jact.addProperty("date","-");
                jact.addProperty("etat","-");
            }
            else{
                jact.addProperty("date", d.toString() );
                jact.addProperty("etat","OK");
            }
            jact.addProperty("lieu", e.getLieuEvenement().getDenomination());
            jlist.add(jact);
        }
        JsonObject container = new JsonObject();
        container.add("evenements",jlist);
        //ecriture
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String result = gson.toJson(container);
        out.println(result);
    }
    
    public static void sendEvenementDetailClient(PrintWriter out, Evenement e)
    {
        //l'evenement en lui même
        JsonObject Jevt = new JsonObject();
        Jevt.addProperty("id", e.getId() );
        Jevt.addProperty("date", e.getDateEvenement().toString() );
        List<Equipe> leqp = e.getEquipes() ;
        JsonArray Jeqp = new JsonArray();
        int i = 1;
        for(Equipe eq:leqp)
        {
            JsonObject Je = new JsonObject();
            Je.addProperty("numero", i);
            i = i+1;
            JsonArray Jadh = new JsonArray();
            List<Adherent> adh = eq.getAdherents();
            for( Adherent a:adh)
            {
                JsonObject Ja = new JsonObject();
                Ja.addProperty("nom", a.getNom() );
                Ja.addProperty("prenom", a.getPrenom() );
                Jadh.add(Ja);
            }
            Je.add("membres",Jadh);
            Jeqp.add(Je);
        }
        Jevt.add("equipes", Jeqp);
        // l'activité
        JsonObject Jact = new JsonObject();
        Activite act = e.getActivite();
        Jact.addProperty("denomination", act.getDenomination());
        Jact.addProperty("parEquipe", act.isParEquipe());
        Jact.addProperty("nbParticipants", act.getNbParticipants());
        // le lieu
        JsonObject Jlieu = new JsonObject();
        Lieu lieu = e.getLieuEvenement();
        Jlieu.addProperty("denomination", lieu.getDenomination());
        Jlieu.addProperty("adress", lieu.getAdresse());
        Jlieu.addProperty("description", lieu.getDescription());
        Jlieu.addProperty("lat",lieu.getLatitude());
        Jlieu.addProperty("lng",lieu.getLongitude());
        Jlieu.addProperty("type",lieu.getTypeLieu().toString());
        //fusion
        JsonObject container = new JsonObject();
        container.add("evenement",Jevt);
        container.add("activite", Jact);
        container.add("lieu", Jlieu);
        //on ecrit
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String result = gson.toJson(container);
        out.println(result);
    }
    
    public static void sendEvenementsManager(PrintWriter out, List<Evenement> l)
    {
        JsonArray jlist = new JsonArray();
        for(Evenement e:l)
        {
            JsonObject jact = new JsonObject();
            jact.addProperty("nomActivite", e.getActivite().getDenomination() );
            Date d = e.getDateEvenement();
            if(d == null){
                jact.addProperty("date","-");
            }
            else{
                jact.addProperty("date", d.toString() );
            }
            jact.addProperty("lieu", "a definir");
            jlist.add(jact);
        }
        JsonObject container = new JsonObject();
        container.add("evenements",jlist);
        //ecriture
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String result = gson.toJson(container);
        out.println(result);
    }
    
    public static void sendLieuxPourEvenement(PrintWriter out, Evenement e)
    {
        //evenement
        JsonObject Jevt = new JsonObject();
        Jevt.addProperty("id", e.getId() );
        Jevt.addProperty("date", e.getDateEvenement().toString() );
        List<Equipe> leqp = e.getEquipes() ;
        JsonArray Jeqp = new JsonArray();
        int i = 1;
        for(Equipe eq:leqp)
        {
            JsonObject Je = new JsonObject();
            Je.addProperty("numero", i);
            i = i+1;
            JsonArray Jadh = new JsonArray();
            List<Adherent> adh = eq.getAdherents();
            for( Adherent a:adh)
            {
                JsonObject Ja = new JsonObject();
                Ja.addProperty("nom", a.getNom() );
                Ja.addProperty("prenom", a.getPrenom() );
                Jadh.add(Ja);
            }
            Je.add("membres",Jadh);
            Jeqp.add(Je);
        }
        Jevt.add("equipes", Jeqp);
        // l'activité
        JsonObject Jact = new JsonObject();
        Activite act = e.getActivite();
        Jact.addProperty("denomination", act.getDenomination());
        Jact.addProperty("parEquipe", act.isParEquipe());
        Jact.addProperty("nbParticipants", act.getNbParticipants());
        //pre fusion
        JsonObject container = new JsonObject();
        container.add("evenement",Jevt);
        container.add("activite", Jact);
        // les lieux
        try {
            JsonArray Jlieux = new JsonArray();
            List<Lieu> lieux = ServiceMetier.recupererLieux();
            for(Lieu l:lieux)
            {
                JsonObject Jl = new JsonObject();
                Jl.addProperty("denomination", l.getDenomination());
                Jl.addProperty("adress", l.getAdresse());
                Jl.addProperty("description", l.getDescription());
                Jl.addProperty("lat",l.getLatitude());
                Jl.addProperty("lng",l.getLongitude());
                Jl.addProperty("type",l.getTypeLieu().toString());
                Jlieux.add(Jl);
            }
            container.add("lieux",Jlieux);
        } catch (ServiceException ex) {
            Logger.getLogger(JsonSender.class.getName()).log(Level.SEVERE, null, ex);
        }
        //ecriture
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String result = gson.toJson(container);
        out.println(result);
        
        
    }
    
}
