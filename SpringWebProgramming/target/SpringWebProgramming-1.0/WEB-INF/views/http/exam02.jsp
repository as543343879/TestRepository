<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
		<h1>요청 HTTP 정보얻기!</h1>
		1.요청방식(method): ${method} <br/>
		2.URI: ${uri}<br/>
		3.QueryString: ${queryString}<br/>
		3.요청 파라미터 : ${type}<br/>
		3.요청 파라미터 : ${bno}<br/>
		3.요청 파라미터 : ${bnoInt}<br/>
		3.요청 파라미터 : 
		<c:forEach var="h" items="${hobby}" varStatus="kim">
			${h}<c:if test="${!kim.last}">,</c:if>
			
		</c:forEach>
		<br/>
		4.요청 헤더값(User-agent): ${userAgent}<br/>
	</body>
</html>
