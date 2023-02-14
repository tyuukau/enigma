import controller.DemonstrationController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Parent;

public class GUIDriver extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        System.setProperty("prism.lcdtext", "false");
        final String STORE_FXML_FILE_PATH = "resources/view/demonstrationScreen.fxml";
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(STORE_FXML_FILE_PATH));
        DemonstrationController homeController = new DemonstrationController();
        fxmlLoader.setController(homeController);
        Parent root = fxmlLoader.load();

        stage.setTitle("Disk Scheduling Application");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
    }

}
