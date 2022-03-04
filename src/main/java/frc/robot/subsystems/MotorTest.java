// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

// imports the motors and the motorcontrollers
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;

import edu.wpi.first.wpilibj.motorcontrol.Spark;

// imports the differential drive for the drivetrain
import edu.wpi.first.wpilibj.drive.DifferentialDrive;


// Importing subsystems  
import edu.wpi.first.wpilibj2.command.SubsystemBase;

// Imports the contants file
import frc.robot.Constants;

// Imports the encoder
import com.revrobotics.RelativeEncoder;

// The drivetrain class contains the methods that control the seperate parts of the drivetrain
public class MotorTest extends SubsystemBase {

  private final Spark m_ballIntake = new Spark(Constants.c_portIntakeSuckerUpper);
  private final Spark m_intakeMover = new Spark(Constants.c_portIntakeMover);
  private final Spark m_index = new Spark(Constants.c_portIndexerUp);
  private final Spark m_lShoot = new Spark(Constants.c_portLShootMotor);
  private final Spark m_rShoot = new Spark(Constants.c_portRShootMotor);



  private final CANSparkMax m_rightFrontMotor = new CANSparkMax(Constants.c_portRightFrontMotor,MotorType.kBrushless);
  private final CANSparkMax m_rightBackMotor = new CANSparkMax(Constants.c_portRightBackMotor,MotorType.kBrushless);
  private final CANSparkMax m_leftFrontMotor = new CANSparkMax(Constants.c_portLeftFrontMotor,MotorType.kBrushless);
  private final CANSparkMax m_leftBackMotor = new CANSparkMax(Constants.c_portLeftBackMotor,MotorType.kBrushless);
  private final CANSparkMax m_climbWinch = new CANSparkMax(Constants.c_portClimberWinch,MotorType.kBrushless);
  private final CANSparkMax m_climbHook = new CANSparkMax(Constants.c_portClimberHook,MotorType.kBrushless);

  
  private int count = 0;
  // This is the constructor
  /** Creates a new ExampleSubsystem. */
  public MotorTest() {
    
  }
  
  public void test(double speed){
    if (0 == count){
      m_ballIntake.set(speed);
    }
    else if (1 == count){
      m_intakeMover.set(speed);
    }
    else if (2 == count){
      m_index.set(speed);
    }
    else if (3 == count){
      m_lShoot.set(speed);
    }
    else if (4 == count){
      m_rShoot.set(speed);
    }
    else if (5 == count){
      m_rightFrontMotor.set(speed);
    }
    else if (6 == count){
      m_rightBackMotor.set(speed);
    }
    else if (7 == count){
      m_leftFrontMotor.set(speed);
    }
    else if (8 == count){
      m_leftBackMotor.set(speed);
    }
    else if (9 == count){
      m_climbWinch.set(speed);
    }
    else if (10 == count){
      m_climbHook.set(speed);
    }
  }
  public void incrementCount(){
    count++;
    if(count > 10){
      count = 0;
    }
  }


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
