package com.mycompany.myapp.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.mycompany.myapp.dao.Exam12Dao;
import com.mycompany.myapp.dto.Exam12Board;
import com.mycompany.myapp.dto.Exam12Member;

@Component
public class Exam12ServiceImpl3 implements Exam12Service{

	@Resource(name="exam12DaoImpl3")    //Autowired는 타입을 보고 대입하기 때문에 resource를 사용하여 이름으로 지정해서 대입한다. 이름은 클래스명의 앞자리를 소문자로 바꾼것
	private Exam12Dao dao;
	
	@Override
	public void boardWrite(Exam12Board board) {
		dao.boardInsert1(board);
	}

	@Override
	public List<Exam12Board> BoardListAll() {
		List<Exam12Board> list = dao.boardSelectAll();
		return list;
	}

	@Override
	public List<Exam12Board> boardListPage(int pageNo, int rowsPerPage) {
		List<Exam12Board> list = dao.boardSelectPage(pageNo, rowsPerPage);
		return list;
	}
	///////////////////////////////
	@Override
	public Exam12Board getBoard(int bno) {
		Exam12Board board=dao.boardSelectByBno(bno);
		board.setBhitcount(board.getBhitcount()+1);
		dao.boardUpdateBhitcount(bno,board.getBhitcount());
		return board;
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	@Override
	public void memberJoin(Exam12Member member) {
		dao.memberInsert2(member);
	}

	@Override
	public int boardTotalRows() {
		int totalRows = dao.boardCountAll();
		return totalRows;
	}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public List<Exam12Member> MemberListPage(int pageNo, int rowsPerPage) {
		List<Exam12Member> list = dao.MemberSelectPage(pageNo, rowsPerPage);
		return list;
	}

	@Override
	public int memberTotalRows() {
		int totalRows = dao.memberCountAll();
		return totalRows;
	}

	@Override
	public String boardCheckBpassword(int bno, String bpassword) {
		String result="fail";
		Exam12Board board=dao.boardSelectByBno(bno);
		if(board.getBpassword().equals(bpassword)){
			result="success";
		}
		
		return result;
	}

	@Override
	public void boardUpdate(Exam12Board board) {
		dao.boardUpdate(board);
		
	}

	@Override
	public void boardDelete(int bno) {
		dao.boardDelete(bno);
		
	}

	@Override
	public Exam12Member getBoardMember(String mid) {
		Exam12Member member=dao.MemberSelectByBno(mid);
		
		return member;
	}

	@Override
	public String MemberCheckMpassword(String mid, String mpassword) {
		String result="fail";
		Exam12Member member=dao.MemberSelectByBno(mid);
		if(member.getMpassword().equals(mpassword)){
			result="success";
	}
return result;
}

	@Override
	public void MemberUpdate(Exam12Member member) {
		dao.memberUpdate(member);
	}

	@Override
	public void MemberDelete(String mid) {
		dao.memberDelete(mid);
		
	}

	@Override
	public void MemberImgDelete(String mid) {
		dao.memberImgDelete(mid);
		
	}
}
