// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

// imports the motors and the motorcontrollers
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;

// imports the differential drive for the drivetrain
import edu.wpi.first.wpilibj.drive.DifferentialDrive;


// Importing subsystems  
import edu.wpi.first.wpilibj2.command.SubsystemBase;

// Imports the contants file
import frc.robot.Constants;

// Imports the encoder
import com.revrobotics.RelativeEncoder;

// The drivetrain class contains the methods that control the seperate parts of the drivetrain
public class Drivetrain extends SubsystemBase {
  private final CANSparkMax m_rightFrontMotor = new CANSparkMax(Constants.c_portRightFrontMotor,MotorType.kBrushless);
  private final CANSparkMax m_rightBackMotor = new CANSparkMax(Constants.c_portRightBackMotor,MotorType.kBrushless);
  private final CANSparkMax m_leftFrontMotor = new CANSparkMax(Constants.c_portLeftFrontMotor,MotorType.kBrushless);
  private final CANSparkMax m_leftBackMotor = new CANSparkMax(Constants.c_portLeftBackMotor,MotorType.kBrushless);

  
  private final MotorControllerGroup m_rightControler = new MotorControllerGroup(m_rightFrontMotor,m_rightBackMotor);
  private final MotorControllerGroup m_leftControler = new MotorControllerGroup(m_leftFrontMotor,m_leftBackMotor);
  private final DifferentialDrive m_drivetrain = new DifferentialDrive(m_leftControler,m_rightControler);
  
  private RelativeEncoder encoder = m_leftBackMotor.getEncoder();

  // This is the constructor
  /** Creates a new ExampleSubsystem. */
  public Drivetrain() {
    
  }
  // This method declares the axes of the drive train
  public void arcadeDrive(double leftYAxis, double rightXAxis) {
    m_drivetrain.arcadeDrive(leftYAxis, rightXAxis);
  }
  // This method is the autonomous code for the robot
  public void autoArcadeDrive() {
    while(encoder.getPosition() < 6) {
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
