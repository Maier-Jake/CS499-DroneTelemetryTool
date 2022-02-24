package dronetelemetrytool.gauges;


import eu.hansolo.tilesfx.Tile;
import eu.hansolo.tilesfx.chart.ChartData;
import eu.hansolo.tilesfx.colors.Bright;
import eu.hansolo.toolboxfx.GradientLookup;
import javafx.scene.paint.Stop;
import javafx.scene.text.TextAlignment;

import java.util.Arrays;

public class ClusterBarGauge extends Gauge{

    private ChartData data;
    private String dataName;
    private GradientLookup gradient;

    public ClusterBarGauge()
    {
        super();

        tile.setPrefSize(TILE_SIZE*2, TILE_SIZE);

        gradient = new GradientLookup(Arrays.asList(
                new Stop(0.0, Bright.BLUE),
                new Stop(0.3, Bright.GREEN),
                new Stop(0.6, Bright.YELLOW),
                new Stop(0.9, Bright.RED)));

        dataName = "";
        data = new ChartData(dataName, Tile.YELLOW);
        data.setFormatString("%.1f kWh");
        tile.addChartData(data);

        tile.setSkinType(Tile.SkinType.CLUSTER_MONITOR);

        tile.setTitle("Bar Gauge");
        data.setFillColor(gradient.getColorAt(data.getValue() / 100));

    }
    @Override
    public void update() {
        data.setValue(RND.nextDouble() * 100);
        data.setFillColor(gradient.getColorAt(data.getValue() / 100));
    }
}
