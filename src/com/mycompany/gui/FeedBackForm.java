/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.InfiniteProgress;
import com.codename1.components.MultiButton;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.ParseException;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.codename1.uikit.cleanmodern.BaseForm;
import com.mycompany.myapp.entites.Evenement;
import com.mycompany.myapp.entites.Eventfeedback;
import com.mycompany.myapp.services.EvenementController;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author user
 */
public class FeedBackForm extends Form {

    public FeedBackForm(Form previous, Resources theme, Evenement f) {
        //  setTitle("Feeds Back Of " + f.getNomEvent());

        
       Toolbar tb = new Toolbar(true);
       setToolbar(tb);
       tb.addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack()); 
        
        
        
   //     Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        tb.addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, t -> previous.showBack());               add(new InfiniteProgress());
        EvenementController fc = new EvenementController();
        Container container = new Container(new BoxLayout(BoxLayout.Y_AXIS));

        ConnectionRequest con = new ConnectionRequest();

        con.setUrl("http://localhost/pidev_world-aid/web/app_dev.php/association/FeedBackMobile/" + f.getIdEvent() + "");
        con.addResponseListener(new ActionListener<NetworkEvent>() {

            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {

                    ArrayList<Eventfeedback> listFeedBack = getFeedBack(new String(con.getResponseData()));
                    //  for (int i = 0; i < listFeedBack.size(); i++) {
                    // container.add(Createcontainer(listFeedBack.get(i), previous, theme));
                    //Component[] listingsToAdd = new Component[listFeedBack.size()];

                    Form hi = new Form("Feeds Back Of " + f.getNomEvent(), BoxLayout.y());
                   // hi.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
                    boolean ans = listFeedBack.isEmpty();
                    if (ans == true) {
                        hi.add(new Label("No available feedback for this event", "WelcomeTitle"));
                        System.out.println("empty");
                    }
                    for (Eventfeedback fb : listFeedBack) {
                        MultiButton twoLinesNoIcon = new MultiButton(fb.getSujet());
                        //        twoLinesNoIcon.setIcon();
                        twoLinesNoIcon.setTextLine1(fb.getSujet());
                        twoLinesNoIcon.setTextLine2(fb.getMessage());
                        //    MultiButton twoLinesNoIcon = new MultiButton("Sujet :" + fb.getSujet());
                        // twoLinesNoIcon.setTextLine2("Message :" + fb.getMessage());  

                        hi.add(twoLinesNoIcon);

                    }

                    //   hi.refreshTheme();
//                    hi.getToolbar().addSearchCommand(ee -> {
//                        String text = (String) ee.getSource();
//
//                        if (text == null || text.length() == 0) {
//                            // clear search
//                            for (Component cmp : hi.getContentPane()) {
//                                cmp.setHidden(false);
//
//                                cmp.setVisible(true);
//
//                            }
//                            hi.getContentPane().animateLayout(150);
//
//                        } else {
//                            text = text.toLowerCase();
//                            for (Component cmp : hi.getContentPane()) {
//                                MultiButton mb = (MultiButton) cmp;
//
//                                String line1 = mb.getTextLine1();
//                                boolean show = line1 != null && line1.toLowerCase().indexOf(text) > -1;
//                                mb.setHidden(!show);
//                                mb.setVisible(show);
//                            }
//                            hi.getContentPane().animateLayout(150);
//                        }
//                    }, 4);
                    hi.show();

                } catch (ParseException ex) {
                }

            }
        }
        );
        NetworkManager.getInstance()
                .addToQueue(con);
        //   add(toolbarC);

        // add(container1);
//        add(container);
//container.add(hi);
        refreshTheme();

    }

    public ArrayList<Eventfeedback> getFeedBack(String jsonText) throws ParseException {

        ArrayList<Eventfeedback> listFeedBack = new ArrayList<>();

        try {

            JSONParser j = new JSONParser();
            Map<String, Object> feedListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) feedListJson.get("root");
            System.out.println(list);
            for (Map<String, Object> obj : list) {
                //Création des tâches et récupération de leurs données

                Eventfeedback fb = new Eventfeedback();

                fb.setSujet(obj.get("sujet").toString());
                fb.setMessage(obj.get("message").toString());
                listFeedBack.add(fb);
            }

        } catch (IOException ex) {

        }
        return listFeedBack;

    }

//    public Container Createcontainer(Eventfeedback fb, Form previous, Resources theme) {
//        Label sujet = new Label("Sujet : " + fb.getSujet());
//        Label message = new Label("Message : " +fb.getMessage());
//        Container ctn = new Container(new BoxLayout(BoxLayout.Y_AXIS));
//        ctn.add(sujet);
//        ctn.add(message);
//        System.out.println(sujet);
//        System.out.println(message);
//        return ctn;
//    }
}
