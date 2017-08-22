package coap.exam04.server;

import com.pi4j.io.gpio.RaspiPin;
import hardware.converter.PCF8591;
import hardware.sensor.GasSensor;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.server.resources.CoapExchange;
import org.json.JSONObject;

//관찰기능예제(받은 값이 30이상일 때 관찰 요청한 클라이언트에게 통보해주기)
public class CoapResource06 extends CoapResource {

	//Field
	private double value;
	private int state;
	//Constructor

	public CoapResource06() {
		//식별이름
		super("resource06");
		setObservable(true);
		getAttributes().setObservable();
		PCF8591 pcf8591 = new PCF8591(0x48, PCF8591.AIN2);
		GasSensor gas = new GasSensor(pcf8591, RaspiPin.GPIO_23);
		Thread thread = new Thread() {
			@Override
			public void run() {
				while (true) {
					try {
						value = gas.getValue();
					} catch (Exception ex) {
					}
					//System.out.println(value);
					
					if (value > 220) {
						if(state!=2){
						state = 2;
						changed();
						}else{}
						
					}
					if (value < 200) {
						if (state == 2) {
							state = 1;
							changed();

						} else {
						}
					}
					try {
						Thread.sleep(1000);
					} catch (InterruptedException ex) {
					}
				}

			}

		};
		thread.start();

	}

	//Method
	@Override
	public void handleGET(CoapExchange exchange) {
		System.out.println("10");
		if (state == 2) {
			exchange.respond("가스검출");
		} else if(state == 1) {
			exchange.respond("정상상태");
		}
	}

	@Override
	public void handlePOST(CoapExchange exchange) {

	}

}
