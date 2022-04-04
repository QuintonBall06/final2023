// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.PS4Controller;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.AutoCommands;
import frc.robot.subsystems.BallMoving;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
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
import edu.wpi.first.wpilibj.DigitalInput;
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


  DigitalInput climberLimitSwitch = new DigitalInput(9); //NEED VALUE
  boolean climbPrevUp = false;

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
    if (m_controller.getR1Button()){
        m_ballMover.limeShoot();
        m_ballMover.indexRamp(0.8);
        m_ballMover.shoot(0.7);
    } 
    if (m_controller.getR2Button()){
      m_ballMover.shootLow(0.4);
      m_ballMover.indexRamp(0.8);
    } 
    if (m_controller.getR2ButtonReleased()){
      m_ballMover.shoot(0);
      m_ballMover.index(0);
    }   
    if (m_controller.getL1Button()){
        m_ballMover.shootHigh();
        m_ballMover.indexRamp(0.8);
    }    
    if (m_controller.getL1ButtonReleased()){
      m_ballMover.shoot(0);
      m_ballMover.index(0);
    } 
    if (m_controller.getR1ButtonReleased()){
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
        m_ballMover.intake(1);
        m_ballMover.lifter(0.9);
        m_ballMover.index(0.4);
        intakeOn = true;
      }
    } 
    if (m_controller.getCrossButton()) {
      m_ballMover.intake(-0.4);
      m_ballMover.index(-0.5);
      m_ballMover.lifter(-1);
    } 
    if (m_controller.getCrossButtonReleased()) {
      m_ballMover.intake(0);
      m_ballMover.index(0);
      m_ballMover.lifter(0);
    } 
    if (m_controller.getSquareButtonPressed()) {
      if (armUp){
        m_ballMover.lifter(0.5);
        armUp = false;
      } else {
        m_ballMover.lifter(-1);
        armUp = true;
      }
    } 
    if (m_controller.getTriangleButtonReleased())
        m_ballMover.lifter(0);
        
    if (m_controller.getSquareButtonReleased())
       m_ballMover.lifter(0);
  }, m_ballMover);
  
  // private RunCommand climb = new RunCommand(
  //    () -> {
  // if (m_controller.getPOV(0) == 0) {
  //   if (climberLimitSwitch.get() && climbPrevUp){
  //     m_climber.stopClimb();
  //   } else {
  //     m_climber.climbUp();
  //     climbPrevUp = true;
  //   }
  // } else if (m_controller.getPOV(0) == 180) {
  //   if (climberLimitSwitch.get() && !climbPrevUp){
  //     m_climber.stopClimb();
  //   } else {
  //     m_climber.climbDown();
  //     climbPrevUp = false;
  //   }
  // } else {
  //   m_climber.stopClimb();
  // }
  // }, m_climber);

  


  private RunCommand climb = new RunCommand(
    () -> {
  if (m_controller.getPOV(0) == 0) {
      m_climber.lClimbUp();
    m_climber.RClimbUp();
  } else if (m_controller.getPOV(0) == 180) {
      m_climber.lClimbDown();
      
    m_climber.RClimbDown();
  
  }  else if (m_controller.getPOV(0) == 270) {
    m_climber.DownLSlow();

  }else if (m_controller.getPOV(0) == 90) {
    m_climber.DownRSlow();

  } else {
    m_climber.stopClimb();
  }
  }, m_climber);

  private RunCommand drive = new RunCommand(
    () -> {
      SmartDashboard.putNumber("LeftDistance", drivetrain.getLeftDistance());
      SmartDashboard.putNumber("RightDistance", drivetrain.getRightDistance());
      if(m_controller.getShareButtonPressed()){
        drivetrain.setLeftZero();
        drivetrain.setRightZero();
      }
      if (m_controller.getOptionsButtonPressed()){
        drivetrain.gearShift();
      }
      if (m_controller.getR1Button()){
        m_limelight.limeDrive();
      } else {
          double maxSpeed = 0.9;
          drivetrain.arcadeDrive(m_controller.getLeftY() * maxSpeed, m_controller.getRightX()* maxSpeed/1.75);
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
    final boolean twoBalls = true;

    // special 3 ball from 2rd station
    if (twoBalls == false) {
      return new SequentialCommandGroup(new ParallelCommandGroup(new RunCommand(() -> {
        m_ballMover.limeShoot();
        m_ballMover.shoot(0.7);
        m_ballMover.index(0.8);
      }, m_ballMover)).withTimeout(2)).withTimeout(5);
      } else {
        //shoot preloaded ball high
        return new SequentialCommandGroup(new ParallelCommandGroup(new RunCommand(() -> {
          m_ballMover.limeShoot();
          m_ballMover.shoot(0.7);
          m_ballMover.index(0.8);
        }, m_ballMover)).withTimeout(2.5), 


        

        new RunCommand(() -> {
          m_ballMover.stopAll();
        }, m_ballMover).withTimeout(0.1),

        //get second ball and shoot
        new ParallelCommandGroup(new RunCommand(() -> {
          m_ballMover.index(0.8);
          m_ballMover.intake(1);
          m_ballMover.lifter(0.8);
        }, m_ballMover), new RunCommand(() -> {
          drivetrain.arcadeDrive(-0.45, 0);
        }, drivetrain)).withTimeout(2),

        new RunCommand(() -> {
          drivetrain.arcadeDrive(0, -0.4);;
        }, drivetrain).withTimeout(0.5),

        new RunCommand(() -> {
          drivetrain.arcadeDrive(0, 0.4);;
        }, drivetrain).withTimeout(1),

        new RunCommand(() -> {
          drivetrain.arcadeDrive(0, -0.4);;
        }, drivetrain).withTimeout(0.5),

        new ParallelCommandGroup(new RunCommand(() -> {
          m_ballMover.index(0.4);
          m_ballMover.intake(1);
          m_ballMover.lifter(0.8);
        }, m_ballMover), new RunCommand(() -> {
          drivetrain.arcadeDrive(0.55, 0);
        }, drivetrain)).withTimeout(1.5),

        new RunCommand(() -> {
          drivetrain.arcadeDrive(0, 0);
        }, drivetrain).withTimeout(0.1), 

        new RunCommand(() -> {
          m_ballMover.stopAll();
        }, m_ballMover).withTimeout(0.1),

        
        new ParallelCommandGroup(new RunCommand(() -> {
          m_ballMover.limeShoot();
          m_ballMover.shoot(0.7);
          m_ballMover.index(1);
        }, m_ballMover), new RunCommand(() -> {
          m_limelight.limeDrive();
        }, m_limelight)).withTimeout(3.5),
        
        new RunCommand(() -> {
          drivetrain.arcadeDrive(0, 0);
        }, drivetrain).withTimeout(0.1), 

        new RunCommand(() -> {
          m_ballMover.stopAll();
        }, m_ballMover).withTimeout(0.1),

        
        new RunCommand(() -> {
          drivetrain.arcadeDrive(-0.5, 0);
        }, drivetrain).withTimeout(2)
        
        ).withTimeout(14);
      }
   }
}
