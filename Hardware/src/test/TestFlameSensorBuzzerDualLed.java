package test;

import com.pi4j.io.gpio.RaspiPin;
import hardware.buzzer.ActiveBuzzer;
import hardware.converter.PCF8591;
import hardware.led.RgbLedDigital;
import hardware.sensor.FlameSensor;

public class TestFlameSensorBuzzerDualLed {
	
	public static void main(String[] args) throws Exception {
		PCF8591 pcf8591 = new PCF8591(0x48, PCF8591.AIN0); //PCF사용을 위한 객체생성
		FlameSensor test = new FlameSensor(pcf8591, RaspiPin.GPIO_00);
		ActiveBuzzer buzzer=new ActiveBuzzer(RaspiPin.GPIO_01);
		RgbLedDigital rgb = new RgbLedDigital(RaspiPin.GPIO_02, RaspiPin.GPIO_04, RaspiPin.GPIO_03);
		while (true) {
			double value = test.getValue();
			System.out.println(value);
			if(value<100){
				rgb.rgb(true,false,false);
				buzzer.on();
			}else{
				rgb.rgb(false,true,false);
				buzzer.off();
			}
			Thread.sleep(1000);
		}
	}
}
