package com.mycompany.myapp.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.mycompany.myapp.dao.Exam10Dao3;
@Component
public class Exam10Service6Impl implements Exam10Service6 {

   @Resource(name="exam10Dao3ImplA")
   private Exam10Dao3 exam10Dao;
   
	@Override
	public void join() {
		System.out.println("Exam10Service5Impl-join() 실행");
		exam10Dao.insert();
	}

	@Override
	public void login() {
		System.out.println("Exam10Service5Impl-login() 실행");
		exam10Dao.select();
	}

}
