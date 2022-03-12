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

// This method declares the axes of the drive train

  /** Creates a new ExampleSubsystem. */
  public BallMoving() {
    
    m_limelight = NetworkTableInstance.getDefault().getTable("limelight");
    m_pipeline = m_limelight.getEntry("pipeline");
    m_pipeline.setNumber(2);
  }

  public void limeShoot(){
    
    double ty = m_limelight.getEntry("ty").getDouble(0);

    if (ty < 0 ){
      shootl.set(-0.7);
      shootr.set(0.7);
    } else {
      
      shootl.set(-0.9);
      shootr.set(0.9);
    }
  }

  public void shoot(double speed){
      shootl.set(-speed);
      shootr.set(speed);
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
