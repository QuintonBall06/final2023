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
//PWM
    public static int p_intakeSuckerUpper = 0;
    public static int p_intakeMover = 1;
    public static int p_indexerUp = 2;
    public static int p_LShootMotor = 3;
    public static int p_RShootMotor = 4;
    public static int p_climber = 5;


//DIO
    public static int p_bottomLimit = 0;
    public static int p_topLimit = 1;
    public static int p_beamBreakerI = 2;
    public static int p_beamBrealerO = 3;
    public static int p_indexerMid = 4;
    public static int p_LShootEncoderI = 5;
    public static int p_LShootEncoderO = 6;
    public static int p_RShootEncoderI = 7;
    public static int p_RShootEncoderO = 8;

//CAN
    public static int p_pidgeon = 0;
    public static int p_gearShift = 1;
    public static int p_leftFrontMotor = 2;
    public static int p_leftBackMotor = 3;
    public static int p_rightFrontMotor = 4; 
    public static int p_rightBackMotor = 5;
    public static int p_climberWinch = 6;
    public static int p_climberLock = 7;
}
