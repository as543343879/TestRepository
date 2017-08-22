
package ch18.exam21;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

public class ObjectInputExample {
    public static void main(String[] args) throws FileNotFoundException, IOException, IOException, ClassNotFoundException {
        FileInputStream fis=new FileInputStream("src/ch18/exam20/Object.dat");
        ObjectInputStream ois= new ObjectInputStream(fis);
        
        Member member= (Member) ois.readObject();
        
        System.out.println(member.getMname());
        System.out.println(member.getAge());
        System.out.println(member.nation);
        
        ois.close();
        fis.close();
    }
}
