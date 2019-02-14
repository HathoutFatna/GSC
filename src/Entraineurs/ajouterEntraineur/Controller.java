package Entraineurs.ajouterEntraineur;
import DataBase.src.Connexion;

import alert.AlertMaker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ResourceBundle;


public class Controller implements Initializable {
    @FXML
    private TextField name;
    @FXML
    private TextArea textArea;

    @FXML
    private TextField prénom;

    @FXML
    private TextField email;

    @FXML
    private TextField teleph;

    @FXML
    private TextField adr;

    @FXML
    private DatePicker d_naissance;

    @FXML
    private ImageView logo;

    @FXML
    private Button btnparcourir;

    @FXML
    private Button btnajouter;
    @FXML
    private AnchorPane rootPane;
    @FXML
    private ComboBox équipe;
    @FXML
    private  Button retour;

    private FileInputStream fis;
    private FileChooser fileChooser;
    private File file;

    private Image image;
    Connection cnx = Connexion.connecterDB();

    PreparedStatement st;
    ResultSet rst;


    @FXML
    public void retour(ActionEvent event) throws IOException {
        try {
            URL url = Paths.get("./Fxml/h9.fxml").toUri().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Stage stage = (Stage) retour.getScene().getWindow();
            Scene scene = new Scene(loader.load());
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

    Connection conn = Connexion.connecterDB();
    @Override
    public void initialize(URL url,ResourceBundle rb) {
        charger(équipe);
    }
@FXML
    public void charger(ComboBox équipe) {

        try {
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM `équipe`  WHERE 1");

            while (rs.next()) {
                String name = rs.getString(2);
                équipe.getItems().add(name);
            }
        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }

    }


  @FXML
    public void ajouter(ActionEvent event) throws FileNotFoundException {
      fis = new FileInputStream(file);
      String nom_équipe=(String) équipe.getSelectionModel().getSelectedItem();
  try{
          String query="INSERT INTO `bdd`.`entraineur` (`idEntraineur`, `Nom`, `Prénom`, `DateNaissance`, `Email`, `Telephone`, `adresse`, `photo`, `équipe_idéquipe`," +
                " `équipe_sport_collectif_idsport_collectif`) VALUES (NULL, '"+name.getText()+"','"+prénom.getText()+"', '"+d_naissance.getValue()+"', '"+email.getText()+"'" +
                  ", '"+teleph.getText()+"', '"+adr.getText()+"','"+fis+"', '"+getIdEquipe((String) équipe.getSelectionModel().getSelectedItem())+"', '"+getIdEquipe_sport((String) équipe.getSelectionModel().getSelectedItem())+"')";
        st=cnx.prepareStatement(query);


        st.execute();
            AlertMaker.showSimpleAlert("Ajout terminé", "l'entraineur "+name.getText()+" à été ajouté");
      name.setText(" ");
      prénom.setText(" ");
      email.setText(" ");
      d_naissance.getEditor().clear();
      teleph.setText(" ");
      adr.setText(" ");
      logo.setImage(null);
      textArea.setText(" ");
      équipe.getSelectionModel().clearSelection();
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


}
