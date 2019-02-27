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

/**
 *
 */
public class AutoStear extends BBCommand {

    boolean initialLock;

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
 
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
    public AutoStear() {

        requires(Robot.chassis);

    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        initialLock = false;
        Robot.chassis.limelightPID.enable();
    	Robot.chassis.gyroPID.disable();
    	Robot.chassis.leftDrivePID.disable();
    	Robot.chassis.rightDrivePID.disable();
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        if(!initialLock){

            if(Robot.chassis.getIsLowGear()){
                Robot.chassis.setLimelightPID(ChassisConst.LimelightTurnLow);
            }
            else{
                Robot.chassis.setLimelightPID(ChassisConst.LimelightTurnHigh);
            }

            double angle = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0);
            if(Math.abs(angle)<3.0){
                initialLock = true;
            }
        }
        else{
            if(Robot.chassis.getIsLowGear()){
                Robot.chassis.setLimelightPID(ChassisConst.LimelightDriveLow);
            }
            else{
                Robot.chassis.setLimelightPID(ChassisConst.LimelightDriveHigh);
            }
        }

        double drive = -Robot.oi.driverR.getY();
        Robot.chassis.tankDrive(drive, drive);
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
        Robot.chassis.limelightPID.disable();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
        Robot.chassis.limelightPID.disable();
    }
}
