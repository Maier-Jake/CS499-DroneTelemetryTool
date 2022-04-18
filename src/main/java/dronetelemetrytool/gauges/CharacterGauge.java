package dronetelemetrytool.gauges;

import dronetelemetrytool.MainApplication;
import dronetelemetrytool.fieldparsing.Field;
import dronetelemetrytool.fieldparsing.StringField;
import eu.hansolo.tilesfx.Tile;
import javafx.scene.text.TextAlignment;

import java.util.Random;

public class CharacterGauge extends Gauge{

    private StringField field;

    public CharacterGauge()
    {
        super();
        field = null;
        tile.setSkinType(Tile.SkinType.CHARACTER);
        tile.setTitle("Character Gauge");
        tile.setTitleAlignment(TextAlignment.CENTER);
        tile.setDescription("C");

    }
    @Override
    public void update() {
//        Random r = new Random();
//        char c = (char)(r.nextInt(26) + 'a');
          tile.setDescription(field.getNext());
//        tile.setDescription(temp);
    }

    public StringField getField() {
        return field;
    }

    public void setField(StringField field) {
        this.field = field;
    }
}
