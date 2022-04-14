package dronetelemetrytool;

import dronetelemetrytool.fieldparsing.BoolField;
import dronetelemetrytool.fieldparsing.Field;
import dronetelemetrytool.fieldparsing.NumberField;
import dronetelemetrytool.fieldparsing.StringField;
import dronetelemetrytool.fxml.GaugeSelector;
import dronetelemetrytool.fxml.XPlotGaugeCreator;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class DTT_GUI {

    public static void barGaugeCreator(NumberField nf) throws IOException {
        Stage newStage = new Stage();
        newStage.setTitle("DTT - Gauge Creation Tool");
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("fxml/barGaugeCreator_view.fxml"));
        createStage(newStage, fxmlLoader);
    }
    public static void onOffGaugeCreator(BoolField bf) throws IOException {
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
    public static void textGaugeCreator(StringField sf) throws IOException {
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
    public static void clockGaugeCreator(NumberField nf) throws IOException {
        Stage newStage = new Stage();
        newStage.setTitle("DTT - Gauge Creation Tool");
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("fxml/clockGaugeCreator_view.fxml"));
        createStage(newStage, fxmlLoader);
    }
    public static void circle180GaugeCreator(NumberField nf) throws IOException {
        Stage newStage = new Stage();
        newStage.setTitle("DTT - Gauge Creation Tool");
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("fxml/circle180GaugeCreator_view.fxml"));
        createStage(newStage, fxmlLoader);
    }
    public static void circle90GaugeCreator(NumberField nf) throws IOException {
        Stage newStage = new Stage();
        newStage.setTitle("DTT - Gauge Creation Tool");
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("fxml/circle90GaugeCreator_view.fxml"));
        createStage(newStage, fxmlLoader);
    }
    public static void circle270GaugeCreator(NumberField nf) throws IOException {
        Stage newStage = new Stage();
        newStage.setTitle("DTT - Gauge Creation Tool");
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("fxml/circle270GaugeCreator_view.fxml"));
        createStage(newStage, fxmlLoader);
    }
    public static void circle360GaugeCreator(NumberField nf) throws IOException {
        Stage newStage = new Stage();
        newStage.setTitle("DTT - Gauge Creation Tool");
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("fxml/circle360GaugeCreator_view.fxml"));
        createStage(newStage, fxmlLoader);
    }

    // Using this gauge as a proof-of-concept for passing Fields down the pipeline
    // and setting values like statistics and units.
    public static void xPlotGaugeCreator(NumberField nf) throws IOException {
        Stage newStage = new Stage();
        newStage.setTitle("DTT - Gauge Creation Tool");
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("fxml/xPlotGaugeCreator_view.fxml"));
        createStage(newStage, fxmlLoader);
    }

    // todo: add second field parameter
    public static void xyPlotGaugeCreator(NumberField nf) throws IOException {
        Stage newStage = new Stage();
        newStage.setTitle("DTT - Gauge Creation Tool");
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("fxml/xyPlotGaugeCreator_view.fxml"));
        XPlotGaugeCreator controller = fxmlLoader.getController();
        controller.setField(nf);
        createStage(newStage, fxmlLoader);
    }

    public static void inputSelector() throws IOException {
        Stage newStage = new Stage();
        newStage.setTitle("DTT - Input Data Selector Tool");
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("fxml/inputsSelector_view.fxml"));
        createStage(newStage, fxmlLoader);
    }

    public static void fieldSelection() throws IOException {
        Stage newStage = new Stage();
        newStage.setTitle("DTT - Field Selection Tool");
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("fxml/fieldSelection_view.fxml"));
        createStage(newStage, fxmlLoader);
    }

    public static void frequencySelector() throws IOException {
        Stage newStage = new Stage();
        newStage.setTitle("DTT - Frequency Selector Tool");
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("fxml/frequencySelector_view.fxml"));
        createStage(newStage, fxmlLoader);
    }

    public static void gaugeSelector() throws IOException {
        Stage newStage = new Stage();
        newStage.setTitle("DTT - Gauge Selector Tool");
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("fxml/gaugeSelector_view.fxml"));
        createStage(newStage, fxmlLoader);
    }

    public static void gaugeSelector(Field relatedField) throws IOException {
        Stage newStage = new Stage();
        newStage.setTitle("DTT - Gauge Selector Tool");
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("fxml/gaugeSelector_view.fxml"));

        GaugeSelector controller = new GaugeSelector();
        controller.setField(relatedField);
        fxmlLoader.setController(controller);
        Scene scene = new Scene(fxmlLoader.load());
        controller.limitOptions();

        newStage.setScene(scene);
        createStage(newStage);
    }

    public static void unitSelector(List<NumberField> nfs) {
        Stage newStage = new Stage();
        newStage.setTitle("DTT - Unit Selection");
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("fxml/unitSelector_view.fxml"));

    }

    private static void createStage(Stage s, FXMLLoader fxmlLoader) throws IOException {
        Scene scene = new Scene(fxmlLoader.load());
        s.setScene(scene);
        createStage(s);
    }
    private static void createStage(Stage s) throws IOException {
        Scene scene = s.getScene();
        scene.getStylesheets().add(MainApplication.class.getResource("fxml/DTT_Style.css").toExternalForm());
        s.show();
        s.setMaxHeight(1000);
        s.setMinHeight(470);
        s.setMaxWidth(1500);
        s.setMinWidth(400);
    }

}