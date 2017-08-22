package com.mycompany.myapp.dao;

import java.util.List;

import com.mycompany.myapp.dto.Exam12Board;
import com.mycompany.myapp.dto.Exam12Member;

public interface Exam12Dao {
	public int boardInsert1(Exam12Board board);
	
	public String memberInsert2(Exam12Member member);
	
	public List<Exam12Board> boardSelectAll();
	public List<Exam12Board> boardSelectPage(int pageNo, int rowsPerPage);
	
	public int boardCountAll();
	///////////////////////////////
	public Exam12Board boardSelectByBno(int bno);
	public void boardUpdateBhitcount(int bno,int bhitcount);
	/////////////////////
	public List<Exam12Member> MemberSelectPage(int pageNo,int rowsPerPage);
public int memberCountAll();
public void boardUpdate(Exam12Board board);
public void boardDelete(int bno);

////////////////////////////////

 public Exam12Member MemberSelectByBno(String mid);
 public void memberUpdate(Exam12Member member);
 public void memberDelete(String mid);
 ///////////////////
 public void memberImgDelete(String mid);
}
