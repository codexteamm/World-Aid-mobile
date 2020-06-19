/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.WebBrowser;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.BrowserComponent;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
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
import com.mycompany.controller.UserController;
import com.mycompany.myapp.entites.Evenement;
import com.mycompany.myapp.entites.Eventfeedback;
import com.mycompany.myapp.services.ServiceEvent;
import com.mycompany.myapp.services.ServiceEventFeedback;
import static jdk.nashorn.internal.runtime.Debug.id;

/**
 *
 * @author hamza
 */
public class EventDetails extends Form {
  
    Form current;
    Resources theme;
  Eventfeedback f;
    int iduser =UserController.getInstance().User.getId();
    private static final Toolbar.BackCommandPolicy BACK_POLICY = Toolbar.BackCommandPolicy.AS_ARROW;

    public EventDetails(Form previous, Resources resourceObjectInstance, Evenement e) {
       
        super("details", BoxLayout.y());
         current=this;
         theme=resourceObjectInstance;
 Toolbar tb = new Toolbar(true);
        setToolbar(tb);

        tb.addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, t -> previous.showBack());       
        Label nom = new Label("Event Name : " + e.getNomEvent());
        //  Label id = new Label("Event id : " + e.getIdEvent());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Label dd = new Label("Start Date:  " + e.getDateDebutEvent());
        Label df = new Label("End Date :  " + e.getDateFinEvent());
        Label desc = new Label("Description :  " + e.getDescription());
        Label cate = new Label("category :  " + e.getCategorie());

        Button btn = new Button("Volunteer");
        Button btn2 = new Button("UnVolunteer");
        Button feedback = new Button("Feedback");
        Button dons = new Button("add donation");
        Button showfedd = new Button("show donation");
     // ArrayList<EvenetFeedback> Evenements = ServiceEvent.getInstance().getAllEvents();
      dons.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                
           
                    new addDonForm(current, theme, e).show();
                                   

                
                
            }
        });
            showfedd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                
           
                    new ListDonsForm(current, theme, e.getIdEvent()).show();
                                   

                
                
            }
        });
        
     
        feedback.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                //System.out.println(ServiceEventFeedback.getInstance().showfeedback(e.getIdEvent(), iduser).isEmpty());
                System.out.println(iduser+"===="+e.getIdEvent());
                    if(ServiceEventFeedback.getInstance().showfeedback(e.getIdEvent(), iduser).isEmpty())
                        
                    {  new AddFeedback(current, theme ,e).show();}
                    else {
                        new ListFeedback(current, theme ,e).show();}

                                   

                
                
            }
        });
        
        
        
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {

//                ServiceEvent.getInstance().Volunteer(e.getIdEvent(), iduser);
//                Dialog.show("Confirmation", "You are Volunteered in  " + e.getNomEvent(), "OK", null);

Dialog dlg = new Dialog("Volunteer");
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
                TextArea ta = new TextArea("are you sure you want to volunteer from "+e.getNomEvent()+"");
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
                       try{
                           System.out.println(e.getIdEvent()+"=========="+iduser);
                        ServiceEvent.getInstance().Volunteer(e.getIdEvent(), iduser);
                        System.out.println("ok pressed ");
                       }
                       catch (Exception e){
                           System.out.println(e.getMessage());
                       }
                       

                    }
                });
                ok.getAllStyles().setBorder(Border.createEmpty());
                ok.getAllStyles().setFgColor(0);
                dlg.add(ok);
                dlg.add(cancel);

                dlg.showDialog();
            }
        });

        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
//                ServiceEvent.getInstance().UnVolunteer(e.getIdEvent(), iduser);

                Dialog dlg = new Dialog("Volunteer");
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
                TextArea ta = new TextArea("are you sure you want to unvolunteer from "+e.getNomEvent()+"");
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
                        try{
                        ServiceEvent.getInstance().UnVolunteer(e.getIdEvent(), iduser);
                        System.out.println("ok pressed ");
                        }
                        catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                        

                    }
                });
                ok.getAllStyles().setBorder(Border.createEmpty());
                ok.getAllStyles().setFgColor(0);
                dlg.add(ok);
                dlg.add(cancel);

                dlg.showDialog();
            }
            
        });
        
        

        Container ctn = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        ctn.add(nom);
        //  ctn.add(id);
        ctn.add(dd);
        ctn.add(df);
        ctn.add(desc);
        ctn.add(cate);
//        ctn.add(nl);
//        ctn.add(n2);
if (ServiceEvent.getInstance().EventVolunteered(iduser, e)) {
            ctn.add(btn2);
        } else {
            ctn.add(btn);
        }
        ctn.add(feedback);
        ctn.add(dons);
                ctn.add(showfedd);


        Container ctn2 = BorderLayout.center(ctn);
        ctn2.setLayout(
                new BoxLayout(BoxLayout.Y_AXIS));
        add(ctn2);

        WebBrowser browser = new WebBrowser() {
            @Override
            public void onLoad(String url) {
       
                BrowserComponent c = (BrowserComponent) this.getInternal();
                System.out.println(e);
                String call = "addpopup(" + e.getLatitude() + "," + e.getLongitude() + ",'" + e.getDescription() + "')";
                c.executeAndReturnString(call);

            }

        };

        addComponent(browser);

        browser.setURL("jar:///webmaps.html");

    }
}
