package sensingcar.coap.server.resource;

import com.pi4j.io.gpio.RaspiPin;
import hardware.motor.PCA9685;
import hardware.motor.SG90ServoPCA9685Duration;
import hardware.sensor.UltrasonicSensor;
import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.server.resources.CoapExchange;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UltrasonicSensorResource extends CoapResource {

	private static final Logger logger = LoggerFactory.getLogger(UltrasonicSensorResource.class);
	private PCA9685 pca9685;
	private SG90ServoPCA9685Duration servoMotor;
	private UltrasonicSensor ultrasonicSensor;
	private final int minAngle = 50;
	private final int maxAngle = 140;
	private int currAngle;
	private int currDistance;

	//지속적인 값보내주는것
	public UltrasonicSensorResource() throws Exception {
		super("ultrasonicsensor");
		setObservable(true); //옵저버기능가능하도록 활성화
		getAttributes().setObservable(); // 클라이언트가 알수있도록

		pca9685 = PCA9685.getInstance();
		servoMotor = new SG90ServoPCA9685Duration(pca9685, PCA9685.PWM_11);
		ultrasonicSensor = new UltrasonicSensor(RaspiPin.GPIO_28, RaspiPin.GPIO_29);
		setAngle(90);//초기화

		//계속 전송해줄수 있는 스레드
		Thread thread = new Thread() {
			@Override
			public void run() {
				int count = 0;
				while (true) {
					try {

						currDistance = ultrasonicSensor.getDistance();
						if (count == 2) {
							changed(); //핸들겟이 자동실행
							count = 0;
						}
						Thread.sleep(500);
						count++;
					} catch (Exception e) {
						logger.info(e.toString());
					}
				}
			}
		};
		thread.start();

	}

	private void setAngle(int angle) {
		if (angle < minAngle) {
			angle = minAngle;
		}
		if (angle > maxAngle) {
			angle = maxAngle;
		}
		currAngle = angle;
		servoMotor.setAngle(angle);
		try {
			Thread.sleep(500);
		} catch (Exception e) {
		}
	}

	@Override
	public void handleGET(CoapExchange exchange) {

		JSONObject responseJsonObject = new JSONObject();
		responseJsonObject.put("angle", String.valueOf(currAngle));
		responseJsonObject.put("distance", String.valueOf(currDistance));
		String responseJson = responseJsonObject.toString();
		exchange.respond(responseJson);

	}

	@Override
	public void handlePOST(CoapExchange exchange) {
		//{"command":"change","angle":"90"} 이런식으로
		//{"command":"status"} 이런식으로 요청

		try {
			String requestJson = exchange.getRequestText();
			JSONObject requestJsonObject = new JSONObject(requestJson);
			String command = requestJsonObject.getString("command");
			if (command.equals("change")) {
				int angle = Integer.parseInt(requestJsonObject.getString("angle"));
				setAngle(angle);
				Thread.sleep(1000);
			} else if (command.equals("status")) {

			}
			JSONObject responseJsonObject = new JSONObject();
			responseJsonObject.put("result", "success");
			responseJsonObject.put("angle", String.valueOf(currAngle));
			responseJsonObject.put("distance", String.valueOf(currDistance));
			String responseJson = responseJsonObject.toString();
			exchange.respond(responseJson);
		} catch (Exception e) {
			JSONObject responseJsonObject = new JSONObject();
			responseJsonObject.put("result", "fail");
			String responseJson = responseJsonObject.toString();
			exchange.respond(responseJson);
		}

	}

}
