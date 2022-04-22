package dronetelemetrytool.fxml;

import dronetelemetrytool.DTT_GUI;
import dronetelemetrytool.DTT_Tools;
import dronetelemetrytool.MainApplication;
import dronetelemetrytool.fieldparsing.Field;
import dronetelemetrytool.fieldparsing.FieldCollection;
import dronetelemetrytool.fieldparsing.TimeField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.media.Media;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

public class FrequencySelector implements Initializable {

//    @FXML
//    public Button continueButton;
    @FXML
    public ListView listView;
    @FXML
    public TextField frequencyINPUT;

    ArrayList<String> listSet = new ArrayList<>();
    ObservableList<String> listFields = FXCollections.observableArrayList();

    @FXML
    protected void onContinueClick() throws IOException {
        Stage stage = (Stage) listView.getScene().getWindow();
        stage.close();
        DTT_GUI.fieldSelection();
    }

    @FXML
    public void initialize() throws FileNotFoundException {

        Pattern validEditingState = Pattern.compile("-?(([1-9][0-9]*)|0)?(\\.[0-9]*)?");
        UnaryOperator<TextFormatter.Change> floatFilter = c -> {
            String text = c.getControlNewText();
            if (validEditingState.matcher(text).matches()) {
                return c ;
            } else {
                return null ;
            }
        };
        StringConverter<Float> floatConverter = new StringConverter<Float>() {
            @Override
            public Float fromString(String s) {
                if (s.isEmpty() || "-".equals(s) || ".".equals(s) || "-.".equals(s)) {
                    return 0.0f ;
                } else {
                    return Float.valueOf(s);
                }
            }
            @Override
            public String toString(Float f) {
                return f.toString();
            }
        };

        frequencyINPUT.setTextFormatter(new TextFormatter<>(floatConverter, 0.0f, floatFilter));
    }

    @FXML
    protected void onSelectFreqClick() throws IOException {
        ObservableList<Integer> indices = listView.getSelectionModel().getSelectedIndices();
        if (indices.size() == 1) {
            //creating gauge w/ 1 field
            String fieldName = (String) listView.getSelectionModel().getSelectedItem();
            TimeField relatedField = null;
            for (TimeField f : MainApplication.fields.getTimeFields()) {
                if (f.getName() == fieldName)
                {
                    relatedField = f;
                }
            }
            if (relatedField != null)
            {
                MainApplication.timestampField = relatedField;
                MainApplication.code = 1;
//                MainApplication.prevTime = MainApplication.timestampField.getNext();
//                MainApplication.currentTime = MainApplication.timestampField.getNext();
                DTT_GUI.setupSelector();
                Stage stage = (Stage) listView.getScene().getWindow();
                stage.close();
            }
            else
            {
                System.out.println("Error... field not found");
            }
        }
        else {
            //error because nothing selected
            Stage popup = DTT_Tools.popup((Stage) listView.getScene().getWindow(), "Select ONE field.");
        }
    }

    @FXML
    protected void onCustomFreqClick() throws IOException {
        if (frequencyINPUT.getText() != "")
        {
            try{
                MainApplication.setFrequency(Float.parseFloat(frequencyINPUT.getText()));
                MainApplication.code = 0;

//                MainApplication.gaugeUpdateFrequency = 1_000_000_000 * MainApplication.frequency;
//                DTT_GUI.fieldSelection();
                DTT_GUI.setupSelector();
                Stage stage = (Stage) listView.getScene().getWindow();
                stage.close();
            } catch (NumberFormatException e)
            {
                //error because not a number was inputed.
                Stage popup = DTT_Tools.popup((Stage) listView.getScene().getWindow(), "Not a valid float.");
            }
        }
        else
        {
            //error because nothing was input
            Stage popup = DTT_Tools.popup((Stage) listView.getScene().getWindow(), "Not a valid float.");
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //get only time fields
        for (TimeField f : MainApplication.fields.getTimeFields())
        {
            listSet.add(f.getName());
        }

        listFields.setAll(listSet);
        listView.setItems(listFields);
        listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }
}