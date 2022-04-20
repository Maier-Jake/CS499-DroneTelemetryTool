package dronetelemetrytool;

import dronetelemetrytool.fxml.PopupController;
import eu.hansolo.tilesfx.Tile;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

public class DTT_Tools {

    public static File changeExtension(File f, String newExtensionWithDot) {
        int i = f.getName().lastIndexOf('.');
        String name = f.getName().substring(0,i);
        return new File(f.getParent(), name + newExtensionWithDot);
    }

    public static Stage displayVideo(Media media)
    {
        //create Stage for tile to go onto
        Stage newStage = new Stage();

        BorderPane borderPane = new BorderPane();
        borderPane.setStyle("-fx-background-color: black;");
        Scene s = new Scene(borderPane);

        // Create the MediaPlayer and set to play automatically.
        MediaPlayer mediaPlayer = new MediaPlayer(media); mediaPlayer.setAutoPlay(false);
        // Place the mediaPlayer into a MediaView
        MediaView mediaView = new MediaView(mediaPlayer);
        mediaView.setFitHeight(500);

        // Pass the mediaView into a new custom tile;

        Text saveLayoutText = new Text("Save Layout");
        saveLayoutText.setFont(Font.font("Lato", 25));
        Text reverseText = new Text("◀");
        reverseText.setFont(Font.font("Lato", 25));
        Text playText = new Text("⏯");
        playText.setFont(Font.font("Lato", 25));
        Text forwardText = new Text("▶");
        forwardText.setFont(Font.font("Lato", 25));
        Text fivespeedText = new Text("⏭ 5x");
        fivespeedText.setFont(Font.font("Lato", 25));
        Text tenspeedText = new Text("⏭ 10x");
        tenspeedText.setFont(Font.font("Lato", 25));

        Button saveLayout = new Button("", saveLayoutText);
        Button reverse = new Button("", reverseText);
        Button play = new Button("", playText);
        Button forward = new Button("", forwardText);
        Button fivespeed = new Button("", fivespeedText);
        Button tenspeed = new Button("", tenspeedText);

        saveLayout.setPrefSize(200,50);
        reverse.setPrefSize(200,50);
        play.setPrefSize(200,50);
        forward.setPrefSize(200,50);
        fivespeed.setPrefSize(200,50);
        tenspeed.setPrefSize(200,50);

        HBox hBox = new HBox(saveLayout,reverse,play,forward,fivespeed,tenspeed);

        borderPane.setCenter(mediaView);
        borderPane.setBottom(hBox);
        BorderPane.setAlignment(hBox, Pos.BOTTOM_CENTER);

        newStage.setTitle("Drone Telemetry Tool");
        newStage.setScene(s);
        newStage.show();

        return newStage;
    }

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

        //newStage.setHeight(TILE_HEIGHT); newStage.setWidth(TILE_WIDTH);

        //newStage.setMinHeight(220); newStage.setMaxHeight(1000);
        //newStage.setMinWidth(220); newStage.setMaxWidth(1000);

        //add a change listener, to resize pane as needed as scene is resized.
        ChangeListener<Number> stageSizeListener = (observable, oldValue, newValue) ->
                tile.setPrefSize(scene.getWidth(), scene.getHeight());

        newStage.widthProperty().addListener(stageSizeListener);
        newStage.heightProperty().addListener(stageSizeListener);

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

        /*
        Tile vT = (Tile)videoTile;
        Stage videoStage = displayTile(vT);
        videoStage.setMinHeight(360); videoStage.setMinWidth(640);
        MediaView mV = (MediaView)vT.getGraphic();
        mV.setFitWidth(videoStage.getScene().getWidth());
        mV.setFitHeight(videoStage.getScene().getHeight());

        //add a change listener, to resize pane as needed as scene is resized.
        ChangeListener<Number> videoSizeListener = (observable, oldValue, newValue) -> {
            vT.setPrefSize(videoStage.getScene().getWidth(), videoStage.getScene().getHeight());
            mV.setFitWidth(videoStage.getScene().getWidth());
            mV.setFitHeight(videoStage.getScene().getHeight());
        };


        videoStage.widthProperty().addListener(videoSizeListener);
        videoStage.heightProperty().addListener(videoSizeListener);
        */
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

    public static Stage popup(Stage parent, String error) throws IOException {

        Stage stage = new Stage();
        stage.initStyle(StageStyle.UTILITY);
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("fxml/popup_view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(MainApplication.class.getResource("fxml/DTT_Style.css").toExternalForm());

        stage.setTitle("DTT - Error");
        stage.setScene(scene);

        PopupController popup = fxmlLoader.getController();
        popup.initData(error);

        stage.initOwner(parent);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();

        return stage;
    }

    public static double normalize(double inputS, double inputE, double val)
    {
        double newVal = 0.0;

        newVal = (val - inputS) / (inputE - inputS);

        return newVal;
    }
    
    public static double map (double value, double iStart, double iStop, double oStart, double oStop)
    {
        return oStart + (oStop - oStart) * ((value - iStart) / (iStop - iStart));
    }
}
