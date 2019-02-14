package Entrainement.listeEntrainement;

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
import java.util.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Controller implements Initializable{
  private ObservableList <Entrainement> list ;

  PreparedStatement st;
  Connection conn=Connexion.connecterDB();


    @FXML
    private AnchorPane contentPane;
    @FXML
    private static StackPane  rootPane;
    @FXML
    private ImageView home;

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
    private TableView<Entrainement> tableView;

    @FXML
    private TableColumn<Entrainement, Integer> NCol;
    @FXML
    private TableColumn<Entrainement, String> nomCol;

    @FXML
    private TableColumn<Entrainement, String> salleCol;
    @FXML
    private TableColumn<Entrainement, Date> dateCol;

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



    @FXML
    private ComboBox combSalle;

    @FXML
    private ComboBox combMateriel;

    @FXML
    private ComboBox combSport;

    @FXML
    private ComboBox combequipe;

    @FXML
    private DatePicker date;

    @FXML
    private TextField name;


    @FXML
    public void ajouter(ActionEvent event){

        try {
            String query="INSERT INTO `bdd`.`entrainement` (`idEntrainement`, `Nom`, `Date`, `Sport_Idividuel_idSport_Idividuel`, `équipe_idéquipe`, `équipe_sport_collectif_idsport_collectif`, `Salles_idSalles`, `matériel_idmatériel`) " +
                    "VALUES (NULL, '"+name.getText()+"', '"+date.getValue()+"', NULL,  '"+getIdEquipe((String) combequipe.getSelectionModel().getSelectedItem())+"',  '"+getIdEquipe_sport((String) combequipe.getSelectionModel().getSelectedItem())+"', '"+getIdSalle((String) combSalle.getSelectionModel().getSelectedItem())+"', '"+getIdMateriel((String) combMateriel.getSelectionModel().getSelectedItem())+"');";
             st=conn.prepareStatement(query);


            st.execute();
            AlertMaker.showSimpleAlert("Ajout terminé", "l'évènement "+name.getText()+" à été ajouté");
            name.setText(" ");
            date.getEditor().clear();
            combSport.getSelectionModel().clearSelection();
            combequipe.getSelectionModel().clearSelection();
            combMateriel.getSelectionModel().clearSelection();
            combSalle.getSelectionModel().clearSelection();
            charger();
            load();
            st.close();
        }catch (SQLException e){
            System.out.println(e.getMessage());

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
    public int getIdEquipe_sport(String nom){
        int id=0;
        try {
            ResultSet rs1 = conn.createStatement().executeQuery(" SELECT * FROM `équipe`  WHERE `Nom` ='"+nom+"' ");
            while (rs1.next()){
                id = rs1.getInt(4);}

        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }

        return id;
    }
    public int getIdSalle(String nom){
        int id=0;
        try {
            ResultSet rs1 = conn.createStatement().executeQuery(" SELECT * FROM `salles`  WHERE `Nom` ='"+nom+"' ");
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
            ResultSet rs2 = conn.createStatement().executeQuery(" SELECT * FROM `matériel`  WHERE `Désignation` ='"+nom+"' ");
            while (rs2.next()){
                id = rs2.getInt(1);}

        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }

        return id;
    }
    @Override
    public void initialize(URL url,ResourceBundle rb) {
        load(); charger();
    }
    @FXML
    public void load(){

        try {
            list= FXCollections.observableArrayList();
            ResultSet rs=conn.createStatement().executeQuery("SELECT * FROM `entrainement`  WHERE 1");

            while (rs.next()) {

                list.add(new Entrainement(rs.getInt(1),rs.getString(2) , getNomsalle(rs.getInt(7)),rs.getDate(3)));

            }
        } catch (SQLException ex) {
            System.err.println("Error"+ ex);
        }

        nomCol.setCellValueFactory(new PropertyValueFactory<Entrainement,String>("nom"));
        dateCol.setCellValueFactory(new PropertyValueFactory<Entrainement,Date>("date"));
        salleCol.setCellValueFactory(new PropertyValueFactory<Entrainement,String>("salle"));


        tableView.setItems(list);
    }
@FXML

        public void loadData(ActionEvent event) {
            load();
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


    public boolean deleteEntrainement(Entrainement entrainement) {

            try {
                String deleteStatement = "DELETE FROM `bdd`.`entrainement` WHERE `entrainement`.`idEntrainement` = ?";
                java.sql.PreparedStatement stmt = conn.prepareStatement(deleteStatement);
                stmt.setInt(1, entrainement.getId());
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
            Entrainement selectedForDeletion = tableView.getSelectionModel().getSelectedItem();
            if (selectedForDeletion == null) {
                AlertMaker.showErrorMessage("Entrainement non séléctionné", "séléctionner un entrainement à supprimer");
                return;
            }



                Boolean result = deleteEntrainement(selectedForDeletion);
                if (result) {
                    AlertMaker.showSimpleAlert("Entrainement Supprimé", selectedForDeletion.getNom() + " was deleted successfully.");
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
        String[] headers = { " Nom    ", " Salle  "};
        printData.add(Arrays.asList(headers));
        for (Entrainement entrainement : list) {
            List<String> row = new ArrayList<>();
            row.add(entrainement.getNom());
            row.add(entrainement.getSalle());

            printData.add(row);
        }
        initPDFExprot( rootPane, contentPane, getStage(), printData);
    }


    @FXML
    public void charger() {

        try {
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM `sport_collectif`  WHERE `Nom` != 'NULL'");

            while (rs.next()) {
                String name = rs.getString(2);
                combSport.getItems().add(name);
            }
        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }

        try {
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM `équipe`  WHERE 1");

            while (rs.next()) {
                String name = rs.getString(2);
                combequipe.getItems().add(name);
            }
        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }


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
    }


        }


