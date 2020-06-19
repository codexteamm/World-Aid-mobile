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
public class showRequestDetailsForm extends Form {

    Form current;

    public showRequestDetailsForm(Form previous, demande_aide da) {
        current = this;
            
        setLayout(BoxLayout.y());
        BorderLayout br = new BorderLayout();

        Toolbar tb = new Toolbar(true);
        setToolbar(tb);

        tb.addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
        current = this;
        Label title = new Label();
        title.setText("Title:"+da.getTitre());
        SpanLabel description = new SpanLabel();
        description.setText("Description:"+da.getDescription());
        SpanLabel id = new SpanLabel();
        id.setText("id:"+da.getId_demande());
        add(title).add(description).add(id).setLayout(BoxLayout.y());
        Button btnupdate = new Button("Update");
        Button btndelete = new Button("Delete");
        Button btnconfirm = new Button("Confirm this request");
        btndelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Dialog.show("Confirmation","Are you sure to delete",new Command("OK"),new Command("Cancel"));
                if (ServiceRequest2.getInstance().deleteRequest(da.getId_demande()))
                    Dialog.show("Success","Request successfully deleted",new Command("OK"));
                else 
                    Dialog.show("Error","There was a problem",new Command("OK"));
            }
        });
        btnconfirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Dialog.show("Confirmation","Are you sure to confirm this request?",new Command("OK"),new Command("Cancel"));
                if (ServiceRequest2.getInstance().confirmRequest(da.getId_demande())){
                                        Dialog.show("Success","Request successfully confirmed",new Command("OK"));

                    Message m = new Message("Your Help request with the following informations"+"\n"+
                            "Title : " + da.getTitre()+ "\n" + "Description : " + da.getDescription()+"\n"+"Has been taken in charge by an association");

                    Display.getInstance().sendMessage(new String[]{"mohamedamine.daghbouji@esprit.tn"}, "Help request confirmed", m);
                    Dialog.show("Success", "Mail sent", new Command("OK"));
                    System.out.println(m);
                    System.out.println("Mail sent successfully");
                }
                else 
                    Dialog.show("Error","There was a problem",new Command("OK"));
            }
        });
        add(btnupdate).add(btndelete).add(btnconfirm).setLayout(BoxLayout.y());
        btnupdate.addActionListener(e -> new UpdateRequestForm(current,da).show());
        //btndelete.addActionListener(e -> new DeleteRequestForm(current).show());
        /*getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK,
                e -> previous.showBack()); // Revenir vers l'interface précédente*/
    }

}
