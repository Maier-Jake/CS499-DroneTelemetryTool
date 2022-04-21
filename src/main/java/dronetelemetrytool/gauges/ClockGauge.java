package dronetelemetrytool.gauges;

import dronetelemetrytool.fieldparsing.Field;
import dronetelemetrytool.fieldparsing.TimeField;
import eu.hansolo.tilesfx.Tile;

public class ClockGauge extends Gauge{

    private TimeField field;

    public ClockGauge()
    {
        super();
        tile.setTitle("Clock Gauge");
        tile.setSkinType(Tile.SkinType.CLOCK);
        tile.setTime(System.currentTimeMillis() / 1000);
    }
    @Override
    public void update() {
        tile.setTime(field.getNext());
    }

    public void setField(TimeField field) {
        this.field = field;
    }

    @Override
    public Field getField() { return field; }
}


