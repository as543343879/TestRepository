package ch18.exam22;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

public class ObjectInputExample {
    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream("C:/Temp/object.dat");
        ObjectInputStream ois = new ObjectInputStream(fis);
        
        Member member = (Member) ois.readObject();
        System.out.println(member.getName());
        System.out.println(member.getAge());
        
        fis.close();
        ois.close();
    }
}
