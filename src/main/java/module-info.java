/**
 *
 */
module droneTelemetryTool {
    requires eu.hansolo.tilesfx;
    requires org.kordamp.ikonli.javafx;
    requires javafx.fxml;
    requires com.opencsv;
    requires org.apache.commons.io;

    opens dronetelemetrytool.fxml to javafx.fxml;
    exports dronetelemetrytool;
    exports dronetelemetrytool.gauges;
    exports dronetelemetrytool.skins;
    exports dronetelemetrytool.fxml;
    exports dronetelemetrytool.fieldparsing;
}