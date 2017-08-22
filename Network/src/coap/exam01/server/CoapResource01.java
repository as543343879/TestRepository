
package coap.exam01.server;
//서버의 관리대상

import hardware.converter.PCF8591;
import hardware.sensor.ThermistorSensor;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.server.resources.CoapExchange;

public class CoapResource01 extends CoapResource{

	//Field
	private ThermistorSensor thermistorSensor;
	//Constructor
	public CoapResource01() {
		super("resource01");   //리소스 식별명
		PCF8591 pcf8591=new PCF8591(0x48,PCF8591.AIN1); //(장치번호,ADC의 몇번에 연결되어 있는지)
		thermistorSensor= new ThermistorSensor(pcf8591);
	}
	//Method
	//요청처리메소드 요청은 서버에서 받고 처리는 각각의 리소스에서 한다.

	@Override
	public void handleGET(CoapExchange exchange) {    //클라이언트가 겟방식으로 요청하였을때 실행되는 메소드
		double value;
		try {
			value = thermistorSensor.getValue();
			exchange.respond("온도: 섭씨 : "+value+"도");    //보내줄 문자열
		} catch (Exception ex) {
		}
		
	}
	
	

}
