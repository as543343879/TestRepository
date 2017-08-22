
package com.mycompany.myapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mycompany.myapp.service.Exam10Service1;
import com.mycompany.myapp.service.Exam10Service2;
import com.mycompany.myapp.service.Exam10Service3;
import com.mycompany.myapp.service.Exam10Service4;
import com.mycompany.myapp.service.Exam10Service5;
import com.mycompany.myapp.service.Exam10Service6;

@Controller
public class Exam10DIController {
	@Autowired
	private Exam10Service1 exam10Service1; //service 계층이바껴도 controller를 수정하지 않도록 이런식으로 구현하도록함 , 인터페이스 타입이지만 자동으로 구현객체를 대입해줌
	
	//autowired를 사용하지 않고 주입하는 두번째 방법
	@Autowired
	private Exam10Service2 exam10Service2;	
	
	@Autowired
	private Exam10Service3 exam10Service3;	
	@Autowired
	private Exam10Service4 exam10Service4;	
	@Autowired
	private Exam10Service5 exam10Service5;
	@Autowired
	private Exam10Service6 exam10Service6;
    

@RequestMapping("/di/exam01")
public String exam01(){
	System.out.println("exam01()실행");
	exam10Service1.join();
	exam10Service2.join();
	exam10Service3.join();
	exam10Service4.join();
	exam10Service5.join();
	exam10Service6.join();
	return "di/exam01";
}

@RequestMapping("/di/exam02")
public String exam02(){
	System.out.println("exam02()실행");
	exam10Service1.login();
	exam10Service2.login();
	exam10Service3.login();
	exam10Service4.login();
	exam10Service5.login();
	exam10Service6.login();
	return "di/exam01";
}

}
