package ch18.exam12;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class CopyExample {
    public static void main(String[] args) throws FileNotFoundException, IOException {
    FileInputStream fis = new FileInputStream("src/ch18/exam12/Desert.jpg");
    OutputStream fos = new FileOutputStream("src/ch18/exam12/Desert3.jpg");
       
    byte[] data = new byte[1024];
    int readBytes = -1;
    while(true){
        readBytes=fis.read(data);
        if(readBytes == -1){
            break;
        }
        fos.write(data,0,readBytes);
    }
    
    fos.flush();
    fos.close();
    fos.close();
    
    }
    
    
}
