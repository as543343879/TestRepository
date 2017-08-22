
package ch17.exam42;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class LoginController implements Initializable {

		@FXML
		private BorderPane login;
		@FXML
		private Button btnMain;

		@Override
		public void initialize(URL url, ResourceBundle rb) {
				btnMain.setOnAction(e->handleBtnMain(e));
		}		

		private void handleBtnMain(ActionEvent e) {
				
						StackPane rootPane=(StackPane)btnMain.getScene().getRoot();
						
						login.setOpacity(1);
						
							
						login.setTranslateX(0);
						KeyValue keyValue= new KeyValue(login.opacityProperty(),0);
						//KeyValue keyValue = new KeyValue(login.translateXProperty(),200);//무엇을 x를 0으로
						KeyFrame keyFrame = new KeyFrame(Duration.millis(1000),
										(event)->{rootPane.getChildren().remove(login);},
										keyValue); //애니메이션 진행시간 0.1초
				
						Timeline timeline = new Timeline();
						timeline.getKeyFrames().add(keyFrame);
						timeline.play();
						
		}
		
}
