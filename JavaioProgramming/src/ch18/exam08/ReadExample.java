package ch18.exam08;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;


public class ReadExample {

    public static void main(String[] arg) throws FileNotFoundException, IOException {

        //String path = ReadExample.class.getResource("test.txt").getPath();
        
        Writer writer =new FileWriter("src/ch18/exam08/test.txt");
        
        char c1='가';
        writer.write(c1);
        
        char[] c2={'나','다','라'};
        writer.write(c2);
        
        writer.write(c2,0,2);
        
        writer.flush();
        writer.close();
        
        
        
    }
}
