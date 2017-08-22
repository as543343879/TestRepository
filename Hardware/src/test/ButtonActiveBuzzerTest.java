
package test;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import hardware.button.Button;
import hardware.buzzer.ActiveBuzzer;
import java.io.IOException;
public class ButtonActiveBuzzerTest {
	public static void main(String[] args) throws IOException {
		Button button = new Button(RaspiPin.GPIO_01);
		ActiveBuzzer buzzer=new ActiveBuzzer(RaspiPin.GPIO_00);
			button.setGpioPinListenerDigital((event) -> {
				if(event.getState()==PinState.HIGH){
						System.out.println("High");
						buzzer.off();
					}else{
						System.out.println("Low");
						buzzer.on();
					}
			});
			System.out.println("Ready");
			System.in.read();
	}

}
