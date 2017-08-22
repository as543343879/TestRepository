<%@page contentType="text/html" pageEncoding="UTF-8"%><!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1,user-scalable=no">
<link
	href="<%=application.getContextPath()%>/resources/bootstrap-3.3.7/css/bootstrap.min.css"
	rel="stylesheet" type="text/css" />
<script
	src="<%=application.getContextPath()%>/resources/jquery/jquery-3.2.1.min.js"
	type="text/javascript"></script>
<script
	src="<%=application.getContextPath()%>/resources/bootstrap-3.3.7/js/bootstrap.min.js"
	type="text/javascript"></script>
</head>

<body>
	WebApplication Home
	<hr />
	<h4>html태그</h4>
	<a href="html/exam01" class="btn btn-warning">exam01</a>
	<h4>CSS</h4>
	<a href="css/exam01" class="btn btn-success">exam01</a>
	<br />
	<a href="css/exam02" class="btn btn-success">exam02</a>
	<br />
	<a href="css/exam03" class="btn btn-success">exam03</a>
	<br />

	<h4>CSS</h4>
	<%
		for (int i = 1; i <= 11; i++) {
			String exam = "exam";
			if (i < 10) {
				exam += "0" + i;
			} else {
				exam += String.valueOf(i);
			}
	%>

	<a href="javascript/<%=exam%>" class="btn btn-primary"><%=exam%></a>
	<%
		}
	%>

	<h4>Jquery</h4>
	<%
		for (int i = 1; i <= 4; i++) {
			String exam = "exam";

			exam += "0" + String.valueOf(i);
	%>
	<a href="javascript/<%=exam%>" class="btn btn-primary"><%=exam%></a>

	<%
		}
	%>

	<h4>bootstrap</h4>
	<%
		for (int i = 1; i <= 3; i++) {
			String exam = "exam";

			exam += "0" + String.valueOf(i);
	%>
	<a href="bootstrap/<%=exam%>" class="btn btn-primary"><%=exam%></a>

	<%
		}
	%>

	<h4>jsp</h4>
	<%
		for (int i = 1; i <= 5; i++) {
			String exam = "exam";

			exam += "0" + String.valueOf(i);
	%>
	<a href="jsp/<%=exam%>" class="btn btn-primary"><%=exam%></a>

	<%
		}
	%>

	<h4>HTTP요청방식</h4>
	GET 방식:
	<a href="http/exam01" class="btn btn-primary">exam01</a>
	<br> POST 방식:
	<form method="post" action="http/exam01" style="display: inline">
		<input type="submit" value="exam01" class="btn btn-primary" />
		<%-- exam01버튼을 누르게 되면 --%>
	</form>

	<h4>HTTP요청방식</h4>
	<a href="http/exam02?type=freeboard&bno=5&hobby=baseball&hobby=soccer"
		class="btn btn-primary">exam02</a>
	<a href="http/exam03?type=freeboard&bno=5&hobby=baseball&hobby=soccer"
		class="btn btn-primary">exam03</a>

	<h4>폼제출</h4>
	<a href="form/exam01" class="btn btn-primary">회원가입양식요청</a>
	<h4>파일업로드</h4>
	<a href="form/exam02" class="btn btn-primary">흐익</a>

	<h4>파일다운로드</h4>
	<a href="file/exam03" class="btn btn-primary">파일로 저장</a>
	<img src="file/exam03" width="100px" height="100px" />


	<h4>의존성주입</h4>
	<a href="di/exam01" class="btn btn-primary">회원가입</a>

	<a href="di/exam02" class="btn btn-primary">로그인</a>

	<h4>리다이렉트(재요청)</h4>
	<a href="redirect/write" class="btn btn-primary">게시물 쓰기</a>

	<a href="redirect/list" class="btn btn-primary">게시물 목록</a>

	<h4>상태유지(세션)</h4>

	<a href="cookie/exam01" class="btn btn-primary">클라이언트 쿠키 저장</a>
	<a href="cookie/exam02" class="btn btn-primary">클라이언트 쿠키 읽기</a>
	<a href="cookie/exam03" class="btn btn-primary">클라이언트 쿠키 제거</a>
	<a href="session/exam04" class="btn btn-primary">서버 세션에 저장</a>
	<a href="session/exam05" class="btn btn-primary">서버 세션에서 읽기</a>
	<a href="session/exam06" class="btn btn-primary">서버 세션에서 제거</a>

	<h4>JDBC</h4>
	<a href="jdbc/exam01" class="btn btn-primary">게시물 쓰기</a>
	<a href="jdbc/exam02" class="btn btn-primary">게시물 쓰기</a>
	<a href="jdbc/exam03" class="btn btn-primary">회원 가입</a>
	<a href="jdbc/exam04" class="btn btn-primary">게시물 목록</a>
	<a href="jdbc/exam05" class="btn btn-primary">게시물 페이지 목록</a>
	<a href="jdbc/exam06" class="btn btn-primary">회원 목록</a>
	
	<h4>AOP</h4>
	<a href="jdbc/exam05" class="btn btn-primary">실행시간 체크</a>
	<a href="aop/exam01" class="btn btn-primary">로그인하기</a>
	<a href="aop/exam02Write" class="btn btn-primary">로그인 해야 볼 수 있는 내용</a>
	<a href="aop/exam02Update" class="btn btn-primary">로그인 해야 볼 수 있는 내용</a>
	
	
	<h4>트랜잭션</h4>
	<a href="transaction/exam01" class="btn btn-primary">트랜잭션 테스트</a>
	
	<h4>WebSocket</h4>
	<a href="websocket/echoClient" class="btn btn-primary">에코 클라이언트</a>
	<a href="websocket/measureClient" class="btn btn-primary">측정값 얻기</a>
	<a href="websocket/chatClient" class="btn btn-primary">채팅 클라이언트</a>
</body>
</html>
