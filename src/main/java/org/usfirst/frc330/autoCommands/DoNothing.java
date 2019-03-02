package org.usfirst.frc330.autoCommands;

import org.usfirst.frc330.commands.*;
import edu.wpi.first.wpilibj.command.BBCommandGroup;

/**
 *
 */
public class DoNothing extends BBCommandGroup {


    public DoNothing() {
        
        addSequential(new ShiftLow());
        //addSequential(new IsFinishedFalse());
       
    }
}
