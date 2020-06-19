/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.cleanmodern;

import com.codename1.components.FloatingHint;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.User;

/**
 *
 * @author DELL
 */
public class FirstForm extends BaseForm {

    public FirstForm(Resources res) {
        super(new BorderLayout());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        tb.setUIID("Container");
        getTitleArea().setUIID("Container");
        Form previous = Display.getInstance().getCurrent();
        tb.setBackCommand("", e -> previous.showBack());
        setUIID("SignIn");

        Button Association = new Button("Association");
        Button Benevole = new Button("Benevole");
        Button Necessiteur = new Button("Necessiteur");
        // Button next = new Button("Next");
        Button signIn = new Button("Sign In");
        signIn.addActionListener(e -> previous.showBack());
        signIn.setUIID("Link");
        Label alreadHaveAnAccount = new Label("Already have an account?");

        Container content = BoxLayout.encloseY(
                new Label("Sign Up", "LogoLabel"),
                Association,
                createLineSeparator(),
                Benevole,
                createLineSeparator(),
                Necessiteur,
                createLineSeparator()
        );
        content.setScrollableY(true);

        add(BorderLayout.CENTER, content);
        add(BorderLayout.SOUTH, BoxLayout.encloseY(
                //next,

                FlowLayout.encloseCenter(alreadHaveAnAccount, signIn)
        ));
        Association.requestFocus();
        Association.addActionListener(e -> {
           
            new SignUpForm(res).show();
        });

        Benevole.addActionListener(e -> {

            new SignUpBen(res).show();
        });
        Necessiteur.addActionListener(e -> {

            new SignUpNec(res).show();
        });

    }

}
