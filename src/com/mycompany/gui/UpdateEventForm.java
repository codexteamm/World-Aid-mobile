/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.WebBrowser;
import com.codename1.googlemaps.MapContainer;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.BrowserComponent;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.codename1.uikit.cleanmodern.BaseForm;

import com.mycompany.myapp.entites.Evenement;
import com.mycompany.myapp.services.EvenementController;
import javafx.scene.web.WebEngine;

/**
 *
 * @author user
 */
public class UpdateEventForm extends  BaseForm{

    private MapContainer.MapObject marker;
    WebEngine webEngine;

    public UpdateEventForm(Form previous, Resources theme, Evenement e) {
        setTitle("Update " + e.getNomEvent());
 Toolbar tb = new Toolbar(true);
        setToolbar(tb);

        tb.addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, t -> previous.showBack());
        //   super("Update  " + e.getNomEvent(), BoxLayout.y());
        TextField tNomEvent = new TextField("", "Event Name");

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Picker tfdateDebFormation = new Picker();//change date format java
        Picker tfdateFinFormation = new Picker();
        tfdateDebFormation.setFormatter(format);
        tfdateFinFormation.setFormatter(format);
        //   Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(f.getDateDebutEvent());         

        TextField tDescription = new TextField("", "description");
        ComboBox categorie = new ComboBox(" ", "Enfants", "Femmes", "SDF", "Refugie");
        TextField tLongitude = new TextField("", "Longitude");
        TextField tLatitude = new TextField("", "Latitude");

        Button modif = new Button("Update");

        tNomEvent.setText(e.getNomEvent());

        //    tfdateDebFormation.setDate(date1);
        //   tfdateFinFormation.setDate(f.getDateFinEvent());
        tDescription.setText(e.getDescription());
        tLongitude.setText(String.valueOf(e.getLongitude()));
        tLatitude.setText(String.valueOf(e.getLatitude()));
        categorie.setSelectedItem(e.getCategorie());

        Label lnom = new Label("Event Name :");
        Label ldateDebut = new Label("Start Date :");
        Label ldateFin = new Label("Finish Date :");
        Label ldescription = new Label("Event Description :");
        Label lcategorie = new Label("Event Categorie :");

        //map
        WebBrowser browser = new WebBrowser() {
            @Override
            public void onLoad(String url) {
                // Placed on onLoad because we need to wait for page 
                // to load to interact with it.

                BrowserComponent c = (BrowserComponent) this.getInternal();
                System.out.println(e);
                String call = "addpopup(" + e.getLatitude() + "," + e.getLongitude() + ",'" + e.getDescription() + "')";
                c.executeAndReturnString(call);

            }
        };

        browser.setURL("jar:///webmaps.html");

        Container ctn = new Container(new BoxLayout(BoxLayout.Y_AXIS));

        ctn.add(lnom);
        ctn.add(tNomEvent);
        ctn.add(ldateDebut);
        ctn.add(tfdateDebFormation);
        ctn.add(ldateFin);
        ctn.add(tfdateFinFormation);
        ctn.add(ldescription);
        ctn.add(tDescription);
        ctn.add(lcategorie);
        ctn.add(categorie);
//        add(tLongitude);
//        add(tLatitude);

        ctn.add(modif);
        ctn.add(browser);

        Container ctn2 = BorderLayout.center(ctn);

        ctn2.setLayout(
                new BoxLayout(BoxLayout.Y_AXIS));

        add(ctn2);

        EvenementController ec = new EvenementController();
        modif.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {

//                System.out.println("picker date in mills:"+tdate.getDate().getTime());
                ec.updateEvent(tNomEvent, tDescription, categorie, tLongitude, tLatitude, e);
                //   System.out.println(ec.UpdateEvent(e));
                Dialog.show("Confirmation", "modification ok", "Ok", null);
                new AfficherMyEvenement(previous, theme).show();
                refreshTheme();
            }
        });
    }
}
