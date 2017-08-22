<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>JSP Page</title>
		<style type="text/css">
			/* 태그 선택자{selector} */
			div {background-color: yellow; border: 1px solid black; height:50px;}
			/* 클래스 선택자{selector} */
			.skyblue{background-color: skyblue;}
			.btn {border: 1px solid black; height: 70px; padding: 5px;}
			.success{background-color: forestgreen;}
			.warning{background-color: red; color: white;}
			/* id 선택자{selector} */
			#goldDiv1{background-color: red;}
			#goldDiv2{background-color: aqua;}
		</style>
	</head>
	<body>
		
		<div>div태그</div>
		<div>div태그</div>
		<div>div태그</div>
		<div class="skyblue">div태그</div>
		<div class="skyblue">div태그</div>
		<div id="goldDiv1" class="skyblue">div태그</div>
		<div id="goldDiv2" class="skyblue">div태그</div>
		<a class="btn success">확인</a>    
		<a class="btn warning">확인</a>
		
	
	</body>
</html>
