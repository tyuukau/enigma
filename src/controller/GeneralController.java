package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public abstract class GeneralController {

    protected Scene scene;
    protected Stage stage;
    protected Parent root;
    protected FXMLLoader loader;

    // FXML objects

    @FXML
    protected AnchorPane scenePane;

    @FXML
    protected Button quitButton;

    // FXML actions

    @FXML
    public void back(ActionEvent event) {
        switchScene("../resources/view/MainScreen.fxml", new MainScreenController(), event);
    }

    @FXML
    public void quit(ActionEvent event) {
        stage = (Stage) scenePane.getScene().getWindow();
        stage.close();
    }

    // Scene-switching method

    public void switchScene(String xmlDir, GeneralController controller, ActionEvent e) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(xmlDir));
            loader.setController(controller);
            root = loader.load();

            stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Enigma");
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}