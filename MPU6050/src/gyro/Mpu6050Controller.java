
package gyro;

import com.pi4j.io.i2c.I2CBus;
import com.pi4j.io.i2c.I2CDevice;
import com.pi4j.io.i2c.I2CFactory;
import java.io.IOException;

public class Mpu6050Controller {
  private static I2CBus bus;
    private static I2CDevice mpu6050;
    
    public static final byte MPU6050_ADDRESS = 0x68;
    public static final byte MPU6050_RA_PWR_MGMT_1 = 107;
    public static final byte MPU6050_RA_SMPLRT_DIV = 25;
    public static final byte MPU6050_RA_CONFIG = 26;
    public static final byte MPU6050_RA_GYRO_CONFIG = 27;
    public static final byte MPU6050_RA_ACCEL_CONFIG = 28;
    public static final byte MPU6050_RA_INT_ENABLE = 56;
    public static final byte MPU6050_RA_PWR_MGMT_2 = 108;
    
    public static void initialize() throws IOException, InterruptedException, I2CFactory.UnsupportedBusNumberException {
        initializeI2C();
        configureMpu6050();
        
        System.out.println("MPU Configured");
    }

    private static void initializeI2C() throws IOException, I2CFactory.UnsupportedBusNumberException {
        System.out.println("Creating I2C bus");
        bus = I2CFactory.getInstance(I2CBus.BUS_1);
        System.out.println("Creating I2C device");
        mpu6050 = bus.getDevice(0x68);
    }
    
    private static void configureMpu6050() throws IOException, InterruptedException {

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
        /*
        for (byte i = 1; i <= 120; i++) {
            byte registerData = Mpu6050Controller.readRegister(i);
            System.out.println(i + "\t\tRegisterData:" + Helper.formatBinary(registerData));
        }
        */
        
        System.out.println("Enabling DMP");
        setDMPEnabled(true);
        
        //System.exit(0);
    }
      
    private static void writeRegister(byte register, byte data) throws IOException {
        mpu6050.write(register, data);
    }

    public static byte readRegister(byte register) throws IOException {
        int data;
        
        while(true){
            try{
                data = mpu6050.read(register);
                break;
            }
            catch(Exception e){}
        }
        
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
    
    public static void setDMPEnabled(boolean enabled) throws IOException{
        writeRegister(MPU6050_ADDRESS, (byte)Mpu6050Registers.MPU6050_USERCTRL_DMP_EN_BIT);
    }
    
    public static void DMPInitialize(){
        
    }
}
