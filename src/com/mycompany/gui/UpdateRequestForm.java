/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.MultiButton;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
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


/**
 *
 * @author HP
 */
public class UpdateRequestForm extends Form{
    Form current;
    
    public UpdateRequestForm(Form previous, demande_aide da){
        current =this;
        setLayout(BoxLayout.y());
        BorderLayout br = new BorderLayout();

        Toolbar tb = new Toolbar(true);
        setToolbar(tb);

        tb.addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
        current = this;
        ComboBox cbtitle =new ComboBox();
        cbtitle.addItem("Food");
        cbtitle.addItem("Money");
        cbtitle.addItem("Accomodation");
        cbtitle.addItem("Clothes");
        TextField tfDescription = new TextField();
        tfDescription.setText(da.getDescription());
        Button btnValider= new Button("Update");
        add(cbtitle).add(tfDescription).add(btnValider).setLayout(BoxLayout.y());
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if(cbtitle.getSelectedItem().toString().length()==0 || tfDescription.getText().length()==0){
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                }
                else{
                    demande_aide da2 = new demande_aide(da.getId_demande(),cbtitle.getSelectedItem().toString(),tfDescription.getText());
                    if( ServiceRequest2.getInstance().updateRequest(da2)){
                        Dialog.show("Success", "Request edited successfully", new Command("OK"));
                    }
                    
                }
            }
        });
        /*getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK
                , e-> previous.showBack()); // Revenir vers l'interface précédente*/
    }
}
