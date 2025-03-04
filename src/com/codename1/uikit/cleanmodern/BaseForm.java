/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions 
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */
package com.codename1.uikit.cleanmodern;

import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Component;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.layouts.Layout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.controller.UserController;
import com.mycompany.controller.UtilisateurService;
import static com.mycompany.controller.UtilisateurService.user;
import com.mycompany.entities.User;
import com.mycompany.gui.HomeCampementForm;
import com.mycompany.gui.HomeForm;
import static jdk.nashorn.internal.runtime.Debug.id;
import com.mycompany.gui.FeedbacksForm;
import com.mycompany.gui.RequestsForm;

/**
 * Base class for the forms with common functionality
 *
 * @author Shai Almog
 */
public class BaseForm extends Form {
    
    private static int a;
        Form current;


    public BaseForm() {
    }

    public BaseForm(Layout contentPaneLayout) {
        super(contentPaneLayout);
    }

    public BaseForm(String title, Layout contentPaneLayout) {
        super(title, contentPaneLayout);
    }

    public Component createLineSeparator() {
        Label separator = new Label("", "WhiteSeparator");
        separator.setShowEvenIfBlank(true);
        return separator;
    }

    public Component createLineSeparator(int color) {
        Label separator = new Label("", "WhiteSeparator");
        separator.getUnselectedStyle().setBgColor(color);
        separator.getUnselectedStyle().setBgTransparency(255);
        separator.setShowEvenIfBlank(true);
        return separator;
    }

    protected void addSideMenuAssociation(Resources res) {
        current=this;
        Toolbar tb = getToolbar();
        Image img = res.getImage("profile-background.jpg");
        if (img.getHeight() > Display.getInstance().getDisplayHeight() / 3) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 3);
        }
        ScaleImageLabel sl = new ScaleImageLabel(img);
        sl.setUIID("BottomPad");
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);

        tb.addComponentToSideMenu(LayeredLayout.encloseIn(
                sl,
                FlowLayout.encloseCenterBottom(
                        new Label(res.getImage("profile-pic.jpg"), "PictureWhiteBackgrond"))
        ));

       tb.addMaterialCommandToSideMenu("Profile", FontImage.MATERIAL_SETTINGS, e -> new ProfileForm(res).show());
                 
                

        tb.addMaterialCommandToSideMenu("Logout", FontImage.MATERIAL_EXIT_TO_APP, e -> new SignInForm(res).show());

        tb.addMaterialCommandToSideMenu("Delete", FontImage.MATERIAL_EXIT_TO_APP, e -> new DeleteForm(res).show());
        tb.addMaterialCommandToSideMenu("Association Home", FontImage.MATERIAL_EXIT_TO_APP, e -> new HomeCampementForm(res,current).show());    
           
        

    }
    
     protected void addSideMenuBenevole(Resources res) {
        Toolbar tb = getToolbar();
        Image img = res.getImage("profile-background.jpg");
        if (img.getHeight() > Display.getInstance().getDisplayHeight() / 3) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 3);
        }
        ScaleImageLabel sl = new ScaleImageLabel(img);
        sl.setUIID("BottomPad");
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);

        tb.addComponentToSideMenu(LayeredLayout.encloseIn(
                sl,
                FlowLayout.encloseCenterBottom(
                        new Label(res.getImage("profile-pic.jpg"), "PictureWhiteBackgrond"))
        ));


        tb.addMaterialCommandToSideMenu("Profile", FontImage.MATERIAL_SETTINGS, e ->{ new ProfilFormBen(res).show();
                 
                });

        tb.addMaterialCommandToSideMenu("Logout", FontImage.MATERIAL_EXIT_TO_APP, e -> new SignInForm(res).show());

        tb.addMaterialCommandToSideMenu("Delete", FontImage.MATERIAL_EXIT_TO_APP, e -> new DeleteForm(res).show());
        
        
        tb.addMaterialCommandToSideMenu("Volunteer", FontImage.MATERIAL_EXIT_TO_APP, e -> new HomeForm(res).show());

           
        

    }
     
     protected void addSideMenuNecessiteux(Resources res) {
         current=this;
        Toolbar tb = getToolbar();
        Image img = res.getImage("profile-background.jpg");
        if (img.getHeight() > Display.getInstance().getDisplayHeight() / 3) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 3);
        }
        ScaleImageLabel sl = new ScaleImageLabel(img);
        sl.setUIID("BottomPad");
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);

        tb.addComponentToSideMenu(LayeredLayout.encloseIn(
                sl,
                FlowLayout.encloseCenterBottom(
                        new Label(res.getImage("profile-pic.jpg"), "PictureWhiteBackgrond"))
        ));


        
        tb.addMaterialCommandToSideMenu("Profile", FontImage.MATERIAL_SETTINGS, e ->{ new ProfilFormNece(res).show();
                 
                });
        tb.addMaterialCommandToSideMenu("Requests", FontImage.MATERIAL_SETTINGS, e ->{ new RequestsForm(current,res).show();
                 
                });
        tb.addMaterialCommandToSideMenu("Feedbacks", FontImage.MATERIAL_SETTINGS, e ->{ new FeedbacksForm(current).show();
                 
                });

        tb.addMaterialCommandToSideMenu("Logout", FontImage.MATERIAL_EXIT_TO_APP, e -> new SignInForm(res).show());

        tb.addMaterialCommandToSideMenu("Delete", FontImage.MATERIAL_EXIT_TO_APP, e -> new DeleteForm(res).show());
            
           
        

    }
     
    protected void addSideMenuAdmin(Resources res) {
        Toolbar tb = getToolbar();
        Image img = res.getImage("profile-background.jpg");
        if (img.getHeight() > Display.getInstance().getDisplayHeight() / 3) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 3);
        }
        ScaleImageLabel sl = new ScaleImageLabel(img);
        sl.setUIID("BottomPad");
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);

        tb.addComponentToSideMenu(LayeredLayout.encloseIn(
                sl,
                FlowLayout.encloseCenterBottom(
                        new Label(res.getImage("profile-pic.jpg"), "PictureWhiteBackgrond"))
        ));


        tb.addMaterialCommandToSideMenu("Profile", FontImage.MATERIAL_SETTINGS, e ->{ new AdminForm(res).show();
                 
                });

        tb.addMaterialCommandToSideMenu("Logout", FontImage.MATERIAL_EXIT_TO_APP, e -> new SignInForm(res).show());

        tb.addMaterialCommandToSideMenu("Delete", FontImage.MATERIAL_EXIT_TO_APP, e -> new DeleteForm(res).show());
            
           
        

    } 
     
     

}
