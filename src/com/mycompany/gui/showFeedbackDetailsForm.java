/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.SpanLabel;
import com.codename1.io.ConnectionRequest;
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
import com.mycompany.myapp.entites.feedback;
import com.mycompany.myapp.services.ServiceFeedback2;

/**
 *
 * @author HP
 */
public class showFeedbackDetailsForm extends Form {

    Form current;

    public showFeedbackDetailsForm(Form previous, feedback fb) {
        current = this;
        setLayout(BoxLayout.y());
        BorderLayout br = new BorderLayout();

        Toolbar tb = new Toolbar(true);
        setToolbar(tb);

        tb.addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
        current = this;
        Label title = new Label();
        title.setText("Title:" + fb.getTitre());
        SpanLabel message = new SpanLabel();
        message.setText("Message:" + fb.getMessage());
        SpanLabel id = new SpanLabel();
        id.setText("id:" + fb.getId_feedback());
        add(title).add(message).add(id).setLayout(BoxLayout.y());
        Button btnupdate = new Button("Update");
        Button btndelete = new Button("Delete");
        btndelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Dialog.show("Confirmation", "Are you sure to delete", new Command("OK"), new Command("Cancel"));
                if (ServiceFeedback2.getInstance().deleteFeedback(fb.getId_feedback())) {
                    Dialog.show("Success", "Feedback successfully deleted", new Command("OK"));
                } else {
                    Dialog.show("Error", "There was a problem", new Command("OK"));
                }
            }
        });

        
        
        add(btnupdate).add(btndelete).setLayout(BoxLayout.y());
        btnupdate.addActionListener(e -> new UpdateFeedbackForm(current, fb).show());
        /*getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK,
                e -> previous.showBack()); // Revenir vers l'interface précédente*/
    }

}
