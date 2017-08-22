package com.mycompany.myapp.service;

import java.util.List;

import com.mycompany.myapp.dto.Exam12Board;
import com.mycompany.myapp.dto.Exam12Member;

public interface Exam12Service {
	public void boardWrite(Exam12Board board);
	public List<Exam12Board> BoardListAll();
	public void memberJoin(Exam12Member member);
	
	
	public List<Exam12Board> boardListPage(int pageNo,int rowsPerPage);
	public int boardTotalRows();
	
	public List<Exam12Member> MemberListPage(int pageNo,int rowsPerPage);
	public int memberTotalRows();
	
	///////////////////////////////
	public Exam12Board getBoard(int bno);
	public String boardCheckBpassword(int bno,String bpassword);
	public void boardUpdate(Exam12Board board);
	public void boardDelete(int bno);
	//////////////////////////////////
	public Exam12Member getBoardMember(String mid);
	public String MemberCheckMpassword(String mid,String mpassword);
	public void MemberUpdate(Exam12Member member);
public void MemberDelete(String mid);
/////////////////////////////////
public void MemberImgDelete(String mid);
}
