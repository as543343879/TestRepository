
package hardware.led;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

public class DualColorLed {
	private GpioPinDigitalOutput redPin;
	private GpioPinDigitalOutput greenPin;
	
	public DualColorLed(Pin redPinNo,Pin greenPinNo){
		//GpioController 객체 얻기
		GpioController gpioController=GpioFactory.getInstance();
		//디지털 출력핀 생성
		redPin=gpioController.provisionDigitalOutputPin(redPinNo);
		//애플리케이션이 종료될 때 출력모드를 해제하고, 핀의 출력을 LOW로 한다.
		redPin.setShutdownOptions(true,PinState.LOW);
		
		
		greenPin=gpioController.provisionDigitalOutputPin(greenPinNo);
		greenPin.setShutdownOptions(true,PinState.LOW);
	}
	
	public void red(){
		redPin.high();
		greenPin.low();
	}
	
	public void green(){
		redPin.low();
		greenPin.high();
	}
	
	public static void main(String[] args) throws InterruptedException {
		DualColorLed test=new DualColorLed(RaspiPin.GPIO_22,RaspiPin.GPIO_21);
		while(true){
		test.green();
		Thread.sleep(3000);
		test.red();
		Thread.sleep(1000);
		}
		/*
		for(int i=0;i<30;i++){
			test.red();
			Thread.sleep(500);
			test.green();
			Thread.sleep(500);
		}
*/
	}

}
