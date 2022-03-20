// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.BallMoving;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Limelight;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.Timer;

/** An example command that uses an example subsystem. */
public class AutoCommands extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final BallMoving m_ballMover;
  private final Drivetrain m_drivetrain;
  private final Limelight m_Limelight;
  private final double kConversion = 1987;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public AutoCommands(Drivetrain _drivetrain, BallMoving _ballMoving, Limelight _limelight) {
    m_drivetrain = _drivetrain;
    m_ballMover = _ballMoving;
    m_Limelight = _limelight;

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(_drivetrain);
    addRequirements(_ballMoving);
    addRequirements(_limelight);

  }

  // // Drive to inches with speed
  // public void driveToDistance(double _dist, double _speed) {

  //   m_drivetrain.setLeftZero();
  //   m_drivetrain.setRightZero();
    
  //   Timer.delay(0.4);

  //   SmartDashboard.putNumber("Left Distance", m_drivetrain.getLeftDistance());
  //   SmartDashboard.putNumber("Right Distance", m_drivetrain.getRightDistance());

  //   while ((Math.abs((m_drivetrain.getLeftDistance()+m_drivetrain.getRightDistance())/2)) < _dist) {
  //     SmartDashboard.putNumber("Left Distance", m_drivetrain.getLeftDistance());
  //     SmartDashboard.putNumber("Right Distance", m_drivetrain.getRightDistance());
  //     m_drivetrain.tankDrive(_speed, _speed);
  //   }

  //   m_drivetrain.tankDrive(0, 0);

  // }

  // // Drive to angle degrees with speed and radius inches and turn left if true
  // public void turnWithRadius(double _angle, double _radius, double _speed, boolean _left) {

  //   m_drivetrain.setLeftZero();
  //   m_drivetrain.setRightZero();

  //   double leftArcLen;
  //   double rightArcLen;

  //   if (_left) {
  //     leftArcLen = (_angle/360)*3.14*_radius;
  //     rightArcLen = (_angle/360)*3.14*(_radius+26);
  //   } else {
  //     leftArcLen = (_angle/360)*3.14*(_radius+26);
  //     rightArcLen = (_angle/360)*3.14*(_radius);
  //   }


  //   Timer.delay(0.4);

  //   SmartDashboard.putNumber("Left Distance", m_drivetrain.getLeftDistance());
  //   SmartDashboard.putNumber("Right Distance", m_drivetrain.getRightDistance());


  //   SmartDashboard.putNumber("Left Arc Length", leftArcLen);
  //   SmartDashboard.putNumber("Right Arc Length", rightArcLen);

  //   if (_left) {

  //     while ((Math.abs(m_drivetrain.getRightDistance())) < Math.abs(rightArcLen)) {
  //       SmartDashboard.putNumber("Left Distance", m_drivetrain.getLeftDistance());
  //       SmartDashboard.putNumber("Right Distance", m_drivetrain.getRightDistance());
  //       m_drivetrain.tankDrive(_speed*(leftArcLen/rightArcLen), _speed);
  //     }

  //   } else {

  //     while ((Math.abs(m_drivetrain.getLeftDistance())) < Math.abs(leftArcLen)) {
  //       SmartDashboard.putNumber("Left Distance", m_drivetrain.getLeftDistance());
  //       SmartDashboard.putNumber("Right Distance", m_drivetrain.getRightDistance());
  //       m_drivetrain.tankDrive(_speed, _speed*(rightArcLen/leftArcLen));

  //     }
  //   }


  //   m_drivetrain.tankDrive(0, 0);

  // }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
