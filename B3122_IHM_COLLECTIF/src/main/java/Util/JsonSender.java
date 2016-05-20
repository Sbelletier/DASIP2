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
import metier.modele.Activite;
import metier.modele.Adherent;
import metier.modele.Evenement;

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
    
}
