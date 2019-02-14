package Home;


        import alert.AlertMaker;
        import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
        import javafx.fxml.FXMLLoader;
        import javafx.scene.Parent;
        import javafx.scene.Scene;
        import javafx.scene.control.Button;
        import javafx.stage.Stage;

        import java.io.IOException;
        import java.net.URL;
        import java.nio.file.Paths;

public class Controller {


    @FXML
    private Button btnGestionEtud;

    @FXML
    private Button btnGestionActivSport;

    @FXML
    private Button btnGestionActivCultur;

    @FXML
    private Button btnMatériaux;
    @FXML
    private Button logout;
    @FXML
    private Button btnrecherche;
    @FXML
    public void btnrecherche(ActionEvent event){
        try {
            URL url = Paths.get("./Fxml/recherche.fxml").toUri().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Stage stage = (Stage) btnrecherche.getScene().getWindow();
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
        }catch (IOException io){
            io.printStackTrace();
        }

    }
    @FXML
   public void logout(ActionEvent event) {
        AlertMaker.showSimpleAlert("Déconnexion", "Vous êtes déconnecter");
        try {
            URL url = Paths.get("./fxml/_Login.fxml").toUri().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Stage stage = (Stage) this.btnGestionActivSport.getScene().getWindow();
            Scene scene = new Scene((Parent) loader.load());
            stage.setScene(scene);
        }catch (IOException io){
            io.printStackTrace();
        }


    }

    @FXML
    void goGActicvSport(ActionEvent event) {

        try {
                URL url = Paths.get("./fxml/h4.fxml").toUri().toURL();
                FXMLLoader loader = new FXMLLoader(url);
                Stage stage = (Stage) this.btnGestionActivSport.getScene().getWindow();
                Scene scene = new Scene((Parent) loader.load());
                stage.setScene(scene);
            }catch (IOException io){
                io.printStackTrace();
            }


    }

    @FXML
    void goGActivCultur(ActionEvent event) {
        try {
            URL url = Paths.get("./fxml/h16.fxml").toUri().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Stage stage = (Stage) this.btnGestionActivCultur.getScene().getWindow();
            Scene scene = new Scene((Parent) loader.load());
            stage.setScene(scene);
        }catch (IOException io){
            io.printStackTrace();
        }

    }

    @FXML
    void goGEtudiant(ActionEvent event) {
        try{
        URL url = Paths.get("./fxml/h23.fxml").toUri().toURL();
        FXMLLoader loader = new FXMLLoader(url);
        Stage stage = (Stage) this.btnGestionEtud.getScene().getWindow();
        Scene scene = new Scene((Parent) loader.load());
        stage.setScene(scene);
    }catch (IOException io){
        io.printStackTrace();
    }


}

    @FXML
    void goGMatériaux(ActionEvent event) {
        try{
            URL url = Paths.get("./fxml/h22.fxml").toUri().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Stage stage = (Stage) this.btnMatériaux.getScene().getWindow();
            Scene scene = new Scene((Parent) loader.load());
            stage.setScene(scene);
        }catch (IOException io){
            io.printStackTrace();
        }

    }

}
