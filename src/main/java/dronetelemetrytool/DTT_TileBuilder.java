package dronetelemetrytool;

import eu.hansolo.tilesfx.Tile;
import eu.hansolo.tilesfx.TileBuilder;
import eu.hansolo.tilesfx.chart.ChartData;
import eu.hansolo.tilesfx.colors.Bright;
import eu.hansolo.toolboxfx.GradientLookup;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.time.Duration;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;

public class DTT_TileBuilder {

    //private static final Random RND = new Random();
    private static final double TILE_SIZE = 350;

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
    public static Tile createVideoTile(Media media, double width, double height) {
        // Create the MediaPlayer and set to play automatically.
        MediaPlayer mediaPlayer = new MediaPlayer(media); mediaPlayer.setAutoPlay(true);
        // Place the mediaPlayer into a MediaView
        MediaView mediaView = new MediaView(mediaPlayer);
        // Pass the mediaView into a new custom tile;
        Tile newTile = TileBuilder.create()
                .skinType(Tile.SkinType.CUSTOM)
                .graphic(mediaView)
                .prefSize(width, height)
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

    public static Tile createOnOffGauge()
    {
        Tile onOffTile = TileBuilder.create()
                .skinType(Tile.SkinType.LED)
                .prefSize(TILE_SIZE, TILE_SIZE)
                .title("OnOff Title")
                .build();

        return onOffTile;
    }

    public static Tile createBarGauge(ChartData cD)
    {

        GradientLookup gradient = new GradientLookup(Arrays.asList(
                new Stop(0.0, Bright.GREEN),
                new Stop(0.4, Bright.YELLOW),
                new Stop(0.8, Bright.RED)));

        cD.setFillColor(gradient.getColorAt(cD.getValue() / 100));

        Tile barChartTile = TileBuilder.create()
                .skinType(Tile.SkinType.CUSTOM)
                .prefSize(TILE_SIZE, TILE_SIZE)
                .title("Bar Title")
                .chartData(cD)
                .build();

        barChartTile.setSkin(new SingleBarTileSkin(barChartTile));

        return barChartTile;
    }

    public static Tile createCountdownGague() {
        Tile newTile = TileBuilder.create()
                .skinType(Tile.SkinType.COUNTDOWN_TIMER)
                .timePeriod(Duration.ofSeconds(60))
                .build();

        newTile.setRunning(true);

        return newTile;
    }


    public static Tile createStopwatchGague() {
//        Image image = new Image("D:\\School\\CS499\\Resources\\stopwatch-gc3fa4e81c_1280.png");
//        ImageView imageView = new ImageView();
//        imageView.setImage(image);

        Text text = new Text(Long.toString(0));
        text.setFont(new Font("lato",69));
        text.setFill(Paint.valueOf(String.valueOf(Color.BLUEVIOLET)));

        Tile newTile = TileBuilder.create()
                .skinType(Tile.SkinType.CUSTOM)
                .backgroundColor(Color.BEIGE)
                //.text("0")
                //.textAlignment(TextAlignment.CENTER)
                //.textColor(Color.BLUEVIOLET)
                //.graphic(imageView)
                .graphic(text)
                //.textSize(Tile.TextSize.BIGGER)
                .duration(LocalTime.MAX)
                .timePeriod(ChronoUnit.FOREVER.getDuration())
                .prefSize(TILE_WIDTH, TILE_HEIGHT)
                .value(0)
                .build();

        newTile.setRunning(true);

        return newTile;
    }
}
