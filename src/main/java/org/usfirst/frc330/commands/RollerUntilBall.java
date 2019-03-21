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
import org.usfirst.frc330.util.Logger;
import org.usfirst.frc330.util.Logger.Severity;

/**
 *
 */
public class RollerUntilBall extends BBCommand {
    double minTime;

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
 
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
    public RollerUntilBall() {

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
        this(0.25, 0.75);
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.pickup);

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        

        
    }
    
    public RollerUntilBall(double maxTimeout) {
    	this(0.25, maxTimeout);
    }
    
    public RollerUntilBall(double minTime, double maxTimeout) {

        requires(Robot.pickup);

        this.setTimeout(maxTimeout);
        this.minTime = minTime;
        if (maxTimeout < minTime) {
        	throw new IllegalArgumentException("maxTimeout (" + maxTimeout + 
        			") must be larger then minTime (" + minTime + ")");
        }
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        Robot.pickup.rollerOnBall();
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return (isTimedOut() || Robot.pickup.getHasBall()) && this.timeSinceInitialized() > minTime;
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
        Robot.pickup.rollerOnSlow(true);
    	Logger.getInstance().println("Sensor Distance: " + Robot.pickup.getSensorDistance(), Severity.INFO);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
        this.end();
    }
}
