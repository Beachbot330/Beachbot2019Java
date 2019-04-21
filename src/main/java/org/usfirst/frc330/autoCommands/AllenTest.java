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
public class AllenTest extends BBCommandGroup {
    //PID Gains
    //PIDGains RampDrive = new PIDGains(0.10, 0, 0.00, 0, 0.3, ChassisConst.defaultMaxOutputStep, ChassisConst.defaultMinStartOutput, "DriveLow");    //AP 3/12/19

    Waypoint wp1 = new Waypoint(0, 60, 0);

    public AllenTest() {
    	
    	addSequential(new LimelightConditionalTurnGyroAbs(180, 8.0, 1.5, ChassisConst.GyroTurnHigh)); //Turn towards wall (since we missed seeing the target once)

    }
}
