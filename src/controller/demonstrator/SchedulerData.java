package controller.demonstrator;

import java.util.stream.Collectors;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.AbstractScheduler;

public class SchedulerData {
    private final SimpleStringProperty nameProperty;
    private final SimpleIntegerProperty totalSeekCountProperty;
    private final SimpleStringProperty sequenceProperty;
    private final ObservableList<SequenceData> sequenceData = FXCollections.observableArrayList();
    private final int head;

    public SchedulerData(AbstractScheduler scheduler) {
        this.nameProperty = new SimpleStringProperty(scheduler.getName());
        this.totalSeekCountProperty = new SimpleIntegerProperty(scheduler.getTotalSeekCount());
        this.sequenceProperty = new SimpleStringProperty(String.join(", ",
            scheduler.getSchedule().stream().map(Object::toString).collect(Collectors.joining(", "))));
        for (int i = 0; i < scheduler.getSchedule().size(); i++) {
            sequenceData.add(new SequenceData(scheduler.getSchedule().get(i), scheduler.getSeekCount().get(i)));
        }
        this.head = scheduler.getInitialHead();
    }

    public String getNameProperty() {
        return this.nameProperty.get();
    }
    public void setNameProperty(String name) {
        this.nameProperty.set(name);
    }
 
    public int getTotalSeekCountProperty() {
        return this.totalSeekCountProperty.get();
    }
    public void setTotalSeekCountProperty(int count) {
        this.totalSeekCountProperty.set(count);
    }
        
    public String getSequenceProperty() {
        return this.sequenceProperty.get();
    }
    public void setSequenceProperty(String sequence) {
        this.sequenceProperty.set(sequence);
    }

    public ObservableList<SequenceData> getSequenceData() {
        return this.sequenceData;
    }

    public int getHead() {
        return this.head;
    }
    
}
