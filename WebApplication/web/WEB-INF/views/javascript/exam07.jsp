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
			//배열에 관한 예제
			var arr1 =["Saab","Volvo","BMW"];
			console.log(arr1[0]);
			console.log(arr1[1]);
			console.log(arr1[2]);
			console.log(arr1.length);
			
			var arr2=new Array("Saab","Volvo","BMW"); 
			arr2.sort();
			for(var i=0;i<arr2.length;i++){
				console.log(arr2[i]);
			}
			
			var arr3=[ "Banana","Orange","Apple","Mango"];  //자바 스크립트 배열은 길이제한이 없다. 추가적으로 더 넣고 싶으면 푸쉬로 넣으면됨
			arr3.push("Lemon");
			for(var i=0;i<arr3.length;i++){
				console.log(arr3[i]);
			}
			arr3[5]="포도";                                       //5인덱스가 없지만 이런식으로 주면 알아서 배열을 늘림
				for(var i=0;i<arr3.length;i++){
				console.log(arr3[i]);
			}
			arr3.splice(2,1,"Kiwi");                    //                      //배열의 메소드를 확인하려면 w3schools.com에 들어가서 javascript->js array에 들어가면 볼수 있다.
				for(var i=0;i<arr3.length;i++){
				console.log(arr3[i]);
			}
			arr3.splice(0,2); // 0인덱스에서 2개를 지워라
			
		</script>
	</head>
	<body>
		<h1>Hello World!</h1>
	</body>
</html>
