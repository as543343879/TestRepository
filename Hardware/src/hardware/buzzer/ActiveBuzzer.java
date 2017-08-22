package hardware.buzzer;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;


public class ActiveBuzzer {
	
	private GpioPinDigitalOutput buzzerPin;
	private String status="off";
	
	public ActiveBuzzer(Pin buzzerPinNo){
		GpioController gpioController=GpioFactory.getInstance();
		buzzerPin=gpioController.provisionDigitalOutputPin(buzzerPinNo);
		buzzerPin.setShutdownOptions(false,PinState.HIGH); //true일때는 입출력상태를 해제한다. false상태는 입출력을 그대로 두겠다. 이때의 상태는 high
	}
	
	public void on(){
		buzzerPin.low();
		status="on";
	}
	
	public void off(){
		buzzerPin.high();
		status="off";
	}

	public String getStatus() {
		return status;
	}
	public static void main(String[] args) throws InterruptedException {
		ActiveBuzzer test=new ActiveBuzzer(RaspiPin.GPIO_24);
		while(true){
			test.on();
			Thread.sleep(500);
			test.off();
			Thread.sleep(300);
		}
	}
}
