package controller.components.interior;

import javafx.geometry.HPos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;

import com.sun.javafx.css.StyleManager;

public abstract class VirtualInternalComponent {

    // Common members

    protected final int height = 468;
    protected int width;

    protected StackPane root;
    protected Pane graphicPane;
    protected GridPane charPane;
    
    protected final int alphabetLength = 26;

    protected Font font = new Font(11);

    // Instantiations

    public VirtualInternalComponent(int width) {

        StyleManager.getInstance().addUserAgentStylesheet("../resources/css/application.css");

        this.root = new StackPane();

        this.root.setMinSize(width, height);
        this.root.setPrefSize(width, height);
        this.root.setMaxSize(width, height);

        this.charPane = new GridPane();

        this.charPane.setMinWidth(width);
        this.charPane.setMaxWidth(width);

        this.width = width;

    }

    // Returns a view of the class
    public Node view() {
        return this.root;
    }

    // Common helper methods

    protected void fill(GridPane gridPane, String labelString, int column, int row) {
        Label label = new Label(labelString);
        label.setFont(font);
        GridPane.setHalignment(label, HPos.CENTER);
        gridPane.add(label, column, row);

    }
    
}