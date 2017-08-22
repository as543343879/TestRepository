
package com.mycompany.myapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Exam02HtmlController {
	
	@RequestMapping("/html/exam01") // http://localhost:8080/WebApplication이 앞에 생략된 형태
	public String html() {
		return "html/exam01";
	}

}
