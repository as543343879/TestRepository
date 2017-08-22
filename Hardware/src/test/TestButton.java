
package test;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import hardware.button.Button;
import hardware.led.RgbLedDigital;
import java.io.IOException;
public class TestButton {
	static boolean red=false;
   static boolean green=false;
	static boolean blue=false;
	public static void main(String[] args) throws IOException {
		
		Button button1 = new Button(RaspiPin.GPIO_00);
		Button button2 = new Button(RaspiPin.GPIO_01);
		Button button3 = new Button(RaspiPin.GPIO_02);
		
		RgbLedDigital rgb = new RgbLedDigital(RaspiPin.GPIO_27, RaspiPin.GPIO_28,RaspiPin.GPIO_29);
		
		button1.setGpioPinListenerDigital((event) -> {
				if(event.getState()==PinState.LOW){
						System.out.println("High");
						red=true;
						rgb.rgb(red, green, blue);
						
					}else {
						red=false;
						rgb.rgb(red, green, blue);
					}
			});
		button2.setGpioPinListenerDigital((event) -> {
				if(event.getState()==PinState.LOW){
					System.out.println("High");
						green=true;
						rgb.rgb(red, green, blue);
					}else{
						green=false;
						rgb.rgb(red, green, blue);
					}
			});
		button3.setGpioPinListenerDigital((event) -> {
				if(event.getState()==PinState.LOW){
						System.out.println("High");
						blue=true;
						rgb.rgb(red, green, blue);
						
					}else{
						blue=false;
						rgb.rgb(red, green, blue);
					}
			});
		
		
		System.out.println("Ready");
			System.in.read();
	}


}
