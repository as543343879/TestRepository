package com.mycompany.myapp.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
@Component
public class Exam10Dao3ImplB implements Exam10Dao3 {
	
	private static final Logger logger=LoggerFactory.getLogger(Exam10Dao3ImplB.class);

	@Override
	public void insert() {
		logger.info("회원가입처리");

	}

	@Override
	public void select() {
		logger.info("회원검색");

	}
}
