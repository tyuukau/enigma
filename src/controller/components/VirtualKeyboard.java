package controller.components;

import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableStringValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Priority;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventTarget;
import javafx.event.EventType;

import com.sun.javafx.css.StyleManager;

public class VirtualKeyboard {

    private final VBox root;

    /**
     * Creates a Virtual Keyboard.
     */
    public VirtualKeyboard(ReadOnlyObjectProperty<Node> target) {

        StyleManager.getInstance().addUserAgentStylesheet("../resources/css/application.css");

        this.root = new VBox(10);
        root.setPadding(new Insets(10));

        final String[][] shifted = new String[][] {
                { "Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P" },
                { "A", "S", "D", "F", "G", "H", "J", "K", "L" },
                { "Z", "X", "C", "V", "B", "N", "M" } };

        final KeyCode[][] codes = new KeyCode[][] {
                { KeyCode.Q, KeyCode.W, KeyCode.E, KeyCode.R, KeyCode.T, KeyCode.Y,
                        KeyCode.U, KeyCode.I, KeyCode.O, KeyCode.P },
                { KeyCode.A, KeyCode.S, KeyCode.D, KeyCode.F, KeyCode.G, KeyCode.H,
                        KeyCode.J, KeyCode.K, KeyCode.L },
                { KeyCode.Z, KeyCode.X, KeyCode.C, KeyCode.V, KeyCode.B, KeyCode.N,
                        KeyCode.M } };

        // build layout
        for (int row = 0; row < shifted.length; row++) {
            HBox hbox = new HBox(10);
            hbox.setAlignment(Pos.CENTER);
            root.getChildren().add(hbox);

            for (int k = 0; k < shifted[row].length; k++) {
                hbox.getChildren().add(createNonshiftableButton(shifted[row][k], codes[row][k], target));
            }
        }

        final Button spaceBar = createNonshiftableButton(" ", KeyCode.SPACE, target);
        spaceBar.setMaxWidth(Double.POSITIVE_INFINITY);
        HBox.setHgrow(spaceBar, Priority.ALWAYS);

    }

    /**
     * Creates a VirtualKeyboard which uses the focusProperty of the scene to which
     * it is attached as its target
     */
    public VirtualKeyboard() {
        this(null);
    }

    /**
     * @return a view of the keyboard.
     */
    public Node view() {
        return root;
    }

    // Creates a button with fixed text not responding to Shift
    private Button createNonshiftableButton(final String text, final KeyCode code,
            final ReadOnlyObjectProperty<Node> target) {
        StringProperty textProperty = new SimpleStringProperty(text);
        Button button = createButton(textProperty, code, target);
        return button;
    }

    // Creates a button with mutable text, and registers listener with it
    private Button createButton(final ObservableStringValue text, final KeyCode code, final ReadOnlyObjectProperty<Node> target) {
        final Button button = new Button();
        button.textProperty().bind(text);

        // Important not to grab the focus from the target:
        button.setFocusTraversable(false);

        // Add a style class for css:
        button.getStyleClass().add("keyboard");
        button.setPrefHeight(60);
        button.setPrefWidth(60);

        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                final Node targetNode;
                if (target != null) {
                    targetNode = target.get();
                } else {
                    targetNode = view().getScene().getFocusOwner();
                }

                if (targetNode != null) {
                    final String character;
                    if (text.get().length() == 1) {
                        character = text.get();
                    } else {
                        character = KeyEvent.CHAR_UNDEFINED;
                    }
                    final KeyEvent keyPressEvent = createKeyEvent(button, targetNode, KeyEvent.KEY_PRESSED, character, code);
                    targetNode.fireEvent(keyPressEvent);
                    final KeyEvent keyReleasedEvent = createKeyEvent(button, targetNode, KeyEvent.KEY_RELEASED, character, code);
                    targetNode.fireEvent(keyReleasedEvent);
                    if (character != KeyEvent.CHAR_UNDEFINED) {
                        final KeyEvent keyTypedEvent = createKeyEvent(button, targetNode, KeyEvent.KEY_TYPED, character, code);
                        targetNode.fireEvent(keyTypedEvent);
                    }
                }
            }
        });
        return button;
    }

    // Utility method to create a KeyEvent
    private KeyEvent createKeyEvent(Object source, EventTarget target,
            EventType<KeyEvent> eventType, String character, KeyCode code) {
        return new KeyEvent(source, target, eventType, character, code.toString(),
                code, false, false, false, false);
    }

}