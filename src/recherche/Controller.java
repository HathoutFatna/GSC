package recherche;

import DataBase.src.Connexion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.SQLException;



public class Controller {


    private ObservableList<Element> list;

    java.sql.Connection conn= Connexion.connecterDB();

    @FXML
    private TextField txt;
    @FXML
    private TableColumn<Element, String> ism;
    @FXML
    private TableColumn<Element, String> tag;
    @FXML
    private TableView<Element> tab;

    @FXML
    private Button btnretour;
    @FXML

    public void btnretour(ActionEvent event) throws IOException {
        try {
            URL url = Paths.get("./Fxml/_Home.fxml").toUri().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Stage stage = (Stage) btnretour.getScene().getWindow();
            Scene scene = new Scene((Parent) loader.load());
            stage.setScene(scene);
        }catch (IOException io){
            io.printStackTrace();
        }
    }
    @FXML
  public void chercher(ActionEvent event) throws IOException {


        try {
            list = FXCollections.observableArrayList();
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM `etudiant` WHERE `Nom` LIKE '"+txt.getText()+"' OR `Prénom` LIKE '"+txt.getText()+"'");

            while (rs.next()) {
                list.add(new Element(rs.getString(2)+" "+rs.getString(3),"Etudiant"));
            }
            ResultSet res = conn.createStatement().executeQuery("SELECT * FROM `entraineur` WHERE `Nom` LIKE '"+txt.getText()+"' OR `Prénom` LIKE '"+txt.getText()+"'");

            while (res.next()) {
                list.add(new Element(res.getString(2)+" "+res.getString(3),"Entraineur"));

            }
            ResultSet rs1 = conn.createStatement().executeQuery("SELECT * FROM `sport_collectif` WHERE `Nom` LIKE '"+txt.getText()+"' ");

            while (rs1.next()) {
                list.add(new Element(rs1.getString(2),"Sport Collectif"));
            }
            ResultSet rs2 = conn.createStatement().executeQuery("SELECT * FROM `activité_culturelle` WHERE `Nom` LIKE '"+txt.getText()+"' ");

            while (rs2.next()) {
                list.add(new Element(rs2.getString(2),"Activité Culturelle"));
            }
            ResultSet rs3 = conn.createStatement().executeQuery("SELECT * FROM `sport_idividuel` WHERE `Nom` LIKE '"+txt.getText()+"' ");

            while (rs3.next()) {
                list.add(new Element(rs3.getString(2),"Sport Individuel"));
            }
            ResultSet rs4 = conn.createStatement().executeQuery("SELECT * FROM `evenement` WHERE `Nom` LIKE '"+txt.getText()+"' ");

            while (rs4.next()) {
                list.add(new Element(rs4.getString(2),"Evènement"));
            }
            ResultSet rs5 = conn.createStatement().executeQuery("SELECT * FROM `compétition` WHERE `Nom` LIKE '"+txt.getText()+"' ");

            while (rs5.next()) {
                list.add(new Element(rs5.getString(2),"Compétition"));
            }
            ResultSet rs6 = conn.createStatement().executeQuery("SELECT * FROM `entrainement` WHERE `Nom` LIKE '"+txt.getText()+"' ");

            while (rs6.next()) {
                list.add(new Element(rs6.getString(2),"Entrainement"));
            }
        } catch (SQLException ex) {
            System.err.println("rien trouvé");
        }


        ism.setCellValueFactory(new PropertyValueFactory<Element, String>("nom"));
        tag.setCellValueFactory(new PropertyValueFactory<Element, String>("table"));

        tab.setItems(list);
    }
    @FXML
    private Button home;
    @FXML

    public void home(ActionEvent actionEvent)  throws IOException {
        try {
            URL url = Paths.get("./Fxml/_Home.fxml").toUri().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Stage stage = (Stage) home.getScene().getWindow();
            Scene scene = new Scene((Parent) loader.load());
            stage.setScene(scene);
        }catch (IOException io){
            io.printStackTrace();
        }

    }
}
