package hardware.sensor;

import hardware.converter.PCF8591;

public class ThermistorSensor {
	//Field
	private PCF8591 pcf8591; //PCF사용을 위해 선언
	
	//Constructor
	public ThermistorSensor(PCF8591 pcf8591){
		this.pcf8591=pcf8591; //pcf 8591 초기화
	}
	//Method
	//온도값리턴을 위한 메소드
	public double getValue() throws Exception{   
		int analogVal=pcf8591.analogRead(); // 0~255 
		//섭씨값 환산을 위한 공식(데이터시트참조)
		double value= 5*(double)analogVal/255;
		value=10000*value/(5-value);
		value=1/( (Math.log(value/10000)/3950)+(1/(273.15+25)) );
		value=value-273.15;
		return value;
	}
	
	public static void main(String[] args) throws Exception {
		PCF8591 pcf8591 = new PCF8591(0x48,PCF8591.AIN1); //PCF사용을 위한 객체생성
		ThermistorSensor test = new ThermistorSensor(pcf8591);
		//1초단위로 온도값 출력
		while(true){
			double value=test.getValue();
			System.out.println(value+"도");
			Thread.sleep(1000);  
		}
	}
}
