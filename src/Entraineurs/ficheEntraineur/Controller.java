package Entraineurs.ficheEntraineur;
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
    private TextField sportCol;

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
        Entraineur selected = Entraineurs.listeEntraineur.Controller.table.getSelectionModel().getSelectedItem();
        int id=selected.getId();
        try {
            ResultSet rs = cnx.createStatement().executeQuery("SELECT * FROM `entraineur`  WHERE `idEntraineur` =" + id + " ");

            while (rs.next()) {
                name.setText(rs.getString(2));
                prénom.setText(rs.getString(3));
                d_naissance.setText(rs.getString(4));
                email.setText(rs.getString(5));
                teleph.setText(rs.getString(6));
                adr.setText(rs.getString(7));
                sportCol.setText(getNomsportCol(rs.getInt(10)));
                équipe.setText(getNoméquipe(rs.getInt(9)));


                 blob = rs.getBlob(8);
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
        Entraineur selected = Entraineurs.listeEntraineur.Controller.table.getSelectionModel().getSelectedItem();
        try{
            String query ="UPDATE `bdd`.`entraineur` SET `Nom` = '"+name.getText()+"', `Prénom` = '"+prénom.getText()+"', " +
                    "`DateNaissance` = '"+d_naissance.getText()+"', `Email` = '"+email.getText()+"', `Telephone` = '"+teleph.getText()+"', `adresse` = '"+adr.getText()+"', " +
                    "`équipe_idéquipe` = '"+getIdEquipe(équipe.getText())+"', `équipe_sport_collectif_idsport_collectif` = '"+getIdEquipe_sport(sportCol.getText())+"' WHERE `entraineur`.`idEntraineur` = "+selected.getId()+" ;";
            st=cnx.prepareStatement(query);

            st.execute();
            AlertMaker.showSimpleAlert("modification terminé", "l'entraineur "+name.getText()+" à été modifié");
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
/*
    @FXML
    private Button modifier;
@FXML
public void modifier(ActionEvent event) throws IOException{
      try{
          String query="UPDATE `entraineur` SET `Nom`=['"+name.getText()+"'],`Prénom`=['"+prénom.getText()+"'],`DateNaissance`=['"+d_naissance.getText()+"'],`Email`=['"+email.getText()+"']," +
                  "`Telephone`=['"+teleph.getText()+"'],`adresse`=['"+adr.getText()+"'],`équipe_idéquipe`=['"+getIdEquipe(équipe.getText())+"'],`équipe_sport_collectif_idsport_collectif`=['"+getIdEquipe_sport(sportCol.getText())+"'] WHERE 1";
          st=cnx.prepareStatement(query);


          st.execute();
          AlertMaker.showSimpleAlert("Modification terminé", "l'entraineur "+name.getText()+" à été modifié");


      }catch (SQLException e){
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
    }*/
    @FXML
   public void retour(ActionEvent event) throws IOException {
        try {
            URL url = Paths.get("./fxml/h9.fxml").toUri().toURL();
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
          URL url = Paths.get("./fxml/h9.fxml").toUri().toURL();
          FXMLLoader loader = new FXMLLoader(url);
          Stage stage = (Stage) btnretour.getScene().getWindow();
          Scene scene = new Scene((Parent) loader.load());
          stage.setScene(scene);
      }catch (IOException io){
          io.printStackTrace();
      }
  }

    }