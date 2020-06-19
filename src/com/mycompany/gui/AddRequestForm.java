/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.messaging.Message;
import com.codename1.notifications.LocalNotification;
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
import com.mycompany.myapp.entites.demande_aide;
import com.mycompany.myapp.services.ServiceRequest2;
import java.util.Calendar;

/**
 *
 * @author bhk
 */
public class AddRequestForm extends Form {
    Form current;

    public AddRequestForm(Form previous) {
        setLayout(BoxLayout.y());
        BorderLayout br = new BorderLayout();

        Toolbar tb = new Toolbar(true);
        setToolbar(tb);

        tb.addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
        current = this;
        /*
        Le paramètre previous définit l'interface(Form) précédente.
        Quelque soit l'interface faisant appel à AddTask, on peut y revenir
        en utilisant le bouton back
         */
        setTitle("Add a new request");
        setLayout(BoxLayout.y());
        ComboBox titleCb = new ComboBox();
        titleCb.addItem("Money");
        titleCb.addItem("Food");
        titleCb.addItem("Accomodation");
        titleCb.addItem("Clothes");
        //TextField tfTitle = new TextField("","Request Title");
        TextField tfDescription = new TextField("", "Request Description");
        Button btnValider = new Button("Add request");

        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((titleCb.getSelectedItem().toString().length() == 0) || (tfDescription.getText().length() == 0)) {
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                } else {
                    demande_aide da = new demande_aide(titleCb.getSelectedItem().toString(), tfDescription.getText());
                    da.setIdCassocial(3);
                    //da.setIdCassocial(1);
                    System.out.println(da);
                    if (ServiceRequest2.getInstance().addRequest(da)) {
                        //Dialog.show("Success", "Connection accepted", new Command("OK"));
                        Dialog.show("Success", "Request successfully added", new Command("OK"));

                        

                        LocalNotification n = new LocalNotification();
                        n.setId("demo-notification");
                        n.setAlertBody("Your request will be viewed by admins in the shortest delays");
                        n.setAlertTitle("Feedback sent!");
                        //n.setAlertSound("/notification_sound_bells.mp3"); //file name must begin with notification_sound
                        long time = Calendar.getInstance().getTime().getTime() + 7000;
                        //Display.getInstance().scheduleLocalNotification(n,time, LocalNotification.REPEAT_MINUTE // Whether to repeat and what frequency
                        //);
                        Display.getInstance().scheduleLocalNotification(n,System.currentTimeMillis() + 10 * 1000, LocalNotification.REPEAT_MINUTE // Whether to repeat and what frequency
                        );
                        System.out.println("Received local notification " + n.getId());
                    } else {
                        Dialog.show("ERROR", "Server error", new Command("OK"));
                    }
                }

            }
        });

        addAll(titleCb, tfDescription, btnValider);
        //getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK,
                //e -> previous.showBack()); // Revenir vers l'interface précédente

    }

}
