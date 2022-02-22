package dronetelemetrytool.gauges;

import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;

public class XYPlotGauge extends Gauge {

    private XYChart.Series<Number, Number> series;

    private NumberAxis xAxis;
    private NumberAxis yAxis;
    private ScatterChart<Number,Number> scatterChart;

    public XYPlotGauge()
    {
        super();

        // LineChart Data
        series = new XYChart.Series();

        tile.setTitle("XYPlot Gauge");

        tile.setAnimated(true);
        tile.setRunning(true);
        tile.setActive(true);

        xAxis = new NumberAxis(0, 10, 1);
        yAxis = new NumberAxis(-100, 500, 100);

        scatterChart = new ScatterChart<Number,Number>(xAxis,yAxis);
        xAxis.setLabel("X Axis Label");
        yAxis.setLabel("Y Axis Label");
        //scatterChart.setTitle("Scatter Chart Title");

        series.setName("Data Points");
        series.getData().add(new XYChart.Data(4.2, 193.2));
        series.getData().add(new XYChart.Data(2.8, 33.6));

        scatterChart.getData().addAll(series);

        tile.setGraphic(scatterChart);

    }
    @Override
    public void update() {
        series.getData().add(new XYChart.Data(RND.nextDouble()*10, RND.nextDouble()*500));
        /*
        XYChart.Series s = new XYChart.Series();
        s.getData().add(new XYChart.Data(RND.nextDouble()*10, RND.nextDouble()*500));
        scatterChart.getData().add(s);
        */

    }
}
