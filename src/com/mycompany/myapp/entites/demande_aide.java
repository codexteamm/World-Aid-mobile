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
public class demande_aide {

    private int id_demande;
    private String titre;
    private String description;
    private int etat;
    private int idCassocial;

    public demande_aide(String titre, String description, int etat) {
        this.titre = titre;
        this.description = description;
        this.etat = etat;
    }

    public demande_aide(int id_demande, String titre, String description) {
        this.id_demande = id_demande;
        this.titre = titre;
        this.description = description;
    }
    
    public demande_aide(int id_demande, String titre, String description, int etat) {
        this.id_demande = id_demande;
        this.titre = titre;
        this.description = description;
        this.etat = etat;
    }

    public demande_aide(String titre, String description) {
        this.titre = titre;
        this.description = description;
    }

    public demande_aide(int id_demande, String titre, String description, int etat, int idCassocial) {
        this.id_demande = id_demande;
        this.titre = titre;
        this.description = description;
        this.etat = etat;
        this.idCassocial = idCassocial;
    }
    
    

    public demande_aide() {
    }

    public int getId_demande() {
        return id_demande;
    }

    public String getTitre() {
        return titre;
    }

    public String getDescription() {
        return description;
    }

    public int getEtat() {
        return etat;
    }

    public int getIdCassocial() {
        return idCassocial;
    }

    public void setIdCassocial(int idCassocial) {
        this.idCassocial = idCassocial;
    }

    
    public void setId_demande(int id_demande) {
        this.id_demande = id_demande;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    @Override
    public int hashCode() {
        int hash = 3;
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
        final demande_aide other = (demande_aide) obj;
        if (this.id_demande != other.id_demande) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "demande_aide{" + "id_demande=" + id_demande + ", titre=" + titre + ", description=" + description + ", etat=" + etat + ", idCassocial=" + idCassocial + '}';
    }

    

}
