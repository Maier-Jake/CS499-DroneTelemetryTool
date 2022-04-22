package dronetelemetrytool.fxml;

import dronetelemetrytool.DTT_GUI;
import dronetelemetrytool.MainApplication;
import dronetelemetrytool.fieldparsing.Field;
import dronetelemetrytool.fieldparsing.FieldCollection;
import dronetelemetrytool.fieldparsing.NumberField;
import dronetelemetrytool.gauges.ClusterBarGauge;
import dronetelemetrytool.gauges.Gauge;
import dronetelemetrytool.gauges.GaugeInfo;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.media.Media;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.net.MalformedURLException;
import java.nio.file.Paths;
import java.util.ArrayList;

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
                        ClusterBarGauge g = new ClusterBarGauge(gaugeInfo.gaugeTitle, gaugeInfo.min, gaugeInfo.max, gaugeInfo.gThresh, gaugeInfo.yThresh, gaugeInfo.rThresh, gaugeInfo.desUnit);
                        g.setField(new NumberField(field));
                        g.setTitle(gaugeInfo.gaugeTitle);
                        g.setAlarm(gaugeInfo.warning);
                        MainApplication.gauges.add(g);
                        System.out.println("made a bar gauge");
                        break;
                    case CIRCLE90:
                        break;
                    case CIRCLE180:
                        break;
                    case CIRCLE270:
                        break;
                    case CIRCLE360:
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