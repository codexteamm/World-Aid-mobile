/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.cleanmodern;

import com.codename1.components.FloatingHint;
import com.codename1.messaging.Message;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
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
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.mycompany.controller.UtilisateurService;
import com.mycompany.entities.User;


/**
 *
 * @author DELL
 */
public class SignUpNec extends BaseForm {
    private boolean user;
    
    public  SignUpNec(Resources res){
        
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
        TextField nom = new TextField("", "Nom", 20, TextField.ANY);
        TextField prenom = new TextField("", "prenom", 20, TextField.ANY);
        TextField pays = new TextField("", "pays", 20, TextField.ANY);
        TextField description = new TextField("", "description", 20, TextField.ANY);
        TextField logo = new TextField("", "logo", 20, TextField.ANY);

        
        //datePicker.setType(Display.PICKER_TYPE_DATE);

        username.setSingleLineTextArea(false);
        email.setSingleLineTextArea(false);
        password.setSingleLineTextArea(false);
        nom.setSingleLineTextArea(false);
        prenom.setSingleLineTextArea(false);
        pays.setSingleLineTextArea(false);
        description.setSingleLineTextArea(false);
        logo.setSingleLineTextArea(false);

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
                new FloatingHint(nom),
                createLineSeparator(),
                new FloatingHint(prenom),
                createLineSeparator(),
                new FloatingHint(pays),
                createLineSeparator(),
                new FloatingHint(description),
                createLineSeparator(),
                createLineSeparator(),
                new FloatingHint(logo),
                createLineSeparator()
               
        );
        content.setScrollableY(true);
        //content.add(datePicker);

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
            u.setNom(nom.getText());
            u.setPrenom(prenom.getText());
            u.setPays(pays.getText());
            u.setDescription(description.getText());
            u.setLogo(logo.getText());
            
            
            user = UtilisateurService.getInstance().addNec(u);
            
             Dialog.show("Alert", "An email will be sent to you containing your information ", "OK", null);

            Message m = new Message("Username: \n"+username.getText()+"\n Password: \n"+password.getText());
//            m.getAttachments().put(textAttachmentUri, "text/plain");
//            m.getAttachments().put(imageAttachmentUri, "image/png");
            Display.getInstance().sendMessage(new String[]{email.getText()}, "Information", m);
            new SignInForm(res).show();
        });
    }

        
    
    
}
