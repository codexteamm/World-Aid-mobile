/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.ToastBar;
import com.codename1.googlemaps.MapContainer;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.maps.Coord;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.codename1.uikit.cleanmodern.BaseForm;
import com.mycompany.myapp.services.EvenementController;

/**
 *
 * @author user
 */
public class AddEventForm extends BaseForm {

    ComboBox cb;
    private MapContainer.MapObject marker;
    private Coord latLng;
    String longi, lati;

    public void geoCode() {
        String url = ("https://maps.googleapis.com/maps/api/geocode/json"
                + "?latlng=" + latLng.getLatitude() + "," + latLng.getLongitude()
                + "&key=AIzaSyBKT70_c4V7sfJny_al-yVe4bowsDCUCAM");
        ConnectionRequest request = new ConnectionRequest(url);
        request.setPost(false);
        request.addResponseListener((evt) -> {
            System.out.println(new String(request.getResponseData()));
        });
        NetworkManager.getInstance().addToQueue(request);
    }

    public AddEventForm(Form previous, Resources theme) {

        ComboBox cb = new ComboBox<String>();

      //  super("Add campement", BoxLayout.y());
      
        setLayout(BoxLayout.y());
        BorderLayout br = new BorderLayout();

        Toolbar tb = new Toolbar(true);
        setToolbar(tb);

        tb.addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

        setTitle(
                "Add a new Event");
        setLayout(BoxLayout.y());

        TextField tfName = new TextField("", "Event Name");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Picker tfdateDebFormation = new Picker();//change date format java
        Picker tfdateFinFormation = new Picker();

        tfdateDebFormation.setFormatter(format);
        tfdateFinFormation.setFormatter(format);

// TextField tfdateDebFormation = new TextField("", "Start Date");
//  TextField tfdateFinFormation = new TextField("", "finish date");
        TextField tfLong = new TextField("", "Longitude");
        TextField tfLat = new TextField("", "Latiutde");
        TextField tfDesc = new TextField("", "Description");
        //  TextField tfCate = new TextField("", "Categorie");
        Label lcategorie = new Label("Choose category:");
        Label lstartDate = new Label("Pick  Start Date:");
        Label lfinDate = new Label("Pick Finish Date:");
        Label position = new Label("Choose the event place:");
        cb.addItem("");
        cb.addItem("Enfants");
        cb.addItem("Femmes");
        cb.addItem("SDF");
        cb.addItem("Refugies");
        Button btnValider = new Button("Add a new event");

        EvenementController ec = new EvenementController();

        btnValider.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt
            ) {
                if (tfName.getText().equals("")) {
                    Dialog.show("Erreur", "veuillez saisir le nom de l evenement", "Ok", null);

                } else if (tfdateDebFormation.getText().equals("")) {
                    Dialog.show("Erreur", "veuillez saisir la date de debut", "Ok", null);

                } else if (tfdateFinFormation.getText().equals("")) {
                    Dialog.show("Erreur", "veuillez saisir la date de fin", "Ok", null);

                } else if (tfLong.getText().equals("")) {
                    Dialog.show("Erreur", "veuillez choisir une position dans la map", "Ok", null);
                } else if (tfLat.getText().equals("")) {
                    Dialog.show("Erreur", "veuillez saisir la latitude", "Ok", null);
                } else if (tfDesc.getText().equals("")) {
                    Dialog.show("Erreur", "veuillez saisir une description", "Ok", null);
                } else if (cb.getSelectedItem().equals("")) {
                    Dialog.show("Erreur", "veuillez choisir une categorie", "Ok", null);
                } else {

                    //  ec.addEvent(tfName, tfdateDebFormation, tfdateFinFormation, tfLong, tfLat, tfDesc, tfCate, theme);
                    //   ec.addEvent(e);
                    ec.addEvent(tfName, tfdateDebFormation, tfdateFinFormation, tfLong, tfLat, tfDesc, cb, theme);

                    Dialog.show("Success", "Connection accepted", new Command("OK"));
                    new AfficherMyEvenement(previous, theme).show();
                    refreshTheme();
                    //  Dialog.show("ERROR", "Server error", new Command("OK"));
                }

            }

        }
        );

        final MapContainer cnt = new MapContainer();
        Style s = new Style();

        s.setFgColor(
                0xff0000);
        s.setBgTransparency(
                0);
        FontImage markerImg = FontImage.createMaterial(FontImage.MATERIAL_PLACE, s);

        cnt.addTapListener((ActionEvent e) -> {
            if (marker != null) {
                cnt.removeMapObject(marker);
            }
            latLng = cnt.getCoordAtPosition(e.getX(), e.getY());

            longi = String.valueOf(latLng.getLongitude());
            System.out.println(longi);
            tfLong.setText(longi);

            lati = String.valueOf(latLng.getLatitude());
            System.out.println(lati);
            tfLat.setText(lati);
            //    geoCode();
            marker = cnt.addMarker(
                    EncodedImage.createFromImage(markerImg, false),
                    cnt.getCoordAtPosition(e.getX(), e.getY()),
                    "",
                    "",
                    e3 -> {
                        ToastBar.showMessage("You clicked " + "", FontImage.MATERIAL_PLACE);
                    }
            );

        });
        Container root = LayeredLayout.encloseIn(
                BorderLayout.center(cnt)
        );

        addAll(tfName, lstartDate, tfdateDebFormation, lfinDate, tfdateFinFormation, tfDesc, lcategorie, cb, btnValider);
        add(position);
        add(root);
//        getToolbar()
//                .addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK,
//                        e -> previous.showBack()); // Revenir vers l'interface précédente

    }

}
