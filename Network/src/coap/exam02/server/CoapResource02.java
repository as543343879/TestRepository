package coap.exam02.server;

import com.pi4j.io.gpio.RaspiPin;
import hardware.motor.PCA9685;
import hardware.motor.SG90ServoPCA9685Duration;
import hardware.sensor.UltrasonicSensor;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.server.resources.CoapExchange;
import org.json.JSONObject;

public class CoapResource02 extends CoapResource {

	//Field
	private SG90ServoPCA9685Duration servoMotor;
	private PCA9685 pca9685;
	private UltrasonicSensor ultrasonicSensor;
	private int distance;

	//Constructor
	public CoapResource02() {
		super("resource02");
		try {
			pca9685 = PCA9685.getInstance();
		} catch (Exception ex) {
		}
		servoMotor = new SG90ServoPCA9685Duration(pca9685, PCA9685.PWM_11);

		ultrasonicSensor = new UltrasonicSensor(RaspiPin.GPIO_28, RaspiPin.GPIO_29);

		Thread thread = new Thread() {
			@Override
			public void run() {
				while (true) {
					distance = ultrasonicSensor.getDistance();
					try {
						Thread.sleep(500);
					} catch (InterruptedException ex) {
					}

				}
			}
		};
		thread.start();
	}

	//Method
	@Override
	public void handleGET(CoapExchange exchange) {    //클라이언트가 겟방식으로 요청하였을때 실행되는 메소드
		String kind = exchange.getRequestOptions().getUriQuery().get(0).split("=")[1];
		String angle = exchange.getRequestOptions().getUriQuery().get(1).split("=")[1];
		//System.out.println("key1 :" + key1);
		//System.out.println("key2 :" + key2);

		if (kind.equals("ultrasonicsensor")) {
			servoMotor.setAngle(Integer.parseInt(angle));
			try {
				Thread.sleep(400);
			} catch (InterruptedException ex) {
			}
			exchange.respond(String.valueOf(distance));
		} else {
			exchange.respond("ok");

		}

	}

	@Override
	public void handlePOST(CoapExchange exchange) {
		String json = exchange.getRequestText();
		JSONObject jsonObject = new JSONObject(json);
		String kind = jsonObject.getString("kind");
		int angle = jsonObject.getInt("angle");
		if (kind.equals("ultrasonicsensor")) {
			servoMotor.setAngle(angle);
			try {
				Thread.sleep(400);
			} catch (InterruptedException ex) {
			}
			exchange.respond(String.valueOf(distance));
		} else {
			exchange.respond("ok");

		}
		/*
		String queryString = exchange.getRequestText();
		String kind=queryString.split("&")[0].split("=")[1];
		String angle=queryString.split("&")[1].split("=")[1];
		
		
		System.out.println(queryString);
		System.out.println(kind);
		System.out.println(angle);
		 */
	}

}
