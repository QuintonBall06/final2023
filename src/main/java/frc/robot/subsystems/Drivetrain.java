// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.AlternateEncoderType;
import com.revrobotics.CANEncoder;
// imports the motors and the motorcontrollers
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
// imports the differential drive for the drivetrain
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Solenoid;

// Importing subsystems  
import edu.wpi.first.wpilibj2.command.SubsystemBase;

// Imports the contants file
import frc.robot.Constants;
import edu.wpi.first.wpilibj.Compressor;


// Imports the encoder
import com.revrobotics.RelativeEncoder;

// The drivetrain class contains the methods that control the seperate parts of the drivetrain
public class Drivetrain extends SubsystemBase {
  private final CANSparkMax m_rightFrontMotor = new CANSparkMax(5,MotorType.kBrushless);
  private final CANSparkMax m_rightBackMotor = new CANSparkMax(3,MotorType.kBrushless);
  private final CANSparkMax m_leftFrontMotor = new CANSparkMax(4,MotorType.kBrushless);
  private final CANSparkMax m_leftBackMotor = new CANSparkMax(2,MotorType.kBrushless);
  private final RelativeEncoder m_leftEncoder = m_leftFrontMotor.getEncoder();
  private final RelativeEncoder m_rightEncoder = m_rightFrontMotor.getEncoder();
  
  private final MotorControllerGroup m_rightControler = new MotorControllerGroup(m_rightFrontMotor, m_rightBackMotor);
  private final MotorControllerGroup m_leftControler = new MotorControllerGroup(m_leftFrontMotor, m_leftBackMotor);
  private final DifferentialDrive m_drivetrain = new DifferentialDrive(m_leftControler, m_rightControler);
  //defines Axis Camera
  
  private Solenoid s1,s2; 
  private final Compressor m_compressor;
  private double kP = 0.3;

  // This is the constructor
  /** Creates a new ExampleSubsystem. */
  public Drivetrain() {
    m_compressor = new Compressor(PneumaticsModuleType.CTREPCM);  //Digtial I/O,Relay

    s1 = new Solenoid(PneumaticsModuleType.CTREPCM, 0);                        // Solenoid port
    s2 = new Solenoid(PneumaticsModuleType.CTREPCM, 2);                        // Solenoid port

    m_leftFrontMotor.setInverted(true);
    m_leftBackMotor.setInverted(true);
    m_leftEncoder.setPosition(0);
		m_rightEncoder.setPosition(0);
  }


  // This method declares the axes of the drive train
  public void arcadeDrive(double leftYAxis, double rightXAxis) {
    double speed;
    if (leftYAxis < 0.1 && leftYAxis > -0.1) {
      kP = 0;
      m_drivetrain.arcadeDrive(leftYAxis, rightXAxis);
    } else {
      speed = kP*leftYAxis;
      if (Math.abs(speed) < Math.abs(leftYAxis)) {

          m_drivetrain.arcadeDrive(-speed, rightXAxis);
          if (leftYAxis<0) {
            kP += (leftYAxis+speed)*-0.05;
          } else {
            kP += (leftYAxis-speed)*0.04;
          }
      } else {
          m_drivetrain.arcadeDrive(-speed, rightXAxis);
      }
    }
  }


  public void gearShift() {
    if (s1.get()) {
      s1.set(false);
      s2.set(false);
    } else {
      s1.set(true);
      s2.set(true);
    }
  }

  public void setLeftZero() {
    m_leftEncoder.setPosition(0);
  }

  public void setRightZero() {
		m_rightEncoder.setPosition(0);
  }

  public double getLeftDistance() {
    return m_leftEncoder.getPosition()*Constants.c_encoderRatio;
  }

  public double getRightDistance() {
    return m_rightEncoder.getPosition()*Constants.c_encoderRatio;
  }


  // Drive to inches with speed
  public void driveToDistance(double _dist, double _speed) {

    setLeftZero();
    setRightZero();
    
    // Timer.delay(0.4);

    SmartDashboard.putNumber("Left Distance", getLeftDistance());
    SmartDashboard.putNumber("Right Distance", getRightDistance());

    while ((Math.abs((getLeftDistance()+getRightDistance())/2)) < _dist) {
      SmartDashboard.putNumber("Left Distance", getLeftDistance());
      SmartDashboard.putNumber("Right Distance", getRightDistance());
      m_drivetrain.tankDrive(_speed*1.035, _speed);
    }

    m_drivetrain.tankDrive(0, 0);

  }

  // Drive to angle degrees with speed and radius inches and turn left if true
  public void turnWithRadius(double _angle, double _radius, double _speed, boolean _left) {
    setLeftZero();
    setRightZero();

    double leftArcLen;
    double rightArcLen;

    if (_left) {
      leftArcLen = (_angle/360)*3.14*_radius;
      rightArcLen = (_angle/360)*3.14*(_radius+26);
    } else {
      leftArcLen = (_angle/360)*3.14*(_radius+26);
      rightArcLen = (_angle/360)*3.14*(_radius);
    }


    // Timer.delay(0.4);

    SmartDashboard.putNumber("Left Distance", getLeftDistance());
    SmartDashboard.putNumber("Right Distance", getRightDistance());


    SmartDashboard.putNumber("Left Arc Length", leftArcLen);
    SmartDashboard.putNumber("Right Arc Length", rightArcLen);

    if (_left) {

      while ((Math.abs(getRightDistance())) < Math.abs(rightArcLen)) {
        SmartDashboard.putNumber("Left Distance", getLeftDistance());
        SmartDashboard.putNumber("Right Distance", getRightDistance());
        m_drivetrain.tankDrive(_speed*(leftArcLen/rightArcLen), _speed);
      }

    } else {

      while ((Math.abs(getLeftDistance())) < Math.abs(leftArcLen)) {
        SmartDashboard.putNumber("Left Distance", getLeftDistance());
        SmartDashboard.putNumber("Right Distance", getRightDistance());
        m_drivetrain.tankDrive(_speed, _speed*(rightArcLen/leftArcLen));

      }
    }


    m_drivetrain.tankDrive(0, 0);

  }

  // This method is the autonomous code for the robot
  public void autoArcadeDrive() {
    // while(encoder.getPosition() < 6) {
    //   arcadeDrive(-1, 0);
    // }
    // arcadeDrive(0, 0);
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
