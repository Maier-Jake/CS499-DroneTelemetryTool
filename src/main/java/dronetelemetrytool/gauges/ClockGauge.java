package dronetelemetrytool.gauges;

import eu.hansolo.tilesfx.Tile;

public class ClockGauge extends Gauge{

    public ClockGauge()
    {
        super();
        tile.setTitle("Clock Gauge");
        tile.setSkinType(Tile.SkinType.CLOCK);
        tile.setTime(System.currentTimeMillis() / 1000);
    }
    @Override
    public void update() {

    }
}


