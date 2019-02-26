

package org.usfirst.frc330.constants;

public final class HandConst {
	
	private HandConst(){}

	public static final double tolerance		= 2.0; 		//WAG AP 2/24/19

	//angles relative to the ground (degrees)
	public static final double upperHardStop 		= 57.0;		//ejo 2.2.19
	public static final double defense 			= 53.0; 	//AP 2/24/19
	//public static final double defense 			= -30.0; 	//AP 2/24/19
	public static final double hatchPlacementLow 		= 0.0; 		//ejo 2.2.19
	public static final double hatchPlacementMid 		= 0.0; 		//AP 2/10/19
	public static final double hatchPlacementHigh 		= 0.0; 		//AP 2/10/19
	public static final double ballPlacementLow 		= 32.74; 		//AP 2/24/19
	public static final double ballPlacementMid 		= 32.74; 		//AP 2/24/19
	public static final double ballPlacementHigh 		= 32.74; 	//AP 2/24/19
	public static final double hatchPickup	 			= -7.0; 		//AP 2/9/19
	public static final double postHatchPickup			= 0.0; 		//AP 2/9/19
	public static final double ballPickupGround 		= -50.0;	//ejo 2.2.19
	public static final double ballPickupHumanPlayer	= -50.0;	//ejo 2.25.18
	public static final double lowerHardStop			= -50.0;		//AP 2/24/19  -53.2 is actual hard stop


	//TODO temporary use versaplanetary. Real robot will be 1.0;
	public static final double encoderGearRatio = 1.0;	  // JR Practice Robot 2/18/19

	public static final int    CAN_Timeout					= 10; // AP 2018 value
	public static final int    CAN_Timeout_No_Wait      	= 0;  // AP 2018 value

	public static final double VoltageRampRate				= 0.0;	// JR disable voltage ramp 2/19/19
	public static final double MaxOutputPercent				= 0.4;	// AP Testing Value

	
	// Motion Magic Constants
	public static final int velocityLimit              = 2000;   // AP 2018 value
	public static final int accelLimit                 = 6000;   // AP 2018 value

	public static final double GamepadDeadZone			= 0.1; //AP 2/17/19
	
	public static final String PracticeZeroString = "PracticeHandZero";
	public static final String CompZeroString = "CompHandZero";
	
	// PID Constants
	public static final double proportional      	= 10.0;   	// AP WAG
	public static final double integral         	= 0.000; 	// AP WAG
	public static final double derivative        	= 40.0;   	// AP WAG
	public static final double feedforward          = 0.0;   	// AP WAG

}	