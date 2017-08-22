var thermistorSensorChart;
//다 끝나고 나중에 실행
$(function() {
	 //차트객체 만드는 코드
	thermistorSensorChart= new Highcharts.Chart({
		chart : {
			renderTo:"thermistorSensorChartContainer",  //차트는 여기 나타내게 할것이고//jsp의 id이름
			type: "spline",   //라인그래프형식
			//데이터를 누가 제공해줄것이냐
			events: {
				load: requestThermistorSensorData //함수이름(차트객체가 생성되고 나서 해당 함수를 실행시키는 코드)
			}
		},
		colors: ['cyan'],
		title:{
			text:"thermistorSensorChart(온도센서)"
		},
		xAxis:{
			type:"datetime", //시간에 따라 변하는 값을 보이기 위해 datetime 타입사용
			tickPixelInterval:100, // 한칸의 간격을 100px로 주겠다.
			minRange: 20*1000
		},
		yAxis:{
			minPadding: 0.2, //여백
			maxPadding: 0.2,
			title: {
				text:"온도",
				margin: 30
			}
		},
		series:[{
			name: "온도",
			data: []
		}]
	});
});
//차트에 데이터를 제공해주는 함수
function requestThermistorSensorData(){
	var ws = new WebSocket("ws://"+location.host+"/SensingCarRemoteWebControl/websocket/thermistorsensor");
	ws.onmessage = function(event){
		var data= JSON.parse(event.data); //JSON.parse는 문자열로 되어있는 제이슨( '{"xxx":"value"}')을 javascript객체( {"xxx":"value"} )로 만들어주는 작업을함
		var series =thermistorSensorChart.series[0];
		var shift= series.data.length > 45; //화면이 이동되는 순간은 20개 이상일때 true를 리턴, 이하일때는 false
		series.addPoint([data.time,data.temperature],true,shift); //[x축,y축]
	};
}




