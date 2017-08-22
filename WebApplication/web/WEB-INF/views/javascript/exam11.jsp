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
			function handleBtn1() {
//DOM에 관한 예제들
//DOM -> Document - HTML문서 전체를 표현하는 객체
           -> 
				var img = document.querySelector("img"); //위에서 아랫방향으로 첫번째 이미지태그를 찾아 객체번지를 리턴
				img.src = "/WebApplication/resources/image/photo02.jpg";  // 이미지의 경로 변경    //


				var imgArray = document.querySelectorAll("img"); //현재 도큐먼트에서 이미지라는 태그들을 모두 찾아서 배열로 리턴
				for (var i = 0; i < imgArray.length; i++) {
					imgArray[i].src = "/WebApplication/resources/image/photo02.jpg";
				}
			}
			function handleBtn2() {

				var img1 = document.querySelector("#img1");    //아이디로 찾는것
				img1.src = "/WebApplication/resources/image/photo04.jpg";


			}
			function handleBtn3() {
				var class1=document.querySelectorAll(".class1");          //클래스로 찾는것
				for (var i = 0; i < class1.length; i++) {
					class1[i].src = "/WebApplication/resources/image/photo05.jpg";
				}

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
