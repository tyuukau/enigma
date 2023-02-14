package controller.components.outerior;

import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableStringValue;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;

import com.sun.javafx.css.StyleManager;

public class VirtualLampboard {

    // Internal members

    private final VBox root;
    private ToggleGroup tg = new ToggleGroup();

    // Instantiations

    // Creates a VirtualLampboard which uses the focusProperty of the scene to which it is attached as its target
    public VirtualLampboard() {
        this(null);
    }

    // Creates a VirtualLampboard
    public VirtualLampboard(ReadOnlyObjectProperty<Node> target) {

        StyleManager.getInstance().addUserAgentStylesheet("../resources/css/application.css");

        this.root = new VBox(2);

        final String[][] shifted = new String[][] {
                { "Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P" },
                { "A", "S", "D", "F", "G", "H", "J", "K", "L" },
                { "Z", "X", "C", "V", "B", "N", "M" } };

        // build layout
        for (int row = 0; row < shifted.length; row++) {
            HBox hbox = new HBox(2);
            hbox.setAlignment(Pos.CENTER);
            root.getChildren().add(hbox);

            for (int k = 0; k < shifted[row].length; k++) {
                hbox.getChildren().add(createFixedButton(shifted[row][k]));
            }
        }

    }

    // Public methods

    // Returns a view of the lampboard
    public Node view() {
        return root;
    }

    public void setButton(String value) {
        for (Toggle t : tg.getToggles()) {
            if (((ToggleButton) t).getText().equals(value)) {
                t.setSelected(true);
            }
        }
    }

    public void unsetButton() {
        tg.selectToggle(null);
    }

    // Internal methods

    // Creates a button with fixed text
    private ToggleButton createFixedButton(final String text) {
        StringProperty textProperty = new SimpleStringProperty(text);
        ToggleButton button = createButton(textProperty);
        return button;
    }

    // Creates a button with mutable text, and registers listener with it
    private ToggleButton createButton(final ObservableStringValue text) {
        final ToggleButton button = new ToggleButton();
        button.textProperty().bind(text);

        // Important not to grab the focus from the target:
        button.setFocusTraversable(false);
        button.setToggleGroup(tg);

        // Add a style class for css:
        button.getStyleClass().add("keyboard");
        button.setPrefSize(28, 36);

        return button;
    }

}