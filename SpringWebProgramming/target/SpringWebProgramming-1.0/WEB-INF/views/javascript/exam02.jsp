<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>JSP Page</title>
		<script>
			//함수(매개변수도 타입안줌)
			function totalSum(from, to) {
			if(to==undefined){
				to=from;
				from=1;
			}
			var sum = 0;
				for (var i = from; i <= to; i++) {
					sum += i;
				}
				return sum;
			}
			var result=totalSum(100);
			console.log("result:"+result);
		</script>
	</head>
	<body>
		<h1>Hello World!</h1>
	</body>
</html>
