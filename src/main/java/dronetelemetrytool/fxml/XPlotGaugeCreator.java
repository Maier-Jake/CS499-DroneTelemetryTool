package dronetelemetrytool.fxml;

import dronetelemetrytool.DTT_Tools;
import dronetelemetrytool.MainApplication;
import dronetelemetrytool.fieldparsing.NumberField;
import dronetelemetrytool.gauges.ClusterBarGauge;
import dronetelemetrytool.gauges.GaugeOrient;
import dronetelemetrytool.gauges.XPlotGauge;
import dronetelemetrytool.fieldparsing.FieldCollection;
import dronetelemetrytool.fieldparsing.UnitConverter;
import eu.hansolo.tilesfx.colors.Bright;
import eu.hansolo.toolboxfx.GradientLookup;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
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

public class XPlotGaugeCreator implements Initializable {

    private NumberField field;

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
    @FXML
    private Button BUTTON_Unit;
    @FXML
    private ComboBox<String> COMBO_Orient;
    @FXML
    private TextField STAT_max;
    @FXML
    private TextField STAT_min;
    @FXML
    private TextField STAT_avg;
    @FXML
    private TextField STAT_stddev;
    @FXML
    private ComboBox<String> unitTypeComboBox;
    @FXML
    private ComboBox<String> currentUnitComboBox;
    @FXML
    private ComboBox<String> desiredUnitComboBox;

    private UnitConverter uc = new UnitConverter();

    // Converts the current data field to the new units according the current value of the combo boxes.
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

    @FXML
    protected void onCancelClick() {
        System.out.println("Cancelled creating this gauge.");

        //Finished, close this Gauge Creation window.
        Stage stage = (Stage) BUTTON_Close.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void initialize(URL url, ResourceBundle rb) {

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

        String default_unit = this.uc.getUnitNames().get(0);
        List<String> default_subunits = this.uc.getSubunits(default_unit);
        unitTypeComboBox.setItems(FXCollections.observableArrayList(this.uc.getUnitNames()));
        unitTypeComboBox.setValue(default_unit);
        currentUnitComboBox.setItems(FXCollections.observableList(default_subunits));
        currentUnitComboBox.setValue(default_subunits.get(0));
        desiredUnitComboBox.setItems(FXCollections.observableList(default_subunits));
        desiredUnitComboBox.setValue(default_subunits.get(0));
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
    protected void onCompletedClick() throws IOException {
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

    private void createGauge(String title, double min, double max, double tickUnit, String label, GaugeOrient orient)
    {
        XPlotGauge newGauge = new XPlotGauge(orient, min, max, tickUnit);
        newGauge.setField(field);
        newGauge.setTitle(title);
        newGauge.setLabel(label);
        MainApplication.gauges.add(newGauge);
        FieldSelection.addToRight(title);
        Stage stage = (Stage) FIELD_Title.getScene().getWindow();
        stage.close();
    }

    public NumberField getField() {
        return field;
    }
}