// RobotBuilder Version: 2.0BB
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc330.subsystems;

import org.usfirst.frc330.Robot;
import org.usfirst.frc330.commands.*;
import org.usfirst.frc330.constants.ChassisConst;
import org.usfirst.frc330.util.CSVLoggable;
import org.usfirst.frc330.util.CSVLogger;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.filters.LinearDigitalFilter;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;
import org.usfirst.frc330.wpilibj.DummyPIDOutput;
import org.usfirst.frc330.wpilibj.MultiPIDController;


// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.SPI.Port;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import org.usfirst.frc330.wpilibj.BBDoubleSolenoid;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS


/**
 *
 */
public class Chassis extends Subsystem {
    public ShuffleboardTab shuffleboardTab = Shuffleboard.getTab("Chassis");

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private AHRS navX1;
    private BBDoubleSolenoid shifters;
    private Encoder driveEncoderRight;
    private Encoder driveEncoderLeft;
    private Spark rightDrive1;
    private Spark rightDrive2;
    private Spark rightDrive3;
    private SpeedControllerGroup rightDrive;
    private Spark leftDrive1;
    private Spark leftDrive2;
    private Spark leftDrive3;
    private SpeedControllerGroup leftDrive;
    private AnalogInput pressureSensor;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

	double  gyro_prevVal = 0.0;
    int     ctrRollOver  = 0;
    boolean fFirstUse    = true;
    double  left, right;
    double gyroComp = 0;
    
    public MultiPIDController gyroPID, leftDrivePID, rightDrivePID;
    private DummyPIDOutput gyroOutput, leftDriveOutput, rightDriveOutput;

    private double x = 0, y = 0;
    private double prevLeftEncoderDistance  = 0, prevRightEncoderDistance = 0;

    public Chassis() {
        super();
    	
    	PIDSource gyroSource = new PIDSource()
        {

			@Override
			public double pidGet()
			{
				return getAngle();
			}

			@Override
			public void setPIDSourceType(PIDSourceType pidSource)
			{
			}

			@Override
			public PIDSourceType getPIDSourceType()
			{
				return PIDSourceType.kDisplacement;
			}
        	
        };
        
        gyroOutput = new DummyPIDOutput();
        leftDriveOutput = new DummyPIDOutput();
        rightDriveOutput = new DummyPIDOutput();

        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        navX1 = new AHRS(Port.kMXP, (byte)50);
        addChild(navX1);
        
        
        shifters = new BBDoubleSolenoid(0, 0, 1);
        addChild("Shifters",shifters);
        
        
        driveEncoderRight = new Encoder(0, 1, false, EncodingType.k4X);
        addChild("DriveEncoderRight",driveEncoderRight);
        driveEncoderRight.setDistancePerPulse(1.0);
        driveEncoderRight.setPIDSourceType(PIDSourceType.kRate);
        
        driveEncoderLeft = new Encoder(2, 3, false, EncodingType.k4X);
        addChild("DriveEncoderLeft",driveEncoderLeft);
        driveEncoderLeft.setDistancePerPulse(1.0);
        driveEncoderLeft.setPIDSourceType(PIDSourceType.kRate);
        
        rightDrive1 = new Spark(0);
        addChild("rightDrive1",rightDrive1);
        rightDrive1.setInverted(false);
        
        rightDrive2 = new Spark(1);
        addChild("rightDrive2",rightDrive2);
        rightDrive2.setInverted(false);
        
        rightDrive3 = new Spark(2);
        addChild("rightDrive3",rightDrive3);
        rightDrive3.setInverted(false);
        
        rightDrive = new SpeedControllerGroup(rightDrive1, rightDrive2 , rightDrive3 );
        addChild("rightDrive",rightDrive);
        
        
        leftDrive1 = new Spark(3);
        addChild("leftDrive1",leftDrive1);
        leftDrive1.setInverted(false);
        
        leftDrive2 = new Spark(4);
        addChild("leftDrive2",leftDrive2);
        leftDrive2.setInverted(false);
        
        leftDrive3 = new Spark(5);
        addChild("leftDrive3",leftDrive3);
        leftDrive3.setInverted(false);
        
        leftDrive = new SpeedControllerGroup(leftDrive1, leftDrive2 , leftDrive3 );
        addChild("leftDrive",leftDrive);
        
        
        pressureSensor = new AnalogInput(0);
        addChild("pressureSensor",pressureSensor);
        
        

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        
    
		/////////////////////////////////////////////////////////////
        // PID Setup
        /////////////////////////////////////////////////////////////  
		gyroPID = new MultiPIDController(ChassisConst.GyroTurnLow, gyroSource,gyroOutput, 1,"Gyro");
        leftDrivePID = new MultiPIDController(ChassisConst.DriveLow, driveEncoderLeft, leftDriveOutput, 1,"LeftDrive");
        rightDrivePID = new MultiPIDController(ChassisConst.DriveLow, driveEncoderRight,rightDriveOutput, 1, "RightDrive");
        
        this.shuffleboardTab.add("gyroPID", gyroPID);
        this.shuffleboardTab.add("leftDrivePID", leftDrivePID);
        this.shuffleboardTab.add("rightDrivePID", rightDrivePID);
        

        /////////////////////////////////////////////////////////////
        // Encoder Setup
        /////////////////////////////////////////////////////////////  
        LinearDigitalFilter.movingAverage(gyroSource, ChassisConst.gyroTolerancebuffer);
        
        double pulsePerRevolutionLeft, pulsePerRevolutionRight;
    	pulsePerRevolutionRight = ChassisConst.pulsePerRevolution;
    	pulsePerRevolutionLeft = ChassisConst.pulsePerRevolution;
        
        double distanceperpulse = Math.PI*ChassisConst.wheelDiameter/pulsePerRevolutionLeft /
        		ChassisConst.encoderGearRatio * ChassisConst.Fudgefactor;

        driveEncoderRight.setReverseDirection(true);
        driveEncoderLeft.setDistancePerPulse(distanceperpulse);
        
        distanceperpulse = Math.PI*ChassisConst.wheelDiameter/pulsePerRevolutionRight /
        		ChassisConst.encoderGearRatio * ChassisConst.Fudgefactor;
        
        driveEncoderRight.setDistancePerPulse(distanceperpulse);

        //-----------------------------------------------------------------------
        // Logging
        //-----------------------------------------------------------------------
        CSVLoggable temp = new CSVLoggable(true) {
			public double get() { return driveEncoderLeft.getDistance(); }
    	};
    	CSVLogger.getInstance().add("DriveTrainDistanceL", temp);
    	
    	temp = new CSVLoggable(true) {
			public double get() { return driveEncoderRight.getDistance(); }
    	};
    	CSVLogger.getInstance().add("DriveTrainDistanceR", temp);
    	
    	temp = new CSVLoggable(true) {
			public double get() { return driveEncoderLeft.getRate(); }  		
    	};
    	CSVLogger.getInstance().add("DriveTrainRateL", temp);
    	
    	temp = new CSVLoggable(true) {
			public double get() { return driveEncoderRight.getRate(); }  		
    	};
    	CSVLogger.getInstance().add("DriveTrainRateR", temp);    	
    	
    	temp = new CSVLoggable() {
			public double get() { return leftDrive.get(); }  		
    	};
    	CSVLogger.getInstance().add("DriveTrainLeft", temp);
    	
    	temp = new CSVLoggable() {
			public double get() { return rightDrive.get(); }  		
    	};
    	CSVLogger.getInstance().add("DriveTrainRight", temp);
    	
    	temp = new CSVLoggable(true) {
			public double get() { return getAngle(); }  		
    	};    	
    	CSVLogger.getInstance().add("ChassisAngle", temp);
    	
    	temp = new CSVLoggable(true) {
			public double get() { return gyroPID.getSetpoint(); }  		
    	};    	
    	CSVLogger.getInstance().add("GyroSetpoint", temp);
    	
    	temp = new CSVLoggable(true) {
			public double get() { return getGyroComp(); }  		
    	};    	
    	CSVLogger.getInstance().add("GyroCompensation", temp);
    	
    	temp = new CSVLoggable(true) {
			public double get() { return navX1.isConnected() ? 1: 0; }  		
    	};    	
    	CSVLogger.getInstance().add("GyroIsConnected", temp);
    	
    	temp = new CSVLoggable(true) {
			public double get() { return navX1.isCalibrating() ? 1: 0; }  		
    	};    	
    	CSVLogger.getInstance().add("GyroIsCalibrating", temp);
    	
    	temp = new CSVLoggable(true) {
			public double get() { return getX(); }  		
    	};     	
    	CSVLogger.getInstance().add("ChassisX", temp);
    	
    	temp = new CSVLoggable(true) {
			public double get() { return getY(); }  		
    	};      	
    	CSVLogger.getInstance().add("ChassisY", temp);
    	
    	temp = new CSVLoggable(true) {
    		public double get() {return leftDrivePID.getError(); }
    	};
    	CSVLogger.getInstance().add("leftDrivePID Error", temp);
    	
    	temp = new CSVLoggable(true) {
			public double get() { return getPressure(); }  		
    	};  
    	CSVLogger.getInstance().add("Pressure", temp);
    	
    	temp = new CSVLoggable(true) {
			public double get() { 
				DoubleSolenoid.Value state = shifters.get();
				double state_int;
				if (state == DoubleSolenoid.Value.kForward)
					state_int = 1.0;
				else
					state_int = 0.0;
				return state_int;}  		
    	};  
    	CSVLogger.getInstance().add("Shifter", temp);
    }
	
	/////////////////////////////////////////////////////////////
	//GET methods
	/////////////////////////////////////////////////////////////   
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public double getPressure() {
		double pressure;
		pressure = 35*(pressureSensor.getAverageVoltage() - 0.5);
		//SmartDashboard.putNumber("Pressure Gauge", pressure);
		return (pressure);
	}
	
    public double getAngle() {  	
    	return navX1.getAngle();
    }
    
    public String getNavXFirmware() {
    	return navX1.getFirmwareVersion();
    }
    
    public double getSpeed() {
    	return (driveEncoderLeft.getRate() + driveEncoderRight.getRate()) / 2;
    }

    public double getGyroComp() {
    	return gyroComp;
    }  

    public boolean isGyroCalibrating() {
		return navX1.isCalibrating();
    }
    
    public double getLeftDistance() 
	{
		return driveEncoderLeft.getDistance();
	}

	public double getRightDistance() {
		return driveEncoderRight.getDistance();
	}

    /////////////////////////////////////////////////////////////
	// SET methods
	/////////////////////////////////////////////////////////////  
    public void setGyroComp(double compensation) {
    	navX1.setAngleAdjustment(compensation);    	
    }

    public void setXY(double x, double y) {
        this.x = x;
        this.y = y;
    } /* End setXY */
    

    /////////////////////////////////////////////////////////////
	//Control methods
	/////////////////////////////////////////////////////////////
    public void pidDriveAuto()
    {
        double left, right, gyroValue, gyroMin;
        
        if (DriverStation.getInstance().isDisabled()) {
            stopDrive();
        }
        else {      	
        	if (Math.abs(gyroOutput.getOutput()) > 0 && gyroPID.isEnabled() && !leftDrivePID.isEnabled() && !rightDrivePID.isEnabled()) 
        		gyroMin = ChassisConst.gyroTurnMin * Math.signum(gyroOutput.getOutput());
        	else
        		gyroMin = 0;
        	
        	gyroValue = Math.signum(gyroOutput.getOutput()) * Math.min(Math.abs(gyroOutput.getOutput()+gyroMin) , 1.0);
        	left = this.left+leftDriveOutput.getOutput() + gyroValue;
            right = this.right+rightDriveOutput.getOutput() - gyroValue;
            drive(left, right);
            this.left = 0;
            this.right = 0;
        }
    } /* End pidDriveAuto() */
    
    public void shiftHigh() {
    	shifters.set(DoubleSolenoid.Value.kReverse);
    }
    
    public void shiftLow() {
    	shifters.set(DoubleSolenoid.Value.kForward);
    }

    public void tankDrive(Joystick leftJoystick, Joystick rightJoystick) {
        double leftTemp = -leftJoystick.getY();
        double rightTemp = -rightJoystick.getY();
        
        if(Math.abs(leftTemp) > ChassisConst.DeadBand){
            left = -leftJoystick.getY();
        }
        if(Math.abs(rightTemp) > ChassisConst.DeadBand){
            right = -rightJoystick.getY();
        }
	}
        
    public void tankDrive(double left, double right) {
        this.left = left;
        this.right = right;
    }

    
    public void stopDrive() {
        if (gyroPID.isEnabled())
            gyroPID.reset();
        if (leftDrivePID.isEnabled())
            leftDrivePID.reset();
        if (rightDrivePID.isEnabled())
            rightDrivePID.reset();        
       
        tankDrive(0, 0);  
    }

    public void pidDrive() {
	    double left, right;
	    if (DriverStation.getInstance().isDisabled()) {
	        stopDrive();
	    }
	    else {
	        left = this.left+leftDriveOutput.getOutput() + gyroOutput.getOutput();
	        right = this.right+rightDriveOutput.getOutput() - gyroOutput.getOutput();
	        drive(left, right);
	        this.left = 0;
	        this.right = 0;
	    }
    }
    
    private void drive(double left, double right) {
        leftDrive1.set(left);
        leftDrive2.set(left);
        leftDrive3.set(left);
        
        rightDrive1.set(-right);
        rightDrive2.set(-right);
        rightDrive3.set(-right);
    }
    
    public void resetPosition() {
    	driveEncoderLeft.reset();
    	driveEncoderRight.reset();
        navX1.zeroYaw();
        fFirstUse = true;
        ctrRollOver = 0;
        setXY(0,0);
        this.prevLeftEncoderDistance = 0;
        this.prevRightEncoderDistance = 0;
    } /* End resetPosition */

    /////////////////////////////////////////////////////////////
	// Other methods
	/////////////////////////////////////////////////////////////
    @Override
    public void periodic() {
    }

    @Override
    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        setDefaultCommand(new TankDrive());

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

    }

	public void calcXY()
	{
		 double distance, 
		 	    leftEncoderDistance, 
		 	    rightEncoderDistance, 
		 	    gyroAngle;
		 
		 leftEncoderDistance  = driveEncoderLeft.getDistance();
		 rightEncoderDistance = driveEncoderRight.getDistance();
		 gyroAngle = getAngle();
		 distance =  ((leftEncoderDistance - prevLeftEncoderDistance) + (rightEncoderDistance - prevRightEncoderDistance))/2;
		 x = x + distance * Math.sin(Math.toRadians(gyroAngle));
		 y = y + distance * Math.cos(Math.toRadians(gyroAngle));
		 prevLeftEncoderDistance  = leftEncoderDistance;
		 prevRightEncoderDistance = rightEncoderDistance;
	}	
}

