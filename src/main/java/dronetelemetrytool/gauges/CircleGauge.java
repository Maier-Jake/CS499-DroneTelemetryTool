package dronetelemetrytool.gauges;

import dronetelemetrytool.DTT_Tools;
import dronetelemetrytool.MainApplication;
import dronetelemetrytool.fieldparsing.Field;
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
    private transient GradientLookup gradient;
    private transient int alarmIndex;
    private transient Media alarm;
    private double redThresh;

    public CircleGauge(int angleRange)
    {
        super();
        if (angleRange == 90) {
            this.gaugeType=GaugeType.CIRCLE90;
        } else if (angleRange == 180) {
            this.gaugeType=GaugeType.CIRCLE180;
        } else if (angleRange == 270) {
            this.gaugeType=GaugeType.CIRCLE270;
        } else if (angleRange == 360) {
            this.gaugeType=GaugeType.CIRCLE360;
        }
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

        Double newVal_o = field.getNext();

        if (newVal_o == null) {
            return;
        }

        double newVal = newVal_o.doubleValue();
        double newValInRange = DTT_Tools.map(newVal, 0, tile.getRange(), tile.getMinValue(), tile.getMaxValue());
        tile.setValue(newValInRange);

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

    public GradientLookup getGradient()
    {
        return gradient;
    }

    public void setGradient(GradientLookup g)
    {
        gradient = g;
        tile.setGradientStops(gradient.getStops());
        redThresh = g.getStops().get(3).getOffset() * tile.getRange();
    }

    public void setAlarm(int i) {
//        String musicFile;
        redThresh = DTT_Tools.map(gradient.getStops().get(3).getOffset(), 0, 1, tile.getMinValue(),tile.getMaxValue());
        alarmIndex = i;
        switch(i)
        {
            case 1: //chirp
//                musicFile = "src/main/resources/dronetelemetrytool/sounds/chirp.wav";
                alarm = new Media(MainApplication.class.getResource("sounds/chirp.wav").toString());
                mediaPlayer = new MediaPlayer(alarm);
                mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
                break;
            case 2: //siren
//                musicFile = "src/main/resources/dronetelemetrytool/sounds/siren.wav";
                alarm = new Media(MainApplication.class.getResource("sounds/sirens.wav").toString());
                mediaPlayer = new MediaPlayer(alarm);
                mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
                break;
            case 3: //scream
//                musicFile = "src/main/resources/dronetelemetrytool/sounds/screams.wav";
                alarm = new Media(MainApplication.class.getResource("sounds/screams.wav").toString());
                mediaPlayer = new MediaPlayer(alarm);
                mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
                break;
            default:
                //no alarm
                break;
        }
    }

    @Override
    public Field getField() { return field; }

    public void setField(NumberField field) {
        this.field = field;
    }

    public int getAlarmIndex() {
        return alarmIndex;
    }
}
