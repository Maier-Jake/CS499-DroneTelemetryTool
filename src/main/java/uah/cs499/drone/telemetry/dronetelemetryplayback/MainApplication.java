package uah.cs499.drone.telemetry.dronetelemetryplayback;

import eu.hansolo.tilesfx.Tile;
import eu.hansolo.tilesfx.Tile.ChartType;
import eu.hansolo.tilesfx.Tile.MapProvider;
import eu.hansolo.tilesfx.Tile.SkinType;
import eu.hansolo.tilesfx.Tile.TileColor;
import eu.hansolo.tilesfx.TileBuilder;
import eu.hansolo.tilesfx.chart.ChartData;;
import eu.hansolo.tilesfx.skins.BarChartItem;
import eu.hansolo.tilesfx.skins.LeaderBoardItem;
import eu.hansolo.tilesfx.tools.Location;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.media.Media;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.media.MediaPlayer;


import java.io.File;
import java.net.MalformedURLException;
import java.util.Random;

public class MainApplication extends Application {

    private static final double TILE_WIDTH = 250;
    private static final double TILE_HEIGHT = 250;

    @Override
    public void start(Stage stage) {

        // Choose the file source
        //Media media = DTT_Tools.chooseVideo();
        File mediaFile = new File("src/main/resources/uah/cs499/drone/telemetry/dronetelemetryplayback/monopolyYES.mp4");
        Media media = null;
        try {
            media = new Media(mediaFile.toURI().toURL().toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        Tile videoTile = DTT_TileBuilder.createVideoTile(media, 1000, 562.5);

        Tile gaugeTile = DTT_TileBuilder.createSpeedGauge(270, 0, 1000);

        DTT_Tools.displaySeparateDTT(videoTile, gaugeTile);

    }

    @Override
    public void stop() {
        System.exit(0);
    }


    public static void main(String[] args) {
        launch(args);
    }
}

