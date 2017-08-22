package ch18.exam16;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
//버퍼 달았을때의 시간 비교
public class BufferedExample {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        byte[] data = "가나다".getBytes();
        
        OutputStream os = new FileOutputStream("src/ch18/exam15/test.txt");
        OutputStreamWriter osw = new OutputStreamWriter(os);
        BufferedWriter bw = new BufferedWriter(osw);
        //프로그램과 가까운 순서대로 닫아줘
        

        long startTime = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            os.write(data);
        }
        
        bw.flush();
        osw.flush();
        os.flush();
        
        //프로그램과 가까운 순서대로 닫아줘 
        bw.close();
        osw.close();
        os.close();
        
    }

}
