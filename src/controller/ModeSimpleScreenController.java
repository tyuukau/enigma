package controller;

import javafx.event.ActionEvent;

public class ModeSimpleScreenController extends ModeDemonstrationController {

    // Mandatory methods
    
    public void initialize() {
        initializeEnigma();

        initializeGUI();    
    }

    public void save(ActionEvent event) {
        saveEnigma();

        initializeGUI();
    }

    public void reset(ActionEvent event) {
        initializeEnigma();
        
        initializeGUI();
    }

    public void update(String input) {
        updateGUI(input);
    }

}
