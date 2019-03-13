package org.usfirst.frc330.autoCommands;


import org.usfirst.frc330.commands.*;
import org.usfirst.frc330.commands.commandgroups.Defense;
import org.usfirst.frc330.commands.drivecommands.*;
import org.usfirst.frc330.constants.ChassisConst;
import org.usfirst.frc330.wpilibj.PIDGains;

import edu.wpi.first.wpilibj.command.BBCommandGroup;

/**
 *
 */
public class AllenTest extends BBCommandGroup {
    //PID Gains
    //PIDGains RampDrive = new PIDGains(0.10, 0, 0.00, 0, 0.3, ChassisConst.defaultMaxOutputStep, ChassisConst.defaultMinStartOutput, "DriveLow");    //AP 3/12/19

    Waypoint wp1 = new Waypoint(0, 60, 0);
    Waypoint wp2 = new Waypoint(60, 96, 0);
    Waypoint wp3 = new Waypoint(60, 0, 0);



    public AllenTest() {
    	
    	addSequential(new ShiftLow());
        addParallel(new Defense());

        // double distance, double tolerance, double timeout, boolean stopAtEnd, PIDGains driveGains, PIDGains gyroGains
        addSequential(new DriveDistanceAtCurAngle(wp1.getY(), 3.0, 3.0, true, ChassisConst.DriveLow, ChassisConst.GyroDriveLow));
        addSequential(new TurnGyroWaypoint(wp2, false, 3.0, 3.0, ChassisConst.GyroTurnLow));
        addSequential(new DriveWaypoint(wp2, false, 3.0, 3.0, true, ChassisConst.DriveLow, ChassisConst.GyroDriveLow));
        addSequential(new TurnLimelight());
        addSequential(new DriveLimelight(0.2, 2.0));
    }
}
