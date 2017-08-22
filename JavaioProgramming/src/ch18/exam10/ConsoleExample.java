
package ch18.exam10;

import java.io.Console;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Scanner;

public class ConsoleExample {
    public static void main(String[] args) throws IOException{
       // int keycode = System.in.read();
        
        //InputStream is = System.in;  위에꺼랑 같은 의미
        //int keycode = is.read();
        
        //How1
        InputStream is = System.in;  //System 클래스의 inputstream타입의 in 필드 
        byte[] keycodes=new byte[10];
        int readBytes= is.read(keycodes);
        System.out.println(Arrays.toString(keycodes));
        String str=new String(keycodes,0,readBytes-2);
        System.out.println(str);
        
        //how2
        Scanner scanner = new Scanner(System.in);
        String str2=scanner.nextLine();
       System.out.println(str2);        
        
        
        //how3
        Console console = System.console(); // 콘솔의 경우는 문자열로만 읽을 수 있다.
        String str3 = console.readLine();
        System.out.println(str3);   
    }
        
    
}
