function backtire(command, direction,speed) {
			var json = {"command": command, "direction":direction, "speed":speed};
			
				$.ajax({
					url:"http://" + location.host + "/SensingCarRemoteWebControl/backtire",
					data: json,
					method: "post",
					success: function(data) {
						if(data.result == "success") {
							$("#backtireStatus").html("방향=" + data.direction + "; 단계=" + data.speed);
							$("#btnDirectionForward").attr("onclick","backtire('change','forward','"+data.speed+"')");
							$("#btnDirectionBackward").attr("onclick","backtire('change','backward','"+data.speed+"')");
							
							$("#btnSpeed0").attr("onclick","backtire('change','"+data.direction+"','0')");
							$("#btnSpeed1").attr("onclick","backtire('change','"+data.direction+"','600')");
							$("#btnSpeed2").attr("onclick","backtire('change','"+data.direction+"','1036')");
							$("#btnSpeed3").attr("onclick","backtire('change','"+data.direction+"','1472')");
							$("#btnSpeed4").attr("onclick","backtire('change','"+data.direction+"','1908')");
							$("#btnSpeed5").attr("onclick","backtire('change','"+data.direction+"','2344')");
							$("#btnSpeed6").attr("onclick","backtire('change','"+data.direction+"','2780')");
						    $("#btnSpeed7").attr("onclick","backtire('change','"+data.direction+"','3216')");
							$("#btnSpeed8").attr("onclick","backtire('change','"+data.direction+"','3352')");
							$("#btnSpeed9").attr("onclick","backtire('change','"+data.direction+"','3654')");
							$("#btnSpeed10").attr("onclick","backtire('change','"+data.direction+"','4095')");
						
						}
					}
				});
			}