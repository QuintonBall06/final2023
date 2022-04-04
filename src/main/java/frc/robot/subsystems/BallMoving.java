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
private double newSpeed = 0.7;

double yNot = 53;

// This method declares the axes of the drive train

  /** Creates a new ExampleSubsystem. */
  public BallMoving() {
    
    m_limelight = NetworkTableInstance.getDefault().getTable("limelight");
    m_pipeline = m_limelight.getEntry("pipeline");
    m_pipeline.setNumber(2);
  }

  public void limeShoot(){
    double ty = m_limelight.getEntry("ty").getDouble(0);
    yNot = 53+(-ty);
    SmartDashboard.putNumber("ynot", yNot);
  }

  public void stopAll() {
    shoot(0);
    index(0);
    intake(0);
    lifter(-1);
  }



  public void shoot(double speed) {
    //speed > 0.6 shoot high with velocity
    if (speed == 0){
      shootl.set(0);
      shootr.set(0);
      newSpeed = 0.7;
    } else if (speed >0.6) {
    
    if (Math.abs(m_ShootEncoder.getRate()/1000) > (Math.abs(yNot))) {
      shootl.set(-newSpeed);
      shootr.set(newSpeed);
      SmartDashboard.putString("direction", "changing");
      newSpeed += ((m_ShootEncoder.getRate()/1000)/yNot)/80;
    } else if (Math.abs(m_ShootEncoder.getRate()/1000) < Math.abs(yNot)-5) {
      shootl.set(-newSpeed);
      shootr.set(newSpeed);
      SmartDashboard.putString("direction", "changing");
      newSpeed -= ((m_ShootEncoder.getRate()/1000)/yNot)/100;
    } else {
      shootl.set(-newSpeed);
      shootr.set(newSpeed);
      SmartDashboard.putString("direction", "backup");
    }
  }
    SmartDashboard.putNumber("speed", m_ShootEncoder.getRate()/1000);
    SmartDashboard.putNumber("speed error", (m_ShootEncoder.getRate()/1000)/yNot);
    SmartDashboard.putNumber("Motor value", newSpeed);

  }

  public void shootHigh() {
      shootl.set(-0.65);
      shootr.set(0.65);
  }

  public void shootLow(double speed) {
    shootl.set(speed*-1);
    shootr.set(speed);
}


  public void index(double speed) {
    index.set(-speed);
    if (speed == 0) {
      kP = 0.1;
    }
  }

  double kP = 0.1;

  public void indexRamp(double speed) {
    if (Math.abs(kP) < Math.abs(speed)) {
      index(kP);
      kP += (speed*kP)*0.04;
    } else {
      index(kP);
    }
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
