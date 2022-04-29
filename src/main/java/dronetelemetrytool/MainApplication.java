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

    public static int code = -1;

    public static void setFrequency(float parseFloat) {
        frequencyOriginal = parseFloat;
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

        timer = new AnimationTimer() {
            private long lastUpdate = 0 ;
            private Long interval = Long.valueOf(0);

            private int updateNumber = 1;

            @Override
            public void handle(final long now) {
                if (code == 0) {
                    if (now - lastUpdate >= (long)((frequencyOriginal / rate) * 1_000_000_000)) {
                        if (updateNumber > fields.fieldLength()) {
                            timer.stop();
                            updateNumber = 1;
                            lastUpdate = 0;
                        } else {
                            //for each gauge CREATED, run an update.
                            gauges.forEach(Gauge::update);
                            lastUpdate = now;
                            updateNumber++;
                        }
                    }
                } else if (code == 1) {
                    if (now - lastUpdate >= (interval / rate)) {
                        interval = timestampField.getNextInterval();
                        if (interval == null) {
                            timer.stop();
                            lastUpdate = 0;
                            interval = Long.valueOf(0);
                            timestampField.setIndex(0);
                        } else {
                            gauges.forEach(Gauge::update);
                            lastUpdate = now;
                        }
                    }
                }
            }
        };
    }

    @Override
    public void start(Stage stage) throws IOException {
        DTT_GUI.inputSelector();
    }

    @Override
    public void stop(){System.exit(0);}
    public static void main(String[] args) {
        launch(args);
    }


}

