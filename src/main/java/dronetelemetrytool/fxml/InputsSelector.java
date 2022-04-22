package dronetelemetrytool.fxml;

import dronetelemetrytool.DTT_GUI;
import dronetelemetrytool.MainApplication;
import dronetelemetrytool.fieldparsing.FieldCollection;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.media.Media;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Paths;

public class InputsSelector {
    @FXML
    public TextField VIDEOaddress;
    @FXML
    public TextField CSVaddress;
    @FXML
    public Button continueButton;

    @FXML
    protected void onCSVClick() {

        FileChooser chooser = new FileChooser();
        chooser.setTitle("Select Drone's Telemetry CSV file");
        chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));

        String currentPath = Paths.get(".").toAbsolutePath().normalize().toString();
        chooser.setInitialDirectory(new File(currentPath));
        Stage stage = (Stage) continueButton.getScene().getWindow();
        File selectedFile = chooser.showOpenDialog(stage);

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
    protected void onVideoClick() throws IOException {

        FileChooser chooser = new FileChooser();
        chooser.setTitle("Select Drone's Video file correlated to Telemetry file");
        chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Video Files", "*.mp4;*.mov"));

        String currentPath = Paths.get(".").toAbsolutePath().normalize().toString();
        chooser.setInitialDirectory(new File(currentPath));
        Stage stage = (Stage) continueButton.getScene().getWindow();
        File selectedFile = chooser.showOpenDialog(stage);

        if (selectedFile != null) {
            String path = selectedFile.toPath().toString();
            VIDEOaddress.setText(path);
            Media tempMed = null;
            if (selectedFile.getAbsolutePath().toLowerCase().contains(".mov"))
            {
                int i = selectedFile.getName().lastIndexOf('.');
                String filename = selectedFile.getName().substring(0,i);
                File copiedFile = new File(selectedFile.getParent(), filename + ".mp4");
                FileUtils.copyFile(selectedFile, copiedFile);
                try { tempMed = new Media(copiedFile.toURI().toURL().toString()); }
                catch (MalformedURLException e) { e.printStackTrace(); }
            }
            else
            {
                try { tempMed = new Media(selectedFile.toURI().toURL().toString()); }
                catch (MalformedURLException e) { e.printStackTrace(); }
            }


            MainApplication.video = tempMed;

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
        DTT_GUI.frequencySelector();
    }

    @FXML
    public void initialize() throws FileNotFoundException {
        continueButton.setDisable(true);
    }

}