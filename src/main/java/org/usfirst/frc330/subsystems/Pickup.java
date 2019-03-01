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

import org.usfirst.frc330.constants.GrabberConst;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import org.usfirst.frc330.util.CSVLogger;
import org.usfirst.frc330.util.CSVLoggable;
import org.usfirst.frc330.util.Logger;
import org.usfirst.frc330.wpilibj.MedianFilter;
// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import org.usfirst.frc330.wpilibj.BBDoubleSolenoid;
import org.usfirst.frc330.wpilibj.SharpIR.SharpType;
import org.usfirst.frc330.wpilibj.SharpIR;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS


/**
 *
 */
public class Pickup extends Subsystem {

    public ShuffleboardTab shuffleboardTab = Shuffleboard.getTab("Pickup");
    boolean lockout = false;

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private WPI_TalonSRX intake;
    private BBDoubleSolenoid claw;
    private BBDoubleSolenoid ballKicker;
    private SharpIR iRSensor;
    private SharpIR iRSensor2;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    private MedianFilter median;

    public Pickup() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        intake = new WPI_TalonSRX(4);
        
        
        
        claw = new BBDoubleSolenoid(0, 2, 3);
        addChild("Claw",claw);
        
        
        ballKicker = new BBDoubleSolenoid(0, 6, 7);
        addChild("BallKicker",ballKicker);
        
        
        iRSensor = new SharpIR(SharpType.GP2Y0A41SK0F, 2);
        addChild(iRSensor);
        
        
        iRSensor2 = new SharpIR(SharpType.GP2Y0A51SK0F, 3);
        addChild(iRSensor2);
        
        

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS

        //double process_noise, double sensor_noise, double estimated_error, double intial_value

        median = new MedianFilter(GrabberConst.medianSamples);
    
    
        /////////////////////////////////////////////////////////////
        // Logging
        /////////////////////////////////////////////////////////////
        CSVLoggable temp = new CSVLoggable(this.shuffleboardTab) {
            public double get() { return getSensorDistance(); }
        };
        CSVLogger.getInstance().add("SensorPickupDistanceFiltered", temp);
        
        temp = new CSVLoggable(this.shuffleboardTab) {
            public double get() { return iRSensor.getDistance(); }
        };
        CSVLogger.getInstance().add("SensorPickupDistanceRaw", temp);

        temp = new CSVLoggable(this.shuffleboardTab) {
            public double get() { return iRSensor.getVoltage(); }
        };
        CSVLogger.getInstance().add("SensorVoltage", temp);

        temp = new CSVLoggable(this.shuffleboardTab) {
            public double get() { 
                if(getBallInRange())
                    return 1.0;
                else
                    return 0.0;
            }
        };
        CSVLogger.getInstance().add("BallInRange", temp);

        temp = new CSVLoggable(this.shuffleboardTab) {
            public double get(){
                if(getHasBall())
                    return 1.0;
                else
                    return 0.0;
            }
        };
        CSVLogger.getInstance().add("HasBall", temp);

        temp = new CSVLoggable(this.shuffleboardTab) {
            public double get(){
                if(getHasHatch())
                    return 1.0;
                else
                    return 0.0;
            }
        };
        CSVLogger.getInstance().add("HasHatch", temp);
    }

    /////////////////////////////////////////////////////////////
	// Other Methods
	/////////////////////////////////////////////////////////////
    @Override
    public void initDefaultCommand() {
    }

    @Override
    public void periodic() {
        updateSensorDistance();
    }

    //-----------SENSORS-------------
    private double updateSensorDistance() {
    	return median.updateFilteredValue(iRSensor.getDistance());
    }
    

    /////////////////////////////////////////////////////////////
	// Get Methods
	/////////////////////////////////////////////////////////////
    public double getSensorDistance() {
    	return median.getFilteredValue();
    }

    //Ball nested deeply in hand, or hatch in claw
	public boolean getHasObject() {
        return getHasBall() || getHasHatch();
	}

    //Triggered when limit switches engage
	public boolean getHatchAligned() {
        //TODO: implement
		return false;
	}

    //Ball is in range of the rollers
	public boolean getBallInRange() {
        double currentDistance = getSensorDistance();
        return currentDistance < GrabberConst.ballPickupMaxDistance;
    }
    
    public boolean getHatchInRange() {
        double currentDistance = getSensorDistance();
        return ((GrabberConst.hatchPickupMinDistance) > currentDistance) && (currentDistance < (GrabberConst.hatchPickupMaxDistance));
    }

	public boolean getHasBall() {
        //sensors say ball is within the min and max ball distance
        double currentDistance = getSensorDistance();
        return (currentDistance < GrabberConst.ballAcquiredMaxDistance);
    }
    
    public boolean getHasHatch() {
        //sensors say hatch is within the min and max hatch distance
        double currentDistance = getSensorDistance();
        return ((GrabberConst.hatchAcquiredMinDistance) < currentDistance) && (currentDistance < (GrabberConst.hatchAcquiredMaxDistance));
    }
    
    public double getIntakeFirmwareVersion() {
		int firmwareVersion = intake.getFirmwareVersion();
		return ((firmwareVersion & 0xFF00) >> 8) + (firmwareVersion & 0xFF) / 100.0;
	}


    /////////////////////////////////////////////////////////////
	// Control Methods
	/////////////////////////////////////////////////////////////
	public void disableLockout() {
        this.lockout = false;
	}

	public void engageLockout() {
        this.lockout = true;
    }
    
    public void closeClaw() {
        if(!lockout){
            claw.set(BBDoubleSolenoid.Value.kReverse);
            Logger.getInstance().println("CLosing Claw", Logger.Severity.INFO);
        }
	}

	public void openClaw() {
        if(!lockout){
            claw.set(BBDoubleSolenoid.Value.kForward);
            Logger.getInstance().println("Opening Claw", Logger.Severity.INFO);
        }
    }
    
    public void clawOff() {
        if(!lockout){
            claw.set(Value.kOff);
            Logger.getInstance().println("Turning Claw off", Logger.Severity.INFO);
        }
    }

	public void rollerOff() {
        if(!lockout){
            intake.stopMotor();
            Logger.getInstance().println("Turning Roller off", Logger.Severity.INFO);
        }
	}

	public void rollerOn() {
        if(!lockout){
            intake.set(-GrabberConst.DefaultRollerSpeed);
            Logger.getInstance().println("Turning Roller On", Logger.Severity.INFO);
        }
    }

    public void rollerReverse() {
        if(!lockout){
            intake.set(GrabberConst.DefaultRollerSpeed);
            Logger.getInstance().println("Reversing Roller", Logger.Severity.INFO);
        }
    }

    public void rollerOn(double speed) {
        if(!lockout){
            intake.set(-speed);
            Logger.getInstance().println("Turning Roller On to " +speed +"% speed", Logger.Severity.INFO);
        }
    }
    
    public void ballKickExtend(){
        if(!lockout){
            ballKicker.set(Value.kForward);
            Logger.getInstance().println("Extending Kicker", Logger.Severity.INFO);
        }
    }

    public void ballKickRetract(){
        if(!lockout){
            ballKicker.set(Value.kReverse);
            Logger.getInstance().println("Retracting Kicker", Logger.Severity.INFO);
        }
    }

    public void ballKickOff() {
        if(!lockout){
            ballKicker.set(Value.kOff);
            Logger.getInstance().println("Turning off Kicker", Logger.Severity.INFO);
        }
    }
}

