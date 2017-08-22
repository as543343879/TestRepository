
package gyro;

import java.io.IOException;

public class BotController {
    private final int runTime = 120;//secondsS
    private final boolean BALANCER;
    private final boolean customControl = false;
    private static ReadMPUThread mpuT;
    boolean warning;
    boolean waitForConnection = true;
    long startTime;
    long time;
    Robot robot;
    
    BotController(boolean balancer) throws IOException{
        robot = new Robot();
        mpuT = new ReadMPUThread();
        
        this.BALANCER = balancer;
    }
    
    public void start() throws IOException, InterruptedException{
        startTime = System.currentTimeMillis(); //fetch starting time
        warning = false;
        mpuT.start();
        
        robot.turnOnMotors();
        robot.setBalancer(BALANCER);
        
        while((System.currentTimeMillis() - startTime) < 1000 * runTime)
        {
            if(customControl){
                robot.control();
            }
            else{
                
                if(BALANCER){
                    robot.balance();
                }
            }
            
            if (!warning && (System.currentTimeMillis() - startTime) > (1000 * runTime) - 5000){
                System.out.println("STOPPING IN 5 SECONDS.");
                
                warning = true;
            }
        }
    }

}
