// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import frc.robot.subsystems.DriveTrain;

public class DPadGyroCommand extends CommandBase {
  /** Creates a new SimpleAuto. */
  private DriveTrain m_drivesystem;
  private Joystick m_joystick;
  private ADXRS450_Gyro m_gyro;
  private double m_rotation;


  public DPadGyroCommand(DriveTrain subsystem, Joystick joystick, ADXRS450_Gyro gyro) {
    m_drivesystem = subsystem;
    m_joystick = joystick;
    m_gyro = gyro;
    addRequirements(subsystem);

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_rotation = 0;

    int dpad_direction = m_joystick.getPOV();
    int gyro_direction = ((int)Math.round(m_gyro.getAngle()))%360;

    System.out.println("Gyro Direction: " + gyro_direction);
    System.out.println("DPAD Direction: " + dpad_direction);
    if(dpad_direction == -1){
        return;
    }
    else{
        if(gyro_direction < ((dpad_direction-5)%360)){
            m_rotation = 0.25;
        }
        else if(gyro_direction > ((dpad_direction+5)%360)){
            m_rotation = -0.25;
        }else{
            m_rotation = 0;
        }
    }

    m_drivesystem.arcadeDrive(0, m_rotation);
    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }

  
}
