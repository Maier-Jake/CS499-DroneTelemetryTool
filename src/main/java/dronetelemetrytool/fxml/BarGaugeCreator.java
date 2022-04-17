package dronetelemetrytool.fxml;

import dronetelemetrytool.DTT_Tools;
import dronetelemetrytool.MainApplication;
import dronetelemetrytool.fieldparsing.NumberField;
import dronetelemetrytool.gauges.ClusterBarGauge;
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

public class BarGaugeCreator implements Initializable {

    private NumberField field;

    @FXML
    private TextField FIELD_Title;
    @FXML
    private TextField FIELD_GreenT;
    @FXML
    private TextField FIELD_YellowT;
    @FXML
    private TextField FIELD_RedT;
    @FXML
    private TextField FIELD_Minimum;
    @FXML
    private TextField FIELD_Maximum;
    @FXML
    private Button BUTTON_Close;
    @FXML
    private TextField STAT_max;
    @FXML
    private TextField STAT_min;
    @FXML
    private TextField STAT_avg;
    @FXML
    private TextField STAT_stddev;

    @FXML
    private ComboBox<String> COMBO_Alarm;
    @FXML
    private ComboBox<String> unitTypeComboBox;
    @FXML
    private ComboBox<String> currentUnitComboBox;
    @FXML
    private ComboBox<String> desiredUnitComboBox;

    @FXML
    protected void onCancelClick() {
        System.out.println("Cancelled creating this gauge.");
        //Finished, close this Gauge Creation window.
        Stage stage = (Stage) BUTTON_Close.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void initialize(URL url, ResourceBundle rb) {

        COMBO_Alarm.getItems().setAll("Chirp", "Siren", "Scream");

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
        FIELD_GreenT.setTextFormatter(new TextFormatter<>(doubleConverter, 0.0, doubleFilter));
        FIELD_YellowT.setTextFormatter(new TextFormatter<>(doubleConverter, 0.0, doubleFilter));
        FIELD_RedT.setTextFormatter(new TextFormatter<>(doubleConverter, 0.0, doubleFilter));

        STAT_min.setText("10");
        STAT_max.setText("20");
        STAT_avg.setText("12");
        STAT_stddev.setText("2");

        //so focus will start on first editable textfield
        STAT_min.setFocusTraversable(false);
        STAT_max.setFocusTraversable(false);
        STAT_avg.setFocusTraversable(false);
        STAT_stddev.setFocusTraversable(false);

        unitTypeComboBox.getItems().setAll("speed", "length");
        currentUnitComboBox.getItems().setAll("m/s", "ft/s", "mph", "m", "ft", "mi");
        desiredUnitComboBox.getItems().setAll("m/s", "ft/s", "mph", "m", "ft", "mi");


    }


    @FXML
    protected void onCompletedClick() throws IOException {
        //welcomeText.setText("Welcome to JavaFX Application!");

        String title = FIELD_Title.textProperty().getValueSafe();
        double minVal = Double.parseDouble(FIELD_Minimum.textProperty().getValueSafe());
        double maxVal = Double.parseDouble(FIELD_Maximum.textProperty().getValueSafe());
        double greenThreshold = Double.parseDouble(FIELD_GreenT.textProperty().getValueSafe());
        double yellowThreshold = Double.parseDouble(FIELD_YellowT.textProperty().getValueSafe());
        double redThreshold = Double.parseDouble(FIELD_RedT.textProperty().getValueSafe());
        String sAlarm = COMBO_Alarm.getValue();

        if (minVal <= greenThreshold)
        {
            if (greenThreshold <= yellowThreshold)
            {
                if (yellowThreshold <= redThreshold)
                {
                    if (redThreshold <= maxVal)
                    {
                        if (minVal < maxVal)
                        {
                            if (sAlarm != null)
                            {
                                createGauge(title, minVal, maxVal, greenThreshold, yellowThreshold, redThreshold, sAlarm);
                            }
                            else
                            {
                                createGauge(title, minVal, maxVal, greenThreshold, yellowThreshold, redThreshold, "");
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
                    else
                    {
                        //warn user what the problem is
                        Stage popup = DTT_Tools.popup((Stage) BUTTON_Close.getScene().getWindow(), "Red Threshold must be less than the Maximum Value.");
                    }
                }
                else
                {
                    //warn user what the problem is
                    Stage popup = DTT_Tools.popup((Stage) BUTTON_Close.getScene().getWindow(), "Yellow Threshold must be less than the Red Threshold.");
                }
            }
            else
            {
                //warn user what the problem is
                Stage popup = DTT_Tools.popup((Stage) BUTTON_Close.getScene().getWindow(), "Green Threshold must be less than the Yellow Threshold.");
            }
        }
        else
        {
            //warn user what the problem is
            Stage popup = DTT_Tools.popup((Stage) BUTTON_Close.getScene().getWindow(), "Minimum Value must be less than the Green Threshold.");
        }

    }

    private void createGauge(String title, double min, double max, double green, double yellow, double red, String sAlarm)
    {
        ClusterBarGauge newGauge = new ClusterBarGauge();
        newGauge.setTitle(title);

        newGauge.tile.getChartData().get(0).setMaxValue(max);
        newGauge.tile.getChartData().get(0).setMinValue(min);
        newGauge.tile.setMaxValue(max);
        newGauge.tile.setMinValue(min);

        GradientLookup gradient = new GradientLookup(Arrays.asList(
                new Stop(0, Bright.BLUE),
                new Stop(DTT_Tools.map(green,min,max,0,1), Bright.GREEN),
                new Stop(DTT_Tools.map(yellow,min,max,0,1), Bright.YELLOW),
                new Stop(DTT_Tools.map(red,min,max,0,1), Bright.RED),
                new Stop(1, Bright.RED)));

        newGauge.setGradient(gradient);

//        switch(unit)
//        {
//            case "%":
//                newGauge.tile.getChartData().get(0).setFormatString("%.1f" + "%%");
//                break;
//            case "m/s":
//                newGauge.tile.getChartData().get(0).setFormatString("%.1f" + " m/s");
//                break;
//            case "ft":
//                newGauge.tile.getChartData().get(0).setFormatString("%.1f" + " ft");
//                break;
//            case "m":
//                newGauge.tile.getChartData().get(0).setFormatString("%.1f" + " m");
//                break;
//            default:
//                break;
//        }
        switch(sAlarm)
        {
            case "Chirp":
                newGauge.setAlarm(1);
                break;
            case "Siren":
                newGauge.setAlarm(2);
                break;
            case "Scream":
                newGauge.setAlarm(3);
                break;
            default:
                break;
        }
        MainApplication.gauges.add(newGauge);
        Stage stage = (Stage) FIELD_Title.getScene().getWindow();
        stage.close();
    }

    public void setField(NumberField relatedField) {
        field = relatedField;
        FIELD_Title.setText(field.getName());
        STAT_max.setText(String.valueOf(field.getMaxValue()));
        STAT_min.setText(String.valueOf(field.getMinValue()));
        STAT_avg.setText(String.valueOf(field.getMean()));
        STAT_stddev.setText(String.valueOf(field.getStandardDeviation()));
    }

    public NumberField getField() {
        return field;
    }
}