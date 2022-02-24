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

import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class MainApplication extends Application {

    private static final double TILE_WIDTH = 250;
    private static final double TILE_HEIGHT = 250;


    private Media video;
    private Tile videoTile;

    private ArrayList<Gauge> gauges;

    private long lastTimerCall;
    private long gaugeUpdateFrequency;
    private int gaugeUpdateFrequencyModifier;
    private AnimationTimer timer;

    @Override public void init() {

        gauges = new ArrayList<Gauge>(10);
        gauges.add(new CharacterGauge());
        gauges.add(new ClusterBarGauge());
        gauges.add(new OnOffGauge());
        gauges.add(new TextGauge());

        File mediaFile = new File("src/main/resources/dronetelemetrytool/monopolyYES.mp4");
        video = null;
        try {
            video = new Media(mediaFile.toURI().toURL().toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        videoTile = DTT_TileBuilder.createVideoTile(video, 1000, 562.5);
        MediaView vi = (MediaView)(videoTile.getGraphic());

        //lastStockCall = System.nanoTime();
        final Duration[] timeStamp = {Duration.ZERO};
        System.out.println("TEST");

        gaugeUpdateFrequencyModifier = 10;
        gaugeUpdateFrequency = 1_000_000_000 / gaugeUpdateFrequencyModifier;

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

                    //XYChart.Series<String, Number> series = xyGauge.tile.getSeries().get(0);
                    //series.getData().forEach(data -> data.setYValue(RND.nextInt(100)));

                    // forEach method of ArrayList and
                    gauges.forEach((n) -> n.update());

                    //onOffTile.setActive(!onOffTile.isActive());

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
    public void start(final Stage stage) {

        // Button code here will eventually be moved to a tile later
        final Button videoButton = new Button("Open an Mp4");
        videoButton.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(final ActionEvent e) {
                        // Choose the file source
                        video = DTT_Tools.chooseVideo();
                        Tile v = DTT_TileBuilder.createVideoTile(video);
                        DTT_Tools.displayTile(v);
                    }
                }
        );


        gauges.forEach((n) -> n.display());
        //xyGauge.display();

        //DTT_Tools.displaySeparateDTT(videoTile, barTile, onOffTile, textTile, characterTile);

        timer.start();

    }

    final GridPane inputGridPane = new GridPane();

        GridPane.setConstraints(videoButton, 0, 0);
        inputGridPane.setHgap(6);
        inputGridPane.setVgap(6);
        inputGridPane.getChildren().addAll(videoButton);

    final Pane rootGroup = new VBox(12);
        rootGroup.getChildren().addAll(inputGridPane);
        rootGroup.setPadding(new Insets(12, 12, 12, 12));

        stage.setScene(new Scene(rootGroup));
        stage.show();

    @Override
    public void stop() {
        System.exit(0);
    }


    public static void main(String[] args) {
        launch(args);
    }
}