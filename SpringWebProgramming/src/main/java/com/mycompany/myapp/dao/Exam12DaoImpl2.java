package com.mycompany.myapp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import com.mycompany.myapp.dto.Exam12Board;
import com.mycompany.myapp.dto.Exam12Member;

@Component
public class Exam12DaoImpl2 implements Exam12Dao {
	private static final Logger LOGGER = LoggerFactory.getLogger(Exam12DaoImpl2.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public int boardInsert1(Exam12Board board) {

		final String sql = "insert into board"
				+ "(bno,btitle,bcontent,bwriter,bdate,bpassword,bhitcount,boriginalfilename,bsavedfilename,bfilecontent)"
				+ "values " + "(board_bno_seq.nextval,?,?,?,sysdate,?,0,?,?,?) ";
		/*
		 * jdbcTemplate.update(sql, board.getBtitle(), board.getBcontent(),
		 * board.getBwriter(), board.getBpassword(),
		 * board.getBoriginalfilename(), board.getBsavedfilename(),
		 * board.getBfilecontent());
		 */
		PreparedStatementCreator psc = new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
				PreparedStatement pstmt = conn.prepareStatement(sql, new String[] { "bno" });// oracle처럼
																								// 시퀀스를사용할때사용하는방법,내가가지고올값을지정
				pstmt.setString(1, board.getBtitle()); // 몇번째 물음표인가, 넣어줄값
				pstmt.setString(2, board.getBcontent());
				pstmt.setString(3, board.getBwriter());
				pstmt.setString(4, board.getBpassword());
				pstmt.setString(5, board.getBoriginalfilename());
				pstmt.setString(6, board.getBsavedfilename());
				pstmt.setString(7, board.getBfilecontent());
				return pstmt;
			}

		};

		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(psc, keyHolder); // bno값을 keyHolder에 저장
		int bno = keyHolder.getKey().intValue(); // 키를 얻어서 키타입이 정수타입이니까 인트밸류로 변환
		LOGGER.info(String.valueOf(bno));
		return bno;
	}

	@Override
	public List<Exam12Board> boardSelectAll() {

		// SQL문 작성
		String sql = "select bno,btitle,bwriter,bdate,bhitcount from board order by bno desc";

		// RowMapper란?
		// 하나의 행을 어떤 객체로 표현할 것인가.
		// 메소드에서는 어떤 놈을 어떤 필드인지 매핑 시켜주는 일을함
		RowMapper<Exam12Board> rowMapper = new RowMapper<Exam12Board>() {

			@Override
			public Exam12Board mapRow(ResultSet rs, int rowNum) throws SQLException { // 가져온
																						// 행수만큼
																						// rowmapper가
																						// 실행되서
																						// 한행씩
																						// 매핑
																						// 시킨다.
				Exam12Board board = new Exam12Board();
				board.setBno(rs.getInt("bno"));
				board.setBtitle(rs.getString("btitle"));
				board.setBwriter(rs.getString("bwriter"));
				board.setBdate(rs.getDate("bdate"));
				board.setBhitcount(rs.getInt("bhitcount"));
				return board;
			}
		};
		List<Exam12Board> list = jdbcTemplate.query(sql, rowMapper); // 리턴타입이
																		// 컬렉션이다.

		return list;
	}

	@Override
	public List<Exam12Board> boardSelectPage(int pageNo, int rowsPerPage) {
		// SQL문이 매개변수를 가질경우
		String sql = "select * ";
		sql += "from( ";
		sql += "select rownum as r,bno,btitle,bwriter,bdate,bhitcount ";
		sql += "from (select bno,btitle,bwriter,bdate,bhitcount from board order by bno desc) ";
		sql += "where rownum<=? ";
		sql += ") ";
		sql += "where r>=?";

		Object[] args = { (pageNo * rowsPerPage), ((pageNo - 1) * rowsPerPage + 1) };
		RowMapper<Exam12Board> rowMapper = new RowMapper<Exam12Board>() {

			@Override
			public Exam12Board mapRow(ResultSet rs, int rowNum) throws SQLException { // 가져온
																						// 행수만큼
																						// rowmapper가
																						// 실행되서
																						// 한행씩
																						// 매핑
																						// 시킨다.
				Exam12Board board = new Exam12Board();
				board.setBno(rs.getInt("bno"));
				board.setBtitle(rs.getString("btitle"));
				board.setBwriter(rs.getString("bwriter"));
				board.setBdate(rs.getDate("bdate"));
				board.setBhitcount(rs.getInt("bhitcount"));
				return board;
			}
		};
		List<Exam12Board> list = jdbcTemplate.query(sql, args, rowMapper);

		return list;
	}

	@Override
	public int boardCountAll() {

		// SQL문 작성
		String sql = "select count(*) from board";

		int count = jdbcTemplate.queryForObject(sql, Integer.class);

		return count;
	}

	@Override
	public Exam12Board boardSelectByBno(int bno) {

		// SQL문 작성
		String sql = "select * from board where bno=?";
		RowMapper<Exam12Board> rowMapper = new RowMapper<Exam12Board>() {

			@Override
			public Exam12Board mapRow(ResultSet rs, int rowNum) throws SQLException { // 가져온
																						// 행수만큼
																						// rowmapper가
																						// 실행되서
																						// 한행씩
																						// 매핑
																						// 시킨다.
				Exam12Board board = new Exam12Board();
				board.setBno(rs.getInt("bno"));
				board.setBtitle(rs.getString("btitle"));
				board.setBwriter(rs.getString("bwriter"));
				board.setBcontent(rs.getString("bcontent"));
				board.setBpassword(rs.getString("bpassword"));
				board.setBoriginalfilename(rs.getString("boriginalfilename"));
				board.setBsavedfilename(rs.getString("bsavedfilename"));
				board.setBfilecontent(rs.getString("bfilecontent"));
				board.setBdate(rs.getDate("bdate"));
				board.setBhitcount(rs.getInt("bhitcount"));
				return board;
			}
		};
		Exam12Board board = jdbcTemplate.queryForObject(sql, rowMapper, bno);

		return board;
	}

	@Override
	public void boardUpdateBhitcount(int bno, int bhitcount) {

		String sql = "update board set bhitcount=? where bno=?";
		jdbcTemplate.update(sql, bhitcount, bno); // 리턴값은 int형의 업데이트된행의수

	}

	@Override
	public void boardUpdate(Exam12Board board) {

		// SQL문 작성
		String sql;
		if (board.getBoriginalfilename() != null) {
			sql = "update board set btitle=?,bcontent=?,bpassword=?,boriginalfilename=?,bdate=sysdate,bsavedfilename=?,bfilecontent=? where bno=?";
			jdbcTemplate.update(sql, board.getBtitle(), board.getBcontent(), board.getBpassword(),
					board.getBoriginalfilename(), board.getBsavedfilename(), board.getBfilecontent(), board.getBno());
		} else {
			sql = "update board set btitle=?,bcontent=?,bpassword=?,bdate=sysdate where bno=?";
			jdbcTemplate.update(sql, board.getBtitle(), board.getBcontent(), board.getBpassword(), board.getBno());
		}

	}

	@Override
	public void boardDelete(int bno) {

		// SQL문 작성
		String sql = "delete from board where bno=?";
		jdbcTemplate.update(sql, bno);

	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public String memberInsert2(Exam12Member member) {

		String sql = "insert into Member ";
		sql += "(mid,mname,mpassword,mdate,mtel,memail,mage,maddress,moriginalfilename,msavedfilename,mfilecontent) ";
		sql += "values ";
		sql += "(?,?,?,sysdate,?,?,?,?,?,?,?) ";

		jdbcTemplate.update(sql, member.getMid(), member.getMname(), member.getMpassword(), member.getMtel(),
				member.getMemail(), member.getMage(), member.getMaddress(), member.getMoriginalfilename(),
				member.getMsavedfilename(), member.getMfilecontent());

		return member.getMid();
	}

	@Override
	public List<Exam12Member> MemberSelectPage(int pageNo, int rowsPerPage) {

		String sql = "select * ";
		sql += "from( ";
		sql += "select rownum as r,mid,mname,mpassword,mdate,mtel,memail,mage,maddress,msavedfilename ";
		sql += "from (select mid,mname,mpassword,mdate,mtel,memail,mage,maddress,msavedfilename from member order by mtel desc) ";
		sql += "where rownum<=?";
		sql += ") ";
		sql += "where r>=? ";
		
		Object[] args={(pageNo * rowsPerPage),((pageNo - 1) * rowsPerPage + 1)};

		RowMapper<Exam12Member> rowMapper = new RowMapper<Exam12Member>() {

			@Override
			public Exam12Member mapRow(ResultSet rs, int rowNum) throws SQLException {
				Exam12Member member = new Exam12Member();
				member.setMid(rs.getString("mid"));
				member.setMname(rs.getString("mname"));
				member.setMpassword(rs.getString("mpassword"));
				member.setMtel(String.valueOf(rs.getString("mtel")));
				member.setMemail(rs.getString("memail"));
				member.setMdate(rs.getDate("mdate"));
				member.setMage(rs.getInt("mage"));
				member.setMaddress(rs.getString("maddress"));
				member.setMsavedfilename(rs.getString("msavedfilename"));

				return member;
			}
		};
		
		List<Exam12Member> list = jdbcTemplate.query(sql, args, rowMapper);

		
	return list;
	}

	@Override
	public int memberCountAll() {
		
			String sql = "select count(*) from member";
			int count = jdbcTemplate.queryForObject(sql, Integer.class);

			return count;
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@Override
	public Exam12Member MemberSelectByBno(String mid) {
		String sql = "select * from member where mid=?";
			
			
			RowMapper<Exam12Member> rowMapper = new RowMapper<Exam12Member>() {

				@Override
				public Exam12Member mapRow(ResultSet rs, int rowNum) throws SQLException {
					Exam12Member member = new Exam12Member();
					member.setMid(rs.getString("mid"));
					member.setMname(rs.getString("mname"));
					member.setMpassword(rs.getString("mpassword"));
					member.setMdate(rs.getDate("mdate"));
					member.setMtel(rs.getString("mtel"));
					member.setMemail(rs.getString("memail"));
					member.setMage(rs.getInt("mage"));
					member.setMaddress(rs.getString("maddress"));
					member.setMoriginalfilename(rs.getString("moriginalfilename"));
					member.setMsavedfilename(rs.getString("msavedfilename"));
					member.setMfilecontent(rs.getString("mfilecontent"));
					return member;
				}
			};
			
			Exam12Member member = jdbcTemplate.queryForObject(sql, rowMapper,mid);
			

		return member;
	}

	@Override
	public void memberUpdate(Exam12Member member) {

			// SQL문 작성
			String sql;
			if (member.getMoriginalfilename() != null) {
				sql = "update member set mpassword=?,mdate=sysdate,mtel=?,memail=?,mage=?,maddress=?,moriginalfilename=?,msavedfilename=?,mfilecontent=? where mid=?";
				jdbcTemplate.update(sql,member.getMpassword(),member.getMtel(),member.getMemail(),member.getMage(),member.getMaddress(),member.getMoriginalfilename(),member.getMsavedfilename(),member.getMfilecontent(),member.getMid());
			} else {
				sql = "update member set mpassword=?,mdate=sysdate,mtel=?,memail=?,mage=?,maddress=? where mid=?";
				jdbcTemplate.update(sql,member.getMpassword(),member.getMtel(),member.getMemail(),member.getMage(),member.getMaddress(),member.getMid());
			}

	}

	@Override
	public void memberDelete(String mid) {
	
			String sql = "delete from member where mid=?";
			jdbcTemplate.update(sql,mid);

	}

	@Override
	public void memberImgDelete(String mid) {
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

	}
	
	public static void main(String[] args) {
		Exam12DaoImpl2 test = new Exam12DaoImpl2();
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
