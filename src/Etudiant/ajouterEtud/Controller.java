package etudiant.ajouterEtud;
import Entraineurs.listeEntraineur.Entraineur;
import alert.AlertMaker;
import javafx.collections.ObservableList;
import javafx.embed.swing.JFXPanel;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import DataBase.src.Connexion;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;



public class Controller implements Initializable{
    @FXML
    private AnchorPane rootPane;


    Connection cnx= Connexion.connecterDB();

    PreparedStatement st;
    ResultSet rst;
    @FXML
    private TextField name;
    @FXML
    private ComboBox comboculture;

    @FXML
    private ComboBox combocollect;

    @FXML
    private ComboBox comboindiv;

    @FXML
    private ComboBox comboéquipe;

    @FXML
    private TextField prénom;

    @FXML
    private TextField email;

    @FXML
    private TextField teleph;

    @FXML
    private TextField adr;

    @FXML
    private TextField groupage;

    @FXML
    private TextField poid;

    @FXML
    private TextField taille;

    @FXML
    private TextField actculturelle;

    @FXML
    private TextField sport;

    @FXML
    private TextField équipe;

    @FXML
    private DatePicker d_naissance;
    @FXML
    private TextArea textArea;
    @FXML
    private ImageView logo;
    @FXML
    private Button btnparcourir;

    @FXML
    private Button btnajouter;

    @FXML
    private Button btnretour;
    private FileInputStream fis;
    private FileChooser fileChooser;
    private File file;
    private Image image;
    @Override
    public void initialize(URL url,ResourceBundle rb) {
        charger();
    }
    public void charger() {

        try {
            ResultSet rs = cnx.createStatement().executeQuery("SELECT * FROM `activité_culturelle`  WHERE 1");

            while (rs.next()) {
                String name = rs.getString(2);
                comboculture.getItems().add(name);
            }
        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }
        try {
            ResultSet rs = cnx.createStatement().executeQuery("SELECT * FROM `sport_collectif`  WHERE 1");

            while (rs.next()) {
                String name = rs.getString(2);
                combocollect.getItems().add(name);
            }
        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }

        try {
            ResultSet rs = cnx.createStatement().executeQuery("SELECT * FROM `sport_idividuel`  WHERE 1");

            while (rs.next()) {
                String name = rs.getString(2);
                comboindiv.getItems().add(name);
            }
        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }
        try {
            ResultSet rs = cnx.createStatement().executeQuery("SELECT * FROM `équipe`  WHERE 1");

            while (rs.next()) {
                String name = rs.getString(2);
                comboéquipe.getItems().add(name);
            }
        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }
    }
    @FXML
  public void retour(ActionEvent event) throws IOException {
        try {
            URL url = Paths.get("./fxml/h23.fxml").toUri().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Stage stage = (Stage) btnretour.getScene().getWindow();
            Scene scene = new Scene((Parent) loader.load());
            stage.setScene(scene);
        }catch (IOException io){
            io.printStackTrace();
        }
    }
    @FXML
    public void parcourir(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        Stage primaryStage = (Stage) rootPane.getScene().getWindow();
        file = fileChooser.showOpenDialog(primaryStage);
        if (file != null) {
            textArea.setText(file.getAbsolutePath());
            image = new Image(file.toURI().toString());
            logo.setImage(image);
        }

    }



  @FXML
  public void valider(ActionEvent actionEvent){
      try {

          fis= new FileInputStream(file);
        String query ="INSERT INTO `bdd`.`etudiant` (`idETUDIANT`, `Nom`, `Prénom`, `DDN`, `Email`, `Telephone`, `poid`, `taille`, `Groupage`,`photo`, `Adresse`" +
                ", `Sport_Idividuel_idSport_Idividuel`, `Activité_Culturelle_idActivité_Culturelle`, `équipe_idéquipe`, `équipe_sport_collectif_idsport_collectif`) VALUES (NULL, '"+name.getText()+"', '"+prénom.getText()+"', '"+d_naissance.getValue()+"', '"+email.getText()+"', '"+teleph.getText()+"', '"+poid.getText()+"', '"+taille.getText()+"', '"+groupage.getText()+"','"+fis+"', '"+adr.getText()+"','"+getIdsport_ind((String) comboindiv.getSelectionModel().getSelectedItem())+"','"+getIdculture((String) comboculture.getSelectionModel().getSelectedItem())+"'" +
                ",'"+getIdEquipe((String) comboéquipe.getSelectionModel().getSelectedItem())+"', '"+getIdEquipe_sport((String) comboéquipe.getSelectionModel().getSelectedItem())+"')";
          st=cnx.prepareStatement(query);

          st.execute();
          AlertMaker.showSimpleAlert("Ajout terminé", "l'etudiant(e) "+name.getText()+" à été ajouté(e)");
          name.setText(" ");
          prénom.setText(" ");
          d_naissance.getEditor().clear();
          email.setText(" ");
          teleph.setText(" ");
          taille.setText(" ");
          poid.setText(" ");
          adr.setText(" ");
          groupage.setText(" ");
          logo.setImage(null);
          textArea.setText(" ");
          combocollect.getSelectionModel().clearSelection();
          comboculture.getSelectionModel().clearSelection();
          comboindiv.getSelectionModel().clearSelection();
          comboéquipe.getSelectionModel().clearSelection();

          st.close();
      }catch (SQLException e){
          System.out.println(e.getMessage());

      } catch (FileNotFoundException e) {
          e.printStackTrace();
      }

  }
    public int getIdEquipe(String nom){
        int id=0;
        try {
            ResultSet rs1 = cnx.createStatement().executeQuery(" SELECT * FROM `équipe`  WHERE `Nom` ='"+nom+"' ");
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
            ResultSet rs1 = cnx.createStatement().executeQuery(" SELECT * FROM `équipe`  WHERE `Nom` ='"+nom+"' ");
            while (rs1.next()){
                id = rs1.getInt(4);}

        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }

        return id;
    }
    public int getIdsport_ind(String nom){
        int id=0;
        try {
            ResultSet rs1 = cnx.createStatement().executeQuery(" SELECT * FROM `sport_idividuel`  WHERE `Nom` ='"+nom+"' ");
            while (rs1.next()){
                id = rs1.getInt(1);}

        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }

        return id;
    }
    public int getIdculture(String nom){
        int id=0;
        try {
            ResultSet rs1 = cnx.createStatement().executeQuery(" SELECT * FROM `activité_culturelle`  WHERE `Nom` ='"+nom+"' ");
            while (rs1.next()){
                id = rs1.getInt(1);}

        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }

        return id;
    }

}