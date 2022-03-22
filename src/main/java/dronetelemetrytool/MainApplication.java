package dronetelemetrytool;

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

public class MainApplication extends Application {

    private static Media video;

    public static ArrayList<Gauge> gauges;

    private static long lastTimerCall;
    private static long gaugeUpdateFrequency;
    private static int gaugeUpdateFrequencyModifier;
    private static AnimationTimer timer;

    @Override public void init() {

        gauges = new ArrayList<Gauge>(10);

        File mediaFile = new File("src/main/resources/dronetelemetrytool/monopolyYES.mp4");
        video = null;
        try {
            video = new Media(mediaFile.toURI().toURL().toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        //lastStockCall = System.nanoTime();
        final Duration[] timeStamp = {Duration.ZERO};

        gaugeUpdateFrequencyModifier = 1;
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

        //gauges.forEach((Gauge n) -> n.display());
        //video = DTT_Tools.chooseVideo();
        //DTT_Tools.displayVideo(video);
        timer.start();
        DTT_GUI.xPlotGaugeCreator();

    }

    @Override
    public void stop() {
        System.exit(0);
    }
    public static void main(String[] args) {
        launch(args);
    }
}

