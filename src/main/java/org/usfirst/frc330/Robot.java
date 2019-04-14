// RobotBuilder Version: 2.0BB
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc330;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.WPILibVersion;
import org.usfirst.frc330.subsystems.*;
import org.usfirst.frc330.util.*;
import org.usfirst.frc330.util.Logger.Severity;
import org.usfirst.frc330.autoCommands.*;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in 
 * the project.
 */
public class Robot extends TimedRobot {

    Command autonomousCommand;
    SendableChooser<Command> autoProgram = new SendableChooser<>();

    public static OI oi;
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public static Chassis chassis;
    public static Lift lift;
    public static Hand hand;
    public static Pickup pickup;
    public static Frills frills;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public static Buzzer buzzer;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    @Override
    public void robotInit() {

        frills = new Frills();
        chassis = new Chassis();
        lift = new Lift();
        hand = new Hand();
        pickup = new Pickup();

        // OI must be constructed after subsystems. If the OI creates Commands
        //(which it very likely will), subsystems are not guaranteed to be
        // constructed yet. Thus, their requires() statements may grab null
        // pointers. Bad news. Don't move it.
        oi = new OI();

        // Add commands to Autonomous Sendable Chooser

        autoProgram.setDefaultOption("Do Nothing", new DoNothing());
        autoProgram.addOption("Hab1Hatch", new Hab1Hatch());

        //Hab 2 Rocket Autos
        autoProgram.addOption("RightBlue_Hab2RocketHatches", new Hab2TwoHatch(false, false));
        autoProgram.addOption("LeftBlue_Hab2RocketHatches", new Hab2TwoHatch(true, false));
        autoProgram.addOption("RightRed_Hab2RocketHatches", new Hab2TwoHatch(false, false));
        autoProgram.addOption("LeftRed_Hab2RocketHatches", new Hab2TwoHatch(true, false));

        // Hab 2 Cargo Ship Autos
        autoProgram.addOption("RightBlue_2CargoHatches", new CargoHatchHighGear(false, false));
        autoProgram.addOption("LeftBlue_2CargoHatches", new CargoHatchHighGear(true, false));
        autoProgram.addOption("RightRed_2CargoHatches", new CargoHatchHighGear(false, true));
        autoProgram.addOption("LeftRed_2CargoHatches", new CargoHatchHighGear(true, true));

        autoProgram.addOption("PoorChoice", new AllenTest());
        autoProgram.addOption("ReallyPoorChoice", new CargoHatchHighGear(true, false));
        autoProgram.addOption("AreYouCrazy?!", new CargoHatchHighGear(false, false));
        autoProgram.addOption("Somebody Stop Me!", new FrontBackRocket(false, false));

        SmartDashboard.putData("Auto mode", autoProgram);

        // Setup Cameras --------------------------------------------------------
        frills.initDriverCamera();
        // </Cameras> -------------------------------------------------------------

        //Setup the buzzer
        buzzer = new Buzzer(frills.buzzer);
                
        // ---------------------------------------------------------------------
        // Logging
        // ---------------------------------------------------------------------
        
        CSVLoggable temp = new CSVLoggable(false) {
			public double get() { return RobotController.getBatteryVoltage(); }
    	};
    	CSVLogger.getInstance().add("BatteryV", temp);
    	
    	temp = new CSVLoggable(false) {
			public double get() { return DriverStation.getInstance().getMatchTime(); }
    	};
    	CSVLogger.getInstance().add("MatchTime", temp);
    	
    	temp = new CSVLoggable(false) {
			public double get() { 
				if (DriverStation.getInstance().isDisabled())
					return 0.0;
				else if (DriverStation.getInstance().isAutonomous())
					return 1.0;
				else if (DriverStation.getInstance().isOperatorControl())
                    return 2.0;
                else if (DriverStation.getInstance().isTest())
                    return 3.0;
				else
					return -1.0;}
    	};
    	CSVLogger.getInstance().add("RobotMode", temp);
        
        CSVLogger.getInstance().writeHeader();

        CSVLogger.getInstance().setPrintOnTimeout(false);    //TODO set to false for competition
        
        Logger.getInstance().println("BeachbotLib Version:                " + BeachbotLibVersion.Version, Severity.INFO);
        Logger.getInstance().println("WPILib Version:                     " + WPILibVersion.Version, Severity.INFO);
        Logger.getInstance().println("NavX Firmware Version:              " + chassis.getNavXFirmware(), Severity.INFO);

        Logger.getInstance().println("Talon Intake Firmware Version:      " + pickup.getIntakeFirmwareVersion(), Severity.INFO);
        Logger.getInstance().println("Talon Hand Firmware Version:        " + hand.getHandFirmwareVersion(), Severity.INFO);
        Logger.getInstance().println("Talon Lift1 Firmware Version:       " + lift.getLift1FirmwareVersion(), Severity.INFO);
        Logger.getInstance().println("Talon Lift2 Firmware Version:       " + lift.getLift2FirmwareVersion(), Severity.INFO);
        Logger.getInstance().println("Talon Lift3 Firmware Version:       " + lift.getLift3FirmwareVersion(), Severity.INFO); 

        if (getIsPracticeRobot())
        	Logger.getInstance().println("Practice Robot Detected",Severity.DEBUG);
        else
        	Logger.getInstance().println("Competition Robot Detected",Severity.DEBUG);
        // </Logging> ----------------------------------------------------------
        
        buzzer.enable(0.4);
    }

    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    @Override
    public void disabledInit(){
        Logger.getInstance().println("Disabled Init", Severity.INFO);
    	Logger.getInstance().println("Battery Voltage: " + RobotController.getBatteryVoltage(), Severity.INFO);
        Scheduler.getInstance().removeAll();

        Robot.lift.stopLift();
        Robot.hand.stopHand();

        Robot.pickup.rollerOff();
        
        Robot.chassis.stopDrive();
        
        Robot.pickup.clawOff();
        Robot.lift.retractClimbPin();

        Robot.frills.disableLimelightLEDs();
    }

    String autoName;

    @Override
    public void disabledPeriodic() {
        Scheduler.getInstance().run();
        chassis.calcXY();
    	CSVLogger.getInstance().writeData();
    	Logger.getInstance().updateDate();
    	CSVLogger.getInstance().updateDate();
    	// if (autoProgram.getSelected().getName() != null)
    	// 	autoName = autoProgram.getSelected().getName();
    	// else
    	// 	autoName = "None Selected";
    	// SmartDashboard.putString("Selected Auto", autoName );
        buzzer.update();
        Robot.frills.updateLEDs();
        if(NetworkTableInstance.getDefault().getTable("limelight").getEntry("getpipe").getDouble(-99) < 0.5){
            Robot.frills.disableLimelightLEDs(); 
        }
    }

    @Override
    public void autonomousInit() {
        buzzer.enable(1.25);
        Logger.getInstance().println("Autonomous Init", true, Severity.INFO);
        Robot.frills.enableLimelightLEDs();
    	
        Robot.chassis.resetPosition();
        
        autonomousCommand = autoProgram.getSelected();
        // schedule the autonomous command (example)
        if (autonomousCommand != null) autonomousCommand.start();

        Logger.getInstance().println("Running Auto: " + autonomousCommand.getName(),true, Severity.INFO);
    	Logger.getInstance().println("Event: " + DriverStation.getInstance().getEventName() + 
    			" Match Type: " + DriverStation.getInstance().getMatchType() + " Match Number: " +
    			DriverStation.getInstance().getMatchNumber(), true, Severity.INFO);

    	
	    if(Math.abs(Robot.chassis.getAngle()) > 0.2){
	    	Robot.chassis.resetPosition();
	    	Logger.getInstance().println("Gyro failed to reset, retrying", Severity.ERROR);
        }
        Robot.frills.setColorRGB(0,0,0);
    }

    /**
     * This function is called periodically during autonomous
     */
    @Override
    public void autonomousPeriodic() {
        chassis.calcXY();

        Scheduler.getInstance().run();
        chassis.pidDriveAuto();
    	CSVLogger.getInstance().writeData();
        buzzer.update();
        Robot.frills.updateLEDs();
    }

    @Override
    public void teleopInit() {
        Robot.frills.setColorRGB(0,0,0);
        Logger.getInstance().println("Teleop Init", Severity.INFO);
        buzzer.enable(1.25);
        Robot.frills.enableLimelightLEDs();
        
        // This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) autonomousCommand.cancel();
    }

    /**
     * This function is called periodically during operator control
     */
    @Override
    public void teleopPeriodic() {
        chassis.calcXY();
        Scheduler.getInstance().run();
        chassis.pidDrive();
        CSVLogger.getInstance().writeData();
        buzzer.update();
        Robot.frills.updateLEDs();
    }

    // -----------------------------------------------------------
    // Get Methods
    // -----------------------------------------------------------

    public boolean getIsPracticeRobot() {
    	return frills.getIsPracticeRobot();
    }

}
