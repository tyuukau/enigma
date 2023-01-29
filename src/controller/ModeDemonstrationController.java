package controller;

import javafx.fxml.FXML;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.Arrays;
import java.util.List;

import model.Enigma;

public abstract class ModeDemonstrationController extends GeneralController {

    // Common GUI members

    @FXML
    protected ComboBox<String> reflector_type;

    @FXML
    protected ComboBox<String> left_rotor_type;

    @FXML
    protected ComboBox<String> left_rotor_start;

    @FXML
    protected ComboBox<String> left_rotor_ring;

    @FXML
    protected ComboBox<String> middle_rotor_type;

    @FXML
    protected ComboBox<String> middle_rotor_start;

    @FXML
    protected ComboBox<String> middle_rotor_ring;

    @FXML
    protected ComboBox<String> right_rotor_type;

    @FXML
    protected ComboBox<String> right_rotor_start;

    @FXML
    protected ComboBox<String> right_rotor_ring;

    @FXML
    protected TextField plugboard;

    @FXML
    protected TextArea inputField;

    @FXML
    protected TextArea outputField;

    // Common members

    protected String output = "";

    protected Enigma enigma;

    protected String outputChar;

    // Common helper methods

    public void initializeEnigma() {

        // Set values for the menus

        reflector_type.getItems().setAll("A", "B", "C");

        List<ComboBox<String>> rotorsType = Arrays.asList(left_rotor_type, middle_rotor_type, right_rotor_type);
        for (ComboBox<String> rotorType : rotorsType) {
            rotorType.getItems().setAll("I", "II", "III", "IV", "V", "VI", "VII", "VIII");
        }

        List<ComboBox<String>> rotorsPosition = Arrays.asList(left_rotor_start, middle_rotor_start, right_rotor_start,
                left_rotor_ring, middle_rotor_ring, right_rotor_ring);
        for (ComboBox<String> rotorPosition : rotorsPosition) {
            rotorPosition.getItems().setAll("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K",
                                            "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U",
                                            "V", "W", "X", "Y", "Z");
        }

        // Set default values for the menus

        List<ComboBox<String>> allBoxes = Arrays.asList(left_rotor_type, middle_rotor_type, right_rotor_type,
                left_rotor_start, middle_rotor_start, right_rotor_start,
                left_rotor_ring, middle_rotor_ring, right_rotor_ring,
                reflector_type);
        int[] defaultIndexes = new int[] { 0, 2, 4, 12, 14, 16, 20, 22, 24, 1 };
        for (ComboBox<String> comboBox : allBoxes) {
            comboBox.getSelectionModel().select(defaultIndexes[allBoxes.indexOf(comboBox)]);
        }
        plugboard.setText("AB CD FE GH IJ KL");

        // Initialise the Enigma machine

        enigma = new Enigma(new String[] { "I", "III", "V" }, "B", new int[] { 12, 14, 16 }, new int[] { 20, 22, 24 },
                plugboard.getText());
        System.out.println(enigma.getInfo());
    }

    public void saveEnigma() {

        // Get values for the Enigma machine

        String a = left_rotor_type.getValue();
        String b = middle_rotor_type.getValue();
        String c = right_rotor_type.getValue();

        int d = left_rotor_start.getSelectionModel().getSelectedIndex();
        int e = middle_rotor_start.getSelectionModel().getSelectedIndex();
        int f = right_rotor_start.getSelectionModel().getSelectedIndex();

        int g = left_rotor_ring.getSelectionModel().getSelectedIndex();
        int h = middle_rotor_ring.getSelectionModel().getSelectedIndex();
        int i = right_rotor_ring.getSelectionModel().getSelectedIndex();

        String r = reflector_type.getValue();
        String p = plugboard.getText();

        // Create new Enigma machine

        enigma = new Enigma(new String[] { a, b, c }, 
                            r, 
                            new int[] { d, e, f }, 
                            new int[] { g, h, i }, 
                            p);
        System.out.println(enigma.getInfo());
    }

    public void initializeGUI() {

        // Blank input, output fields

        inputField.clear();
        outputField.clear();

        inputField.setTextFormatter(new TextFormatter<String>(change -> {
            String input = change.getText();
            if (input.matches("[A-Z]")) {
                update(input);
                return change;
            }
            if (change.isDeleted()) {
                return change;
            }
            return null;
        }));

        inputField.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.BACK_SPACE || event.getCode() == KeyCode.DELETE) {
                    event.consume(); // to cancel character-removing keys
                }
            }
        });

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                inputField.requestFocus();
            }
        });
    }

    public void updateGUI(String input) {
        outputChar = enigma.encrypt(input);
        outputField.appendText(outputChar);
        int[] positions = enigma.getRotorPostions();
        left_rotor_start.getSelectionModel().select(positions[0]);
        middle_rotor_start.getSelectionModel().select(positions[1]);
        right_rotor_start.getSelectionModel().select(positions[2]);
    }

    // Abstract implementations

    protected abstract void initialize();

    protected abstract void save(ActionEvent event);

    protected abstract void reset(ActionEvent event);

    protected abstract void update(String input);

}