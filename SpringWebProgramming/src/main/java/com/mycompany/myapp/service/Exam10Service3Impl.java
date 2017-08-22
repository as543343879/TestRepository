package com.mycompany.myapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mycompany.myapp.dao.Exam10Dao1;

@Component // 객체로만들수 있는것만 component를 붙임 , 스프링 관리 객체가 된다.
public class Exam10Service3Impl implements Exam10Service3 {

	private Exam10Dao1 exam10Dao;

	
	//생성자 주입
	@Autowired
	public Exam10Service3Impl(Exam10Dao1 exam10Dao){ //객체가 생성될 때 주입한다.
		this.exam10Dao=exam10Dao;
	}

	@Override
	public void join() {
		System.out.println("Exam10Service3Impl-join() 실행");
		exam10Dao.insert();
	}

	@Override
	public void login() {
		System.out.println("Exam10Service3Impl-login() 실행");
		exam10Dao.select();
	}

}
