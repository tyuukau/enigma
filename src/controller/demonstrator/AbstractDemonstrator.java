package controller.demonstrator;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import model.*;

import java.util.ArrayList;

import com.sun.javafx.css.StyleManager;

public class AbstractDemonstrator {
    
    // Common members

    protected VBox root;

    protected final int width = 920;
    protected final int height = 680;

    protected ObservableList<SchedulerData> selectedAlgorthimsData = FXCollections.observableArrayList();

    // Instantiations

    public AbstractDemonstrator(ArrayList<AbstractScheduler> selectedAlgorthims) {
        StyleManager.getInstance().addUserAgentStylesheet("/Users/nguyenhieu/Documents/GitHub/disc-scheduling/src/resources/css/application.css");

        this.root = new VBox();

        this.root.setMinSize(width, height);
        this.root.setPrefSize(width, height);
        this.root.setMaxSize(width, height);

        for (AbstractScheduler as : selectedAlgorthims) {
            selectedAlgorthimsData.add(new SchedulerData(as));
        }
    } 

    // View

    public Node view() {
        return this.root;
    }

    // Helper methods

    public void drawAll() {
    }

    protected void fill(GridPane gridPane, String labelString, int column, int row) {
        Label label = new Label(labelString);
        GridPane.setHalignment(label, HPos.CENTER);
        gridPane.add(label, column, row);
    }

}
