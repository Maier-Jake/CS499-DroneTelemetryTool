package dronetelemetrytool.fxml;

import dronetelemetrytool.DTT_Tools;
import dronetelemetrytool.MainApplication;
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
    @FXML
    private Label HEADER;
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


    @FXML // fx:id="COMBO_Format"
    private ComboBox<String> COMBO_Format;


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
        COMBO_Format.getItems().setAll("m/s", "%", "m", "ft");

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
        String format = COMBO_Format.getValue();

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
                            if (format != null)
                            {
                                createGauge(title, minVal, maxVal, greenThreshold, yellowThreshold, redThreshold, format);
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

    private static void createGauge(String title, double min, double max, double green, double yellow, double red, String format)
    {
        ClusterBarGauge newGauge = new ClusterBarGauge();
        newGauge.setTitle(title);

        GradientLookup gradient = new GradientLookup(Arrays.asList(
                new Stop(0.0, Bright.BLUE),
                new Stop(DTT_Tools.normalize(min, max, green), Bright.GREEN),
                new Stop(DTT_Tools.normalize(min, max, yellow), Bright.YELLOW),
                new Stop(DTT_Tools.normalize(min, max, red), Bright.RED)));

        newGauge.setGradient(gradient);

        newGauge.tile.getChartData().get(0).setMaxValue(max);
        newGauge.tile.getChartData().get(0).setMinValue(min);

        switch(format)
        {
            case "%":
                newGauge.tile.getChartData().get(0).setFormatString("%.1f" + "%%");
                break;
            case "m/s":
                newGauge.tile.getChartData().get(0).setFormatString("%.1f" + " m/s");
                break;
            case "ft":
                newGauge.tile.getChartData().get(0).setFormatString("%.1f" + " ft");
                break;
            case "m":
                newGauge.tile.getChartData().get(0).setFormatString("%.1f" + " m");
                break;
            default:
                break;
        }


        MainApplication.gauges.add(newGauge);
        newGauge.display();
    }
}