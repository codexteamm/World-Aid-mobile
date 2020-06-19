/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import static com.codename1.ui.Component.BASELINE;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.controller.UserController;
import com.mycompany.myapp.entites.Evenement;
import com.mycompany.myapp.entites.Eventfeedback;
import com.mycompany.myapp.services.ServiceEventFeedback;

/**
 *
 * @author hamza
 */
public class AddFeedback extends Form {
    
     private static final Toolbar.BackCommandPolicy BACK_POLICY = Toolbar.BackCommandPolicy.AS_ARROW;
    int iduser = UserController.getInstance().User.getId();
    public AddFeedback(Form previous, Resources resourceObjectInstance,Evenement e) {
       
        super("Add Feedback", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        tb.addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, t -> previous.showBack());       
       
        TextField tfsuj= new TextField("", "sujet");
        TextField tfmsg = new TextField ("", "message");
        Button add = new Button("Add");
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                            String message = "";
                          boolean test = false;
                          
                                  
                if ((tfsuj.getText().length() == 0)) {
                    message = message + "object must be filled out \n";
                    test = true;

                }
                if ((tfmsg.getText().length() == 0)) {
                    message = message + "message must be filled out \n";
                    test = true;
                }
                
                if (test) {
                    Dialog.show("ERROR", message, new Command("OK"));
                } else {
                         Eventfeedback fb = new Eventfeedback(tfsuj.getText(), tfmsg.getText());
                       if ( ServiceEventFeedback.getInstance().addFeedback(fb, e.getIdEvent(),iduser)) {
                            Dialog.show("Success", "Feedback Added !", new Command("OK"));
                       }
                       else {
                           Dialog.show("ERROR", "Server error", new Command("OK"));
                       }
                       
                            
                } 
                       
          
            }
        });
      
         add(tfsuj);
         add(tfmsg);
         add(add);
    
        
        
    }
     
      
}

