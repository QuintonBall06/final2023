// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import com.revrobotics.RelativeEncoder;

public class Drivetrain extends SubsystemBase {
  private final CANSparkMax leftfrontmotor = new CANSparkMax(Constants.p_leftFrontMotor,MotorType.kBrushless);
  private final CANSparkMax leftbackomotor = new CANSparkMax(Constants.p_leftBackMotor,MotorType.kBrushless);
  private final CANSparkMax rightfrontmotor = new CANSparkMax(Constants.p_rightFrontMotor,MotorType.kBrushless);
  private final CANSparkMax rightbackmotor = new CANSparkMax(Constants.p_rightBackMotor,MotorType.kBrushless);


  private final MotorControllerGroup leftcontroler = new MotorControllerGroup(leftfrontmotor,leftbackomotor);
  private final MotorControllerGroup rightcontroler = new MotorControllerGroup(rightfrontmotor,rightbackmotor);
  private final DifferentialDrive drivetrain = new DifferentialDrive(leftcontroler,rightcontroler);
  
  private RelativeEncoder encoder = leftbackomotor.getEncoder();

  /** Creates a new ExampleSubsystem. */
  public Drivetrain() {
    
  }
  public void arcadeDrive(double leftyaxis, double rightxaxis){
    drivetrain.arcadeDrive(leftyaxis, rightxaxis);
  }

  public void autoArcadeDrive(){
    while(encoder.getPosition() < 6){
      arcadeDrive(-1, 0);
    }
    arcadeDrive(0, 0);
  }
          //run until getPosition returns 6

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
