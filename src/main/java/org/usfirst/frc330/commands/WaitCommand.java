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
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.BBCommand;

/**
 *
 */
public class WaitCommand extends BBCommand {

	double time;
	double startTime;
	
    public WaitCommand(double seconds) {
    	this.setRunWhenDisabled(false);
    	this.time = seconds;
    }

    protected void initialize() {
    	startTime = Timer.getFPGATimestamp();
    }


    protected void execute() {
    }

    protected boolean isFinished() {
        return Timer.getFPGATimestamp() > (startTime + time);
    }

    protected void end() {
    }


    protected void interrupted() {
    }
}
