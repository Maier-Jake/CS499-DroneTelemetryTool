package dronetelemetrytool.gauges;

import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;

enum GaugeOrient {
    HORIZONTAL,
    VERTICAL
}


public class XPlotGauge extends Gauge {

    private XYChart.Series<Number, Number> series;
    private NumberAxis xAxis;
    private NumberAxis yAxis;
    private ScatterChart<Number,Number> scatterChart;
    private GaugeOrient orient;

    public XPlotGauge()
    {
        super();

        series = new XYChart.Series();

        tile.setTitle("XPlot Gauge");

        tile.setAnimated(true);
        tile.setRunning(true);
        tile.setActive(true);

        xAxis = new NumberAxis(0, 10, 1);
        yAxis = new NumberAxis(0, 2, 0);

        scatterChart = new ScatterChart<Number,Number>(xAxis,yAxis);
        xAxis.setLabel("X Axis Label");

        scatterChart.setVerticalZeroLineVisible(false);
        scatterChart.setVerticalGridLinesVisible(false);
        yAxis.setTickLabelsVisible(false);
        //yAxis.setLabel("Y Axis Label");
        //scatterChart.setTitle("Scatter Chart Title");

        series.setName("Data Points");
        series.getData().add(new XYChart.Data(4.2, 1));
        series.getData().add(new XYChart.Data(2.8, 1));

        scatterChart.getData().addAll(series);

        tile.setGraphic(scatterChart);

    }
    @Override
    public void update() {
        series.getData().add(new XYChart.Data(RND.nextDouble()*10, 1));
        /*
        XYChart.Series s = new XYChart.Series();
        s.getData().add(new XYChart.Data(RND.nextDouble()*10, RND.nextDouble()*500));
        scatterChart.getData().add(s);
        */

    }
}
