package dronetelemetrytool.fxml;

import dronetelemetrytool.MainApplication;
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

import java.io.File;
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
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        MediaPlayer mediaPlayer = new MediaPlayer(MainApplication.video);
        mediaView.setMediaPlayer(mediaPlayer);

    }

}
