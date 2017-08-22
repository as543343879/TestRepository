
package ch18.exam17;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class BufferedExample {
    public static void main(String[] args) throws IOException {
    
        
    InputStream is = System.in;
    InputStreamReader isr = new InputStreamReader(is);
    BufferedReader br = new BufferedReader(isr);
    
    String line = br.readLine();
    System.out.println(line);

    }
    
}
