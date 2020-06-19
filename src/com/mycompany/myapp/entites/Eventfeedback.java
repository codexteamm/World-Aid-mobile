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
public class Eventfeedback {

    int id;
    String sujet;
    String message;
    int idevenement;
    int idbenevole;

    public Eventfeedback(String sujet, String message) {
        this.sujet = sujet;
        this.message = message;
    }

    public Eventfeedback(int id, String sujet, String message, int idevenement, int idbenevole) {
        this.id = id;
        this.sujet = sujet;
        this.message = message;
        this.idevenement = idevenement;
        this.idbenevole = idbenevole;
    }

    public Eventfeedback() {
    }

    public int getId() {
        return id;
    }

    public String getSujet() {
        return sujet;
    }

    public String getMessage() {
        return message;
    }

    public int getIdevenement() {
        return idevenement;
    }

    public int getIdbenevole() {
        return idbenevole;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setIdevenement(int idevenement) {
        this.idevenement = idevenement;
    }

    public void setIdbenevole(int idbenevole) {
        this.idbenevole = idbenevole;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + this.id;
        hash = 97 * hash + Objects.hashCode(this.sujet);
        hash = 97 * hash + Objects.hashCode(this.message);
        hash = 97 * hash + this.idevenement;
        hash = 97 * hash + this.idbenevole;
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
        final Eventfeedback other = (Eventfeedback) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.idevenement != other.idevenement) {
            return false;
        }
        if (this.idbenevole != other.idbenevole) {
            return false;
        }
        if (!Objects.equals(this.sujet, other.sujet)) {
            return false;
        }
        if (!Objects.equals(this.message, other.message)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Eventfeedback{" + "id=" + id + ", sujet=" + sujet + ", message=" + message + ", idevenement=" + idevenement + ", idbenevole=" + idbenevole + '}';
    }

}
