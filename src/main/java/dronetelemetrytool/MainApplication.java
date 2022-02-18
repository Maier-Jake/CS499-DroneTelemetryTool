package dronetelemetrytool;

import eu.hansolo.tilesfx.Tile;
import eu.hansolo.tilesfx.chart.ChartData;
import eu.hansolo.tilesfx.colors.Bright;
import eu.hansolo.toolboxfx.GradientLookup;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.chart.Chart;
import javafx.scene.media.Media;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Stop;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.Random;

public class MainApplication extends Application {

    private static final double TILE_WIDTH = 250;
    private static final double TILE_HEIGHT = 250;
    private static final Random RND = new Random();
    private ChartData chartData1;

    private Tile barTile;
    private Tile onOffTile;
    private Tile textTile;
    private Tile videoTile;

    private long lastTimerCall;
    private AnimationTimer timer;
    private long gaugeUpdateFrequency;

    @Override public void init() {

        chartData1 = new ChartData("", Tile.YELLOW);
        GradientLookup gradient = new GradientLookup(Arrays.asList(
                new Stop(0.0, Bright.GREEN),
                new Stop(0.4, Bright.YELLOW),
                new Stop(0.8, Bright.RED)));

        barTile = DTT_TileBuilder.createBarGauge(chartData1);
        onOffTile = DTT_TileBuilder.createOnOffGauge();
        textTile = DTT_TileBuilder.createTextGauge("Jake");

        File mediaFile = new File("src/main/resources/dronetelemetrytool/monopolyYES.mp4");
        Media media = null;
        try {
            media = new Media(mediaFile.toURI().toURL().toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        videoTile = DTT_TileBuilder.createVideoTile(media, 1000, 562.5);
        MediaView vi = (MediaView)(videoTile.getGraphic());

        //lastStockCall = System.nanoTime();
        final Duration[] timeStamp = {Duration.ZERO};
        System.out.println("TEST");


        gaugeUpdateFrequency = 1_000_000_000/10;
        lastTimerCall = System.nanoTime();
        timer = new AnimationTimer() {
            @Override
            public void handle(final long now) {
                if (now > lastTimerCall + gaugeUpdateFrequency) {


                    //for each gauge CREATED, run an update.

                    //timeStamp[0] = timeStamp[0].add(Duration.millis(100));
                    /*
                    series1.getData().forEach(data -> data.setYValue(RND.nextInt(100)));
                    series2.getData().forEach(data -> data.setYValue(RND.nextInt(30)));
                    series3.getData().forEach(data -> data.setYValue(RND.nextInt(10)));

                    barChartTile.getBarChartItems().get(RND.nextInt(3)).setValue(RND.nextDouble() * 80);
                    */
                    //System.out.println(timeStamp[0]);
                    //vi.getMediaPlayer().seek(timeStamp[0]);
                    //vi.getMediaPlayer().seek(Duration.seconds(2));

                    chartData1.setValue(RND.nextDouble() * 100);
                    chartData1.setFillColor(gradient.getColorAt(chartData1.getValue() / 100));

                    onOffTile.setActive(!onOffTile.isActive());

                    /*
                    chartData2.setValue(RND.nextDouble() * 50);
                    chartData3.setValue(RND.nextDouble() * 50);
                    chartData4.setValue(RND.nextDouble() * 50);
                    chartData5.setValue(RND.nextDouble() * 50);
                    chartData6.setValue(RND.nextDouble() * 50);
                    chartData7.setValue(RND.nextDouble() * 50);
                    chartData8.setValue(RND.nextDouble() * 50);
                    */

                    lastTimerCall = now;
                }
            }
        };
    }

    @Override
    public void start(Stage stage) {

        // Choose the file source
        //Media media = DTT_Tools.chooseVideo();



        DTT_Tools.displayTile(barTile);
        DTT_Tools.displayTile(onOffTile);
        DTT_Tools.displayTile(textTile);
        DTT_Tools.displayTile(videoTile);

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

