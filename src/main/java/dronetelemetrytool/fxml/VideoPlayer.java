package dronetelemetrytool.fxml;

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
        try {
            System.out.println("1");
            Media media = new Media("D:/School/CS499/Data/newVid.mp4");
            System.out.println("2");
            MediaPlayer mediaPlayer = new MediaPlayer(media);
            System.out.println("3");
            mediaView = new MediaView(mediaPlayer);
        } catch (MediaException e) {
            System.out.println("Error1");
        } catch (UnsupportedOperationException f) {
            System.out.println("Error2");
        }
    }

}
