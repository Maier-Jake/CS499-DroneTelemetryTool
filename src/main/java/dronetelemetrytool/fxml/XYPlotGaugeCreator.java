package dronetelemetrytool.fxml;

import dronetelemetrytool.DTT_Tools;
import dronetelemetrytool.MainApplication;
import dronetelemetrytool.gauges.GaugeOrient;
import dronetelemetrytool.gauges.XPlotGauge;
import dronetelemetrytool.gauges.XYPlotGauge;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

public class XYPlotGaugeCreator implements Initializable {
    @FXML
    private Label HEADER;
    @FXML
    private TextField FIELD_Title;
    @FXML
    private TextField FIELD_XLabel;
    @FXML
    private TextField FIELD_YLabel;
    @FXML
    private TextField FIELD_XTickUnit;
    @FXML
    private TextField FIELD_YTickUnit;
    @FXML
    private TextField FIELD_XMinimum;
    @FXML
    private TextField FIELD_XMaximum;
    @FXML
    private TextField FIELD_YMinimum;
    @FXML
    private TextField FIELD_YMaximum;
    @FXML
    private Button BUTTON_Close;

    @FXML
    protected void onCancelClick() {
        System.out.println("Cancelled creating this gauge.");

        //Finished, close this Gauge Creation window.
        Stage stage = (Stage) BUTTON_Close.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void initialize(URL url, ResourceBundle rb) {

//        COMBO_Orient.getItems().setAll("Horizontal", "Vertical");

        Pattern validEditingState = Pattern.compile("-?(([1-9][0-9]*)|0)?(\\.[0-9]*)?");
        UnaryOperator<TextFormatter.Change> doubleFilter = c -> {
            String text = c.getControlNewText();
            if (validEditingState.matcher(text).matches()) {
                return c ;
            } else {
                return null ;
            }
        };
        StringConverter<Double> doubleConverter = new StringConverter<Double>() {
            @Override
            public Double fromString(String s) {
                if (s.isEmpty() || "-".equals(s) || ".".equals(s) || "-.".equals(s)) {
                    return 0.0 ;
                } else {
                    return Double.valueOf(s);
                }
            }

            @Override
            public String toString(Double d) {
                return d.toString();
            }
        };
        FIELD_XMaximum.setTextFormatter(new TextFormatter<>(doubleConverter, 0.0, doubleFilter));
        FIELD_XMinimum.setTextFormatter(new TextFormatter<>(doubleConverter, 0.0, doubleFilter));
        FIELD_XTickUnit.setTextFormatter(new TextFormatter<>(doubleConverter, 0.0, doubleFilter));
        FIELD_YMaximum.setTextFormatter(new TextFormatter<>(doubleConverter, 0.0, doubleFilter));
        FIELD_YMinimum.setTextFormatter(new TextFormatter<>(doubleConverter, 0.0, doubleFilter));
        FIELD_YTickUnit.setTextFormatter(new TextFormatter<>(doubleConverter, 0.0, doubleFilter));
    }


    @FXML
    protected void onCompletedClick() throws IOException {
        //welcomeText.setText("Welcome to JavaFX Application!");

        String title = FIELD_Title.textProperty().getValueSafe();
        double xMinVal = Double.parseDouble(FIELD_XMinimum.textProperty().getValueSafe());
        double xMaxVal = Double.parseDouble(FIELD_XMaximum.textProperty().getValueSafe());
        double xTickUnit = Double.parseDouble(FIELD_XTickUnit.textProperty().getValueSafe());
        double yMinVal = Double.parseDouble(FIELD_YMinimum.textProperty().getValueSafe());
        double yMaxVal = Double.parseDouble(FIELD_YMaximum.textProperty().getValueSafe());
        double yTickUnit = Double.parseDouble(FIELD_YTickUnit.textProperty().getValueSafe());
//        String orient = COMBO_Orient.getValue();
        String xLabel = FIELD_XLabel.textProperty().getValueSafe();
        String yLabel = FIELD_YLabel.textProperty().getValueSafe();

        if (xMinVal < xMaxVal && yMinVal < yMaxVal)
        {
            createGauge(title, xMinVal, xMaxVal, xTickUnit, xLabel, yMinVal, yMaxVal, yTickUnit, yLabel);

            Stage stage = (Stage) BUTTON_Close.getScene().getWindow();
            stage.close();
        }
        else
        {
            //warn user what the problem is
            Stage popup = DTT_Tools.popup((Stage) BUTTON_Close.getScene().getWindow(), "Minimum Value must be less than the Maximum Value");
        }
    }

    private static void createGauge(String title, double xMin, double xMax, double xTick, String xLabel, double yMin, double yMax, double yTick, String yLabel)
    {

        XYPlotGauge newGauge = new XYPlotGauge(xMin, xMax, xTick, yMin, yMax, yTick);
        newGauge.setTitle(title);
        newGauge.setXLabel(xLabel);
        newGauge.setYLabel(yLabel);
        MainApplication.gauges.add(newGauge);
        newGauge.display();
    }
}