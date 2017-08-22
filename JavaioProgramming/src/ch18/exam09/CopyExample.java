
package ch18.exam09;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

public class CopyExample {
    public static void main(String[] args) throws IOException{
        
        Writer writer = new FileWriter("src/ch18/exam09/test2.txt");
        Reader reader = new FileReader("src/ch18/exam09/test.txt");
        
        char[] ch=new char[4];
        int count=-1;
        
        
        while(true)
        {
            
        count=reader.read(ch);
        if(count==-1)break;
        /*String str=new String(ch,0,count);
        writer.write(str);
*/
        writer.write(ch,0,count);
        
        }
        
        
        
        
        writer.flush();
        writer.close();
        reader.close();
    }
    
}
