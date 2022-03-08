package dronetelemetrytool;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class DTT_GUI {

    public static void barGaugeCreator() throws IOException {
        Stage newStage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("fxml/barGaugeCreator_view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(MainApplication.class.getResource("fxml/DTT_Style.css").toExternalForm());
        newStage.setTitle("DTT - Gauge Creation Tool");
        newStage.setScene(scene);
        newStage.show();
        newStage.setMaxHeight(600); newStage.setMinHeight(470);
        newStage.setMaxWidth(1000); newStage.setMinWidth(800);
    }
    public static void onOffGaugeCreator() throws IOException {
        Stage newStage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("fxml/onOffGaugeCreator_view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(MainApplication.class.getResource("fxml/DTT_Style.css").toExternalForm());
        newStage.setTitle("DTT - Gauge Creation Tool");
        newStage.setScene(scene);
        newStage.show();
        newStage.setMaxHeight(600); newStage.setMinHeight(470);
        newStage.setMaxWidth(1000); newStage.setMinWidth(800);
    }
    public static void characterGaugeCreator() throws IOException {
        Stage newStage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("fxml/characterGaugeCreator_view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(MainApplication.class.getResource("fxml/DTT_Style.css").toExternalForm());
        newStage.setTitle("DTT - Gauge Creation Tool");
        newStage.setScene(scene);
        newStage.show();
        newStage.setMaxHeight(600); newStage.setMinHeight(470);
        newStage.setMaxWidth(800); newStage.setMinWidth(600);
    }
    public static void textGaugeCreator() throws IOException {
        Stage newStage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("fxml/textGaugeCreator_view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(MainApplication.class.getResource("fxml/DTT_Style.css").toExternalForm());
        newStage.setTitle("DTT - Gauge Creation Tool");
        newStage.setScene(scene);
        newStage.show();
        newStage.setMaxHeight(600); newStage.setMinHeight(470);
        newStage.setMaxWidth(800); newStage.setMinWidth(600);
    }
}
