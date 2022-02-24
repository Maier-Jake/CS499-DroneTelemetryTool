package dronetelemetrytool;

import dronetelemetrytool.gauges.*;
import eu.hansolo.tilesfx.Tile;
import eu.hansolo.tilesfx.chart.ChartData;
import eu.hansolo.tilesfx.colors.Bright;
import eu.hansolo.toolboxfx.GradientLookup;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.chart.Chart;
import javafx.scene.chart.XYChart;
import javafx.scene.media.Media;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Stop;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class MainApplication extends Application {

    private static final double TILE_WIDTH = 250;
    private static final double TILE_HEIGHT = 250;


    private Media video;
    //private Tile videoTile;

    private ArrayList<Gauge> gauges;

    private long lastTimerCall;
    private long gaugeUpdateFrequency;
    private int gaugeUpdateFrequencyModifier;
    private AnimationTimer timer;

    @Override public void init() {

        gauges = new ArrayList<Gauge>(10);
        //gauges.add(new CharacterGauge());
        //gauges.add(new ClusterBarGauge());
        //gauges.add(new OnOffGauge());
        //gauges.add(new TextGauge());
        gauges.add(new XYPlotGauge());
        gauges.add(new XPlotGauge());
        //gauges.add(new Circle90Gauge());
        //gauges.add(new ClockGauge());
        gauges.add(new TimestampGauge());


        File mediaFile = new File("src/main/resources/dronetelemetrytool/monopolyYES.mp4");
        video = null;
        try {
            video = new Media(mediaFile.toURI().toURL().toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        //videoTile = DTT_TileBuilder.createVideoTile(video, 1000, 562.5);
        //MediaView vi = (MediaView)(videoTile.getGraphic());

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
                    gauges.forEach((n) -> n.update());

                    lastTimerCall = now;
                }
            }
        };
    }

    @Override
    public void start(Stage stage) {

        gauges.forEach((n) -> n.display());

        DTT_Tools.displayVideo(video);

        timer.start();


    }

    @Override
    public void stop() {
        System.exit(0);
    }


    public static void main(String[] args) {
        launch(args);
    }
}

