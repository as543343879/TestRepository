package ch18.exam13;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class InputStreamReaderExample {

    public static void main(String[] args) throws IOException {

        // InputStream is = newFileInputStream
        // socket. ~ 은 소켓에서 읽는거
        /*
        InputStream is = System.in;
        InputStreamReader isr = new InputStreamReader(is);
        int data = isr.read();
        while()
        System.out.println((char)data);
        */

        InputStream is = new FileInputStream("src/ch18/exam13/test2.txt");
        InputStreamReader isr = new InputStreamReader(is,"EUC-KR");
        int data = isr.read();
        System.out.println((char)data);
        
    }

}
