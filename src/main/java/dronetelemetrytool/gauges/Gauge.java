package dronetelemetrytool.gauges;

import eu.hansolo.tilesfx.Tile;
import eu.hansolo.tilesfx.TileBuilder;
import javafx.beans.value.ChangeListener;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.Random;

public abstract class Gauge {

    final int TILE_SIZE = 250;
    public Tile tile;
    final Random RND = new Random();

    public Gauge()
    {
        tile = TileBuilder.create()
                .skinType(Tile.SkinType.CUSTOM)
                .prefSize(TILE_SIZE, TILE_SIZE)
                .title("Gauge")
                .build();
    }

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

//        newStage.setMinHeight(220); newStage.setMaxHeight(1000);
//        newStage.setMinWidth(220); newStage.setMaxWidth(1000);

        //add a change listener, to resize pane as needed as scene is resized.
        ChangeListener<Number> stageSizeListener = (observable, oldValue, newValue) ->
                tile.setPrefSize(scene.getWidth(), scene.getHeight());

        newStage.widthProperty().addListener(stageSizeListener);
        newStage.heightProperty().addListener(stageSizeListener);

        newStage.show();
    }

    public abstract void update();


}

