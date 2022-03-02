// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;
/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    public static int c_controllerChannel = 1;
    
//PWM
    public static int c_portIntakeSuckerUpper = 0;
    public static int c_portIntakeMover = 1;
    public static int c_portIndexerUp = 2;
    public static int c_portLShootMotor = 3;
    public static int c_portRShootMotor = 4;



//DIO
    public static int c_portBottomLimit = 0;
    public static int c_portTopLimit = 1;
    public static int c_portBeamBreakerI = 2;
    public static int c_portBeamBrealerO = 3;
    public static int c_portIndexerMid = 4;
    public static int c_portLShootEncoderI = 5;
    public static int c_portLShootEncoderO = 6;
    public static int c_portRShootEncoderI = 7;
    public static int c_portRShootEncoderO = 8;

//CAN
    public static int c_portPidgeon = 0;
    public static int c_pnuematicsControlModule = 1;
    public static int c_portRightFrontMotor = 2; 
    public static int c_portRightBackMotor = 3;
    public static int c_portLeftFrontMotor = 4;
    public static int c_portLeftBackMotor = 5;
    public static int c_portClimberWinch = 6;
    public static int c_portClimberHook = 7;

// PCM
    public static int c_portGearShiftI = 0;
    public static int c_portGearShiftO = 1;
    public static int c_portLeftLockI = 4;
    public static int c_portLeftLockO = 5;
    public static int c_portRightLockI = 6;
    public static int c_portRightLockO = 7;


}
