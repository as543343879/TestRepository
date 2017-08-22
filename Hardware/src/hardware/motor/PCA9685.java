package hardware.motor;

import com.pi4j.gpio.extension.pca.PCA9685GpioProvider;
import com.pi4j.gpio.extension.pca.PCA9685Pin;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.i2c.I2CBus;
import com.pi4j.io.i2c.I2CFactory;
import java.math.BigDecimal;

public class PCA9685 {

	//Field
	//충돌이 나지 않도록 하나의 객체만 사용하도록 싱글톤 사용
	private static PCA9685 singleton;
	public static PCA9685 getInstance() throws Exception {
		if (singleton == null) {
			singleton = new PCA9685();
		}
		return singleton;
	}

	private PCA9685GpioProvider gpioProvider; //pi4j가 기본적으로 제공하는것.

	//16채널
	public static final Pin PWM_00 = PCA9685Pin.PWM_00;
	public static final Pin PWM_01 = PCA9685Pin.PWM_01;
	public static final Pin PWM_02 = PCA9685Pin.PWM_02;
	public static final Pin PWM_03 = PCA9685Pin.PWM_03;
	public static final Pin PWM_04 = PCA9685Pin.PWM_04;
	public static final Pin PWM_05 = PCA9685Pin.PWM_05;
	public static final Pin PWM_06 = PCA9685Pin.PWM_06;
	public static final Pin PWM_07 = PCA9685Pin.PWM_07;
	public static final Pin PWM_08 = PCA9685Pin.PWM_08;
	public static final Pin PWM_09 = PCA9685Pin.PWM_09;
	public static final Pin PWM_10 = PCA9685Pin.PWM_10;
	public static final Pin PWM_11 = PCA9685Pin.PWM_11;
	public static final Pin PWM_12 = PCA9685Pin.PWM_12;
	public static final Pin PWM_13 = PCA9685Pin.PWM_13;
	public static final Pin PWM_14 = PCA9685Pin.PWM_14;
	public static final Pin PWM_15 = PCA9685Pin.PWM_15;
	
   //해당장치의 period 저장을 하기위한 필드선언
	private int period;

    //Constructor
	public PCA9685() throws Exception {
		I2CBus i2cBus=I2CFactory.getInstance(I2CBus.BUS_1);
		//0x40: PCA9685 모듈의 I2C 장치 번호
		//PWM 주파수를 50Hz로 설정(SG90 Servo motor가 50Hz에서 동작하기 때문
		gpioProvider=new PCA9685GpioProvider(i2cBus,0x40,new BigDecimal(50)); // 0x40은 i2c통신번호,BigDecimal은 주파수 ,provider는 하나만 만들수 있음
		//한 사이클당 시간(Period): 1/frequency= 1/ 50Hz = 0.02s = 20ms = 20000us
		period = gpioProvider.getPeriodDurationMicros();
		
		//GPIO PWM 출력핀 설정
		GpioController gpioController = GpioFactory.getInstance();
		//16핀을 전부다 outputpin으로 쓰겠다고 설정하는 부분
		gpioController.provisionPwmOutputPin(gpioProvider,PWM_00); //라즈베리 파이 핀이 아닌 장치 핀을 얻는코드
		gpioController.provisionPwmOutputPin(gpioProvider,PWM_01); 
		gpioController.provisionPwmOutputPin(gpioProvider,PWM_02); 
		gpioController.provisionPwmOutputPin(gpioProvider,PWM_03); 
		gpioController.provisionPwmOutputPin(gpioProvider,PWM_04); 
		gpioController.provisionPwmOutputPin(gpioProvider,PWM_05); 
		gpioController.provisionPwmOutputPin(gpioProvider,PWM_06); 
		gpioController.provisionPwmOutputPin(gpioProvider,PWM_07); 
		gpioController.provisionPwmOutputPin(gpioProvider,PWM_08); 
		gpioController.provisionPwmOutputPin(gpioProvider,PWM_09); 
		gpioController.provisionPwmOutputPin(gpioProvider,PWM_10); 
		gpioController.provisionPwmOutputPin(gpioProvider,PWM_11); 
		gpioController.provisionPwmOutputPin(gpioProvider,PWM_12); 
		gpioController.provisionPwmOutputPin(gpioProvider,PWM_13); 
		gpioController.provisionPwmOutputPin(gpioProvider,PWM_14);
		gpioController.provisionPwmOutputPin(gpioProvider,PWM_15);
		
		gpioProvider.reset(); // 16개의 pwm 신호의 핀을 리셋하여 신호가 나오지 않도록한다.
		
	}
	
	public void setDuration(Pin pin,int duration){
		//duration: 0~19999us (20000일 경우 한 사이클이 형성되지 않고 한주기 내내 high이기 때문에 불가능)
		if(duration>=period){
			duration=(period-1);
		}else if(duration<0){
			duration=0;
		}
		if(duration!=0){
			gpioProvider.setPwm(pin, duration);
		}else{
			gpioProvider.setAlwaysOff(pin);
		}
		
	}
	
	public void setStep(Pin pin,int step){
		//step:0~4095
		if(step>4095){
			step=4095;
		}else if(step<0){
			step=0;
		}
		if(step!=0){
			gpioProvider.setPwm(pin,0,step);
		}else{
			gpioProvider.setAlwaysOff(pin);
		}
	}
	
	//Method
	
	public static void main(String[] args) throws Exception {
		PCA9685 pca9685 = PCA9685.getInstance();
		//1.방법 : duration으로 제어
		/*
		while(true){
			System.out.println("회전시작");
		pca9685.setDuration(PWM_00,770);
		pca9685.setDuration(PWM_02,770);
		pca9685.setDuration(PWM_04,770);
		Thread.sleep(2000);
		pca9685.setDuration(PWM_00,(770+2600)/2);
		pca9685.setDuration(PWM_02,(770+2600)/2);
		pca9685.setDuration(PWM_04,(770+2600)/2);
		Thread.sleep(2000);
		pca9685.setDuration(PWM_00,2600);
		pca9685.setDuration(PWM_02,2600);
		pca9685.setDuration(PWM_04,2600);
		Thread.sleep(2000);
		}
*/
		//2. 방법: 단계로 제어
		while(true){
			pca9685.setStep(PWM_00,163);
			pca9685.setStep(PWM_02,164);
			pca9685.setStep(PWM_04,164);
			Thread.sleep(2000);
			pca9685.setStep(PWM_00,354);
			pca9685.setStep(PWM_02,354);
			pca9685.setStep(PWM_04,354);
			Thread.sleep(2000);
			pca9685.setStep(PWM_00,548);
			pca9685.setStep(PWM_02,548);
			pca9685.setStep(PWM_04,548);
			Thread.sleep(2000);
		}
		
	}

}
