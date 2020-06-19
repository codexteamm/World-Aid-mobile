/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.codename1.uikit.cleanmodern.BaseForm;
import com.mycompany.myapp.entites.Evenement;
import com.mycompany.myapp.services.ServiceEventFeedback;

/**
 *
 * @author hamza
 */
public class HomeForm extends BaseForm {

    
     public HomeForm(){
      this(com.codename1.ui.util.Resources.getGlobalResources());
    }
     
    Form current;
    Resources theme;
    Evenement a;

    public HomeForm(Resources theme) {
         super(new BorderLayout());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        tb.setUIID("Container");
        getTitleArea().setUIID("Container");
        Form previous = Display.getInstance().getCurrent();
        tb.setBackCommand("", e -> previous.showBack());
        setUIID("SignIn");
        current = this;
        this.theme = theme;

        setTitle("Home");
        setLayout(BoxLayout.y());

        add(new Label("Welcome to Volunteer home"));
        

        tb.addCommandToLeftSideMenu("Home", null, null);
        tb.addCommandToLeftSideMenu("Events", null, e -> new ListEvents(current,theme).show());
       tb.addCommandToLeftSideMenu("My Events", null, e1 -> new MyEvents(current,theme).show());
      //tb.addCommandToLeftSideMenu("Show Donation", null, e1 -> new ListDonsForm(current,theme).show());
    // tb.addCommandToLeftSideMenu("Add Donation", null, e1 -> new addDonForm(current,theme,a).show());
        tb.addCommandToLeftSideMenu("Donate Money Donation", null, e1 -> new addDonPayementForm(current,theme).show());

      
      
    }
}
