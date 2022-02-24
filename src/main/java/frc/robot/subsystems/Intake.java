// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.PS4Controller;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.DigitalInput;

public class Intake extends SubsystemBase {
  private final Spark pickupmotor = new Spark(Constants.p_intakeSuckerUpper);
  private final Spark intakeMover = new Spark(Constants.p_intakeMover);
  private final DigitalInput toplimit = new DigitalInput(Constants.p_topLimit);
  private final DigitalInput bottomlimit = new DigitalInput(Constants.p_bottomLimit);

  /** Creates a new ExampleSubsystem. */
  public Intake() {
    
  }
  public void runIntake(){
    if (toplimit.get()){
      while (!bottomlimit.get()){
        intakeMover.set(-1);
      }
      intakeMover.set(0);
      pickupmotor.set(1);
    }
    else if (bottomlimit.get()){
      pickupmotor.set(0);
      while (!toplimit.get()){
        intakeMover.set(1);
      }
      intakeMover.set(0);
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
