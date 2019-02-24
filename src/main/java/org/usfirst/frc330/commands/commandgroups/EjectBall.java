// RobotBuilder Version: 2.0BB
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc330.commands.commandgroups;

import edu.wpi.first.wpilibj.command.BBCommandGroup;

import org.usfirst.frc330.commands.*;

/**
 *
 */
public class EjectBall extends BBCommandGroup {

    public EjectBall() {
        addSequential(new BallKickExtend());
        addSequential(new WaitCommand(0.1));
        addSequential(new OpenClaw());
        addSequential(new ReversePickup());
        addSequential(new WaitCommand(1.0));
        addSequential(new BallKickRetract());
        addSequential(new WaitCommand(1.0));
        addSequential(new RollerOff());
    } 
}
