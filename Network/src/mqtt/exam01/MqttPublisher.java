
package mqtt.exam01;

import java.util.Date;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class MqttPublisher {
	
	private MqttClient mqttClient;
	private MqttCallback mqttCallback = new MqttCallback() {
		@Override
		public void connectionLost(Throwable thrwbl) {
			
		}

		@Override
		public void messageArrived(String string, MqttMessage mm) throws Exception {
			
		}

		@Override
		public void deliveryComplete(IMqttDeliveryToken imdt) {
			System.out.println("deliveryComplete :"+new Date());
		}
	};
	
	public MqttPublisher() throws MqttException{
		//Mqtt 클라이언트 생성
		mqttClient = new MqttClient("tcp://192.168.3.133:1883", MqttClient.generateClientId());  //브로커주소
		//통신 결과에 따라 실행할 콜백 객체 생성
		mqttClient.setCallback(mqttCallback);
		//MQTT 브로커와 연결하기
		mqttClient.connect();
	}
	//메세지 보내기
	public void publish(String text) throws MqttException{
		//MQTT 브로커로 보내는 메시지 생성
		MqttMessage mqttMessage=new MqttMessage(text.getBytes()); // 실제보내고자 하는 데이터를 스트링으로해서 바이트로 변환해서 전송해야됨
		//지정한 MQTT 브로커로 메시지 보냄
		mqttClient.publish("/devices/device1/temperature", mqttMessage);  //(토픽이름,보내고자하는 메세지) //브로커에 해당 토픽이 없어도 자동으로 생성됨
	}
	
	public void shutdown() throws MqttException{
		mqttClient.disconnect();  //브로커와 연결끊기
		mqttClient.close(); // mqttClient가 사용한리소스해제
	}
	
	public static void main(String[] args) throws MqttException, InterruptedException {
		MqttPublisher publisher=new MqttPublisher();
		//매 1초단위로 온도 메시지를 보냄
		for(int i=0;i<100;i++){
			publisher.publish("zz");
			Thread.sleep(1000);
		}
		publisher.shutdown();
	}

}
