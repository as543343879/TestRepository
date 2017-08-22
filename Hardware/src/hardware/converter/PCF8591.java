package hardware.converter;

import com.pi4j.io.i2c.I2CBus;
import com.pi4j.io.i2c.I2CDevice;
import com.pi4j.io.i2c.I2CFactory;
import java.io.IOException;

public class PCF8591 {
	public static final int AIN0=0x40;
	public static final int AIN1=0x41;
	public static final int AIN2=0x42; //라즈베리 파이에서 연결된 핀이 어떤 핀이랑 연결 되야 할지 선택할때 사용 
	public static final int AIN3=0x43; //보기쉽게 상수화 시켜서 해당 번호 사용
	
	private int i2cAddress; 
	private int ainNo; 
	private int analogVal;
	
	//Constructor
	public PCF8591(int i2cAddress,int ainNo){
		this.i2cAddress=i2cAddress;
		this.ainNo=ainNo;
	}
	
	public int analogRead() throws Exception{
		I2CBus i2cBus = I2CFactory.getInstance(I2CBus.BUS_1);
		I2CDevice i2cDevice = i2cBus.getDevice(i2cAddress); //디바이스를 찾아서
		i2cDevice.read(ainNo); //dummy read
		analogVal=i2cDevice.read(ainNo); //0~255값을 가짐//해당핀으로 부터 아날로그 값을 얻어서
		return analogVal;         //리턴
	}
	
	public void analogWrite(byte value) throws Exception{
		I2CBus i2cBus = I2CFactory.getInstance(I2CBus.BUS_1);
		I2CDevice i2cDevice = i2cBus.getDevice(i2cAddress);
		i2cDevice.write(AIN0,value);
	}
	public static void main(String[] args) throws Exception {
		PCF8591 test= new PCF8591(0x48,PCF8591.AIN0); 
		while(true){
			//아날로그 값 읽기
			int value = test.analogRead();
			System.out.println(value);
			//아날로그 값 출력
			test.analogWrite((byte)0);
			Thread.sleep(200);
		}
		
	}
}
