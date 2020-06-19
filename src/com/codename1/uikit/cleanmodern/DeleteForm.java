/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.cleanmodern;

import com.codename1.components.FloatingHint;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.controller.UserController;
import com.mycompany.controller.UtilisateurService;
import com.mycompany.entities.User;

/**
 *
 * @author DELL
 */
public class DeleteForm extends BaseForm {

    private static User user;
    private static int a;

    public DeleteForm(Resources res) {

        super(new BorderLayout());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        tb.setUIID("Container");
        getTitleArea().setUIID("Container");
        Form previous = Display.getInstance().getCurrent();
        tb.setBackCommand("", e -> previous.showBack());
        setUIID("SignIn");

        TextField username = new TextField("", "Username", 20, TextField.ANY);
        TextField password = new TextField("", "Password", 20, TextField.PASSWORD);
        Button Confirm = new Button("Confirm");
        Label abc = new Label("If you want to delete your account write your username and password then press confirm ! ");
        Label bac = new Label("");
        Label acab = new Label("");
        username.setSingleLineTextArea(false);
        password.setSingleLineTextArea(false);

        Container content = BoxLayout.encloseXCenter(
                abc,
                
                bac,
                
                acab
               
        );
        content.setScrollableY(true);

        add(BorderLayout.CENTER, content);
        add(BorderLayout.SOUTH, BoxLayout.encloseY(
                new FloatingHint(username),
                createLineSeparator(),
                new FloatingHint(password),
                createLineSeparator(),
                Confirm
        ));
        Confirm.requestFocus();
        Confirm.addActionListener(e -> {
            User u = new User();

            user = UserController.getInstance().Login(username.getText(), password.getText());
            a = user.getId();
            UtilisateurService.getInstance().deleteUser(a);
            Dialog.show("Alert", "Your account is deleted", "OK", null);
            new SignInForm(res).show();

        });

    }
}
