// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class DriveTrain extends SubsystemBase {
  private CANSparkMax m_leftmotor;
  private CANSparkMax m_rightmotor;

  private CANSparkMax m_leftInnerMotor;
  private CANSparkMax m_rightInnerMotor;
  
  private DifferentialDrive diffdrive;
  private MotorControllerGroup mcg_left;
  private MotorControllerGroup mcg_right;
  /** Creates a new ExampleSubsystem. */
  public DriveTrain() {
    m_leftmotor = new CANSparkMax(Constants.REV_OUTER_LEFT, MotorType.kBrushless);
    m_leftInnerMotor = new CANSparkMax(Constants.REV_INNER_LEFT, MotorType.kBrushless);
    m_rightInnerMotor = new CANSparkMax(Constants.REV_INNER_RIGHT, MotorType.kBrushless);
    m_rightmotor = new CANSparkMax(Constants.REV_OUTER_RIGHT, MotorType.kBrushless);

    m_rightmotor.setInverted(true);
    m_rightInnerMotor.setInverted(true);
    mcg_left = new MotorControllerGroup(m_leftmotor, m_leftInnerMotor);

    m_leftmotor.setInverted(false);
    m_leftInnerMotor.setInverted(false);
    mcg_right = new MotorControllerGroup(m_rightmotor, m_rightInnerMotor);

    diffdrive = new DifferentialDrive(mcg_left, mcg_right);


  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
  public void drive(double speed, double rotation){
    diffdrive.arcadeDrive(speed, rotation);
  }

  /*
  public void drive(double speedLeft, double speedRight){
    m_leftmotor.set(speedLeft);
    m_leftInnerMotor.set(speedLeft);
    m_rightInnerMotor.set(speedRight);
    m_rightmotor.set(speedRight);
  }
  */

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
