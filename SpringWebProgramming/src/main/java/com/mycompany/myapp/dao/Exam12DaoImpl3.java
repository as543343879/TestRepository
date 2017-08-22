package com.mycompany.myapp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.mycompany.myapp.dto.Exam12Board;
import com.mycompany.myapp.dto.Exam12Member;

@Component
public class Exam12DaoImpl3 implements Exam12Dao {
	private static final Logger LOGGER = LoggerFactory.getLogger(Exam12DaoImpl3.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	@Override
	public int boardInsert1(Exam12Board board) { // 매개변수가 넘어온 시점에서는 bno가 입력이 안되지만 아래 insert문을 불른후는 bno가 삽입되어있음
		sqlSessionTemplate.insert("board.insert", board); //(네임스페이스.insert Id , xml의 parameter type의 객체와 일치하는 해당객체)// 리턴값 삽입된 행의수
		
		return board.getBno();
	}

	@Override
	public List<Exam12Board> boardSelectAll() {
		List<Exam12Board> list=sqlSessionTemplate.selectList("board.selectAll");
		return list;
	}

	@Override
	public List<Exam12Board> boardSelectPage(int pageNo, int rowsPerPage) {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("startNum",(pageNo - 1) * rowsPerPage + 1 );
		map.put("endNum", pageNo * rowsPerPage);
		List<Exam12Board> list = sqlSessionTemplate.selectList("board.selectBypage",map);

		return list;
	}

	@Override
	public int boardCountAll() {
		int count = sqlSessionTemplate.selectOne("board.countAll");
		return count;
	}

	@Override
	public Exam12Board boardSelectByBno(int bno) {
	Exam12Board board=sqlSessionTemplate.selectOne("board.selectByBno",bno);

		return board;
	}

	@Override
	public void boardUpdateBhitcount(int bno, int bhitcount) {
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("bhitcount",bhitcount );
		map.put("bno",bno);
		sqlSessionTemplate.update("board.updateBhitcount",map);
	}

	@Override
	public void boardUpdate(Exam12Board board) {
		sqlSessionTemplate.update("board.update",board);

	}

	@Override
	public void boardDelete(int bno) {
		sqlSessionTemplate.delete("board.delete",bno);

	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public String memberInsert2(Exam12Member member) {

		sqlSessionTemplate.insert("member.insert", member);

		return member.getMid();
	}

	@Override
	public List<Exam12Member> MemberSelectPage(int pageNo, int rowsPerPage) {

		Map<String,Object> map=new HashMap<String,Object>();
		map.put("startNum",(pageNo - 1) * rowsPerPage + 1 );
		map.put("endNum", pageNo * rowsPerPage);
		List<Exam12Member> list = sqlSessionTemplate.selectList("member.memberSelectPage",map);
	
	return list;
	}

	@Override
	public int memberCountAll() {
		
			int count = sqlSessionTemplate.selectOne("member.memberCountAll");
			return count;
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@Override
	public Exam12Member MemberSelectByBno(String mid) {
			
			Exam12Member member = sqlSessionTemplate.selectOne("member.memberSelectByBno",mid);
			
		return member;
	}

	@Override
	public void memberUpdate(Exam12Member member) {
		
		sqlSessionTemplate.update("member.memberUpdate",member);
	}

	@Override
	public void memberDelete(String mid) {
	
		sqlSessionTemplate.delete("member.memberDelete",mid);
	}

	@Override
	public void memberImgDelete(String mid) {
		/*
		Connection conn = null;
		try {
			// JDBC Driver 클래스 로딩
			Class.forName("oracle.jdbc.OracleDriver");
			// 연결 문자열 작성
			String connectionString = "jdbc:oracle:thin:@localhost:1521:orcl";
			// 연결 객체 얻기
			conn = DriverManager.getConnection(connectionString, "iotuser", "iot12345");
			// SQL문 작성
			String sql = "update member set moriginalfilename='' ,msavedfilename='', mfilecontent='' where mid=?";

			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mid);

			pstmt.executeUpdate();
			pstmt.close();
			LOGGER.info("행추가성공");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
			}
		}
*/
		sqlSessionTemplate.update("member.memberImgDelete",mid);
	}
	
	public static void main(String[] args) {
		Exam12DaoImpl3 test = new Exam12DaoImpl3();
	//	List<Exam12Board> list = test.boardSelectPage(2, 20);
		//for (Exam12Board board : list) {
		//	LOGGER.info(board.getBtitle());
/*
			 for (int i = 1; i <= 100; i++) {
			Exam12Member member = new Exam12Member();
			member.setMid("ID"+i);
			member.setMname("김철민");
			member.setMpassword("i");
			member.setMtel(String.valueOf(i));
			member.setMemail("kcm@naver.com");
			member.setMage(i);
			member.setMaddress("서울시");
			member.setMoriginalfilename("a.png");
			member.setMsavedfilename("a.png");
			member.setMfilecontent("a.png");
			String bno = test.memberInsert2(member);
			LOGGER.info(bno);
			
			
		}
		*/	 
			 for (int i = 1; i <= 100; i++) {
			Exam12Board board = new Exam12Board();
			board.setBtitle("제목"+i);
			board.setBcontent("내용"+i);
			board.setBwriter("글쓴이"+i);
			board.setBpassword("i");
			board.setBoriginalfilename(i+".png");
			board.setBsavedfilename(i+".png");
			board.setBfilecontent("a.png");
			
			int bno = test.boardInsert1(board);
			LOGGER.info(String.valueOf(bno));
			
			
		}

	}

}
