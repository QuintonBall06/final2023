// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class Climber extends SubsystemBase {
  private final CANSparkMax winch = new CANSparkMax(Constants.p_climberWinch,MotorType.kBrushless);

    private final DoubleSolenoid leftLock = new DoubleSolenoid(PneumaticsModuleType.CTREPCM,Constants.p_leftLockI, Constants.p_leftLockO);
    private final DoubleSolenoid rightLock = new DoubleSolenoid(PneumaticsModuleType.CTREPCM,Constants.p_rightLockI, Constants.p_rightLockO);

    private final Spark climber = new Spark(Constants.p_climber);

    //Constructor
    public Climber() {

    }

    // All the other methods
    public void lock(){
        if(leftLock.get() == Value.kForward && rightLock.get() == Value.kForward){
            leftLock.set(Value.kReverse);
            rightLock.set(Value.kReverse);
        } else if(leftLock.get() == Value.kReverse && rightLock.get() == Value.kReverse){
            leftLock.set(Value.kForward);
            rightLock.set(Value.kForward);
        }
    }
    public void winch(double motorspeed){
      winch.set(motorspeed);
    }
    public void climberMove(double motorspeed, boolean L2, boolean R2){
      climber.set(motorspeed);
      if(R2){
        climber.set(motorspeed);
      }
      else if(L2){
        climber.set(motorspeed*-1);
      } else {
        climber.set(0);
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
