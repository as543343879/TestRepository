
package hardware.sensor;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;
import hardware.converter.PCF8591;

public class GasSensor {
	
	//Field
	private PCF8591 pcf8591; //PCF사용을 위해 선언
	private GpioPinDigitalInput gpioPinDigitalInput;//이벤트처리를위한 필드선언

	//Constructor
	public GasSensor(PCF8591 pcf8591, Pin digitalPinNo) {
		this.pcf8591 = pcf8591; //pcf 8591 초기화
		GpioController gpioController = GpioFactory.getInstance();
		gpioPinDigitalInput = gpioController.provisionDigitalInputPin(digitalPinNo);
		gpioPinDigitalInput.setShutdownOptions(true, PinState.LOW); //해제하면서 low상태로 만들겠다.
	}

	public void setGpioPinListenerDigital(GpioPinListenerDigital listener) {
		gpioPinDigitalInput.addListener(listener);
	}

	//Method
	//값 리턴을 위한 메소드
	public double getValue() throws Exception {
		int analogVal = pcf8591.analogRead();
		return analogVal;
	}

	public static void main(String[] args) throws Exception {
		PCF8591 pcf8591 = new PCF8591(0x48, PCF8591.AIN2); //PCF사용을 위한 객체생성
		GasSensor test = new GasSensor(pcf8591, RaspiPin.GPIO_23);
		//방법1: Digital 핀의 상태를 이용
		
		test.setGpioPinListenerDigital(new GpioPinListenerDigital() {
			@Override
			public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
				//문제가 생겼을때를 대비한 if문
				if(event.getState()==PinState.LOW){
					System.out.println("**************가스 검출");
					try {
					//	System.out.println("현재상태값!!!!!!!!!!!!!!!!!"+test.getValue());
					} catch (Exception ex) {
					}
				}else{
					System.out.println("**************정상 상태");
					try {
					//	System.out.println("현재상태값!!!!!!!!!!!!!!!!!"+test.getValue());
					} catch (Exception ex) {
					}
				}
			}
		});

		//방법2: Analog 값 이용
		while (true) {
			double value = test.getValue();
			System.out.println(value);
			if(value<100){
				//Analog 값을 이용해서 처리
			}
			Thread.sleep(1000);
		}

//System.in.read();
	}

}
