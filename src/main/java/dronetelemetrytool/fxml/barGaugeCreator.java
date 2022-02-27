package dronetelemetrytool.fxml;

import dronetelemetrytool.DTT_Tools;
import dronetelemetrytool.MainApplication;
import dronetelemetrytool.gauges.ClusterBarGauge;
import eu.hansolo.tilesfx.colors.Bright;
import eu.hansolo.toolboxfx.GradientLookup;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.paint.Stop;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.StringConverter;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

public class barGaugeCreator implements Initializable {
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
    protected void onCancelClick() {
        //welcomeText.setText("Welcome to JavaFX Application!");
        System.out.println("Cancelled creating this gauge.");
    }

    @FXML
    public void initialize(URL url, ResourceBundle rb) {

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
                            createBarGauge(title, minVal, maxVal, greenThreshold, yellowThreshold, redThreshold);
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

    private void createBarGauge(String title, double min, double max, double green, double yellow, double red)
    {
        System.out.println("Heyo");
        ClusterBarGauge newGauge = new ClusterBarGauge(title);

        GradientLookup gradient = new GradientLookup(Arrays.asList(
                new Stop(0.0, Bright.BLUE),
                new Stop(DTT_Tools.normalize(min, max, green), Bright.GREEN),
                new Stop(DTT_Tools.normalize(min, max, yellow), Bright.YELLOW),
                new Stop(DTT_Tools.normalize(min, max, red), Bright.RED)));

        newGauge.setGradient(gradient);

        newGauge.tile.getChartData().get(0).setMaxValue(max);
        newGauge.tile.getChartData().get(0).setMinValue(min);

        MainApplication.gauges.add(newGauge);
        newGauge.display();
    }
}