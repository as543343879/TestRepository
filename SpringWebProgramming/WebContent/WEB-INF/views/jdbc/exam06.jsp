<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<meta name="viewport" content="width=device-width, initial-scale=1,user-scalable=no">
		<link href="<%=application.getContextPath()%>/resources/bootstrap-3.3.7/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
		<script src="<%=application.getContextPath()%>/resources/jquery/jquery-3.2.1.min.js" type="text/javascript"></script>
		<script src="<%=application.getContextPath()%>/resources/bootstrap-3.3.7/js/bootstrap.min.js" type="text/javascript"></script>
	</head>
<body>

	
	<hr />
	<table class="table table-boarder">
		<tr class="success">
			<td>아이디</td>
			<td>이름</td>
			<td>가입날짜</td>
			<td>전화번호</td>
			<td>이메일</td>
			<td>나이</td>
			<td>주소</td>
			<td>프로필 사진</td>
		</tr>
		<c:forEach var="b" items="${list}">
			<tr>
				<td><a href="exam06Detail?mid=${b.mid}">${b.mid}</a></td>
				<td>${b.mname}</td>
				<td>${b.mdate}</td>
				<td>${b.mtel}</td>
				<td>${b.memail}</td>
				<td>${b.mage}</td>
				<td>${b.maddress}</td>

				<c:choose>
				<c:when test="${b.moriginalfilename==null}"><td><img src="<%=application.getContextPath()%>/resources/image/noimage.png" width="100px" height="100px" /></td></c:when>
				<c:otherwise><td><img src="<%=application.getContextPath()%>/resources/image/${b.msavedfilename}" width="100px" height="100px" /></td></c:otherwise>
				</c:choose>
			</tr>
		</c:forEach>
	</table>
	
	<div style="margin-top: 20px; width: 700px; text-align: center;">
		<a href="exam06?pageNo=1">[처음]</a>
		<c:if test="${groupNo>1}">
			<a href="exam06?pageNo=${startPageNo-1}">[이전]</a>
		</c:if>
		
		<c:forEach var="i" begin="${startPageNo}" end="${endPageNo}">
		&nbsp;
		<a href="exam06?pageNo=${i}"<c:if test="${pageNo==i}"> style="font-weight:bold; color:red;"</c:if>>${i}</a>
		&nbsp;
		</c:forEach>
		
		<c:if test="${groupNo<totalGroupNo}">
			<a href="exam06?pageNo=${endPageNo+1}">[다음]</a>
		</c:if>
		<a href="exam06?pageNo=${totalPageNo}">[맨끝]</a>
	</div>
<a href="exam03" class="btn btn-success">회원추가</a> 
</body>
</html>