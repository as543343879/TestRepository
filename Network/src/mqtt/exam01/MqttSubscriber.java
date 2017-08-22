
package mqtt.exam01;

import java.io.IOException;
import java.util.Date;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class MqttSubscriber {
	
	private MqttClient mqttClient;
	private MqttCallback mqttCallback = new MqttCallback() {
		@Override
		public void connectionLost(Throwable thrwbl) {
			
		}

		@Override
		public void messageArrived(String string, MqttMessage mm) throws Exception {
			String text= new String(mm.getPayload()); //바이트배열을 스트링으로 만들어냄
			System.out.println("messageArrived :"+text);
		}

		@Override
		public void deliveryComplete(IMqttDeliveryToken imdt) {
			
		}
	};
	
	public MqttSubscriber() throws MqttException{
		//Mqtt 클라이언트 생성
		mqttClient = new MqttClient("tcp://192.168.3.121:1883", MqttClient.generateClientId()); 
		//통신 결과에 따라 실행할 콜백 객체 생성
		mqttClient.setCallback(mqttCallback);
		//MQTT 브로커와 연결하기
		mqttClient.connect();
	}
	//메세지 보내기
	public void subscribe() throws MqttException{
		//지정한 토픽의 구독자로 신청
		mqttClient.subscribe("/devices/device1/temperature");
	}
	
	public void shutdown() throws MqttException{
		mqttClient.disconnect();  //브로커와 연결끊기
		mqttClient.close(); // mqttClient가 사용한리소스해제
	}
	
	public static void main(String[] args) throws MqttException, InterruptedException, IOException {
		MqttSubscriber subscriber=new MqttSubscriber();
		//구독자로 신청
		subscriber.subscribe();
		System.out.println("Strat subscriber");
		//프로그램이 종료되지 않게 멈춤
	System.out.println("press any key to quit");
		System.in.read();
		
	//클라이언트 종료	
		subscriber.shutdown();
	}

}
