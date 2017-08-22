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
			
			//DOM객체로 img를 찾아와서 jquery 객체로 가진다.
			function handleBtn1() {

				var imgArray = $("img");       //Jquery에서 $는 jquery 객체를 뜻한다. ->jquery()를 의미한다.     이미지 객체를 찾아서 jquery객체로 가져온다는 의미.
				imgArray.attr("src","/WebApplication/resources/image/photo02.jpg"); //attr은 해당 배열의 각각을 빼내서 src속성을 해당 주소로 바꾼다는 의미

                  }
			function handleBtn2() {

				var img1= $("#img1");
				img1.attr("src","/WebApplication/resources/image/photo03.jpg"); //attr은 해당 배열의 각각을 빼내서 src속성을 해당 주소로 바꾼다는 의미


			}
			function handleBtn3() {
				var class1=$(".class1");
				class1.attr("src","/WebApplication/resources/image/photo05.jpg");
				}

			
		</script>
	</head>
	<body>
		<h1>DOM 사용하기</h1>
		<div>
			<button onclick="handleBtn1()">이미지변경</button><br/>
			<img src="/WebApplication/resources/image/photo01.jpg" width="100px" height="100px"/>
			<img src="/WebApplication/resources/image/photo03.jpg" width="100px" height="100px"/>
		</div>

		<div>
			<button onclick="handleBtn2()" class="btn btn-success">이미지변경</button>
			<button onclick="handleBtn3()" class="btn btn-success">이미지변경</button><br/>
			<img id="img1" src="/WebApplication/resources/image/photo01.jpg" width="100px" height="100px"/>
			<img class="class1" src="/WebApplication/resources/image/photo03.jpg" width="100px" height="100px"/>
			<img class="class1" src="/WebApplication/resources/image/photo03.jpg" width="100px" height="100px"/>
		</div>
	</body>
</html>
