
package com.mycompany.myapp.controller;

import com.mycompany.myapp.service.Exam10Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Exam10DIController {
	@Autowired
	private Exam10Service exam10Service; //service 계층이바껴도 controller를 수정하지 않도록 이런식으로 구현하도록함
	
@RequestMapping("/di/exam01")
public String exam01(){
	System.out.println("exam01()실행");
	exam10Service.join();
	
	return "di/exam01";
}

@RequestMapping("/di/exam02")
public String exam02(){
	System.out.println("exam02()실행");
	exam10Service.login();
	return "di/exam01";
}

}
