// RobotBuilder Version: 2.0BB
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

package org.usfirst.frc330.subsystems;

import org.usfirst.frc330.Robot;
import edu.wpi.first.wpilibj.Timer;
import org.usfirst.frc330.util.CSVLoggable;
import org.usfirst.frc330.util.CSVLogger;
import org.usfirst.frc330.util.Logger;
import org.usfirst.frc330.util.Logger.Severity;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.*;
import edu.wpi.first.networktables.NetworkTableInstance;
import org.usfirst.frc330.commands.commandgroups.*;
// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.DriverStation;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS

/**
 *
 */

public class Frills extends Subsystem {	

    public ShuffleboardTab shuffleboardTab = Shuffleboard.getTab("Frills");
    boolean lockout = false;

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    public DigitalOutput buzzer;
    private DigitalInput isPracticeRobot;

    private DigitalOutput redLED;
    private DigitalOutput greenLED;
    private DigitalOutput blueLED;


    private UsbCamera driverCam;

    int currentRed,currentGreen, currentBlue;

    public Frills() {
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        buzzer = new DigitalOutput(4);
        addChild("Buzzer",buzzer);
        
        
        isPracticeRobot = new DigitalInput(10);
        addChild("isPracticeRobot",isPracticeRobot);
        
        
        redLED = new DigitalOutput(5);
        addChild("redLED",redLED);
        
        
        greenLED = new DigitalOutput(9);
        addChild("greenLED",greenLED);
        
        
        blueLED = new DigitalOutput(7);
        addChild("blueLED",blueLED);
        
        

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS

        //-----------------------------------------------------------------------
        // Logging
        //-----------------------------------------------------------------------
        CSVLoggable temp = new CSVLoggable(this.shuffleboardTab) {
			public double get() { return NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv").getDouble(0); }
    	};
        CSVLogger.getInstance().add("Valid LL Target", temp);
        
        temp = new CSVLoggable(this.shuffleboardTab) {
			public double get() { return NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0); }
    	};
        CSVLogger.getInstance().add("LL Target Angle", temp);
        
        temp = new CSVLoggable(this.shuffleboardTab) {
			public double get() { return NetworkTableInstance.getDefault().getTable("limelight").getEntry("ta").getDouble(0); }
    	};
    	CSVLogger.getInstance().add("LL Target Area", temp);
    }

    @Override
    public void initDefaultCommand() {
    }

    @Override
    public void periodic() {
    }

    

    // Camera Functions ----------------------------------------------------
    public void initDriverCamera() {
    	try {
    		driverCam = CameraServer.getInstance().startAutomaticCapture("Driver", 0);
    		if (!driverCam.setResolution(160, 120))
    			Logger.getInstance().println("Resolution failed to set", Severity.ERROR);
    		if (!driverCam.setFPS(30))
    			Logger.getInstance().println("FPS failed to set", Severity.ERROR);
    		Logger.getInstance().println("DriverCam Get Video Mode: " + driverCam.getVideoMode().width +
    				", " + driverCam.getVideoMode().height + ", " + driverCam.getVideoMode().fps, Severity.DEBUG);
    		driverCam.setExposureManual(50);
    	}
    	catch (Exception ex) {
    		Logger.getInstance().println("Exception initializing Driver Camera", Severity.ERROR);
    		Logger.getInstance().printStackTrace(ex);
    	}
    }
    
    public void driverCameraBright(){
    	driverCam.setExposureAuto();
    }
    
    public void driverCameraDark(){
    	driverCam.setExposureManual(1);
    }
    //  </Camera Functions> -----------------------------------------------

	public boolean getIsPracticeRobot() {	
		return isPracticeRobot.get();
    }

    public void disableLockout() {
        this.lockout = false;
	}

	public void engageLockout() {
        this.lockout = true;
    }

    //-----------------------------------------------------------------------
    // LED code
    //-----------------------------------------------------------------------
    
    public static final double brightnessPercent = 50.26;    //EJO 3.10.19
    // ^ update this to modify the brightness level of the leds
    //eg 20.0 = 20.0% brightness
    public static final double brightnessDivisor = 100/brightnessPercent; // there should be no reason to modify this constant, only change the one above
    
    public static final double disabledLEDswapInterval = 0.6; //(seconds) EJO 3.11.19

    boolean target;
    double lastSwapped;

    public void updateLEDs(){
        if(DriverStation.getInstance().isDisabled()) { //if robot is disabled
            if(Timer.getFPGATimestamp() > (lastSwapped + disabledLEDswapInterval)) {
                //if the current  time is greater than the time the LEDs were last swapped plus the interval
                alternateBlueAndYellow(); //alternate the LEDs
                lastSwapped = Timer.getFPGATimestamp(); //update the lastSwapped double
            }
            
        } else if(DriverStation.getInstance().isEnabled()) { //if robot is enabled
            if(getIsVisionTargetInSight()) { // Target visible
                if(Robot.oi.driverL.getRawButton(1)){ //auto aiming
                    setColorRGB(100,0,0);
                }
                else{
                    setColorRGB(0,0,100); 
                }
                if(!target){
                    Logger.getInstance().println("Target acquired", Severity.INFO);
                    target = true;
                }
                SmartDashboard.putBoolean("VisionTarget", true);
            } else
            if(getIsHatchAttained()) { //if we have a hatch
                setColor(GREEN);
                Logger.getInstance().println("Hatch attained", Severity.INFO);
            } else
            if(getIsBallAttained()) { //if we have a ball
                setColor(PURPLE);
                Logger.getInstance().println("Ball attained", Severity.INFO);
            } 
            
        }

    }


    public final Color RED = new Color(255, 0, 0);
    public final Color GREEN = new Color(0, 255, 0);
    public final Color BLUE = new Color(0, 0, 255);
    public final Color YELLOW = new Color(255, 255, 0);
    public final Color YELLOW2 = new Color(115, 80, 0); 
    public final Color PURPLE = new Color(127, 0, 255);
    public final Color WHITE = new Color(51, 51, 51);

    public void setColor(Color color){
        redLED.disablePWM();
        greenLED.disablePWM();
        blueLED.disablePWM();
        redLED.setPWMRate(500);
        greenLED.setPWMRate(500);
        blueLED.setPWMRate(500);
        redLED.enablePWM(((color.getRed())/brightnessDivisor)/255.0);
        greenLED.enablePWM(((color.getGreen())/brightnessDivisor)/255.0);
        blueLED.enablePWM(((color.getBlue())/brightnessDivisor)/255.0);
    }

    public void setColorRGB(int red, int green, int blue){
        if (currentRed != red){
            redLED.disablePWM();
            redLED.setPWMRate(500);
            redLED.enablePWM((red/brightnessDivisor)/255.0);
            currentRed = red;
        }
        if (currentGreen != green){
            greenLED.disablePWM();
            greenLED.setPWMRate(500);
            greenLED.enablePWM((green/brightnessDivisor)/255.0);
            currentGreen = green;
        }
        if (currentBlue != blue){
            blueLED.disablePWM();
            blueLED.setPWMRate(500);
            blueLED.enablePWM((blue/brightnessDivisor)/255.0);
            currentBlue = blue;
        }
    }

    public void disableAllPWM() {
        redLED.disablePWM();
        greenLED.disablePWM();
        blueLED.disablePWM();
    }

    public Color lastcolor;
    public void alternateBlueAndYellow() {
        if(lastcolor == BLUE) {
            setColor(YELLOW);
            lastcolor = YELLOW;
        } else if(lastcolor == YELLOW) {
            setColor(BLUE);
            lastcolor = BLUE;
        }
    }

    public boolean getIsVisionTargetInSight() {
        return (NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv").getDouble(0) > 0.5); // Target visible
    }

    public void indicatorBarLimelight() {
        if(getIsVisionTargetInSight()) { // Target visible
            if(Robot.oi.driverL.getRawButton(1)){ //auto aiming
                setColorRGB(100,0,0);
            }
            else{
                setColorRGB(0,0,100); 
            }
            if(!target){
                Logger.getInstance().println("Target acquired", Severity.INFO);
                target = true;
            }
            SmartDashboard.putBoolean("VisionTarget", true);
        }
        //else if(NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv").getDouble(0) < 0.5){ //No target
        //    setColorRGB(0,0,0);
        //    SmartDashboard.putBoolean("VisionTarget", false);
        //    if(target){ //Target just lost
        //        target = false;
        //        Logger.getInstance().println("Target lost", Severity.INFO);
        //    } 
        //}
    }

    public boolean getIsHatchAttained() {
        return Robot.pickup.getHasHatch();
    }

    public boolean getIsMisaligned() {
        return false;
    }

    public boolean getIsBallAttained() {
        return Robot.pickup.getHasBall();
    }

    public boolean getIsInAssistMode(){
        return false;
    }

    public void indicatorBarGreen() {
        if(getIsHatchAttained()) {
            setColor(GREEN);
        }
    }

    public void indicatorBarRed() {
        if(getIsMisaligned()) {
            setColor(RED);
        }
    }

    public void indicatorBarYellow() {
        if(getIsBallAttained()) {
            setColor(YELLOW2);
        }
    }

    public void indicatorBarPurple() {
        if(!getIsBallAttained() && getIsInAssistMode()) {
            setColor(PURPLE);
        }
    }

    public void indicatorBarWhite(){
        if(lockout) {
            setColor(WHITE);
        }
    }

    class Color{
        int r, g, b;
        private Color(int r, int g, int b) {
            this.r = r;
            this.g = g;
            this.b = b;
        }

        public int getRed(){
            return r;
        }

        public int getGreen(){
            return g;
        }

        public int getBlue(){
            return b;
        }
        
    }


}
