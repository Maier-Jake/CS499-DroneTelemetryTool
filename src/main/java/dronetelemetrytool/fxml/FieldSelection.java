package dronetelemetrytool.fxml;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.util.Callback;

import java.net.URL;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Set;

public class FieldSelection implements Initializable {

    @FXML
    public ListView leftView;

    @FXML
    public ListView rightView;

    @FXML
    public TextField SearchBar;

    ArrayList<String> leftSet = new ArrayList<>();
    ObservableList<String> leftFields = FXCollections.observableArrayList();
    String Filter = new String();
    FilteredList<String> leftFilter = new FilteredList<String>(leftFields, s -> true);
    ArrayList<String> rightSet = new ArrayList<>();
    ObservableList<String> rightFields = FXCollections.observableArrayList();

    ObservableList<Integer> indices;
    ArrayList<String> items = new ArrayList<>();

    @FXML
    protected void onCreateClick() {
        // Checks if right list length is <10
        indices = leftView.getSelectionModel().getSelectedIndices();
        System.out.println(indices.size());
        if (indices.size() > 0 && rightSet.size() + indices.size() <= 10) {
            for (int i = 0; i < indices.size(); i++) {
                items.add(leftSet.get(indices.get(i)));
            }
            for (int i = 0; i < indices.size(); i++) {
                rightSet.add(items.get(i));
                leftSet.remove(items.get(i));
            }
            leftFields.setAll(leftSet);
            rightFields.setAll(rightSet);
            items.clear();
            leftView.getSelectionModel().clearSelection();
        }
    }

    @FXML
    protected void onRemoveClick() {
        indices = rightView.getSelectionModel().getSelectedIndices();
        if ( indices.size() > 0 ) {
            for (int i = 0; i < indices.size(); i++) {
                items.add(rightSet.get(indices.get(i)));
            }
            for (int i = 0; i < indices.size(); i++) {
                leftSet.add(items.get(i));
                rightSet.remove(items.get(i));
            }
            leftFields.setAll(leftSet);
            rightFields.setAll(rightSet);
            items.clear();
            rightView.getSelectionModel().clearSelection();
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
        leftSet.add("United States of America");
        leftSet.add("Free American Empire");
        leftSet.add("Communist States of America");
        leftSet.add("America");
        leftSet.add("British Empire");
        leftSet.add("Great Britain");
        leftSet.add("British Commune");
        leftSet.add("England");
        leftSet.add("String 9");
        leftSet.add("String 10");
        leftSet.add("String 11");
        leftSet.add("String 12");

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
                leftFilter.setPredicate(s -> s.toLowerCase().contains(filter.toLowerCase()));
            }
        });
    }
}
