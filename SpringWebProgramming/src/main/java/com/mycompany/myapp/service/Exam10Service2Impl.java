package com.mycompany.myapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mycompany.myapp.dao.Exam10Dao1;

@Component // 객체로만들수 있는것만 component를 붙임 , 스프링 관리 객체가 된다.
public class Exam10Service2Impl implements Exam10Service2 {

	private Exam10Dao1 exam10Dao;

	
	//세터주입
	@Autowired
	public void setExam10Dao1(Exam10Dao1 exam10Dao) {
		this.exam10Dao = exam10Dao;
		//추가적인 초기화 코드를 넣을 수 있다.
	}

	@Override
	public void join() {
		System.out.println("Exam10Service2Impl-join() 실행");
		exam10Dao.insert();
	}

	@Override
	public void login() {
		System.out.println("Exam10Service2Impl-login() 실행");
		exam10Dao.select();
	}

}
