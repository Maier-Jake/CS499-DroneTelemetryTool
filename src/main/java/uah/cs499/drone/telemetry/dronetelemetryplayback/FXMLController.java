package uah.cs499.drone.telemetry.dronetelemetryplayback;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class FXMLController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}