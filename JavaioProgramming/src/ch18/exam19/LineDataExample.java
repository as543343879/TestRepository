
package ch18.exam19;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class LineDataExample {
    public static void main(String[] args) throws IOException {
        FileOutputStream fw=new FileOutputStream("src/ch18/exam19/text.txt");
        OutputStreamWriter osw = new OutputStreamWriter(fw);
        
        osw.write("abcde\r\n");
        osw.write("12345\r\n");
        osw.write("가나다라마");
        osw.flush();
        fw.flush();
        osw.close();
        fw.close();
        
        FileOutputStream fos = new FileOutputStream("src/ch18/exam19/text.txt"); //행단위로 보낼때
        //PrintStream out = new PrintStream(fos);
        PrintWriter out = new PrintWriter(fos);
        out.println("abcdef");
        out.println("12345");
        out.println("가나다라마");
        out.flush();
        fos.flush();
        out.close();
        fos.close();
        
        
        
        FileInputStream fis = new FileInputStream("src/ch18/exam19/text.txt");  //행단위로 보낼때
        InputStreamReader isr = new InputStreamReader(fis);
        BufferedReader br = new BufferedReader(isr);
        
        while(true){
            String line = br.readLine();
           
            if(line==null)break;
            System.out.println(line);
            
        }
        System.out.printf("ㅋㅋ");
        br.close();
        isr.close();
        
        
        
    }

}
