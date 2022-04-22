package dronetelemetrytool.fxml;

import dronetelemetrytool.DTT_GUI;
import dronetelemetrytool.DTT_Tools;
import dronetelemetrytool.MainApplication;
import dronetelemetrytool.fieldparsing.Field;
import dronetelemetrytool.fieldparsing.NumberField;
import dronetelemetrytool.gauges.Gauge;
import dronetelemetrytool.gauges.XYPlotGauge;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.stage.Stage;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class FieldSelection implements Initializable {

    @FXML
    public ListView leftView;

    @FXML
    public ListView rightView;
    @FXML
    public Button buttonCreate;
    @FXML
    public Button buttonRemove;
    @FXML
    public Button buttonContinue;

    @FXML
    public TextField searchBar;

    public static ObservableList<String> leftFields = FXCollections.observableArrayList();
    public static ObservableList<String> rightFields = FXCollections.observableArrayList();
    FilteredList<String> leftFilter = new FilteredList<String>(leftFields, s -> true);

    @FXML
    protected void onCreateClick() throws IOException {

        ObservableList<Integer> indices = leftView.getSelectionModel().getSelectedIndices();

        //if (selected > 0 , and selected + already made <= 10)
        if (indices.size() > 2)
        {
            //error because we cannot create any gauges from >2 fields.
            Stage popup = DTT_Tools.popup((Stage) buttonCreate.getScene().getWindow(), "Cannot create any valid gauges from > 2 fields.");
        }
        else
        {
            Stage parent = (Stage) buttonCreate.getScene().getWindow();
            if (indices.size() > 0) {
                if (rightFields.size() < 10) {
                    ArrayList<String> items = new ArrayList<>();
                    for (int i = 0; i < indices.size(); i++) {
                        items.add(leftFilter.get(indices.get(i)));
                    }
                    if(items.size() == 1) {
                        //creating gauge w/ 1 field
                        String fieldName = items.get(0);
                        Field relatedField = null;
                        for (Field f : MainApplication.fields.getFields()) {
                            if (f.getName() == fieldName)
                            {
                                relatedField = f;
                            }
                        }
                        if (relatedField != null)
                        {
                            DTT_GUI.gaugeSelector(parent, relatedField);
                            leftFields.remove(fieldName);
                        }
                        else
                        {
                            System.out.println("Error... field not found");
                        }
                    }
                    else {
                        //creating gauge w/ 2 fields
                        //both have to be number fields.
                        String field1Name = items.get(0);
                        String field2Name = items.get(1);
                        Field relatedXField = null;
                        Field relatedYField = null;
                        for (Field f : MainApplication.fields.getFields()) {
                            if (f.getName() == field1Name)
                            {
                                relatedXField = f;
                            }
                            if (f.getName() == field2Name)
                            {
                                relatedYField = f;
                            }
                        }
                        if (relatedXField != null && relatedYField != null)
                        {
                            if (relatedXField.getType() == 0 && relatedYField.getType() == 0)
                            {
                                DTT_GUI.xyPlotGaugeCreator(parent, new NumberField(relatedXField), new NumberField(relatedYField));
                                leftFields.remove(field1Name);
                                leftFields.remove(field2Name);
                            }
                            else
                            {
                                Stage popup = DTT_Tools.popup((Stage) buttonCreate.getScene().getWindow(), "To create a gauge with two fields, they must both be number fields.");
                            }
                        }
                        else
                        {
                            System.out.println("Error... field(s) not found");
                        }
                    }
                }
                else {
                    //error because we cannot create any more gauges
                    Stage popup = DTT_Tools.popup((Stage) buttonCreate.getScene().getWindow(), "Cannot create more than 10 gauges.");
                }
            }
            else {
                //error because nothing selected
                Stage popup = DTT_Tools.popup((Stage) buttonCreate.getScene().getWindow(), "No field(s) selected.");
            }
//            if (indices.size() > 0 && (indices.size() + rightSet.size()) <= 10) {
//                ArrayList<String> items = new ArrayList<>();
//                for (int i = 0; i < indices.size(); i++) {
//                    items.add(leftSet.get(indices.get(i)));
//                }
//                for (int i = 0; i < indices.size(); i++) {
//                    rightSet.add(items.get(i));
//                    leftSet.remove(items.get(i));
//                }
//                leftFields.setAll(leftSet);
//                rightFields.setAll(rightSet);
//                items.clear();
//                leftView.getSelectionModel().clearSelection();
//            }
        }
    }

    @FXML
    protected void onRemoveClick() {
//        indices = rightView.getSelectionModel().getSelectedIndices();
//        if ( indices.size() > 0 ) {
//            ArrayList<String> items = new ArrayList<>();
//            for (int i = 0; i < indices.size(); i++) {
//                items.add(rightSet.get(indices.get(i)));
//            }
//            for (int i = 0; i < indices.size(); i++) {
//                leftSet.add(items.get(i));
//                rightSet.remove(items.get(i));
//            }
//            leftFields.setAll(leftSet);
//            rightFields.setAll(rightSet);
//            items.clear();
//            rightView.getSelectionModel().clearSelection();
//        }

        // Gets the selected index in the right list
        int index = rightView.getSelectionModel().getSelectedIndex();
        String name = rightView.getSelectionModel().getSelectedItem().toString();
        ObservableList<Integer> indices = rightView.getSelectionModel().getSelectedIndices();
        ArrayList<String> items = new ArrayList<>();

        for (int i = 0; i < indices.size(); i++) {
            items.add(rightFields.get(indices.get(i)));
        }
        for (String s:items ) {
            for (Gauge g:MainApplication.gauges) {
                if (g.tile.getTitle() == s) {
                    if (MainApplication.gauges.get(index) instanceof XYPlotGauge) {
                        leftFields.add(((XYPlotGauge) g).getxField().getName());
                        leftFields.add(((XYPlotGauge) g).getyField().getName());
                    }
                    else {
                        leftFields.add(g.getField().getName());
                    }
                    rightFields.remove(s);
                }
            }
        }
    }

    @FXML
    protected void onSaveClick() {

    }

    @FXML
    protected void onContinueClick() throws IOException {
        for (Gauge g : MainApplication.gauges)
        {
            g.display();
        }
//        MainApplication.timer.start();
        ((Stage) buttonContinue.getScene().getWindow()).close();

        DTT_GUI.videoPlayer();

    }

    @FXML
    public void initialize(URL url, ResourceBundle rb) {

        for (String s: MainApplication.fields.getHeaders()) {
            leftFields.add(s);
        }

        leftView.setItems(leftFilter);
        leftView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        rightView.setItems(rightFields);
        rightView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        searchBar.textProperty().addListener(obs->{
            String filter = searchBar.getText();
            if(filter == null || filter.length() == 0) {
                leftFilter.setPredicate(s -> true);
            }
            else {
                leftFilter.setPredicate(s -> s.toLowerCase().contains(filter.toLowerCase()));
            }
        });
    }

    public static void addToRight(String gaugeName)
    {
        rightFields.add(gaugeName);

    }
}
