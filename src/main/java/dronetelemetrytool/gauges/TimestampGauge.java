package dronetelemetrytool.gauges;

import dronetelemetrytool.fieldparsing.Field;
import dronetelemetrytool.fieldparsing.TimeField;
import eu.hansolo.tilesfx.Tile;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class TimestampGauge extends Gauge{

    private DateFormat formatter;
    private TimeField field;

    public TimestampGauge()
    {
        super();
        this.gaugeType = GaugeType.TIMESTAMP;
        formatter = new SimpleDateFormat("HH:mm:ss.SSS");
        formatter.setTimeZone(TimeZone.getTimeZone("UTC"));

        tile.setTitle("Timestamp Gauge");
        tile.setDescription("03:17");
        tile.setText("time elapsed");
        tile.setSkinType(Tile.SkinType.CENTER_TEXT);
    }
    @Override
    public void update() {
        Date date = field.getNextDate();
        if (date == null) {
            return;
        }
        String dateFormatted = formatter.format(date);
        tile.setDescription(dateFormatted);
    }

    @Override
    public Field getField() { return field; }

    public void setField(TimeField field) {
        this.field = field;
    }
}


