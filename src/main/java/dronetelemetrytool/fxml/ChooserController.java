package dronetelemetrytool.fxml;

import dronetelemetrytool.gaugeselection.FieldCollection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.FileChooser;
import javafx.event.EventHandler;
import javafx.scene.text.Text;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.io.FileReader;
import java.io.FileNotFoundException;

import dronetelemetrytool.gaugeselection.Field;
import dronetelemetrytool.gaugeselection.UnitConverter;

public class ChooserController{
    @FXML private Text welcomeText;
    @FXML private Button selectCSVButton;
    FileChooser fileChooser;
    private File selectedFile;
    private UnitConverter uc = new UnitConverter();
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
        // Show the file selection window, and read the fields for the selected file.
        selectCSVButton.setOnAction( new EventHandler<ActionEvent> () {
            public void handle(ActionEvent event) {
                selectedFile = fileChooser.showOpenDialog(csvStage);
                if (selectedFile != null) {
                    myFieldCollection = new FieldCollection();
                    String path = selectedFile.toPath().toString();
                    try {
                        FileReader reader = new FileReader(path);
                        myFieldCollection.loadCSV(reader);
                        // Should run the field selection process.
                        // Pass n
                    } catch (FileNotFoundException noCSV) {
                        System.out.println("File not found: ");
                    }
                    List<ArrayList<Field>> typedFields = myFieldCollection.getTypedFields();
                }
            }});
    }

    public void announce(){
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