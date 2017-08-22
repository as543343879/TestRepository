package coap.exam04.client;

import java.io.IOException;
import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapHandler;
import org.eclipse.californium.core.CoapObserveRelation;
import org.eclipse.californium.core.CoapResponse;
//관찰요청 클라이언트 생성
public class CoapResource05Client {

	//Field
	private CoapClient coapClient;
	private CoapObserveRelation coapObserveRelation;

	//Constructor
	public CoapResource05Client() {
		coapClient = new CoapClient();
	}
	//Method
	public void observe() {
		coapClient.setURI("coap://192.168.3.52/resource05");
		coapObserveRelation =coapClient.observe(new CoapHandler() {
			//통보메세지가 왔을 때 실행
			@Override
			public void onLoad(CoapResponse response) {
				String text=response.getResponseText();
				System.out.println(text);
			}
			//네트워크 통신이 끊겼을때
			@Override
			public void onError() {
			}
		});
	
	}
	
	public void shutdown(){
		coapObserveRelation.proactiveCancel();
		coapClient.shutdown();
	}
	
	public static void main(String[] args) throws IOException {
		CoapResource05Client client=new CoapResource05Client();
		client.observe();
		System.in.read();
		client.shutdown();
	}

}
