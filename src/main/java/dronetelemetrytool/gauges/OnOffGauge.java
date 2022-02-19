package dronetelemetrytool.gauges;

import eu.hansolo.tilesfx.Tile;
import eu.hansolo.tilesfx.TileBuilder;

import java.util.Random;

public class OnOffGauge extends Gauge{

    public OnOffGauge()
    {
        tile = TileBuilder.create().skinType(Tile.SkinType.LED)
                .prefSize(TILE_SIZE, TILE_SIZE)
                .title("OnOff Gauge")
                .build();
    }
    @Override
    public void update() {
        Random r = new Random();
        tile.setActive(r.nextBoolean());
    }
}
