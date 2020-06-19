/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.io.ConnectionRequest;
import com.codename1.ui.Button;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.codename1.uikit.cleanmodern.BaseForm;

/**
 *
 * @author bhk
 */
public class RequestsForm extends BaseForm {

    Form current;
    Resources resourceObjectInstance;

    /*Garder traçe de la Form en cours pour la passer en paramètres 
    aux interfaces suivantes pour pouvoir y revenir plus tard en utilisant
    la méthode showBack*/
    public RequestsForm(Form previous,Resources res) {
        resourceObjectInstance=res;
        current = this; //Récupération de l'interface(Form) en cours
        setTitle("Home");
        setLayout(BoxLayout.y());

        add(new Label("Choose an option"));
        Button btnAddRequest = new Button("Add Request");
        Button btnListRequests = new Button("List Requests");
        Button btnListRequests2 = new Button("List Requests association");
        

        Button imprimer = new Button("Print all requests");

        imprimer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                // ToastBar.showMessage("j'aime effectué ...", FontImage.MATERIAL_FAVORITE);
                ConnectionRequest req = new ConnectionRequest();
                Display.getInstance().execute("http://localhost/pidev_world-aid/web/app_dev.php/necessiteux/pdf");
            }
        });
        btnAddRequest.addActionListener(e -> new AddRequestForm(current).show());
        btnListRequests.addActionListener(e -> new ListRequestsForm(current, resourceObjectInstance).show());
        btnListRequests2.addActionListener(e -> new ListRequestsForm2(current, resourceObjectInstance).show());
        addAll(btnAddRequest, btnListRequests,btnListRequests2,imprimer);
        /*getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK,
                e -> previous.showBack()); // Revenir vers l'interface précédente*/
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Welcome Benevole");
        getContentPane().setScrollVisible(false);

        super.addSideMenuNecessiteux(res);

        tb.addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK,
                e -> previous.showBack());
    }

}
