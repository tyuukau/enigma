package controller.components.interior;

import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.RowConstraints;

public class VirtualInOutIndicator extends VirtualInternalComponent {

    // Internal members

    private final int intCharOffset = 65;

    // Instantiations
    
    public VirtualInOutIndicator(int width) {

        super(width);

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

    // Public methods

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