package GestionSportCol.sportCol_sportInd;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;

public class Controller {
    @FXML
    private Button go;


    @FXML
    private Button retour;

    @FXML
    private Button listeEtud;
    @FXML
    private Button listeEntraineur;

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
    private Button listeSportCol;
    @FXML
    public void listeSportCol(ActionEvent event) throws IOException {
        try {
            URL url = Paths.get("./Fxml/h11.fxml").toUri().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Stage stage = (Stage) listeSportCol.getScene().getWindow();
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
        }catch (IOException io){
            io.printStackTrace();
        }
    }
    @FXML
    private Button listeSportInd;
    @FXML
    public void listeSportInd(ActionEvent event) throws IOException {
        try {
            URL url = Paths.get("./Fxml/h10.fxml").toUri().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Stage stage = (Stage) listeSportInd.getScene().getWindow();
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
        }catch (IOException io){
            io.printStackTrace();
        }
    }

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


}
