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
import edu.wpi.first.wpilibj.DriverStation;
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
    public final Color RED = new Color(255, 0, 0);
    public final Color GREEN = new Color(0, 255, 0);
    public final Color BLUE = new Color(0, 0, 255);
    public final Color YELLOW = new Color(255, 255, 0);
    public final Color YELLOW2 = new Color(115, 80, 0); 
    public final Color PURPLE = new Color(127, 0, 255);
    public final Color WHITE = new Color(51, 51, 51);
    
    public static final double brightnessPercent = 50.26;    //EJO 3.10.19
    // ^ update this to modify the brightness level of the leds
    //eg 20.0 = 20.0% brightness
    public static final double brightnessDivisor = 100/brightnessPercent; // there should be no reason to modify this constant, only change the one above
    
    public static final double disabledLEDswapInterval = 1.1; //(seconds) EJO 3.15.19
    // ^ the interval (in seconds) to swap the led colors while the robot is disabled

    public static final double gradientLoopDuration = 0.5;  //(seconds) EJO 3.17.19
    //^ the time (in seconds) to complete half of a gradient loop (from color A to color B)

    boolean target, hatch, ball;
    double lastSwapped;
    int counts = 0;

    //private enum switchColorList {
    //    YELLOW,
    //    BLUE,
    //    WHITE
    //};
    //private Color newcolor;
    //private int maxCycles = 100;
    //private int currentCycles;
    //private double rgbFactor;
    //private switchColorList currentcolor = switchColorList.YELLOW;
    //private switchColorList previouscolor = switchColorList.WHITE;

    public void updateLEDs(){
        if(DriverStation.getInstance().isDisabled()) { //if robot is disabled

            yellowBlueGradient();   //this needs to be called every cycle, each time it is called the LEDs are updated ONCE.

            //simple transition between yellow and blue - no gradient [DEPRICATED]
            //if(Timer.getFPGATimestamp() > (lastSwapped + disabledLEDswapInterval)) {
            //    //if the current  time is greater than the time the LEDs were last swapped plus the interval
            //    alternateBlueAndYellow(); //alternate the LEDs
            //    lastSwapped = Timer.getFPGATimestamp(); //update the lastSwapped double
            //}
            
            //more advanced gradient transition also DEPRICATED
        //if(Timer.getFPGATimestamp() > (lastSwapped + gradientLoopDuration/yellowBlueGradientArrayOLD.length)) {
        //    //if the current  time is greater than the time the LEDs were last updated plus the delay between each LED swap
        //    //it's important to understand that the gradientLoopDuration/yellowBlueGradientArray.length is 
        //    //the amount of cycles the LEDs are being updated each second
        //
        //    yellowBlueGradienOLDt(); //update the LED color
        //    lastSwapped = Timer.getFPGATimestamp(); //update the lastSwapped double
        //}

        //the below switch-case has been DEPRICATED
        //switch(currentcolor){
        //    
        //    case YELLOW:     //yellow
        //    {
        //        currentCycles = 45;
        //        rgbFactor = 255.0/currentCycles;
//
        //        if(counts++ <= currentCycles) {
        //            newcolor = new Color(255, 255, (int)Math.round(counts*rgbFactor));
        //            setColor(newcolor);
        //        } else {
        //            counts = 0;
        //            currentcolor = switchColorList.WHITE;
        //            previouscolor = switchColorList.YELLOW;
        //        }
        //        break;
        //    }
//
        //    case BLUE:     // blue
        //    {
        //        currentCycles = 45;
        //        rgbFactor = 255.0/currentCycles;
//
        //        if(counts++ <= currentCycles) {
        //            newcolor = new Color(255 - (int)Math.round(counts*rgbFactor), 255 - (int)Math.round(counts*rgbFactor), 255);
        //            setColor(newcolor);
        //        } else {
        //            counts = 0;
        //            currentcolor = switchColorList.WHITE;
        //            previouscolor = switchColorList.BLUE;
        //        }
        //        break;
        //    }
//
        //    default:  //WHITE
        //        currentCycles = 10;
        //        rgbFactor = 255.0/currentCycles;
//
        //        if(counts++ <= currentCycles) {
        //            newcolor = new Color(255, 255, 255);
        //            setColor(newcolor);   
        //        } else {
        //            counts = 0;
        //            if(previouscolor == switchColorList.YELLOW) currentcolor = switchColorList.BLUE;
        //            else if(previouscolor == switchColorList.BLUE) currentcolor = switchColorList.YELLOW;
        //        }
        //        break;
        //}
            
            
        } else if(DriverStation.getInstance().isEnabled()) { //if robot is enabled
            //limelight takes priority
            if(getIsVisionTargetInSight()) { // Target visible
                if(Robot.oi.driverL.getRawButton(1)){ //auto aiming
                    setColor(RED);
                }
                else{
                    setColor(BLUE);
                }
                if(!target){
                    Logger.getInstance().println("Target acquired", Severity.INFO);
                    target = true;
                }
                SmartDashboard.putBoolean("VisionTarget", true);
            } else if(getIsVisionTargetNotInSight()) {//we don't have a target 
                setColorRGB(0, 0, 0); 
                //first things first, turn off LEDs 
                //they will be turned back on if any of the below conditions are met.

                //again, limelight takes priority 
                SmartDashboard.putBoolean("VisionTarget", false);
                if(target){ //if we HAD a target, we don't currently, so we need to update the boolean
                    target = false;
                    Logger.getInstance().println("Target lost", Severity.INFO);
                } 

                //now that limelight is out of the way, we can move on to other LED checks
                if(getIsHatchAttained()) { //if we have a hatch
                    setColor(GREEN);
                    if(!hatch) {    //if the hatch boolean is false
                        Logger.getInstance().println("Hatch attained", Severity.INFO);
                        hatch = true;   //change the boolean to true
                    }
                } else
                if(getIsBallAttained()) { //if we have a ball
                    setColor(PURPLE);
                    if(!ball) { //if the ball boolean is false
                        Logger.getInstance().println("Ball attained", Severity.INFO);
                        ball = true;   //change the boolean to true
                    }
                }    
                
                //final checks (if we don't have an object but the boolean is true, set the boolean false)
                if(!getIsBallAttained() && ball) ball = false;
                if(!getIsHatchAttained() && hatch) hatch = false;
                
            }
            
        }

    }

    public void setColor(Color color){
        if(currentRed != color.getRed()) {
            redLED.disablePWM();
            redLED.setPWMRate(500);
            redLED.enablePWM(((color.getRed())/brightnessDivisor)/255.0);
            currentRed = color.getRed();
        }
        if(currentGreen != color.getGreen()) {
            greenLED.disablePWM();
            greenLED.setPWMRate(500);
            greenLED.enablePWM(((color.getGreen())/brightnessDivisor)/255.0);
            currentGreen = color.getGreen();
        }
        if(currentBlue != color.getBlue()) {
            blueLED.disablePWM();
            blueLED.setPWMRate(500);
            blueLED.enablePWM(((color.getBlue())/(brightnessDivisor/2))/255.0);
            currentBlue = color.getBlue();
        }
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
            blueLED.enablePWM((blue/(brightnessDivisor/2))/255.0);
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
        } else lastcolor = BLUE;
    }
    
    public Color[] yellowBlueGradientArray = new Color[] {        
        new Color(255,215,0),
        new Color(253,214,0),
        new Color(252,212,0),
        new Color(250,211,0),
        new Color(248,209,0),
        new Color(247,208,0),
        new Color(245,206,0),
        new Color(243,205,0),
        new Color(241,204,0),
        new Color(240,202,0),
        new Color(238,201,0),
        new Color(236,199,0),
        new Color(235,198,0),
        new Color(233,196,0),
        new Color(231,195,0),
        new Color(230,194,0),
        new Color(228,192,0),
        new Color(226,191,0),
        new Color(224,189,0),
        new Color(223,188,0),
        new Color(221,186,0),
        new Color(219,185,0),
        new Color(218,183,0),
        new Color(216,182,0),
        new Color(214,181,0),
        new Color(213,179,0),
        new Color(211,178,0),
        new Color(209,176,0),
        new Color(207,175,0),
        new Color(206,173,0),
        new Color(204,172,0),
        new Color(196,164,0),
        new Color(185,156,0),
        new Color(175,147,0),
        new Color(165,138,0),
        new Color(155,130,0),
        new Color(144,121,0),
        new Color(134,112,0),
        new Color(124,104,0),
        new Color(113,95,0),
        new Color(103,87,0),
        new Color(93,78,0),
        new Color(82,69,0),
        new Color(72,61,0),
        new Color(62,52,0),
        new Color(52,43,0),
        new Color(41,35,0),
        new Color(31,26,0),
        new Color(21,17,0),
        new Color(10,9,0),
        new Color(0,0,0),
        new Color(0,0,10),
        new Color(0,0,21),
        new Color(0,0,31),
        new Color(0,0,41),
        new Color(0,0,52),
        new Color(0,0,62),
        new Color(0,0,72),
        new Color(0,0,82),
        new Color(0,0,93),
        new Color(0,0,103),
        new Color(0,0,113),
        new Color(0,0,124),
        new Color(0,0,134),
        new Color(0,0,144),
        new Color(0,0,155),
        new Color(0,0,165),
        new Color(0,0,175),
        new Color(0,0,185),
        new Color(0,0,196),
        new Color(0,0,204),
        new Color(0,0,206),
        new Color(0,0,207),
        new Color(0,0,209),
        new Color(0,0,211),
        new Color(0,0,213),
        new Color(0,0,214),
        new Color(0,0,216),
        new Color(0,0,218),
        new Color(0,0,219),
        new Color(0,0,221),
        new Color(0,0,223),
        new Color(0,0,224),
        new Color(0,0,226),
        new Color(0,0,228),
        new Color(0,0,230),
        new Color(0,0,231),
        new Color(0,0,233),
        new Color(0,0,235),
        new Color(0,0,236),
        new Color(0,0,238),
        new Color(0,0,240),
        new Color(0,0,241),
        new Color(0,0,243),
        new Color(0,0,245),
        new Color(0,0,247),
        new Color(0,0,248),
        new Color(0,0,250),
        new Color(0,0,252),
        new Color(0,0,253),
        new Color(0,0,255),
        new Color(0,0,255),
        new Color(0,0,253),
        new Color(0,0,252),
        new Color(0,0,250),
        new Color(0,0,248),
        new Color(0,0,247),
        new Color(0,0,245),
        new Color(0,0,243),
        new Color(0,0,241),
        new Color(0,0,240),
        new Color(0,0,238),
        new Color(0,0,236),
        new Color(0,0,235),
        new Color(0,0,233),
        new Color(0,0,231),
        new Color(0,0,230),
        new Color(0,0,228),
        new Color(0,0,226),
        new Color(0,0,224),
        new Color(0,0,223),
        new Color(0,0,221),
        new Color(0,0,219),
        new Color(0,0,218),
        new Color(0,0,216),
        new Color(0,0,214),
        new Color(0,0,213),
        new Color(0,0,211),
        new Color(0,0,209),
        new Color(0,0,207),
        new Color(0,0,206),
        new Color(0,0,204),
        new Color(0,0,196),
        new Color(0,0,185),
        new Color(0,0,175),
        new Color(0,0,165),
        new Color(0,0,155),
        new Color(0,0,144),
        new Color(0,0,134),
        new Color(0,0,124),
        new Color(0,0,113),
        new Color(0,0,103),
        new Color(0,0,93),
        new Color(0,0,82),
        new Color(0,0,72),
        new Color(0,0,62),
        new Color(0,0,52),
        new Color(0,0,41),
        new Color(0,0,31),
        new Color(0,0,21),
        new Color(0,0,10),
        new Color(0,0,0),
        new Color(10,9,0),
        new Color(21,17,0),
        new Color(31,26,0),
        new Color(41,35,0),
        new Color(52,43,0),
        new Color(62,52,0),
        new Color(72,61,0),
        new Color(82,69,0),
        new Color(93,78,0),
        new Color(103,87,0),
        new Color(113,95,0),
        new Color(124,104,0),
        new Color(134,112,0),
        new Color(144,121,0),
        new Color(155,130,0),
        new Color(165,138,0),
        new Color(175,147,0),
        new Color(185,156,0),
        new Color(196,164,0),
        new Color(204,172,0),
        new Color(206,173,0),
        new Color(207,175,0),
        new Color(209,176,0),
        new Color(211,178,0),
        new Color(213,179,0),
        new Color(214,181,0),
        new Color(216,182,0),
        new Color(218,183,0),
        new Color(219,185,0),
        new Color(221,186,0),
        new Color(223,188,0),
        new Color(224,189,0),
        new Color(226,191,0),
        new Color(228,192,0),
        new Color(230,194,0),
        new Color(231,195,0),
        new Color(233,196,0),
        new Color(235,198,0),
        new Color(236,199,0),
        new Color(238,201,0),
        new Color(240,202,0),
        new Color(241,204,0),
        new Color(243,205,0),
        new Color(245,206,0),
        new Color(247,208,0),
        new Color(248,209,0),
        new Color(250,211,0),
        new Color(252,212,0),
        new Color(253,214,0),
        new Color(255,215,0)
    };
    private int gradientArrayIndex = 0;
    public void yellowBlueGradient() {
        //ladies and gentlemen, i proudly present the NEW and IMPROVED,
        //SMOOTH, ATTRACTIVE yellow-to-blue LED gradient!
        
        //our top software developers have been hard at work testing and
        //debugging and not sleeping all in order to bring you this 
        //masterpiece of a method.

        //okay, enough chit-chat. I don't particularily feel like explaining
        //my code again, but i probably will anyway.

        //in all honesty this new method is pretty dang simple.
        //the difficult was coming up with that gosh darn array which
        //provides the actual RGB color codes. oh well. if you're 
        //interested in how i actually generated the array, put on your
        //javascript hat and check the generateGradientArray.js file 
        //located in the main directory.

        //but javascript is for nerds, anyways. let's focus on actual
        //JAVA:
        setColor(yellowBlueGradientArray[gradientArrayIndex]);
        
        if(gradientArrayIndex + 1 == yellowBlueGradientArray.length) gradientArrayIndex = 0;
        else gradientArrayIndex++;

        //okay. 3 lines of code. fairly simple, huh? i'll explain it anyways,
        //cause why not?
        //so, before we even go into the function, we have our declarations.
        //we declared 2 things: an array and an integer. the array contains the 
        //actual color values that we're going to call, and the integer contains
        //the index that enables us to access the color values inside the array.

        //we intialized the integer to be zero, because arrays start at 0 (duh)
        //i mean, this isn't MATLAB. Java is actually NORMAL, thank goodness.
        //anyways,yeah, we start at 0 and increment that variable each time this 
        //method is called, UNLESS that variable (plus 1) is equal to the length
        //of the array. this is because we can't access the array if our variable
        //is greater than it's length, so the variable needs to be reset. again,
        //pretty intuitive and simple stuff. the setColor() method just commands
        //the LEDS to change to the color defined in the array.

        //if you actually read through all the Frills code, you'd notice that
        //old gradient function. that means you also would've noticed how I had
        //another declaration, a boolean. this pretty much informed the code when  
        //it needed to reverse the arrayIndex int so that we could have the gradient
        //bouncing back and forth.
        //we have no need for a boolean because this time i crammed all the values
        //into a single array, instead of only half the values. this means, instead
        //of calling the whole from start to finish and then calling it from finish
        //to start, we can just call it from start to finish once and be done.
        //it starts as one color and ends at that same color. nice and SMOOTH,
        //which is what the primary goal was.

        //okie dokie, that's it for this method.
    }
    
    public Color[] yellowBlueGradientArrayOLD = new Color[] { 
        new Color(255, 255, 0),
        new Color(252, 252, 2),
        new Color(249, 249, 5),
        new Color(247, 247, 7),
        new Color(244, 244, 10),
        new Color(242, 242, 12),
        new Color(239, 239, 15),
        new Color(236, 236, 18),
        new Color(234, 234, 20),
        new Color(231, 231, 23),
        new Color(229, 229, 25),
        new Color(226, 226, 28),
        new Color(224, 224, 30),
        new Color(221, 221, 33),
        new Color(218, 218, 36),
        new Color(216, 216, 38),
        new Color(213, 213, 41),
        new Color(211, 211, 43),
        new Color(208, 208, 46),
        new Color(206, 206, 48),
        new Color(203, 203, 51),
        new Color(200, 200, 54),
        new Color(198, 198, 56),
        new Color(195, 195, 59),
        new Color(193, 193, 61),
        new Color(190, 190, 64),
        new Color(188, 188, 66),
        new Color(185, 185, 69),
        new Color(182, 182, 72),
        new Color(180, 180, 74),
        new Color(177, 177, 77),
        new Color(175, 175, 79),
        new Color(172, 172, 82),
        new Color(170, 170, 85),
        new Color(167, 167, 87),
        new Color(164, 164, 90),
        new Color(162, 162, 92),
        new Color(159, 159, 95),
        new Color(157, 157, 97),
        new Color(154, 154, 100),
        new Color(151, 151, 103),
        new Color(149, 149, 105),
        new Color(146, 146, 108),
        new Color(144, 144, 110),
        new Color(141, 141, 113),
        new Color(139, 139, 115),
        new Color(136, 136, 118),
        new Color(133, 133, 121),
        new Color(131, 131, 123),
        new Color(128, 128, 126),
        new Color(126, 126, 128),
        new Color(123, 123, 131),
        new Color(121, 121, 133),
        new Color(118, 118, 136),
        new Color(115, 115, 139),
        new Color(113, 113, 141),
        new Color(110, 110, 144),
        new Color(108, 108, 146),
        new Color(105, 105, 149),
        new Color(103, 103, 151),
        new Color(100, 100, 154),
        new Color(97, 97, 157),
        new Color(95, 95, 159),
        new Color(92, 92, 162),
        new Color(90, 90, 164),
        new Color(87, 87, 167),
        new Color(84, 84, 170),
        new Color(82, 82, 172),
        new Color(79, 79, 175),
        new Color(77, 77, 177),
        new Color(74, 74, 180),
        new Color(72, 72, 182),
        new Color(69, 69, 185),
        new Color(66, 66, 188),
        new Color(64, 64, 190),
        new Color(61, 61, 193),
        new Color(59, 59, 195),
        new Color(56, 56, 198),
        new Color(54, 54, 200),
        new Color(51, 51, 203),
        new Color(48, 48, 206),
        new Color(46, 46, 208),
        new Color(43, 43, 211),
        new Color(41, 41, 213),
        new Color(38, 38, 216),
        new Color(36, 36, 218),
        new Color(33, 33, 221),
        new Color(30, 30, 224),
        new Color(28, 28, 226),
        new Color(25, 25, 229),
        new Color(23, 23, 231),
        new Color(20, 20, 234),
        new Color(18, 18, 236),
        new Color(15, 15, 239),
        new Color(12, 12, 242),
        new Color(10, 10, 244),
        new Color(7,7, 247),
        new Color(5,5, 249),
        new Color(2,2, 252),
        new Color(0,0, 255)
    };
    private int gradientArrayIndexOLD = 0;
    private boolean reverseGradientArrayIndexOLD;
    public void yellowBlueGradientOLD() {
        //I was going to use a for() loop, but setting up a delay
        //is too difficult for that type of loop. instead, i'm going 
        //to define an int outside of my method and update that each 
        //time i loop thru, using that variable to as the array index

        //we will cycle thru the array in the updateLED method that is called
        //periodically. the delay is already taken care of in that method; no need
        //to worry about it here.

        //so, the purpose of the method:
        //smoothly change the LEDs from yellow to blue and then back to yellow, and repeat that


        if(gradientArrayIndexOLD == yellowBlueGradientArrayOLD.length - 1) reverseGradientArrayIndexOLD = true;
        if(gradientArrayIndexOLD == 0) reverseGradientArrayIndexOLD = false;

        //ok, so these two checks are at the top for this reason:
        //if the array index has reached the length of the array, then it is time to start
        //decrementing the array index so that we can reverse the color change...
        //and if the array index has reached -1, we need to start incrementing the array
        //index so that we can again "change direction"

        if(reverseGradientArrayIndexOLD) gradientArrayIndexOLD--;
        else gradientArrayIndexOLD++;

        //pretty simple check above, no need to explain. the reason this comes first
        //is because we start at -1 and we end when our array index is == to the array
        //length. we can't have a -1 index so we need to increment that to make it 0...
        //likewise, we can't have an index == to the length of an array, so we need
        //to decrement that to make it equal to array.length -1 (the highest possible index of an array)

        

        //alright, now that we've gone thru all the necessary checks,
        //we can actually start updating the LED colors.

        setColor(yellowBlueGradientArrayOLD[gradientArrayIndexOLD]);
        
        //so what we do there is again fairly simple. we take the index of 
        //the array and pass that value into the setColor method.
        //the value that we're passing into the setColor method is obviously
        //a Color variable... you can check the contents of the array to verify this.

        //and that should be it.
        
    }
        
    public boolean getIsVisionTargetInSight() {
        return (NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv").getDouble(0) > 0.5); // Target visible
    }

    public boolean getIsVisionTargetNotInSight() {
        return (NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv").getDouble(0) < 0.5); //No target
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
