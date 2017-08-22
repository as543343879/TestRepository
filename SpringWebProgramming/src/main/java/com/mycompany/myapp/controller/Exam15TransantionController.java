package com.mycompany.myapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mycompany.myapp.exception.NoAccountException;
import com.mycompany.myapp.service.Exam15Service;

@Controller
public class Exam15TransantionController {
	private static final Logger LOGGER=LoggerFactory.getLogger(Exam15TransantionController.class);
	@Autowired
	private Exam15Service service;
	
	@RequestMapping(value="/transaction/exam01",method=RequestMethod.GET)
	public String exam01(){
		return "transaction/exam01";
	}
	
	@RequestMapping(value="/transaction/exam01",method=RequestMethod.POST)
	public String exam01(String fromAno,String toAno,int amount){
		service.transfer(fromAno, toAno, amount);
		return "redirect:/";
	}
	
	//해당 메소드는 해당 매개변수 타입의 예외를 처리하는 용도이다. 
	//해당 매개변수 타입의 예외가 발생했을 시에 동작한다.
	//해당 컨트롤러에서만 발생하는 예외 처리 방법
	@ExceptionHandler
	public String handleNoAccountException(NoAccountException e,Model model){  //메소드 이름은 중요x
		model.addAttribute("reason",e.getMessage());
		LOGGER.info("실행");
		return "transaction/exam02";
	}

}
