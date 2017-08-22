package hardware.motor;

import com.pi4j.io.gpio.Pin;

public class SG90ServoPCA9685Duration {

	//Field
	private PCA9685 pca9685;
	private Pin pin; //16채널 중에서 몇채널인지를 받는 필드
	private int angle;
	private int minDuration;
	private int maxDuration;

	//Constructor
	public SG90ServoPCA9685Duration(PCA9685 pca9685, Pin pin){
		//0도(0.8ms= 800us)
		//180도(2.7ms=2700us)
		this(pca9685,pin,800,2700);
	}
	
	public SG90ServoPCA9685Duration(PCA9685 pca9685, Pin pin, int minDuration, int maxDuration) {

		this.pca9685 = pca9685;
		this.pin=pin;
		this.minDuration=minDuration;
		this.maxDuration=maxDuration;
		

	}

	//Method

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
		int duration=minDuration+(int)(angle*(maxDuration-minDuration)/180.0);
		this.pca9685.setDuration(pin, duration);
	}
	
	public static void main(String[] args) throws Exception {
		PCA9685 pca9685= PCA9685.getInstance();
		SG90ServoPCA9685Duration servoUp= new SG90ServoPCA9685Duration(pca9685,PCA9685.PWM_15);
		SG90ServoPCA9685Duration servoLeft= new SG90ServoPCA9685Duration(pca9685,PCA9685.PWM_14);
		SG90ServoPCA9685Duration servoEye= new SG90ServoPCA9685Duration(pca9685,PCA9685.PWM_11);
		/*
		for(int i=0;i<=180;i+=10){
		servoUp.setAngle(i);
		servoLeft.setAngle(i);
		servoEye.setAngle(i);
		Thread.sleep(500);
		
		
		}
		for(int i=180;i>=0;i-=10){
		servoUp.setAngle(i);
		servoLeft.setAngle(i);
		servoEye.setAngle(i);
		Thread.sleep(500);
		}
		/*
				while(true){
				for(int i=0;i<=180;i+=10){
		servo.setAngle(0);
		Thread.sleep(500);
		servo.setAngle(180);
		Thread.sleep(500);
		if(i%3==0){
		servo.setAngle(90);
		Thread.sleep(500);
		}
		}
		}
		*/
		//servo.setAngle(90);
	//	Thread.sleep(1000);
	
	servoUp.setAngle(0);
		servoLeft.setAngle(90);
		servoEye.setAngle(90);
		Thread.sleep(500);

	}
	
}
