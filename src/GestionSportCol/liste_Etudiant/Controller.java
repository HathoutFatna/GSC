package GestionSportCol.liste_Etudiant;

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
  private ObservableList <Etudiant> list ;

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
    private TableView<Etudiant> tableView;

    @FXML
    private TableColumn<Etudiant, Integer> NCol;

    @FXML
    private TableColumn<Etudiant, String> nomCol;

    @FXML
    private TableColumn<Etudiant, String> prenomCol;

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

            ResultSet rs=conn.createStatement().executeQuery("SELECT * FROM `etudiant`  WHERE `etudiant`.`équipe_sport_collectif_idsport_collectif` = '"+id+"'");

            while (rs.next()) {
                int num = rs.getInt(1);
                String name = rs.getString(2);
                String surname = rs.getString(3);

                list.add(new Etudiant(num, name, surname));

            }
        } catch (SQLException ex) {
            System.err.println("Error"+ ex);
        }

        NCol.setCellValueFactory(new PropertyValueFactory<Etudiant,Integer>("id"));
        nomCol.setCellValueFactory(new PropertyValueFactory<Etudiant,String>("nom"));
        prenomCol.setCellValueFactory(new PropertyValueFactory<Etudiant,String>("prénom"));

        tableView.setItems(list);
    }

@FXML

        public void loadData(ActionEvent event) {

        load();

        }




    //////////////////////////////////////////////////////////////////////////////
    public boolean deleteEtudiant(Etudiant etudiant) {

        try {
            String deleteStatement = "DELETE FROM `bdd`.`etudiant` WHERE `etudiant`.`idETUDIANT` = ?";
            java.sql.PreparedStatement stmt = conn.prepareStatement(deleteStatement);
            stmt.setInt(1, etudiant.getId());
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
        Etudiant selectedForDeletion = tableView.getSelectionModel().getSelectedItem();
        if (selectedForDeletion == null) {
            AlertMaker.showErrorMessage("Etudiant non séléctionné", "séléctionner un etudiant à supprimer");
            return;
        }



        Boolean result = deleteEtudiant(selectedForDeletion);
        if (result) {
            AlertMaker.showSimpleAlert("Etudiant Supprimé", selectedForDeletion.getNom() + " was deleted successfully.");
            list.remove(selectedForDeletion);
        } else {
            AlertMaker.showSimpleAlert("Failed", selectedForDeletion.getNom() + " ne peut pas être supprimé");
        }

    }

    public static void initPDFExprot(StackPane root, Node contentPane, Stage stage, List<List> data) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save as PDF");
        FileChooser.ExtensionFilter extFilter
                = new FileChooser.ExtensionFilter("PDF files (*.pdf)", "*.pdf");
        fileChooser.getExtensionFilters().add(extFilter);
        File saveLoc = fileChooser.showSaveDialog(stage);
        ListToPDF ltp = new ListToPDF();
        boolean flag = ltp.doPrintToPdf(data, saveLoc, ListToPDF.Orientation.LANDSCAPE);
        Button openBtn = new Button("View File");
        openBtn.setOnAction((ActionEvent event1) -> {
            try {
                Desktop.getDesktop().open(saveLoc);
            } catch (Exception exp) {
                AlertMaker.showErrorMessage("Could not load file", "Cant load file");
            }
        });
        if (flag) {
            AlertMaker.showSimpleAlert("Export terminé", "Votre Liste à été bien exportée .. Réussi !");
        }
    }

    private Stage getStage() {
        return (Stage) tableView.getScene().getWindow();
    }
    @FXML
    public void exportAsPDF(ActionEvent event) {
        List<List> printData = new ArrayList<>();
        String[] headers = { " nom    ", " prénom  "};
        printData.add(Arrays.asList(headers));
        for (Etudiant etudiant : list) {
            List<String> row = new ArrayList<>();
            row.add(etudiant.getNom());
            row.add(etudiant.getPrénom());


            printData.add(row);
        }

        initPDFExprot( rootPane, contentPane, getStage(), printData);
    }



}


