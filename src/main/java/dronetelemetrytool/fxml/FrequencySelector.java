package dronetelemetrytool.fxml;

import dronetelemetrytool.DTT_GUI;
import dronetelemetrytool.MainApplication;
import dronetelemetrytool.fieldparsing.FieldCollection;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.media.Media;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Paths;

public class FrequencySelector {
    @FXML
    public TextField VIDEOaddress;
    @FXML
    public TextField CSVaddress;
    @FXML
    public Button continueButton;

    @FXML
    public ListView listView;

    @FXML
    protected void onCSVClick() {

        FileChooser chooser = new FileChooser();
        chooser.setTitle("Select Drone's Telemetry CSV file");
        chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));

        String currentPath = Paths.get(".").toAbsolutePath().normalize().toString();
        chooser.setInitialDirectory(new File(currentPath));
        File selectedFile = chooser.showOpenDialog(new Stage());

        if (selectedFile != null) {
            String path = selectedFile.toPath().toString();
            CSVaddress.setText(path);
            FieldCollection collection = new FieldCollection();
            try {
                FileReader reader = new FileReader(path);
                collection.loadCSV(reader);
                MainApplication.fields = collection;
            } catch (FileNotFoundException noCSV) {
                System.out.println("File not found: ");
            }

            //check to see if video loaded. if so, activate the continue button.
            if(VIDEOaddress.getText() != "")
            {
                continueButton.setDisable(false);
            }
        }
    }

    @FXML
    protected void onVideoClick() {

        FileChooser chooser = new FileChooser();
        chooser.setTitle("Select Drone's Video file correlated to Telemetry file");
        chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("MP4 Files", "*.mp4"));

        String currentPath = Paths.get(".").toAbsolutePath().normalize().toString();
        chooser.setInitialDirectory(new File(currentPath));
        File selectedFile = chooser.showOpenDialog(new Stage());

        if (selectedFile != null) {
            String path = selectedFile.toPath().toString();
            VIDEOaddress.setText(path);
            Media tempMed = null;
            try {
                tempMed = new Media(selectedFile.toURI().toURL().toString());
                MainApplication.video = tempMed;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

            //check to see if csv telemetry loaded. if so, activate the continue button.
            if(CSVaddress.getText() != "")
            {
                continueButton.setDisable(false);
            }
        }
    }

    @FXML
    protected void onContinueClick() throws IOException {
        Stage stage = (Stage) continueButton.getScene().getWindow();
        stage.close();
        DTT_GUI.fieldSelection();
    }

    @FXML
    public void initialize() throws FileNotFoundException {
        continueButton.setDisable(true);
    }
}