/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;
import com.codename1.uikit.cleanmodern.BaseForm;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextComponent;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.Layout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entites.Campement;
import com.mycompany.myapp.services.ServiceCampement;

/**
 *
 * @author HP
 */
public class EditForm extends BaseForm {

    private static final Toolbar.BackCommandPolicy BACK_POLICY = Toolbar.BackCommandPolicy.AS_ARROW;
    Form current;

    public EditForm(Form previous, Campement campement, Resources resourceObjectInstance) {
        current = this;
        setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        getToolbar().setBackCommand("Results", BACK_POLICY, e -> previous.showBack());
        
        TextComponent description = new TextComponent().label("Description").multiline(true).text(campement.getDescription());
        
        add(description);
        Button editb = new Button("edit");
        add(editb);
        editb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent t) {
                if (ServiceCampement.getInstance().updateCamps(campement.getId(), description.getText())) {
                    campement.setDescription(description.getText());
                    new showCampDetails(current, resourceObjectInstance, campement);
                } else {
                    Dialog.show("ERROR", "there is something wrong", new Command("OK"));
                    
                }
            }
        });
    }
    
}
