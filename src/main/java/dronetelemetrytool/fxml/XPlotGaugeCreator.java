package dronetelemetrytool.fxml;

import dronetelemetrytool.DTT_Tools;
import dronetelemetrytool.MainApplication;
import dronetelemetrytool.gauges.ClusterBarGauge;
import dronetelemetrytool.gauges.GaugeOrient;
import dronetelemetrytool.gauges.XPlotGauge;
import eu.hansolo.tilesfx.colors.Bright;
import eu.hansolo.toolboxfx.GradientLookup;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.paint.Stop;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

public class XPlotGaugeCreator implements Initializable {
    @FXML
    private Label HEADER;
    @FXML
    private TextField FIELD_Title;
    @FXML
    private TextField FIELD_Label;
    @FXML
    private TextField FIELD_TickUnit;
    @FXML
    private TextField FIELD_Minimum;
    @FXML
    private TextField FIELD_Maximum;
    @FXML
    private Button BUTTON_Close;

    @FXML // fx:id="COMBO_Format"
    private ComboBox<String> COMBO_Orient;


    @FXML
    protected void onCancelClick() {
        System.out.println("Cancelled creating this gauge.");

        //Finished, close this Gauge Creation window.
        Stage stage = (Stage) BUTTON_Close.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void initialize(URL url, ResourceBundle rb) {


        // populate the combo box with string format choices.
        COMBO_Orient.getItems().setAll("Horizontal", "Vertical");

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
        FIELD_Minimum.setTextFormatter(new TextFormatter<>(doubleConverter, 0.0, doubleFilter));
        FIELD_Maximum.setTextFormatter(new TextFormatter<>(doubleConverter, 0.0, doubleFilter));
        FIELD_TickUnit.setTextFormatter(new TextFormatter<>(doubleConverter, 0.0, doubleFilter));
    }


    @FXML
    protected void onCompletedClick() throws IOException {
        //welcomeText.setText("Welcome to JavaFX Application!");

        String title = FIELD_Title.textProperty().getValueSafe();
        double minVal = Double.parseDouble(FIELD_Minimum.textProperty().getValueSafe());
        double maxVal = Double.parseDouble(FIELD_Maximum.textProperty().getValueSafe());
        double tickUnit = Double.parseDouble(FIELD_TickUnit.textProperty().getValueSafe());
        String orient = COMBO_Orient.getValue();
        String label = FIELD_Label.textProperty().getValueSafe();

        if (minVal < maxVal)
        {
            if (orient != null && orient.equals("Vertical"))
            {
                createGauge(title, minVal, maxVal, tickUnit, label, GaugeOrient.VERTICAL);
            }
            else
            {
                createGauge(title, minVal, maxVal, tickUnit, label, GaugeOrient.HORIZONTAL);
            }

            Stage stage = (Stage) BUTTON_Close.getScene().getWindow();
            stage.close();
        }
        else
        {
            //warn user what the problem is
            Stage popup = DTT_Tools.popup((Stage) BUTTON_Close.getScene().getWindow(), "Minimum Value must be less than the Maximum Value");
        }
    }

    private static void createGauge(String title, double min, double max, double tickUnit, String label, GaugeOrient orient)
    {

        XPlotGauge newGauge = new XPlotGauge(orient, min, max, tickUnit);
        newGauge.setTitle(title);
        newGauge.setLabel(label);
        MainApplication.gauges.add(newGauge);
        newGauge.display();
    }
}