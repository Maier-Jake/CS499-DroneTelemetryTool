package dronetelemetrytool.gauges;

import eu.hansolo.tilesfx.Tile;
import javafx.scene.chart.XYChart;

public class XYPlotGauge extends Gauge {

    private XYChart.Series<Number, Number> series;

    public XYPlotGauge()
    {
        super();

        // LineChart Data
        series = new XYChart.Series();
        series.setName("Inside");
        series.getData().add(new XYChart.Data(10, 8));
        series.getData().add(new XYChart.Data(15, 5));
        series.getData().add(new XYChart.Data(30, 0));
        series.getData().add(new XYChart.Data(50, 2));
        series.getData().add(new XYChart.Data(10, 4));
        series.getData().add(new XYChart.Data(5, 5));
        series.getData().add(new XYChart.Data(0, 5));

        tile.setSkinType(Tile.SkinType.SMOOTHED_CHART);
        tile.setTitle("XYPlot Gauge");
        tile.setSmoothing(true);
        //tile.addSeries(series);
        tile.setAnimated(true);
    }
    @Override
    public void update() {

    }
}
