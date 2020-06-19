/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.SpanLabel;
import com.codename1.components.WebBrowser;
import com.codename1.ui.BrowserComponent;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entites.Campement;
import java.util.ArrayList;
import com.mycompany.myapp.services.ServiceCampement;
import com.mycompany.gui.ListCapementForm;
import com.codename1.uikit.cleanmodern.BaseForm;
import com.mycompany.controller.UserController;


/**
 *
 * @author HP
 */
public class showCampDetails extends BaseForm {

    Resources theme;
    Form current;
   
    private static final Toolbar.BackCommandPolicy BACK_POLICY = Toolbar.BackCommandPolicy.AS_ARROW;

    public showCampDetails(Form previous, Resources resourceObjectInstance, Campement Campement) {
                super("Add campement", BoxLayout.y());
        setLayout(BoxLayout.y());
        BorderLayout br = new BorderLayout();

        Toolbar tb = new Toolbar(true);
        setToolbar(tb);

        tb.addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

        current = this;
        theme = resourceObjectInstance;
        setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        Container cnt = new Container(BoxLayout.y());
        
        WebBrowser browser = new WebBrowser() {
            @Override
            public void onLoad(String url) {


                BrowserComponent c = (BrowserComponent) this.getInternal();
                System.out.println(Campement);
                String call = "addpopup(" + Campement.getLongitude() + "," + Campement.getLatitude() + ")";

                System.out.println(call);
                // c.executeAndReturnString(call);
                c.execute(call);

            }
        };

        setTitle("name:" + Campement.getNom());
        //testLabel.getAllStyles.setFgColor(daa520);

        Label l2 = new Label("            Country: " + Campement.getPaye());
                l2.getAllStyles().setFgColor(0xD1841F);

        Font largeBoldProportionalFont = Font.createSystemFont(Font.FACE_PROPORTIONAL, Font.STYLE_BOLD, Font.SIZE_LARGE);
        l2.getUnselectedStyle().setFont(largeBoldProportionalFont);
        
        SpanLabel l3 = new SpanLabel("Description:\n" + Campement.getDescription());
      
        l3.getTextAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE));
        Button d = new Button("Update");
        d.getAllStyles().setBorder(Border.createEmpty());
        d.getAllStyles().setTextDecoration(Style.TEXT_DECORATION_UNDERLINE);
cnt.add(l2);
cnt.add(l3);

        
       add(cnt);

        addComponent(browser);
        int id = UserController.getInstance().User.getId();
        Button support = new Button("Support");

        support.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                 Dialog dlg = new Dialog("Support");
                Style dlgStyle = dlg.getDialogStyle();
                dlgStyle.setBorder(Border.createEmpty());
                dlgStyle.setBgTransparency(255);
                dlgStyle.setBgColor(0xffffff);

                Label title = dlg.getTitleComponent();
                //title.setIcon(finalDuke.scaledHeight(title.getPreferredH()));
                title.getUnselectedStyle().setFgColor(0xff);
                // title.getUnselectedStyle().setAlignment(Component.LEFT);

                dlg.setLayout(BoxLayout.y());
                Label blueLabel = new Label();
                blueLabel.setShowEvenIfBlank(true);
                blueLabel.getUnselectedStyle().setBgColor(0xff);
                blueLabel.getUnselectedStyle().setPadding(1, 1, 1, 1);
                blueLabel.getUnselectedStyle().setPaddingUnit(Style.UNIT_TYPE_PIXELS);
                dlg.add(blueLabel);
                TextArea ta = new TextArea("are you sure you want too support "+Campement.getNom()+"camp's");
                ta.setEditable(false);
                ta.setUIID("DialogBody");
                ta.getAllStyles().setFgColor(0);
                dlg.add(ta);

                Label grayLabel = new Label();
                grayLabel.setShowEvenIfBlank(true);
                grayLabel.getUnselectedStyle().setBgColor(0xcccccc);
                grayLabel.getUnselectedStyle().setPadding(1, 1, 1, 1);
                grayLabel.getUnselectedStyle().setPaddingUnit(Style.UNIT_TYPE_PIXELS);
                dlg.add(grayLabel);
                Button cancel = new Button(new Command("cancel"));
                Button ok = new Button(new Command("OK"));
                ok.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent t) {
                                      ServiceCampement.getInstance().takechage(Campement.getId(), id);

                        System.out.println("delete pressed ");
                      

                        

                    }
                });
                ok.getAllStyles().setBorder(Border.createEmpty());
                ok.getAllStyles().setFgColor(0);
                dlg.add(ok);
                dlg.add(cancel);

                dlg.showDialog();
               previous.show();
              // new HomeCampementForm().show();

            }
        });
        Button unsupport = new Button("Unsupport");

        unsupport.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) { 
                Dialog dlg = new Dialog("Unsupport");
                Style dlgStyle = dlg.getDialogStyle();
                dlgStyle.setBorder(Border.createEmpty());
                dlgStyle.setBgTransparency(255);
                dlgStyle.setBgColor(0xffffff);

                Label title = dlg.getTitleComponent();
                //title.setIcon(finalDuke.scaledHeight(title.getPreferredH()));
                title.getUnselectedStyle().setFgColor(0xff);
                // title.getUnselectedStyle().setAlignment(Component.LEFT);

                dlg.setLayout(BoxLayout.y());
                Label blueLabel = new Label();
                blueLabel.setShowEvenIfBlank(true);
                blueLabel.getUnselectedStyle().setBgColor(0xff);
                blueLabel.getUnselectedStyle().setPadding(1, 1, 1, 1);
                blueLabel.getUnselectedStyle().setPaddingUnit(Style.UNIT_TYPE_PIXELS);
                dlg.add(blueLabel);
                TextArea ta = new TextArea("are you sure you want to unsupport "+Campement.getNom()+"camp's");
                ta.setEditable(false);
                ta.setUIID("DialogBody");
                ta.getAllStyles().setFgColor(0);
                dlg.add(ta);

                Label grayLabel = new Label();
                grayLabel.setShowEvenIfBlank(true);
                grayLabel.getUnselectedStyle().setBgColor(0xcccccc);
                grayLabel.getUnselectedStyle().setPadding(1, 1, 1, 1);
                grayLabel.getUnselectedStyle().setPaddingUnit(Style.UNIT_TYPE_PIXELS);
                dlg.add(grayLabel);
                Button cancel = new Button(new Command("Cancel"));
                Button ok = new Button(new Command("OK"));
                ok.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent t) {
                        ServiceCampement.getInstance().untakechage(Campement.getId(), id);
                        //ListCapementForm()
                                             
                        System.out.println("Delete pressed ");
                        
                        

                    }
                });
                ok.getAllStyles().setBorder(Border.createEmpty());
                ok.getAllStyles().setFgColor(0);
                dlg.add(ok);
                dlg.add(cancel);

                dlg.showDialog();
                previous.show();
                

                
             //  previous.show();

            }
        });
        if (ServiceCampement.getInstance().campsupported(id, Campement)) {
            add(unsupport);
        } else {
            add(support);
        }
        // browser.setURL("jar:///webmaps.html");
        browser.setURL("file:///C:/Users/HP/Desktop/pages/webmaps.html");

        Button c = new Button("Update description");
        c.getAllStyles().setBorder(Border.createEmpty());

        c.getAllStyles().setTextDecoration(Style.TEXT_DECORATION_UNDERLINE);
        c.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent t) {
                new EditForm(current, Campement, theme).show();

            }
        });

        Button b = new Button("Delete");

        b.getAllStyles().setBorder(Border.createEmpty());
        b.getAllStyles().setTextDecoration(Style.TEXT_DECORATION_UNDERLINE);
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent t) {

                Dialog dlg = new Dialog("Delete");
                Style dlgStyle = dlg.getDialogStyle();
                dlgStyle.setBorder(Border.createEmpty());
                dlgStyle.setBgTransparency(255);
                dlgStyle.setBgColor(0xffffff);

                Label title = dlg.getTitleComponent();
                //title.setIcon(finalDuke.scaledHeight(title.getPreferredH()));
                title.getUnselectedStyle().setFgColor(0xff);
                // title.getUnselectedStyle().setAlignment(Component.LEFT);

                dlg.setLayout(BoxLayout.y());
                Label blueLabel = new Label();
                blueLabel.setShowEvenIfBlank(true);
                blueLabel.getUnselectedStyle().setBgColor(0xff);
                blueLabel.getUnselectedStyle().setPadding(1, 1, 1, 1);
                blueLabel.getUnselectedStyle().setPaddingUnit(Style.UNIT_TYPE_PIXELS);
                dlg.add(blueLabel);
                TextArea ta = new TextArea("Are you sure to delete this camp?");
                ta.setEditable(false);
                ta.setUIID("DialogBody");
                ta.getAllStyles().setFgColor(0);
                dlg.add(ta);

                Label grayLabel = new Label();
                grayLabel.setShowEvenIfBlank(true);
                grayLabel.getUnselectedStyle().setBgColor(0xcccccc);
                grayLabel.getUnselectedStyle().setPadding(1, 1, 1, 1);
                grayLabel.getUnselectedStyle().setPaddingUnit(Style.UNIT_TYPE_PIXELS);
                dlg.add(grayLabel);
                Button cancel = new Button(new Command("Cancel"));
                Button ok = new Button(new Command("OK"));
                ok.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent t) {
                                       

                        ServiceCampement.getInstance().delete(Campement.getId());
                    //new HomeCampementForm(theme).show();
                    }
                });
                ok.getAllStyles().setBorder(Border.createEmpty());
                ok.getAllStyles().setFgColor(0);
                dlg.add(ok);
                dlg.add(cancel);

                dlg.showDialog();
                new ListCapementForm(current, theme, "").show();
                

            }
        });
        add(c);

        add(b);

    }

}
