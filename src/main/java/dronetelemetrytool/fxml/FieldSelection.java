package dronetelemetrytool.fxml;

import dronetelemetrytool.DTT_GUI;
import dronetelemetrytool.DTT_Tools;
import dronetelemetrytool.MainApplication;
import dronetelemetrytool.fieldparsing.Field;
import dronetelemetrytool.fieldparsing.NumberField;
import dronetelemetrytool.gauges.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.control.TextField;

import java.io.*;
import java.net.URL;
import java.nio.file.Paths;
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
        ArrayList<GaugeInfo> list = new ArrayList<>();

        for (Gauge g : MainApplication.gauges)
        {
            GaugeInfo info = new GaugeInfo();
            switch (g.gaugeType)
            {
                case BAR:
                    ClusterBarGauge barGauge = (ClusterBarGauge) g;
                    info.gaugeTitle = barGauge.tile.getTitle();
                    info.fieldName = barGauge.getField().getName();
                    info.type = barGauge.gaugeType;
                    info.max = barGauge.tile.getMaxValue();
                    info.min = barGauge.tile.getMinValue();
                    info.gThresh = DTT_Tools.map(barGauge.tile.getChartData().get(0).getGradientLookup().getStops().get(1).getOffset(), 0, 1, info.min, info.max);
                    info.yThresh = DTT_Tools.map(barGauge.tile.getChartData().get(0).getGradientLookup().getStops().get(2).getOffset(), 0, 1, info.min, info.max);
                    info.rThresh =DTT_Tools.map(barGauge.tile.getChartData().get(0).getGradientLookup().getStops().get(3).getOffset(), 0, 1, info.min, info.max);
                    info.desUnit = "";
                    info.warning = barGauge.getAlarmIndex();
                    break;
                case CIRCLE90:
                    CircleGauge circleGauge90 = (CircleGauge) g;
                    info.gaugeTitle = circleGauge90.tile.getTitle();
                    info.fieldName = circleGauge90.getField().getName();
                    info.type = circleGauge90.gaugeType;
                    info.max = circleGauge90.tile.getMaxValue();
                    info.min = circleGauge90.tile.getMinValue();
                    info.gThresh = DTT_Tools.map(circleGauge90.getGradient().getStops().get(1).getOffset(), 0, 1, info.min, info.max);
                    info.yThresh = DTT_Tools.map(circleGauge90.getGradient().getStops().get(2).getOffset(), 0, 1, info.min, info.max);
                    info.rThresh = DTT_Tools.map(circleGauge90.getGradient().getStops().get(3).getOffset(), 0, 1, info.min, info.max);
                    info.desUnit = "";
                    info.warning = circleGauge90.getAlarmIndex();
                    break;
                case CIRCLE180:
                    CircleGauge circleGauge180 = (CircleGauge) g;
                    info.gaugeTitle = circleGauge180.tile.getTitle();
                    info.fieldName = circleGauge180.getField().getName();
                    info.type = circleGauge180.gaugeType;
                    info.max = circleGauge180.tile.getMaxValue();
                    info.min = circleGauge180.tile.getMinValue();
                    info.gThresh = DTT_Tools.map(circleGauge180.getGradient().getStops().get(1).getOffset(), 0, 1, info.min, info.max);
                    info.yThresh = DTT_Tools.map(circleGauge180.getGradient().getStops().get(2).getOffset(), 0, 1, info.min, info.max);
                    info.rThresh = DTT_Tools.map(circleGauge180.getGradient().getStops().get(3).getOffset(), 0, 1, info.min, info.max);
                    info.desUnit = "";
                    info.warning = circleGauge180.getAlarmIndex();
                    break;
                case CIRCLE270:
                    CircleGauge circleGauge270 = (CircleGauge) g;
                    info.gaugeTitle = circleGauge270.tile.getTitle();
                    info.fieldName = circleGauge270.getField().getName();
                    info.type = circleGauge270.gaugeType;
                    info.max = circleGauge270.tile.getMaxValue();
                    info.min = circleGauge270.tile.getMinValue();
                    info.gThresh = DTT_Tools.map(circleGauge270.getGradient().getStops().get(1).getOffset(), 0, 1, info.min, info.max);
                    info.yThresh = DTT_Tools.map(circleGauge270.getGradient().getStops().get(2).getOffset(), 0, 1, info.min, info.max);
                    info.rThresh = DTT_Tools.map(circleGauge270.getGradient().getStops().get(3).getOffset(), 0, 1, info.min, info.max);
                    info.desUnit = "";
                    info.warning = circleGauge270.getAlarmIndex();
                    break;
                case CIRCLE360:
                    CircleGauge circleGauge360 = (CircleGauge) g;
                    info.gaugeTitle = circleGauge360.tile.getTitle();
                    info.fieldName = circleGauge360.getField().getName();
                    info.type = circleGauge360.gaugeType;
                    info.max = circleGauge360.tile.getMaxValue();
                    info.min = circleGauge360.tile.getMinValue();
                    info.gThresh = DTT_Tools.map(circleGauge360.getGradient().getStops().get(1).getOffset(), 0, 1, info.min, info.max);
                    info.yThresh = DTT_Tools.map(circleGauge360.getGradient().getStops().get(2).getOffset(), 0, 1, info.min, info.max);
                    info.rThresh = DTT_Tools.map(circleGauge360.getGradient().getStops().get(3).getOffset(), 0, 1, info.min, info.max);
                    info.desUnit = "";
                    info.warning = circleGauge360.getAlarmIndex();
                    break;
                //EJ DO BELOW. JAKE DO ABOVE
                case CLOCK:
                    ClockGauge clockGauge = (ClockGauge) g;
                    info.gaugeTitle = clockGauge.tile.getTitle();
                    info.fieldName = clockGauge.getField().getName();
                    info.type = clockGauge.gaugeType;
                    break;
                case ONOFF:
                    OnOffGauge onOffGauge = (OnOffGauge) g;
                    info.gaugeTitle = onOffGauge.tile.getTitle();
                    info.fieldName = onOffGauge.getField().getName();
                    info.type = onOffGauge.gaugeType;
                    info.color = String.valueOf(onOffGauge.tile.getActiveColor());
                    break;
                case TEXT:
                    TextGauge textGauge = (TextGauge) g;
                    info.gaugeTitle = textGauge.tile.getTitle();
                    info.fieldName = textGauge.getField().getName();
                    info.type = textGauge.gaugeType;
                    break;
                case TIMESTAMP:
                    TimestampGauge timestampGauge = (TimestampGauge) g;
                    info.gaugeTitle = timestampGauge.tile.getTitle();
                    info.fieldName = timestampGauge.getField().getName();
                    info.type = timestampGauge.gaugeType;
                    break;
                case XPLOT:
                    XPlotGauge xPlotGauge = (XPlotGauge) g;
                    info.gaugeTitle = xPlotGauge.tile.getTitle();
                    info.fieldName = xPlotGauge.getField().getName();
                    info.type = xPlotGauge.gaugeType;
                    info.axisLabel = xPlotGauge.primaryAxis.getLabel();
                    info.max = xPlotGauge.primaryAxis.getUpperBound();
                    info.min = xPlotGauge.primaryAxis.getLowerBound();
                    info.orient = xPlotGauge.orient;
                    info.tick = xPlotGauge.primaryAxis.getTickUnit();
                    break;
                case XYPLOT:
                    XYPlotGauge xyPlotGauge = (XYPlotGauge) g;
                    info.gaugeTitle = xyPlotGauge.tile.getTitle();
                    info.fieldName = xyPlotGauge.getField().getName();
                    info.type = xyPlotGauge.gaugeType;
                    info.axisLabel = xyPlotGauge.xAxis.getLabel();
                    info.max = xyPlotGauge.xAxis.getUpperBound();
                    info.min = xyPlotGauge.xAxis.getLowerBound();
                    info.tick = xyPlotGauge.xAxis.getTickUnit();
                    info.yaxisLabel = xyPlotGauge.yAxis.getLabel();
                    info.ymax = xyPlotGauge.yAxis.getUpperBound();
                    info.ymin = xyPlotGauge.yAxis.getLowerBound();
                    info.ytick = xyPlotGauge.yAxis.getTickUnit();
                    break;
            }
            list.add(info);
        }
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Save gauge setup");
        chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Serialized Files", "*.ser"));

        String currentPath = Paths.get(".").toAbsolutePath().normalize().toString();
        chooser.setInitialDirectory(new File(currentPath));
        Stage stage = (Stage) buttonContinue.getScene().getWindow();
        File selectedFile = chooser.showSaveDialog(stage);

        try {
            FileOutputStream fileOut = new FileOutputStream(selectedFile);
//            FileOutputStream fileOut = new FileOutputStream("src//main//resources//dronetelemetrytool//data//gaugeList.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(list);
            System.out.println("list size: " + list.size());
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    @FXML
    protected void onContinueClick() throws IOException {

//        //String title = FIELD_Title.textProperty().getValueSafe();
//
//        TextGauge testGA = new TextGauge();
//        testGA.setField(field);
//        testGA.setTitle("whatever");
//
//        MainApplication.gauges.add(testGA);
//        //FieldSelection.addToRight("whatever");
//        try {
//            FileInputStream fileIn = new FileInputStream("D:\\School\\CS499\\Resources\\gauge.ser");
//            ObjectInputStream in = new ObjectInputStream(fileIn);
//            testGA = (TextGauge) in.readObject();
//            in.close();
//            fileIn.close();
//        } catch (IOException i) {
//            i.printStackTrace();
//            return;
//        } catch (ClassNotFoundException c) {
//            System.out.println("Class not found");
//            c.printStackTrace();
//            return;
//        }
//
//        MainApplication.gauges.add(testGA);

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
