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
    private GradientLookup gradient;

    public ClusterBarGauge(String title)
    {
        super();
        tile.setPrefSize(TILE_SIZE*2, TILE_SIZE);
        tile.setSkinType(Tile.SkinType.CLUSTER_MONITOR);
        tile.setTitle(title);

        gradient = new GradientLookup(Arrays.asList(
                new Stop(0.0, Bright.BLUE),
                new Stop(0.3, Bright.GREEN),
                new Stop(0.6, Bright.YELLOW),
                new Stop(0.9, Bright.RED)));


        data = new ChartData("", Tile.YELLOW);
        data.setFormatString("%.1f");
        tile.addChartData(data);

    }
    @Override
    public void update() {
        data.setValue(RND.nextDouble() * data.getMaxValue());
        data.setFillColor(gradient.getColorAt(data.getValue() / data.getMaxValue()));
    }

    public void setGradient(GradientLookup g)
    {
        gradient = g;
    }
}
