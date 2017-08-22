
package ch18.exam28;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class ReceiveExample {
    public static void main(String[] args) throws SocketException, IOException {
        DatagramSocket datagramSocket = new DatagramSocket(50002);//받는쪽은 포트가 있어야됨
        DatagramPacket datagramPacket = new DatagramPacket(new byte[100],100);
       datagramSocket.receive(datagramPacket);   //받을때까지 대기상태
       
       byte[] data = datagramPacket.getData();
       int readBytes= datagramPacket.getLength();
       String strData = new String(data,0,readBytes);
       System.out.println("받은데이터 :"+ strData);
       
       datagramSocket.close();
        
 }
}


