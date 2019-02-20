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


import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.RemoteLimitSwitchSource;
// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.DigitalInput;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import org.usfirst.frc330.Robot;
import org.usfirst.frc330.commands.ManualHand;
import org.usfirst.frc330.constants.HandConst;
import org.usfirst.frc330.util.CSVLoggable;
import org.usfirst.frc330.util.CSVLogger;
import org.usfirst.frc330.util.Logger;
import org.usfirst.frc330.util.Logger.Severity;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 *
 */
public class Hand extends Subsystem {

    boolean lockout = false;
    String zeroString = HandConst.CompZeroString;

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private DigitalInput limitSwitch;
    private WPI_TalonSRX hand;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    boolean calibrated = false;
    int zero = 0;

    public Hand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        limitSwitch = new DigitalInput(6);
        addChild("limitSwitch",limitSwitch);
        
        
        hand = new WPI_TalonSRX(3);
        
        
        

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS

        hand.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, 0, HandConst.CAN_Timeout);
        hand.setInverted(false); // Set true if the motor direction does not match the sensor direction
        hand.setSensorPhase(false);

        if (!calibrated) {
            hand.configForwardSoftLimitEnable(false, HandConst.CAN_Timeout); //False until after calibration
            hand.configReverseSoftLimitEnable(false, HandConst.CAN_Timeout);
        } else {
            hand.configForwardSoftLimitThreshold(angleToTicks(HandConst.hardStop), HandConst.CAN_Timeout);
            hand.configReverseSoftLimitThreshold(angleToTicks(HandConst.ballPickup),  HandConst.CAN_Timeout);
            hand.configForwardSoftLimitEnable(true, HandConst.CAN_Timeout); //False until after calibration
            hand.configReverseSoftLimitEnable(true, HandConst.CAN_Timeout);
        }

        hand.setNeutralMode(NeutralMode.Brake);
        hand.configOpenloopRamp(HandConst.VoltageRampRate, HandConst.CAN_Timeout);
        hand.configPeakOutputForward(HandConst.MaxOutputPercent, HandConst.CAN_Timeout);
        hand.configPeakOutputReverse(-HandConst.MaxOutputPercent, HandConst.CAN_Timeout);
        hand.configNominalOutputForward(0, HandConst.CAN_Timeout);	
        hand.configNominalOutputReverse(0, HandConst.CAN_Timeout);
        hand.configForwardLimitSwitchSource(RemoteLimitSwitchSource.Deactivated, LimitSwitchNormal.Disabled, 0, 0);
        hand.configReverseLimitSwitchSource(RemoteLimitSwitchSource.Deactivated, LimitSwitchNormal.Disabled, 0, 0);
        hand.configMotionCruiseVelocity(HandConst.velocityLimit, HandConst.CAN_Timeout);
        hand.configMotionAcceleration(HandConst.accelLimit, HandConst.CAN_Timeout);

        if (Robot.frills.getIsPracticeRobot()) {
            zeroString = HandConst.PracticeZeroString;
        } else {
            zeroString = HandConst.CompZeroString;
        }

        zero = Preferences.getInstance().getInt(zeroString, 0);
        if (zero > 0)
            calibrated = true;

            CSVLoggable temp = new CSVLoggable(true) {
                public double get() { return getHandAngle(); }
            };
            CSVLogger.getInstance().add("HandAngle", temp);
            
            temp = new CSVLoggable(true) {
                public double get() { return getHandOutput(); }
            };
            CSVLogger.getInstance().add("HandOutput", temp);
            
            temp = new CSVLoggable(true) {
                public double get() { return getSetpoint(); }
            };
            CSVLogger.getInstance().add("HandSetpoint", temp);
    
            temp = new CSVLoggable(true) {
                public double get() {
                    if( getCalibrated()) {
                        return 1.0;
                    }
                    else
                        return 0.0;}
            };
            CSVLogger.getInstance().add("LiftCalibrated", temp);
    }

    @Override
    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        setDefaultCommand(new ManualHand());

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }

    @Override
    public void periodic() {
        // Put code here to be run every loop
        SmartDashboard.putNumber("HandTicks", hand.getSelectedSensorPosition());
        SmartDashboard.putNumber("HandAngle", ticksToAngle(hand.getSelectedSensorPosition()));
        SmartDashboard.putNumber("HandTicks2", angleToTicks(ticksToAngle(hand.getSelectedSensorPosition())));
    }

	public void stopHand() {
        hand.disable();
        Logger.getInstance().println("Hand disabled", Logger.Severity.INFO);
        //implemented 2.2.19 -ejo
    }
    
    
    //------------------------
    //      SET METHODS
    //------------------------

	public void setAngle(double angle) {
        //hand.set(mode, value);
    }
    

    //------------------------
    //      GET METHODS
    //------------------------

	public boolean getHandOnTarget() {
        double error = this.getSetpoint() - this.getHandAngle();
      	return (Math.abs(error) < HandConst.tolerance);
	}

	public double getSetpoint() {
		return (ticksToAngle((int)hand.getClosedLoopTarget()));
    }
    
    public double getHandOutput() {
        return hand.getMotorOutputVoltage();
    }

	public double getHandAngle() {
		return (ticksToAngle(hand.getSelectedSensorPosition()));
    }

    public double ticksToAngle(int ticks) {
        return (ticks - zero) * 360.0 * HandConst.encoderGearRatio / 4096.0 + HandConst.hardStop;
    }

    public int angleToTicks(double angle) {
        int ticks = (int)((angle-HandConst.hardStop) * 4096 /360.0 / HandConst.encoderGearRatio + 0.5);
        return ticks + zero;
    }

    public boolean getCalibrated() {
        return calibrated;
    }
    
    public void calibrate() {
        int newZero = hand.getSelectedSensorPosition();
        calibrated = true;
        Preferences.getInstance().putInt(zeroString, newZero);
        zero = newZero;
        hand.configForwardSoftLimitThreshold(angleToTicks(HandConst.hardStop), HandConst.CAN_Timeout_No_Wait);
        hand.configReverseSoftLimitThreshold(angleToTicks(HandConst.ballPickup),  HandConst.CAN_Timeout_No_Wait);
        hand.configForwardSoftLimitEnable(true, HandConst.CAN_Timeout_No_Wait); //False until after calibration
        hand.configReverseSoftLimitEnable(true, HandConst.CAN_Timeout_No_Wait);
        Logger.getInstance().println("Hand Calibrated: " + newZero, Severity.WARNING);
    }

	public void engageLockout() {
        this.lockout = true;
	}

	public void disableLockout() {
        this.lockout = false;
    }
    
    public double getHandFirmwareVersion() {
		int firmwareVersion = hand.getFirmwareVersion();
		return ((firmwareVersion & 0xFF00) >> 8) + (firmwareVersion & 0xFF) / 100.0;
    }
    
    public void manualHand() {
		double gamepadCommand = -Robot.oi.gamePad.getRawAxis(5);
    	double position;
    	
    	if (Math.abs(gamepadCommand) > HandConst.GamepadDeadZone) {
    		setThrottle(gamepadCommand/Math.abs(gamepadCommand)*Math.pow(gamepadCommand, 2)*0.4); //scaled to 0.4 max
    	}
    	else if (hand.getControlMode() != ControlMode.Position && hand.getControlMode() != ControlMode.MotionMagic) {
			//position = getHandAngle();
            //setAngle(position);
            setThrottle(0);
    	}  	
    }
    
    public void setThrottle(double output) {
        if(calibrated) {
        	hand.set(ControlMode.PercentOutput, output);
        }
	}

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CMDPIDGETTERS


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CMDPIDGETTERS

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

}

