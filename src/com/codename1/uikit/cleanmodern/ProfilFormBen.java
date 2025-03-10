/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.cleanmodern;

import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import com.codename1.ui.Display;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import static com.codename1.uikit.cleanmodern.SignInForm.user;
import static com.codename1.uikit.cleanmodern.SignInForm.x;
import com.mycompany.controller.UserController;
import com.mycompany.controller.UtilisateurService;
import com.mycompany.entities.User;

/**
 *
 * @author DELL
 */
public class ProfilFormBen extends BaseForm {

    UtilisateurService us = new UtilisateurService();
    UserController uc = new UserController();
    User u = new User();
    public static int var;
    private boolean a;
    
    public ProfilFormBen(Resources res) {
        super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Welcome Benevole");
        getContentPane().setScrollVisible(false);

        super.addSideMenuBenevole(res);

        tb.addSearchCommand(e -> {
        });

        Image img = res.getImage("profile-background.jpg");
        if (img.getHeight() > Display.getInstance().getDisplayHeight() / 3) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 3);
        }

        ScaleImageLabel sl = new ScaleImageLabel(img);
        sl.setUIID("BottomPad");
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);

        Label facebook = new Label("786 followers", res.getImage("facebook-logo.png"), "BottomPad");
        Label twitter = new Label("486 followers", res.getImage("twitter-logo.png"), "BottomPad");
        facebook.setTextPosition(BOTTOM);
        twitter.setTextPosition(BOTTOM);
        System.out.println(user);
        add(LayeredLayout.encloseIn(
                sl,
                BorderLayout.south(
                        GridLayout.encloseIn(3,
                                facebook,
                                FlowLayout.encloseCenter(
                                        new Label(res.getImage("profile-pic.jpg"), "PictureWhiteBackgrond")),
                                twitter
                        )
                )
        ));

        TextField username = new TextField("", user.getUsername(), 20, TextField.EMAILADDR);
        username.setUIID("TextFieldBlack");
        addStringValue("Username", username);

        TextField email = new TextField("", user.getEmail(), 20, TextField.EMAILADDR);
        email.setUIID("TextFieldBlack");
        addStringValue("E-Mail", email);

        TextField password = new TextField("", user.getPassword(), 20, TextField.PASSWORD);
        password.setUIID("TextFieldBlack");
        addStringValue("Password", password);

        TextField nom = new TextField("", user.getNom(), 20, TextField.EMAILADDR);
        nom.setUIID("TextFieldBlack");
        addStringValue("Nom", nom);

        TextField prenom = new TextField("", user.getPrenom(), 20, TextField.EMAILADDR);
        prenom.setUIID("TextFieldBlack");
        addStringValue("Prenom", prenom);

        TextField logo = new TextField("", user.getLogo(), 20, TextField.EMAILADDR);
        logo.setUIID("TextFieldBlack");
        addStringValue("Logo", logo);

//        TextField rib = new TextField("", user.getRib(), 20, TextField.EMAILADDR);
//        rib.setUIID("TextFieldBlack");
//        addStringValue("Rib", rib);

        TextField pays = new TextField("", user.getPays(), 20, TextField.EMAILADDR);
        pays.setUIID("TextFieldBlack");
        addStringValue("Pays", pays);

//        TextField numero = new TextField("", user.getNumero(), 20, TextField.EMAILADDR);
//        numero.setUIID("TextFieldBlack");
//        addStringValue("Numero", numero);
//
//        TextField adresse = new TextField("", user.getAdresse(), 20, TextField.EMAILADDR);
//        adresse.setUIID("TextFieldBlack");
//        addStringValue("Adresse", adresse);
//
//        TextField categorie = new TextField("", user.getCategorie(), 20, TextField.EMAILADDR);
//        categorie.setUIID("TextFieldBlack");
//        addStringValue("Categorie", categorie);
//
//        TextField description = new TextField("", user.getDescription(), 20, TextField.EMAILADDR);
//        description.setUIID("TextFieldBlack");
//        addStringValue("Descritpion", description);

        TextField role = new TextField("", user.getRole(), 20, TextField.EMAILADDR);
        role.setUIID("TextFieldBlack");
        addStringValue("Role", role);
        
       Button update = new Button("Update");
       add(update);
        update.addActionListener(e -> {
            User u = new User();
            
            
            u.setUsername(username.getText());
            u.setEmail(email.getText());
            u.setPassword(password.getText());
            u.setNom(nom.getText());
            u.setPrenom(prenom.getText());
            u.setLogo(logo.getText());
//            u.setRib(rib.getText());
            u.setPays(pays.getText());
//            u.setNumero(numero.getText());
//            u.setAdresse(adresse.getText());
//            u.setCategorie(categorie.getText());
//            u.setDescription(description.getText());
//            u.setRole(role.getText());
            

            a = UtilisateurService.getInstance().updatBen(u, x);

        });

//        CheckBox cb1 = CheckBox.createToggle(res.getImage("on-off-off.png"));
//        cb1.setUIID("Label");
//        cb1.setPressedIcon(res.getImage("on-off-on.png"));
//        CheckBox cb2 = CheckBox.createToggle(res.getImage("on-off-off.png"));
//        cb2.setUIID("Label");
//        cb2.setPressedIcon(res.getImage("on-off-on.png"));
//        
//        addStringValue("Facebook", FlowLayout.encloseRightMiddle(cb1));
//        addStringValue("Twitter", FlowLayout.encloseRightMiddle(cb2));
    }

    private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel")).
                add(BorderLayout.CENTER, v));
        add(createLineSeparator(0xeeeeee));
    }

}
