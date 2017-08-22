package hardware.sensor;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UltrasonicSensor2 {

	private GpioPinDigitalOutput trigPin;
	private GpioPinDigitalInput echoPin;

	private int previousDistance;
	private boolean again;
	private int count;

	public UltrasonicSensor2(Pin trigPinNo, Pin echoPinNo) {
		GpioController gpioController = GpioFactory.getInstance();
		trigPin = gpioController.provisionDigitalOutputPin(trigPinNo, PinState.LOW);
		trigPin.setShutdownOptions(true, PinState.LOW);
		echoPin = gpioController.provisionDigitalInputPin(echoPinNo); //트리거가 high가 되면 이것도 같이 high가 된다.
		echoPin.setShutdownOptions(true, PinState.LOW);

	}

	public synchronized int getDistance() {

		//초음파 발신 시간 변수와 수신 시간 변수 선언
		double start = 0;
		double end = 0;
		
		//초기화
		trigPin.high();
		try {
			Thread.sleep(0, 5000);
		} catch (InterruptedException ex) {
		}
		trigPin.low();

		//초음파를 10마이크로 초 동안 발생
		trigPin.high(); //트리거발생
		try {
			Thread.sleep(0, 10000);
		} catch (InterruptedException ex) {
		}
		trigPin.low();
		count = 0;
		//echoPin이 High가 될 때까지 기다림
		while (echoPin.isLow()) {
			count++;
			if (count > 50000) {
				return getDistance(); //5만번 돌았다는것은 송수신에 문제가 있는 것이라 간주하여 
			}

		}
		//발신 시간을 저장
		start = System.nanoTime();
		count = 0;
		//echoPin이 Low가 될때까지 기다림
		while (echoPin.isHigh()) {
			count++;
			if (count > 50000) {
				return getDistance(); //5만번 돌았다는것은 송수신에 문제가 있는 것이라 간주하여 
			}

		}

		end = System.nanoTime();
		//편도 시간(sec)
		double seconds = (end - start) / 1000000000 / 2;
		int distance = (int) (seconds *(33130+60.6*25));
		//100이상 튀는 값이 있을 경우 다시 측정
		if(again==false&&Math.abs(previousDistance-distance)>100){
			again=true;
			getDistance();//dummy = 가짜로 한번더 읽어보는것
			getDistance();
			distance =getDistance(); //실제측정값
		}else{
			again=false;
		}
		//초과값 검사(2cm~400cm)
		if(distance<2){
			distance=2;
		}else if(distance>400){
			distance=400;
		}
		
		previousDistance=distance;
		return distance;
	}

	public static void main(String[] args) throws InterruptedException {
		UltrasonicSensor2 test = new UltrasonicSensor2(RaspiPin.GPIO_28, RaspiPin.GPIO_29);
		while (true) {
			int distance = test.getDistance();
			System.out.println("거리(cm)" + distance);
			Thread.sleep(1000);
		}
	}

}
