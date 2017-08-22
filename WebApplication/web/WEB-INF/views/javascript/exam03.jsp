<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>JSP Page</title>
		<script>
			//전역변수
			var v1="A";
			
			
			function fun1(){
				//로컬변수
				var v2="B";
				if(true){
					var v3="C";
					v4= "D"; //var없이 선언시 전역변수
				}
				console.log(v1);
				console.log(v2);
				console.log(v3);
				console.log(v4);
			}
			console.log(v1);
	fun1();		
	console.log(v4);
			

		</script>
	</head>
	<body>
		<h1>Hello World!</h1>
	</body>
</html>
