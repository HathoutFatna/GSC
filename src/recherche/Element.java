package recherche;

import javafx.beans.property.SimpleStringProperty;

public class Element {
    private SimpleStringProperty nom;
    private SimpleStringProperty table;


  public Element(String nom,String table){
        this.nom= new SimpleStringProperty(nom);
        this.table=new SimpleStringProperty(table);
    }

    public String getNom() {
        return nom.get();
    }

    public void setNom(String nom) {
        this.nom.set(nom);
    }

    public String getTable() {
        return table.get();
    }

    public void setTable(String table) {
        this.table.set(table);
    }
}
