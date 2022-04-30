package dronetelemetrytool.fxml;

import dronetelemetrytool.DTT_GUI;
import dronetelemetrytool.DTT_Tools;
import dronetelemetrytool.MainApplication;
import dronetelemetrytool.fieldparsing.*;
import dronetelemetrytool.gauges.*;
import eu.hansolo.tilesfx.colors.Bright;
import eu.hansolo.toolboxfx.GradientLookup;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.media.Media;
import javafx.scene.paint.Stop;
import javafx.scene.paint.Color;
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
        Stage stage = (Stage) buttonNEW.getScene().getWindow();
        stage.close();
    }

    @FXML
    protected void onExistingClick() throws IOException, ClassNotFoundException {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Select saved gauge setup");
        chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Serialized Files", "*.ser"));

        String currentPath = Paths.get(".").toAbsolutePath().normalize().toString();
        chooser.setInitialDirectory(new File(currentPath));
        Stage stage = (Stage) buttonEXIST.getScene().getWindow();
        File selectedFile = chooser.showOpenDialog(stage);

        FileInputStream fileIn= new FileInputStream(selectedFile);
        ObjectInputStream in = new ObjectInputStream(fileIn);
        ArrayList<GaugeInfo> input = (ArrayList<GaugeInfo>) in.readObject();
        for (GaugeInfo gaugeInfo : input) {
            Field field = null;
            for (Field f : MainApplication.fields.getFields()) {
                System.out.println(f.getName());
                if (f.getName().equals(gaugeInfo.fieldName)) {
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
                    ClockGauge clockGauge = new ClockGauge();
                    clockGauge.setField(new TimeField(field));
                    clockGauge.setTitle(gaugeInfo.gaugeTitle);
                    MainApplication.gauges.add(clockGauge);
                    break;
                case ONOFF:
                    OnOffGauge onOffGauge = new OnOffGauge();
                    onOffGauge.setField(new BoolField(field));
                    onOffGauge.setTitle(gaugeInfo.gaugeTitle);
                    onOffGauge.tile.setActiveColor(Color.valueOf(gaugeInfo.color));
                    MainApplication.gauges.add(onOffGauge);
                    break;
                case TEXT:
                    TextGauge newGauge = new TextGauge();
                    newGauge.setField(field);
                    newGauge.setTitle(gaugeInfo.gaugeTitle);
                    MainApplication.gauges.add(newGauge);
                    break;
                case TIMESTAMP:
                    TimestampGauge timestampGauge = new TimestampGauge();
                    timestampGauge.setField(new TimeField(field));
                    timestampGauge.setTitle(gaugeInfo.gaugeTitle);
                    MainApplication.gauges.add(timestampGauge);
                    break;
                case XPLOT:
                    XPlotGauge xPlotGauge = new XPlotGauge(gaugeInfo.orient, gaugeInfo.min, gaugeInfo.max, gaugeInfo.tick);
                    xPlotGauge.setField(new NumberField(field));
                    xPlotGauge.setTitle(gaugeInfo.gaugeTitle);
                    xPlotGauge.setLabel(gaugeInfo.axisLabel);
                    MainApplication.gauges.add(xPlotGauge);
                    break;
                case XYPLOT:
                    XYPlotGauge xyPlotGauge = new XYPlotGauge(gaugeInfo.min, gaugeInfo.max, gaugeInfo.tick, gaugeInfo.ymin, gaugeInfo.ymax, gaugeInfo.ytick);
                    xyPlotGauge.setTitle(gaugeInfo.gaugeTitle);
                    xyPlotGauge.setXLabel(gaugeInfo.axisLabel);
                    xyPlotGauge.setYLabel(gaugeInfo.yaxisLabel);
                    xyPlotGauge.setxField(new NumberField(field));
                    xyPlotGauge.setyField(new NumberField(field));
                    MainApplication.gauges.add(xyPlotGauge);
                    break;
            }
        }
        for (Gauge g : MainApplication.gauges)
        {
            g.display();
        }

        DTT_GUI.videoPlayer();
        ((Stage) buttonEXIST.getScene().getWindow()).close();
        stage.close();
    }

    @FXML
    public void initialize() throws FileNotFoundException {
//        continueButton.setDisable(true);
    }

}