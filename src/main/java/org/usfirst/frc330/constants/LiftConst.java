

package org.usfirst.frc330.constants;

public final class LiftConst {
	
	private LiftConst(){}

	//Positions
	public static final double defense			= 0.0;		//ejo 2.2.19
	public static final double lowerLimit 		= 0.0;		//ejo 2.2.19
	public static final double upperLimit	 	= 30.75;	//ejo 2.2.19

	public static final double DeployBallLow	= 5.0;		//ejo 2.2.19
	public static final double DeployBallMid	= 18.5;		//ejo 2.2.19
	public static final double DeployBallHi		= 30.75;	//ejo 2.2.19 (might be changed)

	public static final double DeployHatchLow	= 0.0;		//ejo 2.2.19
	public static final double DeployHatchMid	= 13.5;		//ejo 2.2.19
	public static final double DeployHatchHi	= 27.5;		//ejo 2.2.19

	public static final double HatchPickup 		= 0.0;		//ejo 2.2.19
	public static final double ClimbLatchDeploy = 21.75;	//AP  2/9/19

	public static final double tolerance		= 0.5; 		//TODO: find 2019 value -AP

	//Encoder Distance Constants
	public static final double  ticksPerRev   		= 4096;	//AP 02/02/2019
	public static final double  inchesPerRev		= 5.50093; //AP 02/02/2019
    public static final double  encoderGearRatio    = 1/1;     //ejo 2.2.19
    public static final double 	sprocketDiameter    = 1.751;    //ejo 2.2.19
	public static final double  Fudgefactor         = 0;       //TODO - Find the 2019 value - ejo
	//encoder is on the output shaft, so it should be gearRation should be 1:1
	
	//Talon SRX Settings
	public static final double VoltageRampRate				= 1.23;	// AP 2018 value
	public static final double MaxOutputPercent				= 0.3;	// AP Testing Value
	public static final int    CAN_Timeout					= 10; // AP 2018 value
	public static final int    CAN_Timeout_No_Wait      	= 0;  // AP 2018 value
	public static final int    CAN_Status_Frame_13_Period 	= 20; // AP 2018 value
	public static final int    CAN_Status_Frame_10_Period 	= 20; // AP 2018 value

	// PID Constants
	public static final double proportional      	= 0.3;   	// AP 2018 value
	public static final double integral         	= 0.000; 	// AP 2018 value
	public static final double derivative        	= 15.0;   	// AP 2018 value
	public static final double feedforward          = 0.5;   	// AP 2018 value

	// Motion Magic Constants
	public static final int velocityLimit              = 2000;   // AP 2018 value
	public static final int accelLimit                 = 6000;   // AP 2018 value

	// Other
	public static final double calibrationSpeed			= -0.15; // AP 2018 value




}	