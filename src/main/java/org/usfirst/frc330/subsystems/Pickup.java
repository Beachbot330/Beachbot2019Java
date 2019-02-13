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


import org.usfirst.frc330.commands.*;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

import org.usfirst.frc330.util.Logger;
import org.usfirst.frc330.util.Logger.Severity;
// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import org.usfirst.frc330.wpilibj.BBDoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS


/**
 *
 */
public class Pickup extends Subsystem {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private WPI_TalonSRX intake;
    private BBDoubleSolenoid claw;
    private BBDoubleSolenoid ballKicker;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    public Pickup() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        intake = new WPI_TalonSRX(4);
        
        
        
        claw = new BBDoubleSolenoid(0, 2, 3);
        addChild("Claw",claw);
        
        
        ballKicker = new BBDoubleSolenoid(0, 6, 7);
        addChild("BallKicker",ballKicker);
        
        

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
    }

    @Override
    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }

    @Override
    public void periodic() {
        // Put code here to be run every loop

    }

	public void stopPickup() {
        intake.disable();
        Logger.getInstance().println("Pickup disabled", Logger.Severity.INFO);
        //implemented 2.2.19 -ejo
        //VERIFY the print shouldn't say wrist - JR (fixed 2.10.19 - ejo)
	}

	public void closeClaw() {
        claw.set(DoubleSolenoid.Value.kReverse);
        Logger.getInstance().println("Opening Claw", Logger.Severity.INFO);
        //VERIFY my implment -ejo 2.12.19
	}

	public void openClaw() {
        claw.set(DoubleSolenoid.Value.kForward);
        Logger.getInstance().println("Closing Claw", Logger.Severity.INFO);
        //VERIFY my implment -ejo 2.12.19
	}

	public void rollerOff() {
        //TODO: Implement - AP
	}

	public void rollerOn() {
        //TODO: Implement - AP
    }
    
    public void ballKickExtend(){
        ballKicker.set(Value.kForward);
    }

    public void ballKickRetract(){
        ballKicker.set(Value.kReverse);
    }

    //Ball nested deeply in hand, or hatch in claw
	public boolean getHasObject() {
        //TODO Implement
        return false;
	}

    //Triggered when limit switches engage
	public boolean getHatchAligned() {
        //TODO Implement
		return false;
	}

    //Ball is in range of the rollers
	public boolean getBallInRange() {
		return false;
	}

	public boolean getHasBall() {
        //TODO Implement
		return false;
    }
    
    public boolean getHasHatch() {
        //TODO Implement
		return false;
    }
    
    public double getIntakeFirmwareVersion() {
		int firmwareVersion = intake.getFirmwareVersion();
		return ((firmwareVersion & 0xFF00) >> 8) + (firmwareVersion & 0xFF) / 100.0;
	}

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CMDPIDGETTERS


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CMDPIDGETTERS

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

}

