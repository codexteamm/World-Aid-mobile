/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entites.Evenement;
import java.util.ArrayList;

/**
 *
 * @author user
 */
public class EvenementController {

    public ArrayList<Evenement> events;

    public static EvenementController instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    public EvenementController() {
        req = new ConnectionRequest();
    }

    public static EvenementController getInstance() {
        if (instance == null) {
            instance = new EvenementController();
        }
        return instance;
    }

    public void addEvent(TextField tfName, Picker tfdateDebFormation, Picker tfdateFinFormation, TextField tfLong, TextField tfLat, TextField tfDesc, ComboBox cb, Resources theme) {
        //Evenement e = new Evenement();
        ConnectionRequest con = new ConnectionRequest();
        String categorie = cb.getSelectedItem().toString();
        con.setUrl("http://localhost/pidev_world-aid/web/app_dev.php/association/newMobile?nomEvent=" + tfName.getText() + "&dateDeb=" + tfdateDebFormation.getText() + "&dateFin=" + tfdateFinFormation.getText() + "&longitude=" + tfLong.getText() + "&latitude=" + tfLat.getText() + "&description=" + tfDesc.getText() + "&categorie=" + categorie + "");

        con.addResponseListener(new ActionListener<NetworkEvent>() {

            @Override
            public void actionPerformed(NetworkEvent evt) {
                byte[] data = (byte[]) evt.getMetaData();
                String s = new String(data);

                Dialog.show("Add", "Added ok", "Ok", null);

            }
        });
        NetworkManager.getInstance().addToQueue(con);
    }

    public void deleteEvent(Evenement e) {

        ConnectionRequest con = new ConnectionRequest();

        con.setUrl("http://localhost/pidev_world-aid/web/app_dev.php/association/deleteMobile/" + e.getIdEvent() + "");
        con.addResponseListener(new ActionListener<NetworkEvent>() {

            @Override
            public void actionPerformed(NetworkEvent evt) {
                byte[] data = (byte[]) evt.getMetaData();
                String s = new String(data);

                if (s.equals("success")) {
                    Dialog.show("Suppression", "suppression ok", "Ok", null);
                }
            }
        });

        NetworkManager.getInstance().addToQueue(con);
    }

    public void updateEvent(TextField tNomEvent, TextField tDescription, ComboBox categorie, TextField tLongitude, TextField tLatitude, Evenement e) {

        ConnectionRequest con = new ConnectionRequest();

        con.setUrl("http://localhost/pidev_world-aid/web/app_dev.php/association/EditEventMobile?idEvent=" + e.getIdEvent() + "&nomEvent=" + tNomEvent.getText() + "&description=" + tDescription.getText() + "&categorie=" + categorie.getSelectedItem() + "&longitude=" + tLongitude.getText() + "&latitude=" + tLongitude.getText());
//creation et insertaion de url de demande de cnx
        con.addResponseListener(new ActionListener<NetworkEvent>() {

            @Override
            public void actionPerformed(NetworkEvent evt) {
                byte[] data = (byte[]) evt.getMetaData();//on recupere les meta data à partir de network evt
                String s = new String(data);//transformer de byte en string

                //  if (s.equals("success")) {
                //   Dialog.show("Confirmation", "modification ok", "Ok", null);
                // new AfficherMyEvenement(previous, theme).show();
                //}
            }
        });

        NetworkManager.getInstance().addToQueue(con);
    }

//    public void feedbackEvent(Evenement e) {
//
//        ConnectionRequest con = new ConnectionRequest();
//
//        con.setUrl("http://localhost/pidev_world-aid/web/app_dev.php/association/FeedBackMobile/" + e.getIdEvent() + "");
//        con.addResponseListener(new ActionListener<NetworkEvent>() {
//
//            @Override
//            public void actionPerformed(NetworkEvent evt) {
//                byte[] data = (byte[]) evt.getMetaData();
//                String s = new String(data);
//
//                Dialog.show("feedback", "feedback ok", "Ok", null);
//
//            }
//        });
//
//        NetworkManager.getInstance().addToQueue(con);
//    }

}
