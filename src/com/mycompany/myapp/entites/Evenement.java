/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entites;

import java.util.Objects;

/**
 *
 * @author user
 */
public class Evenement {

    private int idEvent;
   // private int id_association;
    private String nomEvent;
    private String dateDebutEvent;
    private String dateFinEvent;
    private double longitude;
    private double latitude;
    private String description;
    private String categorie;

    public Evenement(int idEvent, int id_association, String nomEvent, String dateDebutEvent, String dateFinEvent, double longitude, double latitude, String description, String categorie) {
        this.idEvent = idEvent;
       // this.id_association = id_association;
        this.nomEvent = nomEvent;
        this.dateDebutEvent = dateDebutEvent;
        this.dateFinEvent = dateFinEvent;
        this.longitude = longitude;
        this.latitude = latitude;
        this.description = description;
        this.categorie = categorie;
    }

    public Evenement(String nomEvent, String dateDebutEvent, String dateFinEvent, double longitude, double latitude, String description, String categorie) {
        this.nomEvent = nomEvent;
        this.dateDebutEvent = dateDebutEvent;
        this.dateFinEvent = dateFinEvent;
        this.longitude = longitude;
        this.latitude = latitude;
        this.description = description;
        this.categorie = categorie;
    }

    public Evenement() {
    }

    public int getIdEvent() {
        return idEvent;
    }

//    public int getId_association() {
//        return id_association;
//    }

    public String getNomEvent() {
        return nomEvent;
    }

    public String getDateDebutEvent() {
        return dateDebutEvent;
    }

    public String getDateFinEvent() {
        return dateFinEvent;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public String getDescription() {
        return description;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setIdEvent(int idEvent) {
        this.idEvent = idEvent;
    }

//    public void setId_association(int id_association) {
//        this.id_association = id_association;
//    }

    public void setNomEvent(String nomEvent) {
        this.nomEvent = nomEvent;
    }

    public void setDateDebutEvent(String dateDebutEvent) {
        this.dateDebutEvent = dateDebutEvent;
    }

    public void setDateFinEvent(String dateFinEvent) {
        this.dateFinEvent = dateFinEvent;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + this.idEvent;
//        hash = 41 * hash + this.id_association;
        hash = 41 * hash + Objects.hashCode(this.nomEvent);
        hash = 41 * hash + Objects.hashCode(this.dateDebutEvent);
        hash = 41 * hash + Objects.hashCode(this.dateFinEvent);
        hash = 41 * hash + (int) (Double.doubleToLongBits(this.longitude) ^ (Double.doubleToLongBits(this.longitude) >>> 32));
        hash = 41 * hash + (int) (Double.doubleToLongBits(this.latitude) ^ (Double.doubleToLongBits(this.latitude) >>> 32));
        hash = 41 * hash + Objects.hashCode(this.description);
        hash = 41 * hash + Objects.hashCode(this.categorie);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Evenement other = (Evenement) obj;
        if (this.idEvent != other.idEvent) {
            return false;
        }
//        if (this.id_association != other.id_association) {
//            return false;
//        }
        if (Double.doubleToLongBits(this.longitude) != Double.doubleToLongBits(other.longitude)) {
            return false;
        }
        if (Double.doubleToLongBits(this.latitude) != Double.doubleToLongBits(other.latitude)) {
            return false;
        }
        if (!Objects.equals(this.nomEvent, other.nomEvent)) {
            return false;
        }
        if (!Objects.equals(this.dateDebutEvent, other.dateDebutEvent)) {
            return false;
        }
        if (!Objects.equals(this.dateFinEvent, other.dateFinEvent)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.categorie, other.categorie)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Evenement{" + "idEvent=" + idEvent + ", nomEvent=" + nomEvent + ", dateDebutEvent=" + dateDebutEvent + ", dateFinEvent=" + dateFinEvent + ", longitude=" + longitude + ", latitude=" + latitude + ", description=" + description + ", categorie=" + categorie + '}';
    }
//+ ", id_association=" + id_association 
}
