package ch18.exam15;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
//버퍼 달았을때의 시간 비교
public class BufferedExample {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        OutputStream os = new FileOutputStream("src/ch18/exam15/test.txt");
        BufferedOutputStream bos = new BufferedOutputStream(os);
        byte[] data = "가나다".getBytes();

        long startTime = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            os.write(data);
        }
        os.flush();
        long endTime = System.nanoTime();

        System.out.println(endTime - startTime);

        os.close();
    }

}
