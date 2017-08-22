<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>JSP Page</title>
		<meta name="viewport" content="width=device-width, initial-scale=1,user-scalable=no">
		<link href="<%=application.getContextPath()%>/resources/bootstrap-3.3.7/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
		<script src="<%=application.getContextPath()%>/resources/jquery/jquery-3.2.1.min.js" type="text/javascript"></script>
		<script src="<%=application.getContextPath()%>/resources/bootstrap-3.3.7/js/bootstrap.min.js" type="text/javascript"></script>
		<script>
			function handleBtn1() {   //조각을 받아서 사용한 방법
				$.ajax({
					url: "<%=application.getContextPath()%>/jquery/exam04_html_fragment", //여기서 데이터를 다 받고 나면 자동으로 success 메소드 실행 
					success: function (data) {

						$("#div1").html(data);                       
					}
				});
			}
			function handleBtn2() {          //json 데이터를 받아서 사용한방법
				$.ajax({
					url: "<%=application.getContextPath()%>/jquery/exam04_json", //여기서 데이터를 다 받고 나면 자동으로 success 메소드 실행  //json 데이터를 받아서 변경한것
					success: function (data) {
						for (var i = 0; i < data.length; i++) {
							var fileName = data[i].fileName; //객체접근해서 filename을 읽어라
							var message = data[i].message;
							var html_fragment = "";
							html_fragment += '<div>';
							html_fragment += '<img src="<%=application.getContextPath()%>/resources/image/' + fileName + '" width="30px" height="30px"/>';
							html_fragment += '<span>message</span>';
							html_fragment += '</div>';
							$("#div2").append(html_fragment);
						}
					}
				});
			}
		</script>
	</head>
	<body>
		<h1>AJAX!</h1>
		<div class="container">
			<div class="row">
				<div class="col-md-6">
					<button onclick="handleBtn1()">HTML 조각받아오기</button>
					<div id="div1"></div>
				</div>
				<div class="col-md-6">
					<button onclick="handleBtn2()">JSON데이터 받아오기</button>
					<div id="div2"></div>
				</div>
			</div>
		</div>
	</body>
</html>
