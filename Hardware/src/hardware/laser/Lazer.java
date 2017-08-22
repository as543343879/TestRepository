package hardware.laser;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

public class Lazer {
	private GpioPinDigitalOutput outPin;
	private GpioPinDigitalOutput greenPin;
	
	public Lazer(Pin pin){
		//GpioController 객체 얻기
		GpioController gpioController=GpioFactory.getInstance();
		//디지털 출력핀 생성
		outPin=gpioController.provisionDigitalOutputPin(pin);
		//애플리케이션이 종료될 때 출력모드를 해제하고, 핀의 출력을 LOW로 한다.
		outPin.setShutdownOptions(true,PinState.LOW);
		
	}
	
	public void out(){
		outPin.low();
	}
	
	
	public static void main(String[] args) throws InterruptedException {
		Lazer test=new Lazer(RaspiPin.GPIO_25);
		while(true){
		test.out();
		Thread.sleep(3000);
		
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
