package com.mycompany.myapp.dao.sub;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.mycompany.myapp.dao.Exam10Dao3;
@Component("subExam10Dao3ImplA")  //앞으로 이 이름으로 클래스를 관리하겠다라는 의미
public class Exam10Dao3ImplA implements Exam10Dao3 {
	
	private static final Logger logger=LoggerFactory.getLogger(Exam10Dao3ImplA.class);

	@Override
	public void insert() {
		logger.info("회원가입처리");

	}

	@Override
	public void select() {
		logger.info("회원검색");

	}
}
