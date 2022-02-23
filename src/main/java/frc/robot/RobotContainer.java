// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.Limelight;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj.PS4Controller;


/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final Drivetrain drivetrain = new Drivetrain();

  private final Shooter shooter = new Shooter();
  private final Intake intake = new Intake();
  private final Indexer indexer = new Indexer();
  private final Limelight limelighter = new Limelight();
  private final PS4Controller controller = new PS4Controller(0);
  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
  }
  private RunCommand drive = new RunCommand(
    () -> drivetrain.arcadeDrive(controller.getLeftY(), controller.getRightX()), drivetrain);
  private RunCommand shootrun = new RunCommand(
    () -> {if (controller.getCrossButtonPressed()) {
      shooter.shoot(0.7);
    } else {
      shooter.shoot(0);
    }
  }, shooter);
  private RunCommand intaker = new RunCommand(
    () -> {if (controller.getTriangleButtonPressed()) {
      intake.runIntake();
    }
  }, intake);
  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {}

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */

  private void configureDefaultCommands() {
		shooter.setDefaultCommand(shootrun);
    intake.setDefaultCommand(intaker);
    drivetrain.setDefaultCommand(drive);
	}

  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return m_autoCommand;
  }
}
