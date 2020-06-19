/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.cleanmodern;

import com.codename1.components.FloatingHint;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
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
import com.mycompany.controller.UtilisateurService;
import com.mycompany.entities.User;

/**
 *
 * @author DELL
 */
public class AdminForm extends BaseForm {
    
   public AdminForm(Resources res) {
        super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Welcome Admin");
        getContentPane().setScrollVisible(false);

        super.addSideMenuAssociation(res);

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

        SpanLabel sp = new SpanLabel();
        sp.setText(UtilisateurService.getInstance().showUser().toString());
        add(sp);
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
