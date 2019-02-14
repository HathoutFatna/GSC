package Compétition.afficherListCompétition;

import DataBase.src.Connexion;
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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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

public class Controller implements Initializable{
    private ObservableList<Compétition> listCom;

    Connection conn= Connexion.connecterDB();
    PreparedStatement st;
    ResultSet rst;

    @FXML
    StackPane rootPane;
    @FXML
    private AnchorPane contentPane;

    @FXML
    private Button btnSport;

    @FXML
    private Button btnEtraineur;

    @FXML
    private Button btnEtrainement;

    @FXML
    private ImageView btn;

    @FXML
    private TableView<Compétition> tabCpmétion;

    @FXML
    private TableColumn<Compétition, String> ColNom;
    @FXML
    private TableColumn<Compétition, Integer> ColN;

    @FXML
    private TableColumn<Compétition, String> colSportColect;

    @FXML
    private TableColumn<Compétition, String> colSportIndivi;

    @FXML
    private TableColumn<Compétition, String> ColDebut;

    @FXML
    private TableColumn<Compétition, String> colFin;

    @FXML
    private Button btnCréer;

    @FXML
    private Button btnImpimer;

    @FXML
    private TextField recherche;

    @FXML
    private Button btnActualiser;

    @FXML
    private Button home;

    @FXML
    private Button btnretour;


    @FXML
    void recherche(ActionEvent event) {

    }


    @FXML
    void AllerAajout(ActionEvent event) {
        try {
            URL url = Paths.get("./fxml/h19.fxml").toUri().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Stage stage = (Stage) this.btnCréer.getScene().getWindow();
            Scene scene = new Scene((Parent) loader.load());
            stage.setScene(scene);
        }catch (IOException io){
            io.printStackTrace();
        }

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
    @Override
    public void initialize(URL url,ResourceBundle rb) {
        load();
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
    public String getNomsportInd(int id){
        String sport ="";


        try{   ResultSet rst = conn.createStatement().executeQuery("SELECT * FROM `sport_idividuel` WHERE `idsport_idividuel` = "+id+"");
            while (rst.next()){
                sport= rst.getString(2);}}
        catch (SQLException ex) {
            System.err.println("Error" + ex);
        }

        return sport;
    }

    @FXML
    public void load(){
        try {
            listCom = FXCollections.observableArrayList();
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM `compétition`  WHERE 1");

            while (rs.next()) {
                int idcom = rs.getInt(1);
                String nam = rs.getString(2);
                String sc = getNomsportCol(rs.getInt(7));
                String si = getNomsportInd(rs.getInt(8));
                String dd = rs.getString(4);
                String df = rs.getString(5);

                listCom.add(new Compétition(idcom, nam, sc, si, dd, df));

            }
        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }

        ColN.setCellValueFactory(new PropertyValueFactory<Compétition, Integer>("id"));
        ColNom.setCellValueFactory(new PropertyValueFactory<Compétition, String>("nomCom"));
        colSportColect.setCellValueFactory(new PropertyValueFactory<Compétition, String>("sportColec"));
        colSportIndivi.setCellValueFactory(new PropertyValueFactory<Compétition, String>("sportIndiv"));
        ColDebut.setCellValueFactory(new PropertyValueFactory<Compétition, String>("dateDebut"));
        colFin.setCellValueFactory(new PropertyValueFactory<Compétition, String>("dateFin"));

        tabCpmétion.setItems(listCom);
    }
    @FXML
   public void actualiser(ActionEvent event) {
        load();
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

    @FXML
    void goToEtrainement(ActionEvent event) {
        try {
            URL url = Paths.get("./fxml/h5.fxml").toUri().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Stage stage = (Stage) this.btnEtrainement.getScene().getWindow();
            Scene scene = new Scene((Parent) loader.load());
            stage.setScene(scene);
        }catch (IOException io){
            io.printStackTrace();
        }


    }

    @FXML
    void goToEtraineur(ActionEvent event) {
        try {
            URL url = Paths.get("./fxml/h9.fxml").toUri().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Stage stage = (Stage) this.btnEtraineur.getScene().getWindow();
            Scene scene = new Scene((Parent) loader.load());
            stage.setScene(scene);
        }catch (IOException io){
            io.printStackTrace();
        }


    }

    @FXML
    void goToSport(ActionEvent event) {
        try {
            URL url = Paths.get("./fxml/h4.fxml").toUri().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Stage stage = (Stage) this.btnSport.getScene().getWindow();
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
        return (Stage) tabCpmétion.getScene().getWindow();
    }
    @FXML
    public void exportAsPDF(ActionEvent event) {
        List<List> printData = new ArrayList<>();
        String[] headers = { "nomCom", " sportColec", "sportIndiv","dateDebut", "dateFin"};
        printData.add(Arrays.asList(headers));
        for (Compétition compétition : listCom) {
            List<String> row = new ArrayList<>();
            row.add(compétition.getNomCom());
            row.add(compétition.getSportColec());
            row.add(compétition.getSportIndiv());
            row.add(compétition.getDateDebut());
            row.add(compétition.getDateFin());

            printData.add(row);
        }


        initPDFExprot( rootPane, contentPane, getStage(), printData);
    }
}