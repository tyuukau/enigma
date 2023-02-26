package controller.components.interior;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public abstract class VirtualWiredComponent extends VirtualInternalComponent {
    
    // Common members

    protected final List<Character> alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".chars().mapToObj(e -> (char)e).collect(Collectors.toList());

    protected final int charColumnWidth = 20;
    protected int blankColumnWidth;
    protected final int rowHeight = height / 26;

    public final int[] displayWiring = new int[26];

    protected int[] highlight = new int[] {-1};
    protected int[] highlightChars = new int[] {-1};

    protected Color colors[] = {
        Color.web("F60000"),
        Color.web("FF8C00"),
        Color.web("FFEE00").darker(),
        Color.web("4DE94C"),
        Color.web("3783FF"),
        Color.web("4815AA"),
    };

    protected int colorNumber = colors.length;

    // Instantiations

    public VirtualWiredComponent(int width) {
        
        super(width);

        this.graphicPane = new Pane();

        this.graphicPane.setMinSize(width, height);
        this.graphicPane.setMaxSize(width, height);

    }

    // Common methods

    protected abstract void highlight(int[] processArray);

    public void dehighlight() {
        highlight = new int[] {-1};
        highlightChars = new int[] {-1};
    }

    protected abstract void drawGraphic(int[] wiring);

    // Common helper methods

    protected void fillColumn(GridPane gridPane, List<Character> chars, int column) {
        for (int i = 0; i < 26; i++) {
            String c = Character.toString(chars.get(i));
            fill(gridPane, c, column, i);
        }
    }

    protected void fillColumn(GridPane gridPane, List<Character> chars, int column, int offset) {
        List<Character> rotatedAlphabet = new ArrayList<>(chars);
        Collections.rotate(rotatedAlphabet, -offset);
        for (int i = 0; i < 26; i++) {
            String c = Character.toString(rotatedAlphabet.get(i));
            fill(gridPane, c, column, i);
        }
    }

}