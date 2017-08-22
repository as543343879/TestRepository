package com.mycompany.myapp.dao;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;

import com.mycompany.myapp.ApplicationContextLoader;
import com.mycompany.myapp.dto.Exam12Board;

public class Exam12DaoImpl3Test extends ApplicationContextLoader {
	// 내가만들어놓은 Applicationcontextloader를 상속받으면서 어노테이션 상속 가능
	// TEST 클래스가 많아질경우 매번 APPlicationcontestloader에 설정해놓은 선언이 매번 필요하고
	// 중복코드가 발생할 수 있기 때문에 미리 선언해놓은 클래스를 만들어 놓고 상속을 받아서 편리하게 사용

	@Resource(name = "exam12DaoImpl3")
	private Exam12Dao dao;

	@Test // 테스트 메소드로 만들어주는 코드
	public void insertTest() {
		// 테스트 코드 작성
		// Assert.메소드 로 검사해본다.

	//	Assert.assertNotNull(dao); // notnull이면 성공이고 null이면 에러로 나올것임
		// Assert.assertNull(dao);
	//------------------------------------------------------------------------------------	
		Exam12Board board=new Exam12Board();
		board.setBtitle("제목");
		board.setBcontent("내용");
		board.setBwriter("글쓴이");
		board.setBpassword("i");
		int bno=dao.boardInsert1(board);
		
		Exam12Board dbBoard=dao.boardSelectByBno(bno);
		//select잘되는지 확인하여 테스트
		Assert.assertNotNull(dbBoard);
		
		//상세하게 하나씩 확인하여 테스트
		Assert.assertEquals(board.getBtitle(), dbBoard.getBtitle());
		Assert.assertEquals(board.getBcontent(), dbBoard.getBcontent());
		Assert.assertEquals(board.getBwriter(),dbBoard.getBwriter());
		Assert.assertEquals(board.getBpassword(),dbBoard.getBpassword());
		
	}
	@Test // 테스트 메소드로 만들어주는 코드
	public void insertWithAttachTest() {
		
		Exam12Board board=new Exam12Board();
		board.setBtitle("제목");
		board.setBcontent("내용");
		board.setBwriter("글쓴이");
		board.setBpassword("i");
		board.setBoriginalfilename("test.png");
		board.setBsavedfilename("test.png");
		board.setBfilecontent("image/jpeg");
		int bno=dao.boardInsert1(board);
		
		Exam12Board dbBoard=dao.boardSelectByBno(bno);
		//select잘되는지 확인하여 테스트
		Assert.assertNotNull(dbBoard);
		
		//상세하게 하나씩 확인하여 테스트
		Assert.assertEquals(board.getBtitle(), dbBoard.getBtitle());
		Assert.assertEquals(board.getBcontent(), dbBoard.getBcontent());
		Assert.assertEquals(board.getBwriter(),dbBoard.getBwriter());
		Assert.assertEquals(board.getBpassword(),dbBoard.getBpassword());
		Assert.assertEquals(board.getBoriginalfilename(),dbBoard.getBoriginalfilename() );
		Assert.assertEquals(board.getBsavedfilename(),dbBoard.getBsavedfilename() );
		Assert.assertEquals(board.getBfilecontent(),dbBoard.getBfilecontent() );
		
	}

	@Test
	public void updateTest() {
		Exam12Board board=new Exam12Board();
		board.setBtitle("제목");
		board.setBcontent("내용");
		board.setBwriter("글쓴이");
		board.setBpassword("i");
		board.setBoriginalfilename("test.png");
		board.setBsavedfilename("test.png");
		board.setBfilecontent("image/jpeg");
		int bno=dao.boardInsert1(board);
		
		board.setBtitle("제목2");
		board.setBcontent("내용2");
		dao.boardUpdate(board);
		
		Exam12Board dbBoard=dao.boardSelectByBno(bno);
		//select잘되는지 확인하여 테스트
		Assert.assertNotNull(dbBoard);
		
		//상세하게 하나씩 확인하여 테스트
		Assert.assertEquals(board.getBtitle(), dbBoard.getBtitle());
		Assert.assertEquals(board.getBcontent(), dbBoard.getBcontent());
		Assert.assertEquals(board.getBwriter(),dbBoard.getBwriter());

	}
	
	@Test
	public void deleteTest() {
		Exam12Board board=new Exam12Board();
		board.setBtitle("제목");
		board.setBcontent("내용");
		board.setBwriter("글쓴이");
		board.setBpassword("i");
		board.setBoriginalfilename("test.png");
		board.setBsavedfilename("test.png");
		board.setBfilecontent("image/jpeg");
		int bno=dao.boardInsert1(board);
		
		Exam12Board dbBoard=dao.boardSelectByBno(bno);
		Assert.assertNotNull(dbBoard);
		
		dao.boardDelete(bno);
		
		dbBoard=dao.boardSelectByBno(bno);
		Assert.assertNull(dbBoard);
		
		

	}
	


}
