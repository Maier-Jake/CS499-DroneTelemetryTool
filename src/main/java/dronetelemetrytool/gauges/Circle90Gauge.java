package dronetelemetrytool.gauges;

import eu.hansolo.tilesfx.Tile;
import javafx.scene.paint.Color;
import javafx.scene.paint.Stop;

public class Circle90Gauge extends Gauge {

    public Circle90Gauge()
    {
        super();
        tile.setSkinType(Tile.SkinType.GAUGE2);

        //AngleRange is used to change how much of the circle is usable. 0-360.
        tile.setAngleRange(90);

        //Min and Max Values determine the range of possible values for the gauge
        tile.setMinValue(0);
        tile.setMaxValue(90);
        tile.setValue(90);

        tile.setMinValueVisible(true);
        tile.setMaxValueVisible(true);

        tile.setUnit("mph");

        tile.setAnimated(true);
        //tile.setHighlightSections(true);
        tile.setGradientStops(
            new Stop(0.0, Tile.BLUE),
            new Stop(0.26, Tile.LIGHT_GREEN),
            new Stop(0.51, Tile.YELLOW),
            new Stop(0.76, Tile.LIGHT_RED));

        tile.setStrokeWithGradient(true);
    }

    @Override
    public void update() {

    }

}
