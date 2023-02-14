package controller.components.interior;

public class VirtualWiredPlugboard extends VirtualWiredRotorLikeCompoment {
    
    // Instantiations

    public VirtualWiredPlugboard(int width) {
        super(width);
    }

    // Public methods

    public void draw() {
        this.graphicPane.getChildren().clear();

        drawGraphic(displayWiring);

        this.charPane.getChildren().clear();

        fillColumn(this.charPane, alphabet, 0);
        fillColumn(this.charPane, alphabet, 2);
    }

}