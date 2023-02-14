package controller;

import controller.components.interior.VirtualInOutIndicator;
import controller.components.interior.VirtualWiredPlugboard;
import controller.components.interior.VirtualWiredReflector;
import controller.components.interior.VirtualWiredRotor;
import controller.components.outerior.VirtualKeyboard;
import controller.components.outerior.VirtualLampboard;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import model.Plugboard;
import model.Reflector;
import model.Rotor;

public class ModeDetailedController extends ModeDemonstrationController {

    // Internal members

    @FXML
    protected TextArea plugboard;

    @FXML
    private Pane keyboardPane;
    
    @FXML
    private Pane lampboardPane;

    private VirtualKeyboard vk = new VirtualKeyboard();
    private VirtualLampboard vl = new VirtualLampboard();

    @FXML
    private VBox outputPane;

    @FXML
    private HBox grid;

    private VirtualWiredReflector vwrf = new VirtualWiredReflector(80);
    private VirtualWiredRotor vwrt0 = new VirtualWiredRotor(160);
    private VirtualWiredRotor vwrt1 = new VirtualWiredRotor(160);
    private VirtualWiredRotor vwrt2 = new VirtualWiredRotor(160);
    private VirtualWiredPlugboard vwpb = new VirtualWiredPlugboard(110);
    private VirtualInOutIndicator vwio = new VirtualInOutIndicator(18);

    // Mandatory methods

    protected void initializeOther() {
        keyboardPane.getChildren().addAll(vk.view());  
        lampboardPane.getChildren().addAll(vl.view());

        resetIndicator();
        resetHighlight();
        drawAll();
    }

    protected void resetOther() {
        vl.unsetButton();   

        resetIndicator();
        resetHighlight();
        drawAll();
    }

    protected void updateOther(String input) {
        vl.setButton(outputChar);

        setIndicator(input, outputChar);
        setHighlight();
        drawAll();
    }

    // Helper methods
    
    private void setIndicator(String input, String output) {
        vwio.draw(input, output);
    }

    private void resetIndicator() {
        vwio.reset();
    }

    private void setHighlight() {
        int[] encryptProcess = enigma.getEncryptProcess();

        int[] vwpbHighlight = new int[] {encryptProcess[0], encryptProcess[1], encryptProcess[9], encryptProcess[8]};
        vwpb.highlight(vwpbHighlight);

        int[] vwrt0Highlight = new int[] {encryptProcess[3], encryptProcess[4], encryptProcess[6], encryptProcess[5]};
        vwrt0.highlight(vwrt0Highlight);

        int[] vwrt1Highlight = new int[] {encryptProcess[2], encryptProcess[3], encryptProcess[7], encryptProcess[6]};
        vwrt1.highlight(vwrt1Highlight);

        int[] vwrt2Highlight = new int[] {encryptProcess[1], encryptProcess[2], encryptProcess[8], encryptProcess[7]};
        vwrt2.highlight(vwrt2Highlight);

        int[] vwrfHighlight = new int[] {encryptProcess[4], encryptProcess[5]};     
        vwrf.highlight(vwrfHighlight);   
    }

    private void resetHighlight() {
        vwpb.dehighlight();
        vwrt0.dehighlight();
        vwrt1.dehighlight();
        vwrt2.dehighlight();
        vwrf.dehighlight();
    }

    private void drawAll() {
        grid.getChildren().clear();

        int[] startPositions = enigma.getRotorPostions();
        int[] ringOffsets = enigma.getRotorOffsets();

        Plugboard plugboard = enigma.getPlugboard();
        for (int i = 0; i < vwpb.displayWiring.length; i++) {
            vwpb.displayWiring[i] = plugboard.forward(i);
        }
        vwpb.draw();

        Rotor leftRotor = enigma.getLeftRotor();
        for (int i = 0; i < vwrt0.displayWiring.length; i++) {
            vwrt0.displayWiring[i] = leftRotor.forward(i);
        }
        vwrt0.draw(startPositions[0], ringOffsets[0]);

        Rotor middleRotor = enigma.getMiddleRotor();
        for (int i = 0; i < vwrt1.displayWiring.length; i++) {
            vwrt1.displayWiring[i] = middleRotor.forward(i);
        }
        vwrt1.draw(startPositions[1], ringOffsets[1]);

        Rotor rightRotor = enigma.getRightRotor();
        for (int i = 0; i < vwrt2.displayWiring.length; i++) {
            vwrt2.displayWiring[i] = rightRotor.forward(i);
        }
        vwrt2.draw(startPositions[2], ringOffsets[2]);

        Reflector reflector = enigma.getReflector();
        for (int i = 0; i < vwrf.displayWiring.length; i++) {
            vwrf.displayWiring[i] = reflector.forward(i);
        }
        vwrf.draw();

        grid.getChildren().addAll(
            vwrf.view(), 
            vwrt0.view(),
            vwrt1.view(),
            vwrt2.view(),
            vwpb.view(),
            vwio.view()
            );
    }

}