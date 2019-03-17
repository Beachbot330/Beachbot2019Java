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
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 *
 */
public class Hand extends Subsystem {

    public ShuffleboardTab shuffleboardTab = Shuffleboard.getTab("Hand");
    boolean lockout = false;
    String zeroString = HandConst.CompZeroString;
    boolean calibrated = false;
    int zero = 0;

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private DigitalInput limitSwitch;
    private WPI_TalonSRX hand;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    public Hand() {

        //Check if we are calibrated
        if (Robot.frills.getIsPracticeRobot()) {
            zeroString = HandConst.PracticeZeroString;
        } else {
            zeroString = HandConst.CompZeroString;
        }
        calibrated = Preferences.getInstance().getBoolean("HandCalibrated", false);
        
        if (calibrated)
            zero = Preferences.getInstance().getInt(zeroString, 0);

        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        limitSwitch = new DigitalInput(6);
        addChild("limitSwitch",limitSwitch);
        
        
        hand = new WPI_TalonSRX(3);
        
        
        

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS

        /////////////////////////////////////////////////////////////
        // CAN Talon Setup
        /////////////////////////////////////////////////////////////
        
        hand.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, 0, HandConst.CAN_Timeout);
        hand.setInverted(false); // Set true if the motor direction does not match the sensor direction
        hand.setSensorPhase(true);

        double initalHandAngle = getHandAngle();

        if (initalHandAngle > (HandConst.upperHardStop + HandConst.calibrationTolerance) || initalHandAngle < (HandConst.lowerHardStop - HandConst.calibrationTolerance))
            calibrated = false;

        if (!calibrated) {
            hand.configForwardSoftLimitEnable(false, HandConst.CAN_Timeout); //False until after calibration
            hand.configReverseSoftLimitEnable(false, HandConst.CAN_Timeout);
        } else {
            hand.configForwardSoftLimitThreshold(angleToTicks(HandConst.upperHardStop), HandConst.CAN_Timeout);
            hand.configReverseSoftLimitThreshold(angleToTicks(HandConst.lowerHardStop),  HandConst.CAN_Timeout);
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

        setPIDConstants(HandConst.proportional, HandConst.integral, HandConst.derivative, HandConst.feedforward, true);

        /////////////////////////////////////////////////////////////
        // Logging
        /////////////////////////////////////////////////////////////

            CSVLoggable temp = new CSVLoggable(this.shuffleboardTab) {
                public double get() { return getHandAngle(); }
            };
            CSVLogger.getInstance().add("HandAngle", temp);
            
            temp = new CSVLoggable(this.shuffleboardTab) {
                public double get() { return getHandOutput(); }
            };
            CSVLogger.getInstance().add("HandOutput", temp);
            
            temp = new CSVLoggable(this.shuffleboardTab) {
                public double get() { return getSetpoint(); }
            };
            CSVLogger.getInstance().add("HandSetpoint", temp);

            temp = new CSVLoggable(this.shuffleboardTab) {
                public double get() { return hand.getSelectedSensorVelocity(0); }
            };
            CSVLogger.getInstance().add("HandVelocity", temp);
    
            temp = new CSVLoggable(this.shuffleboardTab) {
                public double get() {
                    if( getCalibrated()) {
                        return 1.0;
                    }
                    else
                        return 0.0;}
            };
            CSVLogger.getInstance().add("HandCalibrated", temp);

            temp = new CSVLoggable(this.shuffleboardTab) {
                public double get() {
                    if( limitSwitch.get()) {
                        return 1.0;
                    }
                    else
                        return 0.0;}
            };
            CSVLogger.getInstance().add("HandLimitSwitch", temp);
    }

    /////////////////////////////////////////////////////////////
    // Other Methods
    /////////////////////////////////////////////////////////////
    @Override
    public void initDefaultCommand() {
        setDefaultCommand(new ManualHand());
    }

    @Override
    public void periodic() {
    }

    public double ticksToAngle(int ticks) {
        return (ticks - zero) * 360.0 * HandConst.encoderGearRatio / 4096.0 + HandConst.upperHardStop;
    }

    public int angleToTicks(double angle) {
        int ticks = (int)((angle-HandConst.upperHardStop) * 4096 /360.0 / HandConst.encoderGearRatio + 0.5);
        return ticks + zero;
    }

    /////////////////////////////////////////////////////////////
    // Set Methods
    /////////////////////////////////////////////////////////////
    public void setPIDConstants (double P, double I, double D, double F, boolean timeout)
	{
    	if(timeout) {
    		//assume using main PID loop (index 0)
    		hand.config_kP(0, P, HandConst.CAN_Timeout);
    		hand.config_kI(0, I, HandConst.CAN_Timeout);
    		hand.config_kD(0, D, HandConst.CAN_Timeout);
    		hand.config_kF(0, F, HandConst.CAN_Timeout);
    	}
    	else {
	    	//assume using main PID loop (index 0)
			hand.config_kP(0, P, HandConst.CAN_Timeout_No_Wait);
			hand.config_kI(0, I, HandConst.CAN_Timeout_No_Wait);
			hand.config_kD(0, D, HandConst.CAN_Timeout_No_Wait);
			hand.config_kF(0, F, HandConst.CAN_Timeout_No_Wait);
    	}
	
        Logger.getInstance().println("Hand PIDF set to: " + P + ", " + I + ", " + D + ", " + F, Severity.INFO);
	}
        
    /////////////////////////////////////////////////////////////
    // Get Methods
    /////////////////////////////////////////////////////////////
    public boolean getHandOnTarget() {
        double error = this.getSetpoint() - this.getHandAngle();
      	return (Math.abs(error) < HandConst.tolerance);
	}

	public double getSetpoint() {
        ControlMode mode = hand.getControlMode();
        if(mode == ControlMode.Position || mode == ControlMode.Velocity || mode == ControlMode.MotionMagic)
            return (ticksToAngle((int)hand.getClosedLoopTarget()));
        else
            return 999.0;
    }
    
    public double getHandOutput() {
        return hand.getMotorOutputVoltage();
    }

	public double getHandAngle() {
		return (ticksToAngle(hand.getSelectedSensorPosition()));
    }

    public boolean getCalibrated() {
        return calibrated;
    }
    
    public double getHandFirmwareVersion() {
		int firmwareVersion = hand.getFirmwareVersion();
		return ((firmwareVersion & 0xFF00) >> 8) + (firmwareVersion & 0xFF) / 100.0;
    }

    /////////////////////////////////////////////////////////////
    // Control Methods
    /////////////////////////////////////////////////////////////
    public void setAngle(double setpoint) {
        if(!lockout){
            if(calibrated) {
				if(setpoint > HandConst.upperHardStop) {
					hand.set(ControlMode.MotionMagic, angleToTicks(HandConst.upperHardStop));
					Logger.getInstance().println("Hand setpoint request above upper limit: " + setpoint, Logger.Severity.WARNING);
				}
				else if(setpoint < HandConst.lowerHardStop) {
					hand.set(ControlMode.MotionMagic, angleToTicks(HandConst.lowerHardStop));
					Logger.getInstance().println("Hand setpoint request below lower limit: " + setpoint, Logger.Severity.WARNING);
				}
				else {
					hand.set(ControlMode.MotionMagic, angleToTicks(setpoint));
				}
			}
        }
    }
    
    public void stopHand() {
        hand.disable();
        Logger.getInstance().println("Hand disabled", Logger.Severity.INFO);
    }
    
    public void calibrate() {
        if(!lockout){
            int newZero = hand.getSelectedSensorPosition();
            calibrated = true;
            Preferences.getInstance().putBoolean("HandCalibrated", true);
            Preferences.getInstance().putInt(zeroString, newZero);
            zero = newZero;
            hand.configForwardSoftLimitThreshold(angleToTicks(HandConst.upperHardStop), HandConst.CAN_Timeout_No_Wait);
            hand.configReverseSoftLimitThreshold(angleToTicks(HandConst.lowerHardStop),  HandConst.CAN_Timeout_No_Wait);
            hand.configForwardSoftLimitEnable(true, HandConst.CAN_Timeout_No_Wait); //False until after calibration
            hand.configReverseSoftLimitEnable(true, HandConst.CAN_Timeout_No_Wait);
            Logger.getInstance().println("Hand Calibrated: " + newZero, Severity.WARNING);
        }
    }

	public void engageLockout() {
        this.lockout = true;
	}

	public void disableLockout() {
        this.lockout = false;
    }
    
    public void manualHand() {
        double position;
        if(!lockout){
            double gamepadCommand = -Robot.oi.gamePad.getRawAxis(5);
            //double position;
            
            if (Math.abs(gamepadCommand) > HandConst.GamepadDeadZone) {
                setThrottle(gamepadCommand/Math.abs(gamepadCommand)*Math.pow(gamepadCommand, 2)*HandConst.MaxOutputPercent); //scaled to 0.4 max
            }
            else if (hand.getControlMode() != ControlMode.Position && hand.getControlMode() != ControlMode.MotionMagic) {
                position = getHandAngle();
                setAngle(position);
                //setThrottle(0);
            }  	
        }
    }
    
    public void setThrottle(double output) {
        if(!lockout){
            if(calibrated) {
                hand.set(ControlMode.PercentOutput, output);
            }
        }
	}

}

