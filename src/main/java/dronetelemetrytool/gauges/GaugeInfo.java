package dronetelemetrytool.gauges;

import java.io.Serializable;

public class GaugeInfo implements Serializable {
    public Gauge.GaugeType type;
    public String fieldName;
    public String gaugeTitle;
    public String axisLabel;
    public double min;
    public double max;
    public double tick;
    public String yaxisLabel;
    public double ymin;
    public double ymax;
    public double ytick;
    public String origUnit;
    public String unitType;
    public String desUnit;
    public String yorigUnit;
    public String yunitType;
    public String ydesUnit;
    public double gThresh;
    public double yThresh;
    public double rThresh;
    public int warning;
    public String color;
    public double xPos;
    public double yPos;
    public double width;
    public double height;
    public GaugeOrient orient;
}
