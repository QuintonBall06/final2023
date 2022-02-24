// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Indexer extends SubsystemBase {
  private final Spark indexerUp = new Spark(2);
  private final DigitalInput halfLimit = new DigitalInput(Constants.p_indexerMid);
  private final DigitalInput inputSensor = new DigitalInput(Constants.p_beamBreakerI);
  /** Creates a new ExampleSubsystem. */
  public Indexer() {
    
    
  }
  public void useIndexer(){
    if (inputSensor.get() & !halfLimit.get()){
      while (!halfLimit.get()){
        indexerUp.set(1);
      }
      indexerUp.set(0);
    } else if  (inputSensor.get() & halfLimit.get()){
      while (halfLimit.get()){
        indexerUp.set(1);
      }
      while (!halfLimit.get()){
        indexerUp.set(1);
      }
      indexerUp.set(0);
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
