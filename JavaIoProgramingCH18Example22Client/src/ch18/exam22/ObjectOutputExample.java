package ch18.exam22;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class ObjectOutputExample {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        FileOutputStream fos = new FileOutputStream("C:/Temp/object.dat");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        
        Member member = new Member("감자바",30);
        oos.writeObject(member);
        
        
        oos.flush();
        fos.flush();
        oos.close();
        fos.close();
        
    }
}
