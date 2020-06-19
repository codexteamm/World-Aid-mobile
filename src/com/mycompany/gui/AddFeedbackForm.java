/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

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
import com.mycompany.myapp.entites.feedback;
import com.mycompany.myapp.services.ServiceFeedback2;

/**
 *
 * @author HP
 */
public class AddFeedbackForm extends Form {
    Form current;
    public AddFeedbackForm(Form previous) {
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
        setTitle("Add a new feedback");
        setLayout(BoxLayout.y());
        ComboBox titleCb = new ComboBox();
        titleCb.addItem("Staff");
        titleCb.addItem("Website");
        titleCb.addItem("Bugs");
        titleCb.addItem("Report");
        TextField tfMessage = new TextField("", "Feedback message");
        Button btnValider = new Button("Add feedback");

        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((titleCb.getSelectedItem().toString().length() == 0) || (tfMessage.getText().length() == 0)) {
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                } else {
                    feedback f = new feedback(titleCb.getSelectedItem().toString(), tfMessage.getText());
                    f.setIdCassocial(3);
                    System.out.println(f);
                    if (ServiceFeedback2.getInstance().addFeedback(f)) {
                        Dialog.show("Success", "Feedback successfully added", new Command("OK"));
                        
                        
                    } else {
                        Dialog.show("ERROR", "Server error", new Command("OK"));
                    }
                }

            }
        });

        addAll(titleCb, tfMessage, btnValider);
        /*getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK,
                 e -> previous.showBack()); // Revenir vers l'interface précédente*/

    }
}
