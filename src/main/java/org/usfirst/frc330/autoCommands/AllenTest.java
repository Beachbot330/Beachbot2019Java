package org.usfirst.frc330.autoCommands;


import org.usfirst.frc330.commands.*;
import edu.wpi.first.wpilibj.command.BBCommandGroup;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AllenTest extends BBCommandGroup {


    public AllenTest() {
    	
    	addSequential(new ShiftHigh());
    	addSequential(new Defense());
    	
    }
}
