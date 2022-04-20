package dronetelemetrytool;

import dronetelemetrytool.fieldparsing.*;
import dronetelemetrytool.gauges.*;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.apache.commons.io.FileUtils;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

public class MainApplication extends Application {
    
    public static FieldCollection fields;
    public static ArrayList<Gauge> gauges;
    public static Media video;
    
    public static TimeField timestampField;
    private static float frequency;
    private static float frequencyOriginal;
    
    public static AnimationTimer timer;
    private static long lastTimerCall;

    public static int code = -1;
    public static long prevTime;
    public static long currentTime;

    public static void setFrequency(float parseFloat) {
        parseFloat *= 100;
        frequency = parseFloat;
        frequencyOriginal = parseFloat;
    }

    public static void setSpeed(float rate) {
        frequency = frequencyOriginal * rate;
    }

    public static int code = -1;
    public static long prevTime;
    public static long currentTime;

    @Override
    public void init() {

        gauges = new ArrayList<Gauge>(10);
        video = null;
        fields = null;
        timestampField = null;
        frequency = (long) -0.1;

        final Duration[] timeStamp = {Duration.ZERO};

//        gaugeUpdateFrequencyModifier = 10;
//        gaugeUpdateFrequency = (long)( (double) 1000000000 / gaugeUpdateFrequencyequencyModifier);

        lastTimerCall = System.nanoTime();
        timer = new AnimationTimer() {
            @Override
            public void handle(final long now) {
                if (code == 0) {
                    if (now > lastTimerCall + frequency) {
                        //for each gauge CREATED, run an update.
                        gauges.forEach(Gauge::update);
                        lastTimerCall = now;
                    }
                } else if (code == 1) {
                    if (now > lastTimerCall + (currentTime - prevTime)) {
                        gauges.forEach(Gauge::update);
                        lastTimerCall = now;
                        prevTime = currentTime;
                        currentTime = timestampField.getNext();
                    }
                } else {
                    System.out.println("Err?");
                }
            }
        };

    }

    @Override
    public void start(Stage stage) throws IOException {
//        timer.start();
        DTT_GUI.inputSelector();
    }

    @Override
    public void stop(){System.exit(0);}
    public static void main(String[] args) {
        launch(args);
    }


}

