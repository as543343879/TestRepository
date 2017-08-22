package hardware.sensor;

import hardware.converter.PCF8591;

public class PhotoresistorSensor {
	//Field
	private PCF8591 pcf8591; //PCF사용을 위해 선언

	//Constructor
	public PhotoresistorSensor(PCF8591 pcf8591) {
		this.pcf8591 = pcf8591; //pcf 8591 초기화
	}

	//Method
	//조도값 리턴을 위한 메소드
	public double getValue() throws Exception {
		int analogVal = pcf8591.analogRead(); // 0~255 
		return analogVal;
	}

	public static void main(String[] args) throws Exception {
		PCF8591 pcf8591 = new PCF8591(0x48, PCF8591.AIN0); //PCF사용을 위한 객체생성
		PhotoresistorSensor test = new PhotoresistorSensor(pcf8591);
		//1초단위로 조도값 출력
		while (true) {
			double value = test.getValue();
			System.out.println(value);
			Thread.sleep(1000);
		}
	}

}
