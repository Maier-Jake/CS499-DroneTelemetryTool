package dronetelemetrytool.gauges;

import eu.hansolo.tilesfx.Tile;

import java.util.Random;

public class TextGauge extends Gauge{

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
}


