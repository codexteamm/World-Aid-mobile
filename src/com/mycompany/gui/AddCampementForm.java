/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.WebBrowser;
import com.codename1.io.ConnectionRequest;
import com.codename1.javascript.JSFunction;
import com.codename1.javascript.JSObject;
import com.codename1.javascript.JavascriptContext;
import com.codename1.ui.BrowserComponent;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextComponent;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entites.Campement;
import com.mycompany.myapp.services.ServiceCampement;
import java.util.ArrayList;
import com.codename1.uikit.cleanmodern.BaseForm;
/**
 *
 * @author bhk
 */
public class AddCampementForm extends BaseForm {

    double latitude = 0;
    double longitude = 0;

    public AddCampementForm(Form previous) {
     //   setTitle("Add a new camp");
        super("Add campement", BoxLayout.y()); 
        setLayout(BoxLayout.y());
        BorderLayout br = new BorderLayout();
        
           Toolbar tb = new Toolbar(true);
        setToolbar(tb);

        tb.addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
        TextComponent tfnom = new TextComponent().label("nom").multiline(true);
        TextComponent tfdescription = new TextComponent().label("description").multiline(true);
        TextComponent tfpaye = new TextComponent().label("country").multiline(true);
 
        

        Button btnValider = new Button("Add");


        WebBrowser browser;
        browser = new WebBrowser() {
            @Override
            public void onLoad(String url) {
                // Placed on onLoad because we need to wait for page
                // to load to interact with it.
                BrowserComponent c = (BrowserComponent) this.getInternal();

                // Create a Javascript context for this BrowserComponent
                JavascriptContext ctx = new JavascriptContext(c);
                JSObject window = (JSObject) ctx.get("window");
                window.set("addAsync", new JSFunction() {

                    public void apply(JSObject self, final Object[] args) {
                        latitude = (Double) args[0];
                        longitude = (Double) args[1];
                        JSObject callback = (JSObject) args[2];
                        System.out.println(latitude);
                        System.out.println(longitude);

                        System.out.println();
                        tfpaye.text(ServiceCampement.getInstance().getcoutrty(latitude, longitude));
                    }
                });
            }
        };

        browser.setURL("file:///C:/Users/HP/Desktop/pages/addmaps.html");

        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                String message = "";
                boolean test = false;

                if(namealradyexist(tfnom.getText())){
                   message = message + "name already used  \n";
                    test = true;
                }
                        
                if ((tfnom.getText().length() == 0)) {
                    message = message + "Name must be filled out \n";
                    test = true;

                }
                if ((tfdescription.getText().length() == 0)) {
                    message = message + "Message must be filled out \n";
                    test = true;
                }
                if (longitude == 0) {
                    message = message + "Please select a place in the map\n";

                    test = true;

                }

                if (test) {
                    Dialog.show("ERROR", message, new Command("OK"));
                } else {
                    try {
                        Campement c = new Campement(tfnom.getText(), latitude,longitude, tfdescription.getText(), tfpaye.getText());
                        System.out.println(c);
                        // Campement t = new Campement(Integer.parseInt(tfStatus.getText()), tfName.getText());
                        if (ServiceCampement.getInstance().addCampement(c)) {
                            Dialog.show("Success", "camp added", new Command("OK"));
                        } else {
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                        }
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }

                }

            }

            
        });

        addAll(tfnom, tfdescription, tfpaye, btnValider);
        addComponent(browser);


    }
private boolean namealradyexist(String text) {
                 ArrayList<com.mycompany.myapp.entites.Campement> campements= ServiceCampement.getInstance().getAllCampement();
                  for (Campement c : campements) {
                  if (c.getNom().equals(text)){
                      System.out.println("true");
                      return true ;
                      
                  }
                  }
                 return false;
            }
}
