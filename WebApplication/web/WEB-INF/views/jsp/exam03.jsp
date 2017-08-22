<%-- 주석이다. 주석이야.여기는 주석이야.주석...주석....주....석.....JOO.....SEOCK......주르석

[지시자]

1. 작성: <%@ ......... %>
2. 종류
<%@page ......... %>         : jsp가 무엇을 만들어 내느냐 ? 
<%@include ......... %>     : 외부 파일을 가져와서 포함시킨다.
<%@taglib ......... %>      : jsp에서 추가적으로 사용할 수 있는 커스텀 태그 로딩

--%>
<%@page import="java.util.Calendar,java.io.*"%>
<%@page contentType="text/html;charset=UTF-8" %>  <%-- 이 jsp가 실행하고 만들어내는것은 문자, 페이지인코딩은 jsp가 파일로 저장될때 어떤 문자셋으로 저장될것이냐이고 carset은 네트워크로 보낼때 사용한 인코딩방식을 이야기함 --%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <%-- uri에 속한태그들을 c로 사용하겠다. --%>
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
		<h4>JSTL(Java Standard Tag Library</h4>
		<%for(int i=1;i<=5;i++){ %>
		<img src="/WebApplication/resources/image/member0<%=i%>.png" width="50px" height="50px"/>
		
		<%}%>
		<br/>
		<c:forEach begin="1" end="5" step="1" varStatus="status"> <%--for문과 비슷한 역할 , 달러 중괄호를 넣어주고 객체.필드를 이용하면 상태를 알수있다. varstatus는 몇번도는지 알기위해서--%>
			<img src="/WebApplication/resources/image/member0${status.count}.png" width="50px" height="50px"/>
		</c:forEach>

		

		
	</body>
</html>
