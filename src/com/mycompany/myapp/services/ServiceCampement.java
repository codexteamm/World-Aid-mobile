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
import com.mycompany.myapp.entites.Campement;
import com.mycompany.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author HP
 */
public class ServiceCampement {

    public ArrayList<Campement> Campements;
    public String country;

    public static ServiceCampement instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceCampement() {
        req = new ConnectionRequest();
    }

    public static ServiceCampement getInstance() {
        if (instance == null) {
            instance = new ServiceCampement();
        }
        return instance;
    }

    public ArrayList<Campement> parseCampement(String jsonText) {
        try {
            Campements = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                Campement c = new Campement();
                float id = Float.parseFloat(obj.get("id").toString());
                c.setId((int) id);
                c.setNom(obj.get("nom").toString());
                c.setDescription(obj.get("description").toString());
                c.setPaye(obj.get("paye").toString());
                double longitude = Float.parseFloat(obj.get("longitude").toString());
                c.setLongitude(longitude);
                double latitude = Float.parseFloat(obj.get("latitude").toString());
                c.setLatitude(latitude);

                Campements.add(c);
            }

        } catch (IOException ex) {

        }
        return Campements;
    }

    public ArrayList<Campement> getAllCampement() {
        String url = Statics.BASE_URL + "/association/allmobile";
        System.out.println(url);
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Campements = parseCampement(new String(req.getResponseData()));
                System.out.println(Campements);
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Campements;
    }

    public boolean updateCamps(int id, String description) {
        String urlParameters = "?idcamp=" + id + "&description=" + description;
        String url = Statics.BASE_URL + "/association/updateCampMobile" + urlParameters;
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200;
                req.removeResponseListener(this);

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public boolean addCampement(Campement t) {
        String urlParameters = "?nom=" + t.getNom() + "&description=" + t.getDescription() + "&latitude=" + t.getLatitude() + "&longitude=" + t.getLongitude() + "&pays=" + t.getPaye();
        String url = Statics.BASE_URL + "/association/newmobile" + urlParameters;
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public ArrayList<Campement> getSupportedCamp(int id) {
        String urlParameters = "?idcamp=" + id;
        String url = Statics.BASE_URL + "/association/showmineMobile" + urlParameters;
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Campements = parseCampement(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Campements;
    }

    public boolean campsupported(int id, Campement c) {
        ArrayList<Campement> supported = getSupportedCamp(id);
        if (supported.contains(c)) {
            return true;
        }

        return false;
    }

    /*    public String apipaye(String url) {
        String pays;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                 JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
                 pays= parseCampement(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Campements;
    }*/
 /*http://localhost/pidev_world-aid/web/app_dev.php/association/takeChargeMobileAction?idcamp=13&iduser=5*/
    public boolean takechage(int idcamp, int iduser) {
        String urlParameters = "?idcamp=" + idcamp + "&iduser=" + iduser;
        String url = Statics.BASE_URL + "/association/takeChargeMobileAction" + urlParameters;
        req.setUrl(url);
        System.out.println(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public boolean untakechage(int idcamp, int iduser) {
        String urlParameters = "?idcamp=" + idcamp + "&iduser=" + iduser;
        String url = Statics.BASE_URL + "/association/nontakeChargeMobileMoblie" + urlParameters;
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public boolean delete(int idcamp) {
        String urlParameters = "?idcamp=" + idcamp;
        String url = Statics.BASE_URL + "/association/deletecCmpMobile" + urlParameters;
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public String getcoutrty(double latitude, double longitude) {
        String url = "http://api.geonames.org/countryCodeJSON?lat=" + latitude + "&lng=" + longitude + "&username=sofien";
        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                    req.removeResponseListener(this);
                    JSONParser j = new JSONParser();
                    String jsonText = new String(req.getResponseData());
                    Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
                   country = tasksListJson.get("countryName").toString();
                    
                } catch (IOException ex) {
                    System.out.println("rerror");

                }

            }
        
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return country;
    }
}
