package hardware.led;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

public class RgbLedDigital {

	private GpioPinDigitalOutput redPin;
	private GpioPinDigitalOutput greenPin;
	private GpioPinDigitalOutput bluePin;

	public RgbLedDigital(Pin redPinNo, Pin greenPinNo, Pin bluePinNo) {
		//GpioController 객체 얻기
		GpioController gpioController = GpioFactory.getInstance();
		//디지털 출력핀 생성
		redPin = gpioController.provisionDigitalOutputPin(redPinNo, PinState.HIGH);
		//애플리케이션이 종료될 때 출력모드를 해제하고, 핀의 출력을 LOW로 한다.
		redPin.setShutdownOptions(true, PinState.LOW);

		greenPin = gpioController.provisionDigitalOutputPin(greenPinNo, PinState.HIGH);
		greenPin.setShutdownOptions(true, PinState.LOW);

		bluePin = gpioController.provisionDigitalOutputPin(bluePinNo, PinState.HIGH);
		bluePin.setShutdownOptions(true, PinState.LOW);
	}

	public void rgb(boolean red, boolean green, boolean blue) {

		if (red) {
			redPin.low();
		} else {
			redPin.high();
		}
		if (green) {
			greenPin.low();
		} else {
			greenPin.high();
		}
		if (blue) {
			bluePin.low();
		} else {
			bluePin.high();
		}
	}

	public void red(boolean state) {
		if (state) {
			bluePin.low();
		} else {
			bluePin.high();
		}
	}

	public void green(boolean state) {
		if (state) {
			bluePin.low();
		} else {
			bluePin.high();
		}
	}

	public void blue(boolean state) {
		if (state) {
			bluePin.low();
		} else {
			bluePin.high();
		}
	}

	public void greenOnOff(boolean state) {

	}

	public static void main(String[] args) throws InterruptedException {
		RgbLedDigital test = new RgbLedDigital(RaspiPin.GPIO_04, RaspiPin.GPIO_05, RaspiPin.GPIO_06);
		while (true) {
			test.rgb(true,false,false);
			Thread.sleep(50);
			test.rgb(false,true,false);
			Thread.sleep(100);
			test.rgb(false,false,true);
			Thread.sleep(50);
			
		}

	}
}
