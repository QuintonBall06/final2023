// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
import edu.wpi.first.wpilibj.motorcontrol.Spark;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ExampleSubsystem extends SubsystemBase {
  private Spark intakeBall = new Spark(0);
  private Spark intakeLifter = new Spark(1);
  private Spark index = new Spark(2);
  private Spark shootl = new Spark(3);
  private Spark shootr = new Spark(4);

  /** Creates a new ExampleSubsystem. */
  public ExampleSubsystem() {}

  public void shoot(double speed){
      shootl.set(-speed);
      shootr.set(speed);
  }

  public void index(double speed) {
    index.set(speed);
  }

  public void lifter(double speed){
    intakeLifter.set(speed);    
  }

  
  public void intake(double speed){
    intakeBall.set(speed);    
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
