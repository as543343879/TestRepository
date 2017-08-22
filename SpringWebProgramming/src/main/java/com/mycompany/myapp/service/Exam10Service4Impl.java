package com.mycompany.myapp.service;

import com.mycompany.myapp.dao.Exam10Dao2;

public class Exam10Service4Impl implements Exam10Service4 {

	private Exam10Dao2 exam10Dao;
	
	public void setExam10Dao(Exam10Dao2 exam10Dao) {
		this.exam10Dao = exam10Dao;
	}

	@Override
	public void join() {
		System.out.println("Exam10Service4Impl-join() 실행");
		exam10Dao.insert();
	}

	@Override
	public void login() {
		System.out.println("Exam10Service4Impl-login() 실행");
		exam10Dao.select();
	}

}
