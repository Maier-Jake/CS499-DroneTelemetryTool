package dronetelemetrytool.gauges;

import dronetelemetrytool.DTT_Tools;
import dronetelemetrytool.skins.Circle180TileSkin;
import dronetelemetrytool.skins.Circle270TileSkin;
import eu.hansolo.tilesfx.colors.Bright;
import eu.hansolo.toolboxfx.GradientLookup;
import javafx.scene.paint.Stop;

import java.util.Arrays;

public class Circle270Gauge extends Gauge {

    private GradientLookup gradient;

    public Circle270Gauge()
    {
        super();
        //tile.setSkinType(Tile.SkinType.GAUGE2);
        tile.setUnit("d");
        tile.setSkin(new Circle270TileSkin(tile));
        //AngleRange is used to change how much of the circle is usable. 0-360.
        tile.setAngleRange(270);

        //Min and Max Values determine the range of possible values for the gauge
        tile.setMinValue(0);
        tile.setMaxValue(270);
        tile.setValue(270);

        tile.setMinValueVisible(false); tile.setMaxValueVisible(false);

        tile.setUnit("mph");

        tile.setAnimated(true);

        gradient = new GradientLookup(Arrays.asList(
                new Stop(0.25, Bright.BLUE),
                new Stop(0.50, Bright.GREEN),
                new Stop(0.75, Bright.YELLOW),
                new Stop(1, Bright.RED)));

        tile.setGradientStops(gradient.getStops());

        tile.setStrokeWithGradient(true);
    }

    @Override
    public void update() {
        double newVal = RND.nextDouble() * tile.getRange();
        double newValInRange = DTT_Tools.map(newVal, 0, tile.getRange(), tile.getMinValue(), tile.getMaxValue());
        tile.setValue(newValInRange);
        //tile.setValue(tile.getMaxValue());
        double mappedVal = DTT_Tools.map(tile.getValue(), tile.getMinValue(), tile.getMaxValue(), 0, tile.getRange());
        tile.setBarColor(gradient.getColorAt(newVal / tile.getRange()));
    }

    public void setGradient(GradientLookup g)
    {
        gradient = g;
        tile.setGradientStops(gradient.getStops());
    }

}
