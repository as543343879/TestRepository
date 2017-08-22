package test;

import com.pi4j.io.gpio.RaspiPin;
import hardware.buzzer.ActiveBuzzer;
import hardware.converter.PCF8591;
import hardware.led.DualColorLed;
import hardware.motor.SG90Servo;
import hardware.sensor.GasSensor;

public class GasSensorBuzzerDualLedServoMotorTest {

	public static void main(String[] args) throws Exception {
		PCF8591 pcf8591 = new PCF8591(0x48, PCF8591.AIN0); //PCF사용을 위한 객체생성
		ActiveBuzzer buzzer = new ActiveBuzzer(RaspiPin.GPIO_03);
		DualColorLed dual = new DualColorLed(RaspiPin.GPIO_04, RaspiPin.GPIO_02);
		GasSensor gas = new GasSensor(pcf8591, RaspiPin.GPIO_00);
		SG90Servo motor = new SG90Servo(RaspiPin.GPIO_01, 8, 23);

		while (true) {
			double value = gas.getValue();
			System.out.println(value);
			if (value > 230) {
				motor.setAngle(170);
				buzzer.on();
				dual.red();
			} else {
				motor.setAngle(0);
				buzzer.off();
				dual.green();
			}
			Thread.sleep(1000);
		}
	}

}
