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
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.command.BBCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc330.Robot;
import org.usfirst.frc330.constants.ChassisConst;
import org.usfirst.frc330.util.Logger;
import org.usfirst.frc330.util.Logger.Severity;

/**
 *
 */
public class TurnLimelight extends BBCommand {

    public TurnLimelight() {

        requires(Robot.chassis);
        SmartDashboard.putBoolean("Troy", false);

    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        Robot.chassis.limelightPID.enable();
    	Robot.chassis.gyroPID.disable();
    	Robot.chassis.leftDrivePID.disable();
        Robot.chassis.rightDrivePID.disable();
        
        if(NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv").getDouble(0) > 0.5){
            double angle = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0);
            Logger.getInstance().println("Aiming started with target at: " + angle, Severity.INFO);
        }
        else{
            Logger.getInstance().println("Aiming started with no target!", Severity.WARNING);
        }
        if(Robot.chassis.getIsLowGear()){
            Logger.getInstance().println("Robot in low gear", Severity.INFO);
        }
        else{
            Logger.getInstance().println("Robot in high gear", Severity.INFO);
        }

        SmartDashboard.putBoolean("Troy", true);

        if(Robot.chassis.getIsLowGear()){
            Robot.chassis.setLimelightPID(ChassisConst.LimelightTurnLow);
        }
        else{
            Robot.chassis.setLimelightPID(ChassisConst.LimelightTurnHigh);
        }
        
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {

        Robot.chassis.tankDrive(0, 0);

    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        if(NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv").getDouble(0) > 0.5){
            if(Math.abs(NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0))<1.0){
                return true;
            }
            else
                return false;
        }
        else
            return false;
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
        if(NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv").getDouble(0) > 0.5){
            double angle = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0);
            Logger.getInstance().println("Aiming ended with target at: " + angle, Severity.INFO);
        }
        else{
            Logger.getInstance().println("Aiming ended with no target!", Severity.WARNING);
        }
        Robot.chassis.limelightPID.disable();
        SmartDashboard.putBoolean("Troy", false);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
        this.end();
    }
}
