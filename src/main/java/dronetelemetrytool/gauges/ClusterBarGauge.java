package dronetelemetrytool.gauges;


import dronetelemetrytool.fieldparsing.Field;
import dronetelemetrytool.fieldparsing.NumberField;
import eu.hansolo.tilesfx.Tile;
import eu.hansolo.tilesfx.chart.ChartData;
import eu.hansolo.tilesfx.colors.Bright;
import eu.hansolo.toolboxfx.GradientLookup;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.paint.Stop;

import java.io.File;
import java.util.Arrays;

public class ClusterBarGauge extends Gauge{

    private NumberField field;

    private ChartData data;
//    private GradientLookup gradient;
    private Media alarm;
    private MediaPlayer mediaPlayer;
    private double redThresh;

    public ClusterBarGauge()
    {
        super();
        field = null;
        tile.setPrefSize(TILE_SIZE*2, TILE_SIZE);

        tile.setTitle("");
        tile.setMinValue(0);
        tile.setMaxValue(100);
        tile.setFillWithGradient(true);


//        gradient = new GradientLookup(Arrays.asList(
//                new Stop(0.0, Bright.BLUE),
//                new Stop(0.3, Bright.GREEN),
//                new Stop(0.6, Bright.YELLOW),
//                new Stop(0.9, Bright.RED)));

        redThresh = 0.9 * tile.getMaxValue();

        alarm = null;
        mediaPlayer = null;
    }


    @Override
    public void update() {
        //data.setValue(RND.nextDouble() * data.getMaxValue());
        data.setValue(field.getNext());
//        data.setFillColor(gradient.getColorAt(data.getValue() / data.getMaxValue()));

        if (mediaPlayer != null)
        {
            if (mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING && data.getValue() < redThresh)
            {
                mediaPlayer.stop();
                tile.setBackgroundColor(Tile.BACKGROUND);
            }
            else
            {
                if (data.getValue() >= redThresh)
                {
                    mediaPlayer.play();
                    tile.setBackgroundColor(Color.DARKRED);
                }
            }
        }
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

    @Override
    public Field getField() { return field; }

    public void setField(NumberField field) {
        this.field = field;
//        tile.setMinValue(field.getMinValue());
//        tile.setMaxValue(field.getMaxValue());
    }

    public void setData(ChartData data) {
        this.data = data;
        redThresh = data.getGradientLookup().getStops().get(3).getOffset() * tile.getRange();
        tile.addChartData(data);
        tile.setSkinType(Tile.SkinType.CLUSTER_MONITOR);
    }
}
