

package mpu;

import com.pi4j.io.i2c.I2CFactory;
import java.io.IOException;

public class Main {

	    
    public static void main(String[] args) throws I2CFactory.UnsupportedBusNumberException {
        try {
            MPU6050 mpu6050 = new MPU6050();
        } catch (IOException ex) {
            System.err.println("No se pudo crear el objeto");
        }        
    }   
}
