/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entites.DonMateriel;
import com.mycompany.utils.Statics;
import java.util.ArrayList;

/**
 *
 * @author ASUS
 */
public class DonMaterielController {
     public ArrayList<DonMateriel> dons;

    public static DonMaterielController instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    public DonMaterielController() {
        req = new ConnectionRequest();
    }

    public static DonMaterielController getInstance() {
        if (instance == null) {
            instance = new DonMaterielController();
        }
        return instance;
    }
    
      public boolean UpdateDon(DonMateriel d) {
        String urlParameters= "?reference=" +d.getReference()+"&typeMateriel="+d.getType_Materiel()+"&quantite="+d.getQuantite();   
        String url = Statics.BASE_URL + "/editDonMobile"+urlParameters;
        System.out.println(url);
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
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


    public boolean deleteDon(int reference) {
        String urlParameters = "?reference=" + reference ;
        String url = Statics.BASE_URL + "/deleteDonMobile" + urlParameters;
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
