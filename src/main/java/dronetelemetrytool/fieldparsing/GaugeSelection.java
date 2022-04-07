package dronetelemetrytool.fieldparsing;

import dronetelemetrytool.fxml.InputsSelector;
import dronetelemetrytool.MainApplication;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class GaugeSelection extends Application {
    FXMLLoader fxmlLoader;
    Scene scene;
    Stage fileStage;

    @Override
    public void start(Stage stage) throws Exception {
        fileStage = stage;
        fxmlLoader = new FXMLLoader(MainApplication.class.getResource("fxml/inputsSelector_view.fxml"));
       // Parent root = fxmlLoader.load();

        scene = new Scene(fxmlLoader.load(), 320, 240, Color.DARKSLATEGRAY);
        InputsSelector csvControl = fxmlLoader.getController();

        csvControl.anounce();

        fileStage.setTitle("File Selection");
        fileStage.setScene(scene);
        csvControl.setStage(fileStage);

        //fileMenu = new Menu("File");
        //item = new MenuItem("Open Csv");

        System.out.println("Initializing CSV");

        stage.show();

    }

    public static void main(String[] args) {
//        Long myLong = Long.parseLong("422345");
//        System.out.println(myLong);
        launch();
    }

}