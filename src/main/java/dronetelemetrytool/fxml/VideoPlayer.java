package dronetelemetrytool.fxml;

import dronetelemetrytool.MainApplication;
import dronetelemetrytool.gauges.Gauge;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class VideoPlayer implements Initializable {
//    @FXML
//    private VBox vBox;
//    @FXML
//    private HBox buttonBox;
    @FXML
    private MediaView mediaView;
//    @FXML
//    private HBox mediaBox;
    @FXML
    private Button reverseButton;
    @FXML
    private Button pauseButton;
    @FXML
    private Button forwardButton;
    @FXML
    private Button fiveSpeedButton;
    @FXML
    private Button eightSpeedButton;

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        MediaPlayer mediaPlayer = new MediaPlayer(MainApplication.video);
        mediaPlayer.setAutoPlay(false);
        mediaView.setMediaPlayer(mediaPlayer);
    }

    public void reverseClick() {
        for (Gauge g : MainApplication.gauges) {
            //MainApplication.timer.start();
            g.getField().setIndex(0);
        }
        mediaView.getMediaPlayer().seek(Duration.ZERO);
        //for each gauge, set field index back to start
    }

    public void pauseClick() {
        mediaView.getMediaPlayer().pause();
        for (Gauge g: MainApplication.gauges)
        {
            if (g.mediaPlayer != null)
            {
                if (g.mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
                    g.mediaPlayer.stop();
                }
            }
        }

        MainApplication.timer.stop();
    }

    public void forwardClick() {
        MainApplication.timer.stop();
        mediaView.getMediaPlayer().pause();
        mediaView.getMediaPlayer().setRate(1.0);
        MainApplication.setRate(1);
        MainApplication.timer.start();
        mediaView.getMediaPlayer().play();
    }

    public void fiveSpeedClick() {
        MainApplication.timer.stop();
        mediaView.getMediaPlayer().pause();
        mediaView.getMediaPlayer().setRate(5.0);
        MainApplication.setRate(5);
        MainApplication.timer.start();
        mediaView.getMediaPlayer().play();
    }

    public void eightSpeedClick() {
        MainApplication.timer.stop();
        mediaView.getMediaPlayer().pause();
        mediaView.getMediaPlayer().setRate(8.0);
        MainApplication.setRate(8);
        mediaView.getMediaPlayer().play();
        MainApplication.timer.start();

    }

}
