/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;

/**
 *
 * @author HP
 */
public class FeedbacksForm extends Form{
    Form current;
    Resources resourceObjectInstance;
    
    /*Garder traçe de la Form en cours pour la passer en paramètres 
    aux interfaces suivantes pour pouvoir y revenir plus tard en utilisant
    la méthode showBack*/
    public FeedbacksForm(Form previous) {
                //super("Add campement", BoxLayout.y());
        setLayout(BoxLayout.y());
        BorderLayout br = new BorderLayout();

        Toolbar tb = new Toolbar(true);
        setToolbar(tb);

        tb.addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
        current = this;
        current = this; //Récupération de l'interface(Form) en cours
        setTitle("Home");
        setLayout(BoxLayout.y());

        add(new Label("Choose an option"));
        Button btnAddFeedback = new Button("Add Feedback");
        Button btnListFeedbacks = new Button("List Feedbacks");
        Button btnDeleteFeedback = new Button("Delete Feedback");

        
        btnAddFeedback.addActionListener(e -> new AddFeedbackForm(current).show());
        btnListFeedbacks.addActionListener(e -> new ListFeedbacksForm(current,resourceObjectInstance).show());
        addAll(btnAddFeedback, btnListFeedbacks);
        /*getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK,
                 e -> previous.showBack()); // Revenir vers l'interface précédente*/

    }
}
