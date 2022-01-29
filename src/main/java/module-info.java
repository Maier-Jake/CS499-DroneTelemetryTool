module uah.cs499.drone.telemetry.dronetelemetryplayback {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires javafx.media;

    opens uah.cs499.drone.telemetry.dronetelemetryplayback to javafx.fxml;
    exports uah.cs499.drone.telemetry.dronetelemetryplayback;
}