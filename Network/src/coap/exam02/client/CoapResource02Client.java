package coap.exam02.client;

import coap.exam01.client.*;
import java.awt.PageAttributes;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapResponse;
import org.eclipse.californium.core.coap.CoAP;
import org.eclipse.californium.core.coap.MediaTypeRegistry;
import org.json.JSONObject;

public class CoapResource02Client {

	//Field
	private CoapClient coapClient;

	//Constructor
	public CoapResource02Client() {
		coapClient = new CoapClient();
		//coapClient.useNONs();
		coapClient.useCONs(); //디폴트값
	}

	//Method
	public String get(int angle) {
		String queryString = "kind=ultrasonicsensor&angle=" + angle;
		System.out.println(queryString);
		coapClient.setURI("coap://192.168.3.52:5683/resource02?" + queryString); // 어떤 리소스를 요청하는지 리소스 식별명으로 부른다.//default값이 5683이라 생략가능
		CoapResponse response = coapClient.get(); //겟방식으로 요청 coapClient.post(); 하면 포스트로 요청 //handleGet메소드 요청
		if (response == null) {
			return get(angle);
		} else {
			if (response.getCode() == CoAP.ResponseCode.CONTENT) { // 응답코드 번호 (정상응답) //
				return response.getResponseText();
			} else {
				return get(angle);
			}
		}
	}

	public String post(int angle) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("kind", "ultrasonicsensor");
		jsonObject.put("angle", angle);
		String json=jsonObject.toString();
		System.out.println(json);
		//
		//String queryString = "kind=ultrasonicsensor&angle=" + angle;
		//System.out.println(queryString);
		coapClient.setURI("coap://192.168.3.52:5683/resource02");
		//CoapResponse response = coapClient.post(queryString, MediaTypeRegistry.TEXT_PLAIN); //
		CoapResponse response = coapClient.post(json, MediaTypeRegistry.APPLICATION_JSON); //
		if (response == null) {
			return get(angle);
		} else {
			if (response.getCode() == CoAP.ResponseCode.CONTENT) { // 응답코드 번호 (정상응답) //
				return response.getResponseText();
			} else {
				return get(angle);
			}
		}
		
	}

	//클라이언트를 종료시키는 메소드
	public void shutdown() {
		coapClient.shutdown();
	}

	public static void main(String[] args) {
		CoapResource02Client client = new CoapResource02Client();
			while(true){
		for(int i=10;i<180;i+=10){
			String text=client.post(i);
		System.out.println(i+"각도,거리:"+text);
		}
		for(int i=170;i>0;i-=10){
			String text=client.post(i);
		System.out.println(i+"각도,거리:"+text);
		
		}
		}
	//	client.shutdown();
		/*
		while(true){
		for(int i=10;i<180;i+=10){
			String text=client.get(i);
		System.out.println(i+"각도,거리:"+text);
		}
		for(int i=170;i>0;i--){
			String text=client.get(i);
		System.out.println(i+"각도,거리:"+text);
		
		}
		}
	//	client.shutdown();
		 */
	}

}
