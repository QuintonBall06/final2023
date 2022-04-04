package frc.robot.subsystems;

// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

// imports the motors and the motorcontrollers
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;

// imports the differential drive for the drivetrain
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.BallMoving;


// Importing subsystems  
import edu.wpi.first.wpilibj2.command.SubsystemBase;

// Imports the contants file
import frc.robot.Constants;

// Imports the encoder
import com.revrobotics.RelativeEncoder;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;

public class Climber extends SubsystemBase {
  // private final CANSparkMax m_winch = new CANSparkMax(6,MotorType.kBrushed); //ppretneding like NEO for now, just change to brushed for sim
  private final CANSparkMax m_winchL = new CANSparkMax(6,MotorType.kBrushed);
  private final CANSparkMax m_winchR = new CANSparkMax(7,MotorType.kBrushed);
  public Climber () {

  }
  // This method declares the axes of the drive train
  public void climbUp() {
    m_winchL.set(0.75);
    m_winchR.set(-0.75);
  }

  public void DownLSlow() {
    m_winchL.set(0.5);
  }

  public void  DownRSlow() {
    m_winchR.set(-0.5);
  }

  public void lClimbUp(){
    m_winchL.set(-0.75);
  }

  public void RClimbUp(){
    m_winchR.set(0.75);
  }

  public void lClimbDown(){
    m_winchL.set(1);
  }

  public void RClimbDown(){
    m_winchR.set(-1);
  }

  public void climbDown() {
    m_winchL.set(-1);
    m_winchR.set(1);
  }

  public void stopClimb() {
    m_winchL.set(0);
    m_winchR.set(0);
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
