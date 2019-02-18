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

import org.usfirst.frc330.util.Logger;
import org.usfirst.frc330.util.Logger.Severity;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.*;

// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DigitalOutput;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS

/**
 *
 */

public class Frills extends Subsystem {	

    boolean lockout = false;

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    public DigitalOutput buzzer;
    private DigitalInput isPracticeRobot;
    private DigitalOutput indicatorBar;

    private DigitalOutput redLED;
    private DigitalOutput greenLED;
    private DigitalOutput blueLED;

    private DigitalInput visionTarget;
    private DigitalInput hatch;
    private DigitalInput alignment;
    private DigitalInput ball;
    private DigitalInput assistMode;


    private UsbCamera driverCam;

    public Frills() {
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        buzzer = new DigitalOutput(4);
        addChild("Buzzer",buzzer);
        
        
        indicatorBar = new DigitalOutput(3);
        addChild("indicatorBar",indicatorBar);
        
        
        redLED = new DigitalOutput(0);
        addChild("redLED",redLED);
        
        
        greenLED = new DigitalOutput(1);
        addChild("greenLED",greenLED);
        
        
        blueLED = new DigitalOutput(2);
        addChild("blueLED",blueLED);
        
        
        visionTarget = new DigitalInput(5);
        addChild("visionTarget",visionTarget);
        
        
        hatch = new DigitalInput(6);
        addChild("hatch",hatch);
        
        
        alignment = new DigitalInput(7);
        addChild("alignment",alignment);
        
        
        ball = new DigitalInput(8);
        addChild("ball",ball);
        
        
        assistMode = new DigitalInput(9);
        addChild("assistMode",assistMode);
        
        

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
    }

    @Override
    public void initDefaultCommand() {
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }

    @Override
    public void periodic() {
        // Put code here to be run every loop
    }

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=PIDGETTERS


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=PIDGETTERS

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
    //Camera Functions ----------------------------------------------------
    //VERIFY implement frills.initDriverCamera();  (See 2017 Code)
    //VERIFY Implement the other camera functions as well
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


public void setColor(Color color){
    redLED.disablePWM();
    greenLED.disablePWM();
    blueLED.disablePWM();
    redLED.setPWMRate(500);
    greenLED.setPWMRate(500);
    blueLED.setPWMRate(500);
    redLED.enablePWM(((color.getRed())/5)/255.0);
    greenLED.enablePWM(((color.getGreen())/5)/255.0);
    blueLED.enablePWM(((color.getBlue())/5)/255.0);
}

public void setColorRGB(int red, int green, int blue){
    redLED.disablePWM();
    greenLED.disablePWM();
    blueLED.disablePWM();
    redLED.setPWMRate(500);
    greenLED.setPWMRate(500);
    blueLED.setPWMRate(500);
    redLED.enablePWM((red/5)/255.0);
    greenLED.enablePWM((green/5)/255.0);
    blueLED.enablePWM((blue/5)/255.0);
}

public void disableAllPWM() {
    redLED.disablePWM();
    greenLED.disablePWM();
    blueLED.disablePWM();
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


    public Color RED = new Color(255, 0, 0);
    public Color GREEN = new Color(0, 255, 0);
    public Color BLUE = new Color(0, 0, 255);
    public Color YELLOW1 = new Color(115, 80, 0); //also 255, 255, 0
    public Color YELLOW2 = new Color(255, 255, 0);
    public Color PURPLE = new Color(127, 0, 255);
    public Color WHITE = new Color(51, 51, 51);

    public boolean getIsVisionTargetInSight() {
        return visionTarget.get();
    }

    public boolean getIsHatchAttained() {
        return hatch.get();
    }

    public boolean getIsMisaligned() {
        return alignment.get();
    }

    public boolean getIsBallAttained() {
        return ball.get();
    }

    public boolean getIsInAssistMode(){
        return assistMode.get();
    }

    public void indicatorBarYellow() {
        if(getIsVisionTargetInSight()) {
            setColor(YELLOW2);
        }
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

    public void indicatorBarBlue() {
        if(getIsBallAttained()) {
            setColor(BLUE);
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

}

}


