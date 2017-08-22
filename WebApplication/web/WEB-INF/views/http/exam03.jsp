<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>JSP Page</title>
		<meta name="viewport" content="width=device-width, initial-scale=1,user-scalable=no">
		<link href="/WebApplication/resources/bootstrap-3.3.7/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
		<script src="/WebApplication/resources/jquery/jquery-3.2.1.min.js" type="text/javascript"></script>
		<script src="/WebApplication/resources/bootstrap-3.3.7/js/bootstrap.min.js" type="text/javascript"></script>
	</head>
	<body>
		<h1>HTTP!</h1>
		3.요청 파라미터 : ${bno}<br/>
		3.요청 파라미터 : ${type}<br/>
		4.요청 헤더값(User-agent): ${userAgent}<br/>
		
				<c:forEach var="h" items="${hobby}" varStatus="kim">
			${h}<c:if test="${!kim.last}">,</c:if>
			
		</c:forEach>
	</body>
</html>
