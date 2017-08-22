
package coap.exam04.server;

import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.server.resources.CoapExchange;

//관찰기능예제(관찰요청 했을 시에 계속적으로 요청한 클라이언트들에게 통보를 해주는 예제)
public class CoapResource03 extends CoapResource{

	
	//Field
	private int value;
	//Constructor

	public CoapResource03() {
		//식별이름
		super("resource03");
		//관찰 기능 활성화
		setObservable(true);
		//관찰 기능을 제공한다는 것을 클라이언트가 알 수 있도록 설정
		getAttributes().setObservable();
		//테스트
		Thread thread = new Thread(){
			@Override
			public void run() {
				int i=0;
				while(true){
					value=i;
					changed();
					i++;
					try {Thread.sleep(1000);} catch (InterruptedException ex) {}
				}
			}
			
		};
		thread.start();
	}

	//Method

	//매 1초단위로 모든 관찰 요청을 한 클라이언트에게 값을 전송
	@Override
	public void handleGET(CoapExchange exchange) {
		exchange.respond("value="+value);
	}

	@Override
	public void handlePOST(CoapExchange exchange) {
		
	}
	
	
	
	
}
