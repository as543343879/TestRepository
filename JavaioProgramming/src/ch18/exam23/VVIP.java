package ch18.exam23;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class VVIP extends Person implements Serializable {
    private String grade;
    private int memberShipNo;

    public VVIP() {
    }

    public VVIP(int memberShipNo, String grade, String name, int age) {
        super(name, age);
        this.memberShipNo = memberShipNo;
        this.grade = grade;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public int getMemberShipNo() {
        return memberShipNo;
    }

    public void setMemberShipNo(int memberShipNo) {
        this.memberShipNo = memberShipNo;
    }

    private void writeObject(ObjectOutputStream out) throws IOException { //부모쪽내용을 출력
        out.writeUTF(getName());
        out.writeInt(getAge());
        out.defaultWriteObject();

    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {  //읽는쪽 //public으로 하면 안됨
        setName(in.readUTF());
        setAge(in.readInt());
        in.defaultReadObject();
    }

}
