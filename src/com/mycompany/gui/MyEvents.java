/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.MultiButton;
import com.codename1.ui.Component;
import com.codename1.ui.Form;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.controller.UserController;
import com.mycompany.myapp.entites.Evenement;
import com.mycompany.myapp.services.ServiceEvent;
import java.util.ArrayList;

/**
 *
 * @author hamza
 */
public class MyEvents extends Form {
    
    Form current;
Resources theme;
private static final Toolbar.BackCommandPolicy BACK_POLICY = Toolbar.BackCommandPolicy.AS_ARROW;
    
 public MyEvents(Form previous, Resources resourceObjectInstance) {
        super("My Events", BoxLayout.y());
        current=this;
        theme = resourceObjectInstance;
//getToolbar().setBackCommand("Results", BACK_POLICY, e -> previous.showBack());
        ArrayList<Evenement> Evenements = ServiceEvent.getInstance().getmyEvents(UserController.getInstance().User.getId());
        Component[] listingsToAdd = new Component[Evenements.size()];
        for (Evenement e : Evenements) {
            MultiButton twoLinesNoIcon = new MultiButton(e.getNomEvent());
            twoLinesNoIcon.setTextLine2(e.getDescription());
            twoLinesNoIcon.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    new EventDetails(current, theme, e).show();
                }
            });
            add(twoLinesNoIcon);
        }


    }

}
