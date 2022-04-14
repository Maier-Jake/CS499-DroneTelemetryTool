package dronetelemetrytool.gauges;

import dronetelemetrytool.MainApplication;
import dronetelemetrytool.fieldparsing.Field;
import dronetelemetrytool.fieldparsing.StringField;
import eu.hansolo.tilesfx.Tile;
import javafx.scene.text.TextAlignment;

import java.util.Random;

public class CharacterGauge extends Gauge{



    public CharacterGauge()
    {
        super();
//        field = MainApplication.fields.getStringFields().get(0);

        tile.setSkinType(Tile.SkinType.CHARACTER);
        tile.setTitle("Character Gauge");
        tile.setTitleAlignment(TextAlignment.CENTER);
        tile.setDescription("C");

    }
    @Override
    public void update() {
//        Random r = new Random();
//        char c = (char)(r.nextInt(26) + 'a');

//        tile.setDescription(temp);
    }
}
