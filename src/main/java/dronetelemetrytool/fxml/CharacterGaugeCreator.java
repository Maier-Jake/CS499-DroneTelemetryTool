package dronetelemetrytool.fxml;

import dronetelemetrytool.MainApplication;
import dronetelemetrytool.fieldparsing.NumberField;
import dronetelemetrytool.fieldparsing.StringField;
import dronetelemetrytool.gauges.CharacterGauge;
import dronetelemetrytool.gauges.OnOffGauge;
import eu.hansolo.tilesfx.colors.Bright;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CharacterGaugeCreator implements Initializable {

    private StringField field;

    @FXML
    private TextField FIELD_Title;
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

    }


    @FXML
    protected void onCompletedClick() throws IOException {
        //Nothing to verify, the user can't mess this one up.
        createGauge();

        //Finished, close this Gauge Creation window.
        Stage stage = (Stage) BUTTON_Close.getScene().getWindow();
        stage.close();
    }

    private void createGauge()
    {
        String title = FIELD_Title.textProperty().getValueSafe();
        CharacterGauge newGauge = new CharacterGauge();
        newGauge.setField(field);
        newGauge.setTitle(title);

        MainApplication.gauges.add(newGauge);
        FieldSelection.addToRight(title);
        Stage stage = (Stage) FIELD_Title.getScene().getWindow();
        stage.close();
    }

    public void setField(StringField relatedField) {
        field = relatedField;
        FIELD_Title.setText(field.getName());

    }

    public StringField getField() {
        return field;
    }
}