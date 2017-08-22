<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>JSP Page</title>
		<meta name="viewport" content="width=device-width, initial-scale=1,user-scalable=no">
		<link href="/WebApplication/resources/bootstrap-3.3.7/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
		<script src="/WebApplication/resources/jquery/jquery-3.2.1.min.js" type="text/javascript"></script>
		<script src="/WebApplication/resources/bootstrap-3.3.7/js/bootstrap.min.js" type="text/javascript"></script>
		<script>
			//글로벌 함수-> 함수선언없이 가능한것, 브라우저가 자체적으로 제공하는것 (js global 에서 확인하여 사용가능하다.)
			var v1="100.5";
			var v2=v1+200;
			console.log(v2);
			
			var v3=parseInt(v1)+200;     // 문자열을 정수로 바꾸는것
			console.log(v3);
			
			var vv=parseFloat(v2)+200;    
			console.log(vv);
			
			var v4=String(10)+" 점";     // 숫자를 문자열로 바꾸는것 
			console.log(v4);
			
			
		</script>
	</head>
	<body>
		<h1>Hello World!</h1>
	</body>
</html>
