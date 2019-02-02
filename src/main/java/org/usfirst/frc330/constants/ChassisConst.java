

package org.usfirst.frc330.constants;

//import org.usfirst.frc330.commands.drivecommands.DrivePIDGains;
//TODO figure out drivecommands - ejo
import org.usfirst.frc330.wpilibj.PIDGains;

public final class ChassisConst {

    private ChassisConst(){}

    //Encoder Distance Constants
    public static final double  wheelDiameter        = 6.0;       //ejo 2.2.19
    public static final double  pulsePerRevolution   = 1024;
    public static final double  encoderGearRatio     = 36/12;   //ejo 2.2.19 (supplied by TD)
    public static final double  highGearRatio        = 8.75/1;  //ejo 2.2.19 (supplied by TD)
    public static final double  lowGearRatio         = 18/1;    //ejo 2.2.19 (supplied by TD)
    public static final double  Fudgefactor          = 0;       //TODO - Find the 2019 value - AP

    //Turn Gyro
	public static final int     gyroTolerancebuffer = 0;        //TODO - Find the 2019 value - AP
    public static final double  gyroTurnMin         = 0;        //TODO - Find the 2019 value - AP
       
}