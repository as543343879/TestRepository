package com.mycompany.myapp.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/http")
public class Exam08HttpController {

	@RequestMapping(value = "/exam01", method = RequestMethod.GET)    //요청방식으로 구분을 하고싶을때 사용
	public String exam01Get() {
		System.out.println("exam01Get()..........");
		return "http/exam01";
	}

	@RequestMapping(value = "/exam01", method = RequestMethod.POST)
	public String exam01Post() {
		System.out.println("exam01Post()..........");
		return "http/exam01";
	}

	@RequestMapping("/exam02")
	public String exam02(HttpServletRequest request, Model model) {         //요청 http에 대한 모든 내용을 가지고 있는 request
		String method = request.getMethod();
		String uri = request.getRequestURI();
		String queryString = request.getQueryString();
		String type = request.getParameter("type");
		String bno = request.getParameter("bno");
		int bnoInt = Integer.parseInt(request.getParameter("bno"));
		String userAgent = request.getHeader("User-Agent");
		String[] hobby = request.getParameterValues("hobby");                 //hobby라는 이름을 가진 파라미터들을 배열로 저장해서 받아옴

		if (userAgent.contains("MSIE") || userAgent.contains("Trident")) {
			userAgent = "IE11 이하 브라우저";
		} else if (userAgent.contains("Edge")) {
			userAgent = "엣지브라우저";
		} else if (userAgent.contains("Chrome")) {
			userAgent = "크롬브라우저";
		}

		model.addAttribute("method", method);
		model.addAttribute("uri", uri);
		model.addAttribute("queryString", queryString);
		model.addAttribute("type", type);
		model.addAttribute("bno", bno);
		model.addAttribute("bnoInt", bnoInt);
		model.addAttribute("hobby", hobby);
		model.addAttribute("userAgent", userAgent);
		return "http/exam02";
	}

	@RequestMapping("/exam03")
	public String exam03(
					@RequestParam("type") String ho, //요청파라미터와 다른이름으로 변수를 선언할 경우
					@RequestParam int bno,
					String[] hobby, //앞에 붙는 @RequestParam도 생략 가능하다. 요청파라미터의 이름과 똑같은 이름으로 선언
					@RequestHeader("User-Agent") String userAgent,
					Model model) { //파라미터의 이름과 같은 이름의 매개변수일 경우 이런식으로 불러올 수 있다.

		model.addAttribute("type", ho);
		model.addAttribute("hobby", hobby);
		model.addAttribute("userAgent", userAgent);
		model.addAttribute("bno", bno);

		return "http/exam03";
	}

}
