package dronetelemetrytool.fxml;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.control.cell.ComboBoxListCell;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Callback;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Set;

public class FieldSelection implements Initializable {

    @FXML
    public ListView leftView;

    @FXML
    public ListView rightView;

    @FXML
    protected void onCreateClick() {

    }

    @FXML
    protected void onRemoveClick() {

    }

    @FXML
    protected void onSaveClick() {

    }

    @FXML
    protected void onContinueClick() {

    }

    @FXML
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> leftFields = FXCollections.observableArrayList();

        ArrayList<String> leftSet = new ArrayList<>();
        leftSet.add("String 1");
        leftSet.add("String 2");
        leftSet.add("String 3");
        leftSet.add("String 4");
        leftFields.setAll(leftSet);

        leftView.setItems(leftFields);
        //leftView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);


        ObservableList<String> rightFields = FXCollections.observableArrayList();
        ArrayList<String> rightSet = new ArrayList<>();
        rightFields.setAll(rightSet);

        rightView.setItems(rightFields);

        //rightView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }
}
