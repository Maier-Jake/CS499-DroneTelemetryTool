package dronetelemetrytool.gauges;

import eu.hansolo.tilesfx.Tile;
import eu.hansolo.tilesfx.TileBuilder;
import javafx.scene.text.TextAlignment;

import java.util.Random;

public class CharacterGauge extends Gauge{

    public CharacterGauge()
    {
        super();
        tile.setSkinType(Tile.SkinType.CHARACTER);
        tile.setTitle("Character Gauge");
        tile.setTitleAlignment(TextAlignment.CENTER);
        tile.setDescription("C");

    }
    @Override
    public void update() {
        Random r = new Random();
        char c = (char)(r.nextInt(26) + 'a');
        tile.setDescription(String.valueOf(c));
    }
}
