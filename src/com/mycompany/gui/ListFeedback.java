/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.MultiButton;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.controller.UserController;
import com.mycompany.myapp.entites.Evenement;
import com.mycompany.myapp.entites.Eventfeedback;
import com.mycompany.myapp.services.ServiceEvent;
import com.mycompany.myapp.services.ServiceEventFeedback;
import java.util.ArrayList;

/**
 *
 * @author hamza
 */
public class ListFeedback extends Form {
Form current;
Resources theme;
int iduser =UserController.getInstance().User.getId();
private static final Toolbar.BackCommandPolicy BACK_POLICY = Toolbar.BackCommandPolicy.AS_ARROW;
    public ListFeedback(Form previous, Resources resourceObjectInstance, Evenement e ) {
       
         super("Your Feedback", BoxLayout.y());
        current=this;
        theme = resourceObjectInstance;
//        getToolbar().setBackCommand("Results", BACK_POLICY, e1 -> previous.showBack());
Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        tb.addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, t -> previous.showBack());       
    
        ArrayList<Eventfeedback> Fbs = ServiceEventFeedback.getInstance().showfeedback(e.getIdEvent(), iduser);
        System.out.println(Fbs);
        Component[] listingsToAdd = new Component[Fbs.size()];
        for (Eventfeedback fb : Fbs) {
           Label suj = new Label("Sujet : "+fb.getSujet());
           Label msg = new Label("Message : "+fb.getMessage());
           Container ctn = new Container(new FlowLayout(CENTER));
           Button Edit = new Button("Edit");
           Button Delete = new Button("Delete");
           ctn.add(Edit);
           ctn.add(Delete);
           
           add(suj);
           add(msg);
           add(ctn);
           
           Edit.addActionListener(new ActionListener() {
               @Override
               public void actionPerformed(ActionEvent evt) {
                   new EditFeedback(current, fb, theme).show();
               }
           });
                  
           
           Delete.addActionListener(new ActionListener() {
               @Override
               public void actionPerformed(ActionEvent evt) {
                   ServiceEventFeedback.getInstance().delete(fb.getId());
                    new ListEvents(current, theme).show();
               }
           });
        }
        
            
    
    }
  
}
