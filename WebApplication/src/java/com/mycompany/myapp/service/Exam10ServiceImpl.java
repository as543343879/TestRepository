package com.mycompany.myapp.service;

import com.mycompany.myapp.dao.Exam10Dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component //객체로만들수 있는것만 component를 붙임
public class Exam10ServiceImpl implements Exam10Service {
@Autowired
private Exam10Dao exam10Dao;

   @Override
	public void join(){
		System.out.println("join() z");
		exam10Dao.insert();
	}
	@Override
	public void login(){
			System.out.println("login() d");
			exam10Dao.select();
		}

}
