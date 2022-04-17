package dronetelemetrytool.gauges;

import dronetelemetrytool.fieldparsing.Field;
import dronetelemetrytool.fieldparsing.StringField;
import eu.hansolo.tilesfx.Tile;

import java.util.Random;

public class TextGauge extends Gauge{

    private StringField field;

    public TextGauge()
    {
        super();
        tile.setTitle("Text Gauge");
        tile.setDescription("String");
        tile.setSkinType(Tile.SkinType.CENTER_TEXT);
    }
    @Override
    public void update() {
//        Random r = new Random();
//        char c = (char)(r.nextInt(26) + 'a');
//        tile.setDescription(String.valueOf(c));
        tile.setDescription("Text");
    }

    public StringField getField() {
        return field;
    }

    public void setField(StringField field) {
        this.field = field;
    }
}


