package dronetelemetrytool.gauges;

import dronetelemetrytool.skins.SingleBarTileSkin;
import eu.hansolo.tilesfx.Tile;
import eu.hansolo.tilesfx.chart.ChartData;
import eu.hansolo.tilesfx.colors.Bright;
import eu.hansolo.toolboxfx.GradientLookup;
import javafx.scene.paint.Stop;

import java.util.Arrays;

public class BarGauge extends Gauge{

    private ChartData data;
    private GradientLookup gradient;

    public BarGauge()
    {
        super();

        gradient = new GradientLookup(Arrays.asList(
                new Stop(0.0, Bright.BLUE),
                new Stop(0.3, Bright.GREEN),
                new Stop(0.6, Bright.YELLOW),
                new Stop(0.9, Bright.RED)));

        data = new ChartData("", Tile.YELLOW);
        tile.setChartData(data);


        //tile.setSkinType(Tile.SkinType.CUSTOM);
        tile.setSkin(new SingleBarTileSkin(tile));
        data.setFillColor(gradient.getColorAt(data.getValue() / 100));

    }
    @Override
    public void update() {
        data.setFillColor(gradient.getColorAt(data.getValue() / 100));
    }
}
