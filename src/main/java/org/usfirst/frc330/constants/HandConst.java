

package org.usfirst.frc330.constants;

public final class HandConst {
	
	private HandConst(){}

	public static final double tolerance		= 2.5; 		//AP 3/9/19

	//angles relative to the ground (degrees)
	public static final double upperHardStop 		= 57.0+3.9;		//AP Compbot 2/28/19
	public static final double defense 			= 57.0; 	//AP 2/24/19
	//public static final double defense 			= -30.0; 	//AP 2/24/19
	public static final double hatchPlacementLow 	= 0.0; 		//ejo 2.2.19
	public static final double hatchPlacementMid 	= 0.0; 		//AP 2/10/19
	public static final double hatchPlacementHigh 	= 0.0; 		//AP 2/10/19
	public static final double ballPlacementLow 	= 32.74; 		//AP 2/24/19
	public static final double ballPlacementMid 	= 32.74; 		//AP 2/24/19
	public static final double ballPlacementHigh 	= 32.74; 	//AP 2/24/19
	public static final double ballPlacementCargo   = 41.0;		//JR 4/6/19
	public static final double hatchPickup	 			= -2.0; 		//AP 2/9/19
	public static final double postHatchPickup			= 0.0; 		//AP 2/9/19
	public static final double ballPickupGround 		= -38.0;	//JR 3/17/19
	public static final double ballPickupHumanPlayer	= 0.0;	//AP 3/1/19
	public static final double lowerHardStop	= -49.5;		//AP 2/25/19  -53.2 is actual hard stop

	public static final double calibrationTolerance = 10;	// JR 3/17/19

	public static final double encoderGearRatio = 1.0;	  // JR 2/18/19

	public static final int    CAN_Timeout					= 10; // AP 2018 value
	public static final int    CAN_Timeout_No_Wait      	= 0;  // AP 2018 value

	public static final double VoltageRampRate				= 0.0;	// JR disable voltage ramp 2/19/19
	public static final double MaxOutputPercent				= 1.0;	// JR 3/16/19

	
	// Motion Magic Constants
	public static final int velocityLimit              = 250;   // JR 3/16/19
	public static final int accelLimit                 = 1000;   // JR 3/16/19

	public static final double GamepadDeadZone			= 0.1; //AP 2/17/19
	
	public static final String PracticeZeroString = "PracticeHandZero";
	public static final String CompZeroString = "CompHandZero";
	
	// PID Constants
	public static final double proportional      	= 10.0;   	// AP WAG
	public static final double integral         	= 0.000; 	// AP WAG
	public static final double derivative        	= 0.000;   	// JR 3/15/19
	public static final double feedforward          = 5.0;   	// JR 3/16/19

}	