package dronetelemetrytool.fxml;

import dronetelemetrytool.DTT_Tools;
import dronetelemetrytool.MainApplication;
import dronetelemetrytool.fieldparsing.NumberField;
import dronetelemetrytool.fieldparsing.UnitConverter;
import dronetelemetrytool.gauges.GaugeOrient;
import dronetelemetrytool.gauges.XPlotGauge;
import dronetelemetrytool.gauges.XYPlotGauge;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

public class XYPlotGaugeCreator implements Initializable {
    private UnitConverter uc = new UnitConverter();
    private NumberField xField;
    private NumberField yField;

    @FXML
    private Label xStatLabel;
    @FXML
    private Label yStatLabel;
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
    private Button BUTTON_UnitX;
    @FXML
    private Button BUTTON_UnitY;
    @FXML
    private TextField STAT_max;
    @FXML
    private TextField STAT_min;
    @FXML
    private TextField STAT_avg;
    @FXML
    private TextField STAT_stddev;
    @FXML
    private TextField STAT_max2;
    @FXML
    private TextField STAT_min2;
    @FXML
    private TextField STAT_avg2;
    @FXML
    private TextField STAT_stddev2;
    @FXML
    private ComboBox<String> unitTypeComboBox;
    @FXML
    private ComboBox<String> currentUnitComboBox;
    @FXML
    private ComboBox<String> desiredUnitComboBox;
    @FXML
    private ComboBox<String> unitTypeComboBox2;
    @FXML
    private ComboBox<String> currentUnitComboBox2;
    @FXML
    private ComboBox<String> desiredUnitComboBox2;

    @FXML
    protected void onUnitXChangeClick() {
        String cur = currentUnitComboBox.getValue();
        String des = desiredUnitComboBox.getValue();
        if (cur==this.xField.originalUnit || des==this.xField.chosenUnit || cur==des) { return; }
        this.xField.convert(unitTypeComboBox.getValue(), currentUnitComboBox.getValue(), desiredUnitComboBox.getValue());
        this.updatexStats();
    }

    @FXML
    protected void onNewXUnitType() {
        String newType = unitTypeComboBox.getValue();
        ObservableList<String> newSubunits = FXCollections.observableArrayList(xField.uc.getSubunits(newType));
        currentUnitComboBox.setItems(newSubunits);
        currentUnitComboBox.setValue(newSubunits.get(0));
        desiredUnitComboBox.setItems(newSubunits);
        desiredUnitComboBox.setValue(newSubunits.get(0));
    }

    @FXML
    protected void onNewYUnitType() {
        String newType = unitTypeComboBox2.getValue();
        ObservableList<String> newSubunits = FXCollections.observableArrayList(yField.uc.getSubunits(newType));
        currentUnitComboBox2.setItems(newSubunits);
        currentUnitComboBox2.setValue(newSubunits.get(0));
        desiredUnitComboBox2.setItems(newSubunits);
        desiredUnitComboBox2.setValue(newSubunits.get(0));
    }


    public void setxField(NumberField relatedField) {
        xField = relatedField;
        xStatLabel.setText(xField.getName());
        FIELD_XLabel.setText(xField.getName());
        FIELD_Title.setText(xField.getName());
        updatexStats();
    }

    void updatexStats() {
        STAT_max.setText(String.valueOf(xField.getMaxValue()));
        STAT_min.setText(String.valueOf(xField.getMinValue()));
        STAT_avg.setText(String.valueOf(xField.getMean()));
        STAT_stddev.setText(String.valueOf(xField.getStandardDeviation()));
    }

    @FXML
    protected void onUnitYChangeClick() {
        String cur = currentUnitComboBox2.getValue();
        String des = desiredUnitComboBox2.getValue();
        if (cur==this.yField.originalUnit || des==this.yField.chosenUnit || cur==des) { return; }
        this.yField.convert(unitTypeComboBox2.getValue(), currentUnitComboBox.getValue(), desiredUnitComboBox.getValue());
        this.updateyStats();
    }

    public void setyField(NumberField relatedField) {
        yField = relatedField;
        yStatLabel.setText(yField.getName());
        FIELD_YLabel.setText(yField.getName());
        FIELD_Title.setText(yField.getName());
        updateyStats();
    }

    void updateyStats() {
        STAT_max2.setText(String.valueOf(yField.getMaxValue()));
        STAT_min2.setText(String.valueOf(yField.getMinValue()));
        STAT_avg2.setText(String.valueOf(yField.getMean()));
        STAT_stddev2.setText(String.valueOf(yField.getStandardDeviation()));
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

        String default_unit = this.uc.getUnitNames().get(0);
        List<String> default_subunits = this.uc.getSubunits(default_unit);

        unitTypeComboBox.setItems(FXCollections.observableArrayList(this.uc.getUnitNames()));
        unitTypeComboBox.setValue(default_unit);
        currentUnitComboBox.setItems(FXCollections.observableList(default_subunits));
        currentUnitComboBox.setValue(default_subunits.get(0));
        desiredUnitComboBox.setItems(FXCollections.observableList(default_subunits));
        desiredUnitComboBox.setValue(default_subunits.get(0));

        unitTypeComboBox2.setItems(FXCollections.observableArrayList(this.uc.getUnitNames()));
        unitTypeComboBox2.setValue(default_unit);
        currentUnitComboBox2.setItems(FXCollections.observableList(default_subunits));
        currentUnitComboBox2.setValue(default_subunits.get(0));
        desiredUnitComboBox2.setItems(FXCollections.observableList(default_subunits));
        desiredUnitComboBox2.setValue(default_subunits.get(0));

        //so focus will start on first editable textfield
        STAT_min.setFocusTraversable(false);
        STAT_max.setFocusTraversable(false);
        STAT_avg.setFocusTraversable(false);
        STAT_stddev.setFocusTraversable(false);

        //so focus will start on first editable textfield
        STAT_min2.setFocusTraversable(false);
        STAT_max2.setFocusTraversable(false);
        STAT_avg2.setFocusTraversable(false);
        STAT_stddev2.setFocusTraversable(false);
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

    private void createGauge(String title, double xMin, double xMax, double xTick, String xLabel, double yMin, double yMax, double yTick, String yLabel)
    {
        XYPlotGauge newGauge = new XYPlotGauge(xMin, xMax, xTick, yMin, yMax, yTick);
        newGauge.setTitle(title);
        newGauge.setXLabel(xLabel);
        newGauge.setYLabel(yLabel);
        newGauge.setxField(xField);
        newGauge.setyField(yField);

        MainApplication.gauges.add(newGauge);
        FieldSelection.addToRight(title);
        Stage stage = (Stage) FIELD_Title.getScene().getWindow();
        stage.close();
    }

    public NumberField getxField() {
        return xField;
    }

    public NumberField getyField() {
        return yField;
    }

}