package org.usfirst.frc330.autoCommands;


import org.usfirst.frc330.commands.*;
import org.usfirst.frc330.commands.commandgroups.Defense;
import org.usfirst.frc330.commands.drivecommands.*;
import org.usfirst.frc330.constants.ChassisConst;

import edu.wpi.first.wpilibj.command.BBCommandGroup;

/**
 *
 */
public class AllenTest extends BBCommandGroup {


    public AllenTest() {
    	
    	addSequential(new ShiftLow());
        //addSequential(new Defense());
        //addSequential(new DriveDistance(221.5, ChassisConst.DriveLow));
        // addSequential(new TurnGyroAbs(90, 2, ChassisConst.GyroTurnLow));
        // addSequential(new WaitCommand(4));
    	// addSequential(new TurnGyroAbs(180, 2, ChassisConst.GyroTurnLow));
        // addSequential(new WaitCommand(4));
        // addSequential(new TurnGyroAbs(270, 2, ChassisConst.GyroTurnLow));
        // addSequential(new WaitCommand(4));
    	// addSequential(new TurnGyroAbs(360, 2, ChassisConst.GyroTurnLow));
        // addSequential(new WaitCommand(4));

        // double distance, double tolerance, double timeout, boolean stopAtEnd, PIDGains driveGains, PIDGains gyroGains
        addSequential(new DriveDistanceAtCurAngle(5*12, 3.0, 3.0, true, ChassisConst.DriveLow, ChassisConst.GyroDriveLow));
        addSequential(new TurnGyroWaypoint(5*12, 8*12, 3.0, 3.0, ChassisConst.GyroTurnLow));
        addSequential(new DriveWaypoint(5*12, 8*12, 3.0, 3.0, true, ChassisConst.DriveLow, ChassisConst.GyroDriveLow));

    }
}
