package uah.cs499.drone.telemetry.dronetelemetryplayback;

import eu.hansolo.tilesfx.Tile;
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
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
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
        newStage.setTitle("");
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

    public static void displaySeparateDTT(final Node videoTile, final Node... NODES) {
        int NO_OF_NODES = NODES.length + 1;
        int NO_OF_COLS = 3; int NO_OF_ROWS = (int)Math.ceil((double)NO_OF_NODES / 3);

        /*
        Node[] NODESwVIDEO = new Node[NO_OF_NODES];
        NODESwVIDEO[0] = NODES[0]; NODESwVIDEO[1] = videoTile;
        System.arraycopy(NODES, 1, NODESwVIDEO, 2, NODES.length - 1);
        */


        Tile vT = (Tile)videoTile;
        Stage videoStage = displayTile(vT);
        //add a change listener, to resize pane as needed as scene is resized.
        ChangeListener<Number> videoSizeListener = (observable, oldValue, newValue) -> {
            vT.setPrefSize(videoStage.getScene().getWidth(), videoStage.getScene().getHeight());
            MediaView mV = (MediaView)vT.getGraphic();
            mV.setFitWidth(videoStage.getScene().getWidth());
            mV.setFitHeight(videoStage.getScene().getHeight());
        };
        videoStage.widthProperty().addListener(videoSizeListener);
        videoStage.heightProperty().addListener(videoSizeListener);

        int j = -1;
        for (int i = 0; i < NODES.length; i++)
        {
            Stage s = displayTile((Tile)NODES[i]);
            s.setX((i%NO_OF_COLS)*400);
            if ((i%NO_OF_COLS)==0)
                j++;

            s.setY((j%NO_OF_ROWS)*400);
        }
        //resize video




    }

    public static Stage displayUnifiedDTT(final Node videoTile, final Node... NODES) {

        int NO_OF_NODES = NODES.length + 1;
        Node[] NODESwVIDEO = new Node[NO_OF_NODES];
        NODESwVIDEO[0] = NODES[0]; NODESwVIDEO[1] = videoTile;
        System.arraycopy(NODES, 1, NODESwVIDEO, 2, NODES.length - 1);
            /* System.arraycopy same as...
            for(int i = 1; i < NODES.length; i++) {
                NODESwVIDEO[i+1] = NODES[i];
            }*/

        int NO_OF_COLS = 3; int NO_OF_ROWS = (int)Math.ceil((double)NO_OF_NODES / 3);

        //create Stage for tile to go onto
        Stage DTTStage = new Stage();
        //create FlowGridPane from TilesFX, add to a new scene.
        FlowGridPane DTTPane = new FlowGridPane(NO_OF_COLS, NO_OF_ROWS, NODESwVIDEO);
        DTTPane.setStyle("-fx-background-color: black;");
        Scene DTTScene = new Scene(DTTPane);
        DTTStage.setScene(DTTScene);

        //Set some params for flowgridpane
        DTTPane.setHgap(5); DTTPane.setVgap(5);
        DTTPane.setAlignment(Pos.CENTER);
        DTTPane.setCenterShape(true);
        DTTPane.setPadding(new Insets(5));
        //DTTPane.setPrefSize(1200, 720);
        DTTPane.setBackground(new Background(new BackgroundFill(Color.web("#101214"), CornerRadii.EMPTY, Insets.EMPTY)));

        DTTStage.initStyle(StageStyle.UTILITY);
        DTTStage.setTitle("Drone Telemetry Tool");

        //resize video
        Tile vT = (Tile)videoTile;
        vT.setPrefSize(400,500);
        MediaView mV = (MediaView)vT.getGraphic();

        mV.setFitWidth(400);

        //make Stage Visible
        DTTStage.show();

       return DTTStage;
    }
}
