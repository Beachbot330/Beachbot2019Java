// RobotBuilder Version: 2.0BB
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc330.commands;
import edu.wpi.first.wpilibj.command.BBCommand;
import org.usfirst.frc330.Robot;
import org.usfirst.frc330.constants.*;
import org.usfirst.frc330.util.Logger;
import org.usfirst.frc330.util.Logger.Severity;

/**
 *
 */
public class SensorCloseClawBall extends BBCommand {

    int consecutiveGetBallInRanges;

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
 
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
    public SensorCloseClawBall() {
        requires(Robot.pickup);

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        consecutiveGetBallInRanges = 0;
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        if (Robot.pickup.getBallInRange()){
            this.consecutiveGetBallInRanges ++;
    	}
    	else {
    		this.consecutiveGetBallInRanges = 0;
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return (consecutiveGetBallInRanges > GrabberConst.getBallInRangeMinRepititions);
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
        Robot.pickup.closeClaw();
        Logger.getInstance().println("Ball in range: " + Robot.pickup.getBallInRange(), Severity.INFO);
    	Logger.getInstance().println("Sensor: " + Robot.pickup.getSensorDistance(), Severity.INFO);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
    }
}
