package com.mycompany.myapp.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.mycompany.myapp.dto.Exam13Member;

@Controller
@SessionAttributes(value={"name1","name2","member"}) //각각의 키 이름들은 세션에 저장해야 할 것들이다. value=는 기본이기 때문에 생략 가능
//하나만 사용할 경우 @SessionAttributes("name1") 로 사용
public class Exam13CookieAndSessionController {
	private static final Logger LOGGER=LoggerFactory.getLogger(Exam13CookieAndSessionController.class);
	
	@RequestMapping("/cookie/exam01")
	public String exam01(HttpServletResponse response) throws UnsupportedEncodingException{
		
		//쿠키 생성
		//쿠키는 헤더에 포함되기 때문에 이름은반드시 아스키문자여야 한다.(영문,숫자)
		Cookie cookie1=new Cookie("name1","hongkildong"); //(쿠키이름,값)
		//한글문자를 사용하려면 아래와 같이 해야함
		String name2="홍길동";
		//name2를 UTF-8 타입으로 바이트변환한다.
		name2=URLEncoder.encode(name2,"UTF-8");//URL 인코딩
		//이렇게 바뀐 것을 다시 쿠키에 넣어줌
		Cookie cookie2=new Cookie("name2",name2); //(쿠키이름)
		cookie2.setMaxAge(30*24*60*60);//초로 저장됨(1달을 계산한것) // 이렇게 지정해주면 쿠키를 해당 기간까지 하드디스크에 저장함
		//쿠키를 응답 헤더에 추가
		response.addCookie(cookie1);
		response.addCookie(cookie2);
		
		return "cookie/exam01";
	}
	/*
	@RequestMapping("/cookie/exam02")
	public String exam02(HttpServletRequest request,Model model) throws UnsupportedEncodingException{
		String name1=null;
		String name2=null;
		Cookie[] cookies=request.getCookies();
		for(Cookie cookie:cookies){
			if(cookie.getName().equals("name1")){
				name1=cookie.getValue();
			}else if(cookie.getName().equals("name2")){
				name2=cookie.getValue();
				//헤더첨부를 위해 아스키코드로 변환한것 다시 디코딩하기
				name2=URLDecoder.decode(name2,"UTF-8");
			}
		}
		model.addAttribute("name1",name1);
		model.addAttribute("name2",name2);
		return "cookie/exam02";
	}
*/
	
	//name1과 name2를 바로 받기
	@RequestMapping("/cookie/exam02")
	public String exam02(@CookieValue(defaultValue="") String name1,@CookieValue(defaultValue="") String name2,Model model) throws UnsupportedEncodingException{    //쿠키의 키이름과 변수이름과 동일할 경우는 생략가능//자동으로 디코딩까지 해줌
		model.addAttribute("name1",name1);
		model.addAttribute("name2",name2);
		return "cookie/exam02";
	}
	
	//쿠키삭제
	//쿠키삭제를 할때는 쿠키의 만료일을 현재시점으로 설정함
	@RequestMapping("/cookie/exam03")
	public String exam03(HttpServletResponse response){
		//name1의 쿠키를 해당 값으로 바꿈
		Cookie cookie1= new Cookie("name1","");
		Cookie cookie2=new Cookie("name2","");
		
		//만료를 지금으로 설정하여 쿠키 삭제
		cookie1.setMaxAge(0);
		cookie2.setMaxAge(0);
		
		response.addCookie(cookie1);
		response.addCookie(cookie2);
		return "redirect:/";
	}
	/*
	@RequestMapping("/session/exam04")
	public String exam04(HttpSession session){    //자동으로 만들어져 있는 세션객체가 들어옴
		//세션에 문자열 정보를 저장
		session.setAttribute("name1", "hongkildong");
		session.setAttribute("name2", "홍길동");
		//세션에 객체를 저장
		Exam13Member member=new Exam13Member();
		member.setMname("스프링");
		session.setAttribute("member", member);

		return "redirect:/";
	}
	
	*/
	@RequestMapping("/session/exam04")
	public String exam04(Model model){
		//원래 model은 request 범위에 저장하도록 하는 것이다. 
		//하지만 여기서는 위에 @SessionAttributes를 사용하여 세션에 저장하라고 했기 때문에 이것들은 request가 아니라 session에 저장된다.
		model.addAttribute("name1","hongkildong");
		model.addAttribute("name2","홍길동");
		Exam13Member member=new Exam13Member();
		member.setMname("스프링");
		model.addAttribute("member",member);
	
		return "redirect:/";
	}
	/*
	@RequestMapping("/session/exam05")
	public String exam05(HttpSession session){  
		//세션에서 문자열 정보 가져오기
		String name1=(String)session.getAttribute("name1");
		String name2=(String)session.getAttribute("name2");
		Exam13Member member=(Exam13Member)session.getAttribute("member");
		//세션을 이용하면 모델로 넘겨줄 필요가 없다.
		
		LOGGER.info(name1);
		LOGGER.info(name2);
		LOGGER.info(member.getMname());
		
		return "session/exam05";
	}
	
	*/
	@RequestMapping("/session/exam05")
	public String exam05(HttpSession session,@ModelAttribute String name1,@ModelAttribute String name2,@ModelAttribute Exam13Member member){  //modelattribute는 ${}와 비슷하다. session에서 해당 키를 찾아서 넣어주고 없으면 application에서 찾아넣어줌
		//@ModelAttributes를 사용하여 model로 세션에 넣어준것을 가져올때 @ModelAttributes를 이용하여 가져오는게 스프링다운 방법이다. 꼭 이렇게 안하고 session으로 해도 가능하긴함.
		//String name1=(String)session.getAttribute("name1");
	//	String name2=(String)session.getAttribute("name2");

		LOGGER.debug(name1);
		LOGGER.debug(name2);
		LOGGER.debug(member.getMname());
		return "session/exam05";
	}
	
	/*
	@RequestMapping("/session/exam06")
	public String exam06(HttpSession session){
		//세션에서 지울때는 무조건 session 선언해야됨, @ModelAttribute 없이 session으로 사용했을경우만 삭제가 가능 
		 //세션에서 삭제해도 model에는 남아있을 수 있다. @SessionAttributes 대신 HttpSession만 이용할 경우에 사용
		session.removeAttribute("name1");
		session.removeAttribute("name2");
		session.removeAttribute("member");

		return "redirect:/";
	}
	*/
	@RequestMapping("/session/exam06")
	public String exam06(SessionStatus sessionStatus){
		//세션에 있는 모든 정보를 삭제
		//@SessionAttribute를 사용할 경우 이용
		sessionStatus.setComplete();
		return "redirect:/";
	}
}
