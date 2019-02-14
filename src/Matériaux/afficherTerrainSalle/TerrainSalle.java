package Matériaux.afficherTerrainSalle;


import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class TerrainSalle {
    private final SimpleIntegerProperty id;
    private final SimpleStringProperty lieu;
    private final SimpleStringProperty nom;


    public TerrainSalle(int id, String lieu, String nom) {
        this.id = new SimpleIntegerProperty(id);
        this.lieu = new SimpleStringProperty(lieu);
        this.nom = new SimpleStringProperty(nom);
    }

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getLieu() {
        return lieu.get();
    }

    public SimpleStringProperty lieuProperty() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu.set(lieu);
    }

    public String getNom() {
        return nom.get();
    }

    public SimpleStringProperty nomProperty() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom.set(nom);
    }
}

