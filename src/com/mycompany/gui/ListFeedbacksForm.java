/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.MultiButton;
import com.codename1.ui.Component;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entites.feedback;
import com.mycompany.myapp.services.ServiceFeedback2;
import java.util.ArrayList;

/**
 *
 * @author HP
 */
public class ListFeedbacksForm extends Form{
    private Resources theme;
    Form current;
    

    public ListFeedbacksForm(Form previous, Resources resourceObjectInstance) {
        super("Feedbacks", BoxLayout.y());
        setLayout(BoxLayout.y());
        BorderLayout br = new BorderLayout();

        Toolbar tb = new Toolbar(true);
        setToolbar(tb);

        tb.addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
        current = this;
        
        current = this;
        theme = resourceObjectInstance;
        //getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
        ArrayList<com.mycompany.myapp.entites.feedback> LFeedbacks = ServiceFeedback2.getInstance().getAllFeedbacks();

        Component[] listingsToAdd = new Component[LFeedbacks.size()];
        for (feedback fb : LFeedbacks) {

            MultiButton twoLinesNoIcon = new MultiButton(fb.getTitre());
            twoLinesNoIcon.setTextLine2(fb.getMessage());
            //twoLinesNoIcon.setIcon(theme.getImage("camp.png"));
            twoLinesNoIcon.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    new showFeedbackDetailsForm(current,fb).show();

                }
            });
            add(twoLinesNoIcon);

        }
    }
}
