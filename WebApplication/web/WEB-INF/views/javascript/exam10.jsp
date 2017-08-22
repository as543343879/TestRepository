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
			//BOM에 관한 예제
			//BOM -> 브라우저가 제공하는 오브젝트
			//DOM -> 도큐먼트 내부(예를들면 해당페이지)에 있는 것을 객체로 만든것
			//DOM element는 태그를 의미함.
			//BOM -> WINDOW   ->브라우저 내에서 늘려지는 윈도우를 의미 
			//           NAVIGATOR
			//           Screen        ->방문자의 스크린을 의미
			//           History       -> url에 대한 이전 이력들, 정보를 가진 객체
			//            Location     -> 현재 url의 정보 관련
			var1 = window.outerHeight;
			var2 = window.innerHeight;
			console.log(var1);
			console.log(var2);
			var3 = window.outerWidth;
			var4 = window.innerWidth;
			console.log(var3);
			console.log(var4);


			function handleBtn1() {
				pop1 = window.open("/WebApplication/javascript/exam09", "pop1", "width=300,height=200");
			}

			function handleBtn2() {
				pop1.close();
			}

			function handleBtn3() {
				work1 = window.setInterval(function () {         // setInterval -> 매 1초마다 해당 함수를 실행한다. 애니메이션 효과기능?
					console.log(Date());
				}, 1000);
			}
			function handleBtn4() {
				window.clearInterval(work1);                              // clearInterval 은 취소할때 사용
			}
			function handleBtn5() {
				work2=window.setTimeout(function(){                // setTimeout은 3초후에 실행
					console.log("yahoo~");
				},3000);
			}
			function handleBtn6() {
				window.clearTimeout(work2);                        // 실행되기 전에 취소하는것
			}
			function handleBtn7() {
				location.href="/WebApplication/javascript/exam09";            //현재 페이지이동을 위한것.
			}

		</script>
	</head>
	<body>
		<h1>BOM 사용하기!</h1>
		<h3>팝업</h3>
		<button onclick="handleBtn1()" class="btn btn-warning">팝업(새탭) 띄우기</button>
		<button onclick="handleBtn2()" class="btn btn-warning">팝업(새탭) 닫기</button>

		<h3>주기적실행</h3>
		<button onclick="handleBtn3()" class="btn btn-warning">시작</button>
		<button onclick="handleBtn4()" class="btn btn-warning">중지</button>

		<h3>지연실행</h3>
		<button onclick="handleBtn5()" class="btn btn-warning">시작</button>
		<button onclick="handleBtn6()" class="btn btn-warning">취소</button>
		
		<h3>요청경로변경</h3>
		<button onclick="handleBtn7()" class="btn btn-warning">요청 경로 변경</button>
		

	</body>
</html>
