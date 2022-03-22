package dronetelemetrytool;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class DTT_GUI {

    public static void barGaugeCreator() throws IOException {
        Stage newStage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("fxml/barGaugeCreator_view.fxml"));
        createStage(newStage, fxmlLoader);
    }
    public static void onOffGaugeCreator() throws IOException {
        Stage newStage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("fxml/onOffGaugeCreator_view.fxml"));
        createStage(newStage, fxmlLoader);
    }
    public static void characterGaugeCreator() throws IOException {
        Stage newStage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("fxml/characterGaugeCreator_view.fxml"));
        createStage(newStage, fxmlLoader);
    }
    public static void textGaugeCreator() throws IOException {
        Stage newStage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("fxml/textGaugeCreator_view.fxml"));
        createStage(newStage, fxmlLoader);
    }
    public static void timestampGaugeCreator() throws IOException {
        Stage newStage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("fxml/timestampGaugeCreator_view.fxml"));
        createStage(newStage, fxmlLoader);
    }
    public static void clockGaugeCreator() throws IOException {
        Stage newStage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("fxml/clockGaugeCreator_view.fxml"));
        createStage(newStage, fxmlLoader);
    }
    public static void circle180GaugeCreator() throws IOException {
        Stage newStage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("fxml/circle180GaugeCreator_view.fxml"));
        createStage(newStage, fxmlLoader);
    }
    public static void circle90GaugeCreator() throws IOException {
        Stage newStage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("fxml/circle90GaugeCreator_view.fxml"));
        createStage(newStage, fxmlLoader);
    }
    public static void circle270GaugeCreator() throws IOException {
        Stage newStage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("fxml/circle270GaugeCreator_view.fxml"));
        createStage(newStage, fxmlLoader);
    }
    public static void circle360GaugeCreator() throws IOException {
        Stage newStage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("fxml/circle360GaugeCreator_view.fxml"));
        createStage(newStage, fxmlLoader);
    }
    public static void xPlotGaugeCreator() throws IOException {
        Stage newStage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("fxml/xPlotGaugeCreator_view.fxml"));
        createStage(newStage, fxmlLoader);
    }

    private static void createStage(Stage s, FXMLLoader fxmlLoader) throws IOException {
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(MainApplication.class.getResource("fxml/DTT_Style.css").toExternalForm());
        s.setTitle("DTT - Gauge Creation Tool");
        s.setScene(scene);
        s.show();
        s.setMaxHeight(600); s.setMinHeight(470);
        s.setMaxWidth(1000); s.setMinWidth(800);
    }
}