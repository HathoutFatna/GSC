package Login;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import Entraineurs.listeEntraineur.Entraineur;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javafx.stage.StageStyle;
import org.apache.commons.codec.digest.DigestUtils;
import sun.applet.Main;


public class Controller implements Initializable {


    @FXML
    private TextField Nom;
    @FXML
    private PasswordField password;
@FXML
        private Button login;
    Preferences preference;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        preference = Preferences.getPreferences();
    }


    @FXML
    private void handleLoginButtonAction(ActionEvent event) {
        String uname = Nom.getText();
        String pword = DigestUtils.shaHex(password.getText());

        if (uname.equals(preference.getUsername()) && pword.equals(preference.getPassword())) {
            try {
                URL url = Paths.get("./Fxml/_Home.fxml").toUri().toURL();
                FXMLLoader loader = new FXMLLoader(url);
                Stage stage = (Stage) login.getScene().getWindow();
                Scene scene = new Scene(loader.load());
                stage.setScene(scene);
            }catch (IOException io){
                io.printStackTrace();}


        } else {
            Nom.getStyleClass().add("wrong-credentials");
            password.getStyleClass().add("wrong-credentials");
        }
    }

    }


