package dronetelemetrytool;

import eu.hansolo.tilesfx.Tile;
import javafx.application.Application;
import javafx.scene.media.Media;
import javafx.stage.Stage;

import java.io.File;
import java.net.MalformedURLException;

public class MainApplication extends Application {

    private static final double TILE_WIDTH = 250;
    private static final double TILE_HEIGHT = 250;

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

        Tile videoTile = DTT_TileBuilder.createVideoTile(media, 1000, 562.5);

        Tile gaugeTile = DTT_TileBuilder.createSpeedGauge(270, 0, 1000);

        Tile countdownTile = DTT_TileBuilder.createCountdownGague();

        Tile stopwatchTile = DTT_TileBuilder.createStopwatchGague();

        DTT_Tools.displaySeparateDTT(videoTile, gaugeTile, countdownTile, stopwatchTile);

    }

    @Override
    public void stop() {
        System.exit(0);
    }


    public static void main(String[] args) {
        launch(args);
    }
}

