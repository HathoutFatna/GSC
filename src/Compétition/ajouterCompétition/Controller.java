package Compétition.ajouterCompétition;

import DataBase.src.Connexion;
import alert.AlertMaker;
import com.jfoenix.controls.JFXTextArea;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static java.sql.Types.NULL;

public class Controller implements Initializable{
    Connection conn = Connexion.connecterDB();
    PreparedStatement st;
    ResultSet rst;

    @FXML
    private TextField txtnm;

    @FXML
    private ComboBox combSportColect;

    @FXML
    private ComboBox combSportIndiv;

    @FXML
    private DatePicker pdateDebut;

    @FXML
    private DatePicker pdateFin;

    @FXML
    private JFXTextArea txtAreaDesc;

    @FXML
    private ComboBox combSalle;

    @FXML
    private ComboBox combMateriel;

    @FXML
    private ComboBox combEquipe;

    @FXML
    private Button btnjouter;

    @FXML
    private Button btnretour;


    @FXML
    void ajouterCompétition(ActionEvent event) {
        try {

            String query = "INSERT INTO `bdd`.`compétition` (`idCompétition`, `Nom`, `Description`, `DateDebut`, `DateFin`," +
                    " `équipe_idéquipe`, `équipe_sport_collectif_idsport_collectif`, `Sport_Idividuel_idSport_Idividuel`, `Salles_idSalles`, `matériel_idmatériel`) VALUES (NULL, '"+ txtnm.getText()+"', '"+txtAreaDesc.getText()+"', '"+pdateDebut.getValue()+"', '"+pdateFin.getValue()+"','"+getIdEquipe( (String)combEquipe.getSelectionModel().getSelectedItem() )+"'," +
                    "'"+getIdEquipe_sport((String) combEquipe.getSelectionModel().getSelectedItem())+"', '"+getIdSportIndiv((String)combSportIndiv.getSelectionModel().getSelectedItem())+"'," +
                    " '"+getIdSalle(  (String)combSalle.getSelectionModel().getSelectedItem())+"', '"+getIdMateriel( (String) combMateriel.getSelectionModel().getSelectedItem() )+"')";
            st=conn.prepareStatement(query);


            st.execute();
            AlertMaker.showSimpleAlert("Ajout terminé", "la compétition "+txtnm.getText()+" à été ajouté");
            txtnm.setText(" ");
            txtAreaDesc.setText(" ");
            pdateDebut.getEditor().clear();
            pdateFin.getEditor().clear();
            combEquipe.getSelectionModel().clearSelection();
            combMateriel.getSelectionModel().clearSelection();
            combSalle.getSelectionModel().clearSelection();
            combSportColect.getSelectionModel().clearSelection();
            combSportIndiv.getSelectionModel().clearSelection();
            st.close();
        }catch (SQLException e){
            System.out.println(e.getMessage());

        }

    }

    @FXML
    void retour(ActionEvent event) {
        try {
            URL url = Paths.get("./fxml/h18.fxml").toUri().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Stage stage = (Stage) this.btnretour.getScene().getWindow();
            Scene scene = new Scene((Parent) loader.load());
            stage.setScene(scene);
        }catch (IOException io){
            io.printStackTrace();
        }

    }
@Override
public void initialize(URL url,ResourceBundle rb) {
    Actualiser();
}
    @FXML
    void Actualiser() {
        try {
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM `sport_collectif`  WHERE 1");

            while (rs.next()) {
                String name = rs.getString(2);
                combSportColect.getItems().add(name);
            }
        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }

        //sport indiv
        try {
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM `sport_idividuel`  WHERE 1");

            while (rs.next()) {
                String name = rs.getString(2);
                combSportIndiv.getItems().add(name);
            }
        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }
//
        try {
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM `salles`  WHERE 1");

            while (rs.next()) {
                String name = rs.getString(3);
                combSalle.getItems().add(name);
            }
        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }
        try {
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM `matériel`  WHERE 1");

            while (rs.next()) {
                String name = rs.getString(2);
                combMateriel.getItems().add(name);
            }
        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }
        try {
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM `équipe`  WHERE 1");

            while (rs.next()) {
                String name = rs.getString(2);
                combEquipe.getItems().add(name);
            }
        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }

    }
    public int getIdEquipe(String nom){
        int id=0;
        try {
            ResultSet rs1 = conn.createStatement().executeQuery(" SELECT * FROM `équipe`  WHERE `Nom` ='"+nom+"' ");
            while (rs1.next()){
                id = rs1.getInt(1);}

        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }
        return id;
    }


    public int getIdSportIndiv(String nom){
        int id=0;
        try {
            ResultSet rs1 = conn.createStatement().executeQuery(" SELECT * FROM `sport_idividuel`  WHERE `Nom` ='"+nom+"' ");
            while (rs1.next()){
                id = rs1.getInt(1);}

        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }

        return id;
    }

    public int getIdMateriel(String nom){
        int id=0;
        try {
            ResultSet rs1 = conn.createStatement().executeQuery(" SELECT * FROM `matériel`  WHERE `Désignation` ='"+nom+"' ");
            while (rs1.next()){
                id = rs1.getInt(1);}

        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }

        return id;
    }

    public int getIdSalle(String nom){
        int id=0;
        try {
            ResultSet rs2 = conn.createStatement().executeQuery(" SELECT * FROM `salles`  WHERE `Nom` ='"+nom+"' ");
            while (rs2.next()){
                id = rs2.getInt(1);}

        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }

        return id;
    }
    public int getIdEquipe_sport(String nom){
        int id=0;
        try {
            ResultSet rs3 = conn.createStatement().executeQuery(" SELECT * FROM `équipe`  WHERE `Nom` ='"+nom+"' ");
            while (rs3.next()){
                id = rs3.getInt(4);}

        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }

        return id;
    }
}
