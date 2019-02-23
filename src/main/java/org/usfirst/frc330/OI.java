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
import org.usfirst.frc330.commands.*;
import org.usfirst.frc330.commands.commandgroups.*;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.shuffleboard.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);

    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.

    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:

    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());

    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());

    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());


    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public JoystickButton shiftLow_1;
    public JoystickButton climbDeploy_2WH;
    public JoystickButton autoStear_3WH;
    public JoystickButton ejectBall_4;
    public JoystickButton ejectBall_5;
    public Joystick driverL;
    public JoystickButton shiftHigh_1;
    public JoystickButton ejectBall_4R;
    public JoystickButton ejectBall_5R;
    public JoystickButton climb_3;
    public Joystick driverR;
    public JoystickButton loadHatch_1WH;
    public JoystickButton defense_1WR;
    public JoystickButton ballPickup_2WH;
    public JoystickButton defense_2WR;
    public JoystickButton loadHatch_3WH;
    public JoystickButton defense_3WR;
    public JoystickButton handOut_4WH;
    public JoystickButton raiseLift_5;
    public JoystickButton openClawReverseRoller_6;
    public JoystickButton lowerLift_7;
    public JoystickButton hatchRelese_9;
    public JoystickButton killAll_10;
    public Joystick gamePad;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    public ShuffleboardTab liftTab;
    public ShuffleboardTab pickupTab;
    public ShuffleboardTab chassisTab;
    public ShuffleboardTab handTab;
    public ShuffleboardTab complexTab;
    public ShuffleboardTab ledTab;

    public OI() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS

        gamePad = new Joystick(2);
        
        killAll_10 = new JoystickButton(gamePad, 10);
        killAll_10.whenPressed(new KillAll());
        hatchRelese_9 = new JoystickButton(gamePad, 9);
        hatchRelese_9.whenPressed(new HatchRelease());
        lowerLift_7 = new JoystickButton(gamePad, 7);
        lowerLift_7.whenPressed(new LowerLift());
        openClawReverseRoller_6 = new JoystickButton(gamePad, 6);
        openClawReverseRoller_6.whileHeld(new OpenClawReverseRoller());
        raiseLift_5 = new JoystickButton(gamePad, 5);
        raiseLift_5.whenPressed(new RaiseLift());
        handOut_4WH = new JoystickButton(gamePad, 4);
        handOut_4WH.whileHeld(new HandOut());
        defense_3WR = new JoystickButton(gamePad, 3);
        defense_3WR.whenReleased(new Defense());
        loadHatch_3WH = new JoystickButton(gamePad, 3);
        loadHatch_3WH.whileHeld(new LoadHatch());
        defense_2WR = new JoystickButton(gamePad, 2);
        defense_2WR.whenReleased(new Defense());
        ballPickup_2WH = new JoystickButton(gamePad, 2);
        ballPickup_2WH.whileHeld(new LoadBall());
        defense_1WR = new JoystickButton(gamePad, 1);
        defense_1WR.whenReleased(new Defense());
        loadHatch_1WH = new JoystickButton(gamePad, 1);
        loadHatch_1WH.whileHeld(new LoadHatch());
        driverR = new Joystick(1);
        
        climb_3 = new JoystickButton(driverR, 3);
        climb_3.whenPressed(new Climb());
        ejectBall_5R = new JoystickButton(driverR, 5);
        ejectBall_5R.whenPressed(new EjectBall());
        ejectBall_4R = new JoystickButton(driverR, 4);
        ejectBall_4R.whenPressed(new EjectBall());
        shiftHigh_1 = new JoystickButton(driverR, 1);
        shiftHigh_1.whenPressed(new ShiftHigh());
        driverL = new Joystick(0);
        
        ejectBall_5 = new JoystickButton(driverL, 5);
        ejectBall_5.whenPressed(new EjectBall());
        ejectBall_4 = new JoystickButton(driverL, 4);
        ejectBall_4.whenPressed(new EjectBall());
        autoStear_3WH = new JoystickButton(driverL, 3);
        autoStear_3WH.whileHeld(new AutoStear());
        climbDeploy_2WH = new JoystickButton(driverL, 2);
        climbDeploy_2WH.whileHeld(new ClimbDeployVerify());
        shiftLow_1 = new JoystickButton(driverL, 1);
        shiftLow_1.whenPressed(new ShiftLow());


        // SmartDashboard Buttons

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        

        //Add items to shuffleboard
        liftTab = Shuffleboard.getTab("Lift");
        liftTab.add("RaiseLift", new RaiseLift());
        liftTab.add("LowerLift", new LowerLift());
        liftTab.add("RaiseLiftSensor", new RaiseLiftSensor());
        liftTab.add("LowerLiftSensor", new LowerLiftSensor());
        liftTab.add("CalibrateLift", new CalibrateLift());

        pickupTab = Shuffleboard.getTab("Pickup");
        pickupTab.add("RollerOn", new RollerOn());
        pickupTab.add("AllenTestCommand", new AllenCommand());
        pickupTab.add("ReversePickup", new ReversePickup());
        pickupTab.add("CloseClaw", new CloseClaw());
        pickupTab.add("OpenClaw", new OpenClaw());
        pickupTab.add("RollerOff", new RollerOff());
        pickupTab.add("OpenClawReverseRoller", new OpenClawReverseRoller());
        pickupTab.add("EjectBall", new EjectBall());
        pickupTab.add("BallKickExtend", new BallKickExtend());
        pickupTab.add("BallKickRetract", new BallKickRetract());

        chassisTab = Shuffleboard.getTab("Chassis");
        chassisTab.add("ShiftHigh", new ShiftHigh());
        chassisTab.add("ShiftLow", new ShiftLow());

        handTab = Shuffleboard.getTab("Hand");
        handTab.add("HandOut", new HandOut());

        complexTab = Shuffleboard.getTab("Complex");
        complexTab.add("LoadHatch", new LoadHatch());
        complexTab.add("LoadBall", new LoadBall());
        complexTab.add("Defense", new Defense());
        complexTab.add("ClimbDeploy", new ClimbDeploy());
        complexTab.add("DeployClimbPins", new DeployClimbPins());
        complexTab.add("RetractClimbPins", new RetractClimbPins());
        complexTab.add("EngageClimbLockout", new EngageClimbLockout());
        complexTab.add("DisableClimbLockout", new DisableClimbLockout());
        complexTab.add("Climb", new Climb());

        ledTab = Shuffleboard.getTab("LED");
        ledTab.add("BarRed", new BarRed());
        ledTab.add("BarBlue", new BarBlue());
        ledTab.add("BarGreen", new BarGreen());
        ledTab.add("BarPurple", new BarPurple());
        ledTab.add("BarWhite", new BarWhite());
        ledTab.add("BarYellow", new BarYellow());
        

        Shuffleboard.selectTab("Chassis");
    }

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS
    public Joystick getDriverL() {
        return driverL;
    }

    public Joystick getDriverR() {
        return driverR;
    }

    public Joystick getgamePad() {
        return gamePad;
    }


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS
}

