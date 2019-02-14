package Entraineurs.listeEntraineur;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Entraineur {
    private final SimpleIntegerProperty id;
    private final SimpleStringProperty nom;
    private final SimpleStringProperty prénom;

    public Entraineur(int id, String nom, String prénom) {
        this.nom = new SimpleStringProperty(nom);
        this.id = new SimpleIntegerProperty(id);
        this.prénom = new SimpleStringProperty(prénom);
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

    public String getPrénom() {
        return prénom.get();
    }

    public void setPrénom(String prénom) {
        this.prénom.set(prénom);
    }
}


