package GestionSportCol.liste_Equipe;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Equipe {
    private final SimpleIntegerProperty id;
    private final SimpleStringProperty nom;
    private final SimpleIntegerProperty nb;

    public Equipe(int id, String nom, int nb) {
        this.nom = new SimpleStringProperty(nom);
        this.id = new SimpleIntegerProperty(id);
        this.nb = new SimpleIntegerProperty(nb);
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

    public int getNb() {
        return nb.get();
    }

    public void setNb(int nb) {
        this.nb.set(nb);
    }
}


