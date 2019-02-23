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

import org.usfirst.frc330.util.*;
import org.usfirst.frc330.util.Logger.Severity;
import org.usfirst.frc330.wpilibj.BBDoubleSolenoid;
import org.usfirst.frc330.Robot;
import org.usfirst.frc330.commands.*;
import org.usfirst.frc330.constants.LiftConst;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.command.Subsystem;
import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.RobotController;

/**
 *
 */
public class Lift extends Subsystem {

	boolean calibrated = false; //Has the encoder been properly zeroed?
	boolean lockout = false;

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private WPI_TalonSRX lift1;
    private WPI_TalonSRX lift2;
    private WPI_TalonSRX lift3;
    private DigitalInput magLimitLift;
    private BBDoubleSolenoid climbPin;
    private WPI_TalonSRX pogo;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    public Lift() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        lift1 = new WPI_TalonSRX(6);
        
        
        
        lift2 = new WPI_TalonSRX(1);
        
        
        
        lift3 = new WPI_TalonSRX(2);
        
        
        
        magLimitLift = new DigitalInput(8);
        addChild("MagLimitLift",magLimitLift);
        
        
        climbPin = new BBDoubleSolenoid(0, 4, 5);
        addChild("ClimbPin",climbPin);
        
        
        pogo = new WPI_TalonSRX(5);
        
        
        

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS

        // Setup CAN Talons
        lift1.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, LiftConst.CAN_Timeout);
        lift1.setInverted(true); // Set true if the motor direction does not match the sensor direction
		lift1.setSensorPhase(true);
		pogo.setInverted(false);
		pogo.setInverted(false);
        setPIDConstants(LiftConst.proportional, LiftConst.integral, LiftConst.derivative, LiftConst.feedforward, true);
        setLiftAbsoluteTolerance(LiftConst.tolerance);
        
        // Limits are now set and enabled after calibration
        // lift1 is considered the main controller, 2 and 3 follow 
        lift1.configForwardSoftLimitEnable(false, 0); //False until after calibration
        lift1.configReverseSoftLimitEnable(false, 0);
        lift1.setNeutralMode(NeutralMode.Brake);
        lift1.configOpenloopRamp(LiftConst.VoltageRampRate, LiftConst.CAN_Timeout);
        lift1.configPeakOutputForward(LiftConst.MaxOutputPercent, LiftConst.CAN_Timeout);
        lift1.configPeakOutputReverse(-LiftConst.MaxOutputPercent, LiftConst.CAN_Timeout);
        lift1.configNominalOutputForward(0, LiftConst.CAN_Timeout);	
        lift1.configNominalOutputReverse(0, LiftConst.CAN_Timeout);
        lift1.configForwardLimitSwitchSource(RemoteLimitSwitchSource.Deactivated, LimitSwitchNormal.Disabled, 0, 0);
        lift1.configReverseLimitSwitchSource(RemoteLimitSwitchSource.Deactivated, LimitSwitchNormal.Disabled, 0, 0);
        lift1.configMotionCruiseVelocity(LiftConst.velocityLimit, LiftConst.CAN_Timeout);
        lift1.configMotionAcceleration(LiftConst.accelLimit, LiftConst.CAN_Timeout);
        
		lift2.set(ControlMode.Follower, lift1.getDeviceID());
		lift2.configPeakOutputForward(LiftConst.MaxOutputPercent, LiftConst.CAN_Timeout);
        lift2.configPeakOutputReverse(-LiftConst.MaxOutputPercent, LiftConst.CAN_Timeout);
        lift2.configForwardSoftLimitEnable(false, LiftConst.CAN_Timeout);
        lift2.configReverseSoftLimitEnable(false, LiftConst.CAN_Timeout);
        lift2.setInverted(false);
        lift2.setNeutralMode(NeutralMode.Brake);
        
		lift3.set(ControlMode.Follower, lift1.getDeviceID());
		lift3.configPeakOutputForward(LiftConst.MaxOutputPercent, LiftConst.CAN_Timeout);
        lift3.configPeakOutputReverse(-LiftConst.MaxOutputPercent, LiftConst.CAN_Timeout);
        lift3.configForwardSoftLimitEnable(false, LiftConst.CAN_Timeout);
        lift3.configReverseSoftLimitEnable(false, LiftConst.CAN_Timeout);
        lift3.setNeutralMode(NeutralMode.Brake);
		lift3.setInverted(true);
		
		pogo.configForwardSoftLimitEnable(false, 0); //False until after calibration
        pogo.configReverseSoftLimitEnable(false, 0);
        pogo.setNeutralMode(NeutralMode.Coast);
        pogo.configOpenloopRamp(LiftConst.PogoVoltageRampRate, LiftConst.CAN_Timeout);
        pogo.configPeakOutputForward(LiftConst.PogoMaxOutputPercent, LiftConst.CAN_Timeout);
        pogo.configPeakOutputReverse(-LiftConst.PogoMaxOutputPercent, LiftConst.CAN_Timeout);
        pogo.configNominalOutputForward(0, LiftConst.CAN_Timeout);	
        pogo.configNominalOutputReverse(0, LiftConst.CAN_Timeout);
        pogo.configForwardLimitSwitchSource(RemoteLimitSwitchSource.Deactivated, LimitSwitchNormal.Disabled, 0, 0);
        pogo.configReverseLimitSwitchSource(RemoteLimitSwitchSource.Deactivated, LimitSwitchNormal.Disabled, 0, 0);
        pogo.configMotionCruiseVelocity(LiftConst.PogoVelocityLimit, LiftConst.CAN_Timeout);
		pogo.configMotionAcceleration(LiftConst.PogoAccelLimit, LiftConst.CAN_Timeout);
		
		pogo.config_kP(0, LiftConst.PogoP, LiftConst.CAN_Timeout);
    	pogo.config_kI(0, LiftConst.PogoI, LiftConst.CAN_Timeout);
    	pogo.config_kD(0, LiftConst.PogoD, LiftConst.CAN_Timeout);
    	pogo.config_kF(0, LiftConst.PogoF, LiftConst.CAN_Timeout);
        
        
        //set feedback frame so that getClosedLoopError comes faster then 160ms
        lift1.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, LiftConst.CAN_Status_Frame_13_Period, LiftConst.CAN_Timeout);
        lift1.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, LiftConst.CAN_Status_Frame_10_Period, LiftConst.CAN_Timeout);

        //------------------------------------------------------------------------------
        // Logging
        //------------------------------------------------------------------------------
        CSVLoggable temp = new CSVLoggable(true) {
			public double get() { return getPosition(); }
		};
		CSVLogger.getInstance().add("LiftPosition", temp);
		
		temp = new CSVLoggable(true) {
			public double get() { return getVelocity();}
		};
		CSVLogger.getInstance().add("LiftVelocity", temp);
		
		temp = new CSVLoggable(true) {
			public double get() { return getOutput(); }
		};
		CSVLogger.getInstance().add("LiftOutput", temp);
		
		temp = new CSVLoggable(true) {
			public double get() { return getSetpoint(); }
		};
		CSVLogger.getInstance().add("LiftSetpoint", temp);

		temp = new CSVLoggable(true) {
			public double get() {
				if( getCalibrated()) {
					return 1.0;
				}
				else
					return 0.0;}
		};
		CSVLogger.getInstance().add("LiftCalibrated", temp);

		// Pogo Logging
		temp = new CSVLoggable(true) {
			public double get() { return getPogoPosition(); }
		};
		CSVLogger.getInstance().add("PogoPosition", temp);
		
		temp = new CSVLoggable(true) {
			public double get() { return getPogoOutput(); }
		};
		CSVLogger.getInstance().add("PogoOutput", temp);

		temp = new CSVLoggable(true) {
			public double get() { return getPogoSetpoint(); }
		};
		CSVLogger.getInstance().add("PogoSetpoint", temp);
    }

    @Override
    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        setDefaultCommand(new ManualLift());

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }

    @Override
    public void periodic() {
		if(!calibrated) {
    		if(!magLimitLift.get()) {
    			lift1.setSelectedSensorPosition(0, 0, LiftConst.CAN_Timeout);
    			lift1.disable();
    			calibrated = true;
    			lift1.configForwardSoftLimitThreshold(inchesToTicks(LiftConst.upperLimit), LiftConst.CAN_Timeout_No_Wait);
    	        lift1.configReverseSoftLimitThreshold(inchesToTicks(LiftConst.lowerLimit), LiftConst.CAN_Timeout_No_Wait);
    			lift1.configForwardSoftLimitEnable(true, LiftConst.CAN_Timeout_No_Wait);
    	        lift1.configReverseSoftLimitEnable(true, LiftConst.CAN_Timeout_No_Wait);
    		}
    	}
    }

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CMDPIDGETTERS


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CMDPIDGETTERS

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    //------------------------------------------------------------------------------
    // GET Methods
    //------------------------------------------------------------------------------
    
    double tempInfo;
    
    public double getPosition() {
    	return ticksToInches(lift1.getSelectedSensorPosition(0)); //arg 0 is primary PID
    }
    
    public double getVelocity() {
    	return lift1.getSelectedSensorVelocity(0);
    }
    
    public double getOutput() {
    	return lift1.getMotorOutputPercent() * RobotController.getBatteryVoltage();
    }
    
    public double getSecondOutput() {
    	return lift1.getMotorOutputPercent() * RobotController.getBatteryVoltage();
    }
    
    public double getThirdOutput() {
    	return lift1.getMotorOutputPercent() * RobotController.getBatteryVoltage();
    }
    
    public double getSetpoint() {
		ControlMode mode = lift1.getControlMode();
    	if(mode == ControlMode.Position || mode == ControlMode.Velocity || mode == ControlMode.MotionMagic) {
    		return ticksToInches(lift1.getClosedLoopTarget(0));
    	}
    	else {
    		return 999;
    	}
    }
    
    public boolean getCalibrated() {
    	return calibrated;
    }
    
    public ControlMode getMode() {
    	return lift1.getControlMode();
	}
	
	//Pogo Get
	public double getPogoPosition() {
    	return ticksToInches_Pogo(pogo.getSelectedSensorPosition(0)); //arg 0 is primary PID
    }
    
    public double getPogoVelocity() {
    	return pogo.getSelectedSensorVelocity(0);
    }
    
    public double getPogoOutput() {
    	return pogo.getMotorOutputPercent() * RobotController.getBatteryVoltage();
	}
	
	public double getPogoSetpoint() {
    	if(pogo.getControlMode() == ControlMode.Position || pogo.getControlMode() == ControlMode.MotionMagic) {
    		return ticksToInches_Pogo(pogo.getClosedLoopTarget(0));
    	}
    	else {
    		return 999;
    	}
    }
    
    //------------------------------------------------------------------------------
    // SET Methods
    //------------------------------------------------------------------------------
    
    public void setUncalibrated() {
		this.calibrated = false;
    }
    
    public void setPIDConstants (double P, double I, double D, double F, boolean timeout)
	{
    	if(timeout) {
    		//assume using main PID loop (index 0)
    		lift1.config_kP(0, P, LiftConst.CAN_Timeout);
    		lift1.config_kI(0, I, LiftConst.CAN_Timeout);
    		lift1.config_kD(0, D, LiftConst.CAN_Timeout);
    		lift1.config_kF(0, F, LiftConst.CAN_Timeout);
    	}
    	else {
	    	//assume using main PID loop (index 0)
			lift1.config_kP(0, P, LiftConst.CAN_Timeout_No_Wait);
			lift1.config_kI(0, I, LiftConst.CAN_Timeout_No_Wait);
			lift1.config_kD(0, D, LiftConst.CAN_Timeout_No_Wait);
			lift1.config_kF(0, F, LiftConst.CAN_Timeout_No_Wait);
    	}
	
        Logger.getInstance().println("Lift PIDF set to: " + P + ", " + I + ", " + D + ", " + F, Severity.INFO);
	}

    public void setLiftPosition(double setpoint) {
    	if(calibrated) {
    		if(setpoint > LiftConst.upperLimit) {
    			lift1.set(ControlMode.MotionMagic, inchesToTicks(LiftConst.upperLimit));
    			Logger.getInstance().println("Lift setpoint request above upper limit: " + setpoint, Logger.Severity.WARNING);
    		}
    		else if(setpoint < LiftConst.lowerLimit) {
    			lift1.set(ControlMode.MotionMagic, inchesToTicks(LiftConst.lowerLimit));
    			Logger.getInstance().println("Lift setpoint request below lower limit: " + setpoint, Logger.Severity.WARNING);
    		}
    		else {
    			lift1.set(ControlMode.MotionMagic, inchesToTicks(setpoint));
    		}
    	}
    	else {
    		Scheduler.getInstance().add(new CalibrateLift());
    	}
    }
    
    public void setThrottle(double output) {
        if(calibrated) {
        	lift1.set(ControlMode.PercentOutput, output);
        }
        else {
        	Scheduler.getInstance().add(new CalibrateLift());
        }
	}
	
	public void setPogoDistance(double setpoint){
		pogo.set(ControlMode.MotionMagic, inchesToTicks_Pogo(setpoint));
	}
    
    //------------------------------------------------------------------------------
    // Support Methods
    //------------------------------------------------------------------------------
    private double ticksToInches(int ticks) {
    	return ((double)ticks / (double)LiftConst.ticksPerRev * LiftConst.inchesPerRev);
    }

    private double ticksToInches(double ticks) {
    	return ((double)ticks / (double)LiftConst.ticksPerRev * LiftConst.inchesPerRev);
	}
	
	private double ticksToInches_Pogo(double ticks) {
    	return ((double)ticks / (double)LiftConst.ticksPerRev * LiftConst.PogoInchesPerRev);
	}
	
	public int inchesToTicks_Pogo(double inches) {
		return (int)(inches*LiftConst.ticksPerRev/LiftConst.PogoInchesPerRev);
	}
    
    //Methods to check if the lift is on target
    public boolean onLiftTarget() {
    	double error = ticksToInches(lift1.getClosedLoopError(0));
        return (Math.abs(error) < tolerance);
    }
    
    double tolerance;
    
    public void setLiftAbsoluteTolerance(double absvalue) {
    	tolerance = absvalue;
	}

	public void stopLift() {
		lift1.disable();
		Logger.getInstance().println("Lift disabled", Logger.Severity.INFO);		
	}

	public boolean getLiftOnTarget() {
		double error = getSetpoint() - getPosition();
    	return (Math.abs(error) < tolerance);
	}
	
	
	public void calibrateMove() {
    	if(!calibrated) {
    		lift1.set(ControlMode.PercentOutput, LiftConst.calibrationSpeed);
    	}
    }
	
	public int inchesToTicks(double inches) {
		return (int)(inches*LiftConst.ticksPerRev/LiftConst.inchesPerRev);
	}
	
	public double getLift1FirmwareVersion() {
		int firmwareVersion = lift1.getFirmwareVersion();
		return ((firmwareVersion & 0xFF00) >> 8) + (firmwareVersion & 0xFF) / 100.0;
	}
	
	public double getLift2FirmwareVersion() {
		int firmwareVersion = lift2.getFirmwareVersion();
		return ((firmwareVersion & 0xFF00) >> 8) + (firmwareVersion & 0xFF) / 100.0;
	}
	
	public double getLift3FirmwareVersion() {
		int firmwareVersion = lift3.getFirmwareVersion();
		return ((firmwareVersion & 0xFF00) >> 8) + (firmwareVersion & 0xFF) / 100.0;
	}

	public void deployClimbPin() {
		climbPin.set(DoubleSolenoid.Value.kForward);
	}

	public void retractClimbPin() {
		climbPin.set(DoubleSolenoid.Value.kReverse);
	}

	public void engageLockout() {
		this.lockout = true;
	}

	public void disableLockout() {
		this.lockout = false;
	}

	public void manualLift() {
		double gamepadCommand = -Robot.oi.gamePad.getRawAxis(1);
    	double position;
    	
    	if (Math.abs(gamepadCommand) > LiftConst.GamepadDeadZone) {
    		this.setThrottle(gamepadCommand/Math.abs(gamepadCommand)*Math.pow(gamepadCommand, 2)*0.4); //scaled to 0.4 max
    	}
    	else if (lift1.getControlMode() != ControlMode.Position && lift1.getControlMode() != ControlMode.MotionMagic) {
			position = getPosition();
			setLiftPosition(position);
    	}  	
	}
	
/* 	public boolean hasMagLimitChangedState() {
		boolean magEncoderCurrentState = magSwitch.get();
		 if(magEncoderCurrentState != magEncoderPrevState) {
			 magEncoderPrevState = magEncoderCurrentState;
			 return true;
		 }
		 else{
			 magEncoderPrevState = magEncoderCurrentState;
			 return false;
		 }
	} */

}

