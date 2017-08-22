package ch18.exam02;

import ch18.exam01.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public class ReadExample {

    public static void main(String[] arg) throws FileNotFoundException, IOException {
        System.out.println("Hello Netbeans");
        String path = ReadExample.class.getResource("test.txt").getPath();
        InputStream is = new FileInputStream(path);

        byte[] data = new byte[3];
        int readBytes = -1;
        String strData = "";

        while (true) {
            readBytes = is.read(data);
            if (readBytes == -1) {
                break;
            }
            System.out.println("읽은 바이트수" + readBytes);
            System.out.println(Arrays.toString(data));
            strData += new String(data, 0, readBytes);

        }
        System.out.println("읽은문자열:" + strData);
        is.close();
        /*
        readBytes= is.read(data);
        System.out.println(readBytes);
        System.out.println("읽은 바이트수"+ readBytes);
        System.out.println(Arrays.toString(data));
        strData = new String(data,0,readBytes);
        System.out.println("읽은문자열:" +strData);
         */

    }
}
