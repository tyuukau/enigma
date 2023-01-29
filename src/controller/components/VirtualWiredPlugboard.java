package controller.components;

import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
// import javafx.scene.shape.Line;

import com.sun.javafx.css.StyleManager;

public class VirtualWiredPlugboard extends VirtualWiredComponent {

    private final int width = 130;
    private final int height = 520;

    // private int[] displayWiring;
    
    public VirtualWiredPlugboard() {

        StyleManager.getInstance().addUserAgentStylesheet("../resources/css/application.css");

        this.root = new StackPane();

        this.root.setMinSize(width, height);
        this.root.setPrefSize(width, height);
        this.root.setMaxSize(width, height);

        this.graphicPane = new Pane();

        this.graphicPane.setMinSize(width, height);
        this.graphicPane.setMaxSize(width, height);

        this.charPane = new GridPane();

        this.charPane.setMinWidth(width);
        this.charPane.setMaxWidth(width);

        for (int i = 0; i < alphabetLength; i++) {
            RowConstraints rowConst = new RowConstraints();
            rowConst.setPercentHeight(100.0 / alphabetLength);
            this.charPane.getRowConstraints().add(rowConst);         
        }

        ColumnConstraints colConst0 = new ColumnConstraints(20, 20, 20);
        this.charPane.getColumnConstraints().add(colConst0);

        ColumnConstraints colConst1 = new ColumnConstraints(90, 90, 90);
        this.charPane.getColumnConstraints().add(colConst1);

        ColumnConstraints colConst2 = new ColumnConstraints(20, 20, 20);
        this.charPane.getColumnConstraints().add(colConst2);

        fillColumn(this.charPane, alphabet, 0);
        fillColumn(this.charPane, alphabet, 2);

        this.root.getChildren().addAll(
            this.graphicPane, 
            this.charPane);

    }

    public void draw() {
        this.graphicPane.getChildren().clear();
        // drawGraphic(displayWiring);
    }

    // public void drawGraphic(int[] wiring) {
    //     for (int i = 0; i < wiring.length; i++) {
    //         int startX = 110;
    //         int startY = 10 + 20 * i;
    //         int endX = 20;
    //         int endY = 10 + 20 * wiring[i];

    //         Line line = new Line(startX, startY, endX, endY);
    //         this.graphicPane.getChildren().add(line);
    //     }
    // }

}
