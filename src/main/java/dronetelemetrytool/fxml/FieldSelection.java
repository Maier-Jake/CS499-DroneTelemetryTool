package dronetelemetrytool.fxml;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
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

    ArrayList<String> leftSet = new ArrayList<>();
    ObservableList<String> leftFields = FXCollections.observableArrayList();
    ArrayList<String> rightSet = new ArrayList<>();
    ObservableList<String> rightFields = FXCollections.observableArrayList();

    @FXML
    protected void onCreateClick() {
        // Checks if right list length is <10
        if (rightSet.size() < 10 && leftView.getSelectionModel().getSelectedIndex() != -1) {
            // Adds item to right
            rightSet.add(leftSet.get(leftView.getSelectionModel().getSelectedIndex()));
            // Removes item from left
            leftSet.remove(leftSet.get(leftView.getSelectionModel().getSelectedIndex()));
            // Update the observable lists
            leftFields.setAll(leftSet);
            rightFields.setAll(rightSet);
        }
    }

    @FXML
    protected void onRemoveClick() {
        if ( rightView.getSelectionModel().getSelectedIndex() != -1 ) {
            leftSet.add(rightSet.get(rightView.getSelectionModel().getSelectedIndex()));
            rightSet.remove(rightSet.get(rightView.getSelectionModel().getSelectedIndex()));
            leftFields.setAll(leftSet);
            rightFields.setAll(rightSet);
        }
    }

    @FXML
    protected void onSaveClick() {

    }

    @FXML
    protected void onContinueClick() {

    }

    @FXML
    public void initialize(URL url, ResourceBundle rb) {
        leftSet.add("String 1");
        leftSet.add("String 2");
        leftSet.add("String 3");
        leftSet.add("String 4");
        leftSet.add("String 5");
        leftSet.add("String 6");
        leftSet.add("String 7");
        leftSet.add("String 8");
        leftSet.add("String 9");
        leftSet.add("String 10");
        leftSet.add("String 11");
        leftSet.add("String 12");

        leftFields.setAll(leftSet);
        leftView.setItems(leftFields);
        leftView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);


        rightFields.setAll(rightSet);
        rightView.setItems(rightFields);
        rightView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }
}
