// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.Joystick;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class DriveTrain extends SubsystemBase {
  private CANSparkMax m_leftmotor;
  private CANSparkMax m_rightmotor;

  private CANSparkMax m_leftInnerMotor;
  private CANSparkMax m_rightInnerMotor;
  /** Creates a new ExampleSubsystem. */
  public DriveTrain() {
    m_leftmotor = new CANSparkMax(11, MotorType.kBrushless);
    m_leftInnerMotor = new CANSparkMax(12, MotorType.kBrushless);
    m_rightInnerMotor = new CANSparkMax(13, MotorType.kBrushless);
    m_rightmotor = new CANSparkMax(14, MotorType.kBrushless);

    m_rightmotor.setInverted(true);
    m_rightInnerMotor.setInverted(true);

    m_leftmotor.setInverted(false);
    m_leftInnerMotor.setInverted(false);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void drive(double speedLeft, double speedRight){
    m_leftmotor.set(speedLeft);
    m_leftInnerMotor.set(speedLeft);
    m_rightInnerMotor.set(speedRight);
    m_rightmotor.set(speedRight);
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
