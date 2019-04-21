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
public class AltCargoship extends BBCommandGroup {
    //PID Gains
    //PIDGains RampDrive = new PIDGains(0.10, 0, 0.00, 0, 0.3, ChassisConst.defaultMaxOutputStep, ChassisConst.defaultMinStartOutput, "DriveLow");    //AP 3/12/19

    Waypoint wp0 = new Waypoint(0, 85, 0);   //hab1
    Waypoint wp1 = new Waypoint(26+10, 240-4-4, 0); // Near cargo ship, first hatch
    Waypoint wp2 = new Waypoint(62, 126, 0); //Near rocket
    Waypoint wp3 = new Waypoint(70-5, -5, 0); //Near human player
    Waypoint wp4 = new Waypoint(22-15, 260-15+4, 0); //Cargo Ship Far

    boolean invert = false;



    public AltCargoship(boolean leftSide, boolean redTeam) {

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
        addSequential(new DriveWaypoint(wp1, invert, 3.0, 3.0, true, ChassisConst.DriveHigh, ChassisConst.GyroDriveHigh));
        
        // Turn towards cargo ship
        addParallel(new SetHandAngle(HandConst.hatchPlacementLow));
        if(!invert){
            addSequential(new TurnGyroAbs(-90, 3.0, 1.5, ChassisConst.GyroTurnHigh));
        }
        else{
            addSequential(new TurnGyroAbs(90, 3.0, 1.5, ChassisConst.GyroTurnHigh));
        }
        addSequential(new TurnLimelight(2.5, 1.5)); //Was 0.5, confirm change

        // Place first hatch
        addParallel(new SetHandAngle(HandConst.hatchPlacementLow));
        addParallel(new SetLiftPosition(LiftConst.DeployHatchLow));
        double throttle = 0.4; double timeout = 1.5; double jerkThreshold = 1.0;
        addSequential(new DriveUntilJerk(throttle, timeout, jerkThreshold));
        addSequential(new EjectHatch());
        addSequential(new WaitCommand(0.1));

        // Backup
        addSequential(new DriveWaypointBackward(wp1, invert, 3.0, 3.0, true, ChassisConst.DriveHighSlow, ChassisConst.GyroDriveHigh));
        addSequential(new RollerOff());

        // Drive towards human player station
        addSequential(new TurnGyroWaypoint(wp3, invert, 3.0, 1.5, ChassisConst.GyroDriveHigh));
        BBCommand parallelCommand = new DriveWaypoint(wp3, invert, 3.0, 3.0, true, ChassisConst.DriveHigh, ChassisConst.GyroDriveHigh);
        addSequential(parallelCommand);
        addSequential(new CheckDone(parallelCommand));

        // Pickup second hatch
        addSequential(new LimelightConditionalTurnGyroAbs(180, 8.0, 1.5, ChassisConst.GyroTurnHigh)); //Turn towards wall (since we missed seeing the target once)
        addSequential(new TurnLimelight(3.0, 1.8));
        addParallel(new SensoredHatchPickup());
        addSequential(new DriveLimelightCurrentSense(0.25, 1.0, 10));
        
        //addSequential(new WaitCommand(0.3));

        // Backup and head towards cargo ship
        addSequential(new DriveWaypointBackward(wp4, invert, 3.0, 3.0, true, ChassisConst.DriveHigh, ChassisConst.GyroDriveHigh));
        
        // Turn towards cargo ship
        addParallel(new SetLiftPosition(LiftConst.DeployHatchLow));
        if(!invert){
            addSequential(new TurnGyroAbs(-90, 3.0, 1.5, ChassisConst.GyroTurnHigh));
        }
        else{
            addSequential(new TurnGyroAbs(90, 3.0, 1.5, ChassisConst.GyroTurnHigh));
        }
        addSequential(new TurnLimelight(2.5, 0.5));

        // Place second hatch
        throttle = 0.4; timeout = 1.5; jerkThreshold = 1.0;
        addSequential(new DriveLimelightUntilJerk(throttle, timeout, jerkThreshold));
        addParallel(new EjectHatch());
        addSequential(new WaitCommand(0.2));

        //backup and end in a position suitable for drivers
        addParallel(new DriveWaypointBackward(wp4, invert, 3.0, 1.5, true, ChassisConst.DriveHighSlow, ChassisConst.GyroDriveHigh));
        addSequential(new Defense());
        

    }
}
