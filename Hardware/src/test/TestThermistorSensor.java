package test;

import com.pi4j.io.gpio.RaspiPin;
import hardware.buzzer.ActiveBuzzer;
import hardware.converter.PCF8591;
import hardware.sensor.ThermistorSensor;

public class TestThermistorSensor {

	public static void main(String[] args) throws Exception {
		ActiveBuzzer buzzer = new ActiveBuzzer(RaspiPin.GPIO_00);
		PCF8591 pcf8591 = new PCF8591(0x48, PCF8591.AIN0); //PCF사용을 위한 객체생성
		ThermistorSensor ther = new ThermistorSensor(pcf8591);
		//온도값이 30이상이면 부저가 울린다.
		while (true) {
			double value = ther.getValue();
			System.out.println(value + "도");
			if (value >= 30) {
				buzzer.on();
			} else {
				buzzer.off();
			}
		}
	}
}
