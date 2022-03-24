package dronetelemetrytool.fxml;

import dronetelemetrytool.DTT_GUI;
import dronetelemetrytool.DTT_Tools;
import dronetelemetrytool.MainApplication;
import dronetelemetrytool.gauges.XYPlotGauge;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

public class GaugeSelector implements Initializable {
    @FXML
    private Button BUTTON_Close;

    @FXML
    protected void onCancelClick() {

    }
    @FXML
    protected void circle90Click() throws IOException {
        DTT_GUI.circle90GaugeCreator();
    }
    @FXML
    protected void circle180Click() throws IOException {
        DTT_GUI.circle180GaugeCreator();
    }
    @FXML
    protected void circle270Click() throws IOException {
        DTT_GUI.circle270GaugeCreator();
    }
    @FXML
    protected void circle360Click() throws IOException {
        DTT_GUI.circle360GaugeCreator();
    }
    @FXML
    protected void barClick() throws IOException {
        DTT_GUI.barGaugeCreator();
    }
    @FXML
    protected void timestampClick() throws IOException {
        DTT_GUI.timestampGaugeCreator();
    }
    @FXML
    protected void textClick() throws IOException {
        DTT_GUI.textGaugeCreator();
    }
    @FXML
    protected void characterClick() throws IOException {
        DTT_GUI.characterGaugeCreator();
    }
    @FXML
    protected void xPlotClick() throws IOException {
        DTT_GUI.xPlotGaugeCreator();
    }
    @FXML
    protected void xyPlotClick() throws IOException {
        DTT_GUI.xyPlotGaugeCreator();
    }
    @FXML
    protected void clockClick() throws IOException {
        DTT_GUI.clockGaugeCreator();
    }
    @FXML
    protected void boolClick() throws IOException {
        DTT_GUI.onOffGaugeCreator();
    }
    @FXML
    public void initialize(URL url, ResourceBundle rb) {

    }


    @FXML
    protected void onCompletedClick() throws IOException {

    }
}