package dronetelemetrytool.gauges;

import dronetelemetrytool.DTT_Tools;
import dronetelemetrytool.fieldparsing.NumberField;
import eu.hansolo.tilesfx.Tile;
import eu.hansolo.tilesfx.colors.Bright;
import eu.hansolo.toolboxfx.GradientLookup;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.paint.Stop;

import java.io.File;
import java.util.Arrays;

public class CircleGauge extends Gauge {

    private NumberField field;
    private GradientLookup gradient;
    private Media alarm;
    private MediaPlayer mediaPlayer;
    private double redThresh;

    public CircleGauge(int angleRange)
    {
        super();
        field = null;
        tile.setSkinType(Tile.SkinType.GAUGE2);
        tile.setUnit("d");
        tile.setAngleRange(angleRange);

        //Min and Max Values determine the range of possible values for the gauge
        tile.setMinValue(0);
        tile.setMaxValue(angleRange);
        tile.setValue(angleRange);

        tile.setMinValueVisible(false); tile.setMaxValueVisible(false);

        tile.setUnit("mph");

        tile.setAnimated(true);

        gradient = new GradientLookup(Arrays.asList(
                new Stop(0.25, Bright.BLUE),
                new Stop(0.50, Bright.GREEN),
                new Stop(0.75, Bright.YELLOW),
                new Stop(1, Bright.RED)));

        tile.setGradientStops(gradient.getStops());
        tile.setStrokeWithGradient(true);

        alarm = null;
        mediaPlayer = null;
    }

    @Override
    public void update() {
//        double newVal = RND.nextDouble() * tile.getRange();

        double newVal = field.getNext();


        double newValInRange = DTT_Tools.map(newVal, 0, tile.getRange(), tile.getMinValue(), tile.getMaxValue());

        tile.setValue(newValInRange);
        //tile.setValue(tile.getMaxValue());
        double mappedVal = DTT_Tools.map(tile.getValue(), tile.getMinValue(), tile.getMaxValue(), 0, tile.getRange());
        tile.setBarColor(gradient.getColorAt(newVal / tile.getRange()));

        if (mediaPlayer != null)
        {
            if (mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING && tile.getValue() < redThresh)
            {
                mediaPlayer.stop();
                tile.setBackgroundColor(Tile.BACKGROUND);
            }
            else
            {
                if (tile.getValue() >= redThresh)
                {
                    mediaPlayer.play();
                    tile.setBackgroundColor(Color.DARKRED);
                }
            }
        }
    }

    public void setGradient(GradientLookup g)
    {
        gradient = g;
        tile.setGradientStops(gradient.getStops());
        redThresh = g.getStops().get(3).getOffset() * tile.getRange();
    }

    public void setAlarm(int i) {
        String musicFile;
        switch(i)
        {
            case 1: //chirp
                musicFile = "src/main/resources/dronetelemetrytool/sounds/chirp.wav";
                alarm = new Media(new File(musicFile).toURI().toString());
                mediaPlayer = new MediaPlayer(alarm);
                mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
                break;
            case 2: //siren
                musicFile = "src/main/resources/dronetelemetrytool/sounds/siren.wav";
                alarm = new Media(new File(musicFile).toURI().toString());
                mediaPlayer = new MediaPlayer(alarm);
                mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
                break;
            case 3: //scream
                musicFile = "src/main/resources/dronetelemetrytool/sounds/screams.wav";
                alarm = new Media(new File(musicFile).toURI().toString());
                mediaPlayer = new MediaPlayer(alarm);
                mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
                break;
            default:
                //no alarm
                break;
        }
    }

    public NumberField getField()
    {
        return field;
    }

    public void setField(NumberField field) {
        this.field = field;
    }
}
