// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants;

//These are the imports for the Spark Max
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

//These imports are for the pistons
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;


// the Climber class contains all of the methods that control the different parts of the climber,
public class Climber extends SubsystemBase {
    private final CANSparkMax m_winch = new CANSparkMax(Constants.c_portClimberWinch,MotorType.kBrushless);

    private final DoubleSolenoid m_leftLock = new DoubleSolenoid(PneumaticsModuleType.CTREPCM,Constants.c_portLeftLockI, Constants.c_portLeftLockO);
    private final DoubleSolenoid m_rightLock = new DoubleSolenoid(PneumaticsModuleType.CTREPCM,Constants.c_portRightLockI, Constants.c_portRightLockO);

    private final CANSparkMax m_climberHook = new CANSparkMax(Constants.c_portClimberHook,MotorType.kBrushless);

    //This is the constructor 
    public Climber() {

    }

    // This method controls the lock by moving the pistons forward and backward
    // This method moves the piston when a button is pressed
    public void lock() {
        if(m_leftLock.get() == Value.kForward && m_rightLock.get() == Value.kForward) {
            m_leftLock.set(Value.kReverse);
            m_rightLock.set(Value.kReverse);
        } else if(m_leftLock.get() == Value.kReverse && m_rightLock.get() == Value.kReverse) {
            m_leftLock.set(Value.kForward);
            m_rightLock.set(Value.kForward);
        }
    }
    //The winch method sets the speed of the winch, which is passed into the method
    // This method sets the speed when a button is pressed
    public void winch(double motorSpeed) {
      m_winch.set(motorSpeed);
    }
    // The climberMove method controls the speed of the climber hook
    // It does this by setting the speed when a button is pressed, or when a button in not pressed
    public void climberMove(double motorSpeed, boolean L2, boolean R2) {
      m_climberHook.set(motorSpeed);
      if(R2) {
        m_climberHook.set(motorSpeed);
      }
      else if(L2) {
        m_climberHook.set(motorSpeed*-1);
      } else {
        m_climberHook.set(0);
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
