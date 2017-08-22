package hardware.led;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinPwmOutput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import java.io.IOException;

public class RgbLedPWM {
//Field

	private GpioPinPwmOutput redPin;
	private GpioPinPwmOutput greenPin;
	private GpioPinPwmOutput bluePin;
	private int[] currColorSet= new int[3];

//Constructor
	public RgbLedPWM(Pin redPinNo,Pin greenPinNo,Pin bluePinNo) {
		GpioController gpioController = GpioFactory.getInstance();
		//소프트웨어 PWM 출력 핀 객체 생성
		redPin = gpioController.provisionSoftPwmOutputPin(redPinNo);
		greenPin = gpioController.provisionSoftPwmOutputPin(greenPinNo);
		bluePin = gpioController.provisionSoftPwmOutputPin(bluePinNo);
		
		
		redPin.setShutdownOptions(false,PinState.HIGH);
		greenPin.setShutdownOptions(false,PinState.HIGH);
		bluePin.setShutdownOptions(false,PinState.HIGH);
		//제어단계를 255단계로 변경
		redPin.setPwmRange(255);
		greenPin.setPwmRange(255);
		bluePin.setPwmRange(255);
		//발광하지 않도록 초기화
		redPin.setPwm(255);
		greenPin.setPwm(255);
		bluePin.setPwm(255);
		ledColorSet(0,0,0);
	}
//Method

	public void red(int value) {
		value = 255 - value;
		redPin.setPwm(value);
	}

	public void green(int value) {
		value = 255 - value;
		greenPin.setPwm(value);
	}

	public void blue(int value) {
		value = 255 - value;
		bluePin.setPwm(value);
	}

	public void ledColorSet(int reds, int greens, int blues) {
		currColorSet[0]=reds;
		currColorSet[1]=greens;
		currColorSet[2]=blues;
						
		reds=255-reds;
		greens=255-greens;
		blues=255-blues;
		redPin.setPwm(reds);
		greenPin.setPwm(greens);
		bluePin.setPwm(blues);
	}

	public int[] getCurrColorSet() {
		return currColorSet;
	}

	public static void main(String[] args) throws IOException, InterruptedException {
		RgbLedPWM test = new RgbLedPWM(RaspiPin.GPIO_00,RaspiPin.GPIO_02,RaspiPin.GPIO_03);
		
		for (int i = 0; i < 255; i++) {
			for (int j = 0; j < 255; j++) {
				for (int k = 0; k < 255; k++) {
					test.ledColorSet(i,j,k);
					
					Thread.sleep(3);
				}
			}
		}

	//	test.ledColorSet(255, 0, 0);
	//	Thread.sleep(1000);
	//	test.ledColorSet(0, 255, 0);
	//	Thread.sleep(1000);
//		test.ledColorSet(0,0,255);
	//Thread.sleep(100000);
	//	test.ledColorSet(0, 0, 0);
		
		System.out.println("ready");
		System.in.read();
	}
}
