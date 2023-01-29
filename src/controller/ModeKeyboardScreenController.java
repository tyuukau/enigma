package controller;

import controller.components.VirtualKeyboard;
import controller.components.VirtualLampboard;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;

public class ModeKeyboardScreenController extends ModeDemonstrationController {
    
    // Internal members

    @FXML
    private Pane inputPane;
    
    @FXML
    private Pane outputPane;

    private VirtualKeyboard vk = new VirtualKeyboard();
    private VirtualLampboard vl = new VirtualLampboard();

    // Mandatory methods

    public void initialize() {
        initializeEnigma();

        initializeGUI();

        inputPane.getChildren().addAll(vk.view());  
        outputPane.getChildren().addAll(vl.view());
    }

    public void save(ActionEvent event) {
        saveEnigma();
        
        initializeGUI();

        vl.unsetButton();
    }

    public void reset(ActionEvent event) {
        initializeEnigma();
        
        initializeGUI();
        
        vl.unsetButton();
    }

    public void update(String input) {
        updateGUI(input);

        vl.setButton(outputChar);
    }

}
