
package ch18.exam20;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

public class ObjectInputExample {
    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream("src/ch18/exam20/object.dat");
        ObjectInputStream ois = new ObjectInputStream(fis);
        
        boolean a =ois.readObject() instanceof Member;
        Member member =(Member)ois.readObject();
        System.out.println(member.getAge());
        System.out.println(member.getName());
        ois.close();
        fis.close();
    }

}
