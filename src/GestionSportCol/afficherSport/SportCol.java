package GestionSportCol.afficherSport;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class SportCol {
    private final SimpleIntegerProperty id;
    private final SimpleStringProperty nom;



    public SportCol(int id, String nom) {
        this.nom = new SimpleStringProperty(nom);
        this.id = new SimpleIntegerProperty(id);

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


}

