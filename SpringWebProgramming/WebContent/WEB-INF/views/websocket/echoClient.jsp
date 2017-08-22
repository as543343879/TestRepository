<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<meta name="viewport"
	content="width=device-width, initial-scale=1,user-scalable=no">
<link
	href="<%=application.getContextPath()%>/resources/bootstrap-3.3.7/css/bootstrap.min.css"
	rel="stylesheet" type="text/css" />
<script
	src="<%=application.getContextPath()%>/resources/jquery/jquery-3.2.1.min.js"
	type="text/javascript"></script>
<script
	src="<%=application.getContextPath()%>/resources/bootstrap-3.3.7/js/bootstrap.min.js"
	type="text/javascript"></script>
	<script type="text/javascript">
    var ws=null;
	function handleBtnConnect(){
		//ip를 동적으로 얻어내기 위하여 사용한 widow.~~~
		//이코드로 인하여 디스패쳐서블릿에 매핑해놓은 echoHandler를 실행하게됨. 이때 실행되면서 자동 연결되고 성공될것이다.
		ws=new WebSocket("ws://"+window.location.host+"/SpringWebProgramming/websocket/echo");
		//함수on ( open이 되었을때 이걸 선택해라, 메시지도착했을때 이거해라 라는 함수,내가 아래 정의함), 함수연결
		ws.onopen=handleOnOpen;
		ws.onmessage=handleOnMessage;
		ws.onclose=handleOnClose;
		
	}
	function handleOnOpen(){
		display("[연결성공]<br/>");
		$("#btnConnect").attr("disabled",true);
		$("#btnDisConnect").attr("disabled",false);
	}
	//메시지를 받을 핸들러
	function handleOnMessage(event){
		var strMessage=event.data;
		display("[에코]"+strMessage);
		
	}
	//클라이언트에서는 이것이 실행되겠지만 서버에서는 handler 클래스에서 close가 실행될것
	function handleOnClose(){
		display("[연결끊김]<br/>");
		$("#btnConnect").attr("disabled",false);
		$("#btnDisConnect").attr("disabled",true);
	}
	
	
	
	function handleBtnDisConnect() {
		if(ws!=null){
			ws.close();
			ws=null;
		}
	}
	function handleBtnSend() {
		var strMessage=$("#txtMessage").val();
		ws.send(strMessage);
	}
	
	function display(message){
		$("#divDisplay").append("<span style='display:block;'>"+message+"</span>");
		if($("#divDisplay span").length>20){
			$("#divDisplay span").first().remove();
		}
		$("#divDisplay").scrollTop($("#divDisplay").height());
	}
	</script>
</head>

<body>
	<h3>WebSocket-Echo</h3>
	<hr />
	<div>
	<button id="btnConnect" class="btn btn-warning" onclick="handleBtnConnect()">연결하기</button>
	<button id="btnDisConnect" class="btn btn-danger" onclick="handleBtnDisConnect()">연결끊기</button>
	</div>
	<div>
	<input id="txtMessage" type="text"/>
	<button id="btnSend" class="btn btn-info" onclick="handleBtnSend()">메세지전송</button>
	</div>
	<div>
	<div id="divDisplay" style="width:500px; height:300px; padding:5px; overflow-y:scroll; border:1px solid black; margin-top:5px;"></div> <!-- overflow-y는 초과했을때 넘어가는거 -->
	</div>

</body>
</html>