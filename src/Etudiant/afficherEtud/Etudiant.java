package etudiant.afficherEtud;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.Date;

public class Etudiant {
    private final SimpleIntegerProperty id;
    private final SimpleStringProperty nom;
    private final SimpleStringProperty prénom;
    private final Date date;
    private final SimpleStringProperty tel;
    private final SimpleStringProperty email;
    private final SimpleStringProperty sport;
    private final SimpleStringProperty activité_culturelle;

    public String getEmail() {
        return email.get();
    }

    public String getTel() {
        return tel.get();
    }

    public String getActivité_culturelle() {
        return activité_culturelle.get();
    }

    public String getSport() {
        return sport.get();
    }



    public void setSport(String sport) {
        this.sport.set(sport);
    }

    public void setActivité_culturelle(String activité_culturelle) {
        this.activité_culturelle.set(activité_culturelle);
    }

    public Date getDate() {
        return date;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public void setTel(String poid) {
        this.tel.set(poid);
    }


    public Etudiant(int id, String nom, String prénom, Date date, String email,String tel,String sport,String activité_culturelle) {
        this.nom = new SimpleStringProperty(nom);
        this.id = new SimpleIntegerProperty(id);
        this.prénom = new SimpleStringProperty(prénom);
        this.date=date;
        this.email=new SimpleStringProperty(email);
        this.tel=new SimpleStringProperty(tel);
        this.sport=new SimpleStringProperty(sport);
        this.activité_culturelle=new SimpleStringProperty(activité_culturelle);

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

