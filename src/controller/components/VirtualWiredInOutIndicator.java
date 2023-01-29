package controller.components;

import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;

import com.sun.javafx.css.StyleManager;

public class VirtualWiredInOutIndicator extends VirtualWiredComponent {

    private final int width = 30;
    private final int height = 520;
    private final int intCharOffset = 65;
    
    public VirtualWiredInOutIndicator() {

        StyleManager.getInstance().addUserAgentStylesheet("../resources/css/application.css");

        this.root = new StackPane();

        this.root.setMinSize(width, height);
        this.root.setPrefSize(width, height);
        this.root.setMaxSize(width, height);

        this.charPane = new GridPane();

        this.charPane.setMinWidth(width);
        this.charPane.setMaxWidth(width);

        for (int i = 0; i < alphabetLength; i++) {
            RowConstraints rowConst = new RowConstraints();
            rowConst.setPercentHeight(100.0 / alphabetLength);
            this.charPane.getRowConstraints().add(rowConst);         
        }

        ColumnConstraints colConst0 = new ColumnConstraints(width, width, width);
        this.charPane.getColumnConstraints().add(colConst0);

        this.root.getChildren().addAll(
            this.charPane);

    }

    public void draw(String input, String output) {
        this.charPane.getChildren().clear();

        int i = input.charAt(0) - intCharOffset;
        int o = output.charAt(0) - intCharOffset;

        fill(charPane, "<<", 0, i);
        fill(charPane, ">>", 0, o);

    }

    public void reset() {
        this.charPane.getChildren().clear();
    }

}
