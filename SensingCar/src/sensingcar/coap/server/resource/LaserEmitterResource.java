package sensingcar.coap.server.resource;

import com.pi4j.io.gpio.RaspiPin;
import hardware.laser.LaserEmitter;
import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.server.resources.CoapExchange;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LaserEmitterResource extends CoapResource {
private static final Logger logger=LoggerFactory.getLogger(LaserEmitterResource.class);
	private LaserEmitter laserEmitter;
	private String currstatus;
	


	public LaserEmitterResource() throws Exception {
		super("laseremitter");
		laserEmitter=new LaserEmitter(RaspiPin.GPIO_25);
		off();
		
	}
	
	public void on(){
		laserEmitter.shot();
		currstatus="on";
	}
		public void off(){
		laserEmitter.stop();
		currstatus="off";
	}

	@Override
	public void handleGET(CoapExchange exchange) {

	}

	@Override
	public void handlePOST(CoapExchange exchange) {
	//{"command":"change","status":"on"} 이런식으로
	//{"command":"status"} 이런식으로 요청
	
		try{
		String requestJson = exchange.getRequestText();
		JSONObject requestJsonObject = new JSONObject(requestJson);
		String command = requestJsonObject.getString("command");
		if (command.equals("change")) {
			String status= requestJsonObject.getString("status");
			if(status.equals("on")){
				on();
			}else if(status.equals("off")){
				off();
			}
		}else if (command.equals("status")) {

		}
		JSONObject responseJsonObject = new JSONObject();
		responseJsonObject.put("result", "success");
		responseJsonObject.put("status",currstatus );
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
