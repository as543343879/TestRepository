package ch18.exam03;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;


public class WriteExample {

    public static void main(String[] arg) throws FileNotFoundException, IOException, InterruptedException {
       // String path = WriteExample.class.getResource("test.txt").getPath(); //build 폴더 위치
        OutputStream os = new FileOutputStream("src/ch18/exam03/test.txt"); // 
        
        for(int i=0;i<20;i++){
        os.write(97);
        os.write(100);
        }
        //os.flush();
        //os.close();
        //Thread.sleep(100000);
        
        
        
        

        }
    }

