package dronetelemetrytool.gauges;

import dronetelemetrytool.DTT_Tools;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;


public class XPlotGauge extends Gauge {

    private XYChart.Series<Number, Number> series;
    private NumberAxis primaryAxis;
    private NumberAxis subAxis;
    private ScatterChart<Number,Number> scatterChart;
    private GaugeOrient orient;

    public XPlotGauge(GaugeOrient orient, double lowerBound, double upperBound, double tickUnit)
    {
        super();

        series = new XYChart.Series();

        tile.setTitle("Line Plot Gauge");

        tile.setAnimated(true);
        tile.setRunning(true);
        tile.setActive(true);

        tile.setMaxValue(upperBound);
        tile.setMinValue(lowerBound);

        primaryAxis = new NumberAxis(lowerBound, upperBound, tickUnit);
        subAxis = new NumberAxis(0, 2, 0);

        switch (orient)
        {
            case VERTICAL:
                scatterChart = new ScatterChart<Number, Number>(subAxis, primaryAxis);
                this.orient = GaugeOrient.VERTICAL;
                break;
            default:
                scatterChart = new ScatterChart<Number, Number>(primaryAxis,subAxis);
                this.orient = GaugeOrient.HORIZONTAL;
                break;
        }

        scatterChart.setVerticalZeroLineVisible(false);
        scatterChart.setVerticalGridLinesVisible(false);
        scatterChart.setHorizontalZeroLineVisible(false);
        scatterChart.setHorizontalGridLinesVisible(false);


        primaryAxis.setLabel("X Axis Label");
        subAxis.setTickLabelsVisible(false);
        //yAxis.setLabel("Y Axis Label");
        //scatterChart.setTitle("Scatter Chart Title");

        scatterChart.getData().addAll(series);

        tile.setGraphic(scatterChart);

    }
    @Override
    public void update() {
        double newVal = RND.nextDouble() * tile.getRange();
        double newValInRange = DTT_Tools.map(newVal, 0, tile.getRange(), tile.getMinValue(), tile.getMaxValue());
        switch (orient)
        {
            case VERTICAL:
                series.getData().add(new XYChart.Data(1, newValInRange));
                break;
            default:
                series.getData().add(new XYChart.Data(newValInRange, 1));
                break;
        }
        if(series.getData().size() > 5)
        {
            series.getData().remove(0);
        }
    }

    public void setLabel(String label)
    {
        primaryAxis.setLabel(label);
    }
}
