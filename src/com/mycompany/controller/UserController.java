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
import com.codename1.ui.events.ActionListener;
import com.mycompany.entities.User;
//import com.mycompany.utils.MyConnection;
//import com.mycompany.myapp.views.AffectUser;
import static com.mycompany.utils.Statics.BASE_URL;
import java.io.IOException;
import java.util.List;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
import java.util.Map;

//import java.util.logging.Level;
//import java.util.logging.Logger;
/**
 *
 * @author salmouch
 */
public class UserController {

    public static UserController instance = null;
    public boolean resultOK;
    private ConnectionRequest req;
    //private Connection cnx;
    public User User = new User();

    public UserController() {
        req = new ConnectionRequest();
        //  cnx = MyConnection.getInstance().getCnx();
    }

    public static UserController getInstance() {

        if (instance == null) {
            instance = new UserController();
        }
        return instance;
    }

    public User parseUser(String jsonText) {

        User UserL = new User();
        try {
            JSONParser j = new JSONParser();

            Map<String, Object> UserListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            if (UserListJson.get("type").equals("Login succeed")) {

                float id = Float.parseFloat(UserListJson.get("id").toString());
                UserL.setId((int) (id));
                if (UserListJson.get("username") != null) {
                    UserL.setUsername(UserListJson.get("username").toString());
                }
                if (UserListJson.get("email") != null) {
                    UserL.setEmail(UserListJson.get("email").toString());
                }
                if (UserListJson.get("password") != null) {
                    UserL.setPassword(UserListJson.get("password").toString());
                }
                if (UserListJson.get("nom") != null) {
                    UserL.setNom(UserListJson.get("nom").toString());
                }
                if (UserListJson.get("prenom") != null) {
                    UserL.setPrenom(UserListJson.get("prenom").toString());
                }
                if (UserListJson.get("categorie") != null) {
                    UserL.setCategorie(UserListJson.get("categorie").toString());
                }
                if (UserListJson.get("description") != null) {
                    UserL.setDescription(UserListJson.get("description").toString());
                }
                if (UserListJson.get("adresse") != null) {
                    UserL.setAdresse(UserListJson.get("adresse").toString());
                }
                if (UserListJson.get("pays") != null) {
                    UserL.setPays(UserListJson.get("pays").toString());
                }
                if (UserListJson.get("rib") != null) {
                    UserL.setRib(UserListJson.get("rib").toString());
                }
                if (UserListJson.get("numero") != null) {
                    UserL.setNumero(UserListJson.get("numero").toString());
                }
                if (UserListJson.get("logo") != null) {
                    UserL.setLogo(UserListJson.get("logo").toString());
                }
                if (UserListJson.get("role") != null) {
                    UserL.setRole(UserListJson.get("role").toString());
                }
                

            }
        } catch (IOException ex) {
            ex.getMessage();
        }

        return UserL;
    }

    public User Login(String username, String password) {
        String url = BASE_URL + "/loginMobile/" + username + "/" + password;
        req.setUrl(url);
        req.setPost(false);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                User = parseUser(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return User;
    }

}
