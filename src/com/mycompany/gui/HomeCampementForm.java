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
//import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.codename1.uikit.cleanmodern.BaseForm;

/**
 *
 * @author bhk
 */
public class HomeCampementForm extends BaseForm {

//    public HomeCampementForm() {
//        this(com.codename1.ui.util.Resources.getGlobalResources());
//    }
    Form current;
    private Resources theme;

    public HomeCampementForm(com.codename1.ui.util.Resources resourceObjectInstance, Form previous) {

        super("Add campement", BoxLayout.y());
        setLayout(BoxLayout.y());
        BorderLayout br = new BorderLayout();

        Toolbar tb = new Toolbar(true);
        setToolbar(tb);

        tb.addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
        current = this;
        setTitle("Home");
        setLayout(BoxLayout.y());
        //installSidemenu(resourceObjectInstance);

        add(new Label("Choose an option"));
        Button btnAddTask = new Button("Add camp");
        Button btnListTasks = new Button("List camp");
        Button btnList = new Button("List supported camp");
        Button btnListRequests = new Button("Pending Requests list");

        btnListRequests.addActionListener(e -> new ListRequestsForm2(current, resourceObjectInstance).show());
        btnAddTask.addActionListener(e -> new AddCampementForm(current).show());
        btnListTasks.addActionListener(e -> new ListCapementForm(current, resourceObjectInstance, "").show());
        btnList.addActionListener(e -> new ListsupportedCamp(current, resourceObjectInstance).show());

        Button btnAddevent = new Button("Add An Event");
        Button btnListevent = new Button("List My Events");
        Button btnListEvents = new Button("List All Events");

        btnAddevent.addActionListener(e -> new AddEventForm(current, theme).show());
        btnListevent.addActionListener(e -> new AfficherMyEvenement(current, theme).show());
        btnListEvents.addActionListener(e -> new AfficherAllEvents(current, theme).show());

        addAll(btnAddTask, btnListTasks, btnList, btnAddevent, btnListevent, btnListEvents, btnListRequests);

    }

}
