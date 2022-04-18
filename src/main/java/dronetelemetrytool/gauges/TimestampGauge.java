package dronetelemetrytool.gauges;

import dronetelemetrytool.fieldparsing.TimeField;
import eu.hansolo.tilesfx.Tile;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

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
        Date date = new Date(field.getNext());
        DateFormat formatter = new SimpleDateFormat("HH:mm:ss.SSS");
        formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
        String dateFormatted = formatter.format(date);
        tile.setDescription(dateFormatted);
    }

    public TimeField getField() {
        return field;
    }

    public void setField(TimeField field) {
        this.field = field;
    }
}


