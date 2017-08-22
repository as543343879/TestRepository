
package ch18.exam20;

import java.io.Serializable;

public class Member implements Serializable{  //개발자가 외부로 보낼수 있도록 하는 허가사항
    
    private String name;
    private int age;
  
    public Member(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setMname(String mname) {
        this.name = mname;
    }

    public void setAge(int age) {
        this.age = age;
    }
    
    

}
