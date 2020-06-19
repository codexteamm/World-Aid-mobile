/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.InfiniteProgress;
import com.codename1.components.WebBrowser;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.BrowserComponent;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entites.Evenement;

/**
 *
 * @author user
 */
public class DetailsAllEvents extends Form {

    public DetailsAllEvents(Form previous, Resources theme, Evenement f) {
         super("Add campement", BoxLayout.y()); 
        setLayout(BoxLayout.y());
        BorderLayout br = new BorderLayout();
        
           Toolbar tb = new Toolbar(true);
        setToolbar(tb);

        tb.addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
        setTitle("Details of event " + f.getNomEvent());
      
//        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
        
//        AfficherAllEvents aae = new AfficherAllEvents(previous, theme);
//        getToolbar().addCommandToLeftBar("back", null, new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                aae.showBack();
//            }
//        });

        Label nom = new Label("Event Name : " + f.getNomEvent());
        //  Label id = new Label("Event id : " + f.getIdEvent());

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        Label dd = new Label("Start Date:  " + f.getDateDebutEvent());
        Label df = new Label("End Date :  " + f.getDateFinEvent());
        Label desc = new Label("Description :  " + f.getDescription());
        Label cate = new Label("category :  " + f.getCategorie());
        Label nl = new Label();

        nl.setText(
                "longitude :  " + String.valueOf(f.getLongitude()));
        Label n2 = new Label();

        n2.setText(
                "longitude :  " + String.valueOf(f.getLatitude()));

        Button btn = new Button("See FeedBack");

        Container ctn = new Container(new BoxLayout(BoxLayout.Y_AXIS));

        WebBrowser browser = new WebBrowser() {
            @Override
            public void onLoad(String url) {
                // Placed on onLoad because we need to wait for page 
                // to load to interact with it.

                BrowserComponent c = (BrowserComponent) this.getInternal();
                System.out.println(f);
                String call = "addpopup(" + f.getLatitude() + "," + f.getLongitude() + ",'" + f.getDescription() + "')";
                c.executeAndReturnString(call);

            }
        };
        // addComponent(BorderLayout.CENTER, browser);

        browser.setURL("jar:///webmaps.html");

        ctn.add(nom);
        //  ctn.add(id);

        ctn.add(dd);

        ctn.add(df);

        ctn.add(desc);

        ctn.add(cate);

//        ctn.add(nl);
//
//        ctn.add(n2);
        ctn.add(btn);
        ctn.add(browser);

        Container ctn2 = BorderLayout.center(ctn);

        ctn2.setLayout(
                new BoxLayout(BoxLayout.Y_AXIS));

        add(ctn2);

        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {

                new FeedBackForm(previous, theme, f).show();

            }
        });
//   add(ctn);
    }

}
