package org.usfirst.frc330.autoCommands;


import org.usfirst.frc330.commands.*;
import org.usfirst.frc330.commands.commandgroups.Defense;
import org.usfirst.frc330.commands.commandgroups.EjectBall;
import org.usfirst.frc330.commands.drivecommands.*;
import org.usfirst.frc330.constants.ChassisConst;
import org.usfirst.frc330.constants.HandConst;
import org.usfirst.frc330.constants.LiftConst;
import org.usfirst.frc330.wpilibj.PIDGains;

import edu.wpi.first.wpilibj.command.BBCommandGroup;

/**
 *
 */
public class Hab1Hatch extends BBCommandGroup {
    //PID Gains
    //PIDGains RampDrive = new PIDGains(0.10, 0, 0.00, 0, 0.3, ChassisConst.defaultMaxOutputStep, ChassisConst.defaultMinStartOutput, "DriveLow");    //AP 3/12/19

    Waypoint wp1 = new Waypoint(0, 60, 0);
    Waypoint wp2 = new Waypoint(62, 96, 0);
    Waypoint wp3 = new Waypoint(78, 00, 0);
    Waypoint wp4 = new Waypoint(78, 105, 0);



    public Hab1Hatch() {
    	
    	addSequential(new ShiftLow());
        addParallel(new Defense());

        // double distance, double tolerance, double timeout, boolean stopAtEnd, PIDGains driveGains, PIDGains gyroGains
        addSequential(new DriveDistanceAtCurAngle(wp1.getY(), 3.0, 3.0, true, ChassisConst.DriveLow, ChassisConst.GyroDriveLow));
        addSequential(new TurnGyroWaypoint(wp2, false, 3.0, 3.0, ChassisConst.GyroTurnLow));
        addSequential(new DriveWaypoint(wp2, false, 3.0, 3.0, true, ChassisConst.DriveLow, ChassisConst.GyroDriveLow));
        addSequential(new TurnLimelight(2.0, 2.0));

        addSequential(new SetHandAngle(HandConst.hatchPlacementLow));
        addParallel(new SetLiftPosition(LiftConst.DeployHatchLow));
        addSequential(new DriveLimelight(0.3, 1.0));
        addSequential(new DriveLimelight(0.2, 0.7));

        addSequential(new WaitCommand(0.5));

        addParallel(new EjectBall());
        addSequential(new WaitCommand(0.5));
        addSequential(new DriveWaypointBackward(wp2, false, 3.0, 3.0, true, ChassisConst.DriveLow, ChassisConst.GyroDriveLow));

        addSequential(new WaitCommand(0.5));

        addSequential(new TurnGyroWaypoint(wp3, false, 3.0, 3.0, ChassisConst.GyroTurnLow));
        addSequential(new DriveWaypoint(wp3, false, 3.0, 3.0, true, ChassisConst.DriveLow, ChassisConst.GyroDriveLow));

        addSequential(new OpenClaw());
        addSequential(new TurnLimelight(2.0, 2.0));
        addSequential(new DriveLimelight(0.3, 1.0));
        addSequential(new DriveLimelight(0.2, 1.0));

        addSequential(new CloseClaw());
        addSequential(new Defense());
        addSequential(new DriveWaypointBackward(wp2, false, 3.0, 3.0, true, ChassisConst.DriveLow, ChassisConst.GyroDriveLow));

        addSequential(new TurnGyroWaypoint(wp4, false, 3.0, 3.0, ChassisConst.GyroTurnLow));

        addSequential(new TurnLimelight(2.0, 2.0));
        addParallel(new DriveLimelight(0.2, 3.0));
        addParallel(new SetHandAngle(HandConst.hatchPlacementMid));
        addSequential(new SetLiftPosition(LiftConst.DeployHatchMid));

    }
}
