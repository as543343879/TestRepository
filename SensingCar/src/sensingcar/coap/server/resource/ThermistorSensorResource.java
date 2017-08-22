package sensingcar.coap.server.resource;

import hardware.converter.PCF8591;
import hardware.sensor.ThermistorSensor;
import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.coap.CoAP;
import org.eclipse.californium.core.server.resources.CoapExchange;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ThermistorSensorResource extends CoapResource {

	private static final Logger logger = LoggerFactory.getLogger(ThermistorSensorResource.class);
	private PCF8591 pcf8591;
	private ThermistorSensor thermistorSensor;
	private double currTemperature;

	//지속적인 값보내주는것
	public ThermistorSensorResource() throws Exception {
		super("thermistorsensor");
		setObservable(true); //옵저버기능가능하도록 활성화
		getAttributes().setObservable(); // 클라이언트가 알수있도록
		setObserveType(CoAP.Type.NON);

		pcf8591 = new PCF8591(0x48, PCF8591.AIN1);
		thermistorSensor = new ThermistorSensor(pcf8591);

		//계속 전송해줄수 있는 스레드
		Thread thread = new Thread() {
			@Override
			public void run() {
				while (true) {
					try {
						currTemperature = thermistorSensor.getValue();
						changed();
						Thread.sleep(500);
					} catch (Exception e) {
						logger.info(e.toString());
					}
				}

			}
		};
		thread.start();

	}

	@Override
	public void handleGET(CoapExchange exchange) {

		JSONObject responseJsonObject = new JSONObject();
		responseJsonObject.put("temperature", String.valueOf(currTemperature));
		String responseJson = responseJsonObject.toString();
		exchange.respond(responseJson);

	}

	@Override
	public void handlePOST(CoapExchange exchange) {
		//{"command":"status"} 이런식으로 요청

		try {
			String requestJson = exchange.getRequestText();
			JSONObject requestJsonObject = new JSONObject(requestJson);
			String command = requestJsonObject.getString("command");
			if (command.equals("status")) {

			}
			JSONObject responseJsonObject = new JSONObject();
			responseJsonObject.put("result", "success");
			responseJsonObject.put("temperature", String.valueOf(currTemperature));
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
