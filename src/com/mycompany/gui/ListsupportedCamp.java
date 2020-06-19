/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.MultiButton;
import com.codename1.ui.Component;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entites.Campement;
import com.mycompany.myapp.services.ServiceCampement;
import java.util.ArrayList;

/**
 *
 * @author HP
 */
public class ListsupportedCamp extends Form {
    Resources theme;
    Form current;
    public ArrayList<com.mycompany.myapp.entites.Campement> campements =null;
    private static final Toolbar.BackCommandPolicy BACK_POLICY = Toolbar.BackCommandPolicy.AS_ARROW;

    public ListsupportedCamp(Form previous, Resources resourceObjectInstance) {
                
        super("camps", BoxLayout.y());
                BorderLayout br = new BorderLayout();
        
           Toolbar tb = new Toolbar(true);
        setToolbar(tb);

        tb.addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
        current=this;
        theme = resourceObjectInstance;
//getToolbar().setBackCommand("Results", BACK_POLICY, e -> previous.showBack());
        if (campements==null){
         campements = ServiceCampement.getInstance().getSupportedCamp(5);
        }
        Component[] listingsToAdd = new Component[campements.size()];
        for (Campement c : campements) {

            MultiButton twoLinesNoIcon = new MultiButton(c.getNom());
            twoLinesNoIcon.setTextLine2(c.getDescription());
            twoLinesNoIcon.setIcon(theme.getImage("camp.png"));
            twoLinesNoIcon.addPointerPressedListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    new showCampDetails(current, theme, c).show();
                    
                }
            });
            add(twoLinesNoIcon);

        }
        
    }
    
}
