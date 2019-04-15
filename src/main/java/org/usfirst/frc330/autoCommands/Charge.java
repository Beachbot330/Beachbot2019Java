package org.usfirst.frc330.autoCommands;


import org.usfirst.frc330.commands.*;
import org.usfirst.frc330.commands.commandgroups.*;
import org.usfirst.frc330.commands.drivecommands.*;
import org.usfirst.frc330.constants.ChassisConst;
import org.usfirst.frc330.constants.HandConst;
import org.usfirst.frc330.constants.LiftConst;
import org.usfirst.frc330.wpilibj.PIDGains;

import edu.wpi.first.wpilibj.command.BBCommand;
import edu.wpi.first.wpilibj.command.BBCommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class Charge extends BBCommandGroup {



    public Charge() {
        addSequential(new ShiftHigh());
        addSequential(new SetLiftPosition(LiftConst.DeployHatchLow));
        addSequential(new SetHandAngle(HandConst.hatchPlacementLow));
        addSequential(new WaitCommand("Debug", 3.0));

        //addSequential(new DriveTime(60.0*60.0*10.0, 0.3, 0.3));
        double throttle = 0.4; double timeout = 10.0; double jerkThreshold = 1.0;
        addSequential(new DriveLimelightUntilJerk(throttle, timeout, jerkThreshold));

        addSequential (new EjectHatch());
        addSequential(new WaitCommand("Deploy Hatch", 0.1));
        addSequential(new DriveWaypointBackward(new Waypoint(0,0,0), false, 1.0, 1.0, true, ChassisConst.DriveHigh, ChassisConst.GyroDriveHigh));
    }
}