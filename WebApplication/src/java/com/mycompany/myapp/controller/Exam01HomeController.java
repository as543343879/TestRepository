package com.mycompany.myapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Exam01HomeController {
	@RequestMapping("/")
	public String home() {
		System.out.println("home()");
		return "home";
	}

}
