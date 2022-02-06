package uah.cs499.drone.telemetry.dronetelemetryplayback;

import eu.hansolo.tilesfx.Tile;
import eu.hansolo.tilesfx.TileBuilder;
import eu.hansolo.tilesfx.tools.FlowGridPane;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.paint.Stop;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.net.MalformedURLException;
import java.util.Random;

public class DTT_TileBuilder {

    //private static final Random RND = new Random();
    private static final double TILE_WIDTH = 400;
    private static final double TILE_HEIGHT = 400;
    private static final double VIDEO_WIDTH = 640;
    private static final double VIDEO_HEIGHT = 360;

    public static Tile createVideoTile(Media media) {
        // Create the MediaPlayer and set to play automatically.
        MediaPlayer mediaPlayer = new MediaPlayer(media); mediaPlayer.setAutoPlay(true);
        // Place the mediaPlayer into a MediaView
        MediaView mediaView = new MediaView(mediaPlayer);
        // Pass the mediaView into a new custom tile;
        Tile newTile = TileBuilder.create()
                .skinType(Tile.SkinType.CUSTOM)
                .graphic(mediaView)
                .prefSize(VIDEO_WIDTH, VIDEO_HEIGHT)
                .roundedCorners(false)
                .build();
        return newTile;
    }

    public static Tile createSpeedGauge(int angleRange, int minVal, int maxVal) {

        Tile newTile = TileBuilder.create()
                .skinType(Tile.SkinType.GAUGE2)
                .prefSize(TILE_WIDTH, TILE_HEIGHT)

                //AngleRange is used to change how much of the circle is usable. 0-360.
                .angleRange(angleRange)

                //Min and Max Values determine the range of possible values for the gauge
                .minValue(minVal)
                .maxValue(maxVal)

                .minValueVisible(false)
                .maxValueVisible(false)

                //unit
                .unit("mph")

                /*
                sections: start/stop region for each, and a color.
                */

                .sections(  new eu.hansolo.tilesfx.Section(0,25, Tile.BLUE),
                            new eu.hansolo.tilesfx.Section(25, 50, Tile.LIGHT_GREEN),
                            new eu.hansolo.tilesfx.Section(50, 75, Tile.YELLOW),
                            new eu.hansolo.tilesfx.Section(75, 100, Tile.LIGHT_RED))
                .sectionsVisible(true)

                /*
                Bar Color
                tile.getBarColor();
                .barColor(Tile.YELLOW_ORANGE)
                 */
                .barColor(Tile.PINK)
                .barBackgroundColor(Color.rgb(255, 255, 255, 0.1))

                /*
                Threshold Color
                tile.getThresholdColor();
                .threshold
                 */

                //.title("Gauge Tile")

                .animated(true)
                .textVisible(false)

                .highlightSections(true)
                .strokeWithGradient(true)
                .gradientStops(new Stop(0.0, Tile.BLUE),
                        new Stop(0.25, Tile.BLUE),
                        new Stop(0.25, Tile.LIGHT_GREEN),
                        new Stop(0.50, Tile.LIGHT_GREEN),
                        new Stop(0.50, Tile.YELLOW),
                        new Stop(0.75, Tile.YELLOW),
                        new Stop(0.75, Tile.LIGHT_RED),
                        new Stop(1.0, Tile.LIGHT_RED))
                .build();

        newTile.setValue(100);
        return newTile;
    }
}
