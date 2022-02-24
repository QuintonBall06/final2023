// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.Spark;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Shooter extends SubsystemBase {
  private final Spark lMotor = new Spark(Constants.p_LShootMotor);
  private final Spark rMotor = new Spark(Constants.p_RShootMotor);
  

  /** Creates a new ExampleSubsystem. */
  public Shooter() {
    
  }
  
  public void shoot(double speed){
      lMotor.set(speed);
      rMotor.set(speed*-1);
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
