package coap.exam04.client;

import java.io.IOException;
import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapHandler;
import org.eclipse.californium.core.CoapObserveRelation;
import org.eclipse.californium.core.CoapResponse;
//관찰요청 클라이언트 생성
public class CoapResource04Client {

	//Field
	private CoapClient coapClient;
	private CoapObserveRelation coapObserveRelation;

	//Constructor
	public CoapResource04Client() {
		coapClient = new CoapClient();
	}
	//Method
//관찰요청 클라이언트는 요청을 하지 않음
	public void observe() {
		coapClient.setURI("coap://192.168.3.52/resource04");
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
		//관찰요청 해제 ( 요청 해제 해주지 않으면 서버는 계속 해제한지 모르기 때문)
		coapObserveRelation.proactiveCancel();
		coapClient.shutdown();
	}
	
	public static void main(String[] args) throws IOException {
		CoapResource04Client client=new CoapResource04Client();
		client.observe();
		System.in.read();
		client.shutdown();
	}

}
