

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.net.URL;
import java.nio.file.Paths;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

       URL url = Paths.get("./Fxml/_login.fxml").toUri().toURL();
        Parent root = FXMLLoader.load(url);
        primaryStage.setTitle("GSC");
        primaryStage.setScene(new Scene(root, 775, 530));
        primaryStage.getIcons().add(new Image("/img/logo.png"));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
