package dronetelemetrytool;

import eu.hansolo.tilesfx.Tile;
import eu.hansolo.tilesfx.chart.ChartData;
import eu.hansolo.tilesfx.colors.Bright;
import eu.hansolo.toolboxfx.GradientLookup;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.chart.Chart;
import javafx.scene.media.Media;
import javafx.scene.paint.Stop;
import javafx.stage.Stage;

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
    private long lastTimerCall;
    private AnimationTimer timer;

    @Override public void init() {

        chartData1 = new ChartData("", Tile.YELLOW);
        GradientLookup gradient = new GradientLookup(Arrays.asList(
                new Stop(0.0, Bright.GREEN),
                new Stop(0.4, Bright.YELLOW),
                new Stop(0.8, Bright.RED)));

        barTile = DTT_TileBuilder.createBarGauge(chartData1);
        //lastStockCall = System.nanoTime();
        lastTimerCall = System.nanoTime();
        timer = new AnimationTimer() {
            @Override
            public void handle(final long now) {
                if (now > lastTimerCall + 2_000_000_000) {

                    /*
                    series1.getData().forEach(data -> data.setYValue(RND.nextInt(100)));
                    series2.getData().forEach(data -> data.setYValue(RND.nextInt(30)));
                    series3.getData().forEach(data -> data.setYValue(RND.nextInt(10)));

                    barChartTile.getBarChartItems().get(RND.nextInt(3)).setValue(RND.nextDouble() * 80);
                    */

                    chartData1.setValue(RND.nextDouble() * 100);
                    chartData1.setFillColor(gradient.getColorAt(chartData1.getValue() / 100));

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
        File mediaFile = new File("src/main/resources/dronetelemetrytool/monopolyYES.mp4");
        Media media = null;
        try {
            media = new Media(mediaFile.toURI().toURL().toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        //Tile videoTile = DTT_TileBuilder.createVideoTile(media, 1000, 562.5);

        DTT_Tools.displayTile(barTile);

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

