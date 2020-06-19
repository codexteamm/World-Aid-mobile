/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entites;

import java.util.Date;

/**
 *
 * @author ASUS
 */
public class DonMateriel {
     private int reference;
    private int id_association;
    private int id_evenement;
    private int id_benevole;
    private String date_don;
    private String type_Materiel;
    private double quantite;
    
    public DonMateriel(int reference,int id_association,int id_evenement,int id_benevole,String date_don,String type_Materiel,double quantite){
      this.reference=reference;
      this.id_association=id_association;
      this.id_evenement=id_evenement;
      this.id_benevole=id_benevole;
      this.date_don=date_don;
      this.date_don=date_don;
      this.quantite=quantite;
    }
    
    
    public DonMateriel(){
        
    }
    public DonMateriel(String type_Materiel,double quantite){
        this.type_Materiel=type_Materiel;
        this.quantite=quantite;
    }
     public DonMateriel(String type_Materiel,double quantite,String date_don,int id_benevole,int id_association,int id_evenement){
        this.type_Materiel=type_Materiel;
        this.quantite=quantite;
        this.id_benevole=id_benevole;
        this.id_association=id_association;
        this.id_evenement=id_evenement;
        this.date_don=date_don;
    }

   public DonMateriel(String type_Materiel,double quantite,int id_benevole,int id_association,int id_evenement){
        this.type_Materiel=type_Materiel;
        this.quantite=quantite;
        this.id_benevole=id_benevole;
        this.id_association=id_association;
        this.id_evenement=id_evenement;
       
    }
    public int getReference() {
        return reference;
    }

    public void setReference(int reference) {
        this.reference = reference;
    }

    public int getId_association() {
        return id_association;
    }

    public void setId_association(int id_association) {
        this.id_association = id_association;
    }

    public int getId_evenement() {
        return id_evenement;
    }

    public void setId_evenement(int id_evenement) {
        this.id_evenement = id_evenement;
    }

    public int getId_benevole() {
        return id_benevole;
    }

    public void setId_benevole(int id_benevole) {
        this.id_benevole = id_benevole;
    }

    public String getDate_don() {
        return date_don;
    }

    public void setDate_don(String date_don) {
        this.date_don = date_don;
    }

    public String getType_Materiel() {
        return type_Materiel;
    }

    public void setType_Materiel(String type_Materiel) {
        this.type_Materiel = type_Materiel;
    }

    public double getQuantite() {
        return quantite;
    }

    public void setQuantite(double quantite) {
        this.quantite = quantite;
    }

    @Override
    public String toString() {
        return "DonMateriel{" + "reference=" + reference + ", id_association=" + id_association + ", id_evenement=" + id_evenement + ", id_benevole=" + id_benevole + ", date_don=" + date_don + ", type_Materiel=" + type_Materiel + ", quantite=" + quantite + '}';
    }  
}
