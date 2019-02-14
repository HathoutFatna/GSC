package GestionCulture.ajouterEvent;

import alert.AlertMaker;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;

import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import DataBase.src.Connexion;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ResourceBundle;


public class Controller implements Initializable {

    Connection cnx= Connexion.connecterDB();

    PreparedStatement st;
    ResultSet rst;
    @FXML
    private ComboBox combmatériel;

    @FXML
    private ComboBox combsalle;

    @FXML
    private AnchorPane anchro;

    @FXML
    private TextField remarque;

    @FXML
    private TextField lieu;

    @FXML
    private DatePicker début;

    @FXML
    private DatePicker fin;

    @FXML
    private Button btnajouter;
    @FXML
    private Button btnretour;

    @FXML
    private TextField nom;
    @FXML
    private ComboBox combactivite;
    @FXML
    private Button btnactualiser;

    @FXML
    public void retour(ActionEvent event) throws IOException {
        try {
            URL url = Paths.get("./Fxml/h17.fxml").toUri().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Stage stage = (Stage) btnretour.getScene().getWindow();
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
        }catch (IOException io){
            io.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url,ResourceBundle rb) {
        charger();
    }

    @FXML
    public void ajouter(ActionEvent event){
        try {
            String query="INSERT INTO `bdd`.`evenement` (`idEvenement`, `Nom`, `DateDébut`, `Remarque`, `DateFin`, `Salles_idSalles`, `Activité_Culturelle_idActivité_Culturelle`, `matériel_idmatériel`)" +
                    " VALUES (NULL, '"+nom.getText()+"', '"+début.getValue()+"','"+remarque.getText()+"', '"+fin.getValue()+"', '"+getIdSalle((String) combsalle.getSelectionModel().getSelectedItem())+"','"+getIdCulture((String) combactivite.getSelectionModel().getSelectedItem())+"'," +
                    " '"+getIdMateriel((String) combmatériel.getSelectionModel().getSelectedItem())+"');";
            st=cnx.prepareStatement(query);


            st.execute();
            AlertMaker.showSimpleAlert("Ajout terminé", "l'évènement "+nom.getText()+" à été ajouté");
            st.close();
        }catch (SQLException e){
            System.out.println(e.getMessage());

        }

    }


    public int getIdSalle(String nom){
        int id=0;
        try {
            ResultSet rs1 = cnx.createStatement().executeQuery(" SELECT * FROM `salles`  WHERE `Nom` ='"+nom+"' ");
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
            ResultSet rs2 = cnx.createStatement().executeQuery(" SELECT * FROM `matériel`  WHERE `Désignation` ='"+nom+"' ");
               while (rs2.next()){
                id = rs2.getInt(1);}

        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }

        return id;
    }

    public int getIdCulture(String nom){
        int id=0;
        try {
            ResultSet rs3 = cnx.createStatement().executeQuery(" SELECT * FROM `activité_culturelle`  WHERE `Nom` ='"+nom+"' ");
            while (rs3.next()){
                id = rs3.getInt(1);}

        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }

        return id;
    }

    @FXML
    public void charger() {


            try {
                ResultSet rs = cnx.createStatement().executeQuery("SELECT * FROM `activité_culturelle`  WHERE 1");

                while (rs.next()) {
                    String name = rs.getString(2);
                    combactivite.getItems().add(name);
                }
            } catch (SQLException ex) {
                System.err.println("Error" + ex);
            }


        try {
            ResultSet rs = cnx.createStatement().executeQuery("SELECT * FROM `salles`  WHERE 1");

            while (rs.next()) {
                String name = rs.getString(3);
                combsalle.getItems().add(name);
            }
        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }

        try {
            ResultSet rs = cnx.createStatement().executeQuery("SELECT * FROM `matériel`  WHERE 1");

            while (rs.next()) {
                String name = rs.getString(2);
                combmatériel.getItems().add(name);
            }
        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }
    }}

