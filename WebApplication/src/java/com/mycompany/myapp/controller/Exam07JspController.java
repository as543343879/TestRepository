package com.mycompany.myapp.controller;

import com.mycompany.myapp.dto.Board;
import com.mycompany.myapp.dto.Member;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/jsp")
public class Exam07JspController {

	@RequestMapping("/exam01")
	public String exam01() {
		return "jsp/exam01";
	}

	@RequestMapping("/exam02")
	public String exam02() {
		return "jsp/exam02";
	}

	@RequestMapping("/exam03")
	public String exam03() {
		return "jsp/exam03";
	}

	@RequestMapping("/exam04")
	public String exam04(Model model) {                //(HttpServletRequest request) 도 가능하다  
		model.addAttribute("name2", "홍길동");                      // 모델 매개변수를 선언해놓고 애드어트리뷰트 해서 나중에 값을 얻기 위해서 해당 키에 저장을 해두면 jsp에서는 ${}를 사용해서 값을 불러 올수 있다.
		model.addAttribute("member2", new Member("홍길동", 30)); //모델에 소스를 뜯으면 애드어트리뷰트에 request가 들어가 있을듯
		return "jsp/exam04";
	}

	@RequestMapping("/exam05")
	public String exam05(Model model) {
		model.addAttribute("name3", "홍길동");
		model.addAttribute("member3", new Member("홍길동", 30));
		

		Board board = new Board();
		board.setBno(1);
		board.setBtitle("오늘은 휴가 전날");
		board.setBcontent("ㅋㅋㅋ");
		board.setBwriter("감자바");
		board.setBdate(new Date());
		model.addAttribute("board", board);
		
		List<Board> list = new ArrayList<Board>();
		for(int i=1;i<=10;i++){
			Board b = new Board();
		b.setBno(i);
		b.setBtitle("제목"+i);
		b.setBcontent("내용입니다.장비가 와야할텐데...."+i);
		b.setBwriter("글쓴이"+i);
		b.setBdate(new Date());
		list.add(b);
		}
		model.addAttribute("list",list);   //"list"는 키인데 나중에 jsp에서 ${list}로 불리는 이름이 된다.
		return "jsp/exam05";
	}

}
