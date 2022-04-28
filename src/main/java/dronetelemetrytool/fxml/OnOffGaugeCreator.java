package dronetelemetrytool.fxml;

import dronetelemetrytool.MainApplication;
import dronetelemetrytool.fieldparsing.BoolField;
import dronetelemetrytool.gauges.OnOffGauge;
import eu.hansolo.tilesfx.colors.Bright;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

public class OnOffGaugeCreator implements Initializable {

    private BoolField field;

    @FXML
    private TextField FIELD_Title;
    @FXML
    private ColorPicker COLOR_on;
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
        COLOR_on.setValue(Bright.GREEN);
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
        OnOffGauge newGauge = new OnOffGauge();
        newGauge.setField(field);
        newGauge.setTitle(title);

        Color on = COLOR_on.getValue();
        newGauge.tile.setActiveColor(on);

        MainApplication.gauges.add(newGauge);
        FieldSelection.addToRight(title);
        Stage stage = (Stage) FIELD_Title.getScene().getWindow();
        stage.close();
    }

    public void setField(BoolField field) {
        this.field = field;
    }
}