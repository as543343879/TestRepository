package com.mycompany.myapp.service;

public interface SensingCarService {
	public void changeUltrasonicSensorAngle(int angle) throws Exception;
	public int getUltrasonicSensorDistance() throws Exception;
	public double getGasSensorValue() throws Exception;

}
