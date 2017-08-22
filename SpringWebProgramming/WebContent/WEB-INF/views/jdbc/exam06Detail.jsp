<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<meta name="viewport" content="width=device-width, initial-scale=1,user-scalable=no">
		<link href="<%=application.getContextPath()%>/resources/bootstrap-3.3.7/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
		<script src="<%=application.getContextPath()%>/resources/jquery/jquery-3.2.1.min.js" type="text/javascript"></script>
		<script src="<%=application.getContextPath()%>/resources/bootstrap-3.3.7/js/bootstrap.min.js" type="text/javascript"></script>
	<script
	src="<%=application.getContextPath()%>/resources/bootstrap-3.3.7/js/bootstrap.min.js"
	type="text/javascript"></script>
	
	<script type="text/javascript">
	function handleBtnUpdate(){
		var mpassword=$("#mpassword").val();
		if(mpassword==""){
			$("#mpassword").attr("placeholder","비밀번호를 입력하셔야 합니다.");
			$("#mpassword").css("border-color","red");
			return;
		}
		$.ajax({
			url: "exam06CheckMpassword",
			method:"post",
			//data:"bno=${board.bno}&bpassword="+bpassword
			data:{"mid":"${member.mid}","mpassword":mpassword},
			success: function(data){   //data는 {"result":"success"}의 형태로 서버에서 응답을 받게 된다.
				if(data.result=="success"){   //data의 rEsult부분이 success가 맞냐
				console.log("success");
				location.href="exam06Update?mid=${member.mid}"
				}else{
					$("#mpassword").val("");
					$("#mpassword").attr("placeholder","비밀번호가 다릅니다.");
					$("#mpassword").css("border-color","red");
				}
			}
		});
		
	}
	function handleBtnDelete(){
		var mpassword=$("#mpassword").val();
		if(mpassword==""){
			$("#mpassword").attr("placeholder","비밀번호를 입력하셔야 합니다.");
			$("#mpassword").css("border-color","red");
			return;
		}
		$.ajax({
			url: "exam06CheckMpassword",
			method:"post",
			//data:"bno=${board.bno}&bpassword="+bpassword
			data:{"mid":"${member.mid}","mpassword":mpassword},
			success: function(data){   //data는 {"result":"success"}의 형태로 서버에서 응답을 받게 된다.
				if(data.result=="success"){   //data의 rEsult부분이 success가 맞냐
				console.log("success");
				location.href="exam06Delete?mid=${member.mid}"
				}else{
					$("#mpassword").val("");
					$("#mpassword").attr("placeholder","비밀번호가 다릅니다.");
					$("#mpassword").css("border-color","red");
				}
			}
		});
	}
	function handleDelete(){
		var mpassword=$("#mpassword").val();
		if(mpassword==""){
			$("#mpassword").attr("placeholder","비밀번호를 입력하셔야 합니다.");
			$("#mpassword").css("border-color","red");
			return;
		}
		$.ajax({
			url: "exam06CheckMpassword",
			method:"post",
			//data:"bno=${board.bno}&bpassword="+bpassword
			data:{"mid":"${member.mid}","mpassword":mpassword},
			success: function(data){   //data는 {"result":"success"}의 형태로 서버에서 응답을 받게 된다.
				if(data.result=="success"){   //data의 rEsult부분이 success가 맞냐
				console.log("success");
				location.href="exam06ImgDelete?mid=${member.mid}"
				}else{
					$("#mpassword").val("");
					$("#mpassword").attr("placeholder","비밀번호가 다릅니다.");
					$("#mpassword").css("border-color","red");
				}
			}
		});
		
	}
	
	</script>
	</head>
<body>
<h4>회원 상세 보기</h4>
	<hr />
		<form method="post" style="padding: 0px 20px"
		enctype="multipart/form-data">
		<div class="form-group">
			<div class="input-group">
				<span class="input-group-addon"> <span
					class="glyphicon glyphicon-asterisk"></span>
				</span> <input type="text" class="form-control" placeholder="아이디" name="mid"
					value="${member.mid}" disabled />
			</div>
		</div>
				<div class="form-group">
			<div class="input-group">
				<span class="input-group-addon"> <span
					class="glyphicon glyphicon-user"></span>
				</span> <input type="text" class="form-control" placeholder="이름" name="mname"
					value="${member.mname}" disabled />
			</div>
		</div>
		
				<div class="form-group">
			<div class="input-group">
				<span class="input-group-addon"> <span
					class="glyphicon glyphicon-heart-empty"></span>
				</span> <input id="mpassword" type="password" class="form-control" placeholder="비밀번호" name="mpassword"
					/>
			</div>
		</div>
						<div class="form-group">
			<div class="input-group">
				<span class="input-group-addon"> <span
					class="glyphicon glyphicon-calendar"></span>
				</span> <input type="date" class="form-control" placeholder="가입날짜" name="mdate"
					value="${member.mdate}" disabled/>
			</div>
		</div>
				<div class="form-group">
			<div class="input-group">
				<span class="input-group-addon"> <span
					class="glyphicon glyphicon-phone-alt"></span>
				</span> <input type="text" class="form-control" placeholder="전화번호" name="mtel"
					value="${member.mtel}" disabled/>
			</div>
		</div>
						<div class="form-group">
			<div class="input-group">
				<span class="input-group-addon"> <span
					class="glyphicon glyphicon-envelope"></span>
				</span> <input type="text" class="form-control" placeholder="이메일" name="memail"
					value="${member.memail}" disabled/>
			</div>
		</div>
						<div class="form-group">
			<div class="input-group">
				<span class="input-group-addon"> <span
					class="glyphicon glyphicon-grain"></span>
				</span> <input type="number" class="form-control" placeholder="나이" name="mage"
					value="${member.mage}" disabled/>
			</div>
		</div>
								<div class="form-group">
			<div class="input-group">
				<span class="input-group-addon"> <span
					class="glyphicon glyphicon-home"></span>
				</span> <input type="text" class="form-control" placeholder="주소" name="maddress"
					value="${member.maddress}" disabled/>
			</div>
		</div>
		<div class="form-group">
			<div class="input-group">
				<span class="input-group-addon"> <span
					class="glyphicon glyphicon-picture"></span>
				</span> <a href="#" class="form-control">${member.moriginalfilename}</a>
			</div>
		</div>
			<c:if test="${member.msavedfilename!=null}">
		<img src="<%=application.getContextPath()%>/resources/image/${member.msavedfilename}" width="300px" height="300px"  id="img1" />
		<hr/>
		<button onclick="handleDelete()" class="btn btn-success">이미지삭제</button>
		<a href="<%=application.getContextPath()%>/file/download?msavedfilename=${member.msavedfilename}" class="btn btn-primary">이미지 다운로드</a>
		</c:if>
		<hr/>
		<a href="exam06" class="btn btn-success">회원목록</a> 
		<input onclick="handleBtnUpdate()" type="button" class="btn btn-warning" value="수정" /> 
		<input onclick="handleBtnDelete()" type="button" class="btn btn-danger" value="삭제" />
		
		
		</form>
</body>
</html>