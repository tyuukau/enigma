package controller.components.interior;

import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

import java.util.stream.IntStream;

public class VirtualWiredReflector extends VirtualWiredComponent {
    
    // Instantiations

    public VirtualWiredReflector(int width) {

        super(width);

        blankColumnWidth = width - charColumnWidth;

        for (int i = 0; i < alphabetLength; i++) {
            RowConstraints rowConst = new RowConstraints();
            rowConst.setPercentHeight(100.0 / alphabetLength);
            this.charPane.getRowConstraints().add(rowConst);         
        }

        ColumnConstraints colConst0 = new ColumnConstraints(blankColumnWidth, blankColumnWidth, blankColumnWidth);
        this.charPane.getColumnConstraints().add(colConst0);

        ColumnConstraints colConst1 = new ColumnConstraints(charColumnWidth, charColumnWidth, charColumnWidth);
        this.charPane.getColumnConstraints().add(colConst1);

        fillColumn(this.charPane, alphabet, 1);

        this.root.getChildren().addAll(
            this.graphicPane, 
            this.charPane);

    }

    // Public methods

    public void draw() {
        this.graphicPane.getChildren().clear();

        drawGraphic(displayWiring);
    }

    public void highlight(int[] processArray) {
        highlightChars = processArray;
        highlight = processArray;
    }

    // Helper methods
    
    @Override
    protected void drawGraphic(int[] wiring) {
        int where = blankColumnWidth - 2;
        for (int i = 0; i < wiring.length; i++) {
            final int k = i;
            int startX = 0 + k * 2;
            int startY = rowWidth / 2 + k * rowWidth;
            int width = where - startX;
            int height = (wiring[k] - k) * rowWidth;

            if (height > 0) {
                Rectangle rect = new Rectangle(startX, startY, width, height);
                rect.setFill(Color.TRANSPARENT);
                if (highlight.length > 1 && IntStream.of(highlight).anyMatch(j -> j == k)) {
                    rect.setStroke(Color.BLACK);
                    rect.setStrokeWidth(3);
                } else {
                    rect.setStroke(colors[k % colorNumber]);
                }
                this.graphicPane.getChildren().add(rect);
            }
        }
        Line line = new Line(where, 0, where, height);
        line.setStroke(Color.web("F4F4F5"));
        line.setStrokeWidth(4);
        this.graphicPane.getChildren().add(line);
    }

}