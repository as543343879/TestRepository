package com.mycompany.myapp.service;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.mycompany.myapp.dao.Exam15Dao;
import com.mycompany.myapp.dto.Exam15Account;
import com.mycompany.myapp.exception.NoAccountException;
@Component
public class Exam15ServiceImpl implements Exam15Service {

	@Autowired
	private Exam15Dao dao;
	@Override
	public Exam15Account getAccount(String ano) {
		Exam15Account account=dao.selectByAno(ano);
		return account;
	}

	@Override
	public void deposit(String ano,int amount) {
		Exam15Account account=dao.selectByAno(ano);
		account.setAbalance(account.getAbalance()+amount);
		int state=dao.update(account);

	}

	@Override
	public void withdraw(String ano,int amount) {
		Exam15Account account=dao.selectByAno(ano);
		account.setAbalance(account.getAbalance()-amount);
		dao.update(account);

	}

	
	@Override
	@Transactional
	public void transfer(String fromAno, String toAno, int amount) throws NoAccountException{
		Exam15Account fromAccount=dao.selectByAno(fromAno);
		if(fromAccount!=null){
		fromAccount.setAbalance(fromAccount.getAbalance()-amount);
		dao.update(fromAccount);
		}else{
			throw new NoAccountException("출금계좌가존재하지 않음");
		}
		
		
		Exam15Account toAccount=dao.selectByAno(toAno);
		if(toAccount!=null){
		toAccount.setAbalance(toAccount.getAbalance()+amount);
		dao.update(toAccount);
		}else{
			throw new NoAccountException("출금계좌가존재하지 않음");
		}

	}

}
