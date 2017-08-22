package ch18.exam25.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

public class ServerExample1 {

    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        try {   //현재 네트워크 어뎁터가 사용할 수 없는 상태일때를 위한 예외처리
            //서버소켓생성
            serverSocket = new ServerSocket(); //네트워크카드가 문제 있을경우 예외발생
            //포트와 바인딩 (서버와 포트를 연결)
            serverSocket.bind(new InetSocketAddress("192.168.3.31",50001)); //192.168.3.10의 IP로 오는 요청만 수락하겠다는 뜻 //이미 50001번을 사용할 경우 예외발생
            //연결기다리기
            Socket socket =serverSocket.accept(); //클라이언트 요청이 오기전까지 대기상태
            //클라이언트의 정보얻기
            InetSocketAddress isa = (InetSocketAddress)socket.getRemoteSocketAddress();//inetsocketaddress를 리턴함
            System.out.println(isa.toString());  //대표문자열 리턴
            System.out.println(isa.getHostName());
            System.out.println(isa.getAddress());

            //통신하기
            
            
            //연결끊기
            socket.close();
        } catch (IOException ex) {
           ex.printStackTrace();
        }
        //close하기 전에 서버소켓이 50001번을 현재 사용하고 있느냐를 물어보는부분
        if(serverSocket!=null&&!serverSocket.isClosed()){
            //서버소켓닫기
            try {
                serverSocket.close(); //서버소켓닫기(50001번을 해제하겠다는 의미)
            } catch (IOException ex1) { }
        }
        }
        }
    


