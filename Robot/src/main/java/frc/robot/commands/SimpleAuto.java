// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.DriveTrain;
import edu.wpi.first.wpilibj2.command.CommandBase;
//import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.Timer;


public class SimpleAuto extends CommandBase {
  /** Creates a new SimpleAuto. */
  DriveTrain dt = new DriveTrain();
  Timer timer;

  AnalogGyro gyro = new AnalogGyro(1);

  double currentAngle;
  double errorRate;
  double errorAngle;
  double rightPower = 0.25;
  double leftPower = 0.25;

  public SimpleAuto() {
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    timer = new Timer();
    timer.start();

    gyro.reset();
    gyro.calibrate();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    
    currentAngle = gyro.getAngle();

    System.out.println(Math.round(currentAngle));

    dt.drive(0.5, 0.5);
    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }

  
  public void driveStraight(double heading, double correctionRate){
    errorRate = gyro.getRate();

    dt.drive(leftPower - errorRate, rightPower + errorRate);
  }

  public void setHeadingInplace(double heading) {
    errorAngle = heading - gyro.getAngle();

    if (errorAngle > 180.0) {
      rightPower = -rightPower;
      leftPower = -leftPower;
    }

    dt.drive(leftPower * errorAngle, -rightPower * errorAngle);
  }

  public void setHeadingArc(double heading, double turnRate) {
    errorAngle = heading - gyro.getAngle();

    if (errorAngle > 180.0) {
      turnRate = -turnRate;
    }

    dt.drive(leftPower + turnRate/2.0, -rightPower - turnRate/2.0);

  }
}
