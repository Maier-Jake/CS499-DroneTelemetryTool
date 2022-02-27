package dronetelemetrytool.gaugeselection

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class GaugeSelection extends Application {
    FXMLLoader fxmlLoader;
    Scene scene;
    Stage csvStage;

    @Override
    public void start(Stage stage) throws Exception {
        csvStage = stage;
        fxmlLoader = new FXMLLoader(GaugeSelection.class.getResource("Chooser.fxml"));
       // Parent root = fxmlLoader.load();

        scene = new Scene(fxmlLoader.load(), 320, 240, Color.DARKSLATEGRAY);
        ChooserController csvControl = fxmlLoader.getController();

        csvControl.anounce();

        csvStage.setTitle("CSV Selection");
        csvStage.setScene(scene);
        csvControl.setStage(csvStage);

        //fileMenu = new Menu("File");
        //item = new MenuItem("Open Csv");

        System.out.println("Initializing CSV");

        stage.show();

    }

    public static void main(String[] args) {
        Long myLong = Long.parseLong("422345");
        System.out.println(myLong);
        launch();
    }

}