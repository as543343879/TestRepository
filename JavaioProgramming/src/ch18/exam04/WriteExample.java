package ch18.exam04;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;


public class WriteExample {

    public static void main(String[] arg) throws FileNotFoundException, IOException, InterruptedException {
       // String path = WriteExample.class.getResource("test.txt").getPath(); //build 폴더 위치
        OutputStream os = new FileOutputStream("src/ch18/exam04/test.txt"); // 
        
       byte[] data = {97,98,99};
       
        os.write(data);
        os.write(data,1,2);
        
        
        
        os.flush();
        os.close();
        
        
        
        
        

        }
    }

