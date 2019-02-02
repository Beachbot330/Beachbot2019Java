

package org.usfirst.frc330.constants;

//import org.usfirst.frc330.commands.drivecommands.DrivePIDGains;
//TODO figure out drivecommands - ejo
import org.usfirst.frc330.wpilibj.PIDGains;

public final class ChassisConst {

    private ChassisConst(){}

    //Encoder Distance Constants
    public static final double  wheelDiameter        = 0;       //TODO - Get Correct Value from Shane - AP
    public static final double  pulsePerRevolution   = 1024;
    public static final int     encoderGearRatio     = 0;       //TODO - Get Correct Value from Shane - AP
    public static final int     gearRatio            = 0;       //TODO - Get Correct Value from Shane - AP
    public static final double  Fudgefactor          = 0;       //TODO - Find the 2019 value - AP

    //Turn Gyro
	public static final int     gyroTolerancebuffer = 0;        //TODO - Find the 2019 value - AP
    public static final double  gyroTurnMin         = 0;        //TODO - Find the 2019 value - AP
       
}