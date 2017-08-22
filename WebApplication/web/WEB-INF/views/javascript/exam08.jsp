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
			//객체생성
			var car={
				//필드(속성)
				"model":"승용차",     //네트워크 전송시를 위해 필드 이름에 "필드이름" 이런식으로 붙여줌
				"company":"현대",
				"speed":0,
				"start":function(){                                           //start 이름의 메소드
					console.log("시동을 겁니다.");
					
				},
				"setSpeed":function(speed){
					this.speed=speed;               //javascript에서는 this를 절대 생략하면 안된다.
				},
				"getSpeed":function(){
					return this.speed;
				}
			};
			//객체 필드(속성)값 읽기
			console.log(car.model);
			car.company="기아"; // 값 수정
			console.log(car.company);
			car.speed=60;
			console.log(car.speed);
			//메소드 호출
			car.start();
			car.setSpeed(100);
			console.log(car.getSpeed());
			
		</script>
	</head>
	<body>
		<h1>자바스크림트 객체</h1>
	</body>
</html>
