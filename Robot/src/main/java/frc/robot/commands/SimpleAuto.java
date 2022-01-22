// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Timer;
import frc.robot.subsystems.Sensors;

public class SimpleAuto extends CommandBase {
  /** Creates a new SimpleAuto. */
  Timer timer;
  VictorSPX victorRight = new VictorSPX(3);
  VictorSPX victorLeft = new VictorSPX(1);
  AHRS gyro = Sensors.getGyro();
  double startAngle;
  public SimpleAuto() {
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    timer = new Timer();
    timer.start();
    gyro.reset();
    startAngle = gyro.getAngle();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double currentAngle = gyro.getAngle()-((int)gyro.getAngle()/360*360);
    victorRight.set(ControlMode.PercentOutput, -0.25);
    victorLeft.set(ControlMode.PercentOutput, 0.25);
    if (timer.get() >= 2) {
      if (currentAngle <= startAngle+5 && currentAngle >= startAngle-5){
        victorRight.set(ControlMode.PercentOutput, 0.25);
        victorLeft.set(ControlMode.PercentOutput, 0.25);
      } else {
      victorRight.set(ControlMode.PercentOutput, 0);
      victorLeft.set(ControlMode.PercentOutput, 0);
      }
    }
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
