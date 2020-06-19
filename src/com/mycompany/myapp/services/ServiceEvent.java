/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entites.Evenement;
import com.mycompany.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.List;

/**
 *
 * @author hamza
 */
public class ServiceEvent {

    public ArrayList<Evenement> events;

    public static ServiceEvent instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceEvent() {
        req = new ConnectionRequest();
    }

    public static ServiceEvent getInstance() {
        if (instance == null) {
            instance = new ServiceEvent();
        }
        return instance;
    }

    public ArrayList<Evenement> parseEvents(String jsonText) {
        try {
            events = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String,Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");

            for (Map<String,Object> obj : list) {
               
                Evenement c = new Evenement();

                c.setNomEvent(obj.get("nomEvent").toString());
                String date = obj.get("dateDebutEvent").toString();
                String value = date.substring(6, 16);
                c.setDateDebutEvent(value);
                String date1 = obj.get("dateFinEvent").toString();
                String value1 = date1.substring(6, 16);
                c.setDateFinEvent(value1);
                float longitude = Float.parseFloat(obj.get("longitude").toString());
               c.setLongitude((int) longitude);
               float latitude = Float.parseFloat(obj.get("latitude").toString());
               c.setLatitude((int) latitude);

                c.setDescription(obj.get("description").toString());
                c.setCategorie(obj.get("categorie").toString());

                float id = Float.parseFloat(obj.get("idEvent").toString());
                c.setIdEvent((int) id);

//               float idAssoc = Float.parseFloat(obj.get("id_association").toString());
//               c.setId_association((int)idAssoc);
                events.add(c);
            }

        } catch (IOException ex) {

        }

        return events;
    }

    public ArrayList<Evenement> getAllEvents() {
        String url = Statics.BASE_URL + "/benevole/eventsJSON";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                events = parseEvents(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return events;

    }
    
    public ArrayList<Evenement> getmyEvents(int id) {
        String url = Statics.BASE_URL + "/benevole/myeventsjson?idbenevole="+id;
        req.setUrl(url);
        req.setPost(false);
        
        System.out.println(id);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                events = parseEvents(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return events;

    }
    
     public void Volunteer(int id, int idben) {
        String url = Statics.BASE_URL + "/benevole/volunteerjson?idevenement="+id+"&idbenevole="+idben;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                events = parseEvents(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
       
            
    }
    
      public void UnVolunteer(int id, int idben) {
        String url = Statics.BASE_URL + "/benevole/unvolunteerjson?idevenement="+id+"&idbenevole="+idben;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                events = parseEvents(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
       
            
    }
    
     public boolean EventVolunteered(int id,Evenement e)
            {
                ArrayList<Evenement> volunteered =getmyEvents(id);
                System.out.println(volunteered);
                
                
                System.out.println("********");
                System.out.println(e);
                if(volunteered.contains(e)){
                    return true;
                }
                return false;
            }
    
           

}
