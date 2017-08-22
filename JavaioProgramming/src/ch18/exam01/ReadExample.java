package ch18.exam01;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class ReadExample {

    public static void main(String[] arg) throws FileNotFoundException, IOException {
        System.out.println("Hello Netbeans");
        String path = ReadExample.class.getResource("test.txt").getPath();
        InputStream is = new FileInputStream(path);

        while (true) {
            int v1 = is.read();
            if (v1 == -1) {
                break;
            }
            System.out.println("v1:" + (char) v1+"ww"); //v1이 바이트로 읽혀지는데 (char)를 해주면 다시 문자로 돌아감
        }
            int v2 = -1;
            while ((v2 = is.read()) != -1) {
                System.out.print((char) v2+"sss");
            }
            is.close();

        }
    }

