// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
import edu.wpi.first.wpilibj.motorcontrol.Spark;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;


import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.CounterBase;

public class BallMoving extends SubsystemBase {
  private Spark intakeBall = new Spark(0);
  private Spark intakeLifter = new Spark(1);
  private Spark index = new Spark(2);
  private Spark shootl = new Spark(3);
  private Spark shootr = new Spark(4);
  private final NetworkTable m_limelight;
  private NetworkTableEntry m_pipeline;
public double m_rightCommand;
public double m_leftCommand;

private final Encoder m_ShootEncoder = new Encoder(0, 1, true, CounterBase.EncodingType.k4X);
private double newSpeed = 0.6;

// This method declares the axes of the drive train

  /** Creates a new ExampleSubsystem. */
  public BallMoving() {
    
    m_limelight = NetworkTableInstance.getDefault().getTable("limelight");
    m_pipeline = m_limelight.getEntry("pipeline");
    m_pipeline.setNumber(2);
  }

  public void limeShoot(){
    double ty = m_limelight.getEntry("ty").getDouble(0);
    double yNot = ty*0.01;
    if (ty>3) {
      shoot(yNot+0.7);
    } else {
      shoot(0.7);
    }
  }

  public void stopAll() {
    shoot(0);
    index(0);
    intake(0);
    lifter(-1);
  }

  public void shoot(double speed) {
    //speed > 0.6 shoot high with velocity
    if (Math.abs(m_ShootEncoder.getRate()) < 70 && speed >= 0.6) {
      shootl.set(-newSpeed);
      shootr.set(newSpeed);
      newSpeed += 0.03;
    } else {
      shootl.set(-speed);
      shootr.set(speed);
    }
  }

  public void index(double speed) {
    index.set(-speed);
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
