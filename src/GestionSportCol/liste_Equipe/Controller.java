package GestionSportCol.liste_Equipe;

import DataBase.src.Connexion;

import GestionSportCol.afficherSport.SportCol;
import alert.AlertMaker;
import export.pdf.ListToPDF;
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


public class Controller implements Initializable{
  private ObservableList <Equipe> list ;

    Connection conn=Connexion.connecterDB();


    @FXML
    private AnchorPane contentPane;
    @FXML
    private static StackPane  rootPane;

    @FXML
    private Button retour;

    @FXML
    private TextField recherche;

    @FXML
    private Button ajouter;
    @FXML
    private Button Load;

    @FXML
    private Button supprimer;

    @FXML
    private Button imprimer;

    @FXML
    private Button help;

    @FXML
    private TableView<Equipe> tableView;
    @FXML
    public  static TableView<Equipe> table3;

    @FXML
    private TableColumn<Equipe, Integer> NCol;

    @FXML
    private TableColumn<Equipe, String> nomCol;

    @FXML
    private TableColumn<Equipe, Integer> nbCol;

    @FXML
    private Button go;

    @FXML
    public void retour(ActionEvent event) throws IOException {
        try {
            URL url = Paths.get("./Fxml/h6.fxml").toUri().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Stage stage = (Stage) retour.getScene().getWindow();
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
        }catch (IOException io){
            io.printStackTrace();
        }
    }

    @FXML
    public void passer(ActionEvent event) throws IOException {
        try {
            URL url = Paths.get("./Fxml/_Home.fxml").toUri().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Stage stage = (Stage) go.getScene().getWindow();
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
        }catch (IOException io){
            io.printStackTrace();
        }
    }

    @FXML
    private Button sport;

    @FXML
    public void sport(ActionEvent event) throws IOException {
        try {
            URL url = Paths.get("./Fxml/h4.fxml").toUri().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Stage stage = (Stage) sport.getScene().getWindow();
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
        }catch (IOException io){
            io.printStackTrace();
        }
    }

    @FXML
    private Button entraineur;

    @FXML
    public void entraineur(ActionEvent event) throws IOException {
        try {
            URL url = Paths.get("./Fxml/h9.fxml").toUri().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Stage stage = (Stage) entraineur.getScene().getWindow();
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
        }catch (IOException io){
            io.printStackTrace();
        }
    }

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
    private Button entrainement;

    @FXML
    public void entrainement(ActionEvent event) throws IOException {
        try {
            URL url = Paths.get("./Fxml/h5.fxml").toUri().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Stage stage = (Stage) entrainement.getScene().getWindow();
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
        }catch (IOException io){
            io.printStackTrace();
        }
    }

    @FXML
    private Button compétition;

    @FXML
    public void compétition(ActionEvent event) throws IOException {
        try {
            URL url = Paths.get("./Fxml/h18.fxml").toUri().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Stage stage = (Stage) compétition.getScene().getWindow();
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
        }catch (IOException io){
            io.printStackTrace();
        }
    }
    @FXML Button Detail;

    @FXML
    public void gett(Equipe équipe){
        try {
            URL url = Paths.get("./Fxml/h12.fxml").toUri().toURL();
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
        table3=tableView;
        Equipe selectedForDetail = table3.getSelectionModel().getSelectedItem();
        if (selectedForDetail == null) {
            AlertMaker.showErrorMessage("équipe non séléctionnée", "séléctionner une équipe ");
            return;
        }
        gett(selectedForDetail);
    }
    @Override
    public void initialize(URL url,ResourceBundle rb) {
        load();
    }
    @FXML
    public void load(){
        try {
            SportCol selected=  GestionSportCol.afficherSport.Controller.table2.getSelectionModel().getSelectedItem();
            int id= selected.getId();
            list= FXCollections.observableArrayList();

            ResultSet rs=conn.createStatement().executeQuery("SELECT * FROM `équipe`  WHERE `équipe`.`sport_collectif_idsport_collectif` = '"+id+"'");

            while (rs.next()) {
                int num = rs.getInt(1);
                String name = rs.getString(2);
                int nb = rs.getInt(3);

                list.add(new Equipe(num, name, nb));

            }
        } catch (SQLException ex) {
            System.err.println("Error"+ ex);
        }

        NCol.setCellValueFactory(new PropertyValueFactory<Equipe,Integer>("id"));
        nomCol.setCellValueFactory(new PropertyValueFactory<Equipe,String>("nom"));
        nbCol.setCellValueFactory(new PropertyValueFactory<Equipe,Integer>("nb"));

        tableView.setItems(list);
    }

@FXML

        public void loadData(ActionEvent event) {

          load();
        }




    public boolean delete(Equipe entraineur) {

            try {
                String deleteStatement = "DELETE FROM `bdd`.`équipe` WHERE `équipe`.`idéquipe` = ?";
                java.sql.PreparedStatement stmt = conn.prepareStatement(deleteStatement);
                stmt.setInt(1, entraineur.getId());
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
        public void supprimer(ActionEvent event) {
            //Fetch the selected row
            Equipe selectedForDeletion = tableView.getSelectionModel().getSelectedItem();
            if (selectedForDeletion == null) {
                AlertMaker.showErrorMessage("Equipe non séléctionné", "séléctionner une équipe à supprimer");
                return;
            }



                Boolean result = delete(selectedForDeletion);
                if (result) {
                    AlertMaker.showSimpleAlert("Equipe Supprimée", selectedForDeletion.getNom() + " was deleted successfully.");
                    list.remove(selectedForDeletion);
                } else {
                    AlertMaker.showSimpleAlert("Failed", selectedForDeletion.getNom() + " ne peut pas être supprimé");
                }

            }
@FXML
private Button btnajouter;
    @FXML

    public void go(ActionEvent event) throws IOException {
        try {
            URL url = Paths.get("./Fxml/h27.fxml").toUri().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Stage stage = (Stage) btnajouter.getScene().getWindow();
            Scene scene = new Scene((Parent) loader.load());
            stage.setScene(scene);
        }catch (IOException io){
            io.printStackTrace();
        }
    }


        }


