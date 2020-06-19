/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entites;

/**
 *
 * @author HP
 */
public class feedback {

    private int id_feedback;
    private String titre;
    private String message;
    private int etat;
    private int idCassocial;

    public feedback(int id_feedback, String titre, String message) {
        this.id_feedback = id_feedback;
        this.titre = titre;
        this.message = message;
    }

    public feedback(String titre, String message) {
        this.titre = titre;
        this.message = message;
    }

    public feedback(int id_feedback, String titre, String message, int etat) {
        this.id_feedback = id_feedback;
        this.titre = titre;
        this.message = message;
        this.etat = etat;
    }

    public int getEtat() {
        return etat;
    }

    
    public feedback() {
    }

    public int getId_feedback() {
        return id_feedback;
    }

    public String getTitre() {
        return titre;
    }

    public int getIdCassocial() {
        return idCassocial;
    }

    public void setIdCassocial(int idCassocial) {
        this.idCassocial = idCassocial;
    }

    public String getMessage() {
        return message;
    }

    public void setId_feedback(int id_feedback) {
        this.id_feedback = id_feedback;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    
    public void setTitre(String titre) {
        this.titre = titre;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 61 * hash + this.id_feedback;
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
        final feedback other = (feedback) obj;
        if (this.id_feedback != other.id_feedback) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "feedback{" + "id_feedback=" + id_feedback + ", titre=" + titre + ", message=" + message + ", etat=" + etat + '}';
    }

    

}
