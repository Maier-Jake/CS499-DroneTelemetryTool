package dronetelemetrytool.fxml;

import dronetelemetrytool.DTT_Tools;
import dronetelemetrytool.MainApplication;
import dronetelemetrytool.gauges.ClusterBarGauge;
import eu.hansolo.tilesfx.colors.Bright;
import eu.hansolo.toolboxfx.GradientLookup;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.paint.Stop;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

public class popupCreator implements Initializable {

    @FXML
    private Button BUTTON_Close;

    @FXML
    private Label errorText;

    @FXML
    protected void onCloseClick() {
        Stage stage = (Stage) BUTTON_Close.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void initData(String err) {
        errorText.setText(err);
    }

}