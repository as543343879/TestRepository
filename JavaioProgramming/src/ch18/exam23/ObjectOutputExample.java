package ch18.exam23;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class ObjectOutputExample {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        FileOutputStream fos = new FileOutputStream("src/ch18/exam23/object.dat");
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        VVIP vvip = new VVIP(1,"A","홍길동",30);
        oos.writeObject(vvip);

        oos.flush();
        fos.flush();
        oos.close();
        fos.close();
    }

}
