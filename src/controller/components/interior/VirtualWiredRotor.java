package controller.components.interior;

import java.util.Set;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class VirtualWiredRotor extends VirtualWiredRotorLikeCompoment {

    // Internal members

    private final int intCharOffset = 65;

    // Instantiations

    public VirtualWiredRotor(int width) {
        super(width);
    }

    // Public methods

    public void draw(int position, int offset, Set<Integer> notchs) {
        this.graphicPane.getChildren().clear();

        drawGraphic(displayWiring);

        this.charPane.getChildren().clear();

        fillColumn(this.charPane, alphabet, 2, position);
        fillColumn(this.charPane, alphabet, 0, position + offset);

        for (int i = 0; i < alphabetLength; i++) {
            String displayedCharacter = getNode(i, 2, charPane).getText();
            if (notchs.contains(displayedCharacter.charAt(0) - intCharOffset)) {
                System.out.println(i);
                Rectangle rect = new Rectangle(2, i * rowHeight, width - 4, rowHeight);
                rect.setFill(Color.TRANSPARENT);
                rect.setStroke(Color.web("C6C6C8").darker());
                rect.setStrokeWidth(1);
                this.graphicPane.getChildren().add(rect);
            }
        }

    }

    private Label getNode(int row, int column, GridPane gridPane) {
        Label result = null;

        ObservableList<Node> childrens = gridPane.getChildren();

        for (Node node : childrens) {
            if (GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == column) {
                result = (Label) node;
                break;
            }
        }

        return result;
    }

}