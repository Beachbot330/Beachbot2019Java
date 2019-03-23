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

/**
 *
 */
public class CargoHatch extends BBCommandGroup {
    //PID Gains
    //PIDGains RampDrive = new PIDGains(0.10, 0, 0.00, 0, 0.3, ChassisConst.defaultMaxOutputStep, ChassisConst.defaultMinStartOutput, "DriveLow");    //AP 3/12/19

    Waypoint wp0 = new Waypoint(0, 120, 0);
    Waypoint wp1 = new Waypoint(10, 200+22-3, 0); // Near cargo ship
    Waypoint wp2 = new Waypoint(62, 96+30, 0); //Near rocket
    Waypoint wp3 = new Waypoint(78, 00+10, 0); //Near human player
    Waypoint wp4 = new Waypoint(78, 105+25, 0);



    public CargoHatch() {
    	
    	addSequential(new ShiftLow());
        addParallel(new HatchDefense());

        // double distance, double tolerance, double timeout, boolean stopAtEnd, PIDGains driveGains, PIDGains gyroGains
        //addSequential(new DriveDistanceAtCurAngle(wp1.getY(), 3.0, 3.0, true, ChassisConst.DriveLow, ChassisConst.GyroDriveLow));
        addSequential(new DriveWaypoint(wp0, false, 3.0, 3.0, true, ChassisConst.DriveLow, ChassisConst.GyroDriveLow));
        addSequential(new DriveWaypoint(wp1, false, 3.0, 3.0, true, ChassisConst.DriveLow, ChassisConst.GyroDriveLow));
        //addSequential(new TurnGyroWaypoint(wp2, false, 3.0, 3.0, ChassisConst.GyroTurnLow));
        //addSequential(new DriveWaypoint(wp2, false, 3.0, 3.0, true, ChassisConst.DriveLow, ChassisConst.GyroDriveLow));
        addSequential(new TurnGyroAbs(-90, 3.0, 1.5, ChassisConst.GyroTurnLow));
        addSequential(new TurnLimelight(2.0, 2.0));

        addSequential(new SetHandAngle(HandConst.hatchPlacementLow));
        addParallel(new SetLiftPosition(LiftConst.DeployHatchLow));
        //addSequential(new DriveLimelight(0.3, 1.0));
        addSequential(new DriveLimelight(0.2, 0.7));

        addSequential(new WaitCommand(0.3));

        addParallel(new EjectHatch());
        addSequential(new WaitCommand(0.1));
        addSequential(new DriveWaypointBackward(wp1, false, 3.0, 3.0, true, ChassisConst.DriveLow, ChassisConst.GyroDriveLow));

        addSequential(new WaitCommand(0.5));

        addSequential(new TurnGyroWaypoint(wp3, false, 3.0, 3.0, ChassisConst.GyroTurnLow));
        addSequential(new DriveWaypoint(wp3, false, 3.0, 3.0, true, ChassisConst.DriveLow, ChassisConst.GyroDriveLow));

        addSequential(new TurnLimelight(3.0, 2.0));
        addSequential(new DriveLimelight(0.55, 0.5));
        addParallel(new DriveLimelight(0.2, 0.5));

        addParallel(new SensoredHatchPickup());
        addSequential(new WaitCommand(0.3));
        addSequential(new DriveWaypointBackward(wp2, false, 3.0, 3.0, true, ChassisConst.DriveLow, ChassisConst.GyroDriveLow));
        addSequential(new RollerOff());

        addSequential(new TurnGyroWaypoint(wp4, false, 3.0, 3.0, ChassisConst.GyroTurnLow));

        addSequential(new TurnLimelight(3.0, 2.0));
        addParallel(new SetHandAngle(HandConst.hatchPlacementMid));
        BBCommand parallelCommand = new SetLiftPosition(LiftConst.DeployHatchMid);
        addParallel(parallelCommand);
        addSequential(new DriveLimelight(0.40, 1.5));
        
        addSequential(new CheckDone(parallelCommand));

        addSequential(new WaitCommand(0.2));
        addParallel(new EjectHatch());
        addSequential(new DriveWaypointBackward(wp2, false, 3.0, 3.0, true, ChassisConst.DriveLow, ChassisConst.GyroDriveLow));

        addSequential(new WaitCommand(0.3));
        addSequential(new RollerOff());
        addSequential(new Defense());

    }
}
