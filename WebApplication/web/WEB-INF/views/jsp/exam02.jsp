<%-- 주석이다. 주석이야.여기는 주석이야.주석...주석....주....석.....JOO.....SEOCK......주르석

[지시자]

1. 작성: <%@ ......... %>
2. 종류
<%@page ......... %>         : jsp가 무엇을 만들어 내느냐 ? 
<%@include ......... %>     : 외부 파일을 가져와서 포함시킨다.
<%@taglib ......... %>      :



--%>
<%@page contentType="text/html;charset=UTF-8"%>  <%-- 이 jsp가 실행하고 만들어내는것은 문자, 페이지인코딩은 jsp가 파일로 저장될때 어떤 문자셋으로 저장될것이냐이고 carset은 네트워크로 보낼때 사용한 인코딩방식을 이야기함 --%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8"> <%-- HTTP에 들어가는것은 기본적으로 UTF-8로 하겠다는 선언--%>
		<title>JSP Page</title>
		<meta name="viewport" content="width=device-width, initial-scale=1,user-scalable=no">
		<link href="/WebApplication/resources/bootstrap-3.3.7/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
		<script src="/WebApplication/resources/jquery/jquery-3.2.1.min.js" type="text/javascript"></script>
		<script src="/WebApplication/resources/bootstrap-3.3.7/js/bootstrap.min.js" type="text/javascript"></script>
	</head>
	<body>
		<h4>외부파일을 가져와서 포함시키기</h4>
		<%@include file="exam02_include.jsp" %>

		
	</body>
</html>
