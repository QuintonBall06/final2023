// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.Spark;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
// The shooter class controls how the ball is launched from the robot
public class Shooter extends SubsystemBase {
  private final Spark m_leftMotor = new Spark(Constants.c_portLShootMotor);
  private final Spark m_rightMotor = new Spark(Constants.c_portRShootMotor);
  

  /** Creates a new ExampleSubsystem. */
  // This is the Constructor
  public Shooter() {
    
  }
  // When a button is pressed, the motors are spun in opposite directions so that the ball will be launched
  public void shoot(double speed) {
      m_leftMotor.set(speed);
      m_rightMotor.set(speed*-1);
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
