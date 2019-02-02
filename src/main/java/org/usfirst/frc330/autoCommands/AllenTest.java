package org.usfirst.frc330.autoCommands;


import org.usfirst.frc330.commands.*;
import org.usfirst.frc330.commands.commandgroups.Defense;

import edu.wpi.first.wpilibj.command.BBCommandGroup;

/**
 *
 */
public class AllenTest extends BBCommandGroup {


    public AllenTest() {
    	
    	addSequential(new ShiftHigh());
    	addSequential(new Defense());
    	
    }
}
