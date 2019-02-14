package Entrainement.listeEntrainement;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Entrainement {
    private final SimpleIntegerProperty id;
    private final SimpleStringProperty nom;
    private final SimpleStringProperty salle;
    private final Date date;

    public Entrainement(int id,String nom, String salle,Date date) {
         this.id= new SimpleIntegerProperty(id);
        this.nom = new SimpleStringProperty(nom);
        this.date = date;
        this.salle = new SimpleStringProperty(salle);
    }


    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getNom() {
        return nom.get();
    }

    public void setNom(String nom) {
        this.nom.set(nom);
    }

    public String getSalle() {
        return salle.get();
    }

    public void setSalle(String salle) {
        this.salle.set(salle);
    }
    public Date getDate() {
        return date;
    }
}


