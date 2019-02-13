

package org.usfirst.frc330.constants;


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
	public static final int     gyroTolerancebuffer = 5;        //TODO - Find the 2019 value - AP
    public static final double  gyroTurnMin         = 0.2;        //TODO - Find the 2019 value - AP

    //Distances
    public static final double  PlatformDrive   = 18;   //AP 2/9/19

    // PID MaxOutputs
	public static final double backupThrottle       	  = 0.5;     //TODO - Find the 2019 value - AP
	public static final double defaultMaxOutput     	  = 0.9;    //TODO - Find the 2019 value - AP
	public static final double defaultMaxOutputStep 	  = 0.05;   //TODO - Find the 2019 value - AP
    public static final double defaultMinStartOutput      = 0.20;   //TODO - Find the 2019 value - AP
    
    //PID Gains
    public static final PIDGains DriveLow	   = new PIDGains(0.100,0,0.000,0,defaultMaxOutput,defaultMaxOutputStep,defaultMinStartOutput, "DriveLow");    //TODO - Find the 2019 value - AP
    public static final PIDGains DriveHigh     = new PIDGains(0.100,0,0.80,0,defaultMaxOutput,defaultMaxOutputStep, defaultMinStartOutput,"DriveHigh");   //TODO - Find the 2019 value - AP
    public static final PIDGains GyroTurnLow   = new PIDGains(0.020,0,0.05,0,0.5,1,0,"GyroTurnLow");  //TODO - Find the 2019 value - AP
    public static final PIDGains GyroTurnHigh  = new PIDGains(0.030,0,0.000,0,1,1,0, "GyroTurnHigh"); //TODO - Find the 2019 value - AP
    public static final PIDGains GyroDriveLow  = new PIDGains(0.010,0,0.000,0,1,1,0, "GyroDriveLow"); //TODO - Find the 2019 value - AP
    public static final PIDGains GyroDriveHigh = new PIDGains(0.01,0,0.000,0,1,1,0, "GyroDriveHigh"); //TODO - Find the 2019 value - AP
       
}