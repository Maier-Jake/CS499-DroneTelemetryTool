package dronetelemetrytool;

import eu.hansolo.tilesfx.Tile;
import eu.hansolo.tilesfx.TileBuilder;
import eu.hansolo.tilesfx.chart.ChartData;
import eu.hansolo.tilesfx.colors.Bright;
import eu.hansolo.toolboxfx.GradientLookup;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.time.Duration;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;

public class DTT_TileBuilder {

    private static final double TILE_WIDTH = 400;
    private static final double TILE_HEIGHT = 400;

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
