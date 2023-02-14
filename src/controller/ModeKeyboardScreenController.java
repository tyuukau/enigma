package controller;

import controller.components.outerior.VirtualKeyboard;
import controller.components.outerior.VirtualLampboard;
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

    @Override
    protected void initializeOther() {
        inputPane.getChildren().addAll(vk.view());  
        outputPane.getChildren().addAll(vl.view());
    }

    @Override
    protected void resetOther() {
        vl.unsetButton();        
    }

    @Override
    protected void updateOther(String input) {
        vl.setButton(outputChar);        
    }

}