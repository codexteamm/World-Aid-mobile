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
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entites.DonMateriel;
import com.mycompany.myapp.services.DonMaterielController;

/**
 *
 * @author ASUS
 */
public class updateDonForm extends Form {
    
    private static final Toolbar.BackCommandPolicy BACK_POLICY = Toolbar.BackCommandPolicy.AS_ARROW;
    public updateDonForm(Form previous,Resources theme,DonMateriel d){
        setTitle("Update " + d.getType_Materiel());
 Toolbar tb = new Toolbar(true);
        setToolbar(tb);

        tb.addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
        Label ltype = new Label("Material Type :");
        ComboBox typeMat = new ComboBox("", "Medicaments", "Vetements", "Hebergements", "Alimentations");
        Label lquantite = new Label("Quantity :");
        TextField tQuantite = new TextField("","Quantity");
        Button modif = new Button("Update");
        typeMat.setSelectedItem(d.getType_Materiel());
        tQuantite.setText(String.valueOf(d.getQuantite()));
        
        add(ltype);
        add(typeMat);
        add(lquantite);
        add(tQuantite);
        add(modif);
        DonMaterielController dm = new DonMaterielController();
        modif.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
        d.setType_Materiel(typeMat.getSelectedItem().toString());
        double quantite = Double.parseDouble(tQuantite.getText());
        d.setQuantite(quantite);
        dm.UpdateDon(d);
        if((typeMat.getSelectedItem().toString().isEmpty()) || (tQuantite.getText().toString().isEmpty())){
            Dialog.show("Alert", "Please Fill In The Form", new Command("Ok"));
        }
       else{
        Dialog.show("Info", "Donation Updated!", new Command("Ok"));
        //new ListDonsForm(previous,theme);
        }
        
            }
        });
    }
}
