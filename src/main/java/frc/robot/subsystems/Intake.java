// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

// imports the motorcontrols
import edu.wpi.first.wpilibj.motorcontrol.Spark;

// imports the subsystems and the constants
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

// Imports the encoders
import edu.wpi.first.wpilibj.DigitalInput;

// The intake controls the moving of the intake to the ground and back and and the picking up of the ball
public class Intake extends SubsystemBase {
  private final Spark m_pickUpMotor = new Spark(Constants.c_portIntakeSuckerUpper);
  private final Spark m_intakeMover = new Spark(Constants.c_portIntakeMover);
  private final DigitalInput m_topLimit = new DigitalInput(Constants.c_portTopLimit);
  private final DigitalInput m_bottomLimit = new DigitalInput(Constants.c_portBottomLimit);

  /** Creates a new ExampleSubsystem. */
  public Intake() {
    
  }
  // Moves the intake motors up and down and starts the intake picker upper then turns it off
  public void runIntake() {
    if (m_topLimit.get()) {
      while (!m_bottomLimit.get()) {
        m_intakeMover.set(-1);
      }
      m_intakeMover.set(0);
      m_pickUpMotor.set(1);
    }
    else if (m_bottomLimit.get()) {
      m_pickUpMotor.set(0);
      while (!m_topLimit.get()) {
        m_intakeMover.set(1);
      }
      m_intakeMover.set(0);
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
