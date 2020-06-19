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
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.codename1.uikit.cleanmodern.BaseForm;
import com.mycompany.myapp.entites.Evenement;
import com.mycompany.myapp.services.EvenementController;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author user
 */
public class AfficherAllEvents extends BaseForm {

    public AfficherAllEvents(Form previous, Resources theme) {
//        super("List of All Events", BoxLayout.y());
//        Toolbar tb = new Toolbar();
//        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e ->previous.showBack());

super("Add campement", BoxLayout.y()); 
        setLayout(BoxLayout.y());
        BorderLayout br = new BorderLayout();
        
           Toolbar tb = new Toolbar(true);
        setToolbar(tb);

        tb.addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

        add(new InfiniteProgress());
        EvenementController fc = new EvenementController();
        // Container cont = new Container(new BoxLayout(BoxLayout.Y_AXIS));

        ConnectionRequest con = new ConnectionRequest();

        con.setUrl("http://localhost/pidev_world-aid/web/app_dev.php/association/showAllMobile");
        con.addResponseListener(new ActionListener<NetworkEvent>() {

            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {

                    ArrayList<Evenement> listEvent = getListEvent(new String(con.getResponseData()));

                    //  cont.add(Createcontainer(listEvent.get(i), fc, previous, theme));
                    //  Component[] listingsToAdd = new Component[listEvent.size()];
                    Form hi = new Form("List Events", BoxLayout.y());
                    // hi.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

                    for (Evenement e : listEvent) {

                        MultiButton twoLinesNoIcon = new MultiButton(e.getNomEvent());
                        //MultiButton m = new MultiButton();
                        //MultiButton m1 = new MultiButton(e.getNomEvent());
                        twoLinesNoIcon.setTextLine2("Event Name : " + e.getNomEvent());
                        twoLinesNoIcon.setTextLine1("category : " + e.getCategorie());

                        twoLinesNoIcon.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent evt) {
                                new DetailsAllEvents(previous, theme, e).show();
                            }
                        });
                        hi.add(twoLinesNoIcon);
                        // hi.revalidate();
                        //  hi.refreshTheme();

//                        hi.getToolbar().setTitleComponent(
//                                FlowLayout.encloseCenterMiddle(
//                                        new Label("List of All Events", "Title"),
//                                        new Label("7", "InboxNumber")
//                                )
//                        );
//                        hi.getToolbar().addSearchCommand(ee -> {
//                            String text = (String) ee.getSource();
//
//                            if (text == null || text.length() == 0) {
//                                // clear search
//                                for (Component cmp : hi.getContentPane()) {
//                                    cmp.setHidden(false);
//                                    cmp.setVisible(true);
//
//                                }
//                                hi.getContentPane().animateLayout(150);
//                            } else {
//                                text = text.toLowerCase();
//                                for (Component cmp : hi.getContentPane()) {
//                                    MultiButton mb = (MultiButton) cmp;
//
//                                    String line1 = mb.getTextLine1();
//                                    boolean show = line1 != null && line1.toLowerCase().indexOf(text) > -1;
//                                    mb.setHidden(!show);
//                                    mb.setVisible(show);
//
//                                }
//                                hi.getContentPane().animateLayout(150);
//                            }
//                        }, 4);
                        hi.show();
                    }

                } catch (ParseException ex) {
                }
            }
        });
        NetworkManager.getInstance().addToQueue(con);
        //   add(toolbarC);
        // add(cont);
        //add(hi);
        refreshTheme();

    }

    public ArrayList<Evenement> getListEvent(String jsonText) throws ParseException {

        ArrayList<Evenement> events = new ArrayList<>();

        try {

            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            System.out.println(list);
            for (Map<String, Object> obj : list) {
                //Création des tâches et récupération de leurs données
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

//                 float id = Float.parseFloat(obj.get("id_association").toString());
//                c.setId_association((int)idAssoc);
                events.add(c);
            }

        } catch (IOException ex) {

        }

        return events;

    }

//    public Container Createcontainer(Evenement f, EvenementController fc, Form previous, Resources theme) {
//        Label nom = new Label("Event Name : " + f.getNomEvent());
//        Label id = new Label("Event id : " + f.getIdEvent());
//
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//
//        Label dd = new Label("Start Date:  " + f.getDateDebutEvent());
//        Label df = new Label("End Date :  " + f.getDateFinEvent());
//        Label desc = new Label("Description :  " + f.getDescription());
//        Label cate = new Label("category :  " + f.getCategorie());
//        Label nl = new Label();
//        nl.setText("longitude :  " + String.valueOf(f.getLongitude()));
//        Label n2 = new Label();
//        n2.setText("longitude :  " + String.valueOf(f.getLatitude()));
//
//        Label lign = new Label("--------------------------------------------------------------------");
//
//        Button btn = new Button("Update");
//        Button btn2 = new Button("Delete");
//        Button btn3 = new Button("See Details");
//
//        Container ctn = new Container(new BoxLayout(BoxLayout.Y_AXIS));
//        ctn.add(nom);
//        // ctn.add(id);
//        //ctn.add(dd);
//        //ctn.add(df);
//        ctn.add(desc);
//        //ctn.add(cate);
//        //ctn.add(nl);
//        //ctn.add(n2);
//        Container ctn2 = BorderLayout.center(ctn);
//        ctn2.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
//
//        ctn2.add(btn3);
//        ctn2.add(lign);
//        btn3.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent evt) {
//
//                new DetailsAllEvents(previous, theme, f).show();
//
//            }
//        });
//        return ctn2;
//        
//    }
}

//public class ListEvents extends Form {
//
//    Form current;
//    Resources theme;
//    private static final Toolbar.BackCommandPolicy BACK_POLICY = Toolbar.BackCommandPolicy.AS_ARROW;
//
//    public ListEvents(Form previous, Resources resourceObjectInstance) {
//        super("All Events", BoxLayout.y());
//        current = this;
//        theme = resourceObjectInstance;
//        getToolbar().setBackCommand("Results", BACK_POLICY, e -> previous.showBack());
//        ArrayList<Evenement> campements = ServiceEvent.getInstance().getAllEvents();
//        Component[] listingsToAdd = new Component[campements.size()];
//        for (Evenement e : campements) {
//            MultiButton twoLinesNoIcon = new MultiButton(e.getNomEvent());
//            twoLinesNoIcon.setTextLine2(e.getDescription());
//            twoLinesNoIcon.addActionListener(new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent evt) {
//                    new EventDetails(current, theme, e).show();
//                }
//            });
//            add(twoLinesNoIcon);
//        }
//
//    }
//
//}
