package ch17.exam25;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.Slider;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

public class RootController implements Initializable {

    @FXML
    private MediaView mediaView;
    @FXML
    private Button btnPlay;
    @FXML
    private Button btnPause;
    @FXML
    private Button btnStop;

    private boolean endOfMedia;
    @FXML
    private Slider sliderVolume;
    @FXML
    private ProgressBar progressBar;
    @FXML
    private ProgressIndicator progressIndicator;
    @FXML
    private Label labelTime;
    @FXML
    private Slider slidermedia;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Media media = new Media(getClass().getResource("media/video.m4v").toString());  //미디어 재생
        // Media media = new Media(getClass().getResource("media/audio.wav").toString());     //음악재생
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaView.setMediaPlayer(mediaPlayer);

        mediaPlayer.setOnReady(() -> {
            btnPlay.setDisable(false);
            btnPause.setDisable(true);
            btnStop.setDisable(true);
            labelTime.setText("0/" + (int) mediaPlayer.getTotalDuration().toSeconds() + "초");
            slidermedia.setMax(mediaPlayer.getTotalDuration().toSeconds());    
        });

        mediaPlayer.setOnPlaying(() -> {
            btnPlay.setDisable(true);
            btnPause.setDisable(false);
            btnStop.setDisable(false);
        });

        mediaPlayer.setOnPaused(() -> {
            btnPlay.setDisable(false);
            btnPause.setDisable(true);
            btnStop.setDisable(false);
        });

        mediaPlayer.setOnStopped(() -> {
            btnPlay.setDisable(false);
            btnPause.setDisable(true);
            btnStop.setDisable(true);
        });
        
        mediaPlayer.setOnEndOfMedia(() -> {
            endOfMedia = true;
            btnPlay.setDisable(false);
            btnPause.setDisable(true);
            btnStop.setDisable(true);
            progressBar.setProgress(1.0);
            progressIndicator.setProgress(1.0);
            // slidermedia.setValue(0);

        });

        btnPlay.setOnAction(event -> {
            if (endOfMedia) {
                mediaPlayer.stop();
                mediaPlayer.seek(mediaPlayer.getStartTime());
            }
            mediaPlayer.play();
            endOfMedia = false;
        });
        btnPause.setOnAction(event -> mediaPlayer.pause());
        btnStop.setOnAction(event -> mediaPlayer.stop());

        //-----------------------------------------slider----------------------------------------
        sliderVolume.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

                mediaPlayer.setVolume(newValue.doubleValue() / 100.0);
            }
        });
        sliderVolume.setValue(50);

        mediaPlayer.currentTimeProperty().addListener(new ChangeListener<Duration>() {
            @Override
            public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) {

                double progress = newValue.toSeconds() / mediaPlayer.getTotalDuration().toSeconds();
                progressBar.setProgress(progress);
                progressIndicator.setProgress(progress);
                labelTime.setText((int) newValue.toSeconds() + "/" + (int) mediaPlayer.getTotalDuration().toSeconds() + "초");
            }
        });
        //--------------------------------------sidermedia----------------

        mediaPlayer.currentTimeProperty().addListener(new ChangeListener<Duration>() {
            @Override
            public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) {

                double progress = newValue.toSeconds();
                //slidermedia.setValue(progress / mediaPlayer.getTotalDuration().toSeconds() * 100);
                slidermedia.setValue(progress);
            }
        });

        slidermedia.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                double oldvalue=oldValue.doubleValue();
                double newvalue=newValue.doubleValue();

                if (slidermedia.isValueChanging()) {
                    mediaPlayer.seek(Duration.seconds(newvalue));

                } else {
                  if (Math.abs(oldvalue-newvalue) > 0.5) {

                        mediaPlayer.seek(Duration.seconds(newValue.doubleValue()));

                  }

                }

            }

        });
    }

}
