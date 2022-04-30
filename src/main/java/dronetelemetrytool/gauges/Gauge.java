package dronetelemetrytool.gauges;

import dronetelemetrytool.fieldparsing.Field;
import eu.hansolo.tilesfx.Tile;
import eu.hansolo.tilesfx.TileBuilder;
import javafx.beans.value.ChangeListener;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.Random;

public abstract class Gauge implements java.io.Serializable {

    public transient MediaPlayer mediaPlayer;

    public enum GaugeType {
        BAR,
        CHARACTER,
        CIRCLE90,
        CIRCLE180,
        CIRCLE270,
        CIRCLE360,
        CLOCK,
        ONOFF,
        TEXT,
        TIMESTAMP,
        XPLOT,
        XYPLOT
    };

    public GaugeType gaugeType;
    final transient int TILE_SIZE = 250;
    public transient Tile tile;
    //final Random RND = new Random();

    public Gauge()
    {
        mediaPlayer = null;
        tile = TileBuilder.create()
            .skinType(Tile.SkinType.CUSTOM)
            .prefSize(TILE_SIZE, TILE_SIZE)
            .title("Gauge")
            .build();
    }

    public abstract Field getField();

    public void display()
    {
        //create Stage for tile to go onto
        Stage newStage = new Stage();

        //create basic Pane with Tile on it, add to a new Scene
        Pane newPane = new Pane(tile);

        newPane.setStyle("-fx-background-color: black;");
        Scene scene = new Scene(newPane);

        //display Scene on Stage
        newStage.setScene(scene);

        //give Stage sample title, set it to only have 'X' button
        newStage.initStyle(StageStyle.UTILITY);
        newStage.setTitle("");

        //make Stage Visible
        newStage.show();

        //add a change listener, to resize pane as needed as scene is resized.
        ChangeListener<Number> stageSizeListener = (observable, oldValue, newValue) ->
                tile.setPrefSize(scene.getWidth(), scene.getHeight());

        newStage.widthProperty().addListener(stageSizeListener);
        newStage.heightProperty().addListener(stageSizeListener);

        newStage.show();
    }

    public void setTitle(String tit)
    {
        tile.setTitle(tit);
    }

    public abstract void update();


}

