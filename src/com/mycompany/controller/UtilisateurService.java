/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.controller;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
//import java.io.InputStream;
import java.util.ArrayList;
//import java.util.Date;
import java.util.List;
import java.util.Map;

import com.mycompany.entities.User;
import com.mycompany.utils.Statics;
import static com.mycompany.utils.Statics.BASE_URL;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import java.util.logging.Level;
//import java.util.logging.Logger;

public class UtilisateurService {

    public ArrayList<User> us;
    public static UtilisateurService instance = null;
    public static User user;
    public boolean resultOK;
    int k = 0;
   

    public static UtilisateurService getInstance() {
        if (instance == null) {
            instance = new UtilisateurService();
        }
        return instance;
    }

    public void getK(int i) {
        k = i;

    }

    public User getListUtilisateur(String json) {
        User u = new User();

        try {

            JSONParser j = new JSONParser();
            Map<String, Object> utilisateurs = j.parseJSON(new CharArrayReader(json.toCharArray()));

            if (utilisateurs.get("id") != null) {
                float id = Float.parseFloat(utilisateurs.get("id").toString());
                u.setId((int) id);
                u.setUsername(utilisateurs.get("username").toString());
                u.setEmail(utilisateurs.get("email").toString());
                u.setPassword(utilisateurs.get("password").toString());

            }
            System.out.println(u);

        } catch (IOException ex) {

        }

        return u;
    }


    public boolean addUser(User u) {
        ConnectionRequest con = new ConnectionRequest();
        con.setPost(true);
        String Url = Statics.BASE_URL + "/addUser" + "/" + u.getUsername() + "/" + u.getEmail() + "/" + u.getPassword() + "/" + u.getRib() + "/" + u.getAdresse() + "/" + u.getCategorie() + "/" + u.getLogo() + "/" + u.getNumero();

        con.setUrl(Url);

        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                String str = new String(con.getResponseData());
                UtilisateurService us = new UtilisateurService();
                UtilisateurService.user = us.getListUtilisateur(str);
                resultOK = con.getResponseCode() == 200; //Code HTTP 200 OK
                con.removeResponseListener(this);
                System.out.println(str);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        System.out.println(user);
        return resultOK;
    }

    public boolean addBen(User u) {
        ConnectionRequest con = new ConnectionRequest();
        con.setPost(true);
        String Url = Statics.BASE_URL + "/addBen" + "/" + u.getUsername() + "/" + u.getEmail() + "/" + u.getPassword() + "/" + u.getNom() + "/" + u.getPrenom() + "/" + u.getPays() + "/" + u.getLogo();

        con.setUrl(Url);

        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                String str = new String(con.getResponseData());
                UtilisateurService us = new UtilisateurService();
                UtilisateurService.user = us.getListUtilisateur(str);
                resultOK = con.getResponseCode() == 200; //Code HTTP 200 OK
                con.removeResponseListener(this);
                System.out.println(str);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        System.out.println(user);
        return resultOK;
    }

    public boolean addNec(User u) {
        ConnectionRequest con = new ConnectionRequest();
        con.setPost(true);
        String Url = Statics.BASE_URL + "/addNec" + "/" + u.getUsername() + "/" + u.getEmail() + "/" + u.getPassword() + "/" + u.getNom() + "/" + u.getPrenom() + "/" + u.getPays() + "/" + u.getDescription() + "/" + u.getLogo();

        con.setUrl(Url);

        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                String str = new String(con.getResponseData());
                UtilisateurService us = new UtilisateurService();
                UtilisateurService.user = us.getListUtilisateur(str);
                resultOK = con.getResponseCode() == 200; //Code HTTP 200 OK
                con.removeResponseListener(this);
                System.out.println(str);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        System.out.println(user);
        return resultOK;
    }

    public boolean deleteUser(int id) {
        ConnectionRequest con = new ConnectionRequest();

        String Url = Statics.BASE_URL + "/deleteUser/" + id;
        con.setUrl(Url);

        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                String json = new String(con.getResponseData());
                if (json.equals("success")) {
                    resultOK = con.getResponseCode() == 200; //Code HTTP 200 OK

                }
                con.removeResponseListener(this);
                System.out.println(json);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);

        return resultOK;
    }

    public boolean updateUser(User u,int id) {
         
        ConnectionRequest con = new ConnectionRequest();

        String Url = Statics.BASE_URL + "/updateUser"+ "/"+ u.getUsername() + "/" + u.getEmail() + "/" + u.getPassword()+"/"+u.getRib()+"/"+u.getCategorie()+"/"+u.getLogo()+"/"+u.getNumero() +"/"+u.getPays()+"/"+u.getAdresse()+"/"+id;

        con.setUrl(Url);
        System.out.println(Url);

        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                String str = new String(con.getResponseData());

                resultOK = con.getResponseCode() == 200; //Code HTTP 200 OK
                con.removeResponseListener(this);
                System.out.println(str);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        System.out.println("updated");
        return resultOK;
    }
    public boolean updatBen(User u,int id) {
         
        ConnectionRequest con = new ConnectionRequest();

        String Url = Statics.BASE_URL + "/updateBen"+ "/"+ u.getUsername() + "/" + u.getEmail() + "/" + u.getPassword()+"/"+u.getLogo()+"/"+u.getNom()+"/"+u.getPrenom()+"/"+u.getPays()+"/"+id;

        con.setUrl(Url);
        System.out.println(Url);

        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                String str = new String(con.getResponseData());

                resultOK = con.getResponseCode() == 200; //Code HTTP 200 OK
                con.removeResponseListener(this);
                System.out.println(str);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        System.out.println("updated");
        return resultOK;
    }
    public boolean updateNece(User u,int id) {
         
        ConnectionRequest con = new ConnectionRequest();

        String Url = Statics.BASE_URL + "/updateNece"+ "/"+ u.getUsername() + "/" + u.getEmail() + "/" + u.getPassword()+"/"+u.getLogo()+"/"+u.getNom()+"/"+u.getPrenom()+"/"+u.getPays()+"/"+u.getDescription()+"/"+id;

        con.setUrl(Url);
        System.out.println(Url);

        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                String str = new String(con.getResponseData());

                resultOK = con.getResponseCode() == 200; //Code HTTP 200 OK
                con.removeResponseListener(this);
                System.out.println(str);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        System.out.println("updated");
        return resultOK;
    }

    public ArrayList<User> showUser() {
        ConnectionRequest req = new ConnectionRequest();

        String url = Statics.BASE_URL + "/showuser";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                us = parseUserr(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return us;
    }

    public ArrayList<User> parseUserr(String jsonText) {
        try {
            us = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");

            for (Map<String, Object> obj : list) {

                User t = new User();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int) id);
                t.setEmail((obj.get("email").toString()));
                t.setPassword((obj.get("password").toString()));
                t.setUsername((obj.get("username").toString()));
               // t.setLogo((obj.get("logo").toString()));
//                t.setAdresse((obj.get("adresse").toString()));
//                t.setCategorie((obj.get("categorie").toString()));
//                t.setDatenaissance((obj.get("datenaissance").toString()));
//                t.setDescription((obj.get("descritpion").toString()));
//
//                t.setNom((obj.get("nom").toString()));
//                t.setNumero((obj.get("numero").toString()));
//
//                t.setPays((obj.get("pays").toString()));
//                t.setPrenom((obj.get("prenom").toString()));
//                t.setRole((obj.get("role").toString()));

                us.add(t);
            }

        } catch (IOException ex) {

        }
        /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web
        
         */
        return us;
    }


}
