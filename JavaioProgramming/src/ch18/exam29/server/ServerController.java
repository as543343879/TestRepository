package ch18.exam29.server;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class ServerController implements Initializable {

		public static ServerController instance;

		@FXML
		private Button btnStartStop;
		@FXML
		private TextArea txtDisplay;

		private ExecutorService executorService;
		private ServerSocket serverSocket;
		private List<Client> connections = new Vector<Client>();

		@Override
		public void initialize(URL url, ResourceBundle rb) {
				ServerController.instance = this;
				btnStartStop.setOnAction(e -> handleBtnStartStop());
		}

		private void handleBtnStartStop() {

				if (btnStartStop.getText().equals("시작")) {
						startServer();
				} else if (btnStartStop.getText().equals("멈춤")) {
						stopServer();
				}
		}

		private void startServer() {
				//스레드풀생성
				executorService = Executors.newFixedThreadPool(100);

				try {
						//서버소켓 생성
						serverSocket = new ServerSocket();
						serverSocket.bind(new InetSocketAddress("localhost", 50003));
				} catch (IOException ex) {
						stopServer();
						return;
				}

				//연결수락작업정의
				Runnable acceptTask = () -> {
						Platform.runLater(() -> {
								btnStartStop.setText("멈춤");
								display("서버시작");
						});

						while (true) {
								try {
										//수락코드
										Socket socket = serverSocket.accept();
										//연결된 클라이언트 정보얻기
										String clientInfo = "[연결 수락:" + socket.getRemoteSocketAddress() + "]";
										Platform.runLater(() -> display(clientInfo));
										//통신용 Client 객체 생성
										Client client = new Client(socket); //클라이언트 객체 생성해서 socket넘
										//vector에 client 저장
										connections.add(client);
										//총 client 수 출력
										Platform.runLater(() -> display("[연결갯수:" + connections.size() + "]"));

								} catch (IOException ex) {
										stopServer();
										break;
								}

						}

				};

				//스레드풀의 작업큐에 작업넣기
				executorService.submit(acceptTask);

		}

		public void stopServer() {

				try {

						Iterator<Client> iterator = connections.iterator();
						while (iterator.hasNext()) {
								Client client = iterator.next();
								client.socket.close();
								iterator.remove();
						}
						//for(Client client:connections){
						//		client.socket.close();
						//  }
						connections.clear();
						executorService.shutdownNow();
						serverSocket.close();
						Platform.runLater(() -> {
								btnStartStop.setText("시작");
								display("서버멈춤");
						});
				} catch (IOException ex) {
				}

		}

		private void display(String text) {
				txtDisplay.appendText(text + "\n");
		}

		class Client {

				Socket socket;

				Client(Socket socket) {
						this.socket = socket;
						receive();
				}

				void receive() {
						//받기작업정의
						Runnable receiveTask = () -> {
								try {
										while (true) {
												InputStream inputStream = socket.getInputStream();
												byte[] byteArr = new byte[100];
												int readBytes = inputStream.read(byteArr); //데이터 보내기 전까지 여기서 대기가 발생,정상종료-1비정상일때 예외
												if (readBytes == -1) {
														throw new Exception();
												}
												String strData = new String(byteArr, 0, readBytes);
												for (Client client : connections) {
														client.send(strData);
												}
										}
								} catch (Exception e) {
										try {
												String clientInfo = "[연결 끊김:" + socket.getRemoteSocketAddress() + "]";
												Platform.runLater(() -> display(clientInfo));
												this.socket.close();
												connections.remove(Client.this);
												Platform.runLater(() -> display("[연결갯수:" + connections.size() + "]"));
										} catch (IOException ex) {

										}
								}
						};
						//스레드풀의 작업큐에 작업넣기
						executorService.submit(receiveTask);

				}

				void send(String message) {
						Thread thread = new Thread() {
								@Override
								public void run() {
										try {
												OutputStream os = socket.getOutputStream();
												byte[] byteArr = message.getBytes();
												os.write(byteArr);
												os.flush();
										} catch (IOException ex) {
												connections.remove(Client.this);
												try {
														socket.close();
												} catch (IOException ex1) {
												}
										}

								}
						};

				}

		}
}
