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

import com.codename1.components.FloatingHint;
import com.codename1.messaging.Message;
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
import com.mycompany.controller.UtilisateurService;
import com.mycompany.entities.User;

/**
 * Signup UI
 *
 * @author Shai Almog
 */
public class SignUpForm extends BaseForm {

    private boolean user;

    public SignUpForm(Resources res) {
        super(new BorderLayout());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        tb.setUIID("Container");
        getTitleArea().setUIID("Container");
        Form previous = Display.getInstance().getCurrent();
        tb.setBackCommand("", e -> previous.showBack());
        setUIID("SignIn");

        TextField email = new TextField("", "E-Mail", 20, TextField.EMAILADDR);
        TextField username = new TextField("", "Username", 20, TextField.ANY);
        TextField password = new TextField("", "Password", 20, TextField.PASSWORD);
//        TextField nom = new TextField("", "Nom", 20, TextField.ANY);
//        TextField prenom = new TextField("", "prenom", 20, TextField.ANY);
//        TextField pays = new TextField("", "pays", 20, TextField.ANY);
//        TextField description = new TextField("", "description", 20, TextField.ANY);
        TextField rib = new TextField("", "rib", 20, TextField.ANY);
        TextField adresse = new TextField("", "adresse", 20, TextField.ANY);
        TextField categorie = new TextField("", "categorie", 20, TextField.ANY);
        TextField logo = new TextField("", "logo", 20, TextField.ANY);
        TextField numero = new TextField("", "numero", 20, TextField.ANY);

        username.setSingleLineTextArea(false);
        email.setSingleLineTextArea(false);
        password.setSingleLineTextArea(false);
//        nom.setSingleLineTextArea(false);
//        prenom.setSingleLineTextArea(false);
//        pays.setSingleLineTextArea(false);
//        description.setSingleLineTextArea(false);
        rib.setSingleLineTextArea(false);
        adresse.setSingleLineTextArea(false);
        categorie.setSingleLineTextArea(false);
        logo.setSingleLineTextArea(false);
        numero.setSingleLineTextArea(false);

        Button next = new Button("Next");
        Button signIn = new Button("Sign In");
        signIn.addActionListener(e -> previous.showBack());
        signIn.setUIID("Link");
        Label alreadHaveAnAccount = new Label("Already have an account?");

        Container content = BoxLayout.encloseY(
                new Label("Sign Up", "LogoLabel"),
                new FloatingHint(username),
                createLineSeparator(),
                new FloatingHint(email),
                createLineSeparator(),
                new FloatingHint(password),
                createLineSeparator(),
                new FloatingHint(rib),
                createLineSeparator(),
                new FloatingHint(adresse),
                createLineSeparator(),
                new FloatingHint(categorie),
                createLineSeparator(),
                new FloatingHint(logo),
                createLineSeparator(),
                new FloatingHint(numero),
                createLineSeparator()
        );
        content.setScrollableY(true);

        add(BorderLayout.CENTER, content);
        add(BorderLayout.SOUTH, BoxLayout.encloseY(
                next,
                FlowLayout.encloseCenter(alreadHaveAnAccount, signIn)
        ));
        next.requestFocus();
        //next.addActionListener(e -> new ActivateForm(res).show());
        next.addActionListener(e -> {
            User u = new User();
            u.setUsername(username.getText());
            u.setEmail(email.getText());
            u.setPassword(password.getText());
            u.setRib(rib.getText());
            u.setAdresse(adresse.getText());
            u.setCategorie(categorie.getText());
            u.setLogo(logo.getText());
            u.setNumero(numero.getText());

            user = UtilisateurService.getInstance().addUser(u);
            
             Dialog.show("Alert", "An email will be sent to you containing your information ", "OK", null);

            Message m = new Message("Username: \n"+username.getText()+"\n Password: \n"+password.getText());
//            m.getAttachments().put(textAttachmentUri, "text/plain");
//            m.getAttachments().put(imageAttachmentUri, "image/png");
            Display.getInstance().sendMessage(new String[]{email.getText()}, "Information", m);

            new SignInForm(res).show();

        });
    }

}
