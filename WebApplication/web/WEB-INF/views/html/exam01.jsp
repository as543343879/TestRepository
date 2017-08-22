<%@page contentType="text/html;charset=EUC-KR"%><!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>JSP Page</title>
		<script>
			function handleBtnNaver(){
			
				console.log("handleBtnNaver().....");
				location.href="http://www.naver.com";
			}
			function handleBtnGoogle(){
				console.log("Google().....");
				
				location.href="http://www.google.com";
			}
		</script>
	</head>
	<body>
		<h1>태그종류</h1>
		<h3>링크태그</h3>
		
		<a href="http://www.naver.com">네이버</a><br/>
		
		<h3>버튼태그</h3>
		<button id="btnNaver" onclick="handleBtnNaver()">네이버</button>
		<button id="btnGoogle" onclick="handleBtnGoogle()">구글</button>
		<input type="button" onclick="handleBtnNaver()" value="네이버"/>
		
		<h3>이미지태그</h3>
		<img src="/WebApplication/resources/image/girl.jpg" alt="Smiley face" height="300">
		<input type="image" src="/WebApplication/resources/image/girl.jpg" />
		
		<h3>컨테이너태그</h3>
		<div>
			<button onclick="handleBtnNaver()">네이버</button>
			<img src="/WebApplication/resources/image/girl.jpg" alt="Smiley face" height="300">
		</div>
		<div>
			<button onclick="handleBtnNaver()">네이버</button>
			<img src="/WebApplication/resources/image/girl.jpg" alt="Smiley face" height="300">
		</div>
		
		<h3>폼태그</h3>
		<form>
			이름:<input type="text"/><br/>
			나이:<input type="number"/><br/>
			생년월일:<input type="date"/><br/>
			성별:<input type="radio" name="sex"/>남<input type="radio" name="sex"/>여<br/>
			관심분야:<input type="checkbox"/>자바
			<input type="checkbox"/>자바FX
			<input type="checkbox"/>스프링<br/>
			<input type="button" value="일반버튼"/>
			<input type="reset" value="리셋버튼"/>
			<input type="submit" value="제출버튼"/>
		</form>
	</body>
</html>
