// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.Timer;

public class SimpleAuto extends CommandBase {
  /** Creates a new SimpleAuto. */
  Timer timer;
  VictorSPX victorRight = new VictorSPX(3);
  VictorSPX victorLeft = new VictorSPX(1);
  public SimpleAuto() {
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    timer = new Timer();
    timer.start();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    victorRight.set(ControlMode.PercentOutput, -0.25);
    victorLeft.set(ControlMode.PercentOutput, 0.25);
    if (timer.get() >= 2) {
      victorRight.set(ControlMode.PercentOutput, 0);
      victorLeft.set(ControlMode.PercentOutput, 0);
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
