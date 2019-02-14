package Matériaux.afficherTerrainSalle;

import DataBase.src.Connexion;
import alert.AlertMaker;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

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
    private ObservableList<TerrainSalle> listTS;

    Connection conn= Connexion.connecterDB();
    PreparedStatement st;
    ResultSet rst;

    @FXML
    private Button btnMateriaux;

    @FXML
    private Label labMatériaux;

    @FXML
    private TableView<TerrainSalle> tabTerrainSalle;

    @FXML
    private TableColumn<TerrainSalle, String> colNom;

    @FXML
    private TableColumn<TerrainSalle, Integer> colLieu;

    @FXML
    private TextField txtnom;

    @FXML
    private TextField txtLieu;

    @FXML
    private Button btnAjouterST;

    @FXML
    private Button btnActualiserST;

    @FXML
    private Button btnSupprimerST;

    @FXML
    private TextField recherche;

    @FXML
    private Button home;

    @FXML
    private Button btnretour;

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
    @FXML
    public void load(){
        try {
            listTS = FXCollections.observableArrayList();
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM `salles`  WHERE 1");

            while (rs.next()) {
                int n = rs.getInt(1);
                String l = rs.getString(2);
                String nam= rs.getString(3);


                listTS.add(new TerrainSalle(n, l, nam));

            }
        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }

        //nCol.setCellValueFactory(new PropertyValueFactory<Matériel, Integer>("id"));
        colNom.setCellValueFactory(new PropertyValueFactory<TerrainSalle, String>("nom"));
        colLieu.setCellValueFactory(new PropertyValueFactory<TerrainSalle, Integer>("lieu"));

        tabTerrainSalle.setItems(listTS);
    }
    @FXML
    void ActualiserTerrainSalle(ActionEvent event) {


       load();
    }


    @FXML
    public void ajouterTerrainSalle( ActionEvent actionEvent) {
        try {

            String query = "INSERT INTO `bdd`.`salles` (`idSalles`, `Lieu`, `Nom`) VALUES (NULL,'" + txtLieu.getText() + "','" + txtnom.getText() + "')";
            st = conn.prepareStatement(query);

            st.execute();
            st.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
    }
    public boolean deleteTerrain(TerrainSalle ts) {

        try {
            String deleteStatement = "DELETE FROM `bdd`.`salles` WHERE `salles`.`idSalles` = ?";
            java.sql.PreparedStatement stmt = conn.prepareStatement(deleteStatement);
            stmt.setInt(1, ts.getId());
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
    public void supprimerTerrainSalle(javafx.event.ActionEvent event) {
        //Fetch the selected row
        TerrainSalle selectedForDeletion = (TerrainSalle) tabTerrainSalle.getSelectionModel().getSelectedItem();
        if (selectedForDeletion == null) {
            AlertMaker.showErrorMessage("Terrain/Salle non séléctionné", "séléctionner un Terrain/Salle à supprimer");
            return;
        }



        Boolean result = deleteTerrain(selectedForDeletion);
        if (result) {
            AlertMaker.showSimpleAlert("Terrain/Salle Supprimé", selectedForDeletion.getNom() + " was deleted successfully.");
            listTS.remove(selectedForDeletion);
        } else {
            AlertMaker.showSimpleAlert("Failed", selectedForDeletion.getNom() + " ne peut pas être supprimé");
        }

    }
@FXML
    void retour(ActionEvent event) {
        try {
            URL url = Paths.get("./fxml/h22.fxml").toUri().toURL();
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

    @FXML
    void goMateriaux(ActionEvent event) {
        try {
            URL url = Paths.get("./fxml/h22.fxml").toUri().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Stage stage = (Stage) this.btnMateriaux.getScene().getWindow();
            Scene scene = new Scene((Parent) loader.load());
            stage.setScene(scene);
        }catch (IOException io){
            io.printStackTrace();
        }

    }





}
