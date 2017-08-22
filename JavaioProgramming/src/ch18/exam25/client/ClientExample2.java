package ch18.exam25.client;

 

import java.io.IOException;

import java.io.InputStream;

import java.io.OutputStream;

import java.net.InetSocketAddress;

import java.net.Socket;

 

public class ClientExample2 {

    public static void main(String[] args) {

       Socket socket = null;
        try {
            //소켓 생성
            socket = new Socket();
            //연결 요청
            socket.connect(new InetSocketAddress("192.168.3.31", 50001));
            //통신하기

            OutputStream os = socket.getOutputStream();
            String strData = "감자바";
            byte[] data = strData.getBytes("UTF-8");
            os.write(data);
            os.flush();
            System.out.println("데이터 보내기 성공");
            
            InputStream is = socket.getInputStream();
            data = new byte[100];
            int readBytes = is.read(data);
            strData = new String(data, 0, readBytes, "UTF-8");
            System.out.println("받은 데이터: " + strData);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        if(!socket.isClosed()){

            try { socket.close();} catch (IOException ex) { }

        }

        

    }

}
