package dronetelemetrytool.fxml;

import dronetelemetrytool.gaugeselection.FieldCollection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import javafx.scene.control.Button;
import javafx.scene.Node;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileReader;
import java.io.FileNotFoundException;

public class ChooserController{
    @FXML private Text welcomeText;
    @FXML private Button selectCSVButton;
    @FXML private Button selectVideoButton;
    FileChooser fileChooser;
    FileChooser fileChooserV;   // This one is for video selection
    File selectedFile;
    private Stage csvStage;
    private FieldCollection myFieldCollection;

    @FXML
    public void initialize() throws FileNotFoundException {
        System.out.println("Initializing Controller");
        fileChooser = new FileChooser();
        // Set the file chooser button string
        fileChooser.setTitle("Open CSV file");
        // Set the types of files accepted
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
            selectCSVButton.setOnAction( new EventHandler<ActionEvent> () {
                public void handle(ActionEvent event) {
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
                }});


        fileChooserV = new FileChooser();
        // Set the file chooser button string
        fileChooserV.setTitle("Open Video file");
        // Set the types of files accepted
        fileChooserV.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("MP4 Files", "*.mp4"));
        selectVideoButton.setOnAction( new EventHandler<ActionEvent> () {
            public void handle(ActionEvent event) {
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
            }});
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