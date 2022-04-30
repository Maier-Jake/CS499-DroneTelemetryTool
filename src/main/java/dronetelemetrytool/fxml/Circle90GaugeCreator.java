package dronetelemetrytool.fxml;

import dronetelemetrytool.DTT_Tools;
import dronetelemetrytool.MainApplication;
import dronetelemetrytool.fieldparsing.NumberField;
import dronetelemetrytool.fieldparsing.UnitConverter;
import dronetelemetrytool.gauges.CircleGauge;
import eu.hansolo.tilesfx.colors.Bright;
import eu.hansolo.toolboxfx.GradientLookup;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Stop;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

public class Circle90GaugeCreator implements Initializable {

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
    private Button BUTTON_Unit;
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
    private UnitConverter uc = new UnitConverter();


    @FXML
    protected void onUnitChangeClick() {
        String cur = currentUnitComboBox.getValue();
        String des = desiredUnitComboBox.getValue();
        if (cur==this.field.originalUnit || des==this.field.chosenUnit || cur==des) { return; }
        this.field.convert(unitTypeComboBox.getValue(), currentUnitComboBox.getValue(), desiredUnitComboBox.getValue());
        this.updateStats();
    }

    public void setField(NumberField relatedField) {
        field = relatedField;
        FIELD_Title.setText(field.getName());
        updateStats();
    }

    void updateStats() {
        STAT_max.setText(String.valueOf(field.getMaxValue()));
        STAT_min.setText(String.valueOf(field.getMinValue()));
        STAT_avg.setText(String.valueOf(field.getMean()));
        STAT_stddev.setText(String.valueOf(field.getStandardDeviation()));
    }

    // Called when a new unit type (like "distance") is chosen in the dropdown.
    // Updates the current and desired subunit type dropdowns to include the appropriate options.
    @FXML
    protected void onNewUnitType() {
        String newType = unitTypeComboBox.getValue();
        ObservableList<String> newSubunits = FXCollections.observableArrayList(field.uc.getSubunits(newType));
        currentUnitComboBox.setItems(newSubunits);
        currentUnitComboBox.setValue(newSubunits.get(0));
        desiredUnitComboBox.setItems(newSubunits);
        desiredUnitComboBox.setValue(newSubunits.get(0));
    }

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

        String default_unit = this.uc.getUnitNames().get(0);
        List<String> default_subunits = this.uc.getSubunits(default_unit);
        unitTypeComboBox.setItems(FXCollections.observableArrayList(this.uc.getUnitNames()));
        unitTypeComboBox.setValue(default_unit);
        currentUnitComboBox.setItems(FXCollections.observableList(default_subunits));
        currentUnitComboBox.setValue(default_subunits.get(0));
        desiredUnitComboBox.setItems(FXCollections.observableList(default_subunits));
        desiredUnitComboBox.setValue(default_subunits.get(0));

        FIELD_Minimum.setTextFormatter(new TextFormatter<>(doubleConverter, 0.0, doubleFilter));
        FIELD_Maximum.setTextFormatter(new TextFormatter<>(doubleConverter, 0.0, doubleFilter));
        FIELD_GreenT.setTextFormatter(new TextFormatter<>(doubleConverter, 0.0, doubleFilter));
        FIELD_YellowT.setTextFormatter(new TextFormatter<>(doubleConverter, 0.0, doubleFilter));
        FIELD_RedT.setTextFormatter(new TextFormatter<>(doubleConverter, 0.0, doubleFilter));

        //so focus will start on first editable textfield
        STAT_min.setFocusTraversable(false);
        STAT_max.setFocusTraversable(false);
        STAT_avg.setFocusTraversable(false);
        STAT_stddev.setFocusTraversable(false);
    }


    @FXML
    protected void onCompletedClick() throws IOException {

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
        CircleGauge newGauge = new CircleGauge(90);
        newGauge.setField(field);
        newGauge.setTitle(title);

        newGauge.tile.setMaxValue(max);
        newGauge.tile.setMinValue(min);

        GradientLookup gradient = new GradientLookup(Arrays.asList(
                new Stop(0, Bright.BLUE),
                new Stop(DTT_Tools.map(green,min,max,0,1), Bright.GREEN),
                new Stop(DTT_Tools.map(yellow,min,max,0,1), Bright.YELLOW),
                new Stop(DTT_Tools.map(red,min,max,0,1), Bright.RED),
                new Stop(1, Bright.RED)));

        newGauge.setGradient(gradient);

//        switch(format)
//        {
//            case "%":
//                newGauge.tile.setUnit("%");
//                break;
//            case "m/s":
//                newGauge.tile.setUnit("m/s");
//                break;
//            case "ft":
//                newGauge.tile.setUnit("ft");
//                break;
//            case "m":
//                newGauge.tile.setUnit("m");
//                break;
//            default:
//                newGauge.tile.setUnit("");
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
        FieldSelection.addToRight(title);
        Stage stage = (Stage) FIELD_Title.getScene().getWindow();
        stage.close();
    }

    public NumberField getField() {
        return field;
    }
}
