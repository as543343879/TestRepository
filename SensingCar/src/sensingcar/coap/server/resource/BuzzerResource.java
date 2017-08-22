package sensingcar.coap.server.resource;

import com.pi4j.io.gpio.RaspiPin;
import hardware.buzzer.ActiveBuzzer;
import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.server.resources.CoapExchange;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BuzzerResource extends CoapResource {
private static final Logger logger=LoggerFactory.getLogger(BuzzerResource.class);
	private ActiveBuzzer buzzer;
	private String currstatus;
	private static BuzzerResource instance;
	


	public BuzzerResource() throws Exception {
		super("buzzer");
		instance=this;
		buzzer=new ActiveBuzzer(RaspiPin.GPIO_24);
		off();
		
	}
	
	public static BuzzerResource getInstance(){
		
		return instance;
	}
	
	public void on(){
		buzzer.on();
		currstatus="on";
	}
		public void off(){
		buzzer.off();
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
