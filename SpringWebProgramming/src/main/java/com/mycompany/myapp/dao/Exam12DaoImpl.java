package com.mycompany.myapp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mycompany.myapp.dto.Exam12Board;
import com.mycompany.myapp.dto.Exam12Member;

@Component
public class Exam12DaoImpl implements Exam12Dao {
	private static final Logger LOGGER = LoggerFactory.getLogger(Exam12DaoImpl.class);

	@Autowired
	private DataSource dataSource; // 커넥션 풀을 이용하겠다.
	//단순히 서버를 이용하지 않고 사용할때는 되지 않음
	
	@Override
	public int boardInsert1(Exam12Board board) {
		int bno = -1;
		Connection conn=null;
		try {
			conn=dataSource.getConnection();
			LOGGER.info("연결성공");
			// SQL문 작성
			String sql = "insert into board ";
			sql += "(bno,btitle,bcontent,bwriter,bdate,bpassword,bhitcount,boriginalfilename,bsavedfilename,bfilecontent) ";
			sql += "values ";
			sql += "(board_bno_seq.nextval,?,?,?,sysdate,?,0,?,?,?) ";
			
			System.out.println("3");

		
			PreparedStatement pstmt = conn.prepareStatement(sql, new String[] { "bno" });// oracle처럼 시퀀스를사용할때사용하는방법,내가가지고올값을지정
			pstmt.setString(1, board.getBtitle()); // 몇번째 물음표인가, 넣어줄값
			pstmt.setString(2, board.getBcontent());
			pstmt.setString(3, board.getBwriter());
			pstmt.setString(4, board.getBpassword());
			pstmt.setString(5, board.getBoriginalfilename());
			pstmt.setString(6, board.getBsavedfilename());
			pstmt.setString(7, board.getBfilecontent());
			// pstmt.executeQuery(); 업데이트 대신 이거써도 됨
			System.out.println("4");

			pstmt.executeUpdate(); // 위에것들을 셋팅하고 업데이트 하는것
			ResultSet rs = pstmt.getGeneratedKeys(); // 결과를 받아와서
			rs.next(); // 첫행을 가리키고 있는 커서를 아래행으로 내려라는 뜻
			bno = rs.getInt(1); // 1번째 컬럼의 정수값을 읽어와라
			pstmt.close();
			LOGGER.info("행추가성공");
			System.out.println("zzs");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				// 연결끊기
				System.out.println("zz");
				conn.close();
				//LOGGER.info("연결끊기성공");

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return bno;
	}

	@Override
	public List<Exam12Board> boardSelectAll() {
		List<Exam12Board> list = new ArrayList<>();

		Connection conn = null;
		try {
			// JDBC Driver 클래스 로딩
			Class.forName("oracle.jdbc.OracleDriver");
			// 연결 문자열 작성
			String connectionString = "jdbc:oracle:thin:@localhost:1521:orcl";
			// 연결 객체 얻기
			conn = DriverManager.getConnection(connectionString, "iotuser", "iot12345");
			LOGGER.info("연결성공");
			// SQL문 작성
			String sql = "select bno,btitle,bwriter,bdate,bhitcount from board order by bno desc";

			// SQL 문을 전송해서 실행
			PreparedStatement pstmt = conn.prepareStatement(sql);// oracle처럼
																	// 시퀀스를 사용할때
																	// 사용하는 방법,
																	// 내가 가지고
																	// 올값을 지정
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Exam12Board board = new Exam12Board();
				board.setBno(rs.getInt("bno"));
				board.setBtitle(rs.getString("btitle"));
				board.setBwriter(rs.getString("bwriter"));
				board.setBdate(rs.getDate("bdate"));
				board.setBhitcount(rs.getInt("bhitcount"));
				list.add(board);
			}
			rs.close();
			pstmt.close();
			LOGGER.info("행추가성공");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				// 연결끊기
				conn.close();
				LOGGER.info("연결끊기성공");

			} catch (SQLException e) {
			}
		}
		return list;
	}

	@Override
	public List<Exam12Board> boardSelectPage(int pageNo, int rowsPerPage) {
		List<Exam12Board> list = new ArrayList<>();
		Connection conn = null;
		try {
			// JDBC Driver 클래스 로딩
			Class.forName("oracle.jdbc.OracleDriver");
			// 연결 문자열 작성
			String connectionString = "jdbc:oracle:thin:@localhost:1521:orcl";
			// 연결 객체 얻기
			conn = DriverManager.getConnection(connectionString, "iotuser", "iot12345");
			LOGGER.info("연결성공");
			// SQL문 작성
			String sql = "select * ";
			sql += "from( ";
			sql += "select rownum as r,bno,btitle,bwriter,bdate,bhitcount ";
			sql += "from (select bno,btitle,bwriter,bdate,bhitcount from board order by bno desc) ";
			sql += "where rownum<=? ";
			sql += ") ";
			sql += "where r>=?";
			/*
			 * select * from( select rownum as
			 * r,bno,btitle,bwriter,bdate,bhitcount from (select
			 * bno,btitle,bwriter,bdate,bhitcount from board order by bno desc)
			 * --where rownum<=(pageNo*rowsPerPage) where rownum<=(2*15) )
			 * --where r>=((pageNo-1)*rowsPerPage+1); where r>=((2-1)*15+1);
			 */
			// SQL 문을 전송해서 실행
			PreparedStatement pstmt = conn.prepareStatement(sql);// oracle처럼
																	// 시퀀스를 사용할때
																	// 사용하는 방법,
																	// 내가 가지고
																	// 올값을 지정
			pstmt.setInt(1, pageNo * rowsPerPage);
			pstmt.setInt(2, (pageNo - 1) * rowsPerPage + 1); // 몇번째 물음표인지
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Exam12Board board = new Exam12Board();
				board.setBno(rs.getInt("bno"));
				board.setBtitle(rs.getString("btitle"));
				board.setBwriter(rs.getString("bwriter"));
				board.setBdate(rs.getDate("bdate"));
				board.setBhitcount(rs.getInt("bhitcount"));
				list.add(board);
			}
			rs.close();
			pstmt.close();
			LOGGER.info("행추가성공");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				// 연결끊기
				conn.close();
				LOGGER.info("연결끊기성공");
			} catch (SQLException e) {
			}
		}
		return list;
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public String memberInsert2(Exam12Member member) {
		int bno = -1;
		Connection conn = null;
		try {
			// JDBC Driver 클래스 로딩
			Class.forName("oracle.jdbc.OracleDriver");
			// 연결 문자열 작성
			String connectionString = "jdbc:oracle:thin:@localhost:1521:orcl";
			// 연결 객체 얻기
			conn = DriverManager.getConnection(connectionString, "iotuser", "iot12345");
			LOGGER.info("연결성공");
			// SQL문 작성
			String sql = "insert into Member ";
			sql += "(mid,mname,mpassword,mdate,mtel,memail,mage,maddress,moriginalfilename,msavedfilename,mfilecontent) ";
			sql += "values ";
			sql += "(?,?,?,sysdate,?,?,?,?,?,?,?) ";

			// SQL 문을 전송해서 실행
			// Statement stmt = conn.createStatement(); // 이것은 통째로 sql을 넣어서
			// 전송해줄때 사용
			// 테이블 정의시 컬럼의 속성으로 자동 증가를 지정할 수 있는 DB일 경우(MySQL,MS SQL)에만 아래의 코드를
			// 사용가능
			// PreparedStatement pstmt =
			// conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			// //sql문을 실행하고 생성된 키를 리턴해다오
			// 오라클일 경우 Sequence 외부 객체로 자동 증가값을 얻기 때문에 다음과 같이 지정
			PreparedStatement pstmt = conn.prepareStatement(sql);// oracle처럼
																	// 시퀀스를 사용할때
																	// 사용하는 방법,
																	// 내가 가지고
																	// 올값을 지정
			pstmt.setString(1, member.getMid()); // 몇번째 물음표인가, 넣어줄값
			pstmt.setString(2, member.getMname());
			pstmt.setString(3, member.getMpassword());
			pstmt.setString(4, member.getMtel());
			pstmt.setString(5, member.getMemail());
			pstmt.setInt(6, member.getMage());
			pstmt.setString(7, member.getMaddress());
			pstmt.setString(8, member.getMoriginalfilename());
			pstmt.setString(9, member.getMsavedfilename());
			pstmt.setString(10, member.getMfilecontent());
			// pstmt.executeQuery(); 업데이트 대신 이거써도 됨

			pstmt.executeUpdate(); // 위에것들을 셋팅하고 업데이트 하는것

			LOGGER.info("행추가성공");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				// 연결끊기
				conn.close();
				LOGGER.info("연결끊기성공");

			} catch (SQLException e) {
			}
		}

		return member.getMid();
	}

	@Override
	public int boardCountAll() {
		int count = 0;
		//List<Exam12Board> list = new ArrayList<>();
		Connection conn = null;
		try {
			// JDBC Driver 클래스 로딩
			Class.forName("oracle.jdbc.OracleDriver");
			// 연결 문자열 작성
			String connectionString = "jdbc:oracle:thin:@localhost:1521:orcl";
			// 연결 객체 얻기
			conn = DriverManager.getConnection(connectionString, "iotuser", "iot12345");
			LOGGER.info("연결성공");
			// SQL문 작성
			String sql = "select count(*) from board";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			count=rs.getInt(1);
			rs.close();
			pstmt.close();
			LOGGER.info("행추가성공");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				// 연결끊기
				conn.close();
				LOGGER.info("연결끊기성공");
			} catch (SQLException e) {
			}
		}
		return count;
	}
	//////////////////////////////////////////////////////////
	
	@Override
	public List<Exam12Member> MemberSelectPage(int pageNo, int rowsPerPage) {
		List<Exam12Member> list = new ArrayList<>();
		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			String connectionString = "jdbc:oracle:thin:@localhost:1521:orcl";
			conn = DriverManager.getConnection(connectionString, "iotuser", "iot12345");
			String sql = "select * ";
			sql+="from( ";
			sql+="select rownum as r,mid,mname,mpassword,mdate,mtel,memail,mage,maddress,msavedfilename ";
			sql+="from (select mid,mname,mpassword,mdate,mtel,memail,mage,maddress,msavedfilename from member order by mtel desc) ";
			sql+="where rownum<=?";
			sql+=") ";
			sql+="where r>=? ";
			
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pageNo * rowsPerPage);
			pstmt.setInt(2, (pageNo - 1) * rowsPerPage + 1); // 몇번째 물음표인지
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
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
				list.add(member);
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				conn.close();
				LOGGER.info("연결끊기성공");
			} catch (SQLException e) {
			}
		}
		
		return list;
	}
	
	@Override
	public int memberCountAll() {
		int count = 0;
		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			String connectionString = "jdbc:oracle:thin:@localhost:1521:orcl";
			conn = DriverManager.getConnection(connectionString, "iotuser", "iot12345");
			String sql = "select count(*) from member";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			count=rs.getInt(1);
			rs.close();
			pstmt.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				LOGGER.info("연결끊기성공");
			} catch (SQLException e) {
			}
		}
		return count;
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public static void main(String[] args) {
		Exam12DaoImpl test = new Exam12DaoImpl();
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
///////////////////////////////////////////////////////////////////////////
	@Override
	public Exam12Board boardSelectByBno(int bno) {
		Exam12Board board=null;
		Connection conn = null;
		try {
			// JDBC Driver 클래스 로딩
			Class.forName("oracle.jdbc.OracleDriver");
			// 연결 문자열 작성
			String connectionString = "jdbc:oracle:thin:@localhost:1521:orcl";
			// 연결 객체 얻기
			conn = DriverManager.getConnection(connectionString, "iotuser", "iot12345");
			LOGGER.info("연결성공");
			// SQL문 작성
			String sql = "select * from board where bno=?";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,bno);
			ResultSet rs = pstmt.executeQuery();
			//bno가 없을수도 있기 때문에 확인해보고 실행하기 위해
			if(rs.next()){
				board=new Exam12Board();
				board.setBno(rs.getInt("bno"));
				board.setBtitle(rs.getString("btitle"));
				board.setBwriter(rs.getString("bwriter"));
				board.setBdate(rs.getDate("bdate"));
				board.setBhitcount(rs.getInt("bhitcount"));
				board.setBcontent(rs.getString("bcontent"));
				board.setBpassword(rs.getString("bpassword"));
				board.setBoriginalfilename(rs.getString("boriginalfilename"));
				board.setBsavedfilename(rs.getString("bsavedfilename"));
				board.setBfilecontent(rs.getString("bfilecontent"));
			}
			rs.close();
			pstmt.close();
			LOGGER.info("행추가성공");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				// 연결끊기
				conn.close();
				LOGGER.info("연결끊기성공");
			} catch (SQLException e) {
			}
		}
		return board;
	}

	@Override
	public void boardUpdateBhitcount(int bno, int bhitcount) {
		
		Connection conn = null;
		try {
			// JDBC Driver 클래스 로딩
			Class.forName("oracle.jdbc.OracleDriver");
			// 연결 문자열 작성
			String connectionString = "jdbc:oracle:thin:@localhost:1521:orcl";
			// 연결 객체 얻기
			conn = DriverManager.getConnection(connectionString, "iotuser", "iot12345");
			LOGGER.info("연결성공");
			// SQL문 작성
			String sql = "update board set bhitcount=? where bno=?";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bhitcount);
			pstmt.setInt(2, bno);
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
				LOGGER.info("연결끊기성공");
			} catch (SQLException e) {
			}
		}
		
	}

	@Override
	public void boardUpdate(Exam12Board board) {
		
		Connection conn = null;
		try {
			// JDBC Driver 클래스 로딩
			Class.forName("oracle.jdbc.OracleDriver");
			// 연결 문자열 작성
			String connectionString = "jdbc:oracle:thin:@localhost:1521:orcl";
			// 연결 객체 얻기
			conn = DriverManager.getConnection(connectionString, "iotuser", "iot12345");
			// SQL문 작성
			String sql;
			if(board.getBoriginalfilename()!=null){
				sql = "update board set btitle=?,bcontent=?,bpassword=?,boriginalfilename=?,bdate=sysdate,bsavedfilename=?,bfilecontent=? where bno=?";
			}else{
				sql = "update board set btitle=?,bcontent=?,bpassword=?,bdate=sysdate where bno=?";
			}
			
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,board.getBtitle());
			pstmt.setString(2, board.getBcontent());
			pstmt.setString(3, board.getBpassword());
			if(board.getBoriginalfilename()!=null){
				pstmt.setString(4, board.getBoriginalfilename());
				pstmt.setString(5, board.getBsavedfilename());
				pstmt.setString(6, board.getBfilecontent());
				pstmt.setInt(7, board.getBno());
			}else{
				pstmt.setInt(4, board.getBno());
			}
			
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

	@Override
	public void boardDelete(int bno) {
		
		Connection conn = null;
		try {
			// JDBC Driver 클래스 로딩
			Class.forName("oracle.jdbc.OracleDriver");
			// 연결 문자열 작성
			String connectionString = "jdbc:oracle:thin:@localhost:1521:orcl";
			// 연결 객체 얻기
			conn = DriverManager.getConnection(connectionString, "iotuser", "iot12345");
			// SQL문 작성
			String sql="delete from board where bno=?";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,bno);
			
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

	@Override
	public Exam12Member MemberSelectByBno(String mid) {
		Exam12Member member=null;
		Connection conn = null;
		try {
			// JDBC Driver 클래스 로딩
			Class.forName("oracle.jdbc.OracleDriver");
			// 연결 문자열 작성
			String connectionString = "jdbc:oracle:thin:@localhost:1521:orcl";
			// 연결 객체 얻기
			conn = DriverManager.getConnection(connectionString, "iotuser", "iot12345");
			LOGGER.info("연결성공");
			// SQL문 작성
			String sql = "select * from member where mid=?";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,mid);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()){
				member=new Exam12Member();
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
			}
			rs.close();
			pstmt.close();
			LOGGER.info("행추가성공");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				LOGGER.info("연결끊기성공");
			} catch (SQLException e) {
			}
		}
		return member;
	}

	@Override
	public void memberUpdate(Exam12Member member) {

		Connection conn = null;
		try {
			// JDBC Driver 클래스 로딩
			Class.forName("oracle.jdbc.OracleDriver");
			// 연결 문자열 작성
			String connectionString = "jdbc:oracle:thin:@localhost:1521:orcl";
			// 연결 객체 얻기
			conn = DriverManager.getConnection(connectionString, "iotuser", "iot12345");
			// SQL문 작성
			String sql;
			if(member.getMoriginalfilename()!=null){
				sql = "update member set mpassword=?,mdate=sysdate,mtel=?,memail=?,mage=?,maddress=?,moriginalfilename=?,msavedfilename=?,mfilecontent=? where mid=?";
			}else{
				sql = "update member set mpassword=?,mdate=sysdate,mtel=?,memail=?,mage=?,maddress=? where mid=?";
			}
			
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, member.getMpassword());
			pstmt.setString(2, member.getMtel());
			pstmt.setString(3, member.getMemail());
			pstmt.setInt(4, member.getMage());
			pstmt.setString(5, member.getMaddress());
			if(member.getMoriginalfilename()!=null){
				pstmt.setString(6, member.getMoriginalfilename());
				pstmt.setString(7, member.getMsavedfilename());
				pstmt.setString(8, member.getMfilecontent());
				pstmt.setString(9, member.getMid());
			}else{
				pstmt.setString(6, member.getMid());
			}
			
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

	@Override
	public void memberDelete(String mid) {
		Connection conn = null;
		try {
			// JDBC Driver 클래스 로딩
			Class.forName("oracle.jdbc.OracleDriver");
			// 연결 문자열 작성
			String connectionString = "jdbc:oracle:thin:@localhost:1521:orcl";
			// 연결 객체 얻기
			conn = DriverManager.getConnection(connectionString, "iotuser", "iot12345");
			// SQL문 작성
			String sql="delete from member where mid=?";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,mid);
			
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
			String sql="update member set moriginalfilename='' ,msavedfilename='', mfilecontent='' where mid=?";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,mid);
			
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



}
