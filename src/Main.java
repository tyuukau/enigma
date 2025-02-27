import controller.ModeDetailedController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Parent;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        System.setProperty("prism.lcdtext", "false");
        final String STORE_FXML_FILE_PATH = "resources/view/WiresScreen3.fxml";
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(STORE_FXML_FILE_PATH));
        ModeDetailedController homeController = new ModeDetailedController();
        fxmlLoader.setController(homeController);
        Parent root = fxmlLoader.load();

        stage.setTitle("Enigma");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
    }

}
