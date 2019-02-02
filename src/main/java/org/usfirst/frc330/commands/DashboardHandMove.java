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
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc330.Robot;

/**
 *
 */
public class DashboardHandMove extends BBCommand {

    public DashboardHandMove() {
    	this.setRunWhenDisabled(false);
    	this.requires(Robot.hand);
    }

    protected void initialize() {
    	Robot.buzzer.enable(0.1);
    	Robot.hand.setAngle(SmartDashboard.getNumber("DashboardHandMoveSetpoint", Robot.hand.getHandAngle()));
    }


    protected void execute() {
    }

    protected boolean isFinished() {
        return Robot.hand.getHandOnTarget();
    }

    protected void end() {
    }


    protected void interrupted() {
    }
}
