package controller.components;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javafx.geometry.HPos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class VirtualWiredComponent {

    protected StackPane root;
    protected Pane graphicPane;
    protected GridPane charPane;
    
    protected final int alphabetLength = 26;

    protected final List<Character> alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".chars().mapToObj(e -> (char)e).collect(Collectors.toList());

    protected void fill(GridPane gridPane, String labelString, int column, int row) {
        Label label = new Label(labelString);
        GridPane.setHalignment(label, HPos.CENTER);
        gridPane.add(label, column, row);

    }

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
    
    /**
     * @return a view of the lampboard.
     */
    public Node view() {
        return this.root;
    }
    
}