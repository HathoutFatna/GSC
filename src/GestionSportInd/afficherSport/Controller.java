package GestionSportInd.afficherSport;
import DataBase.src.Connexion;


import GestionSportCol.afficherSport.SportCol;
import alert.AlertMaker;
import export.pdf.ListToPDF;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Controller implements Initializable {
    private ObservableList <SportInd> list ;

    Connection conn=Connexion.connecterDB();

    PreparedStatement st;
    ResultSet rst;
    @FXML
    private Button btnretour;
    @FXML
    private static StackPane  rootPane;
    @FXML
    private Button btnhome;

    @FXML
    private AnchorPane contentPane;


    @FXML
    private TableColumn<SportInd, Integer> nbr;


    @FXML
    private TableColumn<SportInd, String> nom;


    @FXML
    private TableView<SportInd> table;
    @FXML
    public static TableView<SportInd> table2;





    @FXML
    private ImageView home;

    @FXML
    private ImageView retour;

    @FXML
    private Button help;


    @FXML
    private Button btnentraineur;

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
    private Button btnentrainement;

    @FXML
    private Button btncompétition;

    @FXML
    private Button btnsport;
    @FXML
    private Button btnsupprimer;
    @FXML
    private Button btnload;
    @FXML
    private Button btnajouter;

    @FXML
    private TextField recherche;

    @FXML

    public void go(ActionEvent event) throws IOException {
        try {
            URL url = Paths.get("./Fxml/h7.fxml").toUri().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Stage stage = (Stage) btnajouter.getScene().getWindow();
            Scene scene = new Scene((Parent) loader.load());
            stage.setScene(scene);
        }catch (IOException io){
            io.printStackTrace();
        }
    }

    @FXML
    void gocompétition(ActionEvent event) throws Exception{
        try {
            URL url = Paths.get("./Fxml/h18.fxml").toUri().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Stage stage = (Stage) btncompétition.getScene().getWindow();
            Scene scene = new Scene((Parent) loader.load());
            stage.setScene(scene);
        }catch (IOException io){
            io.printStackTrace();
        }

    }

    @FXML
    void goentrainement(ActionEvent event)throws Exception {
        try {
            URL url = Paths.get("./Fxml/h5.fxml").toUri().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Stage stage = (Stage) btnentrainement.getScene().getWindow();
            Scene scene = new Scene((Parent) loader.load());
            stage.setScene(scene);
        }catch (IOException io){
            io.printStackTrace();
        }

    }

    @FXML
    void goentraineur(ActionEvent event)throws Exception {
        try {
            URL url = Paths.get("./Fxml/h9.fxml").toUri().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Stage stage = (Stage) btnentraineur.getScene().getWindow();
            Scene scene = new Scene((Parent) loader.load());
            stage.setScene(scene);
        }catch (IOException io){
            io.printStackTrace();
        }

    }
    @Override
    public void initialize(URL url,ResourceBundle rb) {
        load();
    }
    @FXML
    public void load(){
        try {
            list= FXCollections.observableArrayList();
            ResultSet rs=conn.createStatement().executeQuery("SELECT * FROM `sport_idividuel`  WHERE `Nom` != 'NULL'");

            while (rs.next()) {


                list.add(new SportInd(rs.getInt(1), rs.getString(2)));

            }
        } catch (SQLException ex) {
            System.err.println("Error"+ ex);
        }
        nbr.setCellValueFactory(new PropertyValueFactory<SportInd,Integer>("id"));

        nom.setCellValueFactory(new PropertyValueFactory<SportInd,String>("nom"));

        table.setItems(list);
    }
    @FXML

    public void load(ActionEvent event) {

      load();
    }




    @FXML




    public void retour(ActionEvent event) throws IOException {
        try {
            URL url = Paths.get("./Fxml/h4.fxml").toUri().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Stage stage = (Stage) btnretour.getScene().getWindow();
            Scene scene = new Scene((Parent) loader.load());
            stage.setScene(scene);
        }catch (IOException io){
            io.printStackTrace();
        }
    }
    @FXML

    public void gosport(ActionEvent event) throws IOException {
        try {
            URL url = Paths.get("./Fxml/h4.fxml").toUri().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Stage stage = (Stage) btnsport.getScene().getWindow();
            Scene scene = new Scene((Parent) loader.load());
            stage.setScene(scene);
        }catch (IOException io){
            io.printStackTrace();
        }
    }


    public boolean deleteSportIndividuel(SportInd sportCol) {

        try {
            String deleteStatement = "DELETE FROM `bdd`.`sport_idividuel` WHERE `sport_idividuel`.`idSport_Idividuel` = ?";
            java.sql.PreparedStatement stmt = conn.prepareStatement(deleteStatement);
            stmt.setInt(1, sportCol.getId());
            int res = stmt.executeUpdate();
            if (res == 1) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Connexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    @FXML Button Detail;

    @FXML
    public void gett(SportInd sportInd){
        try {
            URL url = Paths.get("./Fxml/h2.fxml").toUri().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Stage stage = (Stage) Detail.getScene().getWindow();
            Scene scene = new Scene((Parent) loader.load());
            stage.setScene(scene);
        }catch (IOException io){
            io.printStackTrace();
        }
    }

    @FXML
    public void Detail(ActionEvent event) throws IOException{
        table2=table;
        SportInd selectedForDetail = table2.getSelectionModel().getSelectedItem();
        if (selectedForDetail == null) {
            AlertMaker.showErrorMessage("Sport non séléctionné", "séléctionner un sport ");
            return;
        }
        gett(selectedForDetail);
    }

    @FXML
    public void supprimer(ActionEvent event) {
        //Fetch the selected row
        SportInd selectedForDeletion = table.getSelectionModel().getSelectedItem();
        if (selectedForDeletion == null) {
            AlertMaker.showErrorMessage("Sport non séléctionné", "séléctionner un sport à supprimer");
            return;
        }



        Boolean result = deleteSportIndividuel(selectedForDeletion);
        if (result) {
            AlertMaker.showSimpleAlert("Sport Supprimé", selectedForDeletion.getNom() + " was deleted successfully.");
            list.remove(selectedForDeletion);
        } else {
            AlertMaker.showSimpleAlert("Failed", selectedForDeletion.getNom() + " ne peut pas être supprimé");
        }

    }


    public void home(ActionEvent actionEvent)  throws IOException{
        try {
            URL url = Paths.get("./Fxml/h14.fxml").toUri().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Stage stage = (Stage) btnhome.getScene().getWindow();
            Scene scene = new Scene((Parent) loader.load());
            stage.setScene(scene);
        }catch (IOException io){
            io.printStackTrace();
        }
    }


}
