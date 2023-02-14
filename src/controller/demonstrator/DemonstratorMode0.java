package controller.demonstrator;

import java.util.ArrayList;

import javafx.geometry.Pos;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import model.*;

public class DemonstratorMode0 extends AbstractDemonstrator {

    HBox upperPane = new HBox();

    TableView<SchedulerData> table = new TableView<SchedulerData>();

    public DemonstratorMode0(ArrayList<AbstractScheduler> selectedAlgorthims) {
        super(selectedAlgorthims);

        upperPane.setMinSize(width, height / 2);
        upperPane.setPrefSize(width, height / 2);
        upperPane.setMaxSize(width, height / 2);
        upperPane.setAlignment(Pos.CENTER_LEFT);

        table.setEditable(false);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.setMinSize(width, 312);
        table.setPrefSize(width, 312);
        table.setMaxSize(width, 312);
        table.setFixedCellSize(24);

        table.skinProperty().addListener((obs, ol, ne) -> {
            Pane header = (Pane) table.lookup("TableHeaderRow");   
            header.prefHeightProperty().bind(table.heightProperty().divide(13));
        });
        
        TableColumn<SchedulerData, String> column1 = new TableColumn<>("Algorithm");
        column1.setCellValueFactory(new PropertyValueFactory<SchedulerData, String>("nameProperty"));
        column1.impl_setReorderable(false);
        column1.setMinWidth(100);
        column1.setPrefWidth(100);
        column1.setMaxWidth(100);
    
        TableColumn<SchedulerData, String> column2 = new TableColumn<>("Seek count");
        column2.setCellValueFactory(new PropertyValueFactory<SchedulerData, String>("totalSeekCountProperty"));
        column2.impl_setReorderable(false);
        column2.setMinWidth(100);
        column2.setPrefWidth(100);
        column2.setMaxWidth(100);

        TableColumn<SchedulerData, String> column3 = new TableColumn<>("Sequence");
        column3.setCellValueFactory(new PropertyValueFactory<SchedulerData, String>("sequenceProperty"));
        column3.impl_setReorderable(false);
        column3.setMinWidth(500);
        column3.setPrefWidth(500);
        column3.setMaxWidth(500);
    
        table.setItems(this.selectedAlgorthimsData);
        table.getColumns().add(column1);
        table.getColumns().add(column2);
        table.getColumns().add(column3);

        upperPane.getChildren().add(table);

        this.root.getChildren().add(upperPane);
    }
    
}
