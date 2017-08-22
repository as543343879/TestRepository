
package mqtt.exam02;

import java.io.IOException;
import org.eclipse.paho.client.mqttv3.MqttException;

public class DistributorExample {

	public static void main(String[] args) throws MqttException, IOException {
		Distributor distributor=new Distributor();
		distributor.subscribe();
		
		System.out.println("press any key to quit");
		System.in.read();
		
		distributor.close();
	}
}
