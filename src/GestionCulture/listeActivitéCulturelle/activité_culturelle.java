package GestionCulture.listeActivitéCulturelle;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.text.SimpleDateFormat;
import java.util.Date;

public class activité_culturelle {
    private final SimpleIntegerProperty id;
    private final SimpleStringProperty nom;



    public activité_culturelle(int id,String nom) {
        this.id = new SimpleIntegerProperty(id);
        this.nom = new SimpleStringProperty(nom);
    }

    public String getNom() {
        return nom.get();
    }

    public void setNom(String nom) {
        this.nom.set(nom);
    }

    public int getId() {
        return id.get();
    }


    public void setId(int id) {
        this.id.set(id);
    }
}


