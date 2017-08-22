
package com.mycompany.myapp.dto;
public class Member {
	private String name;
	private int age;
	public Member(){
		
	}
	
	public Member(String name,int age){
		this.age=age;
		this.name=name;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAge(int age) {
		this.age = age;
	}
	

}
