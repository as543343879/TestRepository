<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>transaction</title>
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

	<h4>계좌이체</h4>
	<hr />
	<form method="post">
		출금계좌:<input type="text" name="fromAno" /><br /> 
		입금계좌:<input type="text" name="toAno" /><br /> 
		이체금액:<input type="number" name="amount" min="1" /><br /> 
		<input type="submit" value="이체하기"
			class="btn btn-info" />
	</form>

</body>
</html>