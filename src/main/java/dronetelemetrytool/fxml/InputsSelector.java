package dronetelemetrytool.fxml;

import dronetelemetrytool.fieldparsing.FieldCollection;
import eu.hansolo.tilesfx.tools.FlowGridPane;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.event.EventHandler;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileReader;
import java.io.FileNotFoundException;

public class InputsSelector {
    @FXML
    public TextField VIDEOaddress;
    @FXML
    public TextField CSVaddress;
    @FXML
    public FlowGridPane Fields;

    FileChooser fileChooser;
    FileChooser fileChooserV;   // This one is for video selection
    File selectedFile;
    private Stage csvStage;
    private FieldCollection myFieldCollection;

    @FXML
    protected void onCSVClick() {
        selectedFile = fileChooser.showOpenDialog(csvStage);
        if (selectedFile != null) {
            String path = selectedFile.toPath().toString();
            CSVaddress.setText(path);
            myFieldCollection = new FieldCollection();
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
            String path = selectedFile.toPath().toString();
            VIDEOaddress.setText(path);
        }
    }

    @FXML
    protected void onContinueClick() {

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

    public void setStage(Stage stage) {
        csvStage = stage;
        Fields = new FlowGridPane(8, 6);
        Fields.setHgap(5);
        Fields.setVgap(5);
        Fields.setAlignment(Pos.CENTER);
        Fields.setCenterShape(true);
        Fields.setPadding(new Insets(5));
        //pane.setPrefSize(800, 600);
        Fields.setBackground(new Background(new BackgroundFill(Color.web("#101214"), CornerRadii.EMPTY, Insets.EMPTY)));
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