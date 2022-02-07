// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import frc.robot.subsystems.DriveTrain;

public class ADCommand extends CommandBase {
  /** Creates a new SimpleAuto. */
  private DriveTrain m_drivesystem;
  private Joystick m_joystick;
  private double m_speed;
  private double m_rotation;


  public ADCommand(DriveTrain subsystem, Joystick joystick) {
    m_drivesystem = subsystem;
    m_joystick = joystick;
    addRequirements(subsystem);

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_speed = m_joystick.getRawAxis(1)/1.5;
    m_rotation = m_joystick.getRawAxis(4)/1.5;


    m_drivesystem.arcadeDrive(m_speed, m_rotation);
    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }

  
}
