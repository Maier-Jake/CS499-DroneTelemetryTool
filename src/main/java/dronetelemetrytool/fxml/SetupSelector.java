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

public class SetupSelector {
    @FXML
    public TextField VIDEOaddress;
    @FXML
    public TextField CSVaddress;
    @FXML
    public Button continueButton;

    @FXML
    protected void onNewClick() throws IOException {
        DTT_GUI.fieldSelection();
    }

    @FXML
    protected void onExistingClick() throws IOException {

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