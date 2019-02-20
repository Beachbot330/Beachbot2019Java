

package org.usfirst.frc330.constants;

public final class HandConst {
	
	private HandConst(){}

	public static final double tolerance		= 0; 		//WAG -ejo 2.2.19

	//angles relative to the ground (degrees)
	public static final double defense 			= 57.0; 	//ejo 2.2.19
	public static final double hardStop 		= 57.0;		//ejo 2.2.19
	public static final double hatchPlacementLow 	= 0.0; 		//ejo 2.2.19
	public static final double hatchPlacementMid 	= 0.0; 		//AP 2/10/19
	public static final double hatchPlacementHigh 	= 0.0; 		//AP 2/10/19
	public static final double ballPlacementLow 	= 0.0; 		//AP 2/10/19
	public static final double ballPlacementMid 	= 0.0; 		//AP 2/10/19
	public static final double ballPlacementHigh 	= 35.0; 	//ejo 2.2.19
	public static final double hatchPickup	 	= -2.0; 		//AP 2/9/19
	public static final double postHatchPickup	= 0.0; 		//AP 2/9/19
	public static final double ballPickup 		= -46.0;	//ejo 2.2.19

	//TODO temporary use versaplanetary. Real robot will be 1.0;
	public static final double encoderGearRatio = 1.0;	  // JR Practice Robot 2/18/19

	public static final int    CAN_Timeout					= 10; // AP 2018 value
	public static final int    CAN_Timeout_No_Wait      	= 0;  // AP 2018 value

	public static final double VoltageRampRate				= 12;	// AP 2018 value
	public static final double MaxOutputPercent				= 0.3;	// AP Testing Value

	
	// Motion Magic Constants
	public static final int velocityLimit              = 2000;   // AP 2018 value
	public static final int accelLimit                 = 6000;   // AP 2018 value

	public static final double GamepadDeadZone			= 0.1; //AP 2/17/19
	
	public static final String PracticeZeroString = "PracticeHandZero";
    public static final String CompZeroString = "CompHandZero";

}	