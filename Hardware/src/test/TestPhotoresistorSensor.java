package test;

import com.pi4j.io.gpio.RaspiPin;
import hardware.converter.PCF8591;
import hardware.led.RgbLedDigital;
import hardware.sensor.PhotoresistorSensor;

public class TestPhotoresistorSensor {

	public static void main(String[] args) throws Exception {
		RgbLedDigital rgb = new RgbLedDigital(RaspiPin.GPIO_00, RaspiPin.GPIO_01, RaspiPin.GPIO_02);
		PCF8591 pcf8591 = new PCF8591(0x48, PCF8591.AIN0); //PCF사용을 위한 객체생성
		PhotoresistorSensor photo = new PhotoresistorSensor(pcf8591); //Photoresistor센서 객체 생성

		while (true) {
			double value = photo.getValue();
			System.out.println(value);
			Thread.sleep(1000);
			
			//조도값에 따라 RGB LED가 다른 색상이 나타나도록 한다.
			if(value<=80){
				rgb.rgb(true,false,false);
			}else if(value>=120){
				rgb.rgb(false,true,false);
			}else{
				rgb.rgb(false,false,true);
			}
		}
	}

}
