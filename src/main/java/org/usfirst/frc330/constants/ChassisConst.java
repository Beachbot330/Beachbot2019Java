

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
    public static final double  Fudgefactor          = 1.000;       //3/10/19 AP

    //Turn Gyro
	public static final int     gyroTolerancebuffer = 5;        //TODO - Find the 2019 value - AP
    public static final double  gyroTurnMin         = 0.02;        //TODO - Find the 2019 value - AP

    //Distances
    public static final double  PlatformDrive   = 18;   //AP 2/9/19

    // PID MaxOutputs
	public static final double backupThrottle       	  = 0.5;     //TODO - Find the 2019 value - AP
	public static final double defaultMaxOutput     	  = 0.9;    //TODO - Find the 2019 value - AP
	public static final double defaultMaxOutputStep 	  = 0.04;   //TODO - Find the 2019 value - AP
    public static final double defaultMinStartOutput      = 0.00;   // AP 3/10/19
    
    //PID Gains
    public static final PIDGains DriveLow	   = new PIDGains(0.10, 0, 0.00, 0, defaultMaxOutput, defaultMaxOutputStep, defaultMinStartOutput, "DriveLow");    //AP 3/10/19
    public static final PIDGains DriveLowSlow  = new PIDGains(0.10, 0, 0.00, 0, 0.4, defaultMaxOutputStep, defaultMinStartOutput, "DriveLow");    //AP 4/05/19
    public static final PIDGains DriveHigh     = new PIDGains(0.04, 0, 0.30, 0, defaultMaxOutput, 0.03, defaultMinStartOutput, "DriveHigh");   //AP 4/9/19
    public static final PIDGains DriveHighSlow = new PIDGains(0.04, 0, 0.30, 0, 0.4, defaultMaxOutputStep, defaultMinStartOutput, "DriveHighSlow");   //AP 3/26/19
    public static final PIDGains GyroTurnLow   = new PIDGains(0.02, 0, 0.10, 0, 0.5, 1, 0, "GyroTurnLow");  //AP 3/10/19
    public static final PIDGains GyroTurnHigh  = new PIDGains(0.025,0, 0.20, 0, 0.5, 1,0, "GyroTurnHigh"); //ejo 4.13.19
    public static final PIDGains GyroDriveLow  = new PIDGains(0.010,0,0.000,0,1,1,0, "GyroDriveLow"); //TODO - Find the 2019 value - AP
    public static final PIDGains GyroDriveHigh = new PIDGains(0.008,0,0.000,0,1,1,0, "GyroDriveHigh"); //ejo 4.13.19

    public static final PIDGains LimelightTurnLow   = new PIDGains(0.030, 0, 0.05, 0, 0.3, 1, 0, "LimelightTurnLow");  //AP 3/16/19
    public static final PIDGains LimelightTurnHigh   = new PIDGains(0.025,0,0.05,0,0.3,1,0,"GyroTurnLow");  //TODO - Find the 2019 value - AP
    public static final PIDGains LimelightDriveLow  = new PIDGains(0.012,0,0.000,0,1,1,0, "GyroDriveLow"); //TODO - Find the 2019 value - AP
    public static final PIDGains LimelightDriveHigh = new PIDGains(0.012,0,0.000,0,1,1,0, "GyroDriveHigh"); //TODO - Find the 2019 value - AP
       
    public static final double DeadBand = 0.1;

    public static final double LowGearMaxSpeed = 8.0; //ft/sec JR 2/24/19
}