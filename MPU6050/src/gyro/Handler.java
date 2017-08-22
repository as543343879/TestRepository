/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gyro;

import com.pi4j.io.i2c.I2CFactory;
import java.io.IOException;

/**
 *
 * @author Administrator
 */
public class Handler {
   private static final boolean BALANCER = true;
    static BotController botControl;
    
    /**
     * @param args
     * @throws InterruptedException
     * @throws IOException 
     */
    public static void main(String[] args) throws InterruptedException, IOException, I2CFactory.UnsupportedBusNumberException {
        
        
        if(!BALANCER){
            botControl = new BotController(false);
            

            Thread.sleep(2000);
            botControl.start();
            Thread.sleep(250);
        }
        else {
            Mpu6050Controller.initialize();
            botControl = new BotController(true);


            Thread.sleep(2000);
            botControl.start();
            Thread.sleep(250);
        }
    }
}
