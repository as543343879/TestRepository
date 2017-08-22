
package mqtt.exam02;

import java.util.Scanner;
import org.eclipse.paho.client.mqttv3.MqttException;

public class Device1Example {
	public static void main(String[] args) throws MqttException {
		Device device=new Device("device1");
		device.subscribe();
		Scanner scanner= new Scanner(System.in,"UTF-8"); //UTF-8로 읽겠다
	    while(true){
				System.out.print("input message or q(quit): ");
				String text= scanner.nextLine();
				if(text.equals("q")) break;
				device.publish("device2", text);
					
			}
			device.close();
	}

}
