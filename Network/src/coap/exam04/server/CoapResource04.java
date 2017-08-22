
package coap.exam04.server;

import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.server.resources.CoapExchange;
import org.json.JSONObject;

//관찰기능예제(받은 값이 30이상일 때 관찰 요청한 클라이언트에게 통보해주기)
public class CoapResource04 extends CoapResource{

	
	//Field
	private int value;
	//Constructor

	public CoapResource04() {
		//식별이름
		super("resource04");
		//관찰 기능 활성화
		setObservable(true);
		//관찰 기능을 제공한다는 것을 클라이언트가 알 수 있도록 설정
		getAttributes().setObservable();
		
	}

	//Method

	//관찰요청을 한 클라이언트에게 전송
	@Override
	public void handleGET(CoapExchange exchange) {
		exchange.respond("value="+value);
	}

	@Override
	public void handlePOST(CoapExchange exchange) {
		JSONObject jsonObject=new JSONObject(exchange.getRequestText());
		value=jsonObject.getInt("value");
		if(value>30){
			changed(); //handleGet()메소드를 불러 통보 보내기
		}
		exchange.respond(""); //의미없는 값이지만 응답을 주기 위해서 , 응답은 무조건 줘야한다네
	}
	
	
	
	
}
