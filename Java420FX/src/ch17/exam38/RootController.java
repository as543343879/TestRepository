package ch17.exam38;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

public class RootController implements Initializable {

	@FXML
	private ProgressBar progressBar;
	@FXML
	private Label label;
	@FXML
	private Label lblWorkDone;
	@FXML
	private Button btnStart;
	@FXML
	private Button btnStop;

	private Task<Integer> task;
		@FXML
		private Label lblResult;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		btnStart.setOnAction(e -> handleBtnStart(e));
		btnStop.setOnAction(e -> handleBtnStop(e));
	}

	private void handleBtnStart(ActionEvent e) {
		task = new Task<Integer>() {
			@Override
			protected Integer call() throws Exception {
					int result=0;
				for (int i = 0; i < 100; i++) {
						result+=i;
					//how1
                    //updateProgress(i, 100);
					//updateMessage(String.valueOf(i));
					
					//how2
					double value=i;
					Platform.runLater(()->{
					progressBar.setProgress(value/100);
					lblWorkDone.setText(String.valueOf(value));
					});
					//
					
					if (isCancelled()) {
						break;
					}
					try {
						Thread.sleep(100);
					} catch (Exception e) {
						break;
					}
				}
				return result;
			}

			@Override
			protected void succeeded() { //call끝나면자동적으로 실행됨 . 여기서 UI변경작업이 된다. javafxapplication이 실행하기 때문에
					int result=getValue();
				//System.out.println("succedded: " +Thread.currentThread().getName());
				lblResult.setText(String.valueOf(result));
			}

			@Override
			protected void cancelled() {
					lblResult.setText("작업취소됨");
			}

			@Override
			protected void failed() {
			}

		};
//1번째 방법. UPDATE이용해서 TASK에서 값을 바꾸게 하고 BINDING하여 JAVAFX가 변경할수 있도록 한다. VALUE와 STRING변경하는 타입두가지뿐이다.
//이외의 타입은 PLATFORM.RUNLATE()사용해서 코딩한다. 
		//progressBar.progressProperty().bind(task.progressProperty());
	//	lblWorkDone.textProperty().bind(task.messageProperty());

		Thread thread = new Thread(task);
		thread.setDaemon(true);
		thread.start();
	}

	private void handleBtnStop(ActionEvent e) {
		task.cancel();
	}

}
