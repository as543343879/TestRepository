
package coap.exam01.client;

import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapResponse;
import org.eclipse.californium.core.coap.CoAP;

public class CoapResource01Client {
	//Field
	private CoapClient coapClient;
	
	//Constructor
	public CoapResource01Client(){
		coapClient = new CoapClient();
	}
	
	//Method
	public String get(){
		coapClient.setURI("coap://192.168.3.52:5683/resource01"); // 어떤 리소스를 요청하는지 리소스 식별명으로 부른다.//default값이 5683이라 생략가능
		CoapResponse response=coapClient.get(); //겟방식으로 rkqtdmf coapClient.post(); 하면 포스트로 요청 //handleGet메소드 요청
		if(response.getCode()==CoAP.ResponseCode.CONTENT){ // 응답코드 번호 (정상응답) //
		
			return response.getResponseText();
		}else{
			return null;
		}
	}
	
	//클라이언트를 종료시키는 메소드
	public void shutdown(){
		coapClient.shutdown();
	}
	
	public static void main(String[] args) {
		CoapResource01Client client=new CoapResource01Client();
		String text=client.get();
		System.out.println("거리"+text);
		client.shutdown();
	}

}
