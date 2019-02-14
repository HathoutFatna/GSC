package GestionCulture.listeEvenementActivitéCulturelle;

import DataBase.src.Connexion;

import GestionCulture.listeActivitéCulturelle.activité_culturelle;
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
import java.sql.ResultSet;

import java.sql.SQLException;
import java.util.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Controller implements Initializable{
  private ObservableList <Evenement> list ;

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
    private TableView<Evenement> tableView;

    @FXML
    private TableColumn<Evenement, String> nomCol;

    @FXML
    private TableColumn<Evenement, String> lieuCol;
    @FXML
    private TableColumn<Evenement, String> activitéCol;
    @FXML
    private TableColumn<Evenement, Date> dateCol;



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
    private Button go;

    @FXML
    public void retour(ActionEvent event) throws IOException {
        try {
            URL url = Paths.get("./Fxml/h13.fxml").toUri().toURL();
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
    @Override
    public void initialize(URL url,ResourceBundle rb) {
        load();
    }
    @FXML
    public void load(){
        try {
            activité_culturelle selected= GestionCulture.listeActivitéCulturelle.Controller.tableView2.getSelectionModel().getSelectedItem();
            int id= selected.getId();
            list= FXCollections.observableArrayList();
            ResultSet rs=conn.createStatement().executeQuery("SELECT * FROM `evenement`  WHERE `Activité_Culturelle_idActivité_Culturelle` = '"+id+"'");

            while (rs.next()) {
                String name = rs.getString("Nom");
                Date d = rs.getDate(3);

                list.add(new Evenement(name, getNomsalle(rs.getInt(6)), d,getNomActivité(rs.getInt(7))));

            }
        } catch (SQLException ex) {
            System.err.println("Error"+ ex);
        }

        nomCol.setCellValueFactory(new PropertyValueFactory<Evenement,String>("nom"));
        lieuCol.setCellValueFactory(new PropertyValueFactory<Evenement,String>("lieu"));
        dateCol.setCellValueFactory(new PropertyValueFactory<Evenement,Date>("date"));



        tableView.setItems(list);
    }

@FXML

        public void loadData(ActionEvent event) {
        load();
        }

        public String getNomActivité(int id){
        String activité ="";
            try{
                ResultSet rst = conn.createStatement().executeQuery("SELECT * FROM `activité_culturelle` WHERE `idActivité_Culturelle` = "+id+"");
                while (rst.next()){
                activité= rst.getString(2);}}
            catch (SQLException ex) {
                System.err.println("Error" + ex);
            }

        return activité;
        }
    public String getNomsalle(int id){
        String salle ="";


        try{   ResultSet rst = conn.createStatement().executeQuery("SELECT * FROM `salles` WHERE `idSalles` = "+id+"");
            while (rst.next()){
            salle= rst.getString(3);}}
        catch (SQLException ex) {
            System.err.println("Error" + ex);
        }

        return salle;
    }



        public boolean delete(Evenement event) {

            try {
                String deleteStatement = "DELETE FROM `bdd`.`evenement` WHERE `evenement`.`Nom` = ?";
                java.sql.PreparedStatement stmt = conn.prepareStatement(deleteStatement);
                stmt.setString(1, event.getNom());
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
            Evenement selectedForDeletion = tableView.getSelectionModel().getSelectedItem();
            if (selectedForDeletion == null) {
                AlertMaker.showErrorMessage("Evenement non séléctionné", "séléctionner un evenement à supprimer");
                return;
            }



                Boolean result = delete(selectedForDeletion);
                if (result) {
                    AlertMaker.showSimpleAlert("Evenement Supprimé", selectedForDeletion.getNom() + " was deleted successfully.");
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
        String[] headers = { " nom    ", " Lieu  ","   Date   ","  Activité Culturelle"};
        printData.add(Arrays.asList(headers));
        for (Evenement eventt : list) {
            List<String> row = new ArrayList<>();
            row.add(eventt.getNom());
            row.add(eventt.getLieu());
            row.add(eventt.getDate().toString());
            row.add(eventt.getActivité());

            printData.add(row);
        }
        initPDFExprot( rootPane, contentPane, getStage(), printData);
    }


    @FXML
    private Button Activité;

    @FXML
    public void activity(ActionEvent event) throws IOException {
        try {
            URL url = Paths.get("./Fxml/h16.fxml").toUri().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Stage stage = (Stage) Activité.getScene().getWindow();
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
        }catch (IOException io){
            io.printStackTrace();
        }
    }
    @FXML
    private Button eventt;

    @FXML
    public void event(ActionEvent event) throws IOException {
        try {
            URL url = Paths.get("./Fxml/h17.fxml").toUri().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Stage stage = (Stage) eventt.getScene().getWindow();
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
        }catch (IOException io){
            io.printStackTrace();
        }
    }





        }


