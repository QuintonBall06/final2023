// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

// Import for the spark motor controller
import edu.wpi.first.wpilibj.motorcontrol.Spark;
//Import for the limit switches
import edu.wpi.first.wpilibj.DigitalInput;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

// The indexer class controls when the indexer runs, which carries the balls to the shooter
public class Indexer extends SubsystemBase {
  private final Spark m_indexerUp = new Spark(Constants.c_portIndexerUp);
  private final DigitalInput m_halfLimit = new DigitalInput(Constants.c_portIndexerMid);
  private final DigitalInput m_ballDetector = new DigitalInput(Constants.c_portBeamBreakerI);
  /** Creates a new ExampleSubsystem. */
  // This is the constructor
  public Indexer() {
    
  }
  // The indexer runs automaticlly from the beamBreaker to the limitSwitch
  public void useIndexer() {
    if (m_ballDetector.get() & !m_halfLimit.get()) {
      while (!m_halfLimit.get()) {
        m_indexerUp.set(1);
      }
      m_indexerUp.set(0);
    } else if  (m_ballDetector.get() & m_halfLimit.get()) {
      while (m_halfLimit.get()) {
        m_indexerUp.set(1);
      }
      while (!m_halfLimit.get()) {
        m_indexerUp.set(1);
      }
      m_indexerUp.set(0);
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
