package Matériaux.afficherMatériel;

import DataBase.src.Connexion;
//import com.jfoenix.controls.JFXTextField;
import alert.AlertMaker;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

//import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;



public class Controller implements Initializable{

    private ObservableList <Matériel> listMat;

    Connection conn= Connexion.connecterDB();
    PreparedStatement st;
    ResultSet rst;


    @FXML
    private Button help;
    @FXML
    public void help(ActionEvent event) throws IOException {
        try {
            URL url = Paths.get("./Fxml/help.fxml").toUri().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Stage stage = (Stage) help.getScene().getWindow();
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
        }catch (IOException io){
            io.printStackTrace();
        }
    }
    @FXML
    private Button btnTerrainSalle;

    @FXML
    private TableView<Matériel> tabListMatériel;

    @FXML
    private TableColumn<Matériel, Integer> nCol;

    @FXML
    private TableColumn<Matériel, String> DésignationCol;

    @FXML
    private TableColumn<Matériel, Integer> nombreCol;

    @FXML
    private Button btnAjouter;

    @FXML
    private TextField txtDésignation;

    @FXML
    private TextField txtNombre;

    @FXML
    private Button btnSupprimer;

    @FXML
    private Button btnActualiser;

    @FXML
    private TextField recherche;

    @FXML
    private Button home;



    @FXML
    private Button btnretour;

    @Override
    public void initialize(URL url,ResourceBundle rb) {
        load();
    }
    @FXML
    public void load(){
        try {
            listMat = FXCollections.observableArrayList();
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM `matériel`  WHERE 1");

            while (rs.next()) {
                int nm = rs.getInt(1);
                String des = rs.getString(2);
                int nbr = rs.getInt(3);

                listMat.add(new Matériel(nm, des, nbr));

            }
        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }

        nCol.setCellValueFactory(new PropertyValueFactory<Matériel, Integer>("id"));
        DésignationCol.setCellValueFactory(new PropertyValueFactory<Matériel, String>("Désignation"));
        nombreCol.setCellValueFactory(new PropertyValueFactory<Matériel, Integer>("nombre"));

        tabListMatériel.setItems(listMat);
    }
    @FXML
    void ActualiserMatériel(ActionEvent event) {


       load();
    }


    @FXML
    public void supprimerMatériel(javafx.event.ActionEvent event) {
        //Fetch the selected row
        Matériel selectedForDeletion = (Matériel) tabListMatériel.getSelectionModel().getSelectedItem();
        if (selectedForDeletion == null) {
            AlertMaker.showErrorMessage("Matériel non séléctionné", "séléctionner un matériel à supprimer");
            return;
        }



        Boolean result = deleteMatériel(selectedForDeletion);
        if (result) {
            AlertMaker.showSimpleAlert("Matériel Supprimé", selectedForDeletion.getDésignation() + " was deleted successfully.");
            listMat.remove(selectedForDeletion);
        } else {
            AlertMaker.showSimpleAlert("Failed", selectedForDeletion.getDésignation() + " ne peut pas être supprimé");
        }

    }

@FXML
    public void ajouterMateriel(ActionEvent actionEvent) {
        try {

            String query = "INSERT INTO `bdd`.`matériel` (`idmatériel`, `Désignation`, `nombre`) VALUES (NULL,'"+txtDésignation.getText()+"','"+txtNombre.getText()+"')";
            st=conn.prepareStatement(query);

            st.execute();
            st.close();
        }catch (SQLException e){
            System.out.println(e.getMessage());

        }

    }
    public boolean deleteMatériel(Matériel mater) {

        try {
            String deleteStatement = "DELETE FROM `bdd`.`matériel` WHERE `matériel`.`idmatériel` = ?";
            java.sql.PreparedStatement stmt = conn.prepareStatement(deleteStatement);
            stmt.setInt(1, mater.getId());
            int res = stmt.executeUpdate();
            if (res == 1) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Connexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }


    @FXML
    void goToTrrainSall(ActionEvent event) {
        try {
        URL url = Paths.get("./Fxml/h21.fxml").toUri().toURL();
        FXMLLoader loader = new FXMLLoader(url);
        Stage stage = (Stage) this.btnTerrainSalle.getScene().getWindow();
        Scene scene = new Scene((Parent) loader.load());
        stage.setScene(scene);
    }catch (IOException io){
        io.printStackTrace();
    }

    }

    @FXML
    void retour(ActionEvent event) {
        try {
            URL url = Paths.get("./fxml/_Home.fxml").toUri().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Stage stage = (Stage) this.btnretour.getScene().getWindow();
            Scene scene = new Scene((Parent) loader.load());
            stage.setScene(scene);
        }catch (IOException io){
            io.printStackTrace();
        }

    }

    @FXML
    void goHome(ActionEvent event) {
        try {
            URL url = Paths.get("./fxml/_Home.fxml").toUri().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Stage stage = (Stage) this.home.getScene().getWindow();
            Scene scene = new Scene((Parent) loader.load());
            stage.setScene(scene);
        }catch (IOException io){
            io.printStackTrace();
        }

    }


}

