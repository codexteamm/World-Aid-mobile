/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

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
import com.mycompany.myapp.entites.feedback;
import com.mycompany.myapp.services.ServiceFeedback2;

/**
 *
 * @author HP
 */
public class UpdateFeedbackForm extends Form{
    Form current;
    
    public UpdateFeedbackForm(Form previous, feedback fb){
        current =this;
        setLayout(BoxLayout.y());
        BorderLayout br = new BorderLayout();

        Toolbar tb = new Toolbar(true);
        setToolbar(tb);

        tb.addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
        current = this;
        ComboBox cbtitle =new ComboBox();
        cbtitle.addItem("Staff");
        cbtitle.addItem("Website");
        cbtitle.addItem("Bug");
        cbtitle.addItem("Report");
        TextField tfMessage = new TextField();
        tfMessage.setText(fb.getMessage());
        Button btnValider= new Button("Update");
        add(cbtitle).add(tfMessage).add(btnValider).setLayout(BoxLayout.y());
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if(cbtitle.getSelectedItem().toString().length()==0 || tfMessage.getText().length()==0){
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                }
                else{
                    feedback fb2 = new feedback(fb.getId_feedback(),cbtitle.getSelectedItem().toString(),tfMessage.getText());
                    if( ServiceFeedback2.getInstance().updateFeedback(fb2)){
                        Dialog.show("Success", "Feedback edited successfully", new Command("OK"));
                    }
                    
                }
            }
        });
        /*getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK
                , e-> previous.showBack()); // Revenir vers l'interface précédente*/
    }
}
