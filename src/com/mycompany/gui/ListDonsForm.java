/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.ParseException;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entites.DonMateriel;
import com.mycompany.myapp.services.DonMaterielController;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ASUS
 */
public class ListDonsForm extends Form{
    public int idEvent=0;
    
    public ListDonsForm(Form previous,Resources theme, int idevent) {
       super("Donation List",BoxLayout.y());
       idEvent=idevent;
       
       Toolbar tb = new Toolbar(true);
       setToolbar(tb);
       tb.addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());       Container toolbarC = new Container(new BoxLayout(BoxLayout.X_AXIS));
       TextField zoneRecherche = new TextField();
       zoneRecherche.setHint("Search");
       Button boutonRecherche = new Button("Ok");
       toolbarC.add(boutonRecherche);
       toolbarC.add(zoneRecherche);
       add(toolbarC);
       DonMaterielController dc = new DonMaterielController();
       Container container = new Container(new BoxLayout(BoxLayout.Y_AXIS));
       ConnectionRequest con = new ConnectionRequest();
       con.setUrl("http://localhost/pidev_world-aid/web/app_dev.php/allDonsMobile");
       con.addResponseListener(new ActionListener<NetworkEvent>() {
              @Override
            public void actionPerformed(NetworkEvent evt) {
                

                  try {
                      ArrayList<DonMateriel> listDons = getListDons(new String(con.getResponseData()));
                      for (int i = 0; i < listDons.size(); i++) {
                          System.out.println(listDons.get(i).getId_evenement()+"=========="+idevent);
  
                          
                          container.add(Createcontainer(listDons.get(i), dc, previous, theme));
                     }
                      refreshTheme();
                  } catch (ParseException ex) {
                      
                  } catch (IOException ex) {
                      
                  }
            }
        });
        NetworkManager.getInstance().addToQueue(con);
        //   add(toolbarC);

        // add(container1);
        add(container);

        refreshTheme();

}
    
    public ArrayList<DonMateriel> getListDons(String json) throws ParseException, IOException {
         ArrayList<DonMateriel> dons = new ArrayList<>();
         JSONParser j = new JSONParser();
         Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(json.toCharArray()));
         List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
         System.out.println(list);
      for (Map<String, Object> obj : list) {
            //Création des tâches et récupération de leurs données
            
         DonMateriel d = new DonMateriel();
         String date = obj.get("dateDon").toString();
         String value = date.substring(6, 16);
         d.setDate_don(value);
         d.setType_Materiel(obj.get("typeMateriel").toString());
         //float id = Float.parseFloat(obj.get("id_evenement").toString());
        // d.setId_evenement((int)id);
         float reference = Float.parseFloat(obj.get("reference").toString());
         d.setReference((int)reference);
         d.setQuantite((double)Float.parseFloat(obj.get("quantite").toString()));
         dons.add(d);
        }
     return dons;
    }
      
    public Container Createcontainer(DonMateriel d, DonMaterielController dc, Form previous, Resources theme) {
        Label Reference = new Label("Reference : "+d.getReference());
        Label Quantity = new Label("Quantity : " + d.getQuantite());
        Label typeMateriel = new Label("Type Materiel : " + d.getType_Materiel());
        Label DateDon = new Label("Donation Date : "+d.getDate_don());
        Button btn = new Button("Update");
        Button btn2 = new Button("Delete");

        Container ctn = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        ctn.add(Reference);
        ctn.add(Quantity);
        ctn.add(typeMateriel);
        ctn.add(DateDon);
        Container ctn2 = BorderLayout.center(ctn);
        ctn2.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        ctn2.add(btn);
        ctn2.add(btn2);

        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (Dialog.show("Confirmation", "Are you sure ?", "ok", "cancel")) {
                new updateDonForm(previous, theme, d).show();                }
            }
        });

        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {

                if (Dialog.show("Confirmation", "Are you sure ?", "ok", "cancel")) {
                    dc.deleteDon(d.getReference());
                }
                new ListDonsForm(previous, theme, idEvent).show();

            }
        });

        return ctn2;
       }
    
}
