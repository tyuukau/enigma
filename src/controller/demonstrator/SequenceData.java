package controller.demonstrator;

import javafx.beans.property.SimpleIntegerProperty;

public class SequenceData {
    private final SimpleIntegerProperty pointProperty;
    private final SimpleIntegerProperty seekCountProperty;

    public SequenceData(int x, int y) {
        this.pointProperty = new SimpleIntegerProperty(x);
        this.seekCountProperty = new SimpleIntegerProperty(y);
    }

    public int getPointProperty() {
        return this.pointProperty.get();
    }
    public void setPointProperty(int x) {
        this.pointProperty.set(x);
    }
 
    public int getSeekCountProperty() {
        return this.seekCountProperty.get();
    }
    public void setSeekCountProperty(int count) {
        this.seekCountProperty.set(count);
    }
            
}
