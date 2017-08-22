package coap.exam03.client;

import java.io.IOException;
import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapHandler;
import org.eclipse.californium.core.CoapResponse;
import org.eclipse.californium.core.coap.CoAP;
import org.eclipse.californium.core.coap.MediaTypeRegistry;
import org.json.JSONObject;

public class CoapResource03AsyncClient {

	//Field
	private CoapClient coapClient;

	//Constructor
	public CoapResource03AsyncClient() {
		coapClient = new CoapClient();
	}

	//Method
	public void get(int angle) {
		String queryString = "kind=ultrasonicsensor&angle=" + angle;
		coapClient.setURI("coap://192.168.3.52:5683/resource02?" + queryString); // 어떤 리소스를 요청하는지 리소스 식별명으로 부른다.//default값이 5683이라 생략가능

		coapClient.get(new CoapHandler() {
			//응답이 왔을 때 실행된다.
			@Override
			public void onLoad(CoapResponse response) {

				if (response.getCode() == CoAP.ResponseCode.CONTENT) { // 응답코드 번호 (정상응답) //
					String text = response.getResponseText();
					System.out.println(angle + "각도거리:" + text);
				}
			}

			//에러가 발생했을 때 실행된다. (네트워크 통신이 단절되었을 때)
			@Override
			public void onError() {

			}
		});

	}

	public void post(int angle) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("kind", "ultrasonicsensor");
		jsonObject.put("angle", angle);
		String json = jsonObject.toString();
		
		
		coapClient.setURI("coap://192.168.3.52:5683/resource02");
		
		coapClient.post(new CoapHandler() {
			@Override
			public void onLoad(CoapResponse response) {
					if (response.getCode() == CoAP.ResponseCode.CONTENT) { // 응답코드 번호 (정상응답) //
					String text = response.getResponseText();
					System.out.println(angle + "각도거리:" + text);
				}
			}

			@Override
			public void onError() {
				
			}
		},json, MediaTypeRegistry.APPLICATION_JSON);
	

	}

	//클라이언트를 종료시키는 메소드
	public void shutdown() {
		coapClient.shutdown();
	}

	public static void main(String[] args) throws Exception {
		CoapResource03AsyncClient client = new CoapResource03AsyncClient();
		for(int i=10;i<170;i+=1){
		client.post(i);
		Thread.sleep(10);
		}
		System.in.read();
		client.shutdown();
	
	}

}
