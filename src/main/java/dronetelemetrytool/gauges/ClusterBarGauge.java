package dronetelemetrytool.gauges;


import dronetelemetrytool.DTT_Tools;
import dronetelemetrytool.fieldparsing.NumberField;
import dronetelemetrytool.skins.SingleBarTileSkin;
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

    public ClusterBarGauge(String title, double min, double max, double green, double yellow, double red, String unit)
    {
        super();
        field = null;
        tile.setPrefSize(TILE_SIZE*2, TILE_SIZE);
        tile.setTitle(title);
        tile.setMaxValue(max);
        tile.setMinValue(min);
        tile.setFillWithGradient(true);

        data = new ChartData("");
        data.setFormatString("%.1f " + unit);
        data.setMinValue(min);
        data.setMaxValue(max);
        data.setGradientLookup(new GradientLookup((Arrays.asList(
                new Stop(0, Bright.BLUE),
                new Stop(DTT_Tools.map(green,min,max,0,1), Bright.GREEN),
                new Stop(DTT_Tools.map(yellow,min,max,0,1), Bright.YELLOW),
                new Stop(DTT_Tools.map(red,min,max,0,1), Bright.RED),
                new Stop(1, Bright.RED)))));
        tile.addChartData(data);

        tile.setSkin(new SingleBarTileSkin(tile));

//        tile.setSkinType(Tile.SkinType.CLUSTER_MONITOR);
        redThresh = 0.9 * tile.getMaxValue();
        alarm = null;
        mediaPlayer = null;
    }


    @Override
    public void update() {
        //data.setValue(RND.nextDouble() * data.getMaxValue());
        data.setValue(field.getNext());

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

//    public void setGradient(GradientLookup g)
//    {
//        gradient = g;
//        redThresh = g.getStops().get(3).getOffset() * tile.getRange();
//    }

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

    public NumberField getField() {
        return field;
    }

    public void setField(NumberField field) {
        this.field = field;
//        tile.setMinValue(field.getMinValue());
//        tile.setMaxValue(field.getMaxValue());
    }
}
