package dronetelemetrytool.fxml;

import dronetelemetrytool.DTT_GUI;
import dronetelemetrytool.DTT_Tools;
import dronetelemetrytool.MainApplication;
import dronetelemetrytool.fieldparsing.Field;
import dronetelemetrytool.fieldparsing.NumberField;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.control.cell.ComboBoxListCell;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Set;

public class FieldSelection implements Initializable {

    @FXML
    public ListView leftView;

    @FXML
    public ListView rightView;
    public Button createButton;
    public Button removeButton;

    @FXML
    public TextField SearchBar;

    ArrayList<String> leftSet = new ArrayList<>();
    ObservableList<String> leftFields = FXCollections.observableArrayList();
    String Filter = new String();
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
            Stage popup = DTT_Tools.popup((Stage) createButton.getScene().getWindow(), "Cannot create any valid gauges from > 2 fields.");
        }
        else
        {
            if (indices.size() > 0) {
                if (rightSet.size() < 10) {
                    ArrayList<String> items = new ArrayList<>();
                    for (int i = 0; i < indices.size(); i++) {
                        items.add(leftSet.get(indices.get(i)));
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
                            DTT_GUI.gaugeSelector(relatedField);
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
                    Stage popup = DTT_Tools.popup((Stage) createButton.getScene().getWindow(), "Cannot create more than 10 gauges.");
                }
            }
            else {
                //error because nothing selected
                Stage popup = DTT_Tools.popup((Stage) createButton.getScene().getWindow(), "No field(s) selected.");
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
    protected void onContinueClick() {

    }

    @FXML
    public void initialize(URL url, ResourceBundle rb) {

        for (String s: MainApplication.fields.getHeaders()) {
            leftSet.add(s);
        }

//        leftSet.add("String 1");
//        leftSet.add("String 2");
//        leftSet.add("String 3");
//        leftSet.add("String 4");
//        leftSet.add("String 5");
//        leftSet.add("String 6");
//        leftSet.add("String 7");
//        leftSet.add("String 8");
//        leftSet.add("String 9");
//        leftSet.add("String 10");
//        leftSet.add("String 11");
//        leftSet.add("String 12");

        leftFields.setAll(leftSet);
        leftView.setItems(leftFilter);
        leftView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);


        rightFields.setAll(rightSet);
        rightView.setItems(rightFields);
        rightView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        SearchBar.textProperty().addListener(obs->{
            String filter = SearchBar.getText();
            if(filter == null || filter.length() == 0) {
                leftFilter.setPredicate(s -> true);
            }
            else {
                leftFilter.setPredicate(s -> s.contains(filter));
            }
        });
    }
}
