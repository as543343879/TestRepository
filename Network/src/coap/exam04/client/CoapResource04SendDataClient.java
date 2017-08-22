package coap.exam04.client;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.coap.MediaTypeRegistry;
import org.json.JSONObject;

public class CoapResource04SendDataClient {

	//Field
	private CoapClient coapClient;

	//Constructor
	public CoapResource04SendDataClient() {
		coapClient = new CoapClient();
	}
	//Method
	//클라이언트가 sever에게 지속적으로 post방식으로 데이터를 전달해주는 메소드
	public void post() {
		coapClient.setURI("coap://192.168.3.52/resource04");
		Random random = new Random();
		while (true) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("value", random.nextInt(50));
			String json=jsonObject.toString();
			coapClient.post(json, MediaTypeRegistry.APPLICATION_JSON);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException ex) {
			}
		}
	}
	
	public void shutdown(){
		coapClient.shutdown();
	}
	
	public static void main(String[] args) {
		CoapResource04SendDataClient client=new CoapResource04SendDataClient();
		client.post();
		client.shutdown();
	}

}
