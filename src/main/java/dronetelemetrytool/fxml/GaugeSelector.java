package dronetelemetrytool.fxml;

import dronetelemetrytool.DTT_GUI;
import dronetelemetrytool.fieldparsing.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GaugeSelector implements Initializable {

    private Field field;

    @FXML
    public Button buttonTimestamp;
    @FXML
    public Button buttonXYPlot;
    @FXML
    public Button buttonXPlot;
    @FXML
    public Button buttonBool;
    @FXML
    public Button buttonCharacter;
    @FXML
    public Button buttonText;
    @FXML
    public Button buttonBar;
    @FXML
    public Button buttonClock;
    @FXML
    public Button buttonCircle360;
    @FXML
    public Button buttonCircle270;
    @FXML
    public Button buttonCircle180;
    @FXML
    public Button buttonCircle90;
    @FXML
    public Label fieldName;

    @FXML
    public Button buttonClose;

    @FXML
    protected void onCancelClick() {

    }
    @FXML
    protected void circle90Click() throws IOException {
        DTT_GUI.circle90GaugeCreator(new NumberField(field));
    }
    @FXML
    protected void circle180Click() throws IOException {
        DTT_GUI.circle180GaugeCreator(new NumberField(field));
    }
    @FXML
    protected void circle270Click() throws IOException {
        DTT_GUI.circle270GaugeCreator(new NumberField(field));
    }
    @FXML
    protected void circle360Click() throws IOException {
        DTT_GUI.circle360GaugeCreator(new NumberField(field));
    }
    @FXML
    protected void barClick() throws IOException {
        DTT_GUI.barGaugeCreator(new NumberField(field));
    }
    @FXML
    protected void timestampClick() throws IOException {
        DTT_GUI.timestampGaugeCreator(new TimeField(field));
    }
    @FXML
    protected void textClick() throws IOException {
        DTT_GUI.textGaugeCreator(new StringField(field));
    }
    @FXML
    protected void characterClick() throws IOException {
        DTT_GUI.characterGaugeCreator(new StringField(field));
    }
    @FXML
    protected void xPlotClick() throws IOException {
        DTT_GUI.xPlotGaugeCreator(new NumberField(field));
    }

    // todo: add second field to xyPlotClock
    @FXML
    protected void xyPlotClick() throws IOException {
        DTT_GUI.xyPlotGaugeCreator(new NumberField(field));
    }
    @FXML
    protected void clockClick() throws IOException {
        DTT_GUI.clockGaugeCreator(new NumberField(field));
    }
    @FXML
    protected void boolClick() throws IOException {
        DTT_GUI.onOffGaugeCreator(new BoolField(field));
    }

    public void limitOptions()
    {
        if (field != null)
        {
            fieldName.setText(field.getName());

            switch (field.getType())
            {
                case 0:
                    //number field;
//                    buttonCircle90.setDisable(true);
//                    buttonCircle180.setDisable(true);
//                    buttonCircle270.setDisable(true);
//                    buttonCircle360.setDisable(true);
                    buttonClock.setDisable(true);
//                    buttonBar.setDisable(true);
//                    buttonText.setDisable(true);
                    buttonCharacter.setDisable(true);
                    buttonBool.setDisable(true);
//                    buttonXPlot.setDisable(true);
                    buttonXYPlot.setDisable(true);
                    buttonTimestamp.setDisable(true);
                    break;
                case 1:
                    //time field;
                    buttonCircle90.setDisable(true);
                    buttonCircle180.setDisable(true);
                    buttonCircle270.setDisable(true);
                    buttonCircle360.setDisable(true);
//                    buttonClock.setDisable(true);
                    buttonBar.setDisable(true);
//                    buttonText.setDisable(true);
                    buttonCharacter.setDisable(true);
                    buttonBool.setDisable(true);
                    buttonXPlot.setDisable(true);
                    buttonXYPlot.setDisable(true);
//                    buttonTimestamp.setDisable(true);
                    break;
                case 2:
                    //boolean field;
                    buttonCircle90.setDisable(true);
                    buttonCircle180.setDisable(true);
                    buttonCircle270.setDisable(true);
                    buttonCircle360.setDisable(true);
                    buttonClock.setDisable(true);
                    buttonBar.setDisable(true);
//                    buttonText.setDisable(true);
                    buttonCharacter.setDisable(true);
//                    buttonBool.setDisable(true);
                    buttonXPlot.setDisable(true);
                    buttonXYPlot.setDisable(true);
                    buttonTimestamp.setDisable(true);

                    break;
                case 3:
                    //string field;
                    buttonCircle90.setDisable(true);
                    buttonCircle180.setDisable(true);
                    buttonCircle270.setDisable(true);
                    buttonCircle360.setDisable(true);
                    buttonClock.setDisable(true);
                    buttonBar.setDisable(true);
//                    buttonText.setDisable(true);
                    buttonCharacter.setDisable(true);
                    buttonBool.setDisable(true);
                    buttonXPlot.setDisable(true);
                    buttonXYPlot.setDisable(true);
                    buttonTimestamp.setDisable(true);
                    break;
                case 4:
                    //null field;
                    buttonCircle90.setDisable(true);
                    buttonCircle180.setDisable(true);
                    buttonCircle270.setDisable(true);
                    buttonCircle360.setDisable(true);
                    buttonClock.setDisable(true);
                    buttonBar.setDisable(true);
                    buttonText.setDisable(true);
                    buttonCharacter.setDisable(true);
                    buttonBool.setDisable(true);
                    buttonXPlot.setDisable(true);
                    buttonXYPlot.setDisable(true);
                    buttonTimestamp.setDisable(true);
                    break;
                default:
                    //fieldName not found in saved FieldCollection
                    break;
            }
        }
    }

    @FXML
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    protected void onCompletedClick() throws IOException {

    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }
}