
package mpu;

import java.io.IOException;

public class Main {
private static MPU6050 mpu;
    private static KalmanFilter velocityKF;
    private static KalmanFilter gyroscopeKF;
    private static KalmanFilter accelerometerKF;
    private static ComplementaryFilter mpuFusionCF;
    private static PID anglePID;
    private static PID speedPID;
    
    private static double stepperLeftSpeedRatio;
    private static double stepperRightSpeedRatio;
    private static double previousAngle;
    private static double targetAngle;
    private static double speed;
    private static int stepperIntervalDelay;
    
    private static final int MAXSTEPINTERVAL = 10000000;//10,000,000 nanoseconds
    
    private static Thread mpuThread;
    private static Thread stepperLeftThread;
    private static Thread stepperRightThread;
    private static Thread controllerThread;

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        initialize();
        
        runMPU();
        
        while(true){
            balance();
        }
    }
    
    public static void initialize() throws IOException{
        mpu = new MPU6050();
        velocityKF = new KalmanFilter(0.125);
        gyroscopeKF = new KalmanFilter(0.25);
        accelerometerKF = new KalmanFilter(0.25);
        mpuFusionCF = new ComplementaryFilter(0.4, 0.6);
        anglePID = new PID(0.5, 0, 28, 15);
        speedPID = new PID(0.1, 0.04, 0, 500);
    }
    
    public static void balance(){
        double angle = mpuFusionCF.getFilteredValue();
        double angularVelocity = angle - previousAngle;
        double estimatedVelocity = -speed - angularVelocity;
        
        velocityKF.filter(estimatedVelocity);
        
        anglePID.addIteration(speed, velocityKF.getFilteredValue());
        speedPID.addIteration(anglePID.returnCorrectedValue(), angle + targetAngle);
        
        speed = speedPID.returnCorrectedValue();
        previousAngle = angle;
        
        speedToDelay();
    }
    
    public static void readMPU(){
        mpu.readAccelerometer();
        mpu.readXGyroscope();
        mpu.calculateGravityVector();
				
    }
    
    public static void filterMPU(){
        gyroscopeKF.filter(mpu.getRotationVector().getX());
        accelerometerKF.filter(mpu.getGravityVector().getX());
        
        mpuFusionCF.filter(gyroscopeKF.getFilteredValue(), accelerometerKF.getFilteredValue());
    }
    
    public static void runMPU(){
        mpuThread = new Thread() {
            @Override
            public void run() {
                readMPU();
                filterMPU();
            }
        };
        mpuThread.start();
    }
    
    
    
    public static void speedToDelay(){
        stepperIntervalDelay = (int)(MAXSTEPINTERVAL / Math.abs(speed));
    }
}
