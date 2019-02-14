package Matériaux.afficherMatériel;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Matériel {
    private final SimpleIntegerProperty id;
    private final SimpleStringProperty Désignation;
    private final SimpleIntegerProperty nombre;

    public Matériel(int id, String Désignation, int nombre) {
        this.id = new SimpleIntegerProperty(id);
        this.Désignation = new SimpleStringProperty(Désignation);
        this.nombre = new SimpleIntegerProperty(nombre);
    }

    public  int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public String getDésignation() {
        return Désignation.get();
    }

    public SimpleStringProperty désignationProperty() {
        return Désignation;
    }

    public int getNombre() {
        return nombre.get();
    }

    public SimpleIntegerProperty nombreProperty() {
        return nombre;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public void setDésignation(String désignation) {
        this.Désignation.set(désignation);
    }

    public void setNombre(int nombre) {
        this.nombre.set(nombre);
    }
}
