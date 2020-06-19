/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.WebBrowser;
import com.codename1.ui.BrowserComponent;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.util.Resources;

/**
 *
 * @author ASUS
 */
public class addDonPayementForm extends Form{
    Resources theme;
    Form current;
    private static final Toolbar.BackCommandPolicy BACK_POLICY = Toolbar.BackCommandPolicy.AS_ARROW;
    public addDonPayementForm(Form previous, Resources resourceObjectInstance) {
                   Toolbar tb = new Toolbar(true);
        setToolbar(tb);

        tb.addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
setLayout(new BorderLayout());
 WebBrowser browser = new WebBrowser(){
    @Override
    public void onLoad(String url) {
        // Placed on onLoad because we need to wait for page 
        // to load to interact with it.
        
        BrowserComponent c = (BrowserComponent)this.getInternal();
    } 
};
addComponent(BorderLayout.CENTER, browser);

browser.setURL("jar:///Checkout.html");

       


show();
    }
}
