package etudiant.afficherEtud;
import DataBase.src.Connexion;

import etudiant.afficherEtud.Etudiant;
import Entraineurs.listeEntraineur.Entraineur;
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

public class Controller implements Initializable{
    private ObservableList <Etudiant> list ;

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
    private Button btnexporter;

    @FXML
    private AnchorPane contentPane;



    @FXML
    private TableColumn<Etudiant, String> surname;
    @FXML
    private TableColumn<Etudiant, Integer> num;

    @FXML
    private TableColumn<Etudiant, String> nom;


    @FXML
    private TableView<Etudiant> table;
    @FXML
    public static TableView<Etudiant> tableView;

    @FXML
    private AnchorPane noméquipe;



    @FXML
    private ImageView home;

    @FXML
    private ImageView retour;

    @FXML
    private TableColumn<Etudiant, String> date;

    @FXML
    private TableColumn<Etudiant, String> tel;

    @FXML
    private TableColumn<Etudiant, String> email;
    @FXML
    private TableColumn<Etudiant, String> sport;
    @FXML
    private TableColumn<Etudiant, String> activité_culturelle;

    @FXML
    private Button btnsupprimer;
    @FXML
    private Button Détails;
    @FXML
    private Button btngoto;

    @FXML
    private TextField recherche;

    @FXML

    public void Goto(ActionEvent event) throws IOException {
        try {
            URL url = Paths.get("./fxml/h25.fxml").toUri().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Stage stage = (Stage) btngoto.getScene().getWindow();
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
            ResultSet rs=conn.createStatement().executeQuery("SELECT * FROM `etudiant`  WHERE 1");

            while (rs.next()) {


                list.add(new Etudiant( rs.getInt(1),rs.getString(2), rs.getString(3),rs.getDate(4),
                        rs.getString(5),rs.getString(6),getNomsportCol(rs.getInt(15)),getNomactivité(rs.getInt(13))));

            }
        } catch (SQLException ex) {
            System.err.println("Error"+ ex);
        }


        nom.setCellValueFactory(new PropertyValueFactory<Etudiant,String>("nom"));
        surname.setCellValueFactory(new PropertyValueFactory<Etudiant,String>("prénom"));
        date.setCellValueFactory(new PropertyValueFactory<Etudiant,String>("date"));
        email.setCellValueFactory(new PropertyValueFactory<Etudiant,String>("email"));
        table.setItems(list);

    }

    public String getNomactivité(int id){
        String activité ="";


        try{   ResultSet rst = conn.createStatement().executeQuery("SELECT * FROM `activité_culturelle` WHERE `idActivité_Culturelle` = "+id+"");
            while (rst.next()){
                activité= rst.getString(2);}}
        catch (SQLException ex) {
            System.err.println("Error" + ex);
        }

        return activité;
    }
    public String getNomsportCol(int id){
        String sport ="";


        try{   ResultSet rst = conn.createStatement().executeQuery("SELECT * FROM `sport_collectif` WHERE `idsport_collectif` = "+id+"");
            while (rst.next()){
                sport= rst.getString(2);}}
        catch (SQLException ex) {
            System.err.println("Error" + ex);
        }

        return sport;
    }


    @FXML
    public void gettt(Etudiant etud) {
        try {
            URL url = Paths.get("./Fxml/h24.fxml").toUri().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Stage stage = (Stage) Détails.getScene().getWindow();
            Scene scene = new Scene((Parent) loader.load());
            stage.setScene(scene);
        }catch (IOException io){
            io.printStackTrace();
        }

    }
    @FXML
    public void getDetail(ActionEvent event) throws IOException{
        tableView=table;
        Etudiant selectedForDetail = tableView.getSelectionModel().getSelectedItem();
        if (selectedForDetail == null) {
            AlertMaker.showErrorMessage("étudiant non séléctionnée", "séléctionner un étudiant ");
            return;
        }
        gettt(selectedForDetail);
    }

   public void loadData(ActionEvent event) {
        load();

    }

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
  public void retour (ActionEvent event) throws IOException {
        try {
            URL url = Paths.get("./fxml/_Home.fxml").toUri().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Stage stage = (Stage) btnretour.getScene().getWindow();
            Scene scene = new Scene((Parent) loader.load());
            stage.setScene(scene);
        }catch (IOException io){
            io.printStackTrace();
        }
    }





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
        Etudiant selectedForDeletion = table.getSelectionModel().getSelectedItem();
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


    public void home(ActionEvent actionEvent)  throws IOException{
        try {
            URL url = Paths.get("./fxml/_Home.fxml").toUri().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Stage stage = (Stage) btnhome.getScene().getWindow();
            Scene scene = new Scene((Parent) loader.load());
            stage.setScene(scene);
        }catch (IOException io){
            io.printStackTrace();
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
        return (Stage) table.getScene().getWindow();
    }
    @FXML
    public void exportAsPDF(ActionEvent event) {
        List<List> printData = new ArrayList<>();
        String[] headers = { " nom    ", " prénom  ","  Email  "};
        printData.add(Arrays.asList(headers));
        for (Etudiant etudiant : list) {
            List<String> row = new ArrayList<>();
            row.add(etudiant.getNom());
            row.add(etudiant.getPrénom());
            row.add(etudiant.getEmail());

            printData.add(row);
        }

        initPDFExprot( rootPane, contentPane, getStage(), printData);
    }





}
