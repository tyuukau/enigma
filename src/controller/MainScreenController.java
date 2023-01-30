package controller;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;

public class MainScreenController extends GeneralController {

    // FXML actions

    @FXML
    public void switchSimple(ActionEvent event) {
        switchScene("../resources/view/SimpleScreen2.fxml", new ModeSimpleScreenController(), event);
    }

    @FXML
    public void switchKeyboard(ActionEvent event) {
        switchScene("../resources/view/KeyboardScreen2.fxml", new ModeKeyboardScreenController(), event);
    }

    @FXML
    public void switchWires(ActionEvent event) {
        switchScene("../resources/view/WiresScreen2.fxml", new ModeWiresScreenController(), event);
    }

    @FXML
    public void switchHack(ActionEvent event) {
        // switchScene("../resources/view/WiresScreenVersion3.fxml", new ModeWiresScreenController(), event);
    }

    @FXML
    public void switchHelp(ActionEvent event) {
        super.switchScene("../resources/view/HelpScreen2.fxml", new HelpScreenController(), event);
    }

}