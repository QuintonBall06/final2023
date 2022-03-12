package frc.robot.subsystems;

// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

// imports the motors and the motorcontrollers
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;

// imports the differential drive for the drivetrain
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.BallMoving;


// Importing subsystems  
import edu.wpi.first.wpilibj2.command.SubsystemBase;

// Imports the contants file
import frc.robot.Constants;

// Imports the encoder
import com.revrobotics.RelativeEncoder;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;

public class Limelight extends SubsystemBase {

  private Drivetrain m_drivetrain;
  private double xVal = 0;
  
	private final NetworkTable m_limelight;
    private NetworkTableEntry m_pipeline;
	public double m_rightCommand;
	public double m_leftCommand;

  // This is the constructor
  /** Creates a new ExampleSubsystem. */
  public Limelight (Drivetrain _drivetrain) {
        m_drivetrain = _drivetrain;
        m_limelight = NetworkTableInstance.getDefault().getTable("limelight");
        m_pipeline = m_limelight.getEntry("pipeline");
        m_pipeline.setNumber(2);
  }
  // This method declares the axes of the drive train
  public void limeDrive() {
    
    double tx = m_limelight.getEntry("tx").getDouble(0);
		SmartDashboard.putNumber("tx", tx);
    double ty = m_limelight.getEntry("ty").getDouble(0);

    double xVal;

    if (tx < 1 && tx > -1){
      xVal = tx/-30;
    } else {
		  xVal = tx/-19;
    }

		SmartDashboard.putNumber("Xvsl", m_leftCommand);

    m_drivetrain.arcadeDrive(0, xVal);
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
