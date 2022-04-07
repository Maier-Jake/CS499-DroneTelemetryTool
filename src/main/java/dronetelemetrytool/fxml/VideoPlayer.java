package dronetelemetrytool.fxml;

import dronetelemetrytool.DTT_Tools;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaException;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
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
    private Button eigthSpeedButton;

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Media media = DTT_Tools.chooseVideo();
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(false);
        mediaView.setMediaPlayer(mediaPlayer);
    }

    public void reverseClick() {
        mediaView.getMediaPlayer().seek(Duration.ZERO);
    }

    public void pauseClick() {
        mediaView.getMediaPlayer().pause();
    }

    public void forwardClick() {
        mediaView.getMediaPlayer().setRate(1.0);
        mediaView.getMediaPlayer().play();
    }

    public void fiveSpeedClick() {
        mediaView.getMediaPlayer().setRate(5.0);
        mediaView.getMediaPlayer().play();
    }

    public void eightSpeedClick() {
        mediaView.getMediaPlayer().setRate(8.0);
        mediaView.getMediaPlayer().play();
    }

}
