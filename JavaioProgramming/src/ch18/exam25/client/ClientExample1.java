
package ch18.exam25.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class ClientExample1 {
    public static void main(String[] args) {
        Socket socket=null;
        try {
            //How1
            //socket = new Socket("192.168.3.31",50001);
          
            //How2
            socket=new Socket();
            socket.connect(new InetSocketAddress("192.168.3.31",50001)); // 연결하고 연결끊고를 반복하는 작업일 시에 사용. 매번 소켓생성을 하지 않아도 되서
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        if(!socket.isClosed()){
        try {socket.close();} catch (IOException ex) {}
        }
        
    }

}
