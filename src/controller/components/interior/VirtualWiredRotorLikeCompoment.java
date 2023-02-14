package controller.components.interior;

import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

import java.util.stream.IntStream;

public abstract class VirtualWiredRotorLikeCompoment extends VirtualWiredComponent {

    // Internal members

    protected final int height = 520;

    public VirtualWiredRotorLikeCompoment(int width) {

        super(width);

        blankColumnWidth = width - charColumnWidth * 2;

        for (int i = 0; i < alphabetLength; i++) {
            RowConstraints rowConst = new RowConstraints();
            rowConst.setPercentHeight(100.0 / alphabetLength);
            this.charPane.getRowConstraints().add(rowConst);         
        }

        ColumnConstraints colConst0 = new ColumnConstraints(charColumnWidth, charColumnWidth, charColumnWidth);
        this.charPane.getColumnConstraints().add(colConst0);

        ColumnConstraints colConst1 = new ColumnConstraints(blankColumnWidth, blankColumnWidth, blankColumnWidth);
        this.charPane.getColumnConstraints().add(colConst1);

        ColumnConstraints colConst2 = new ColumnConstraints(charColumnWidth, charColumnWidth, charColumnWidth);
        this.charPane.getColumnConstraints().add(colConst2);

        this.root.getChildren().addAll(
            this.graphicPane, 
            this.charPane);
    }

    @Override
    public void highlight(int[] processArray) {
        highlightChars = processArray;
        highlight = new int[] {processArray[0], processArray[2]};
    }

    // Helper methods

    @Override
    protected void drawGraphic(int[] wiring) {
        for (int i = 0; i < wiring.length; i++) {
            final int k = i;
            int startX = charColumnWidth + blankColumnWidth;
            int startY = rowHeight / 2 + rowHeight * k;
            int endX = charColumnWidth;
            int endY = rowHeight / 2 + rowHeight * wiring[k];
            
            Line line = new Line(startX, startY, endX, endY);
            if (highlight.length > 1 && IntStream.of(highlight).anyMatch(j -> j == k)) {
                line.setStroke(Color.BLACK);
                line.setStrokeWidth(3);
            } else {
                line.setStroke(colors[k % colorNumber]);
            }
            this.graphicPane.getChildren().add(line);
        }
    }
    
}