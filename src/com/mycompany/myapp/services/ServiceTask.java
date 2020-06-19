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
import com.mycompany.myapp.entites.DonMateriel;
import com.mycompany.utils.Statics;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ASUS
 */
public class ServiceTask {
     public ArrayList<DonMateriel> dons;

    public static ServiceTask instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceTask() {
        req = new ConnectionRequest();
    }

    public static ServiceTask getInstance() {
        if (instance == null) {
            instance = new ServiceTask();
        }
        return instance;
    }
    public boolean addDon(DonMateriel d,int idEvent,int iduser) {
        String urlParameters = "?typeMateriel=" +d.getType_Materiel()  + "&quantite=" + d.getQuantite()+ "&idBenevole=" + iduser + "&idEvent=" + idEvent;
        String url = Statics.BASE_URL + "/newDonMobile" + urlParameters;
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
