/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.SpanLabel;
import com.codename1.messaging.Message;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entites.demande_aide;
import com.mycompany.myapp.services.ServiceRequest2;

/**
 *
 * @author HP
 */
public class showRequestDetailsForm2 extends Form {

    Form current;

    public showRequestDetailsForm2(Form previous, demande_aide da) {
        current = this;
        setLayout(BoxLayout.y());
        BorderLayout br = new BorderLayout();

        Toolbar tb = new Toolbar(true);
        setToolbar(tb);

        tb.addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
        current = this;
        Label title = new Label();
        title.setText("Title:" + da.getTitre());
        SpanLabel description = new SpanLabel();
        description.setText("Description:" + da.getDescription());
        SpanLabel id = new SpanLabel();
        id.setText("id:" + da.getId_demande());
        add(title).add(description).add(id).setLayout(BoxLayout.y());
        Button btnconfirm = new Button("Confirm this request");
        btnconfirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Dialog.show("Confirmation", "Are you sure to confirm this request?", new Command("OK"), new Command("Cancel"));
                if (ServiceRequest2.getInstance().confirmRequest(da.getId_demande())) {
                    Dialog.show("Success", "Request successfully confirmed", new Command("OK"));

                    /**/
                } else {
                    Dialog.show("Error", "There was a problem", new Command("OK"));
                }
            }
        });
        add(btnconfirm).setLayout(BoxLayout.y());
        /*getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK,
                e -> previous.showBack()); // Revenir vers l'interface précédente*/
    }

}
