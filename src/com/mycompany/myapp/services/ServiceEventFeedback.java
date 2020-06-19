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
import com.mycompany.myapp.entites.Eventfeedback;
import static com.mycompany.myapp.services.ServiceEvent.instance;
import com.mycompany.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.List;

/**
 *
 * @author hamza
 */
public class ServiceEventFeedback {

    public ArrayList<Eventfeedback> Feedbacks;

    public static ServiceEventFeedback instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceEventFeedback() {
        req = new ConnectionRequest();
    }

    public static ServiceEventFeedback getInstance() {
        if (instance == null) {
            instance = new ServiceEventFeedback();
        }
        return instance;
    }

    public ArrayList<Eventfeedback> parseFeedbacks(String jsonText) {
        try {
            Feedbacks = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");

            for (Map<String, Object> obj : list) {

                Eventfeedback fb = new Eventfeedback();

                float id = Float.parseFloat(obj.get("id").toString());
                fb.setId((int) id);

                fb.setSujet(obj.get("sujet").toString());
                fb.setMessage(obj.get("message").toString());
//                float idevn = Float.parseFloat(obj.get("idevenement").toString());
//                fb.setIdevenement((int) idevn);
//                float idben = Float.parseFloat(obj.get("idbenevole").toString());
//                fb.setIdbenevole((int) idben);

                Feedbacks.add(fb);
            }

        } catch (IOException ex) {

        }

        return Feedbacks;
    }

    public boolean addFeedback(Eventfeedback fb, int idevenement, int iduser) {

        String urlParameters = "?sujet=" + fb.getSujet() + "&message=" + fb.getMessage() + "&idbenevole=" + iduser + "&idevenement=" + idevenement;
        String url = Statics.BASE_URL + "/benevole/addfeedbackjson" + urlParameters;
         System.out.println(url);
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
    
    
    public ArrayList<Eventfeedback> showfeedback(int idevenement ,int idben) {
        String url = Statics.BASE_URL + "/benevole/showfeedbackjson?idevenement="+idevenement+"&idbenevole="+idben;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Feedbacks = parseFeedbacks(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Feedbacks;

    }
    public boolean editfeedback (int id ,String sujet,String message) {
        String urlParameters = "?id="+id+"&sujet="+sujet+"&message="+message;
        String url = Statics.BASE_URL + "/benevole/editfeedbackjson" + urlParameters;
        System.out.println(url);
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
    
     public boolean delete(int id) {
        String urlParameters = "?id=" + id ;
        String url = Statics.BASE_URL + "/benevole/deletefeedbackjson" + urlParameters;
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
    
   

}
