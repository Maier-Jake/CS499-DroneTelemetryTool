package dronetelemetrytool.gauges;

import dronetelemetrytool.fieldparsing.TimeField;
import eu.hansolo.tilesfx.Tile;

public class TimestampGauge extends Gauge{

    private TimeField field;

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

    public TimeField getField() {
        return field;
    }

    public void setField(TimeField field) {
        this.field = field;
    }
}


