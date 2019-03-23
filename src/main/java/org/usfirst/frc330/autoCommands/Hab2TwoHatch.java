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
public class Hab2TwoHatch extends BBCommandGroup {
    //PID Gains
    //PIDGains RampDrive = new PIDGains(0.10, 0, 0.00, 0, 0.3, ChassisConst.defaultMaxOutputStep, ChassisConst.defaultMinStartOutput, "DriveLow");    //AP 3/12/19

    Waypoint wp1 = new Waypoint(0, 60+30, 0);  //Off the platform
    Waypoint wp2 = new Waypoint(62, 96+30, 0); //Near rocket
    Waypoint wp3 = new Waypoint(78, 00+10, 0); //Near human player
    Waypoint wp4 = new Waypoint(78, 105+30, 0);

    boolean invert = false;

    public Hab2TwoHatch(boolean leftSide, boolean redTeam) {
        
        if(!redTeam){
            this.wp1 = wp1;
            this.wp2 = wp2;
            this.wp3 = wp3;
            this.wp4 = wp4;
        }
        
        invert = leftSide;

    	addSequential(new ShiftLow());
        addParallel(new HatchDefense());

        // Drive off of Hab2
        addSequential(new DriveDistanceAtCurAngle(wp1.getY(), 3.0, 3.0, true, ChassisConst.DriveLow, ChassisConst.GyroDriveLow));
        addSequential(new TurnGyroWaypoint(wp2, invert, 3.0, 3.0, ChassisConst.GyroTurnLow));
        addSequential(new DriveWaypoint(wp2, invert, 3.0, 3.0, true, ChassisConst.DriveLow, ChassisConst.GyroDriveLow));
        addSequential(new TurnLimelight(3.0, 2.0));

        // Deploy First Hatch
        addParallel(new SetHandAngle(HandConst.hatchPlacementLow));
        addParallel(new SetLiftPosition(LiftConst.DeployHatchLow));
        addSequential(new DriveLimelight(0.60, 0.55));
        addSequential(new DriveLimelight(0.2, 0.4));
        addParallel(new EjectHatch());
        addSequential(new WaitCommand(0.2));
        addSequential(new DriveWaypointBackward(wp2, invert, 3.0, 3.0, true, ChassisConst.DriveLow, ChassisConst.GyroDriveLow));
        addSequential(new WaitCommand(0.1));
        addSequential(new RollerOff());

        // Drive towards human station
        addSequential(new TurnGyroWaypoint(wp3, invert, 3.0, 3.0, ChassisConst.GyroTurnLow));
        addSequential(new DriveWaypoint(wp3, invert, 3.0, 3.0, true, ChassisConst.DriveLow, ChassisConst.GyroDriveLow));

        //Pickup second hatch
        addSequential(new TurnLimelight(3.0, 2.0));
        addSequential(new DriveLimelight(0.55, 0.55));
        addParallel(new SensoredHatchPickup());
        addParallel(new DriveLimelight(0.2, 0.40));

        //addParallel(new SensoredHatchPickup());
        addSequential(new WaitCommand(0.3));
        addSequential(new DriveWaypointBackward(wp2, invert, 3.0, 3.0, true, ChassisConst.DriveLow, ChassisConst.GyroDriveLow));
        addSequential(new RollerOff());

        addSequential(new TurnGyroWaypoint(wp4, invert, 3.0, 3.0, ChassisConst.GyroTurnLow));

        addSequential(new TurnLimelight(3.0, 2.0));
        addParallel(new SetHandAngle(HandConst.hatchPlacementMid));
        BBCommand parallelCommand = new SetLiftPosition(LiftConst.DeployHatchMid);
        addParallel(parallelCommand);
        addSequential(new WaitCommand(0.5)); //Give the lift some time, so we don't bonk our head AP 3/21/19
        addSequential(new DriveLimelight(0.40, 1.3));
        
        addSequential(new CheckDone(parallelCommand));

        addSequential(new WaitCommand(0.2));
        addParallel(new EjectHatch());
        addSequential(new DriveWaypointBackward(wp2, invert, 3.0, 3.0, true, ChassisConst.DriveLow, ChassisConst.GyroDriveLow));

        addSequential(new WaitCommand(0.3));
        addSequential(new RollerOff());
        addSequential(new Defense());

    }
}
