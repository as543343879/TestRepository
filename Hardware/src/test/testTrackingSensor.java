package test;

import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import hardware.buzzer.ActiveBuzzer;
import hardware.sensor.TrackingSensor;
import java.io.IOException;

public class testTrackingSensor {

	public static void main(String[] args) throws IOException {
		ActiveBuzzer buzzer = new ActiveBuzzer(RaspiPin.GPIO_01);
		TrackingSensor tracking = new TrackingSensor(RaspiPin.GPIO_00);
		tracking.setGpioPinListenerDigital(event -> {
			if (event.getState() == PinState.HIGH) {
				buzzer.off();
			} else {
				buzzer.on();
			}
		});
		System.out.println("Ready");
		System.in.read();
	}

}
