package ch18.exam30.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javafx.application.Platform;
import javafx.event.ActionEvent;
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
        btnStartStop.setOnAction(e -> handleBtnStartStop(e));
    }

    private void handleBtnStartStop(ActionEvent e) {
        if (btnStartStop.getText().equals("시작")) {
            startServer();
        } else if (btnStartStop.getText().equals("멈춤")) {
            stopServer();
        }
    }

    private void startServer() {
        //스레드풀 생성
        executorService = Executors.newFixedThreadPool(100);

        //서버 소켓 생성
        try {
            serverSocket = new ServerSocket();
            serverSocket.bind(new InetSocketAddress("192.168.56.1", 50001));
        } catch (IOException ex) {
            stopServer();
            return;
        }

        //연결 수락 작업 정의
        Runnable acceptTask = () -> {
            //UI 변경 코드
            Platform.runLater(() -> {
                btnStartStop.setText("멈춤");
                display("서버 시작...");
            });
            while (true) {
                try {
                    //수락 코드
                    Socket socket = serverSocket.accept();
                    //연결된 클라이언트 정보 출력
                    String clientInfo = "[연결 수락: " + socket.getRemoteSocketAddress() + "]";
                    Platform.runLater(() -> display(clientInfo));
                    //통신용 Client 객체 생성
                    Client client = new Client(socket);
                    //Vector에 Client 저장
                    connections.add(client);
                    //총 Client 수 출력
                    Platform.runLater(() -> display("[연결 개수: " + connections.size() + "]"));
                } catch (IOException ex) {
                    stopServer();
                    break;
                }
            }
        };

        //스레드풀의 작업큐에 작업 넣기
        executorService.submit(acceptTask);
    }

    public void stopServer() {
        try {
            for (Client client : connections) {
                client.socket.close(); //소켓닫기
            }
            connections.clear(); //리스트를 지움

            executorService.shutdownNow(); //스레드풀 종료시킴
            serverSocket.close();   //서버소켓닫기
        } catch (IOException ex) {
        }
        Platform.runLater(() -> {
            btnStartStop.setText("시작");
            display("서버 멈춤...");
        });
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
            //받기 작업 정의
            Runnable receiveTask = () -> {
                try {
                    while (true) {
                        InputStream inputStream = socket.getInputStream();
                        byte[] byteArr = new byte[100];
                        int readBytes = inputStream.read(byteArr);
                        if (readBytes == -1) {
                            throw new Exception();
                        }
                        String strData = new String(byteArr, 0, readBytes);
                        String txtdata = socket.getRemoteSocketAddress() + " :" + strData;
                        for (Client client : connections) {
                            client.send(txtdata);
                        }
                    }
                } catch (Exception e) {
                    try {
                        String clientInfo = "[연결 끊김: " + socket.getRemoteSocketAddress() + "]";
                        Platform.runLater(() -> display(clientInfo));
                        this.socket.close();
                        connections.remove(Client.this);
                        Platform.runLater(() -> display("[연결 개수: " + connections.size() + "]"));
                    } catch (IOException ex) {
                    }
                }
            };

            //스레드풀의 작업큐에 작업 넣기
            executorService.submit(receiveTask);
        }

        void send(String message) {
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
    }
}
