package hardware.motor;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinPwmOutput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.wiringpi.Gpio;

public class SG90Servo {
	
	private GpioPinPwmOutput pin;
	private int angle;
	private double minStep; //8단계
	private double maxStep; //27단계
	
	public SG90Servo(Pin pin){
		this(pin,8,23);
	}
	
	public SG90Servo(Pin pinNo,double minStep,double maxStep){
		GpioController gpioController = GpioFactory.getInstance();
		pin=gpioController.provisionPwmOutputPin(pinNo);
		Gpio.pwmSetMode(Gpio.PWM_MODE_MS);
		Gpio.pwmSetClock(1920);
		Gpio.pwmSetRange(200);
		this.minStep=minStep;
		this.maxStep=maxStep;
	}

	public int getAngle() {
		return angle;
	}

	public void setAngle(int angle) {
		if(angle<0){
			angle=0;
		}else if(angle>180){
			angle=180;
		}
		this.angle = angle;
		int step=(int)(minStep+(angle*(maxStep-minStep)/180.0));
		pin.setPwm(step);
	}
	
	public static void main(String[] args) throws InterruptedException {
		SG90Servo test=new SG90Servo(RaspiPin.GPIO_01,8,25);
		/*
		for(int i=0;i<=180;i+=10){
			
			test.setAngle(i);
			Thread.sleep(500);
			
		}*/

		while(true){
		//test.setAngle(0);
	//	Thread.sleep(1000);
		test.setAngle(90);
		Thread.sleep(1000);
		//test.setAngle(180);
		//Thread.sleep(1000);
		
		}
			
	}

}
