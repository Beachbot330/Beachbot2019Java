

package org.usfirst.frc330.constants;

public final class LiftConst {
	
	private LiftConst(){}

	//Positions
	public static final double defense			= 0.0;		//ejo 2.2.19
	public static final double lowerLimit 		= 0.0;		//ejo 2.2.19
	public static final double upperLimit	 	= 30.7;		//JR 3/09/19

	public static final double DeployBallLow	= 2.60;		//JR 3/10/19
	public static final double DeployBallMid	= 16.3;		//AP 2/28/19
	public static final double DeployBallHi		= 30.5;		//AP 3/15/19
	public static final double DeployBallCargo  = 8.25;		//JR 4/6/19
	//public static final double DeployBallHi		= 29.8;		//AP 2/28/19 (OCR)

	public static final double DeployHatchLow	= 0.0;		//ejo 2.2.19
	public static final double DeployHatchMid	= 15.01;		//AP 2/28/19
	public static final double DeployHatchHi	= 27.5;		//JR 7/13/19 (IRI)

	public static final double BallPickupGround	= 0.0;		// TBD 2/9/19 (TBD)
	public static final double BallPickupPlayer = 13.85;		//AP 3/1/19
	public static final double HatchPickup 		= 0.0;		//ejo 2.2.19
	public static final double ClimbLatchDeploy = 22.75;	//AP  2/9/19
	public static final double PogoDrive		= 0.0;		//JR 2/26/19
	public static final double PogoRetract		= 2.0;		//JR 2/26/19

	public static final double tolerance		= 0.8; 		//TODO: find 2019 value -AP

	public static double[] ballPositions = new double[]{
        defense,
        DeployBallLow,
        DeployBallMid,
        DeployBallHi,
    };
    public static double[] hatchPositions = new double[]{
        defense,
        DeployHatchLow,
        DeployHatchMid,
        DeployHatchHi
    };
    public static double[] allPositions = new double[]{
        defense,
        DeployBallLow,
        DeployBallMid,
        DeployBallHi,
        DeployHatchLow,
        DeployHatchMid,
        DeployHatchHi
    };

	//Encoder Distance Constants
	public static final double  ticksPerRev   		= 4096;	//AP 02/02/2019
	public static final double  inchesPerRev		= 5.50093; //AP 02/02/2019
    public static final double  encoderGearRatio    = 1/1;     //ejo 2.2.19
    public static final double 	sprocketDiameter    = 1.751;    //ejo 2.2.19
	//encoder is on the output shaft, so it should be gearRatio should be 1:1

	//Pogo Distance Constants
	public static final double  PogoInchesPerRev	= 5.105088062083414; //AP 02/09/2019
	public static final double  PogoDriveDistance	= 25; //AP 2/9/19
	
	//Talon SRX Settings
	public static final double VoltageRampRate				= 1.00;	// AP 2/24/19  Could go to 0?
	public static final double MaxOutputPercent				= 1.0;	// Dial it to max! 3/2/19 AP
	public static final int    CAN_Timeout					= 10; // AP 2018 value
	public static final int    CAN_Timeout_No_Wait      	= 0;  // AP 2018 value
	public static final int    CAN_Status_Frame_13_Period 	= 20; // AP 2018 value
	public static final int    CAN_Status_Frame_10_Period 	= 20; // AP 2018 value

	//Pogo Talon SRX Settings
	public static final double PogoVoltageRampRate			= 1.23;	// AP 2018 value
	public static final double PogoMaxOutputPercent			= 1.0;	// JR 2/25/19
	// Pogo Motion Magic Constants
	public static final int PogoVelocityLimit             	= 2000;   // AP 2018 value
	public static final int PogoAccelLimit                 	= 6000;   // AP 2018 value

	// PID Constants
	public static final double proportional      	= 0.6; //0.9;   	// JR 2/26/19
	public static final double integral         	= 0.0; 	// AP 2018 value
	public static final double derivative        	= 0.0;   	// Motion Magic 
	public static final double feedforward          = 0.5;   	// JR 4/7/19 new lift gear ratio
	public static final double downFFPercent		= 0.15;		//JR 4/7/19 new lift gear ratio. How much to substract from speed when going down

	public static final double climpP				= 0.9;		//JR 3/19/29

	// Pogo PID Constants
	public static final double PogoP      	= 0.3;   	// AP 2018 value
	public static final double PogoI        = 0.000; 	// AP 2018 value
	public static final double PogoD        = 15.0;   	// AP 2018 value
	public static final double PogoF        = 0.5;   	// AP 2018 value

	// Motion Magic Constants
	public static final int velocityLimit              = 1600;   // JR 4/7/19 new lift gear ratio
	public static final int accelLimit                 = 5000;   // JR 4/7/19 speed up lift

	// Other
	public static final double calibrationSpeed			= -0.15; // AP 2018 value
	public static final double GamepadDeadZone			= 0.1; //AP 2/17/19
	public static final double liftMaxMovement			= 2.0; //JR 3/15/19

	public static final double pogoMaxSpeed				= 0.93;	//Ft/sec /JR 2/24/19


}	