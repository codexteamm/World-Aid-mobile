/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.MultiButton;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.ComponentGroup;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entites.Campement;
import com.mycompany.myapp.services.ServiceCampement;
import java.util.ArrayList;
import com.codename1.uikit.cleanmodern.BaseForm;


/**
 *
 * @author bhk
 */
public class ListCapementForm extends BaseForm {

    Resources theme;
    Form current;
    public ArrayList<Campement> campements = null;
            


    private static final Toolbar.BackCommandPolicy BACK_POLICY = Toolbar.BackCommandPolicy.AS_ARROW;
    public ListCapementForm(Form previous, Resources resourceObjectInstance,String text){
                super("camps", BoxLayout.y());
                System.out.println("2222222222222222222222222222222222");
        current = this;
        theme = resourceObjectInstance;
     
        setLayout(BoxLayout.y());
        BorderLayout br = new BorderLayout();

        Toolbar tb = new Toolbar(true);
        setToolbar(tb);

        tb.addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
        current = this;

        
                    /*********************************************/
                    final TextField search = new TextField("", "Search", 20, TextArea.ANY);

              Button go = new Button("Go");
       // Button myLocation = new Button("My Location");
        ComponentGroup gp = ComponentGroup.enclose(search, go);
        
        // this allows the "Done" button in the virtual keyboard to perform the search and also binds to the Go button.
        ActionListener plainTextSearch = evt -> {
           
            if(search.getText().length() > 1) {
                new ListCapementForm(previous, resourceObjectInstance, search.getText()).show();
            } else {
                Dialog.show("Error", "You need to enter a search string", "OK", null);
                new ListCapementForm(previous, resourceObjectInstance, "").show();
            }
           
        };
        
                search.addActionListener(plainTextSearch);

        add( gp);
        //go.addActionListener(plainTextSearch);
                
   
        
            /********************************************************/
            

        
        
        
        
        
            campements = ServiceCampement.getInstance().getAllCampement();
            
             ArrayList<Campement> listClone = new ArrayList<Campement>(); 
           for (Campement c : campements) {
               if(c.getNom().toLowerCase().contains(text.toLowerCase())){
                   listClone.add(c);
               }
           }
            
            
        
        //Component[] listingsToAdd = new Component[campements.size()];
        for (Campement c : listClone) {

            MultiButton twoLinesNoIcon = new MultiButton(c.getNom());
            twoLinesNoIcon.setTextLine2(c.getDescription());
            twoLinesNoIcon.setIcon(theme.getImage("camp.png"));
            twoLinesNoIcon.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    new showCampDetails(current, theme, c).show();

                }
            });
            /*twoLinesNoIcon.addPointerPressedListener();*/
        add(twoLinesNoIcon);
        }
            

        
        
    }

}
