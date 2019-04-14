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
public class CargoHatchHighGear extends BBCommandGroup {
    //PID Gains
    //PIDGains RampDrive = new PIDGains(0.10, 0, 0.00, 0, 0.3, ChassisConst.defaultMaxOutputStep, ChassisConst.defaultMinStartOutput, "DriveLow");    //AP 3/12/19

    Waypoint wp0 = new Waypoint(0, 90-5, 0);   //hab1
    Waypoint wp1 = new Waypoint(26, 236, 0); // Near cargo ship
    Waypoint wp2 = new Waypoint(62, 96+30, 0); //Near rocket
    Waypoint wp3 = new Waypoint(67+5, 00+10, 0); //Near human player
    Waypoint wp4 = new Waypoint(22, 255+6, 0); //Cargo Ship Far

    boolean invert = false;



    public CargoHatchHighGear(boolean leftSide, boolean redTeam) {

        if(!redTeam){
            this.wp1 = wp1;
            this.wp2 = wp2;
            this.wp3 = wp3;
            this.wp4 = wp4;
        }
        
        invert = leftSide;
        
        //Prepare for auto
    	addSequential(new ShiftHigh());
        addParallel(new HatchDefense());

        // Drive off platform to caargo ship
        addSequential(new DriveWaypoint(wp0, invert, 12.0, 5.0, true, ChassisConst.DriveHighSlow, ChassisConst.GyroDriveHigh));
        //addSequential(new ShiftHigh());
        addSequential(new DriveWaypoint(wp1, invert, 3.0, 3.0, true, ChassisConst.DriveHigh, ChassisConst.GyroDriveHigh));
        
        // Turn towards cargo ship
        
       // addSequential(new ShiftLow());
        if(!invert){
            addSequential(new TurnGyroAbs(-90, 3.0, 1.5, ChassisConst.GyroTurnHigh));
        }
        else{
            addSequential(new TurnGyroAbs(90, 3.0, 1.5, ChassisConst.GyroTurnHigh));
        }
        addSequential(new TurnLimelight(2.5, 0.5));

        // Place first hatch
        //addSequential(new ShiftHigh());
        addParallel(new SetHandAngle(HandConst.hatchPlacementLow));
        addParallel(new SetLiftPosition(LiftConst.DeployHatchLow));
        addSequential(new DriveLimelight(0.3, 0.5));
        addSequential(new WaitCommand(0.15));
        addParallel(new EjectHatch(1.0));
        addSequential(new WaitCommand(0.2));

        // Backup
        addSequential(new DriveWaypointBackward(wp1, invert, 3.0, 3.0, true, ChassisConst.DriveHighSlow, ChassisConst.GyroDriveHigh));
        addSequential(new RollerOff());
        //addSequential(new WaitCommand(0.1)); //TODO: Should we remove this?

        // Drive towards human player station
        addSequential(new TurnGyroWaypoint(wp3, invert, 3.0, 1.5, ChassisConst.GyroDriveHigh));
        //addSequential(new WaitCommand(2.0));
        BBCommand parallelCommand = new DriveWaypoint(wp3, invert, 3.0, 3.0, true, ChassisConst.DriveHigh, ChassisConst.GyroDriveHigh);
        addSequential(parallelCommand);
        //addSequential(new WaitCommand(0.2));
        //addSequential(new ShiftHigh());
        //addSequential(new HatchDefense());
        addSequential(new CheckDone(parallelCommand));
        //addSequential(new WaitCommand(100.0));

        // Pickup second hatch
        //addSequential(new ShiftLow());
        addSequential(new TurnLimelight(3.0, 1.8));
        addParallel(new SensoredHatchPickup());
        addSequential(new DriveLimelightCurrentSense(0.25, 1.0, 10));
        
        //addSequential(new WaitCommand(0.3));

        // Backup and head towards cargo ship
        //parallelCommand = new DriveWaypointBackward(wp3, invert, 3.0, 1.5, true, ChassisConst.DriveHigh, ChassisConst.GyroDriveHigh);
        //addSequential(parallelCommand);
        //addSequential(new WaitCommand(0.2));
        //addSequential(new ShiftHigh());
        //addSequential(new WaitCommand(0.2));
        //addSequential(new HatchDefense());
        addSequential(new DriveWaypointBackward(wp4, invert, 3.0, 3.0, true, ChassisConst.DriveHigh, ChassisConst.GyroDriveHigh));
        
        
        // Turn towards cargo ship
        //addSequential(new SetHandAngle(HandConst.hatchPlacementLow));       //run in parallel?
        addParallel(new SetLiftPosition(LiftConst.DeployHatchLow));
        //addSequential(new ShiftLow());
        if(!invert){
            addSequential(new TurnGyroAbs(-80, 3.0, 1.5, ChassisConst.GyroTurnHigh));
        }
        else{
            addSequential(new TurnGyroAbs(80, 3.0, 1.5, ChassisConst.GyroTurnHigh));
        }
        addSequential(new TurnLimelight(2.5, 0.5));

        // Place second hatch
        //addSequential(new ShiftHigh());
        
        addSequential(new DriveLimelight(0.3, 0.5));
        addSequential(new WaitCommand(0.1));
        addParallel(new EjectHatch(1.0));
        addSequential(new WaitCommand(0.1));

        //backup and end in a position suitable for drivers
        addParallel(new DriveWaypointBackward(wp4, invert, 3.0, 1.5, true, ChassisConst.DriveHighSlow, ChassisConst.GyroDriveHigh));
        addSequential(new Defense());
        

    }
}
