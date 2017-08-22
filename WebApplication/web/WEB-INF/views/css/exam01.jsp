<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>JSP Page</title>
	</head>
	<body>
		<h1>블록 태그 및 인라인 태그</h1>
		
		<h3>블록태그</h3>
		<form style="background-color: gold; border: 1px solid black; height:100px">form태그</form>
		<div style="background-color: yellow; border: 1px solid black; height:100px">div태그</div>
		<p style="background-color: skyblue; border: 1px solid black; height:100px">p 태그</p>
	    <h4 style="background-color: coral; border: 1px solid black; height:100px">h태그</h4>
	    <form style="background-color: gold; border: 1px solid black; height:50px">form태그</form>
	    <table style="background-color: blanchedalmond; border: 1px solid black; height:50px ">
			<tr>
				<td>table태그</td>
			</tr>
			</table>
			<table style="background-color: blanchedalmond; border: 1px solid black; height:50px; width:100%;">
			<tr>
				<td>table태그</td>
			</tr>
			</table>
			
			
			<h3>인라인 태그</h3>
			<span style="background-color: yellow; border: 1px solid black; height:50px">span태그</span>
			<span style="background-color: skyblue; border: 1px solid black; height:50px">span태그</span>
			<img sytle="border:1px solid black;" src="/WebApplication/resources/image/girl.jpg" height="100"/>
			<a sytle="border:1px solid black;" href="/html">HTML</a>
			<button>button태그</button>
			
			<h3>블록->인라인</h3>
			<div style="background-color: yellow; border: 1px solid black; height:50px; display: inline;">div태그</div>
			<div style="background-color: skyblue; border: 1px solid black; height:50px; display: inline;">div태그</div>
			
	</body>
</html>
