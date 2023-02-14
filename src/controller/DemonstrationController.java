package controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;

import controller.demonstrator.DemonstratorMode0;
import controller.demonstrator.DemonstratorMode1;
import controller.demonstrator.DemonstratorMode2;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import model.*;

public class DemonstrationController {

    // Top bar

    @FXML
    private TextField headInput;

    @FXML
    private TextField requestsInput;

    @FXML
    private Label selectedAlgorithmsLabel;

    // Button bar

    @FXML
    private CheckBox fcfsButton;

    @FXML
    private CheckBox lifoButton;

    @FXML
    private CheckBox sstfButton;

    @FXML
    private CheckBox scanButton;

    @FXML
    private CheckBox cscanButton;

    @FXML
    private CheckBox lookButton;

    @FXML
    private CheckBox clookButton;

    // Draw pane

    @FXML
    private Pane drawPane;

    // Other internal members

    private final static int diskSize = 200;
    private int initialHead;
    private int[] requests;
    private int goAble = 0;
    private final int maxLength = 10;
    int mode;

    private FCFSScheduler fcfsScheduler;
    private LIFOScheduler lifoScheduler;
    private SSTFScheduler sstfScheduler;
    private SCANScheduler scanSchedulerLeft;
    private SCANScheduler scanSchedulerRight;
    private CSCANScheduler cscanSchedulerLeft;
    private CSCANScheduler cscanSchedulerRight;
    private LOOKScheduler lookSchedulerLeft;
    private LOOKScheduler lookSchedulerRight;
    private CLOOKScheduler clookSchedulerLeft;
    private CLOOKScheduler clookSchedulerRight;

    private Hashtable<CheckBox, AbstractScheduler[]> buttonScheduler = new Hashtable<CheckBox, AbstractScheduler[]>();
    private ArrayList<AbstractScheduler> selectedAlgorthims = new ArrayList<AbstractScheduler>();

    // FXML actions

    @FXML
    void save(ActionEvent event) {
        saveSettings();
        initializeGUI();
    }

    @FXML
    void reset(ActionEvent event) {
        initializeSettings();
        saveSettings();
        initializeGUI();
    }

    @FXML
    void go(ActionEvent event) {
        if (goAble == 0) {
            alert("Settings Not Initialized", "You must configure the settings for\nwaiting track (maximum 10 requests) and head.");
        } else {
            selectAlgorithms();
            drawPane.getChildren().clear();
            drawGUI();
        }
    }

    @FXML
    void help(ActionEvent event) {
        alert("Help", 
            "This is a GUI application for disk scheduling demonstrations.\n"
          + "It was developed as the Operating System project for the class 136315, semester 20221.");
    }

    // Helper methods

    public void initialize() {
        initializeSettings();
        initializeGUI();

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                saveSettings();
            }
        });
    }

    private void initializeSettings() {
        headInput.setText("50");
        requestsInput.setText("82 170 43 140 24 16 190");
    }

    private void initializeGUI() {
        selectedAlgorithmsLabel.setText("None");

        CheckBox[] checkBox1Dir = {fcfsButton, lifoButton, sstfButton};
        CheckBox[] checkBox2Dir = {scanButton, cscanButton, lookButton, clookButton};
    
        for (CheckBox i : checkBox1Dir) {
            i.setSelected(false);
        }
        for (CheckBox i : checkBox2Dir) {
            i.setSelected(false);
        }

        drawPane.getChildren().clear();
    }

    private void saveSettings() {
        String headString = headInput.getText();
        String requestsString = requestsInput.getText();

        try {
            int head = Integer.parseInt(headString);

            String[] string = requestsString.split(" ");
            int[] arr = new int[string.length];
            for (int i = 0; i < string.length; i++) {
                arr[i] = Integer.valueOf(string[i]);
            }

            List<Integer> intList = new ArrayList<Integer>(arr.length);
            for (int i : arr) {
                intList.add(i);
            }
            intList.add(head);

            int check = 1;
            for (Integer i : intList) {
                if (i < 0 || i > diskSize) {
                    check = 0;
                }
            }

            check = (intList.size() < 2 || intList.size() > maxLength + 1) ? 0 : 1;
            
            if (check == 1) {
                initialHead = head;
                requests = arr;
                goAble = 1;
                alert("Save Settings Completed", "Head: " + head + "\nRequests: " + Arrays.toString(requests));
            } else {
                goAble = 0;
                alert("Save Settings Failed", "You must enter integers ranging\nfrom 0 to 199, inclusive.");
            }

        } catch (NumberFormatException e) {
            goAble = 0;
            alert("Save Settings Failed", "You must enter integers ranging\nfrom 0 to 199, inclusive.");
        }

    }

    private void selectAlgorithms() {
        selectedAlgorthims = new ArrayList<AbstractScheduler>();

        this.fcfsScheduler = new FCFSScheduler(requests, initialHead);
        this.lifoScheduler = new LIFOScheduler(requests, initialHead);
        this.sstfScheduler = new SSTFScheduler(requests, initialHead);
        this.scanSchedulerLeft = new SCANScheduler(requests, initialHead, false);
        this.scanSchedulerRight = new SCANScheduler(requests, initialHead, true);
        this.lookSchedulerLeft = new LOOKScheduler(requests, initialHead, false);
        this.lookSchedulerRight = new LOOKScheduler(requests, initialHead, true);
        this.cscanSchedulerLeft = new CSCANScheduler(requests, initialHead, false);
        this.cscanSchedulerRight = new CSCANScheduler(requests, initialHead, true);
        this.clookSchedulerLeft = new CLOOKScheduler(requests, initialHead, false);
        this.clookSchedulerRight = new CLOOKScheduler(requests, initialHead, true);
        
        buttonScheduler.put(fcfsButton, new AbstractScheduler[] {fcfsScheduler});
        buttonScheduler.put(lifoButton, new AbstractScheduler[] {lifoScheduler});
        buttonScheduler.put(sstfButton, new AbstractScheduler[] {sstfScheduler});
        buttonScheduler.put(scanButton, new AbstractScheduler[] {scanSchedulerLeft, scanSchedulerRight});
        buttonScheduler.put(cscanButton, new AbstractScheduler[] {cscanSchedulerLeft, cscanSchedulerRight});
        buttonScheduler.put(lookButton, new AbstractScheduler[] {lookSchedulerLeft, lookSchedulerRight});
        buttonScheduler.put(clookButton, new AbstractScheduler[] {clookSchedulerLeft, clookSchedulerRight});    

        ArrayList<String> schedulerNameList = new ArrayList<String>();

        CheckBox[] checkBox1Dir = {fcfsButton, lifoButton, sstfButton};
        CheckBox[] checkBox2Dir = {scanButton, cscanButton, lookButton, clookButton};    

        for (CheckBox checkBox : checkBox1Dir) {
            if (checkBox.isSelected()) {
                for (AbstractScheduler s : buttonScheduler.get(checkBox)) {
                    schedulerNameList.add(s.getName());
                    selectedAlgorthims.add(s);
                }
            }
        }
        for (CheckBox checkBox : checkBox2Dir) {
            if (checkBox.isSelected()) {
                for (AbstractScheduler s : buttonScheduler.get(checkBox)) {
                    schedulerNameList.add(s.getName());
                    selectedAlgorthims.add(s);
                }
            }
        }
        selectedAlgorithmsLabel.setText(String.join(", ", schedulerNameList));

        switch (selectedAlgorthims.size()) {
            case 1:
                mode = 1;
                break;
            case 2:
                mode = 2;
                break;
            default:
                mode = 0;
                break;
        }

    }

    private void drawGUI() {
        switch (mode) {
            case 1:
                DemonstratorMode1 demo1 = new DemonstratorMode1(this.selectedAlgorthims);
                this.drawPane.getChildren().add(demo1.view());
                break;
            case 2:
                DemonstratorMode2 demo2 = new DemonstratorMode2(this.selectedAlgorthims);
                this.drawPane.getChildren().add(demo2.view());
                break;
            case 0:
                DemonstratorMode0 demo0 = new DemonstratorMode0(this.selectedAlgorthims);
                this.drawPane.getChildren().add(demo0.view());
                break;
        }
    }

    private void alert(String title, String content) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);

        // Header Text: null
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

}