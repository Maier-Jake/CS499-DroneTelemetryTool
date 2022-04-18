package dronetelemetrytool.fxml;

import dronetelemetrytool.DTT_GUI;
import dronetelemetrytool.DTT_Tools;
import dronetelemetrytool.MainApplication;
import dronetelemetrytool.fieldparsing.Field;
import dronetelemetrytool.gauges.Gauge;
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

    ArrayList<String> leftSet = new ArrayList<>();
    ObservableList<String> leftFields = FXCollections.observableArrayList();
    FilteredList<String> leftFilter = new FilteredList<String>(leftFields, s -> true);
    ArrayList<String> rightSet = new ArrayList<>();
    ObservableList<String> rightFields = FXCollections.observableArrayList();
    ObservableList<Integer> indices;


    @FXML
    protected void onCreateClick() throws IOException {

        // Checks if right list length is <10
        indices = leftView.getSelectionModel().getSelectedIndices();
//        System.out.println(indices.size());
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
                if (rightSet.size() < 10) {
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
                        }
                        else
                        {
                            System.out.println("Error... field not found");
                        }
                    }
                    else {
                        //creating gauge w/ 2 fields
                        //both have to be number fields.
                        Field field1;
                        Field field2;
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
        MainApplication.timer.start();
        ((Stage) buttonContinue.getScene().getWindow()).close();

        DTT_GUI.videoPlayer();

    }

    @FXML
    public void initialize(URL url, ResourceBundle rb) {

        for (String s: MainApplication.fields.getHeaders()) {
            leftSet.add(s);
        }

        leftFields.setAll(leftSet);
        leftView.setItems(leftFilter);
        leftView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        rightSet.add("Altitude");
        rightSet.add("xSpeed");
        rightSet.add("ySpeed");
        rightSet.add("Timestamp");
        rightSet.add("Battery Life");
        rightSet.add("Is propeller Catapult?");

        rightFields.setAll(rightSet);
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

    public void addToRightSet(String gaugeName)
    {
        //add to the right set

    }
}
