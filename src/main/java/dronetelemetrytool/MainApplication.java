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

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

public class MainApplication extends Application {

    public static ArrayList<Gauge> gauges;
    public static Media video;
    public static FieldCollection fields;
    public static TimeField timestampField;
    public static float frequency;
    public static AnimationTimer timer;

    private static long lastTimerCall;
    private static long gaugeUpdateFrequency;
    private static int gaugeUpdateFrequencyModifier;

    @Override
    public void init() {

        gauges = new ArrayList<Gauge>(10);
        video = null;
        fields = null;
        timestampField = null;
        frequency = -1.0f;

        final Duration[] timeStamp = {Duration.ZERO};

        gaugeUpdateFrequencyModifier = 10;
        gaugeUpdateFrequency = 1_000_000_000 / gaugeUpdateFrequencyModifier;

        lastTimerCall = System.nanoTime();
        timer = new AnimationTimer() {
            @Override
            public void handle(final long now) {
                if (now > lastTimerCall + gaugeUpdateFrequency) {
                    //for each gauge CREATED, run an update.
                    gauges.forEach(Gauge::update);
                    lastTimerCall = now;
                }
            }
        };

    }

    @Override
    public void start(Stage stage) throws IOException {
       timer.start();
//        Field f = new Field("data");
//        NumberField nf = new NumberField(f);
//        DTT_GUI.xyPlotGaugeCreator();
       DTT_GUI.inputSelector();
//        XPlotGauge ga = new XPlotGauge(GaugeOrient.HORIZONTAL, 0, 10, 1);
//        ga.display();


    }

    @Override
    public void stop() {
        System.exit(0);
    }
    public static void main(String[] args) {
        launch(args);
    }
}

