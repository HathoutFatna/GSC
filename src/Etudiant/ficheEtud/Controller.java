package etudiant.ficheEtud;
import Entraineurs.listeEntraineur.Entraineur;
import etudiant.afficherEtud.Etudiant;
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


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
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
    private TextField activité_culturelle;

    @FXML
    private TextField sportCol;

    @FXML
    private TextField sportInd;
    @FXML
    private TextField d_naissance;
   public byte byteImage[];
    public Blob blob;


    @FXML
    private TextField équipe;

    @FXML
    private ImageView logo;

    @FXML
    private Button btnajouter;

    @FXML
    private Button btnretour;


    @Override
    public void initialize(URL url,ResourceBundle rb){
        Etudiant selected = etudiant.afficherEtud.Controller.tableView.getSelectionModel().getSelectedItem();
        int id=selected.getId();
        try {
            ResultSet rs = cnx.createStatement().executeQuery("SELECT * FROM `etudiant`  WHERE `idETUDIANT` =" + id + " ");

            while (rs.next()) {
                name.setText(rs.getString(2));
                prénom.setText(rs.getString(3));
                d_naissance.setText(rs.getString(4));
                email.setText(rs.getString(5));
                teleph.setText(rs.getString(6));
                adr.setText(rs.getString(11));
                taille.setText(rs.getString(8));
                poid.setText(rs.getString(7));
                groupage.setText(rs.getString(9));
                sportCol.setText(getNomsportCol(rs.getInt(15)));
                sportInd.setText(getNomsportInd(rs.getInt(12)));
                activité_culturelle.setText(getNomactivité(rs.getInt(13)));
                équipe.setText(getNoméquipe(rs.getInt(14)));


                 blob = rs.getBlob(10);
                byteImage = blob.getBytes(1,(int)blob.length());
                Image img = new Image(new ByteArrayInputStream(byteImage));
                logo.setImage(img);

            }
        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }
    }
    @FXML
    public void modifier(ActionEvent event){
        Etudiant selected = etudiant.afficherEtud.Controller.tableView.getSelectionModel().getSelectedItem();
      try{

          String query ="UPDATE `bdd`.`etudiant` SET `Nom` = '"+name.getText()+"', `Prénom` = '"+prénom.getText()+"', `DDN` = '"+d_naissance.getText()+"', `Email` = '"+email.getText()+"', " +
                  "`Telephone` = '"+teleph.getText()+"', `poid` = '"+poid.getText()+"', `taille` = '"+taille.getText()+"', `Groupage` = '"+groupage.getText()+"', `Adresse` = '"+adr.getText()+"'" +
                  ", `Sport_Idividuel_idSport_Idividuel` = '"+getIdsport_ind(sportInd.getText())+"', `Activité_Culturelle_idActivité_Culturelle` = '"+getIdculture(activité_culturelle.getText())+"', `équipe_idéquipe` = '"+getIdEquipe(équipe.getText())+"'," +
                  " `équipe_sport_collectif_idsport_collectif` = '"+getIdEquipe_sport(sportCol.getText())+"' WHERE `etudiant`.`idETUDIANT` = "+selected.getId()+";";
        st=cnx.prepareStatement(query);

        st.execute();
        AlertMaker.showSimpleAlert("modification terminé", "l'etudiant(e) "+name.getText()+" à été modifié(e)");
          st.close();
      }catch (SQLException e) {
          System.out.println(e.getMessage());

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



    public String getNomactivité(int id){
        String activité ="";


        try{   ResultSet rst = cnx.createStatement().executeQuery("SELECT * FROM `activité_culturelle` WHERE `idActivité_Culturelle` = "+id+"");
            while (rst.next()){
                activité= rst.getString(2);}}
        catch (SQLException ex) {
            System.err.println("Error" + ex);
        }

        return activité;
    }
    public String getNomsportCol(int id){
        String sport ="";


        try{   ResultSet rst = cnx.createStatement().executeQuery("SELECT * FROM `sport_collectif` WHERE `idsport_collectif` = "+id+"");
            while (rst.next()){
                sport= rst.getString(2);}}
        catch (SQLException ex) {
            System.err.println("Error" + ex);
        }

        return sport;
    }
    public String getNomsportInd(int id){
        String sport ="";


        try{   ResultSet rst = cnx.createStatement().executeQuery("SELECT * FROM `sport_idividuel` WHERE `idsport_idividuel` = "+id+"");
            while (rst.next()){
                sport= rst.getString(2);}}
        catch (SQLException ex) {
            System.err.println("Error" + ex);
        }

        return sport;
    }
    public String getNoméquipe(int id){
        String sport ="";


        try{   ResultSet rst = cnx.createStatement().executeQuery("SELECT * FROM `équipe` WHERE `idéquipe` = "+id+"");
            while (rst.next()){
                sport= rst.getString(2);}}
        catch (SQLException ex) {
            System.err.println("Error" + ex);
        }

        return sport;
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
  public void valider(ActionEvent event) throws IOException{
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

    }