
package test;

import com.pi4j.io.gpio.RaspiPin;
import hardware.buzzer.ActiveBuzzer;
import hardware.sensor.UltrasonicSensor;
public class UltrasonicSensortest {
	public static void main(String[] args) throws InterruptedException {
				UltrasonicSensor test = new UltrasonicSensor(RaspiPin.GPIO_00, RaspiPin.GPIO_01);
				ActiveBuzzer buzzer=new ActiveBuzzer(RaspiPin.GPIO_02);
		while (true) {
			int distance = test.getDistance();
			if(distance>=20){
				buzzer.off();
			}else{
				buzzer.on();
			}
			System.out.println("거리(cm)" + distance);
			Thread.sleep(1000);
		}
	}

}
