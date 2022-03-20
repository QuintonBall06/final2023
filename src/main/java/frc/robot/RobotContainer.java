// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.PS4Controller;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.AutoCommands;
import frc.robot.subsystems.BallMoving;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Limelight;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;


// Importing subsystems  
import edu.wpi.first.wpilibj2.command.SubsystemBase;

// Imports the contants file
import frc.robot.Constants;

import java.util.ResourceBundle.Control;

// Imports the encoder
import com.revrobotics.RelativeEncoder;
import frc.robot.subsystems.Climber;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final BallMoving m_ballMover = new BallMoving();
  private final PS4Controller m_controller = new PS4Controller(0);

  private final Drivetrain drivetrain = new Drivetrain();

  private final Limelight m_limelight = new Limelight(drivetrain);

  private final Climber m_climber = new Climber();
  // private final AutoCommands m_autoCommand = new AutoCommands(drivetrain, m_ballMover, m_limelight);


  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
   
    // Configure the button bindings
    configureButtonBindings();
    configureDefaultCommands();
    // This is the constructor
    
  }
  private boolean armUp = true;
  private boolean intakeOn = false;

  private RunCommand ballIsLife = new RunCommand(
    () -> {
    if (m_controller.getR2Button()){
        m_ballMover.shoot(0.5);
        m_ballMover.index(0.8);
    } 
    if (m_controller.getR2ButtonReleased()){
      m_ballMover.shoot(0);
      m_ballMover.index(0);
    } 
    if (m_controller.getTriangleButtonPressed()){
      if (intakeOn) {
        m_ballMover.intake(0);
        m_ballMover.lifter(-1);
        m_ballMover.index(0);
        intakeOn = false;
      } else {
        m_ballMover.intake(0.6);
        m_ballMover.lifter(0.9);
        m_ballMover.index(0.4);
        intakeOn = true;
      }
    } 
    if (m_controller.getCrossButton()) {
      m_ballMover.intake(-0.4);
      m_ballMover.index(-0.8);
      m_ballMover.lifter(-1);
    } 
    if (m_controller.getCrossButtonReleased()) {
      m_ballMover.intake(0);
      m_ballMover.index(0);
      m_ballMover.lifter(0);
    } 
    if (m_controller.getSquareButtonPressed()) {
      if (armUp){
        m_ballMover.lifter(0.9);
        armUp = false;
      } else {
        m_ballMover.lifter(-1);
        armUp = true;
      }
    } 
  }, m_ballMover);
  private RunCommand climb = new RunCommand(
    () -> {
  if (m_controller.getPOV(0) == 0) {
    m_climber.climbUp();
  } else if (m_controller.getPOV(0) == 180) {
    m_climber.climbDown();
  } else {
    m_climber.stopClimb();
  }
}, m_climber);

  private RunCommand drive = new RunCommand(
    () -> {
      if (m_controller.getL2Button()){
        m_limelight.limeDrive();
      } else if (m_controller.getPOV(0) == 90) {
        drivetrain.arcadeDrive(0, 0.2);
      } else if (m_controller.getPOV(0) == 270) {
      drivetrain.arcadeDrive(0, -0.2);
      } else {
        drivetrain.arcadeDrive(Math.sqrt(m_controller.getLeftY()), Math.sqrt(m_controller.getRightX()));
      }
      
    }, drivetrain);


  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {}

  private void configureDefaultCommands(){
    m_ballMover.setDefaultCommand(ballIsLife);
    drivetrain.setDefaultCommand(drive);
    m_climber.setDefaultCommand(climb);
  }

  
  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    final boolean low = false;
    final int station = 2; 

    // special 3 ball from 2rd station
    if (station == 2 && low == false) {

      //shoot preloaded ball in lower hub
      return new SequentialCommandGroup(new ParallelCommandGroup(new RunCommand(() -> {
          m_ballMover.shoot(0.54);
        }, m_ballMover), new RunCommand(() -> {
          m_ballMover.index(0.4);
        }, m_ballMover)).withTimeout(1), 
        //Drive closer to second ball 
        new RunCommand(() -> {
          drivetrain.driveToDistance(100, 0.7);
        }, drivetrain).withTimeout(2), 
        //drive to get ball, pick up and shoot
        new ParallelCommandGroup(new RunCommand(() -> {
          m_ballMover.shoot(0.64);
        }, m_ballMover), new RunCommand(() -> {
          m_ballMover.index(0.8);
        }, m_ballMover), new RunCommand(() -> {
          m_ballMover.intake(0.6);
        }, m_ballMover), new RunCommand(() -> {
          drivetrain.driveToDistance(30, 0.5);
        }, drivetrain)).withTimeout(1),
        //turn to terminal ball
        new RunCommand(() -> {
          drivetrain.turnWithRadius(20, 0, 0.4, false);
        }, drivetrain).withTimeout(0.5), 
        //drive close to terminal ball
        new RunCommand(() -> {
          drivetrain.driveToDistance(140, 0.7);
        }, drivetrain).withTimeout(3),

        //get closer and grab ball
        new ParallelCommandGroup(new RunCommand(() -> {
          m_ballMover.index(0.2);
        }, m_ballMover), new RunCommand(() -> {
          m_ballMover.intake(0.6);
        }, m_ballMover), new RunCommand(() -> {
          drivetrain.driveToDistance(30, 0.5);
        }, drivetrain)).withTimeout(1),
        //drive back to general shooting area
        new RunCommand(() -> {
          drivetrain.driveToDistance(160, -0.7);
        }, drivetrain).withTimeout(4),

        //shoot 3rd ball
        new ParallelCommandGroup(new RunCommand(() -> {
          m_ballMover.shoot(0.64);
        }, m_ballMover), new RunCommand(() -> {
          m_ballMover.index(0.4);
        }, m_ballMover), new RunCommand(() -> {
          m_limelight.limeDrive();
        }, m_limelight)).withTimeout(1)

        ).withTimeout(15);

      } else if (low == true) {
        return new SequentialCommandGroup(
        //drive to second ball and grab it
        new ParallelCommandGroup(new RunCommand(() -> {
          m_ballMover.index(0.8);
        }, m_ballMover), new RunCommand(() -> {
          m_ballMover.intake(0.6);
        }, m_ballMover), new RunCommand(() -> {
          drivetrain.driveToDistance(50, 0.5);
        }, drivetrain)).withTimeout(3),
        
        //drive to lower hub
        new RunCommand(() -> {
          drivetrain.driveToDistance(140, -0.7);
        }, drivetrain).withTimeout(4),

        //shoot both balls low
        new ParallelCommandGroup(new RunCommand(() -> {
          m_ballMover.index(0.2);
        }, m_ballMover), new RunCommand(() -> {
          m_ballMover.intake(0.6);
        }, m_ballMover), new RunCommand(() -> {
          m_ballMover.shoot(0.5);
        }, m_ballMover)).withTimeout(1),

        //drive bak out just to be safe
        new RunCommand(() -> {
          drivetrain.driveToDistance(160, 0.7);
        }, drivetrain).withTimeout(4)

        ).withTimeout(15);
      } else {
        //shoot preloaded ball low
        return new SequentialCommandGroup(new ParallelCommandGroup(new RunCommand(() -> {
          m_ballMover.shoot(0.54);
        }, m_ballMover), new RunCommand(() -> {
          m_ballMover.index(0.4);
        }, m_ballMover)).withTimeout(1), 
        //drive close to second ball
        new RunCommand(() -> {
          drivetrain.driveToDistance(100, 0.7);
        }, drivetrain).withTimeout(3), 
        //get second ball and shoot
        new ParallelCommandGroup(new RunCommand(() -> {
          m_ballMover.shoot(0.64);
        }, m_ballMover), new RunCommand(() -> {
          m_ballMover.index(0.8);
        }, m_ballMover), new RunCommand(() -> {
          m_ballMover.intake(0.6);
        }, m_ballMover), new RunCommand(() -> {
          drivetrain.driveToDistance(30, 0.5);
        }, drivetrain)).withTimeout(1)
        
        ).withTimeout(15);
      }
  }
}
