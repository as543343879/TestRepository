package ch18.exam22;

import java.io.Serializable;

public class Member implements Serializable {
    //Field
    private static final long serialVersionUID = 1;
    private String name;
    private int age;
    //Constructor
    public Member(String name, int age) {
    this.name = name;
    this.age = age;    
    }
    //Metsod
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }
}
