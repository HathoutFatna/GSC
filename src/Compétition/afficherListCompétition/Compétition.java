package Compétition.afficherListCompétition;



import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Compétition {
    private final SimpleIntegerProperty idc;
    private final SimpleStringProperty nomCom;
    private final SimpleStringProperty sportColec;
    private final SimpleStringProperty sportIndiv;
    private final SimpleStringProperty dateDebut;
    private final SimpleStringProperty dateFin;


    public Compétition(int idc, String nomCom, String sportColec, String sportIndiv, String dateDebut, String dateFin) {

        this.idc = new SimpleIntegerProperty(idc);
        this.nomCom = new SimpleStringProperty(nomCom);
        this.sportColec = new SimpleStringProperty(sportColec);
        this.sportIndiv = new SimpleStringProperty(sportIndiv);
        this.dateDebut = new SimpleStringProperty(dateDebut);
        this.dateFin = new SimpleStringProperty(dateFin);
    }

    public int getIdc() {
        return idc.get();
    }

    public SimpleIntegerProperty idcProperty() {
        return idc;
    }

    public void setIdc(int idc) {
        this.idc.set(idc);
    }

    public String getNomCom() {
        return nomCom.get();
    }

    public SimpleStringProperty nomComProperty() {
        return nomCom;
    }

    public void setNomCom(String nomCom) {
        this.nomCom.set(nomCom);
    }

    public String getSportColec() {
        return sportColec.get();
    }

    public SimpleStringProperty sportColecProperty() {
        return sportColec;
    }

    public void setSportColec(String sportColec) {
        this.sportColec.set(sportColec);
    }

    public String getSportIndiv() {
        return sportIndiv.get();
    }

    public SimpleStringProperty sportIndivProperty() {
        return sportIndiv;
    }

    public void setSportIndiv(String sportIndiv) {
        this.sportIndiv.set(sportIndiv);
    }

    public String getDateDebut() {
        return dateDebut.get();
    }

    public SimpleStringProperty dateDebutProperty() {
        return dateDebut;
    }

    public void setDateDebut(String dateDebut) {
        this.dateDebut.set(dateDebut);
    }

    public String getDateFin() {
        return dateFin.get();
    }

    public SimpleStringProperty dateFinProperty() {
        return dateFin;
    }

    public void setDateFin(String dateFin) {
        this.dateFin.set(dateFin);
    }
}
