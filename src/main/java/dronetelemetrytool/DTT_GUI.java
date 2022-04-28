package dronetelemetrytool;

import dronetelemetrytool.fieldparsing.*;
import dronetelemetrytool.fxml.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class DTT_GUI {

    public static void videoPlayer() throws IOException {
        Stage newStage = new Stage();
        newStage.setTitle("DTT - Video Playback");
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("fxml/videoPlayer_view.fxml"));

        newStage.setOnCloseRequest(event -> {
            System.out.println("Stage is closing");

            System.exit(0);
        });

        createStage(newStage, fxmlLoader);
    }

    public static void barGaugeCreator(Stage parent, NumberField relatedField) throws IOException {
        Stage newStage = new Stage();
        newStage.setTitle("DTT - Gauge Creation Tool");
        newStage.initOwner(parent);
        newStage.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("fxml/barGaugeCreator_view.fxml"));
        BarGaugeCreator controller = new BarGaugeCreator();
        fxmlLoader.setController(controller);
        Scene scene = new Scene(fxmlLoader.load());
        controller.setField(relatedField);
        newStage.setScene(scene);
        createStage(newStage);
    }
    public static void onOffGaugeCreator(Stage parent, BoolField relatedField) throws IOException {
        Stage newStage = new Stage();
        newStage.setTitle("DTT - Gauge Creation Tool");
        newStage.initOwner(parent);
        newStage.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("fxml/onOffGaugeCreator_view.fxml"));
        OnOffGaugeCreator controller = new OnOffGaugeCreator();
        fxmlLoader.setController(controller);
        Scene scene = new Scene(fxmlLoader.load());
        controller.setField(relatedField);
        newStage.setScene(scene);
        createStage(newStage);
    }
    public static void characterGaugeCreator(Stage parent, StringField relatedField) throws IOException {
        Stage newStage = new Stage();
        newStage.setTitle("DTT - Gauge Creation Tool");
        newStage.initOwner(parent);
        newStage.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("fxml/characterGaugeCreator_view.fxml"));
        CharacterGaugeCreator controller = new CharacterGaugeCreator();
        fxmlLoader.setController(controller);
        Scene scene = new Scene(fxmlLoader.load());
        controller.setField(relatedField);
        newStage.setScene(scene);
        createStage(newStage);
    }
    public static void textGaugeCreator(Stage parent, StringField relatedField) throws IOException {
        Stage newStage = new Stage();
        newStage.setTitle("DTT - Gauge Creation Tool");
        newStage.initOwner(parent);
        newStage.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("fxml/textGaugeCreator_view.fxml"));
        TextGaugeCreator controller = new TextGaugeCreator();
        fxmlLoader.setController(controller);
        Scene scene = new Scene(fxmlLoader.load());
        controller.setField(relatedField);
        newStage.setScene(scene);
        createStage(newStage);
    }
    public static void timestampGaugeCreator(Stage parent, TimeField relatedField) throws IOException {
        Stage newStage = new Stage();
        newStage.setTitle("DTT - Gauge Creation Tool");
        newStage.initOwner(parent);
        newStage.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("fxml/timestampGaugeCreator_view.fxml"));
        TimestampGaugeCreator controller = new TimestampGaugeCreator();
        fxmlLoader.setController(controller);
        Scene scene = new Scene(fxmlLoader.load());
        controller.setField(relatedField);
        newStage.setScene(scene);
        createStage(newStage);
    }
    public static void clockGaugeCreator(Stage parent, TimeField relatedField) throws IOException {
        Stage newStage = new Stage();
        newStage.setTitle("DTT - Gauge Creation Tool");
        newStage.initOwner(parent);
        newStage.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("fxml/clockGaugeCreator_view.fxml"));
        ClockGaugeCreator controller = new ClockGaugeCreator();
        fxmlLoader.setController(controller);
        Scene scene = new Scene(fxmlLoader.load());
        controller.setField(relatedField);
        newStage.setScene(scene);
        createStage(newStage);
    }
    public static void circle180GaugeCreator(Stage parent, NumberField relatedField) throws IOException {
        Stage newStage = new Stage();
        newStage.setTitle("DTT - Gauge Creation Tool");
        newStage.initOwner(parent);
        newStage.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("fxml/circle180GaugeCreator_view.fxml"));
        Circle180GaugeCreator controller = new Circle180GaugeCreator();
        fxmlLoader.setController(controller);
        Scene scene = new Scene(fxmlLoader.load());
        controller.setField(relatedField);
        newStage.setScene(scene);
        createStage(newStage);
    }
    public static void circle90GaugeCreator(Stage parent, NumberField relatedField) throws IOException {
        Stage newStage = new Stage();
        newStage.setTitle("DTT - Gauge Creation Tool");
        newStage.initOwner(parent);
        newStage.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("fxml/circle90GaugeCreator_view.fxml"));
        Circle90GaugeCreator controller = new Circle90GaugeCreator();
        fxmlLoader.setController(controller);
        Scene scene = new Scene(fxmlLoader.load());
        controller.setField(relatedField);
        newStage.setScene(scene);
        createStage(newStage);
    }
    public static void circle270GaugeCreator(Stage parent, NumberField relatedField) throws IOException {
        Stage newStage = new Stage();
        newStage.setTitle("DTT - Gauge Creation Tool");
        newStage.initOwner(parent);
        newStage.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("fxml/circle270GaugeCreator_view.fxml"));
        Circle270GaugeCreator controller = new Circle270GaugeCreator();
        fxmlLoader.setController(controller);
        Scene scene = new Scene(fxmlLoader.load());
        controller.setField(relatedField);
        newStage.setScene(scene);
        createStage(newStage);
    }
    public static void circle360GaugeCreator(Stage parent, NumberField relatedField) throws IOException {
        Stage newStage = new Stage();
        newStage.setTitle("DTT - Gauge Creation Tool");
        newStage.initOwner(parent);
        newStage.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("fxml/circle360GaugeCreator_view.fxml"));
        Circle360GaugeCreator controller = new Circle360GaugeCreator();
        fxmlLoader.setController(controller);
        Scene scene = new Scene(fxmlLoader.load());
        controller.setField(relatedField);
        newStage.setScene(scene);
        createStage(newStage);
    }

    // Using this gauge as a proof-of-concept for passing Fields down the pipeline
    // and setting values like statistics and units.
    public static void xPlotGaugeCreator(Stage parent, NumberField relatedField) throws IOException {
        Stage newStage = new Stage();
        newStage.setTitle("DTT - Gauge Creation Tool");
        newStage.initOwner(parent);
        newStage.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("fxml/xPlotGaugeCreator_view.fxml"));
        XPlotGaugeCreator controller = new XPlotGaugeCreator();
        fxmlLoader.setController(controller);
        Scene scene = new Scene(fxmlLoader.load());
        controller.setField(relatedField);
        newStage.setScene(scene);
        createStage(newStage);
    }

    public static void xyPlotGaugeCreator(Stage parent, NumberField relatedXField, NumberField relatedYField) throws IOException {
        Stage newStage = new Stage();
        newStage.setTitle("DTT - Gauge Creation Tool");
        newStage.initOwner(parent);
        newStage.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("fxml/xyPlotGaugeCreator_view.fxml"));
        XYPlotGaugeCreator controller = new XYPlotGaugeCreator();
        fxmlLoader.setController(controller);
        Scene scene = new Scene(fxmlLoader.load());
        controller.setxField(relatedXField);
        controller.setyField(relatedYField);
        newStage.setScene(scene);
        createStage(newStage);
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

    public static void gaugeSelector(Stage parent, Field relatedField) throws IOException {
        Stage newStage = new Stage();
        newStage.setTitle("DTT - Gauge Selector Tool");
        newStage.initOwner(parent);
        newStage.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("fxml/gaugeSelector_view.fxml"));

        GaugeSelector controller = new GaugeSelector();
        controller.setField(relatedField);
        controller.setParent(parent);
        fxmlLoader.setController(controller);
        Scene scene = new Scene(fxmlLoader.load());
        controller.limitOptions();

        newStage.setScene(scene);
        createStage(newStage);
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

    public static void setupSelector() throws IOException {
        Stage newStage = new Stage();
        newStage.setTitle("DTT - Setup Selector");
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("fxml/setupSelector_view.fxml"));
        createStage(newStage, fxmlLoader);
    }
}