

package org.usfirst.frc330.constants;

public final class GrabberConst {
	
	private GrabberConst(){}
	
	public static final double BallRollerInSpeed		= 1.0;	// JR 3/17/19
	public static final double BallRollerOutSpeed		= -1.0; // JR 3/17/19
	public static final double HatchRollerInSpeed		= -1.0;  // JR 3/17/19
	public static final double HatchRollerOutSpeed		= 0.5; // ejo 4.13.19
	public static final double MaxRollerSpeed			= 1.0;	// ejo 2.12.19
	public static final double RollerSlowSpeed			= 0.2; // JR 3/17/19

	public static final int getHatchInRangeMinRepititions		= 5;	//WAG -ejo 2.18.19
	public static final int getBallInRangeMinRepititions		= 5;	//WAG -ejo 2.18.19

	//Sensor (all ported from 2018 unless otherwise specificed -ejo 2.16.19)
	public static final double hatchPickupMinDistance					= 7.5;				//(inches) AP 2/24/19
	public static final double hatchPickupMaxDistance					= 8.0;				//(inches) EJO 2.16.19
	public static final double ballPickupMinDistance					= 4.0;				//(inches) JR 2.19.19
	public static final double ballPickupMaxDistance					= 6.0;				//(inches) JR 2.19.19

	public static final double ballAcquiredMinDistance					= 1.0;				//(inches) EJO 2.18.19
	public static final double ballAcquiredMaxDistance					= 3.5;				//(inches) EJO 2.18.19
	public static final double hatchAcquiredMinDistance					= 7.0;				//(inches) AP 3/15/19
	public static final double hatchAcquiredMaxDistance					= 25.0;				//(inches) AP 3/15/19

	public static final double distanceFromRollersToSensors				= 0.0;				//TODO update value -ejo 2.16.19

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

	public static final double currentLimit								= 40;
	public static final int	   currentLimitSamples						= 150;
}	