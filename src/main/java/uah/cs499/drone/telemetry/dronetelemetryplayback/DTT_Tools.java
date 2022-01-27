package uah.cs499.drone.telemetry.dronetelemetryplayback;

import eu.hansolo.tilesfx.Tile;
import javafx.beans.value.ChangeListener;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class DTT_Tools {

    public static Stage displayTile(Tile tile) {
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
        newStage.setTitle("New Stage Title");
        //make Stage Visible
        newStage.show();

        //add a change listener, to resize pane as needed as scene is resized.
        ChangeListener<Number> stageSizeListener = (observable, oldValue, newValue) ->
                tile.setPrefSize(scene.getWidth(), scene.getHeight());

        newStage.widthProperty().addListener(stageSizeListener);
        newStage.heightProperty().addListener(stageSizeListener);
        newStage.setMinHeight(150);
        newStage.setMinWidth(150);

        return newStage;
    }
}
