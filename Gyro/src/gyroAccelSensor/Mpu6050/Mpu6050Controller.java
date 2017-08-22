package gyroAccelSensor.Mpu6050;
			
import com.pi4j.io.i2c.I2CBus;
import com.pi4j.io.i2c.I2CDevice;
import com.pi4j.io.i2c.I2CFactory;
import java.io.IOException;
import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapResponse;
import org.eclipse.californium.core.coap.MediaTypeRegistry;
import org.json.JSONObject;

public class Mpu6050Controller {

	private static byte[] accelData = new byte[6];
	public static double gyroAngleZ = 0.;
	
	public static long lastUpdateTime = 0;

	private static I2CBus bus = null;
	private static I2CDevice mpu6050 = null;
	public static final double PI = 3.14159265359;
	public static double filteredValue;

	public static String ipAdress = "192.168.3.133";
	public static CoapClient coapClient;
	public static CoapResponse coapResponse;
	public static JSONObject jsonObject;
	public static String json;
	public static double gyroiZ;
	public static double gyroAngularSpeedOffsetZ;

	public static void initialize() throws IOException, InterruptedException, I2CFactory.UnsupportedBusNumberException {
		initializeI2C();
		configureMpu6050();
	}

	private static void initializeI2C() throws IOException, I2CFactory.UnsupportedBusNumberException {
		System.out.println("Creating I2C bus");
		bus = I2CFactory.getInstance(I2CBus.BUS_1);
		System.out.println("Creating I2C device");
		mpu6050 = bus.getDevice(0x68);
	}

	private static void configureMpu6050() throws IOException, InterruptedException {
lastUpdateTime = System.currentTimeMillis();
calibrateSensors();

		//1 Waking the device up
		writeConfigRegisterAndValidate(
						"Waking up device",
						"Wake-up config succcessfully written: ",
						Mpu6050Registers.MPU6050_RA_PWR_MGMT_1,
						Mpu6050RegisterValues.MPU6050_RA_PWR_MGMT_1);

		//2 Configure sample rate
		writeConfigRegisterAndValidate(
						"Configuring sample rate",
						"Sample rate succcessfully written: ",
						Mpu6050Registers.MPU6050_RA_SMPLRT_DIV,
						Mpu6050RegisterValues.MPU6050_RA_SMPLRT_DIV);

		//3 Setting global config
		writeConfigRegisterAndValidate(
						"Setting global config (digital low pass filter)",
						"Global config succcessfully written: ",
						Mpu6050Registers.MPU6050_RA_CONFIG,
						Mpu6050RegisterValues.MPU6050_RA_CONFIG);

		//4 Configure Gyroscope
		writeConfigRegisterAndValidate(
						"Configuring gyroscope",
						"Gyroscope config successfully written: ",
						Mpu6050Registers.MPU6050_RA_GYRO_CONFIG,
						Mpu6050RegisterValues.MPU6050_RA_GYRO_CONFIG);

		//5 Configure Accelerometer
		writeConfigRegisterAndValidate(
						"Configuring accelerometer",
						"Accelerometer config successfully written: ",
						Mpu6050Registers.MPU6050_RA_ACCEL_CONFIG,
						Mpu6050RegisterValues.MPU6050_RA_ACCEL_CONFIG);

		//6 Configure interrupts
		writeConfigRegisterAndValidate(
						"Configuring interrupts",
						"Interrupt config successfully written: ",
						Mpu6050Registers.MPU6050_RA_INT_ENABLE,
						Mpu6050RegisterValues.MPU6050_RA_INT_ENABLE);

		//7 Configure low power operations
		writeConfigRegisterAndValidate(
						"Configuring low power operations",
						"Low power operation config successfully written: ",
						Mpu6050Registers.MPU6050_RA_PWR_MGMT_2,
						Mpu6050RegisterValues.MPU6050_RA_PWR_MGMT_2);

		for (byte i = 1; i <= 120; i++) {
			byte registerData = Mpu6050Controller.readRegister(i);
			System.out.println(i + "\t\tRegisterData:" + Helper.formatBinary(registerData));
		}

		mpu6050.read(0x3B, accelData, 0, 6);
		for (int j = 0; j < 10000; j++) {
			/*
								 for(int i=0;i<6;i++){
								 System.out.print(accelData[i]);
								 }
								 System.out.println("--");
								 Thread.sleep(1000);
			 */
			//byte value1=Mpu6050Controller.readRegister(Mpu6050.Mpu6050Registers.MPU6050_RA_GYRO_XOUT_H);
			//double value=readWord2C(Mpu6050.Mpu6050Registers.MPU6050_RA_ACCEL_XOUT_H) / 16384;
			//	System.out.println(value);
			//	Thread.sleep(500);
			double angleXZ = 0;
			double angleXY = 0;
			double angleYZ = 0;
			double angleXY1 = 0;

			double accelX = readWord2C(gyroAccelSensor.Mpu6050.Mpu6050Registers.MPU6050_RA_ACCEL_XOUT_H) / 16384.0;
			//	System.out.println("accelX      "+(int)(accelX*100));

			double accelY = readWord2C(gyroAccelSensor.Mpu6050.Mpu6050Registers.MPU6050_RA_ACCEL_YOUT_H) / 16384.0;
			//System.out.println("accelY      "+(int)(accelY*100));
			//	System.out.println("accelY      "+(int)(accelY*100));
			//	System.out.println(" -");
			//	System.out.println(" -");
			//	System.out.println(" -");
			//	System.out.println(" -");
			//	System.out.println(" -");

			//System.out.println("accelX"+accelX+"                     accelY"+accelY);
			//Thread.sleep(200);
			double accelZ = readWord2C(gyroAccelSensor.Mpu6050.Mpu6050Registers.MPU6050_RA_ACCEL_ZOUT_H) / 16384.0;
			//System.out.println("accelZ"+accelZ);
			//System.out.println("accelX: "+accelX+"\t" + "accelY: "+accelY+ "\t" + "accelZ: "+accelZ);

			double gyroX = readWord2C(gyroAccelSensor.Mpu6050.Mpu6050Registers.MPU6050_RA_GYRO_XOUT_H) / 131.0;
			//	filter(gyroX);
			double gyroY = readWord2C(gyroAccelSensor.Mpu6050.Mpu6050Registers.MPU6050_RA_GYRO_YOUT_H) / 131.0;
			//double gyroZ = readWord2C(gyroAccelSensor.Mpu6050.Mpu6050Registers.MPU6050_RA_GYRO_ZOUT_H) / 131.0;
			//	System.out.println("gyroX: "+(int)gyroX*10+"\t" + "gyroY: "+(int)gyroY*10+ "\t" + "gyroZ: "+(int)gyroZ*10);
			//Thread.sleep(200);

			double gyroZ = readWord2C(gyroAccelSensor.Mpu6050.Mpu6050Registers.MPU6050_RA_GYRO_ZOUT_H) / 131.0;
			//double gyroZ1 = readWord(gyroAccelSensor.Mpu6050.Mpu6050Registers.MPU6050_RA_GYRO_ZOUT_H) *131.0;
			System.out.println("gyroZ:  "+(int)gyroZ*10);
			double rotationX = get_x_rotation(accelX, accelY, accelZ);
			//System.out.println("rotationX:  "+rotationX);
			double rotationY = get_y_rotation(accelX, accelY, accelZ);
			//	System.out.println("rotationY:  "+rotationY);

			
			
			//-------------------------------------------------------------------
			double dpu=readWord2C(gyroAccelSensor.Mpu6050.Mpu6050Registers.MPU6050_RA_FIFO_COUNTH);
			System.out.println(dpu+"-------------------");
			double dt = Math.abs(System.currentTimeMillis() - lastUpdateTime) / 1000.; // s
			double deg;
			deg = Math.atan2(accelX, accelZ) * 180 / Math.PI;
			angleXZ = (0.95 * (angleXZ + (gyroY * 0.001))) + (0.05 * deg);
			System.out.println("angleXZ    " + (int) ((angleXZ + 9) * 20));
			//		Thread.sleep(200);
			
			deg = Math.atan2(accelX, accelY) * 180 / Math.PI;
			angleXY = (0.95 * (angleXY + (gyroZ * 0.001))) + (0.05 * deg);
			double z=gyroZ-gyroAngularSpeedOffsetZ;
			gyroAngleZ =z*dt;
			gyroiZ=gyroiZ+gyroAngleZ;
			System.out.println("angleXY    "+gyroiZ);
			//		Thread.sleep(200);
			
			lastUpdateTime = System.currentTimeMillis();

			deg = Math.atan2(accelY, accelZ) * 180 / Math.PI;
			angleYZ = (0.95 * (angleYZ + (gyroX * 0.001))) + (0.05 * deg);
			System.out.println("angleYZ    " + (angleYZ + 9) * 20);
			//		Thread.sleep(200);
			//System.out.println("gyroZ    " + gyroZ);
			System.out.println("----------");
			System.out.println("----------");
			System.out.println("----------");
			System.out.println("----------");

			//int y=(int) ((angleXZ + 9)* 20*500/360);
			//ultraSensorState((int)((angleXZ+9)*20));
			//backTire((int)(angleYZ+9)*200);
			mouseMove((int) gyroZ, (int) (angleXZ + 9) * 20);
			ultraSensorState((int) (angleYZ + 9) * 20);

			Thread.sleep(100);

		}

		System.exit(0);
	}

	public static void mouseMove(int x, int y) {
		int axisX = x;
		int axisY = y;

		jsonObject = new JSONObject();
		jsonObject.put("command", "change");
		jsonObject.put("axisX", String.valueOf(axisX));
		jsonObject.put("axisY", String.valueOf(axisY));
		json = jsonObject.toString();

		coapClient = new CoapClient();
		coapClient.setURI("coap://" + ipAdress + "/mouse");
		coapResponse = coapClient.post(json, MediaTypeRegistry.APPLICATION_JSON);
		coapClient.shutdown();
	}

	public static void ultraSensorState(int angle1) {
		int angle = angle1;

		jsonObject = new JSONObject();
		jsonObject.put("command", "change");
		jsonObject.put("angle", String.valueOf(angle));
		json = jsonObject.toString();

		coapClient = new CoapClient();
		coapClient.setURI("coap://" + ipAdress + "/ultrasonicsensor");
		coapResponse = coapClient.post(json, MediaTypeRegistry.APPLICATION_JSON);
		coapClient.shutdown();
	}

	public static void backTire(int angle1) {
		int speed = angle1;
		jsonObject = new JSONObject();
		jsonObject.put("command", "change");
		jsonObject.put("direction", "forward");
		jsonObject.put("speed", String.valueOf(speed));
		json = jsonObject.toString();

		coapClient = new CoapClient();
		coapClient.setURI("coap://" + ipAdress + "/backtire");
		coapResponse = coapClient.post(json, MediaTypeRegistry.APPLICATION_JSON);
		coapClient.shutdown();
	}

	public void readXGyroscope() {

	}

	public void readYGyroscope() {

	}

	public void readZGyroscope() {

	}

	public void readXAccelerometer() {

	}

	public void readYAccelerometer() {

	}

	public void readZAccelerometer() {

	}

	private static void writeRegister(byte register, byte data) throws IOException {
		mpu6050.write(register, data);
	}

	public static byte readRegister(byte register) throws IOException {
		int data = mpu6050.read(register);
		return (byte) data;
	}

	public static byte readRegister() throws IOException {
		int data = mpu6050.read();
		return (byte) data;
	}


	public static void writeConfigRegisterAndValidate(String initialText, String successText, byte register, byte registerData) throws IOException {
		System.out.println(initialText);
		writeRegister(register, registerData);
		byte returnedRegisterData = Mpu6050Controller.readRegister(register);
		if (returnedRegisterData == registerData) {
			System.out.println(successText + Helper.formatBinary(returnedRegisterData));
		} else {
			throw new RuntimeException("Tried to write " + Helper.formatBinary(registerData) + " to "
							+ register + ", but validiating value returned " + Helper.formatBinary(returnedRegisterData));
		}
	}

	public static double readWord2C(byte addr) throws IOException {
		double value = readWord(addr);

		if (value >= 0x8000) {
			value = (double) -((65535 - value) + 1);
		} else {
			value = (double) value;
		}

		return value;
	}

	public static double readWord(byte addr) throws IOException {
		int high = readRegister(addr);
		int low = readRegister((byte) (addr + 1));
		double value = (high << 8) + low;

		return value;
	}

	public static double dist(double a, double b) {
		return Math.sqrt((a * a) + (b * b));
	}

	public static double get_y_rotation(double x, double y, double z) {
		double radians;
		radians = Math.atan2(y, dist(y, z));
		return -(radians * (180.0 / Math.PI));

	}

	public static double get_x_rotation(double x, double y, double z) {
		double radians;
		radians = Math.atan2(y, dist(x, z));
		return (radians * (180.0 / Math.PI));
	}

	public static void filter(double... values) {
		int i = 0;
		double sum = 0;
		double avg;
		double gainInverse = (1 - 0.25);

		for (double value : values) {
			sum += value;
			i++;
		}

		avg = sum / i;

		filteredValue = (0.25 * filteredValue) + (gainInverse * avg);
	}
	
	private static void calibrateSensors() throws IOException {
        int nbReadings = 50;
        
        // Gyroscope offsets
        
        gyroAngularSpeedOffsetZ = 0.;
        for(int i = 0; i < nbReadings; i++) {
           double gyroZ = readWord2C(gyroAccelSensor.Mpu6050.Mpu6050Registers.MPU6050_RA_GYRO_ZOUT_H) / 131.0;
            gyroAngularSpeedOffsetZ += gyroZ;
        }
        gyroAngularSpeedOffsetZ /= nbReadings;
        
    }

}
