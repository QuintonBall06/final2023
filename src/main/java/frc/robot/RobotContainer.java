// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.PS4Controller;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.ExampleCommand;
import frc.robot.subsystems.ExampleSubsystem;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Drivetrain;



// Importing subsystems  
import edu.wpi.first.wpilibj2.command.SubsystemBase;

// Imports the contants file
import frc.robot.Constants;

// Imports the encoder
import com.revrobotics.RelativeEncoder;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  private final PS4Controller m_controller = new PS4Controller(0);

  private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);
  private final Drivetrain drivetrain = new Drivetrain();


  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
   
    // Configure the button bindings
    configureButtonBindings();
    configureDefaultCommands();
    // This is the constructor
    
  }
  
  private RunCommand bhfsi = new RunCommand(
    () -> {
      if (m_controller.getR2Button()){
        m_exampleSubsystem.shoot(0.5);
        m_exampleSubsystem.index(0.8);
    }
      else if (m_controller.getTriangleButton()){
        m_exampleSubsystem.intake(0.4);
        m_exampleSubsystem.lifter(0.4);
        m_exampleSubsystem.index(0.4);
    } else {
      m_exampleSubsystem.intake(0);
      m_exampleSubsystem.lifter(-0.9);
      m_exampleSubsystem.index(0);
      m_exampleSubsystem.shoot(0);
    }
    
  }, m_exampleSubsystem);

  private RunCommand drive = new RunCommand(
    () -> drivetrain.arcadeDrive(m_controller.getLeftY()/1.1, m_controller.getRightX()/1.8), drivetrain);


  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {}

  private void configureDefaultCommands(){
    m_exampleSubsystem.setDefaultCommand(bhfsi);
    drivetrain.setDefaultCommand(drive);
  }

  
  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return m_autoCommand;
  }
}
