package com.mycompany.myapp.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Exam14AopController {
	@RequestMapping("/aop/exam01")
	public String exam01(HttpSession session){
		session.setAttribute("mid","hi");
		return "aop/exam01";
	}
	
	
	@RequestMapping("/aop/exam02Write")
	public String exam02Write(){
		return "aop/exam02";
	}
	
	@RequestMapping("/aop/exam02Update")
	public String exam02Update(){
		return "aop/exam02";
	}

}
