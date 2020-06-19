/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.controller.UserController;
import com.mycompany.myapp.entites.DonMateriel;
import com.mycompany.myapp.entites.Evenement;
import com.mycompany.myapp.services.ServiceTask;

/**
 *
 * @author ASUS
 */
public class addDonForm extends Form {
    
      int iduser = UserController.getInstance().User.getId();
      int idEvent=0 ;
    public addDonForm(Form previous,Resources resourceObjectInstance,Evenement e ){
        
         super(new BorderLayout());
         idEvent=e.getIdEvent();
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        tb.setUIID("Container");
        getTitleArea().setUIID("Container");
        setUIID("SignIn");
                tb.addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, t -> previous.showBack());
        setTitle("Add a new Don");
        setLayout(BoxLayout.y());
     
        TextField tfQuantite = new TextField("", "quantite");
        ComboBox typeMat = new ComboBox("", "Medicaments", "Vetements", "Hebergements", "Alimentations");
        Button btnValider = new Button("Add Don");

         btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                 double quantite = Double.parseDouble(tfQuantite.getText());
                 DonMateriel d = new DonMateriel(typeMat.getSelectedItem().toString(),quantite);
                 ServiceTask.getInstance().addDon(d,idEvent, iduser);
                 if(typeMat.getSelectedItem().toString().isEmpty()){
                     Dialog.show("Alert", "Please Fill In The Form", new Command("Ok"));
                 }
                 else{
                 Dialog.show("Info", "Donation Added!", new Command("Ok"));
                 }
                 System.out.println(e.getIdEvent());
            }
         });
         add(tfQuantite);
         add(typeMat);
         add(btnValider);
    }
}
