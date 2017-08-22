package mqtt.exam02;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.json.JSONObject;

public class Device {

private String url = "tcp://localhost:1883"; //브로커접속을 위한 정보
	private String myClientId = "device1";
	private String topicRequest = "/devices/" + myClientId + "/request";
	private String topicResponse = "/devices/" + myClientId + "/response";
	private int qos = 1;
	private MqttClient mqttClient;

	private MqttCallback callback = new MqttCallback() {
		@Override
		public void deliveryComplete(IMqttDeliveryToken imdt) {

		}

		@Override
		public void messageArrived(String string, MqttMessage mm) throws Exception {
	System.out.println("Message -> " + mm.toString());
			System.out.print("input message or q(quit): ");
		}

		@Override
		public void connectionLost(Throwable thrwbl) {
			try {
				close();
			} catch (MqttException ex) {
				ex.printStackTrace();
			}
		}
	};

	//생성자
		public Device(String myClientId) throws MqttException {
		this.myClientId = myClientId;
		topicRequest = "/devices/" + myClientId + "/request";
		topicResponse = "/devices/" + myClientId + "/response";		
		
		mqttClient = new MqttClient(url, myClientId);
		mqttClient.setCallback(callback);
		mqttClient.connect();
	}
	//메소드

	public void subscribe() throws MqttException {
		mqttClient.subscribe(topicResponse);
	}

	public void publish(String targetClientId,String text) throws MqttException {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("from", myClientId);
		jsonObject.put("to", targetClientId);
		jsonObject.put("text", text);
		String json=jsonObject.toString();
		MqttMessage mqttMessage=new MqttMessage(json.getBytes());
		mqttMessage.setQos(qos); //qos 1은 메세지 전달이 최소한번 보장을 의미
		mqttClient.publish(topicRequest, mqttMessage);
	}

	public void close() throws MqttException {
		if (mqttClient != null) {
			mqttClient.disconnect();
			mqttClient.close();
			mqttClient = null;
		}
	}
}
