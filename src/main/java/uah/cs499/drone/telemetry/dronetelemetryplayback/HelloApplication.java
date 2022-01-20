package uah.cs499.drone.telemetry.dronetelemetryplayback;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 620, 240);
        stage.setTitle("Stage Title!");
        stage.setScene(scene);
        stage.show();

        Stage newStage = new Stage();
        newStage.setTitle("newstage title");

        FXMLLoader fxmlLoader2 = new FXMLLoader(HelloApplication.class.getResource("test-view2.fxml"));
        Scene scene2 = new Scene(fxmlLoader2.load(), 300, 300);
        newStage.setScene(scene2);
        newStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}