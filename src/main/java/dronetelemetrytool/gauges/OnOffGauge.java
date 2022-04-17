package dronetelemetrytool.gauges;

import dronetelemetrytool.fieldparsing.BoolField;
import eu.hansolo.tilesfx.Tile;
import eu.hansolo.tilesfx.TileBuilder;

import java.util.Random;

public class OnOffGauge extends Gauge{

    private BoolField field;

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

    public void setField(BoolField field) {
        this.field = field;
    }

    public BoolField getField() {
        return field;
    }
}
