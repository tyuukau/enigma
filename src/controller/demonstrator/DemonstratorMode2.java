package controller.demonstrator;

import java.util.ArrayList;

import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import model.AbstractScheduler;

public class DemonstratorMode2 extends AbstractDemonstrator {

    HBox upperPane = new HBox();
    HBox lowerPane = new HBox();

    Pane upperInsidePane = new Pane();
    GridPane upperCharPane = new GridPane();

    Pane lowerInsidePane = new Pane();
    GridPane lowerCharPane = new GridPane();

    TableView<SequenceData> upperTable = new TableView<SequenceData>();
    TableView<SequenceData> lowerTable = new TableView<SequenceData>();

    int spacing = 10;
    int insidePaneWidth = 780;
    int insidePaneHeight = 312;
    int tableWidth = 130;
    int charColumnWidth = 40;
    int columnWidth = 60;
    int rowHeight = 24;
    int blankColumnWidth = insidePaneWidth - 2 * charColumnWidth;

    public DemonstratorMode2(ArrayList<AbstractScheduler> selectedAlgorthims) {
        super(selectedAlgorthims);

        upperPane.setMinSize(width, height / 2);
        upperPane.setPrefSize(width, height / 2);
        upperPane.setMaxSize(width, height / 2);
        upperPane.setAlignment(Pos.CENTER_LEFT);
        upperPane.setSpacing(spacing);

        lowerPane.setMinSize(width, height / 2);
        lowerPane.setPrefSize(width, height / 2);
        lowerPane.setMaxSize(width, height / 2);
        lowerPane.setAlignment(Pos.CENTER_LEFT);
        lowerPane.setSpacing(spacing);

        setTable(upperTable, this.selectedAlgorthimsData.get(0).getSequenceData());
        setInsidePane(upperInsidePane, upperCharPane, 0);
        upperPane.getChildren().addAll(upperTable, upperInsidePane);

        setTable(lowerTable, this.selectedAlgorthimsData.get(1).getSequenceData());
        setInsidePane(lowerInsidePane, lowerCharPane, 1);
        lowerPane.getChildren().addAll(lowerTable, lowerInsidePane);

        this.root.getChildren().addAll(upperPane, lowerPane);
    }

    private void setTable(TableView<SequenceData> table, ObservableList<SequenceData> value) {
        table.setEditable(false);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.setMinSize(tableWidth, insidePaneHeight);
        table.setPrefSize(tableWidth, insidePaneHeight);
        table.setMaxSize(tableWidth, insidePaneHeight);
        table.setFixedCellSize(rowHeight);

        table.skinProperty().addListener((obs, ol, ne) -> {
            Pane header = (Pane) table.lookup("TableHeaderRow");   
            header.prefHeightProperty().bind(table.heightProperty().divide(13));
        });
        
        TableColumn<SequenceData, String> column1 = new TableColumn<>("Point");
        column1.setCellValueFactory(new PropertyValueFactory<SequenceData, String>("pointProperty"));
        column1.impl_setReorderable(false);
        column1.setMinWidth(columnWidth);
        column1.setPrefWidth(columnWidth);
        column1.setMaxWidth(columnWidth);
    
        TableColumn<SequenceData, String> column2 = new TableColumn<>("Count");
        column2.setCellValueFactory(new PropertyValueFactory<SequenceData, String>("seekCountProperty"));
        column2.impl_setReorderable(false);
        column2.setMinWidth(columnWidth);
        column2.setPrefWidth(columnWidth);
        column2.setMaxWidth(columnWidth);
    
        table.setItems(value);
        table.getColumns().add(column1);
        table.getColumns().add(column2);
    }

    private void setInsidePane(Pane insidePane, GridPane charPane, int k) {
        SchedulerData internal = this.selectedAlgorthimsData.get(k);

        insidePane.setMinSize(insidePaneWidth, insidePaneHeight);
        insidePane.setPrefSize(insidePaneWidth, insidePaneHeight);
        insidePane.setMaxSize(insidePaneWidth, insidePaneHeight);

        insidePane.setStyle("-fx-background-color: white; -fx-background-radius: 3");

        charPane.setMinSize(width, height / 2);
        charPane.setPrefSize(width, height / 2);
        charPane.setMaxSize(width, height / 2);
        for (int i = 0; i < 13; i++) {
            RowConstraints rowConst = new RowConstraints();
            rowConst.setPrefHeight(rowHeight);
            charPane.getRowConstraints().add(rowConst);         
        }

        ColumnConstraints colConst0 = new ColumnConstraints(charColumnWidth, charColumnWidth, charColumnWidth);
        charPane.getColumnConstraints().add(colConst0);

        ColumnConstraints colConst1 = new ColumnConstraints(blankColumnWidth, blankColumnWidth, blankColumnWidth);
        charPane.getColumnConstraints().add(colConst1);

        ColumnConstraints colConst2 = new ColumnConstraints(charColumnWidth, charColumnWidth, charColumnWidth);
        charPane.getColumnConstraints().add(colConst2);

        ObservableList<SequenceData> filler = internal.getSequenceData();

        ArrayList<Integer> topNumbers = new ArrayList<Integer>();

        for (int i = 0; i < filler.size(); i++) {
            topNumbers.add(filler.get(i).getPointProperty());
            fill(charPane, "" + filler.get(i).getSeekCountProperty(), 0, i + 1);
            fill(charPane, "" + filler.get(i).getSeekCountProperty(), 2, i + 1);
        }
        topNumbers.add(0, internal.getHead());

        ArrayList<Integer> topNumbersLabel = new ArrayList<Integer>(topNumbers);
        if (!(topNumbersLabel.contains(0))) {
            topNumbersLabel.add(0);
        }
        if (!(topNumbersLabel.contains(199))) {
            topNumbersLabel.add(199);
        }

        for (int i = 0; i < topNumbersLabel.size(); i++) {
            Label label = new Label("" + topNumbersLabel.get(i));
            double x = 40 - 20 + (double)topNumbersLabel.get(i) / 200 * (double)blankColumnWidth;
            label.setMinSize(40, 24);
            label.setPrefSize(40, 24);
            label.setMaxSize(40, 24);
            label.setAlignment(Pos.CENTER);
            label.setLayoutX(x);
            label.setLayoutY(0);
            insidePane.getChildren().add(label);
        }

        for (int i = 0; i < topNumbers.size() - 1; i++) {
            double x_start = 40 + (double)topNumbersLabel.get(i) / 200 * (double)blankColumnWidth;
            double y_start = (i == 0) ? rowHeight : rowHeight / 2 + rowHeight * i;
            double x_end = 40 + (double)topNumbersLabel.get(i + 1) / 200 * (double)blankColumnWidth;
            double y_end = rowHeight / 2 + rowHeight * (i + 1);
            Line line = new Line(x_start, y_start, x_end, y_end);
            insidePane.getChildren().add(line);
        }

        Line lineLeft = new Line(charColumnWidth, rowHeight, charColumnWidth, insidePaneHeight - 2);
        Line lineRight = new Line(insidePaneWidth - charColumnWidth, rowHeight, insidePaneWidth - charColumnWidth, insidePaneHeight - 2);
        Line lineTop = new Line(charColumnWidth, rowHeight, insidePaneWidth - charColumnWidth, rowHeight);

        lineLeft.setStroke(Color.web("C6C6C8"));
        lineRight.setStroke(Color.web("C6C6C8"));
        lineTop.setStroke(Color.web("C6C6C8"));

        insidePane.getChildren().addAll(charPane, lineLeft, lineRight, lineTop);

    }
    
}
