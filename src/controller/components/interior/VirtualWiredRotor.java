package controller.components.interior;

public class VirtualWiredRotor extends VirtualWiredRotorLikeCompoment {
    
    // Instantiations

    public VirtualWiredRotor(int width) {
        super(width);
    }

    // Public methods

    public void draw(int position, int offset) {
        this.graphicPane.getChildren().clear();

        drawGraphic(displayWiring);

        this.charPane.getChildren().clear();

        fillColumn(this.charPane, alphabet, 2, position);
        fillColumn(this.charPane, alphabet, 0, position + offset);
    }

}