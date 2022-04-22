package dronetelemetrytool.fxml;

import dronetelemetrytool.DTT_GUI;
import dronetelemetrytool.DTT_Tools;
import dronetelemetrytool.MainApplication;
import dronetelemetrytool.fieldparsing.Field;
import dronetelemetrytool.fieldparsing.FieldCollection;
import dronetelemetrytool.fieldparsing.NumberField;
import dronetelemetrytool.fieldparsing.TimeField;
import dronetelemetrytool.gauges.CircleGauge;
import dronetelemetrytool.gauges.ClusterBarGauge;
import dronetelemetrytool.gauges.Gauge;
import dronetelemetrytool.gauges.GaugeInfo;
import eu.hansolo.tilesfx.colors.Bright;
import eu.hansolo.toolboxfx.GradientLookup;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.media.Media;
import javafx.scene.paint.Stop;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.net.MalformedURLException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

public class SetupSelector {
    @FXML
    public Button buttonNEW;
    @FXML
    public Button buttonEXIST;
    private GaugeInfo gaugeInfo;

    @FXML
    protected void onNewClick() throws IOException {
        DTT_GUI.fieldSelection();
    }

    @FXML
    protected void onExistingClick() throws IOException {
//        Gauge e = null;
        try {
            FileInputStream fileIn = new FileInputStream("src//main//resources//dronetelemetrytool//data//gaugeList.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            ArrayList<GaugeInfo> input = (ArrayList<GaugeInfo>) in.readObject();
            for (GaugeInfo gaugeInfo : input) {
                Field field = null;
                System.out.println("looking for: " + gaugeInfo.fieldName);
                for (Field f : MainApplication.fields.getFields()) {
                    System.out.println(f.getName());
                    if (f.getName().equals(gaugeInfo.fieldName)) {
                        System.out.println("found matching gauge");
                        field = f;
                    }
                }
                switch (gaugeInfo.type) {
                    case BAR:
                        ClusterBarGauge barGauge = new ClusterBarGauge(gaugeInfo.gaugeTitle, gaugeInfo.min, gaugeInfo.max, gaugeInfo.gThresh, gaugeInfo.yThresh, gaugeInfo.rThresh, gaugeInfo.desUnit);
                        barGauge.setField(new NumberField(field));
                        barGauge.setTitle(gaugeInfo.gaugeTitle);
                        barGauge.setAlarm(gaugeInfo.warning);
                        MainApplication.gauges.add(barGauge);
                        System.out.println("made a bar gauge");
                        break;
                    case CIRCLE90:
                        CircleGauge circleGauge90 = new CircleGauge(90);
                        circleGauge90.setField(new NumberField(field));
                        circleGauge90.setTitle(gaugeInfo.gaugeTitle);
                        circleGauge90.tile.setMaxValue(gaugeInfo.max);
                        circleGauge90.tile.setMinValue(gaugeInfo.min);
                        GradientLookup grad1 = new GradientLookup(Arrays.asList(
                                new Stop(0, Bright.BLUE),
                                new Stop(DTT_Tools.map(gaugeInfo.gThresh,gaugeInfo.min,gaugeInfo.max,0,1), Bright.GREEN),
                                new Stop(DTT_Tools.map(gaugeInfo.yThresh,gaugeInfo.min,gaugeInfo.max,0,1), Bright.YELLOW),
                                new Stop(DTT_Tools.map(gaugeInfo.rThresh,gaugeInfo.min,gaugeInfo.max,0,1), Bright.RED),
                                new Stop(1, Bright.RED)));
                        circleGauge90.setGradient(grad1);
                        circleGauge90.setAlarm(gaugeInfo.warning);
                        MainApplication.gauges.add(circleGauge90);
                        break;
                    case CIRCLE180:
                        CircleGauge circleGauge180 = new CircleGauge(180);
                        circleGauge180.setField(new NumberField(field));
                        circleGauge180.setTitle(gaugeInfo.gaugeTitle);
                        circleGauge180.tile.setMaxValue(gaugeInfo.max);
                        circleGauge180.tile.setMinValue(gaugeInfo.min);
                        GradientLookup grad2 = new GradientLookup(Arrays.asList(
                                new Stop(0, Bright.BLUE),
                                new Stop(DTT_Tools.map(gaugeInfo.gThresh,gaugeInfo.min,gaugeInfo.max,0,1), Bright.GREEN),
                                new Stop(DTT_Tools.map(gaugeInfo.yThresh,gaugeInfo.min,gaugeInfo.max,0,1), Bright.YELLOW),
                                new Stop(DTT_Tools.map(gaugeInfo.rThresh,gaugeInfo.min,gaugeInfo.max,0,1), Bright.RED),
                                new Stop(1, Bright.RED)));
                        circleGauge180.setGradient(grad2);
                        circleGauge180.setAlarm(gaugeInfo.warning);
                        MainApplication.gauges.add(circleGauge180);
                        break;
                    case CIRCLE270:
                        CircleGauge circleGauge270 = new CircleGauge(270);
                        circleGauge270.setField(new NumberField(field));
                        circleGauge270.setTitle(gaugeInfo.gaugeTitle);
                        circleGauge270.tile.setMaxValue(gaugeInfo.max);
                        circleGauge270.tile.setMinValue(gaugeInfo.min);
                        GradientLookup grad3 = new GradientLookup(Arrays.asList(
                                new Stop(0, Bright.BLUE),
                                new Stop(DTT_Tools.map(gaugeInfo.gThresh,gaugeInfo.min,gaugeInfo.max,0,1), Bright.GREEN),
                                new Stop(DTT_Tools.map(gaugeInfo.yThresh,gaugeInfo.min,gaugeInfo.max,0,1), Bright.YELLOW),
                                new Stop(DTT_Tools.map(gaugeInfo.rThresh,gaugeInfo.min,gaugeInfo.max,0,1), Bright.RED),
                                new Stop(1, Bright.RED)));
                        circleGauge270.setGradient(grad3);
                        circleGauge270.setAlarm(gaugeInfo.warning);
                        MainApplication.gauges.add(circleGauge270);
                        break;
                    case CIRCLE360:
                        CircleGauge circleGauge360 = new CircleGauge(360);
                        circleGauge360.setField(new NumberField(field));
                        circleGauge360.setTitle(gaugeInfo.gaugeTitle);
                        circleGauge360.tile.setMaxValue(gaugeInfo.max);
                        circleGauge360.tile.setMinValue(gaugeInfo.min);
                        GradientLookup grad4 = new GradientLookup(Arrays.asList(
                                new Stop(0, Bright.BLUE),
                                new Stop(DTT_Tools.map(gaugeInfo.gThresh,gaugeInfo.min,gaugeInfo.max,0,1), Bright.GREEN),
                                new Stop(DTT_Tools.map(gaugeInfo.yThresh,gaugeInfo.min,gaugeInfo.max,0,1), Bright.YELLOW),
                                new Stop(DTT_Tools.map(gaugeInfo.rThresh,gaugeInfo.min,gaugeInfo.max,0,1), Bright.RED),
                                new Stop(1, Bright.RED)));
                        circleGauge360.setGradient(grad4);
                        circleGauge360.setAlarm(gaugeInfo.warning);
                        MainApplication.gauges.add(circleGauge360);
                        break;

                    //EJ DO BELOW. JAKE DO ABOVE
                    case CLOCK:
                        break;
                    case ONOFF:
                        break;
                    case TEXT:
                        break;
                    case TIMESTAMP:
                        break;
                    case XPLOT:
                        break;
                    case XYPLOT:
                        break;
                }
            }
        } catch (ClassNotFoundException ex) {
            System.out.println("classnotfound");
            ex.printStackTrace();
        }
        for (Gauge g : MainApplication.gauges)
        {
            g.display();
            System.out.println("displayed gauge");
        }
        ((Stage) buttonEXIST.getScene().getWindow()).close();
        DTT_GUI.videoPlayer();
    }

    @FXML
    public void initialize() throws FileNotFoundException {
//        continueButton.setDisable(true);
    }

}