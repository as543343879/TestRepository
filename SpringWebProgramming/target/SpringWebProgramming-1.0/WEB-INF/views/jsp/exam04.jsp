<%--
[표현식]
1. 작성: ${...}
2. 용도: 값 또는 객체의 Getter를 이용해서 값을 얻고 출력

--%>



<%@page import="com.mycompany.myapp.dto.Member"%>
<%@page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>JSP Page</title>
		<meta name="viewport" content="width=device-width, initial-scale=1,user-scalable=no">
		<link href="<%=application.getContextPath()%>/resources/bootstrap-3.3.7/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
		<script src="<%=application.getContextPath()%>/resources/jquery/jquery-3.2.1.min.js" type="text/javascript"></script>
		<script src="<%=application.getContextPath()%>/resources/bootstrap-3.3.7/js/bootstrap.min.js" type="text/javascript"></script>
	</head>
	<body>
	<%-- dto에 member클래스가 저장되어 있다.  --%>
		<div>
		<%String name="홍길동";%>
		이름:<%=name%>
		</div>
		
		
		<div>
			<%Member member = new Member("홍길동",30);%>
			이름: <%=member.getName()%><br/>
			나이:<%=member.getAge()%>
		
		</div>
			
		<hr/>
		<%request.setAttribute("name","홍길동");%> <%--  --%>
		<%=request.getAttribute("name")%>
		이름: ${name}
		
		<div>
			<%request.setAttribute("member",new Member("홍길동",30));%>
			이름:<%=((Member)request.getAttribute("member")).getName()%>
			이름: ${member.name}<br/>                 <%-- 게터를 호출해서 리턴값을 받아 출력 --%>
			나이: ${member.age}
		</div>
		
		<div>
			이름:<%=((Member)request.getAttribute("member2")).getName()%><br/>
			이름:<%=request.getAttribute("name2")%><br/>
			이름:${name2} <br/>     <%-- 필드라면 이렇게 부르고 객체라면 아래줄처럼--%>
			이름:${member2.name}<br/>
			나이:${member2.age}
		</div>
	</body>
</html>