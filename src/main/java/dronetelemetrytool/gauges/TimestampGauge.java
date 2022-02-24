package dronetelemetrytool.gauges;

import eu.hansolo.tilesfx.Tile;

import java.util.Random;

public class TimestampGauge extends Gauge{

    public TimestampGauge()
    {
        super();
        tile.setTitle("Timestamp Gauge");
        tile.setDescription("03:17");
        tile.setText("time elapsed");
        tile.setSkinType(Tile.SkinType.CENTER_TEXT);
    }
    @Override
    public void update() {

    }
}


