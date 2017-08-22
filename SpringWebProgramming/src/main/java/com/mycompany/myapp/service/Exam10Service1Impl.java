package com.mycompany.myapp.service;

import com.mycompany.myapp.dao.Exam10Dao1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component //객체로만들수 있는것만 component를 붙임 , 스프링 관리 객체가 된다.
public class Exam10Service1Impl implements Exam10Service1 {
@Autowired //스프링이 관리하는 객체중에서 해당하는 객체가 있으면 자동으로 대입을 해줘라 라는뜻
private Exam10Dao1 exam10Dao;

   @Override
	public void join(){
		System.out.println("Exam10Service1Impl-join() 실행");
		exam10Dao.insert();
	}
	@Override
	public void login(){
			System.out.println("Exam10Service1Impl-login() 실행");
			exam10Dao.select();
		}

}
