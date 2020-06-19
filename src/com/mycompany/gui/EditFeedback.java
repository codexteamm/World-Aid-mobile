/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextComponent;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entites.Eventfeedback;
import com.mycompany.myapp.services.ServiceEventFeedback;

/**
 *
 * @author hamza
 */
public class EditFeedback extends Form {
    
    private static final Toolbar.BackCommandPolicy BACK_POLICY = Toolbar.BackCommandPolicy.AS_ARROW;
    Form current;

    public EditFeedback(Form previous, Eventfeedback fb, Resources resourceObjectInstance) {
        current = this;
        setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        getToolbar().setBackCommand("Results", BACK_POLICY, e -> previous.showBack());
        Label suj = new Label("Sujet");
        Label msg = new Label("Message");
        TextComponent sujet = new TextComponent().multiline(true).text(fb.getSujet());
        TextComponent message = new TextComponent().multiline(true).text(fb.getMessage());
        add(suj);
        add(sujet);
        add(msg);
        add(message);
        Button editb = new Button("edit");
        add(editb);
        editb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent t) {
                if (ServiceEventFeedback.getInstance().editfeedback(fb.getId(), sujet.getText() ,message.getText())) {
                    fb.setSujet(sujet.getText());
                    fb.setMessage(message.getText());
                   Dialog.show("success", "feedback edited", new Command("OK"));
                } else {
                    Dialog.show("ERROR", "there is something wrong", new Command("OK"));
                    
                }
            }
        });
    }
    
}
