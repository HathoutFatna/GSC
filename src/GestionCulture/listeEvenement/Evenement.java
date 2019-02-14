package GestionCulture.listeEvenement;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Evenement {
    private final SimpleStringProperty nom;
    private final SimpleStringProperty lieu;
    private final Date date;
    private final SimpleStringProperty activité;



    public Evenement(String nom, String lieu, Date date,String activité) {
        this.nom = new SimpleStringProperty(nom);
        this.lieu = new SimpleStringProperty(lieu);
        this.date = date;
        this.activité = new SimpleStringProperty(activité);
    }

    public String getNom() {
        return nom.get();
    }

    public void setNom(String nom) {
        this.nom.set(nom);
    }

    public String getLieu() {
        return lieu.get();
    }

    public void setLieu(String lieu) {
        this.lieu.set(lieu);
    }

    public String getActivité() {
        return activité.get();
    }

    public void setActivité(String activité) {
        this.activité.set(activité);
    }


    public Date getDate() {
        return date;
    }

}


