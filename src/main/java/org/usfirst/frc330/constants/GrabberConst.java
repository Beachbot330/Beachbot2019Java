

package org.usfirst.frc330.constants;

public final class GrabberConst {
	
	private GrabberConst(){}
	
	public static final double DefaultRollerSpeed		= 0.5;	//WAG -ejo 2.12.19
	public static final double MaxRollerSpeed			= 1.0;	//WAG -ejo 2.12.19

	//Sensor (all ported from 2018 unless otherwise specificed -ejo 2.16.19)
	public static final double minDistanceToTriggerHatch				= 7.0;				//(inches) EJO 2.16.19
	public static final double maxDistanceToTriggerHatch				= 9.0;				//(inches) EJO 2.16.19

	public static final double minDistanceToTriggerBall					= 2.0;				//(inches) EJO 2.16.19
	public static final double maxDistanceToTriggerBall					= 3.0;				//(inches) EJO 2.16.19
	
	public static final double distanceBetweenSensors					= 0.5; 				//(inches) EJO 2.16.19
	public static final double sensorMaximumInnerDistance				= 2.2; 				//How far the sensor is from the gripper back wall     //(inches) JR 3.3.18    
	public static final double sensorMinLength							= 1.5748031496; 	//(inches) EJO 2.4.18
	public static final double sensorMaxLength							= 15.0;				//(inches) JR 3.3.18
	public static final double sensorMaximumOuterDistance				= 12.0 + sensorMaximumInnerDistance;	//(inches) JR 3.3.18
	public static final double centerSensorMaximumInnerDistance			= 2.8 + sensorMaximumInnerDistance;		//(inches) JR 3.3.18
	
	public static final double kalmanProcessNoise						= 4.5;				//(inches) 14ft/sec*12in/ft/50samples per second + 0.5 JDR 2/24/18
	public static final double kalmanSensorNoise						= 1.0;				//(inches) Based on observed data JDR 2/24/18
	public static final double kalmanEstimatedError						= 100;				//(inches) //JDR 2/24/18

	public static final int    medianSamples							= 5;
}	