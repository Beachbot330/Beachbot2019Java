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
import org.usfirst.frc330.constants.LiftConst;
import org.usfirst.frc330.util.Logger;

/**
 *
 */
public class SetLiftPosition extends BBCommand {
	
    double position, tolerance;
    boolean override;
	
    public SetLiftPosition(double position) {
        requires(Robot.lift);
        this.position = position;
        this.tolerance = LiftConst.tolerance;
        this.override = false;
    }

    public SetLiftPosition(double position, boolean override) {
        requires(Robot.lift);
        this.position = position;
        this.tolerance = LiftConst.tolerance;
        this.override = override;
    }
    
    public SetLiftPosition(double position, double tolerance) {
        requires(Robot.lift);
        this.position = position;
        this.tolerance = tolerance;
        this.override = false;
    }

    public SetLiftPosition(double position, double tolerance, boolean override) {
        requires(Robot.lift);
        this.position = position;
        this.tolerance = tolerance;
        this.override = override;
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
    	Robot.lift.setLiftPosition(position, override);
    	Robot.lift.setLiftAbsoluteTolerance(tolerance);
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
    	return Robot.lift.getLiftOnTarget();
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
    	Robot.lift.setLiftAbsoluteTolerance(LiftConst.tolerance);
    	Logger.getInstance().println("Lift Setpoint: " + Robot.lift.getSetpoint(), Logger.Severity.INFO);
    	Logger.getInstance().println("Lift Final Position: " + Robot.lift.getPosition(), Logger.Severity.INFO);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
    	this.end();
    }
}
