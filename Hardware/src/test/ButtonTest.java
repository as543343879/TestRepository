
package test;

import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import hardware.button.Button;
import hardware.led.DualColorLed;
import java.io.IOException;
public class ButtonTest {

	
	public static void main(String[] args) throws IOException {
		
			DualColorLed dual = new DualColorLed(RaspiPin.GPIO_02, RaspiPin.GPIO_01);
			Button button = new Button(RaspiPin.GPIO_00);
			button.setGpioPinListenerDigital((event) -> {
				if(event.getState()==PinState.HIGH){
						System.out.println("High");
						dual.green();
					}else{
						System.out.println("Low");
						dual.red();
					}
			});
		System.out.println("Ready");
			System.in.read();
	}

}
