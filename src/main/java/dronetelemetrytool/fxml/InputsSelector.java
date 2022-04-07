package dronetelemetrytool.fxml;

import dronetelemetrytool.fieldparsing.FieldCollection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.event.EventHandler;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileReader;
import java.io.FileNotFoundException;

public class InputsSelector {
    FileChooser fileChooser;
    FileChooser fileChooserV;   // This one is for video selection
    File selectedFile;
    private Stage csvStage;
    private FieldCollection myFieldCollection;

    @FXML
    protected void onCSVClick() {
        selectedFile = fileChooser.showOpenDialog(csvStage);
        if (selectedFile != null) {
            myFieldCollection = new FieldCollection();
            String path = selectedFile.toPath().toString();
            try {
                FileReader reader = new FileReader(path);
                myFieldCollection.loadCSV(reader);
            } catch (FileNotFoundException noCSV) {
                System.out.println("File not found: ");
            }

        }
    }

    @FXML
    protected void onVideoClick() {
        selectedFile = fileChooserV.showOpenDialog(csvStage);
        if (selectedFile != null) {
            myFieldCollection = new FieldCollection();
            String path = selectedFile.toPath().toString();
                    /*
                    try {
                        FileReader reader = new FileReader(path);
                        myFieldCollection.loadCSV(reader);
                    } catch (FileNotFoundException noCSV) {
                        System.out.println("File not found: ");
                    }
                    */
        }
    }

    @FXML
    public void initialize() throws FileNotFoundException {
        System.out.println("Initializing Controller");
        fileChooser = new FileChooser();
        // Set the file chooser button string
        fileChooser.setTitle("Open CSV file");
        // Set the types of files accepted
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));

        fileChooserV = new FileChooser();
        // Set the file chooser button string
        fileChooserV.setTitle("Open Video file");
        // Set the types of files accepted
        fileChooserV.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("MP4 Files", "*.mp4"));
    }

    public void anounce(){
        System.out.println("Controller accessed.");
    }

    public void setStage(Stage stage) {
        System.out.println("Setting stage");
        System.out.println(stage.getTitle());
        csvStage = stage;
        }
    /*
    @FXML
    protected void onCSVButton(ActionEvent ae) {
        Node eventNode = (Node) ae.getSource();
        stage = (Stage) eventNode.getScene().getWindow();
        selectedFile = fileChooser.showOpenDialog(stage);
        System.out.println(selectedFile.toPath());
    }
    */

}