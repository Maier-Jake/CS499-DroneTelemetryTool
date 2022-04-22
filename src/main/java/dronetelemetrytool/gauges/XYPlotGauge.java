package dronetelemetrytool.gauges;

import dronetelemetrytool.DTT_Tools;
import dronetelemetrytool.fieldparsing.Field;
import dronetelemetrytool.fieldparsing.NumberField;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;

public class XYPlotGauge extends Gauge {

    private NumberField xField;
    private NumberField yField;

    private XYChart.Series<Number, Number> series;

    public NumberAxis xAxis;
    public NumberAxis yAxis;
    private ScatterChart<Number,Number> scatterChart;

    public XYPlotGauge(double xMin, double xMax, double xTick, double yMin, double yMax, double yTick)
    {
        super();
        this.gaugeType = GaugeType.XYPLOT;

        // LineChart Data
        series = new XYChart.Series();

        tile.setTitle("XYPlot Gauge");

        tile.setAnimated(true);
        tile.setRunning(true);
        tile.setActive(true);

        xAxis = new NumberAxis(xMin, xMax, xTick);
        yAxis = new NumberAxis(yMin, yMax, yTick);
        xAxis.setLabel("X");
        yAxis.setLabel("Y");

        scatterChart = new ScatterChart<Number,Number>(xAxis,yAxis);
        scatterChart.getData().addAll(series);

        tile.setGraphic(scatterChart);

    }

    @Override
    public Field getField() {
        return null;
    }

    @Override
    public void update() {

//        double yRange = yAxis.getUpperBound() - yAxis.getLowerBound();
//        double xRange = xAxis.getUpperBound() - xAxis.getLowerBound();

        Double xValue = xField.getNext();
        Double yValue = yField.getNext();

        if (xValue == null || yValue == null) {
            return;
        }

        double newXVal = xValue.doubleValue();
        //double newXVal = DTT_Tools.map(newVal, 0, xRange, xAxis.getLowerBound(), xAxis.getUpperBound());
        double newYVal = yValue.doubleValue();
        //double newYVal = DTT_Tools.map(newVal, 0, yRange, yAxis.getLowerBound(), yAxis.getUpperBound());

        series.getData().add(new XYChart.Data(newXVal, newYVal));
        if(series.getData().size() > 5)
        {
            series.getData().remove(0);
        }
    }

    public void setXLabel(String label)
    {
        xAxis.setLabel(label);
    }
    public void setYLabel(String label) { yAxis.setLabel(label); }

    public NumberField getxField() {
        return xField;
    }

    public void setxField(NumberField xField) {
        this.xField = xField;
    }

    public NumberField getyField() {
        return yField;
    }

    public void setyField(NumberField yField) {
        this.yField = yField;
    }
}
