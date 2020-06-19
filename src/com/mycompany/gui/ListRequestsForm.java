/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.MultiButton;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entites.demande_aide;
import com.mycompany.myapp.services.ServiceRequest2;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author bhk
 */
public class ListRequestsForm extends Form {

    private Resources theme;
    Form current;

    public ListRequestsForm(Form previous, Resources resourceObjectInstance) {

        super("Requests", BoxLayout.y());
        setLayout(BoxLayout.y());
        BorderLayout br = new BorderLayout();

        Toolbar tb = new Toolbar(true);
        setToolbar(tb);

        tb.addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
        current = this;
        current = this;
        theme = resourceObjectInstance;
        //getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
        ArrayList<com.mycompany.myapp.entites.demande_aide> LRequests = ServiceRequest2.getInstance().getAllRequests();

        Component[] listingsToAdd = new Component[LRequests.size()];
        for (demande_aide da : LRequests) {

            MultiButton twoLinesNoIcon = new MultiButton(da.getTitre());
            twoLinesNoIcon.setTextLine2(da.getDescription());
            //twoLinesNoIcon.setIcon(theme.getImage("camp.png"));
            twoLinesNoIcon.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    new showRequestDetailsForm(current, da).show();

                }
            });
            add(twoLinesNoIcon);

        }
    }
}
