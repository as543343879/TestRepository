package ch18.exam29.client;

import ch18.exam30.server.ServerController;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ClientController implements Initializable {

		@FXML
		private Button btnConn;
		@FXML
		private TextField tntInput;
		@FXML
		private Button btnSend;
		@FXML
		private TextArea txtDisplay;

		private Socket socket;

		@Override
		public void initialize(URL url, ResourceBundle rb) {
				btnConn.setOnAction(e -> handleBtnConn(e));
				btnSend.setOnAction(e -> handleBtnSend(e));
		}

		private void handleBtnConn(ActionEvent e) {

				if (btnConn.getText().equals("연결")) {
						startClient();

				} else if (btnConn.getText().equals("끊기")) {
						stopClient();
				}
		}

		private void handleBtnSend(ActionEvent e) {

				send(tntInput.getText());

		}

		private void startClient() {
				Thread thread = new Thread() {
						@Override
						public void run() {

								try {
										//소켓생성
										socket = new Socket();
										//서버와 연결하기
										socket.connect(new InetSocketAddress("localhost", 50003));
										//연결 성공 출력하기
										Platform.runLater(() -> {
												btnConn.setText("끊기");
												display("[연결성공!!!!!!!!!!!]");
										});
										//데이터받기
										receive();
								} catch (Exception ex) {
										Platform.runLater(()->{
												display("[연결실패]");
												stopClient();
										});
										
										return;
								}

						}
				};

				thread.start();
		}

		private void stopClient() {
				try {
						socket.close();
				} catch (IOException ex) {
				
				}
				btnConn.setText("연결");
				display("[연결끊김........Bye.........]");
				
		}

		private void display(String text) {
				txtDisplay.appendText(text + "\n");
		}

		private void receive() {
							try{
						while(true){
								InputStream inputStream = socket.getInputStream();
								byte[] byteArr=new byte[100];
								int readBytes=inputStream.read(byteArr); //데이터 보내기 전까지 여기서 대기가 발생,정상종료-1비정상일때 예외
								if(readBytes==-1) throw new Exception();
								String strData = new String(byteArr,0,readBytes);
								Platform.runLater(()->{display(strData);});///////////////
						}
							
								}catch(Exception e){
												Platform.runLater(()->display("연결끊김"));
												stopClient();
								}
				
				
		}

		private void send(String message) {
					try {
								OutputStream os = socket.getOutputStream();
								byte[] byteArr = message.getBytes();
								os.write(byteArr);
								os.flush();
						} catch (IOException ex) {
								try {
										socket.close();
								} catch (IOException ex1) {
								}
								stopClient();

		}
		}
}
