package dronetelemetrytool;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class DTT_GUI {

    public static void barGaugeCreator() throws IOException {
        Stage newStage = new Stage();
        newStage.setTitle("DTT - Gauge Creation Tool");
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("fxml/barGaugeCreator_view.fxml"));
        createStage(newStage, fxmlLoader);
    }
    public static void onOffGaugeCreator() throws IOException {
        Stage newStage = new Stage();
        newStage.setTitle("DTT - Gauge Creation Tool");
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("fxml/onOffGaugeCreator_view.fxml"));
        createStage(newStage, fxmlLoader);
    }
    public static void characterGaugeCreator() throws IOException {
        Stage newStage = new Stage();
        newStage.setTitle("DTT - Gauge Creation Tool");
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("fxml/characterGaugeCreator_view.fxml"));
        createStage(newStage, fxmlLoader);
    }
    public static void textGaugeCreator() throws IOException {
        Stage newStage = new Stage();
        newStage.setTitle("DTT - Gauge Creation Tool");
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("fxml/textGaugeCreator_view.fxml"));
        createStage(newStage, fxmlLoader);
    }
    public static void timestampGaugeCreator() throws IOException {
        Stage newStage = new Stage();
        newStage.setTitle("DTT - Gauge Creation Tool");
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("fxml/timestampGaugeCreator_view.fxml"));
        createStage(newStage, fxmlLoader);
    }
    public static void clockGaugeCreator() throws IOException {
        Stage newStage = new Stage();
        newStage.setTitle("DTT - Gauge Creation Tool");
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("fxml/clockGaugeCreator_view.fxml"));
        createStage(newStage, fxmlLoader);
    }
    public static void circle180GaugeCreator() throws IOException {
        Stage newStage = new Stage();
        newStage.setTitle("DTT - Gauge Creation Tool");
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("fxml/circle180GaugeCreator_view.fxml"));
        createStage(newStage, fxmlLoader);
    }
    public static void circle90GaugeCreator() throws IOException {
        Stage newStage = new Stage();
        newStage.setTitle("DTT - Gauge Creation Tool");
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("fxml/circle90GaugeCreator_view.fxml"));
        createStage(newStage, fxmlLoader);
    }
    public static void circle270GaugeCreator() throws IOException {
        Stage newStage = new Stage();
        newStage.setTitle("DTT - Gauge Creation Tool");
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("fxml/circle270GaugeCreator_view.fxml"));
        createStage(newStage, fxmlLoader);
    }
    public static void circle360GaugeCreator() throws IOException {
        Stage newStage = new Stage();
        newStage.setTitle("DTT - Gauge Creation Tool");
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("fxml/circle360GaugeCreator_view.fxml"));
        createStage(newStage, fxmlLoader);
    }
    public static void xPlotGaugeCreator() throws IOException {
        Stage newStage = new Stage();
        newStage.setTitle("DTT - Gauge Creation Tool");
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("fxml/xPlotGaugeCreator_view.fxml"));
        createStage(newStage, fxmlLoader);
    }

    public static void xyPlotGaugeCreator() throws IOException {
        Stage newStage = new Stage();
        newStage.setTitle("DTT - Gauge Creation Tool");
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("fxml/xyPlotGaugeCreator_view.fxml"));
        createStage(newStage, fxmlLoader);
    }

    public static void gaugeSelector() throws IOException {
        Stage newStage = new Stage();
        newStage.setTitle("DTT - Gauge Selector Tool");
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("fxml/gaugeSelector_view.fxml"));
        createStage(newStage, fxmlLoader);
    }

    public static void inputSelector() throws IOException {
        Stage newStage = new Stage();
        newStage.setTitle("DTT - Input Data Selector Tool");
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("fxml/inputsSelector_view.fxml"));
        createStage(newStage, fxmlLoader);
    }
    public static void videoPlayer() throws IOException {
        Stage newStage = new Stage();
        newStage.setTitle("DTT");
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("fxml/VideoPlayer_view.fxml"));
        createStage(newStage, fxmlLoader);
    }

    private static void createStage(Stage s, FXMLLoader fxmlLoader) throws IOException {
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(MainApplication.class.getResource("fxml/DTT_Style.css").toExternalForm());
        s.setScene(scene);
        s.show();
        s.setMaxHeight(1000); s.setMinHeight(470);
        s.setMaxWidth(1500); s.setMinWidth(400);
    }


}