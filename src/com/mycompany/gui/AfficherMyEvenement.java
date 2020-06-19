/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
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
public class AfficherMyEvenement extends  BaseForm {

    public AfficherMyEvenement(Form previous, Resources theme) {
        // HomeForm h = new HomeForm();
      
        super("Add campement", BoxLayout.y()); 
        setLayout(BoxLayout.y());
        BorderLayout br = new BorderLayout();
        
           Toolbar tb = new Toolbar(true);
        setToolbar(tb);

        tb.addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
//        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

        Container toolbarC = new Container(new BoxLayout(BoxLayout.X_AXIS));
        // TextField zoneRecherche = new TextField();

//        zoneRecherche.setHint("Search");
//        Button boutonRecherche = new Button("Ok");
//        toolbarC.add(boutonRecherche);
//        toolbarC.add(zoneRecherche);
        add(toolbarC);

        EvenementController fc = new EvenementController();
        Container container = new Container(new BoxLayout(BoxLayout.Y_AXIS));

        ConnectionRequest con = new ConnectionRequest();

        con.setUrl("http://localhost/pidev_world-aid/web/app_dev.php/association/showMyMobile");
        con.addResponseListener(new ActionListener<NetworkEvent>() {

            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {

                    ArrayList<Evenement> listEvent = getListEvent(new String(con.getResponseData()));
                    for (int i = 0; i < listEvent.size(); i++) {
                        container.add(Createcontainer(listEvent.get(i), fc, previous, theme));
                    }
                    refreshTheme();
                } catch (ParseException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueue(con);
        //   add(toolbarC);
        

        // add(container1);
        add(container);

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

                //c.setDateDebutEvent(obj.get("dateDebEvent").toString());
                //c.setDateFinEvent(obj.get("dateFinEvent").toString());
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
                //Ajouter l'evenement extrait de la réponse Json à la liste
            }

        } catch (IOException ex) {

        }

        return events;

    }

    public Container Createcontainer(Evenement f, EvenementController fc, Form previous, Resources theme) {
        Label nom = new Label("Event Name : " + f.getNomEvent());
        //  Label id = new Label("Event id : " + f.getIdEvent());

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        Label dd = new Label("Start Date:  " + f.getDateDebutEvent());
        Label df = new Label("End Date :  " + f.getDateFinEvent());
        Label desc = new Label("Description :  " + f.getDescription());
        Label cate = new Label("category :  " + f.getCategorie());
        Label nl = new Label();
        nl.setText("longitude :  " + String.valueOf(f.getLongitude()));
        Label n2 = new Label();
        n2.setText("longitude :  " + String.valueOf(f.getLatitude()));

        Label lign = new Label("--------------------------------------------------------------------");

        Button btn = new Button("Update");
        Button btn2 = new Button("Delete");
      //  Button btn3 = new Button("location");

        Container ctn = new Container(new BoxLayout(BoxLayout.Y_AXIS));

        ctn.add(nom);
        //  ctn.add(id);

        ctn.add(dd);

        ctn.add(df);

        ctn.add(desc);

        ctn.add(cate);

        ctn.add(nl);

        ctn.add(n2);
        Container ctn2 = BorderLayout.center(ctn);

        ctn2.setLayout(
                new BoxLayout(BoxLayout.Y_AXIS));

        ctn2.add(btn);

        ctn2.add(btn2);

        ctn2.add(lign);

        btn.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt
            ) {

                new UpdateEventForm(previous, theme, f).show();

            }
        }
        );

        btn2.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt
            ) {

                if (Dialog.show("Confirmation", "Are you sure ?", "ok", "cancel")) {
                    fc.deleteEvent(f);
                }
                //  new AfficherMyEvenement(previous, theme).show();
                refreshTheme();
                new AfficherMyEvenement(previous, theme).show();
            }
        }
        );

        return ctn2;

    }

}
