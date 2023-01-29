package controller;

import controller.components.VirtualWiredInOutIndicator;
import controller.components.VirtualWiredPlugboard;
import controller.components.VirtualWiredReflector;
import controller.components.VirtualWiredRotor;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ModeWiresScreenController extends ModeDemonstrationController {

    // Internal members

    @FXML
    private VBox outputPane;

    @FXML
    private HBox grid;

    private VirtualWiredReflector vwrf = new VirtualWiredReflector();
    private VirtualWiredRotor vwrt0 = new VirtualWiredRotor();
    private VirtualWiredRotor vwrt1 = new VirtualWiredRotor();
    private VirtualWiredRotor vwrt2 = new VirtualWiredRotor();
    private VirtualWiredPlugboard vwpb = new VirtualWiredPlugboard();
    private VirtualWiredInOutIndicator vwio = new VirtualWiredInOutIndicator();

    // Mandatory methods

    public void initialize() {
        initializeEnigma();

        initializeGUI();

        resetIndicator();
        fillAllColumns();
    }

    public void save(ActionEvent event) {
        saveEnigma();

        initializeGUI();

        resetIndicator();
        fillAllColumns();
    }

    public void reset(ActionEvent event) {
        initializeEnigma();
        
        initializeGUI();

        resetIndicator();
        fillAllColumns();
    }

    public void update(String input) {
        updateGUI(input);

        fillIndicator(input, outputChar);
        fillAllColumns();
    }
    
    private void fillAllColumns() {
        grid.getChildren().clear();

        int[] startPositions = enigma.getRotorPostions();
        int[] ringOffsets = enigma.getRotorOffsets();

        vwrf.draw();
        vwrt0.draw(startPositions[0], ringOffsets[0]);
        vwrt1.draw(startPositions[1], ringOffsets[1]);
        vwrt2.draw(startPositions[2], ringOffsets[2]);
        vwpb.draw();

        grid.getChildren().addAll(
            vwrf.view(), 
            vwrt0.view(),
            vwrt1.view(),
            vwrt2.view(),
            vwpb.view(),
            vwio.view()
            );
    }

    private void fillIndicator(String input, String output) {
        vwio.draw(input, output);
    }

    private void resetIndicator() {
        vwio.reset();
    }

}
