package GestionCulture.listeActivitéCulturelle;

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
    private ObservableList<activité_culturelle> list;

    Connection conn = Connexion.connecterDB();

    @FXML
    private StackPane rootPane;
    @FXML
    private AnchorPane contentPane;

    @FXML
    private Button retour;

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
    private TableView<activité_culturelle> tableView;

    @FXML
    public static TableView<activité_culturelle> tableView2;

    @FXML
    private TableColumn<activité_culturelle, String> nomCol;

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
            URL url = Paths.get("./Fxml/_Home.fxml").toUri().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Stage stage = (Stage) retour.getScene().getWindow();
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
        } catch (IOException io) {
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
            ResultSet rs=conn.createStatement().executeQuery("SELECT * FROM `activité_culturelle` WHERE `Nom` != 'NULL'");

            while (rs.next()) {
                int id=rs.getInt(1);
                String name = rs.getString(2);
                list.add(new activité_culturelle(id,name));

            }
        } catch (SQLException ex) {
            System.err.println("Error"+ ex);
        }

        nomCol.setCellValueFactory(new PropertyValueFactory<activité_culturelle,String>("nom"));
        tableView.setItems(list);
    }
    @FXML

    public void loadData(ActionEvent event) {
        load();
    }
    @FXML Button Detail;

    @FXML
    public void gett(activité_culturelle activitéCulturelle){
        try {
            URL url = Paths.get("./Fxml/h13.fxml").toUri().toURL();
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
        tableView2=tableView;
        activité_culturelle selectedForDetail = tableView2.getSelectionModel().getSelectedItem();
        if (selectedForDetail == null) {
            AlertMaker.showErrorMessage("Sport non séléctionné", "séléctionner un sport ");
            return;
        }
        gett(selectedForDetail);
    }

    @FXML
    public void passer(ActionEvent event) throws IOException {
        try {
            URL url = Paths.get("./Fxml/_Home.fxml").toUri().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Stage stage = (Stage) go.getScene().getWindow();
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    @FXML
    public void ajouter(ActionEvent event) throws IOException {
        try {
            URL url = Paths.get("./Fxml/h14.fxml").toUri().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Stage stage = (Stage) ajouter.getScene().getWindow();
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
        } catch (IOException io) {
            io.printStackTrace();
        }
    }





        public boolean deleteEntraineur(activité_culturelle activ) {

            try {
                String deleteStatement = "DELETE FROM `bdd`.`activité_culturelle` WHERE `activité_culturelle`.`Nom` = ?";
                java.sql.PreparedStatement stmt = conn.prepareStatement(deleteStatement);
                stmt.setString(1, activ.getNom());
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
            activité_culturelle selectedForDeletion = tableView.getSelectionModel().getSelectedItem();
            if (selectedForDeletion == null) {
                AlertMaker.showErrorMessage("activité non séléctionnée", "séléctionner une activité à supprimer");
                return;
            }



                Boolean result = deleteEntraineur(selectedForDeletion);
                if (result) {
                    AlertMaker.showSimpleAlert("activité Supprimée", selectedForDeletion.getNom() + " was deleted successfully.");
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
        String[] headers = { " nom    "};
        printData.add(Arrays.asList(headers));
        for (activité_culturelle activ : list) {
            List<String> row = new ArrayList<>();
            row.add(activ.getNom());

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


