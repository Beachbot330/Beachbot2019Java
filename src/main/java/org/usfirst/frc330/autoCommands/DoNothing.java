package org.usfirst.frc330.autoCommands;

import org.usfirst.frc330.commands.*;
import edu.wpi.first.wpilibj.command.BBCommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class DoNothing extends BBCommandGroup {


    public DoNothing() {
    	
       addSequential(new IsFinishedFalse());
       addParallel(new IsFinishedFalse());
       addParallel(new WaitCommand(1.0));
       
    }
}
