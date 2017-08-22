package sensingcar.coap.server.resource;

import com.pi4j.io.gpio.RaspiPin;
import hardware.motor.DCMotor;
import hardware.motor.PCA9685;
import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.server.resources.CoapExchange;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BackTireResource extends CoapResource {
	
	private static final Logger logger=LoggerFactory.getLogger(BackTireResource.class);

	private DCMotor dcMoterLeft;
	private DCMotor dcMoterRight;
	private PCA9685 pca9685;
	private final int maxStep = 4095;
	private final int minStep = 0;
	private int currStep;
	private String currDirection;

	public BackTireResource() throws Exception {
		super("backtire");
		pca9685 = PCA9685.getInstance();
		dcMoterLeft = new DCMotor(RaspiPin.GPIO_00, RaspiPin.GPIO_01, pca9685, PCA9685.PWM_05);
		dcMoterRight = new DCMotor(RaspiPin.GPIO_02, RaspiPin.GPIO_03, pca9685, PCA9685.PWM_04);
		forward();
	}

	public void forward() {
		dcMoterLeft.forward();
		dcMoterRight.forward();
		currDirection = "forward";
	}

	public void backward() {
		dcMoterLeft.backward();
		dcMoterRight.backward();
		currDirection = "backward";
	}

	public void setSpeed(int step) {
		if (step < minStep) {
			step = minStep;
		}
		if (step > maxStep) {
			step = maxStep;
		}
		currStep = step;
		dcMoterLeft.setSpeed(currStep);
		dcMoterRight.setSpeed(currStep);

	}

	public void stop() {
		dcMoterLeft.stop();
		dcMoterRight.stop();
	}

	@Override
	public void handleGET(CoapExchange exchange) {

	}

	@Override
	public void handlePOST(CoapExchange exchange) {
		//{"command":"change","direction":"forward","speed":"1000"} 이런식으로 올수도 있고 
		//{"command":"status"} 이렇게 올수 있음
		try{
		String requestJson = exchange.getRequestText();
		JSONObject requestJsonObject = new JSONObject(requestJson);
		String command = requestJsonObject.getString("command");
		if (command.equals("change")) {
			String reqDirection = requestJsonObject.getString("direction");
			int reqSpeed = Integer.parseInt(requestJsonObject.getString("speed"));
			if (reqDirection.equals("forward")) {
				forward();
			} else if (reqDirection.equals("backward")) {
				backward();
			}
			setSpeed(reqSpeed);
		}else if (command.equals("status")) {

		}
		JSONObject responseJsonObject = new JSONObject();
		responseJsonObject.put("result", "success");
		responseJsonObject.put("direction", currDirection);
		responseJsonObject.put("speed", String.valueOf(currStep));
		String responseJson = responseJsonObject.toString();
		exchange.respond(responseJson);
		}catch(Exception e){
		JSONObject responseJsonObject = new JSONObject();
		responseJsonObject.put("result", "fail");
		String responseJson = responseJsonObject.toString();
		exchange.respond(responseJson);
		}
	}

}
