package dronetelemetrytool.gauges;

import java.io.Serializable;

public class GaugesInfo implements Serializable {
    Gauge.GaugeType type;
    String fieldName;
    String gaugeTitle;
    String axisLabel;
    double min;
    double max;
    double tick;
    String yaxisLabel;
    double ymin;
    double ymax;
    double ytick;
    String origUnit;
    String unitType;
    String desUnit;
    String yorigUnit;
    String yunitType;
    String ydesUnit;
    double gThresh;
    double yThresh;
    double rThresh;
    int warning;
    String color;
    double xPos;
    double yPos;
    double width;
    double height;
}
