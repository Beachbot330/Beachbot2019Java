

package org.usfirst.frc330.constants;

public final class LiftConst {
	
	private LiftConst(){}

	public static final double defense			= 0.0;		//ejo 2.2.19
	public static final double LowHardStop 		= 0.0;		//ejo 2.2.19
	public static final double HighHardStop 	= 30.75;	//ejo 2.2.19

	public static final double DeployBallLow	= 5.0;		//ejo 2.2.19
	public static final double DeployBallMid	= 18.5;		//ejo 2.2.19
	public static final double DeployBallHi		= 30.75;	//ejo 2.2.19 (might be changed)

	public static final double DeployHatchLow	= 0.0;		//TODO: Get Value from Shane -AP
	public static final double DeployHatchMid	= 13.5;		//ejo 2.2.19
	public static final double DeployHatchHi	= 27.5;		//ejo 2.2.19

	public static final double HatchPickup 		= 0.0;		//ejo 2.2.19

	public static final double tolerance		= 0; //TODO: find 2019 value -AP

	//Encoder Distance Constants
    public static final double  wheelDiameter        = 1.751;       //TODO - Get Correct Value from Shane - EJO
    public static final double  pulsePerRevolution   = 1024;
    public static final int     encoderGearRatio     = 0;       //TODO - Get Correct Value from Shane - EJO
    public static final int     gearRatio            = 0;       //TODO - Get Correct Value from Shane - EJO
    public static final double  Fudgefactor          = 0;       //TODO - Find the 2019 value - ejo

	//encoder is on the output shaft, so it should be gearRation should be 1:1


}	