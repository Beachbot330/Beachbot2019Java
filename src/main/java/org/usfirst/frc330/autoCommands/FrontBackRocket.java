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
public class FrontBackRocket extends BBCommandGroup {
    //PID Gains
    //PIDGains RampDrive = new PIDGains(0.10, 0, 0.00, 0, 0.3, ChassisConst.defaultMaxOutputStep, ChassisConst.defaultMinStartOutput, "DriveLow");    //AP 3/12/19

    Waypoint wp1 = new Waypoint(0, 21, 0);      //Hab 1
    Waypoint wp2 = new Waypoint(80, 133, 0);  //Near rocket
    Waypoint wp3 = new Waypoint(85, 00-3, 0);  //Near human player
    Waypoint wp4 = new Waypoint(60, 250, 0);    //Far side of rocket
    Waypoint wp5 = new Waypoint(88-18, 216+15, 0);  //Far rocket
    Waypoint wp6 = new Waypoint(78, 230, 0);    //Line up with middle cargo ship

    boolean invert = false;

    public FrontBackRocket(boolean leftSide, boolean redTeam) {
        
        if(!redTeam){
            this.wp1 = wp1;
            this.wp2 = wp2;
            this.wp3 = wp3;
            this.wp4 = wp4;
        }
        
        invert = leftSide;

    	addSequential(new ShiftLow());
        addParallel(new HatchDefense());

        // Drive off of Hab2 to Hab1
        addSequential(new DriveDistanceAtCurAngle(wp1.getY(), 2.0, 3.0, true, ChassisConst.DriveLowSlow, ChassisConst.GyroDriveLow));
        addSequential(new WaitCommand(0.8));
        //addSequential(new ShiftHigh());

        // Drive to rocket
        addSequential(new TurnGyroWaypoint(wp2, invert, 4.0, 2.0, ChassisConst.GyroTurnLow));
        BBCommand parallelCommand = new DriveWaypoint(wp2, invert, 3.0, 3.0, true, ChassisConst.DriveLow, ChassisConst.GyroDriveLow);
        addParallel(parallelCommand);
        addSequential(new WaitCommand(0.3));
        addParallel(new SetHandAngle(HandConst.hatchPlacementLow));
        addSequential(new SetLiftPosition(LiftConst.DeployHatchLow));
        addSequential(new CheckDone(parallelCommand));
        //addSequential(new WaitCommand(0.5));

        // Deploy First Hatch
        //addSequential(new ShiftLow());
        addSequential(new TurnLimelight(3.0, 2.0));
        addSequential(new DriveLimelight(0.3, 0.5));
        addParallel(new EjectHatch(1.0));
        addSequential(new WaitCommand(0.2));

        //Pull back from first hatch
        addSequential(new DriveWaypointBackward(wp2, invert, 3.0, 3.0, true, ChassisConst.DriveLow, ChassisConst.GyroDriveLow));
        //addSequential(new DriveDistance(-4, ChassisConst.DriveLowSlow));
        addSequential(new WaitCommand(0.1));
        addSequential(new RollerOff());
        addSequential(new WaitCommand(0.3));

        // Drive towards human station
        addSequential(new ShiftHigh());
        addSequential(new TurnGyroWaypoint(wp3, invert, 3.0, 3.0, ChassisConst.GyroTurnLow));
        parallelCommand = new DriveWaypoint(wp3, invert, 3.0, 3.0, true, ChassisConst.DriveHigh, ChassisConst.GyroDriveHigh);
        addParallel(parallelCommand);
        addSequential(new WaitCommand(0.2));
        addSequential(new ShiftHigh());
        addParallel(new SetHandAngle(HandConst.hatchPlacementLow));
        addSequential(new SetLiftPosition(LiftConst.DeployHatchLow));
        addSequential(new CheckDone(parallelCommand));
        //addSequential(new WaitCommand(0.5));

        //Pickup second hatch
        //addSequential(new ShiftLow());
        addSequential(new TurnLimelight(3.0, 2.0));
        addParallel(new SensoredHatchPickup());
        addSequential(new DriveLimelightCurrentSense(0.2, 1.0, 10));
        addSequential(new WaitCommand(0.1));
        addSequential(new DriveWaypointBackward(wp3, invert, 3.0, 3.0, true, ChassisConst.DriveLow, ChassisConst.GyroDriveLow));
        addParallel(new HatchDefense());

        //Drive to far side of rocket
        addSequential(new TurnGyroWaypointBackward(wp4, invert, 3.0, 3.0, ChassisConst.GyroTurnLow));
        parallelCommand = new DriveWaypointBackward(wp4, invert, 3.0, 3.0, true, ChassisConst.DriveHigh, ChassisConst.GyroDriveHigh);
        addParallel(parallelCommand);
        addSequential(new WaitCommand(0.2));
        addSequential(new ShiftHigh());
        addSequential(new WaitCommand(0.3));
        addParallel(new SetHandAngle(HandConst.hatchPlacementLow));
        addSequential(new SetLiftPosition(LiftConst.DeployHatchLow));
        addSequential(new CheckDone(parallelCommand));
        //addSequential(new WaitCommand(1.0));

        //Turn towards rocket
        addSequential(new ShiftLow());
        addSequential(new TurnGyroWaypoint(wp5, invert, 3.0, 3.0, ChassisConst.GyroTurnLow));
        //addSequential(new WaitCommand(1.0));
        //addSequential(new DriveWaypoint(wp5, invert, 4.0, 4.0, true, ChassisConst.DriveLow, ChassisConst.GyroDriveLow));

        
        // Deploy Second Hatch
        addSequential(new TurnLimelight(3.0, 1.0));
        addSequential(new DriveLimelight(0.6, 0.8));
        addSequential(new DriveLimelight(0.2, 0.4));
        addParallel(new EjectHatch(1.0));
        addSequential(new WaitCommand(0.5));

        //Pull back from second hatch
        addSequential(new DriveWaypointBackward(wp6, invert, 3.0, 3.0, true, ChassisConst.DriveLow, ChassisConst.GyroDriveLow));
        //addSequential(new DriveDistance(-4, ChassisConst.DriveLowSlow));
        addSequential(new WaitCommand(0.1));
        addSequential(new RollerOff());
        
    }
}
