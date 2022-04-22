package dronetelemetrytool;

import dronetelemetrytool.fieldparsing.*;
import dronetelemetrytool.gauges.*;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.media.Media;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;

public class MainApplication extends Application {
    
    public static FieldCollection fields;
    public static ArrayList<Gauge> gauges;
    public static Media video;
    
    public static TimeField timestampField;
    private static int rate;
    private static long frequency;
    private static double frequencyOriginal;
    
    public static AnimationTimer timer;
//    private static long lastTimerCall;

    public static int code = -1;
//    public static long prevTime;
//    public static long currentTime;

    public static void setFrequency(float parseFloat) {
        frequencyOriginal = parseFloat * 100;
    }

    public static void setRate(int r) {
        rate = r;
    }

    @Override
    public void init() {

        gauges = new ArrayList<Gauge>(10);
        video = null;
        fields = null;
        timestampField = null;
        frequencyOriginal = 0.1;
        frequency = 100_000_000;

        final Duration[] timeStamp = {Duration.ZERO};

//        gaugeUpdateFrequencyModifier = 10;
//        gaugeUpdateFrequency = (long)( (double) 1000000000 / gaugeUpdateFrequencyequencyModifier);

//        lastTimerCall = System.nanoTime();

        timer = new AnimationTimer() {
            private long lastUpdate = 0 ;
            private long interval = 0;

            @Override
            public void handle(final long now) {
                if (code == 0) {
                    if (now - lastUpdate >= (long)((frequencyOriginal / rate) * 1_000_000_000)) {
                        //for each gauge CREATED, run an update.
                        gauges.forEach(Gauge::update);
                        lastUpdate = now;
                    }
                } else if (code == 1) {
                    if (now - lastUpdate >= (interval / rate)) {
                        interval = timestampField.getNextInterval();
                        gauges.forEach(Gauge::update);
                        lastUpdate = now;
                    }
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

